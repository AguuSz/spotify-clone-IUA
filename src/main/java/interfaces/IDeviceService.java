package main.java.interfaces;

import main.java.dto.DeviceDTO;
import main.java.exception.ValidationException;
import main.java.models.Device;

import java.sql.SQLException;
import java.util.List;


public interface IDeviceService {

    //  CREATE
    public Device createDevice(DeviceDTO device) throws ValidationException, SQLException;

    // DELETE
    public Device deleteOne(int id) throws SQLException;

    //  UPDATE
    public Device updateOne(int id, DeviceDTO device) throws SQLException;

    //  READ
    public List<Device> findByUserId(int userId) throws SQLException;
    public List<Device> findByModel(String model) throws SQLException;
    public Device findByMacAddress(String macAddress) throws SQLException;
    public List<Device> findByName(String name) throws SQLException;
    public Device findOne(int id) throws SQLException;
}
