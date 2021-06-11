package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.dto.UserDTO;
import main.java.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    public User findOne(int id) throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country` WHERE `id_user` = ?;";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

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
                user.setPassword(rs.getString("password"));
            }
        }
        return user;
    }

    public User findByEmail(String email) throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country` WHERE `email` = ?;";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);

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
                user.setPassword(rs.getString("password"));
            }
        }
        return user;
    }

    public List<User> findByName(String name) throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country` WHERE `name` = ?;";
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<User>();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + name + "%");

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
                users.add(user);
            }
        }
        return users;
    }

    public List<User> findByCountry(String country) throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country` WHERE `country`.`name` = ?;";
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<User>();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, country);

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
                users.add(user);
            }
        }
        return users;
    }

    public List<User> getAll() throws SQLException {
        String QUERY = "SELECT * FROM `user` INNER JOIN `country` ON `user`.`id_country` = `country`.`id_country`;";
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<User>();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);

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
                users.add(user);
            }
        }
        return users;
    }

    public User create(UserDTO userDTO) throws SQLException {
        String QUERY = "INSERT INTO `user` (name, last_name, email, birthdate, password, id_country) VALUES (?, ?, ?, ?, ?, ?);";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDTO.getName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getBirthdate());
            preparedStatement.setString(5, userDTO.getPassword());
            preparedStatement.setString(6, userDTO.getCountry());   // TODO: Fixear para que no reciba una string y reciba un int haciendo referencia a un pais

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            user = new User(userDTO);
        }
        return user;
    }

    public User update(UserDTO userDTO) throws SQLException {
        String QUERY = "UPDATE `user` SET name = ?, SET last_name = ?, SET email = ?, SET birthdate = ?, SET password = ?, SET id_country = ? WHERE id_user = ?;";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDTO.getName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getBirthdate());
            preparedStatement.setString(5, userDTO.getPassword());
            preparedStatement.setString(6, userDTO.getCountry());   // TODO: Fixear para que no reciba una string y reciba un int haciendo referencia a un pais
            preparedStatement.setInt(7, userDTO.getId());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            user = new User(userDTO);
        }
        return user;
    }

    public User delete(int id) throws SQLException {
        String QUERY = "DELETE FROM `user` WHERE (`id_user` = ?);";
        Connection connection = dataSource.getConnection();
        User user = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            user = findOne(id);
            preparedStatement.executeUpdate();
        }
        return user;
    }

}
