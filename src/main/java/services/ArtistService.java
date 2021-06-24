package services;

import dao.ArtistDAO;
import exception.ValidationException;
import interfaces.IArtistService;
import models.Artist;
import utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class ArtistService implements IArtistService {

    ArtistDAO dao = new ArtistDAO();

    @Override
    public Artist findOne(int id) throws SQLException, ValidationException {
        return dao.findOne(Validate.validateId(id));
    }

    @Override
    public List<Artist> findByName(String name) throws SQLException, ValidationException {
        return dao.findByName(Validate.validateString(name));
    }

    @Override
    public List<Artist> findByCountry(String country) throws ValidationException, SQLException {
        return dao.findByCountry(Validate.validateString(country));
    }

    @Override
    public List<Artist> getAll() throws SQLException{
        return dao.getAll();
    }

}
