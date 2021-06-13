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

    public Playlist getMostListenedGenrePlaylistByUserId(int userId) throws SQLException {
        String QUERY = "SELECT id_content, name, length, genre, language FROM content " +
                "INNER JOIN genre ON content.id_genre = genre.id_genre\n" +
                "INNER JOIN language ON content.id_language = language.id_language WHERE content.id_genre = (SELECT g.id_genre FROM `spotify-clone`.listen l " +
                "INNER JOIN content c ON l.id_content = c.id_content " +
                "INNER JOIN genre g ON c.id_genre = g.id_genre " +
                "INNER JOIN user u ON l.id_user = u.id_user " +
                "WHERE `date` >= DATE_SUB(NOW(), INTERVAL 15 DAY) AND u.id_user = ? " +
                "GROUP BY genre " +
                "ORDER BY COUNT(genre) DESC LIMIT 1);";
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
