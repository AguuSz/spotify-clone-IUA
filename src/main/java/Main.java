import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.AuthController;
import controllers.PlaylistController;
import controllers.ArtistController;
import dto.AuthDTO;
import utils.Paths;

import java.nio.file.Path;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        port(port);
        System.out.println("Servidor escuchando en: localhost:" + port);

        // Setteando las rutas

        // Auth
        post(Paths.Auth.login, AuthController.login);
        post(Paths.Auth.register, AuthController.register);

        // Playlists
        get(Paths.Playlists.getAll, PlaylistController.getAll);
        get(Paths.Playlists.findOne, PlaylistController.findOne);
        get(Paths.Playlists.findByName, PlaylistController.findByName);
        get(Paths.Playlists.findByUserId, PlaylistController.findByUserId);
        get(Paths.Playlists.getMostListenedGenreByUserId, PlaylistController.getMostListenedGenreByUserId);
        get(Paths.Playlists.getMostListenedSongsByUserId, PlaylistController.getMostListenedSongsByUserId);
        post(Paths.Playlists.create, PlaylistController.create);
        post(Paths.Playlists.insertContent, PlaylistController.insertContent);
        delete(Paths.Playlists.delete, PlaylistController.delete);
        put(Paths.Playlists.update, PlaylistController.update);

        // Artists
        get(Paths.Artists.getAll, ArtistController.getAll);
        get(Paths.Artists.findOne, ArtistController.findOne);
        get(Paths.Artists.findByName, ArtistController.findByName);
        get(Paths.Artists.findByCountry, ArtistController.findByCountry);
    }
}
