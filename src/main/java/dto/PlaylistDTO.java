package dto;

import models.Content;
import models.Playlist;

import java.sql.Timestamp;
import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private Timestamp createdAt;
    private int userId;
    private List<Content> content;

    public PlaylistDTO (Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.createdAt = playlist.getCreatedAt();
        this.userId = playlist.getUserId();
        this.content = playlist.getContentList();
    }

    public PlaylistDTO (String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public PlaylistDTO (int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
