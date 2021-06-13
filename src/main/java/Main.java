package main.java;

import main.java.dao.ArtistDAO;
import main.java.dao.PlaylistDAO;
import main.java.dao.UserDAO;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.dto.UserDTO;
import main.java.exception.ForbiddenAccessException;
import main.java.exception.NotFoundException;
import main.java.exception.ValidationException;
import main.java.interfaces.ICountryService;
import main.java.interfaces.IUserService;
import main.java.models.Artist;
import main.java.models.Content;
import main.java.models.Playlist;
import main.java.models.User;
import main.java.services.*;
import main.java.utils.DateTime;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //AuthService authService = new AuthService();

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

        //PlaylistDAO
        //Pidiendo una por id
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findOne(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Pidiendo por nombre
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findByName("o"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Pidiendo por user
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findByUserId(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Borrando una playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.delete(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Creando una playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.create(new PlaylistDTO(1, "chill", 1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Metiendole contenido a playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.
                    insertContent(1, 1, new ContentDTO(134,"100", 5, "Reggaeton", "Spanish")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Metiendole lista de contenido a playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            List<ContentDTO> dto = new ArrayList<>();
            for(int i = 145; i < 162; i++) {
                dto.add(new ContentDTO(i, "a", 5, "a", "a"));
            }
            System.out.println(playlistService.
                    insertContent(1,1, dto));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //Actualizando una playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.update(new PlaylistDTO(1,"Chota", 8)));
        } catch (SQLException | ForbiddenAccessException throwables) {
            throwables.printStackTrace();
        }*/
        //Borrar contenido de la playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.deleteContent(1,154));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
