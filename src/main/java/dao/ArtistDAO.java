package dao;

import conf.DataSourceFactory;
import models.Artist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Artist findOne(int id) throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country.id_country, country.name FROM artist INNER JOIN country ON artist.id_country = country.id_country WHERE id_artist = ?;";
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
                artist.setCountry(rs.getString("name"));
            }

        }

        return artist;
    }

    public List<Artist> findByName(String name) throws SQLException {
        String QUERY = "SELECT id_artist, stage_name, country.id_country, country.name FROM artist INNER JOIN country ON artist.id_country = country.id_country WHERE stage_name LIKE ?;";
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
                artist.setCountry(rs.getString("name"));
                artistList.add(artist);
            }
        }
        return artistList;
    }

    public List<Artist> findByCountry(String country) throws SQLException {
        String QUERY = "SELECT * FROM artist INNER JOIN country ON artist.id_country = country.id_country WHERE country.name LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Artist> artistList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setString(1, "%" + country + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id_artist"));
                artist.setName(rs.getString("stage_name"));
                artist.setCountry(rs.getString("country.name"));
                artistList.add(artist);
            }
        }
        return artistList;
    }

    public List<Artist> getAll() throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country.name FROM artist INNER JOIN country ON country.id_country = artist.id_country;";
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
                artist.setCountry(rs.getString("name"));
                artistList.add(artist);
            }
        }
        return artistList;
    }
}
