package main.java.services;

import main.java.dao.DeviceDAO;
import main.java.dto.DeviceDTO;
import main.java.exception.ValidationException;
import main.java.interfaces.IDeviceService;
import main.java.models.Device;
import main.java.utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class DeviceService implements IDeviceService {
    DeviceDAO dao = new DeviceDAO();
    @Override
    public Device createDevice(DeviceDTO device) throws SQLException {
        return dao.create(device);
    }

    @Override
    public Device deleteOne(int id) throws SQLException, ValidationException {
        Validate.validateId(id);
        return dao.delete(id);
    }

    @Override
    public Device updateOne(DeviceDTO deviceDTO) throws SQLException {
        return dao.update(deviceDTO);
    }

    @Override
    public List<Device> findByUser(int userId) throws SQLException, ValidationException {
        Validate.validateId(userId);
        return dao.getByUserId(userId);
    }

    @Override
    public List<Device> findByModel(String model) throws SQLException, ValidationException {
        model = Validate.validateString(model);
        return dao.findByModel(model);
    }

    @Override
    public Device findByMacAddress(String macAddress) throws SQLException, ValidationException {
        macAddress = Validate.validateMacAddress(macAddress);
        return dao.findByMacAddress(macAddress);
    }

    @Override
    public List<Device> findByName(String name) throws SQLException, ValidationException {
        name = Validate.validateString(name);
        return dao.findByName(name);
    }

    @Override
    public Device findOne(int id) throws SQLException, ValidationException {
        Validate.validateId(id);
        return dao.findOne(id);
    }
}
