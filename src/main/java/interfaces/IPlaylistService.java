package main.java.interfaces;

import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ValidationException;
import main.java.models.Playlist;

import java.sql.SQLException;
import java.util.List;

public interface IPlaylistService {

    //  CREATE
    public Playlist create(PlaylistDTO dto) throws ValidationException, SQLException;

    // DELETE
    public boolean delete(int id) throws SQLException;

    //  UPDATE
    public Playlist update(PlaylistDTO dto) throws SQLException;
    public boolean insertContent(int id, ContentDTO dto);

    //  READ
    public Playlist getOne(int id);
    public List<Playlist> list() throws SQLException;
    public List<Playlist> findByName(String name) throws SQLException;

}
