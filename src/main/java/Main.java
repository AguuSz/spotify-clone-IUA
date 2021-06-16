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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        AuthService authService = new AuthService();
        IUserService userService = new UserService();
        IContentService contentService = new ContentService();
        IArtistService artistService = new ArtistService();
        ICountryService countryService = new CountryService();
        PlaylistService playlistService = new PlaylistService();

        Scanner scanner = new Scanner(System.in);
        String timeout;
        User user2 = null;

        try {
            System.out.println("Registramos 4 usuarios");
            Thread.sleep(3000);

            UserDTO userDTO = new UserDTO("El charky", "Camargo", "elcharky@gmail.com", DateTime.now(), "passwordop", "Argentina");
            User user = authService.register(userDTO);
            System.out.println(user);

            // ----------------------------------------------
            userDTO = new UserDTO("Agustin", "Sepulveda", "agus.sepu92@gmail.com", DateTime.now(), "gorogil123", "Argentina");
            authService.register(userDTO);
            System.out.println(userDTO);

            // ----------------------------------------------
            userDTO = new UserDTO("Agustin", "Sanguesa", "agussanguesa@gmail.com", DateTime.now(), "eaegordao23", "Argentina");
            authService.register(userDTO);
            System.out.println(userDTO);

            // ----------------------------------------------
            userDTO = new UserDTO("Aurelio", "Garcia", "aurebidart@gmail.com", DateTime.now(), "aure2002", "Arg");
            authService.register(userDTO);
            System.out.println(userDTO);

            // ----------------------------------------------
            System.out.println("Logueando con el usuario de id 1");
            Thread.sleep(3000);

            user2 = authService.login("elcharky@gmail.com", "passwordop");
            System.out.println(user2);

            // ----------------------------------------------
            scanner.next();

            // UserService
            System.out.println("Pidiendo detalles de un usuario por id (1)");
            Thread.sleep(3000);
            System.out.println(userService.findOne(user2.getId()));

            scanner.next();

            System.out.println("Pidiendo detalles de un usuario por email (elcharky@gmail.com)");
            Thread.sleep(3000);
            System.out.println(userService.findOneByEmail("elcharky@gmail.com"));

            //Pidiendo detalles de un usuario por nombre
            System.out.println("Pidiendo detalles de un usuario por nombre (Agustin)");
            Thread.sleep(3000);
            System.out.println(userService.findByName("Agustin"));

            scanner.next();

            // Agregando amigo
            System.out.println("Agregando un amigo (id 1 con 2, 2 con 3 y 4 con 1)");
            Thread.sleep(3000);
            System.out.println(userService.addFriend(user2.getId(),2));
            System.out.println(userService.addFriend(2,3));
            System.out.println(userService.addFriend(4,user2.getId()));

            scanner.next();

            // Pidiendo lista de amigos
            System.out.println("Pidiendo lista de amigos del usuario 1");
            Thread.sleep(3000);
            System.out.println(userService.getFriendsList(user2.getId()));

            scanner.next();

            // Eliminando amigo
            System.out.println("Eliminando de amigos el usuario de id 1 y 2");
            Thread.sleep(3000);
            System.out.println(userService.deleteFriend(user2.getId(),2));

            scanner.next();

            // Escuchando un tema
            System.out.println("Escuchando cancion con usuario 1");
            Thread.sleep(3000);
            System.out.println(contentService.playContent(user2.getId(),140));

            scanner.next();

            // Pidiendo actividad
            System.out.println("Mostrando actividad del usuario 1");
            Thread.sleep(3000);
            System.out.println(userService.getActivity(user2.getId()));

            scanner.next();

            // Pidiendo artistas de un pais
            System.out.println("Pidiendo artistas de un pais");
            Thread.sleep(3000);
            System.out.println(artistService.findByCountry("Argentina"));

            scanner.next();

            //CountryService

            // Pidiendo por id
            System.out.println("Pidiendo pais por id 1");
            Thread.sleep(3000);
            System.out.println(countryService.findOne(1));

            scanner.next();

            //Pidiendo por nombre
            System.out.println("Pidiendo pais por nombre");
            Thread.sleep(3000);
            System.out.println(countryService.findByName("Col"));

            scanner.next();

            //PlaylistDAO

            //Pidiendo una por id
            System.out.println("Pedimos que nos muestre la playlist de id 1");
            Thread.sleep(3000);
            System.out.println(playlistService.findOne(1));

            scanner.next();

            //Pidiendo por nombre
            System.out.println("Pedimos una playlist por nombre (MiPlaylist)");
            Thread.sleep(3000);
            System.out.println(playlistService.findByName("MiPlaylist"));

            scanner.next();

            //Pidiendo por user
            System.out.println("Pedimos las playlist del usuario 1");
            Thread.sleep(3000);
            System.out.println(playlistService.findByUserId(user2.getId()));

            scanner.next();

            //Borrando una playlist
            System.out.println("Borramos la playlist 'Gym' de id 3");
            Thread.sleep(3000);
            System.out.println(playlistService.delete(3));

            scanner.next();

            //Creando una playlist
            System.out.println("Creamos la playlist 'Chill'");
            Thread.sleep(3000);
            System.out.println(playlistService.create(new PlaylistDTO("Chill", 1)));

            scanner.next();

            //Metiendole contenido a playlist
            System.out.println("Le metemos canciones a la playlist 'Chill'");
            Thread.sleep(3000);
            System.out.println(playlistService.insertContent(4, user2.getId(), 128));
            System.out.println(playlistService.insertContent(4, user2.getId(), 129));
            System.out.println(playlistService.insertContent(4, user2.getId(), 130));

            scanner.next();

            //Metiendole lista de contenido a playlist
            System.out.println("Metemos una lista de canciones a una playlist");
            Thread.sleep(3000);
            List<Integer> contentList = new ArrayList<>();
            for(int i = 145; i < 162; i++) {
                contentList.add(i);
            }
            System.out.println(playlistService.insertContent(4,user2.getId(), contentList));

            scanner.next();

            // Actualizando una playlist
            System.out.println("Cambiamos el nombre de la playlist 'Chill' a 'Relax' del usuario");
            Thread.sleep(3000);
            System.out.println(playlistService.update(new PlaylistDTO(4,"Relax", user2.getId())));

            scanner.next();

            // Obtener una recomendacion de playlist en base al genero mas escuchado del user
            System.out.println("Generamos una playlist en base al genero mas escuchado del usuario");
            Thread.sleep(3000);
            System.out.println(playlistService.getMostListenedGenrePlaylist(user2.getId()));

            scanner.next();

            // Obtener una recomendacion de playlist en base a nuestro top 20 de temas mas escuchados
            System.out.println("Generamos una playlist en base a las canciones mas escuchadas del usuario");
            Thread.sleep(3000);
            System.out.println(playlistService.getMostListenedSongsPlaylistByUserId(user2.getId()));

        } catch(Exception e) {
            System.out.println(e);
        }



    }
}
