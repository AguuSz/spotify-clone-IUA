package main.java.interfaces;

import main.java.dto.DeviceDTO;
import main.java.exception.ValidationException;
import main.java.models.Device;

import java.sql.SQLException;
import java.util.List;

public interface IDeviceService {

    //  CREATE
    public Device create(DeviceDTO dto) throws ValidationException, SQLException;

    // DELETE
    public boolean delete(int id) throws SQLException;

    //  UPDATE
    public boolean update(int id, DeviceDTO dto) throws SQLException;

    //  READ
    public Device getOne(int id);
    public List<Device> list() throws SQLException;
    public List<Device> find(String data) throws SQLException;
}
