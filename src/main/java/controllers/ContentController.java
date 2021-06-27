package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Content;
import models.Playlist;
import services.ContentService;
import spark.*;
import utils.JSONTransformer;

import java.util.List;

public class ContentController {

    private static ContentService contentService = new ContentService();

    public static Route findOne = (Request request, Response response) -> {
        response.type("application/json");
        int contentId = Integer.parseInt(request.params(":contentId"));
        Content content = contentService.findOne(contentId);
        return JSONTransformer.toJson(content);
    };

    public static Route findByName = (Request request, Response response) -> {
        response.type("application/json");
        List<Content> content = contentService.findByName(request.params(":name"));
        return JSONTransformer.toJson(content);
    };
    public static Route findByGenre = (Request request, Response response) -> {
        response.type("application/json");
        List<Content> content = contentService.findByGenre(request.params(":genre"));
        return JSONTransformer.toJson(content);
    };
    public static Route findByArtist = (Request request, Response response) -> {
        response.type("application/json");
        List<Content> content = contentService.findByArtist(request.params(":artist"));
        return JSONTransformer.toJson(content);
    };
    public static Route findByLanguage = (Request request, Response response) -> {
        response.type("application/json");
        List<Content> content = contentService.findByLanguage(request.params(":language"));
        return JSONTransformer.toJson(content);
    };
    
    public static Route playContent = (Request request, Response response) -> {
        response.type("application/json");
        Content content = contentService.playContent(Integer.parseInt(request.params(":userId")), Integer.parseInt(request.params(":contentId")));
        return JSONTransformer.toJson(content);
    };
}
