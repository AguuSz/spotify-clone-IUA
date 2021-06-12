package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.models.Playlist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Playlist findOne(int id) throws SQLException {
        String QUERY = "SELECT id_playlist, name FROM playlist WHERE id_playlist = ?;";
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

    public List<Playlist> findByName(String name) throws SQLException {
        String QUERY = "SELECT * FROM playlist WHERE name LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Playlist> playlists = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "%" + name + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }

        }

        return playlists;
    }

    public List<Playlist> getAll() throws SQLException {

        String QUERY = "SELECT id_playlist, name FROM playlist;";
        Connection connection = dataSource.getConnection();
        List<Playlist> playlists = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }

        }
        return playlists;
    }

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

    //  UPDATE
    public Playlist update(PlaylistDTO playlistDTO) throws SQLException {
        String QUERY = "UPDATE Playlist SET name = ? WHERE id_playlist = ?;";
        Connection connection = dataSource.getConnection();
        Playlist playlist;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setInt(2, playlistDTO.getId());

            preparedStatement.executeUpdate();

            playlist = new Playlist(playlistDTO);
        }

        return playlist;
    }

    // DELETE
    public boolean delete(int id) throws SQLException {
        String QUERY = "DELETE FROM Playlist WHERE id_playlist = ?;";
        Connection connection = dataSource.getConnection();
        Playlist playlist;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);
            playlist = findOne(id);
        }

        return true;
    }

    // TODO: Rellenar contenido
    public boolean insertContent(int id, ContentDTO dto) {
        return true;
    }
}
