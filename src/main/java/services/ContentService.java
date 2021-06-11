package main.java.services;

import main.java.dao.ContentDAO;
import main.java.interfaces.IContentService;
import main.java.models.Content;

import java.sql.SQLException;
import java.util.List;

public class ContentService implements IContentService {

    ContentDAO dao = new ContentDAO();

    @Override
    public Content getOne(int id) throws SQLException {
        return dao.getOne(id);
    }

    @Override
    public List<Content> findByName(String name) throws SQLException {
        return dao.findByName(name);
    }

    @Override
    public List<Content> getAll() throws SQLException {
        return dao.getAll();
    }
}
