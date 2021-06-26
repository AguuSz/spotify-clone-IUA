package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PlaylistDTO;
import exception.ForbiddenAccessException;
import exception.ValidationException;
import models.Playlist;
import services.PlaylistService;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.JSONTransformer;

import java.util.List;

public class PlaylistController {

    private static PlaylistService playlistService = new PlaylistService();

    public static Route create = (Request request, Response response) -> {
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (request.body() == null) throw new Exception();
            Playlist json = mapper.readValue(request.body(), Playlist.class);
            if (!json.isValid()) throw new Exception();
            return JSONTransformer.toJson(playlistService.create(new PlaylistDTO(json)));
        } catch (ValidationException validationException) {
            response.status(500);
            return "Los datos no son validos.";
        } catch (Exception e) {
            response.status(500);
            return "El JSON no fue valido";
        }
    };

    public static Route delete = (Request request, Response response) -> {
        response.type("application/json");
        int playlistId = Integer.parseInt(request.params(":playlistId"));
        Playlist playlist = playlistService.delete(playlistId);
        return JSONTransformer.toJson(playlist);
    };

    public static Route update = (Request request, Response response) -> {
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (request.body() == null) throw new Exception();
            Playlist json = mapper.readValue(request.body(), Playlist.class);
            if (!json.isValidForUpdates()) throw new Exception();
            return JSONTransformer.toJson(playlistService.update(new PlaylistDTO(json)));
        } catch (ForbiddenAccessException forbiddenAccessException) {
            response.status(401);
            return "La playlist a la que intenta acceder no es de su propiedad.";
        } catch (Exception e) {
            response.status(500);
            return "El JSON no fue valido";
        }
    };

    public static Route insertContent = (Request request, Response response) -> {
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (request.body() == null) throw new Exception();
            Playlist json = mapper.readValue(request.body(), Playlist.class);
            if (!json.isValidForInsertingContent()) throw new Exception();
            return JSONTransformer.toJson(playlistService.insertContent(new PlaylistDTO(json)));
        } catch (ForbiddenAccessException forbiddenAccessException) {
            return "La playlist a la que intenta acceder no es de su propiedad.";
        } catch (Exception e) {
            response.status(500);
            return "El JSON no fue valido";
        }
    };

    public static Route getAll = (Request request, Response response) -> {
        response.type("application/json");
        return JSONTransformer.toJson(playlistService.getAll());
    };

    public static Route getMostListenedGenreByUserId = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        return JSONTransformer.toJson(playlistService.getMostListenedGenrePlaylist(userId));
    };

    public static Route getMostListenedSongsByUserId = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        return JSONTransformer.toJson(playlistService.getMostListenedSongsPlaylistByUserId(userId));
    };

    public static Route findOne = (Request request, Response response) -> {
        response.type("application/json");
        int playlistId = Integer.parseInt(request.params(":playlistId"));
        Playlist playlist = playlistService.findOne(playlistId);
        return JSONTransformer.toJson(playlist);
    };

    public static Route findByName = (Request request, Response response) -> {
        response.type("application/json");
        List<Playlist> playlists = playlistService.findByName(request.params(":name"));
        return JSONTransformer.toJson(playlists);
    };

    public static Route findByUserId = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        List<Playlist> playlists = playlistService.findByUserId(userId);
        return JSONTransformer.toJson(playlists);
    };
}
