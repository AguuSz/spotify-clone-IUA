package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ValidationException;
import main.java.models.Content;
import main.java.models.Playlist;
import main.java.services.ContentService;
import main.java.utils.DateTime;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaylistDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Playlist findOne(int id) throws SQLException {
        String QUERY = "SELECT id_playlist, id_user, name FROM playlist WHERE id_playlist = ?;";
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
                playlist.setUserId(rs.getInt("id_user"));
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

    public List<Playlist> findByUserId(int id) throws SQLException {
        String QUERY = "SELECT * FROM playlist WHERE id_user = ?;";
        Connection connection = dataSource.getConnection();
        List<Playlist> playlists = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

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

    // Devuelve una playlist en base al genero mas escuchado del usuario
    public Playlist getMostListenedGenrePlaylistByUserId(int userId) throws SQLException {
        String QUERY = "SELECT id_content, name, length, genre, language FROM content " +
                "INNER JOIN genre ON content.id_genre = genre.id_genre\n" +
                "INNER JOIN language ON content.id_language = language.id_language WHERE content.id_genre = (SELECT g.id_genre FROM `spotify-clone`.listen l " +
                "INNER JOIN content c ON l.id_content = c.id_content " +
                "INNER JOIN genre g ON c.id_genre = g.id_genre " +
                "INNER JOIN user u ON l.id_user = u.id_user " +
                "WHERE `date` >= DATE_SUB(NOW(), INTERVAL 15 DAY) AND u.id_user = ? " +
                "GROUP BY genre " +
                "ORDER BY COUNT(genre) DESC LIMIT 1) LIMIT 30;";
        Connection connection = dataSource.getConnection();
        Playlist playlist = new Playlist();
        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, userId);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            List<Content> songs = new ArrayList<Content>();
            while(rs.next()) {
                Content song = new Content();
                song.setId(rs.getInt("id_content"));
                song.setName(rs.getString("name"));
                song.setLength(rs.getInt("length"));
                song.setGenre(rs.getString("genre"));
                song.setLanguage(rs.getString("language"));
                songs.add(song);
            }
            playlist.setContentList(songs);
        }
        playlist.setName("Recomendacion de genero: " + playlist.getContentList().get(1).getGenre());
        return playlist;
    }

    // Devuelve una playlist en base a las canciones que mas escucho el usuario
    public Playlist getMostListenedSongsPlaylistByUserId(int userId) throws SQLException, ValidationException {
        String QUERY = "SELECT listen.id_content, COUNT(listen.id_content) AS cantidad FROM listen " +
                "INNER JOIN user ON user.id_user = listen.id_user " +
                "INNER JOIN content ON content.id_content = listen.id_content WHERE listen.id_user = ? GROUP BY listen.id_content ORDER BY cantidad DESC LIMIT 20;";
        Connection connection = dataSource.getConnection();
        Playlist playlist = new Playlist();
        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, userId);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            List<Content> songs = new ArrayList<Content>();
            ContentService contentService = new ContentService();
            while(rs.next()) {
                Content song = new Content();
                song = contentService.findOne(rs.getInt("id_content"));
                songs.add(song);
            }
            playlist.setContentList(songs);
        }
        playlist.setName("Tus 20 canciones mas escuchadas!");
        return playlist;
    }

    //  CREATE
    public Playlist create(PlaylistDTO playlistDTO) throws SQLException {
        String QUERY = "INSERT INTO Playlist (name, created_date, id_user) " + "VALUES(?, ?, ?);";
        Connection connection = dataSource.getConnection();

        Playlist playlist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setTimestamp(2, playlistDTO.getCreatedAt());
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

    public Playlist deleteContent(int playlistId, int contentId) throws SQLException {
        String QUERY = "DELETE FROM adds WHERE id_playlist = ? AND id_content = ?;";
        Connection connection = dataSource.getConnection();

        Playlist playlist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, contentId);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            playlist = findOne(playlistId);
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

            playlist = findOne(playlistDTO.getId());
        }

        return playlist;
    }

    public Playlist insertContent(int playlistId, int userId, int contentId) throws SQLException {
        String QUERY = "INSERT INTO adds (id_playlist, id_user, id_content, created_date) VALUES(?, ?, ?, ?);";
        Connection connection = dataSource.getConnection();

        Playlist playlist = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, contentId);
            preparedStatement.setTimestamp(4, DateTime.now());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            playlist = findOne(playlistId);

        }
        return playlist;
    }

    // DELETE
    public Playlist delete(int id) throws SQLException {
        String QUERY = "DELETE FROM Playlist WHERE id_playlist = ?;";
        Connection connection = dataSource.getConnection();
        Playlist playlist;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);
            playlist = findOne(id);
            preparedStatement.executeUpdate();
        }

        return playlist;
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return contentList;
    }
}
