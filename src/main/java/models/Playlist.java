package models;

import dto.PlaylistDTO;
import interfaces.Validable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist implements Validable {
    private int id;
    private int userId;
    private String name;
    private Timestamp createdAt;
    private List<Content> contentList = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(PlaylistDTO dto) {
        this.name = dto.getName();
    }

    @Override
    public boolean isValid() {
        if (name == null || userId == 0) return false;
        return !name.isEmpty();
    }

    public boolean isValidForUpdates() {
        if (id == 0 || userId == 0) return false;
        return isValid();
    }

    public boolean isValidForInsertingContent() {
        if (id == 0 || userId == 0) return false;
        return !contentList.isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public void addContent(Content content) {
        this.contentList.add(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return name.equals(playlist.name) && contentList.equals(playlist.contentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contentList);
    }

    @Override
    public String toString() {
        return "\nPlaylist {" +
                "\n\tId = " + id +
                "\n\tName = " + name +
                "\n\tContentList = " + contentList + "\n}";
    }
}
