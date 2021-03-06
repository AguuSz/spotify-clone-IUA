package interfaces;

import exception.ValidationException;
import models.Artist;

import java.sql.SQLException;
import java.util.List;

public interface IArtistService {
    //  READ
    public Artist findOne(int id) throws SQLException, ValidationException;
    public List<Artist> findByName(String name) throws SQLException, ValidationException;
    public List<Artist> findByCountry(String country) throws SQLException, ValidationException;
    public List<Artist> getAll() throws SQLException;

}
