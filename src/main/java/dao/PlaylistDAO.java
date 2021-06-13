package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.models.Content;
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
                playlist.setContentList(findContentByPlayList(playlist.getId(), connection));
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
                playlist.setContentList(findContentByPlayList(playlist.getId(), connection));
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
                playlist.setContentList(findContentByPlayList(playlist.getId(), connection));
                playlists.add(playlist);
            }
        }
        return playlists;
    }

    //  CREATE
    public Playlist create(PlaylistDTO playlistDTO) throws SQLException {
        String QUERY = "INSERT INTO Playlist (name, created_at, id_user) " + "VALUES(?, ?, ?);";
        Connection connection = dataSource.getConnection();

        Playlist playlist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setDate(2, playlistDTO.getCreatedAt());
            preparedStatement.setInt(3, playlistDTO.getUserId());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                playlist = new Playlist(playlistDTO);
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

    // Auxiliar

    private List<Content> findContentByPlayList(int id, Connection connection) {
        String QUERY = "SELECT * FROM content " +
                "INNER JOIN adds ON content.id_content = adds.id_content " +
                "INNER JOIN playlist ON adds.id_playlist = playlist.id_playlist " +
                "INNER JOIN genre ON content.id_genre = genre.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language " +
                "WHERE playlist.id_playlist = ?;";

        List<Content> contentList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id_content"));
                content.setName(rs.getString("name"));
                content.setLength(rs.getInt("length"));
                content.setGenre(rs.getString("genre"));
                content.setLanguage(rs.getString("language"));
                contentList.add(content);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contentList;
    }
}
