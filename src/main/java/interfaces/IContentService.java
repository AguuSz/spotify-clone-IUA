package main.java.interfaces;

import main.java.models.Content;

import java.sql.SQLException;
import java.util.List;

public interface IContentService {
    //  READ
    public Content getOne(int id) throws SQLException;
    public List<Content> findByName(String name) throws SQLException;
    public List<Content> getAll() throws SQLException;
}
