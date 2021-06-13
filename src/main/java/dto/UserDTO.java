package main.java.dto;

import java.sql.Timestamp;

public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private Timestamp birthdate;
    private String password;
    private String country;

    public UserDTO(String name, String lastName, String email, Timestamp birthdate, String password, String country) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthdate =birthdate;
        this.password = password;
        this.country = country;
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

    public void setEmail(String email)  {
        this.email = email;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
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

}
