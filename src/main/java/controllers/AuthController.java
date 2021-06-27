package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AuthDTO;
import dto.UserDTO;
import exception.*;
import services.AuthService;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.JSONTransformer;

public class AuthController {

    private static AuthService authService = new AuthService();

    public static Route login = (Request request, Response response) -> {
        /*
            {
                "email": "testUser@gmail.com",
                "password": "verystrongpassword"
            }
        */
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (request.body() == null) throw new Exception();
            String requestBody = request.body();
            AuthDTO json = mapper.readValue(requestBody, AuthDTO.class);
            if (!json.isValid()) throw new Exception();
            return JSONTransformer.toJson(authService.login(json.getEmail(), json.getPassword()));
        } catch (EmailNotRegisteredException emailNotRegisteredException) {
            response.status(500);
            return "El email no se ha sido registrado.";
        } catch (InvalidPasswordException invalidPasswordException){
            response.status(500);
            return "Credenciales incorrectas.";
        } catch (Exception e) {
            response.status(500);
            return "El JSON no fue valido";
        }
    };

    public static Route register = (Request request, Response response) -> {
        /*
            {
                "email": "testUser@gmail.com",
                "password": "verystrongpassword",
                "country": "arg"
            }
        */
        response.type("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (request.body() == null) throw new Exception();
            String requestBody = request.body();
            AuthDTO json = mapper.readValue(requestBody, AuthDTO.class);
            if (!json.isValidForRegister()) throw new Exception();
            UserDTO userDTO = new UserDTO(json);
            return JSONTransformer.toJson(authService.register(userDTO));
        } catch (EmailAlreadyUsedException emailAlreadyUsedException) {
            response.status(500);
            return "Los datos no son validos.";
        } catch(InvalidPasswordException invalidPasswordException) {
            return "Credenciales invalidas.";
        } catch (Exception e) {
            response.status(500);
            return "El JSON no fue valido";
        }
    };
}
