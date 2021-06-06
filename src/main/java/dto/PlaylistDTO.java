package main.java.dto;

import main.java.models.Content;
import main.java.models.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private String name;
    private List<Content> contentList = new ArrayList<>();

    public PlaylistDTO (String name) {
        this.name = name;
    }

    public PlaylistDTO (String name, List<Content> contentList) {
        this.name = name;
        this.contentList = contentList;
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

}
