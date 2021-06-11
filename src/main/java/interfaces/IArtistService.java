package main.java.interfaces;

import main.java.models.Artist;

import java.sql.SQLException;
import java.util.List;

public interface IArtistService {
    //  READ
    public Artist findOne(int id) throws SQLException;
    public List<Artist> findByName(String name) throws SQLException;
    public List<Artist> findByCountry(String country) throws SQLException;

}
