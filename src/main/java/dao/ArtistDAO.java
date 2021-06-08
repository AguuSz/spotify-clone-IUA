package main.java.dao;

import main.java.conf.JDBCUtil;
import main.java.models.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    //  READ
    public Artist getOne(int id) throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country " +
                "FROM artist INNER JOIN country ON artist.id_country = country.id_country " +
                "WHERE id_artist = ?;";

        Artist artist = null;

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY)) {

            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                artist = new Artist();
                artist.setId(rs.getInt("id_artist"));
                artist.setName(rs.getString("stage_name"));
                artist.setCountry(rs.getString("country"));
            }

        } catch (SQLException e) {
            throw e;
        }

        return artist;
    }

    public List<Artist> list() throws SQLException {

        String QUERY = "SELECT id_artist, stage_name, country " +
                "FROM content;";

        List<Artist> artistList = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

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

    public List<Artist> find(String data) throws SQLException {
        String QUERY = "SELECT id_artist, stage_name, country " +
                "FROM artist INNER JOIN country ON artist.idcountry = country.idcountry" +
                "WHERE name LIKE '%?%';";

        List<Artist> artistList = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

            preparedStatement.setString(1, data);

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
}
