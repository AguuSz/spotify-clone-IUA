package main.java.models;

import main.java.dto.UserDTO;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String birthdate;
    private String password;
    private String country;
    private List<User> friends;
    private List<Device> devices;
    private List<String> languagesPreferences;
    private List<Playlist> playlists;

    public User() {}

    public User (UserDTO dto){
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.birthdate = dto.getBirthdate();
        this.password = dto.getPassword();
        this.country = dto.getCountry();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        this.devices.add(device);
    }

    public List<String> getLanguagesPreferences() {
        return languagesPreferences;
    }

    public void setLanguagesPreferences(List<String> languagesPreferences) {
        this.languagesPreferences = languagesPreferences;
    }

    public void addLanguagePreference(String languagePreference) {
        this.languagesPreferences.add(languagePreference);
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(lastName, user.lastName)
                && email.equals(user.email)
                && Objects.equals(birthdate, user.birthdate)
                && Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, email, birthdate, country);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", friends=" + friends +
                ", devices=" + devices +
                ", languagesPreferences=" + languagesPreferences +
                '}';
    }
}
