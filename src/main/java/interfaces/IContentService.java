package main.java.interfaces;

import main.java.exception.ValidationException;
import main.java.models.Content;

import java.sql.SQLException;
import java.util.List;

public interface IContentService {
    //  READ
    public Content findOne(int id) throws SQLException, ValidationException;
    public List<Content> findByName(String name) throws SQLException, ValidationException;
    public List<Content> findByGenre(String genre) throws SQLException, ValidationException;
    public List<Content> findByArtist(String artist) throws SQLException, ValidationException;
    public List<Content> findByLanguage(String language) throws SQLException, ValidationException;
}
