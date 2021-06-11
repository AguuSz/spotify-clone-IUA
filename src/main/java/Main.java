package main.java;

import main.java.dto.UserDTO;
import main.java.exception.NotFoundException;
import main.java.models.User;
import main.java.services.AuthService;
import main.java.utils.Formatter;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();

        // Registrando un usuario
        /*
        try {
            UserDTO userDTO = new UserDTO("El charky", "Camargo", "elcharky@gmail.com", Formatter.formatDate(new Date()), "passwordop", "1");
            User user = authService.register(userDTO);
            System.out.println(user);
        } catch (SQLException e) {
            System.out.println(e);
        }

         */

        // Loggeando al usuario
        try {
            User user2 = authService.login("elcharky@gmail.com", "passwordop");
            System.out.println(user2);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


}
