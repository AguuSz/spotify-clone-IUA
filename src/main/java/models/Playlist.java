package main.java.models;

import main.java.dto.PlaylistDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private String name;
    private Date createdAt;
    private List<Content> contentList = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(PlaylistDTO pll) {
        this.name = pll.getName();
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
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contentList=" + contentList +
                '}';
    }
}
