package dto;

import interfaces.Validable;
import models.Artist;

import java.sql.Timestamp;
import java.util.List;

public class ContentDTO implements Validable {
    private int id;
    private String name;
    private int length;
    private String genre;
    private String language;
    private Timestamp date;
    private List<Artist> artists;
    private List<String> albums;

    public ContentDTO(){
    }

    public ContentDTO(int id, String name, int length, String genre, String language) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.genre = genre;
        this.language = language;
    }

    @Override
    public boolean isValid() {
        return id != 0;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public void addAlbum(String album) {
        this.albums.add(album);
    }
}
