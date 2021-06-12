package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.models.Country;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {

    private DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    public Country findOne(int id) throws SQLException {
        String QUERY = "SELECT * FROM `country` WHERE `id_country` = ?;";
        Connection connection = dataSource.getConnection();
        Country country = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                country = new Country();
                country.setId(rs.getInt("id_country"));
                country.setName(rs.getString("name"));
            }
        }
        return country;
    }

    public List<Country> findByName(String name) throws SQLException {
        String QUERY = "SELECT * FROM `country` WHERE `name` LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Country> countryList = new ArrayList<>();
        Country country = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + name + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                country = new Country();
                country.setId(rs.getInt("id_country"));
                country.setName(rs.getString("name"));
                countryList.add(country);
            }
        }
        return countryList;
    }
}
