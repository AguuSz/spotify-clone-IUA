package main.java.models;

import main.java.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private List<Content> contentList = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(PlaylistDTO pll) {
        this.name = pll.getName();
        this.contentList = pll.getContentList();
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
}
