package services;

import dao.PlaylistDAO;
import dto.ContentDTO;
import dto.PlaylistDTO;
import exception.ForbiddenAccessException;
import exception.ValidationException;
import interfaces.IPlaylistService;
import models.Content;
import models.Playlist;
import utils.DateTime;
import utils.Validate;

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
    public Playlist insertContent(PlaylistDTO playlistDTO) throws SQLException {
        Playlist playlist = null;
        try {
            for(Content simpleContent : playlistDTO.getContent())
                playlist = dao.insertContent(playlistDTO.getId(), playlistDTO.getUserId(), simpleContent.getId());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Content already in playlist");
        }
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

    public Playlist getMostListenedSongsPlaylistByUserId(int userId) throws SQLException, ValidationException {
        return dao.getMostListenedSongsPlaylistByUserId(userId);
    }

    private void isPlaylistOwnedByUser(int playlistId, int userId) throws SQLException, ForbiddenAccessException {
        if (dao.findOne(playlistId).getUserId() != userId)
            throw new ForbiddenAccessException("Cannot edit other??s playlists");
    }
}
