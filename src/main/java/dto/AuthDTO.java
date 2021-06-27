package dto;

import interfaces.Validable;

import java.util.Objects;

public class AuthDTO implements Validable {
    String email;
    String password;
    String country;

    @Override
    public boolean isValid() {
        return (email != null && !email.isEmpty() && password != null && !password.isEmpty());
    }

    public boolean isValidForRegister() {
        return (email != null && !email.isEmpty() && password != null && !password.isEmpty() && country != null && !country.isEmpty());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDTO authDTO = (AuthDTO) o;
        return Objects.equals(email, authDTO.email) && Objects.equals(password, authDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
