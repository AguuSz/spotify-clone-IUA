package main.java.models;

import java.util.Objects;

public class Artist {
    private int id;
    private String name;
    private String country;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return name.equals(artist.name) && country.equals(artist.country);
    }

    @Override
    public String toString() {
        return "\nArtist { " +
                "\n\tId = " + id +
                "\n\tName = " + name +
                "\n\tCountry = " + country + "\n}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
