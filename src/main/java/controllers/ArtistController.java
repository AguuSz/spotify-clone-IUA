package controllers;

import models.Artist;
import services.ArtistService;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.JSONTransformer;

import java.util.List;

public class ArtistController {

    private static ArtistService artistService = new ArtistService();

    public static Route findOne = (Request request, Response response) -> {
        response.type("application/json");
        int artistId = Integer.parseInt(request.params(":artistId"));
        Artist artist = artistService.findOne(artistId);
        return JSONTransformer.toJson(artist);
    };

    public static Route findByName = (Request request, Response response) -> {
        response.type("application/json");
        List<Artist> artists = artistService.findByName(request.params(":name"));
        return JSONTransformer.toJson(artists);
    };

    public static Route findByCountry = (Request request, Response response) -> {
        response.type("application/json");
        List<Artist> artists = artistService.findByCountry(request.params(":country"));
        return JSONTransformer.toJson(artists);
    };

    public static Route getAll = (Request request, Response response) -> {
        response.type("application/json");
        return JSONTransformer.toJson(artistService.getAll());
    };

}
