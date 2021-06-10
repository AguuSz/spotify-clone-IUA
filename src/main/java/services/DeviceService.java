package main.java.services;

import main.java.dto.DeviceDTO;
import main.java.exception.ValidationException;
import main.java.interfaces.IDeviceService;
import main.java.models.Device;

import java.sql.SQLException;
import java.util.List;

public class DeviceService implements IDeviceService {
    @Override
    public Device createDevice(DeviceDTO device) throws ValidationException, SQLException {
        return null;
    }

    @Override
    public Device deleteOne(int id) throws SQLException {
        return null;
    }

    @Override
    public Device updateOne(int id, DeviceDTO device) throws SQLException {
        return null;
    }

    @Override
    public List<Device> findByUserId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Device> findByModel(String model) throws SQLException {
        return null;
    }

    @Override
    public Device findByMacAddress(String macAddress) throws SQLException {
        return null;
    }

    @Override
    public List<Device> findByName(int id) throws SQLException {
        return null;
    }

    @Override
    public Device findOne(int id) throws SQLException {
        return null;
    }
}
