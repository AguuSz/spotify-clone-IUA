package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.dto.UserDTO;
import main.java.models.User;

import javax.sql.DataSource;
import java.sql.*;

// Clase encargada de comunicarse con la BD. Encargada de hacer logins y registers

public class AuthDAO {
    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    public User login(String email, String password) throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country` WHERE `email` = ? AND password = ?;";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setCountry(rs.getString("country.name"));
            }
        }
        return user;
    }

    // TODO: Hacer cuando este listo el service y DAO del usuario.
//    public User register(UserDTO user) {
//
//    }


}
