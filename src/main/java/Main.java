package main.java;

import main.java.dao.ArtistDAO;
import main.java.dao.UserDAO;
import main.java.dto.UserDTO;
import main.java.exception.NotFoundException;
import main.java.exception.ValidationException;
import main.java.interfaces.ICountryService;
import main.java.interfaces.IUserService;
import main.java.models.Artist;
import main.java.models.User;
import main.java.services.ArtistService;
import main.java.services.AuthService;
import main.java.services.CountryService;
import main.java.services.UserService;
import main.java.utils.Formatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();

        // Registrando un usuario
        /*try {
            UserDTO userDTO = new UserDTO("El charky", "Camargo", "elcharky@gmail.com", Formatter.formatDate(new Date()), "passwordop", "1");
            User user = authService.register(userDTO);
            System.out.println(user);
        } catch (SQLException e) {
            System.out.println(e);
        }*/

        // Pidiendo artistas de un pais


        // Loggeando al usuario
        /*try {
            User user2 = authService.login("elcharky@gmail.com", "passwordop");
            System.out.println(user2);
        } catch (SQLException e) {
            System.out.println(e);
        }*/

        //UserService
        // Pidiendo detalles de un usuario por id
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.findOne(3));
        } catch (SQLException | ValidationException throwables) {
            throwables.printStackTrace();
        }*/

        // Pidiendo detalles de un usuario por email
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.findOneByEmail("elcharky@gmail.com"));
        } catch (SQLException | ValidationException throwables) {
            throwables.printStackTrace();
        }*/

        // Pidiendo detalles de un usuario por nombre
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.findByName("Aure"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        // Creando usuario TODO {todavia falta probar}
        /*try {
            IUserService userService = new UserService();
            UserDTO dto = new UserDTO("Agustin",
                    "Sanguesa", "sanguesa030@alumnos.com",
                    new Date(2002, 01, 01), "salgruesa", "Argentina");
            System.out.println(userService.createOne(dto));
        } catch (SQLException | ValidationException throwables) {
            throwables.printStackTrace();
        }*/

        // Agregando amigo
       /*try {
           IUserService userService = new UserService();
           System.out.println(userService.addFriend(3,2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        // Eliminando amigo
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.deleteFriend(1,2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        // Pidiendo lista de amigos
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.getFriendsList(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        // Pidiendo actividad
        /*try {
            IUserService userService = new UserService();
            System.out.println(userService.getActivity(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/


        //CountryService
        // Pidiendo por id
        /*try {
            ICountryService countryService = new CountryService();
            System.out.println(countryService.findOne(1));
        } catch (ValidationException | SQLException throwables) {
            throwables.printStackTrace();
        }*/

        //Pidiendo por nombre
        /*try {
                ICountryService countryService = new CountryService();
                System.out.println(countryService.findByName("Col"));
            } catch (ValidationException | SQLException throwables) {
                throwables.printStackTrace();
            }*/
    }
}
