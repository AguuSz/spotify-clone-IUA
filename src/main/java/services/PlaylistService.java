package main.java.services;

import main.java.dao.PlaylistDAO;
import main.java.dto.ContentDTO;
import main.java.dto.PlaylistDTO;
import main.java.exception.ValidationException;
import main.java.interfaces.IPlaylistService;
import main.java.models.Playlist;
import main.java.utils.Formatter;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    private PlaylistDAO dao = new PlaylistDAO();

    @Override
    public Playlist create(PlaylistDTO dto) throws ValidationException, SQLException {
        if(dto.getName().isBlank())
            throw new ValidationException("The name can not be empty");

        dto.setCreatedAt(Formatter.formatDate(new Date()));

        return dao.create(dto);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public boolean update(int id, PlaylistDTO dto) throws SQLException {
        Playlist pll = new Playlist(dto);
        return dao.update(id, pll);
    }

    @Override
    public boolean insertContent(int id, ContentDTO dto) {
        return dao.insertContent(id, dto);
    }

    @Override
    public Playlist getOne(int id) {
        return getOne(id);
    }

    @Override
    public List<Playlist> list() throws SQLException {
        return dao.list();
    }

    @Override
    public List<Playlist> find(String data) throws SQLException {
        return dao.find(data);
    }
}
