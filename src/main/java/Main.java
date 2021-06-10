package main.java;

import main.java.dao.AuthDAO;
import main.java.exception.NotFoundException;
import main.java.models.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        AuthDAO authDAO = new AuthDAO();

        try {
            User user = authDAO.login("agus.sepu2000@hotmail.com", "testtest");
            if (user == null) throw new NotFoundException("Error 404. Usuario no encontrado.");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } catch (NotFoundException notFoundException) {
            System.out.println(notFoundException.getMessage());
        }

    }


}
