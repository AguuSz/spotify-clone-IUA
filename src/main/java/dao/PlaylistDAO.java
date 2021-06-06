package main.java.dao;

import main.java.conf.JDBCUtil;
import main.java.models.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    public Playlist getOne(int id) {

        String QUERY = "SELECT id-playlist, name " +
                "FROM playlist " +
                "WHERE id-playlist = ?;";

        Playlist playlist = null;

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY)){

            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);

            ResultSet rs =  preparedStatement.executeQuery();
            while(rs.next()) {
                playlist = new Playlist();
                playlist.setId(rs.getInt("id-playlist"));
                playlist.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return playlist;
    }

    public List<Playlist> list() {

        String QUERY = "SELECT id-playlist, name " +
                "FROM playlist ";

        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);){

            System.out.println(preparedStatement);

            ResultSet rs =  preparedStatement.executeQuery();
            while(rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id-playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return playlists;
    }

    public Playlist create(Playlist playlist) {
        String QUERY = "INSERT INTO Playlist (name) " +
                "VALUES( ? );";
        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);){


            preparedStatement.setString(1, playlist.getName());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()) {
                playlist.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return playlist;
    }

    public boolean update(int id, Playlist playlist) {
        String QUERY = "UPDATE Playlist" +
                "SET name = ? " +
                "WHERE id-playlist = ?;";

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);){

            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setString(2, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        String QUERY = "DELETE FROM Playlist" +
                "WHERE id-playlist = ?;";

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);){

            preparedStatement.setString(1, String.valueOf(id));

            int result = preparedStatement.executeUpdate();

            System.out.println("Numero of rows affected " + result);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
