package main.java.interfaces;

import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ForbiddenAccessException;
import main.java.exception.ValidationException;
import main.java.models.Playlist;

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
    public Playlist insertContent(int playlistId, int userId, int contentId) throws SQLException;
    public Playlist insertContent(int PlaylistId, int userId, List<Integer> contentId) throws SQLException;

    //  READ
    public Playlist findOne(int id) throws SQLException;
    public List<Playlist> getAll() throws SQLException;
    public List<Playlist> findByName(String name) throws SQLException;
    public List<Playlist> findByUserId(int id) throws SQLException;

}
