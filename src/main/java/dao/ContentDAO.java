package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.exception.ValidationException;
import main.java.models.Artist;
import main.java.models.Content;
import main.java.utils.DateTime;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Content findOne(int id) throws SQLException {

        String QUERY = "SELECT id_content, name, length, genre, language FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language " +
                "WHERE id_content = ?;";
        Connection connection = dataSource.getConnection();
        Content content = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content = new Content();
                content.setId(rs.getInt("id_content"));
                content.setName(rs.getString("name"));
                content.setLength(rs.getInt("length"));
                content.setGenre(rs.getString("genre"));
                content.setLanguage(rs.getString("language"));
            }

        }

        return content;
    }

    public List<Content> findByName(String name) throws SQLException {
        String QUERY = "SELECT id_content, name, length, genre, language " +
                "FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language" +
                "WHERE name LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, '%' + name + '%');

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

        }

        return contentList;
    }

    public List<Content> findByGenre(String genre) throws SQLException {
        String QUERY = "SELECT content.id_content, content.name, length, genre, language, date, stage_name AS artist, album.name AS album_name " +
                "FROM content INNER JOIN genre ON content.id_genre = genre.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language " +
                "INNER JOIN uploads ON content.id_content = uploads.id_content " +
                "INNER JOIN artist ON uploads.id_artist = artist.id_artist " +
                "WHERE genre.genre LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, '%' + genre + '%');

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id_content"));
                content.setName(rs.getString("name"));
                content.setLength(rs.getInt("length"));
                content.setGenre(rs.getString("genre"));
                content.setLanguage(rs.getString("language"));
                content.setDate(rs.getTimestamp("date"));
                content.setArtists(findArtistByContent(content.getId(), connection));
                content.setAlbums(findArtistByAlbum(content.getId(), connection));

                contentList.add(content);
            }
        }
        return contentList;
    }

    public List<Content> findByArtist(String artist) throws SQLException {
        String QUERY = "SELECT * FROM content " +
                "INNER JOIN uploads ON content.id_content = uploads.id_content " +
                "INNER JOIN artist ON uploads.id_artist = artist.id_artist " +
                "WHERE artist.stage_name LIKE ?;";

        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, '%' + artist + '%');

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id_content"));
                content.setName(rs.getString("name"));
                content.setLength(rs.getInt("length"));
                content.setGenre(rs.getString("genre"));
                content.setLanguage(rs.getString("language"));
                content.setArtists(findArtistByContent(content.getId(), connection));
                content.setAlbums(findArtistByAlbum(content.getId(), connection));
                contentList.add(content);
            }

        }
        return contentList;
    }

    public List<Content> findByLanguage(String language) throws SQLException {
        String QUERY = "SELECT content.id_content, content.name, genre.genre, language.language, uploads.date " +
                "FROM content INNER JOIN genre ON content.id_genre = genre.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language " +
                "INNER JOIN uploads ON content.id_content = uploads.id_content " +
                "WHERE language.language LIKE ? " +
                "GROUP BY content.id_content, content.name, genre.genre, language.language, uploads.date;";

        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, '%' + language + '%');

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Content content = new Content();
                content.setId(rs.getInt("id_content"));
                content.setName(rs.getString("name"));
                content.setLength(rs.getInt("length"));
                content.setGenre(rs.getString("genre"));
                content.setLanguage(rs.getString("language"));
                content.setArtists(findArtistByContent(content.getId(), connection));
                content.setAlbums(findArtistByAlbum(content.getId(), connection));
                contentList.add(content);
            }
        }
        return contentList;
    }

    public Content playContent(int userId, int contentId) throws SQLException {

        String QUERY = "INSERT INTO listen SET id_content = ?, id_user = ?, date = ?;";

        Content content = null;
        Connection connection = dataSource.getConnection();

        try(connection){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, contentId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setTimestamp(3, DateTime.now());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            content = findOne(contentId);
        }
        return content;
    }

    // Auxiliares

    private List<Artist> findArtistByContent(int contentId, Connection connection){

        String QUERY = "SELECT artist.stage_name FROM content " +
                "INNER JOIN uploads ON content.id_content = uploads.id_content " +
                "INNER JOIN artist ON uploads.id_artist = artist.id_artist " +
                "WHERE content.id_content = ?;";

        List<Artist> artistList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, contentId);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Artist artist = new Artist();
                artist.setName(rs.getString("stage_name"));
                artistList.add(artist);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return artistList;
    }

    private List<String> findArtistByAlbum(int contentId, Connection connection){

        String QUERY = "SELECT album.name FROM content " +
                "INNER JOIN uploads ON content.id_content = uploads.id_content " +
                "INNER JOIN album ON uploads.id_album = album.id_album " +
                "WHERE content.id_content = ? GROUP BY album.name;";

        List<String> albumList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, contentId);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                albumList.add(rs.getString("album.name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return albumList;
    }


}
