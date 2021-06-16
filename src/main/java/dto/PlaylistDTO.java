package main.java.dto;

import main.java.exception.ValidationException;
import main.java.utils.Validate;

import java.sql.Date;
import java.sql.Timestamp;

public class PlaylistDTO {
    private int id;
    private String name;
    private Timestamp createdAt;
    private int userId;

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
}
