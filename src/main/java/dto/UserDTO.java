package main.java.dto;

import main.java.exception.ValidationException;
import main.java.utils.Validate;

import java.sql.Date;

public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private Date birthdate;
    private String password;
    private String country;

    public UserDTO(String name, String lastName, String email, Date birthdate, String password, String country) throws ValidationException {
        this.name = Validate.validateString(name);
        this.lastName = Validate.validateString(lastName);
        this.email = Validate.validateEmail(email);
        this.birthdate = Validate.validateDate(birthdate);
        this.password = Validate.validatePassword(password);
        this.country = Validate.validateString(country);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationException {
        this.id = Validate.validateId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        this.name = Validate.validateString(name);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws ValidationException {
        this.lastName = Validate.validateString(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationException {
        this.email = Validate.validateEmail(email);
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) throws ValidationException {
        this.birthdate = Validate.validateDate(birthdate);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ValidationException {
        this.password = Validate.validatePassword(password);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws ValidationException {
        this.country = Validate.validateString(country);
    }

}
