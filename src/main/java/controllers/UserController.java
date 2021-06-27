package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import exception.ValidationException;
import models.User;
import services.UserService;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.JSONTransformer;

public class UserController {
    private static final UserService userService = new UserService();

    public static Route findOne = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        return JSONTransformer.toJson(userService.findOne(userId));
    };

    public static Route findOneByEmail = (Request request, Response response) -> {
        response.type("application/json");
        String email = request.params(":email");
        return JSONTransformer.toJson(userService.findOneByEmail(email));
    };

    public static Route findByName = (Request request, Response response) -> {
        response.type("application/json");
        String name = request.params(":name");
        return JSONTransformer.toJson(userService.findByName(name));
    };

    public static Route update = (Request request, Response response) -> {
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(request.body());
            if (request.body() == null) throw new ValidationException("Body null");
            User json = mapper.readValue(request.body(), User.class);
            if (!json.isValid()) throw new ValidationException("Invalid body");
            return JSONTransformer.toJson(userService.updateOne(new UserDTO(json)));
        } catch (ValidationException validationException) {
            response.status(500);
            return validationException;
        }
    };

    public static Route delete = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        return JSONTransformer.toJson(userService.deleteOne(userId));
    };

    public static Route getActivity = (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params(":userId"));
        return JSONTransformer.toJson(userService.getActivity(userId));
    };

}
