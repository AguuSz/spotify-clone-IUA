package main.java.services;

import main.java.dao.PlaylistDAO;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ForbiddenAccessException;
import main.java.exception.ValidationException;
import main.java.interfaces.IPlaylistService;
import main.java.models.Playlist;
import main.java.utils.DateTime;
import main.java.utils.Validate;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    private PlaylistDAO dao = new PlaylistDAO();

    @Override
    public Playlist create(PlaylistDTO dto) throws SQLException, ValidationException {
        Validate.validateString(dto.getName());
        dto.setCreatedAt(DateTime.now());
        return dao.create(dto);
    }

    @Override
    public Playlist delete(int id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public Playlist deleteContent(int playlistId, int contentId) throws SQLException {
        return dao.deleteContent(playlistId, contentId);
    }

    @Override
    public Playlist update(PlaylistDTO dto) throws SQLException, ForbiddenAccessException {
        isPlaylistOwnedByUser(dto.getId(), dto.getUserId());
        return dao.update(dto);
    }

    @Override
    public Playlist insertContent(int playlistId, int userId, ContentDTO dto) throws SQLException {
        Playlist playlist = null;
        try {
            playlist = insertContent(playlistId, userId, dto);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Content already in playlist");
        }
        return playlist;
    }

    @Override
    public Playlist insertContent(int playlistId, int userId, List<ContentDTO> dto) throws SQLException {
        Playlist playlist = null;

        for(ContentDTO contentDTO : dto)
            playlist = insertContent(playlistId, userId, contentDTO);

        return playlist;
    }

    @Override
    public Playlist findOne(int id) throws SQLException {
        return dao.findOne(id);
    }

    @Override
    public List<Playlist> getAll() throws SQLException {
        return dao.getAll();
    }

    @Override
    public List<Playlist> findByName(String name) throws SQLException {
        return dao.findByName(name);
    }

    @Override
    public List<Playlist> findByUserId(int id) throws SQLException {
        return dao.findByUserId(id);
    }

    public Playlist getMostListenedGenrePlaylist(int userId) throws SQLException {
        return dao.getMostListenedGenrePlaylistByUserId(userId);
    }

    private void isPlaylistOwnedByUser(int playlistId, int userId) throws SQLException, ForbiddenAccessException {
        if (dao.findOne(playlistId).getUserId() == userId)
            throw new ForbiddenAccessException("Cannot edit other´s playlists");
    }
}
