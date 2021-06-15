package main.java;

import main.java.dao.ArtistDAO;
import main.java.dao.PlaylistDAO;
import main.java.dao.UserDAO;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.dto.UserDTO;
import main.java.exception.*;
import main.java.interfaces.IArtistService;
import main.java.interfaces.IContentService;
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

        AuthService authService = new AuthService();

        // Registrando un usuario
        System.out.println("Registramos 4 usuarios");
        try {
            UserDTO userDTO = new UserDTO("El charky", "Camargo", "elcharky@gmail.com", DateTime.now(), "passwordop", "Argentina");
            User user = authService.register(userDTO);
            System.out.println(user);
        } catch(Exception e){
            System.out.println(e);
        }

        try {
            UserDTO userDTO = new UserDTO("Agustin", "Sepulveda", "agus.sepu92@gmail.com", DateTime.now(), "gorogil123", "Argentina");
            authService.register(userDTO);
            System.out.println(userDTO);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            UserDTO userDTO = new UserDTO("Agustin", "Sanguesa", "agussanguesa@gmail.com", DateTime.now(), "eaegordao23", "Argentina");
            authService.register(userDTO);
            System.out.println(userDTO);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            UserDTO userDTO = new UserDTO("Aurelio", "Garcia", "aurebidart@gmail.com", DateTime.now(), "aure2002", "Arg");
            authService.register(userDTO);
            System.out.println(userDTO);
        } catch (Exception e) {
            System.out.println(e);
        }


        // Loggeando al usuario

        System.out.println("Logueando con el usuario de id 1");

        try {
            User user2 = authService.login("elcharky@gmail.com", "passwordop");
            System.out.println(user2);
        } catch (Exception e) {
            System.out.println(e);
        }

        // UserService
        System.out.println("Pidiendo detalles de un usuario por id (1)");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.findOne(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Pidiendo detalles de un usuario por email (elcharky@gmail.com)");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.findOneByEmail("elcharky@gmail.com"));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Pidiendo detalles de un usuario por nombre

        System.out.println("Pidiendo detalles de un usuario por nombre (Agustin)");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.findByName("Agustin"));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Agregando amigo

        System.out.println("Agregando un amigo (id 1 con 2, 2 con 3 y 4 con 1)");
       try {
           IUserService userService = new UserService();
           System.out.println(userService.addFriend(1,2));
           System.out.println(userService.addFriend(2,3));
           System.out.println(userService.addFriend(4,1));
        } catch (Exception e) {
           System.out.println(e);
        }

        // Pidiendo lista de amigos
        System.out.println("Pidiendo lista de amigos del usuario 1");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.getFriendsList(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Eliminando amigo

        System.out.println("Eliminando de amigos el usuario de id 1 y 2");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.deleteFriend(1,2));
        } catch (Exception e) {
            System.out.println(e);
        }



        // Escuchando un tema
        System.out.println("Escuchando cancion con usuario 1");
        try{
            IContentService contentService = new ContentService();
            System.out.println(contentService.playContent(1,140));
        }catch(Exception e){
            System.out.println(e);
        }

        // Pidiendo actividad
        System.out.println("Mostrando actividad del usuario 1");
        try {
            IUserService userService = new UserService();
            System.out.println(userService.getActivity(1));
        } catch (Exception e) {
            System.out.println(e);
        }



        // Pidiendo artistas de un pais

        System.out.println("Pidiendo artistas de un pais");

        try{
            IArtistService artistService = new ArtistService();
            System.out.println(artistService.findByCountry("Argentina"));
        }catch(Exception e){
            System.out.println(e);
        }

        //CountryService
        // Pidiendo por id
        System.out.println("Pidiendo pais por id 1");
        try {
            ICountryService countryService = new CountryService();
            System.out.println(countryService.findOne(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Pidiendo por nombre
        System.out.println("Pidiendo pais por nombre");
        try {
                ICountryService countryService = new CountryService();
                System.out.println(countryService.findByName("Col"));
            } catch (Exception e) {
            System.out.println(e);
        }

        //PlaylistDAO

        //Pidiendo una por id

        System.out.println("Pedimos que nos muestre la playlist de id 1");

        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findOne(1));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Pidiendo por nombre

        System.out.println("Pedimos una playlist por nombre (MiPlaylist)");
        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findByName("MiPlaylist"));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Pidiendo por user
        System.out.println("Pedimos las playlist del usuario 1");
        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.findByUserId(1));
        } catch (Exception e) {
            System.out.println(e);
        }


        //Borrando una playlist
        System.out.println("Borramos la playlist 'Gym' de id 3");
        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.delete(3));
        } catch (Exception e) {
            System.out.println(e);
        }
        //Creando una playlist
        System.out.println("Creamos la playlist 'Chill'");
        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.create(new PlaylistDTO(1, "chill", 1)));
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Le metemos canciones a la playlist 'Chill'");
        //Metiendole contenido a playlist

        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.insertContent(4, 1, 128));
            System.out.println(playlistService.insertContent(4, 1, 129));
            System.out.println(playlistService.insertContent(4, 1, 130));
        } catch (Exception e) {
            System.out.println(e);
        }
        //Metiendole lista de contenido a playlist
        System.out.println("Metemos una lista de canciones a una playlist");
        try {
            PlaylistService playlistService = new PlaylistService();
            List<Integer> contentList = new ArrayList<>();
            for(int i = 145; i < 162; i++) {
                contentList.add(i);
            }
            System.out.println(playlistService.insertContent(4,1, contentList));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Actualizando una playlist

        //TODO No funciona, no valida que el usuario es dueno de la playlist
        System.out.println("Cambiamos el nombre de la playlist 'Chill' a 'Relax'");
        try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.update(new PlaylistDTO(4,"Relax", 1)));
        } catch (Exception e) {
            System.out.println(e);
        }

        //Borrar contenido de la playlist
        /*try {
            PlaylistService playlistService = new PlaylistService();
            System.out.println(playlistService.deleteContent(1,154));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
