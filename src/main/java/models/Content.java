package main.java.models;

import java.util.List;
import java.util.Objects;

public class Content {
    private int id;
    private String name;
    private int length;
    private String genre;
    private String language;
    private List<Artist> artists;
    private List<String> albums;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return length == content.length && name.equals(content.name) && Objects.equals(genre, content.genre) && Objects.equals(language, content.language) && Objects.equals(artists, content.artists) && Objects.equals(albums, content.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, genre, language, artists, albums);
    }

    @Override
    public String toString() {
        return "Content:" +
                "-id:" + id +
                "-name:'" + name + '\'' +
                "-length:" + length +
                "-genre:" + genre + '\'' +
                "-language:'" + language + '\'' +
                "-artists:" + artists +
                "-albums:" + albums +
                '\'';
    }
}
