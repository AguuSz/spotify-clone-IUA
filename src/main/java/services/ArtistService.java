package main.java.services;

import main.java.dao.ArtistDAO;
import main.java.exception.ValidationException;
import main.java.interfaces.IArtistService;
import main.java.models.Artist;
import main.java.utils.ValidateString;

import java.sql.SQLException;
import java.util.List;

public class ArtistService implements IArtistService {

    ArtistDAO dao = new ArtistDAO();

    @Override
    public Artist findOne(int id) throws SQLException {
        return dao.findOne(id);
    }

    @Override
    public List<Artist> findByName(String name) throws SQLException {

        try{
            ValidateString.validate(name);

        } catch (ValidationException e) {

            System.out.println(e.getMessage());
            return null;

        }

        return dao.findByName(name);

    }

    @Override
    public List<Artist> findByCountry(String country) throws SQLException {

        try{
            ValidateString.validate(country);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return dao.findByCountry(country);
    }

}
