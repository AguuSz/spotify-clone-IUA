package main.java.interfaces;

import main.java.models.Artist;

import java.sql.SQLException;
import java.util.List;

public interface IArtistService {
    //  READ
    public Artist getOne(int id) throws SQLException;
    public List<Artist> list() throws SQLException;
    public List<Artist> find(String data) throws SQLException;
}
