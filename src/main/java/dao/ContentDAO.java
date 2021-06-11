package main.java.dao;

import main.java.models.Content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {

    //  READ
    public Content getOne(int id) throws SQLException {

        String QUERY = "SELECT id_content, name, length, genre, language " +
                "FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language" +
                "WHERE id_content = ?;";

        Content content = null;

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY)) {

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

        } catch (SQLException e) {
            throw e;
        }

        return content;
    }

    public List<Content> list() throws SQLException {

        String QUERY = "SELECT id_playlist, name, length, genre, language " +
                "FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language;";

        List<Content> contentList = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

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

        } catch (SQLException e) {
            throw e;
        }

        return contentList;
    }

    public List<Content> find(String data) throws SQLException {
        String QUERY = "SELECT id_content, name, length, genre, language " +
                "FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language" +
                "WHERE name LIKE '%?%';";

        List<Content> contentList = new ArrayList<>();

        try (Connection connection = DriverManager.
                getConnection(JDBCUtil.getURL(), JDBCUtil.getUser(), JDBCUtil.getPassword());

             PreparedStatement preparedStatement = connection.
                     prepareStatement(QUERY);) {

            preparedStatement.setString(1, data);

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

        } catch (SQLException e) {
            throw e;
        }

        return contentList;
    }

}
