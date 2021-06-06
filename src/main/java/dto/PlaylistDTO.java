package main.java.dto;

import main.java.models.Content;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private String name;
    private List<Content> contentList = new ArrayList<>();

    public PlaylistDTO (String name) {
        this.name = name;
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
