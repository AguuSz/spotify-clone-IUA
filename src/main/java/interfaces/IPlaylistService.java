package main.java.interfaces;

import main.java.dto.PlaylistDTO;
import main.java.exception.ValidationException;
import main.java.models.Playlist;

import java.util.List;

public interface IPlaylistService {

    //  CREATE
    public Playlist create(PlaylistDTO dto) throws ValidationException;

    // DELETE
    public Playlist delete(int id);

    //  UPDATE
    public Playlist update(int id, PlaylistDTO dto);

    //  READ
    public Playlist getOne(int id);
    public List<Playlist> list();
}
