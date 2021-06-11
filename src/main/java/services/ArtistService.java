package main.java.services;

import main.java.dao.ArtistDAO;
import main.java.interfaces.IArtistService;
import main.java.models.Artist;

import java.sql.SQLException;
import java.util.List;

public class ArtistService implements IArtistService {

    ArtistDAO dao = new ArtistDAO();

    @Override
    public Artist getOne(int id) throws SQLException {
        return dao.getOne(id);
    }

    @Override
    public List<Artist> findByName(String name) throws SQLException {
        return dao.findByName(name);
    }

    @Override
    public List<Artist> getAll() throws SQLException {
        return dao.getAll();
    }
}
