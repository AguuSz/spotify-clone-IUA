package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.models.Artist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Artist getOne(int id) throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country FROM artist INNER JOIN country ON artist.id_country = country.id_country WHERE id_artist = ?;";
        Connection connection = dataSource.getConnection();
        Artist artist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                artist = new Artist();
                artist.setId(rs.getInt("id_artist"));
                artist.setName(rs.getString("stage_name"));
                artist.setCountry(rs.getString("country"));
            }

        }

        return artist;
    }

    public List<Artist> findByName(String name) throws SQLException {
        String QUERY = "SELECT id_artist, stage_name, country FROM artist INNER JOIN country ON artist.idcountry = country.idcountry WHERE name LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Artist> artistList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setString(1, "%" + name + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id_artist"));
                artist.setName(rs.getString("stage_name"));
                artist.setCountry(rs.getString("country"));
                artistList.add(artist);
            }

        } catch (SQLException e) {
            throw e;
        }

        return artistList;
    }

    public List<Artist> getAll() throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country FROM content;";
        Connection connection = dataSource.getConnection();
        List<Artist> artistList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id_artist"));
                artist.setName(rs.getString("stage_name"));
                artist.setCountry(rs.getString("country"));
                artistList.add(artist);
            }

        }

        return artistList;
    }
}
