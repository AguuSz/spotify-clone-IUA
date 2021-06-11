package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.models.Content;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  READ
    public Content getOne(int id) throws SQLException {

        String QUERY = "SELECT id_content, name, length, genre, language FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language" +
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
                "WHERE name LIKE '%?%';";
        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, name);

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

    public List<Content> getAll() throws SQLException {

        String QUERY = "SELECT id_playlist, name, length, genre, language " +
                "FROM content INNER JOIN genre ON content.id_genre = content.id_genre " +
                "INNER JOIN language ON content.id_language = language.id_language;";
        Connection connection = dataSource.getConnection();
        List<Content> contentList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(QUERY);

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

}
