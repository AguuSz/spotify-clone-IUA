package main.java.services;

import main.java.dao.ArtistDAO;
import main.java.exception.ValidationException;
import main.java.interfaces.IArtistService;
import main.java.models.Artist;
import main.java.utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class ArtistService implements IArtistService {

    ArtistDAO dao = new ArtistDAO();

    @Override
    public Artist findOne(int id) throws SQLException, ValidationException {
        Validate.validateId(id);
        return dao.findOne(id);
    }

    @Override
    public List<Artist> findByName(String name) throws SQLException, ValidationException {
        name = Validate.validateString(name);
        return dao.findByName(name);
    }

    @Override
    public List<Artist> findByCountry(String country) throws ValidationException, SQLException {
        country = Validate.validateString(country);
        return dao.findByCountry(country);
    }

}
