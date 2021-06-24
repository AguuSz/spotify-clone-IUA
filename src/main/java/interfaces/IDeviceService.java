package interfaces;

import dto.DeviceDTO;
import exception.ValidationException;
import models.Device;

import java.sql.SQLException;
import java.util.List;


public interface IDeviceService {

    //  CREATE
    public Device createDevice(DeviceDTO device) throws ValidationException, SQLException;

    // DELETE
    public Device deleteOne(int id) throws SQLException, ValidationException;

    //  UPDATE
    public Device updateOne(DeviceDTO device) throws SQLException;

    //  READ
    public List<Device> findByUser(int userId) throws SQLException, ValidationException;
    public List<Device> findByModel(String model) throws SQLException, ValidationException;
    public Device findByMacAddress(String macAddress) throws SQLException, ValidationException;
    public List<Device> findByName(String name) throws SQLException, ValidationException;
    public Device findOne(int id) throws SQLException, ValidationException;
}
