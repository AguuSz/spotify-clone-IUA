package main.java.services;

import main.java.dao.PlaylistDAO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ValidationException;
import main.java.interfaces.IPlaylistService;
import main.java.models.Playlist;

import java.util.List;

public class PlaylistService implements IPlaylistService {

    private PlaylistDAO dao = new PlaylistDAO();
    @Override
    public Playlist create(PlaylistDTO dto) throws ValidationException {
        if(dto.getName().isBlank())
            throw new ValidationException("The name can not be empty");

        Playlist playlist = new Playlist(dto);
        return dao.create(playlist);
    }

    @Override
    public Playlist delete(int id) {
        return null;
    }

    @Override
    public Playlist update(int id, PlaylistDTO dto) {
        return null;
    }

    @Override
    public Playlist getOne(int id) {
        return null;
    }

    @Override
    public List<Playlist> list() {
        return dao.list();
    }
}
