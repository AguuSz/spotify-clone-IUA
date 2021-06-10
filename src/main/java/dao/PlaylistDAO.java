package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.conf.JDBCUtil;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.models.Playlist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  CREATE
    public Playlist create(PlaylistDTO playlistDTO) throws SQLException {
        String QUERY = "INSERT INTO Playlist (name) " + "VALUES( ? );";
        Connection connection = dataSource.getConnection();

        Playlist playlist = new Playlist(playlistDTO);

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playlistDTO.getName());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                playlist.setId(rs.getInt(1));
            }
        }
        return playlist;
    }

    //  READ
    public Playlist getOne(int id) throws SQLException {
        String QUERY = "SELECT id_playlist, name " + "FROM playlist " + "WHERE id_playlist = ?;";
        Connection connection = dataSource.getConnection();
        Playlist playlist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                playlist = new Playlist();
                playlist.setId(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
            }
        }
        return playlist;
    }

    public List<Playlist> list() throws SQLException {

        String QUERY = "SELECT id_playlist, name " +
                "FROM playlist;";

        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw e;
        }

        return playlists;
    }

    public List<Playlist> find(String data) throws SQLException {
        String QUERY = "SELECT id_playlist, name " +
                "FROM playlist " +
                "WHERE name LIKE '%?%';";

        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

            preparedStatement.setString(1, data);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw e;
        }

        return playlists;
    }

    //  UPDATE
    public boolean update(int id, Playlist playlist) throws SQLException {
        String QUERY = "UPDATE Playlist" +
                "SET name = ? " +
                "WHERE id_playlist = ?;";

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setString(2, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
        return true;
    }

    // TODO: Rellenar contenido
    public boolean insertContent(int id, ContentDTO dto) {
        return true;
    }

    // DELETE
    public boolean delete(int id) throws SQLException {
        String QUERY = "DELETE FROM Playlist" +
                "WHERE id_playlist = ?;";

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, String.valueOf(id));

            int result = preparedStatement.executeUpdate();

            System.out.println("Number of rows affected " + result);

        } catch (SQLException e) {
            throw e;
        }
        return true;
    }
}
