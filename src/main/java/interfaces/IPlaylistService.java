package interfaces;

import dto.ContentDTO;
import dto.PlaylistDTO;
import exception.ForbiddenAccessException;
import exception.ValidationException;
import models.Playlist;

import java.sql.SQLException;
import java.util.List;

public interface IPlaylistService {

    //  CREATE
    public Playlist create(PlaylistDTO dto) throws SQLException, ValidationException;

    // DELETE
    public Playlist delete(int id) throws SQLException;
    public Playlist deleteContent(int playlistId, int contentId) throws SQLException;

    //  UPDATE
    public Playlist update(PlaylistDTO dto) throws SQLException, ForbiddenAccessException;
    public Playlist insertContent(PlaylistDTO playlistDTO) throws SQLException;

    //  READ
    public Playlist findOne(int id) throws SQLException;
    public List<Playlist> getAll() throws SQLException;
    public List<Playlist> findByName(String name) throws SQLException;
    public List<Playlist> findByUserId(int id) throws SQLException;

}
