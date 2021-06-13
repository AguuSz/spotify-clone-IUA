package main.java.services;

import main.java.dao.UserDAO;
import main.java.dto.UserDTO;
import main.java.exception.*;
import main.java.models.User;

import java.sql.SQLException;

public class AuthService {
    UserDAO userDAO = new UserDAO();

    public User login(String email, String password) throws SQLException, EmailNotRegisteredException {
        if (!userExists(email)) throw new EmailNotRegisteredException("El usuario no existe. Intente registrarse primero.");
        User user = this.userDAO.findByEmail(email);

        if (PasswordService.checkPassword(password, user.getPassword())) {
            // Si matchean, entonces es porque el login es correcto
            return user;
        } else {
            System.out.println("Credenciales incorrectas.");
            return null;
        }
    }

    public User register(UserDTO userDTO) throws SQLException, EmailAlreadyUsedException, InvalidEmailException, InvalidPasswordException {
        // Checkeamos si el email ya existe en la db
        if (userExists(userDTO.getEmail())) throw new EmailAlreadyUsedException("El correo ya se ha registrado anteriormente. Intente con otro.");

        if (didFieldsPassValidation(userDTO.getEmail(), userDTO.getPassword())) {
            userDTO.setPassword(PasswordService.hashPassword(userDTO.getPassword()));

            return this.userDAO.create(userDTO);
        }
        return null;
    }

    private boolean userExists(String email) throws SQLException {
        User user = this.userDAO.findByEmail(email);
        return (user == null);
    }

    private boolean didFieldsPassValidation(String email, String password) throws InvalidEmailException, InvalidPasswordException {
        /*
        Condiciones para la password:
            - Minimo 6 caracteres
            - Minimo una letra minuscula
            - Minimo 1 digito
         */

        if (password.length() < 6) throw new InvalidPasswordException("La password debe tener mas de 6 caracteres.");
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])$")) throw new InvalidPasswordException("La password debe tener minimo 1 digito, y 1 letra minuscula por lo menos.");

        if (!email.matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")) throw new InvalidEmailException("Ingrese un correo valido.");

        return true;
    }
}
