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
    public List<Content> list() throws SQLException {
        return dao.list();
    }

    @Override
    public List<Content> find(String data) throws SQLException {
        return dao.find(data);
    }
}
