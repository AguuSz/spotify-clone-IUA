package main.java.dto;

import main.java.models.Device;
import main.java.models.Playlist;
import main.java.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String name;
    private String lastName;
    private String email;
    private String birthdate;
    private String password;
    private String country;
    private List<User> friends = new ArrayList<>();
    private List<Device> devices = new ArrayList<>();
    private List<String> languagesPreferences = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();

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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device dev) {
        this.devices.add(dev);
    }

    public List<String> getLanguagesPreferences() {
        return languagesPreferences;
    }

    public void setLanguagesPreferences(List<String> languagesPreferences) {
        this.languagesPreferences = languagesPreferences;
    }

    public void addLanguage(String lan) {
        this.languagesPreferences.add(lan);
    }

    public List<Playlist> getPlayLists() {
        return playlists;
    }

    public void setPlayLists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlayList(Playlist pll) {
        this.playlists.add(pll);
    }

}
