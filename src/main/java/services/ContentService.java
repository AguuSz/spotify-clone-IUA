package services;

import dao.ContentDAO;
import exception.ValidationException;
import interfaces.IContentService;
import models.Content;
import utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class ContentService implements IContentService {

    ContentDAO dao = new ContentDAO();

    @Override
    public Content findOne(int id) throws SQLException, ValidationException {
        return dao.findOne(Validate.validateId(id));
    }

    @Override
    public List<Content> findByName(String name) throws SQLException, ValidationException {
        return dao.findByName(Validate.validateString(name));
    }

    @Override
    public List<Content> findByGenre(String genre) throws SQLException, ValidationException {
        return dao.findByGenre(Validate.validateString(genre));
    }

    @Override
    public List<Content> findByArtist(String artist) throws SQLException, ValidationException {
        return dao.findByArtist(Validate.validateString(artist));
    }

    @Override
    public List<Content> findByLanguage(String language) throws SQLException, ValidationException {
        return dao.findByLanguage(Validate.validateString(language));
    }

    @Override
    public Content playContent(int userId, int contentId) throws SQLException, ValidationException {
        return dao.playContent(Validate.validateId(userId), Validate.validateId(contentId));
    }
}
