package dao;

import conf.DataSourceFactory;
import dto.DeviceDTO;
import models.Device;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  CREATE
    public Device create(DeviceDTO deviceDTO) throws SQLException {
        String QUERY = "INSERT INTO Device (mac_address, id_user, name, model, pairing_date) " +
                "VALUES( ?, ?, ?, ?, ? );";
        Connection connection = dataSource.getConnection();

        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, deviceDTO.getMacAddress());
            preparedStatement.setInt(2, deviceDTO.getUserId());
            preparedStatement.setString(3, deviceDTO.getName());
            preparedStatement.setString(4, deviceDTO.getModel());
            preparedStatement.setTimestamp(5, deviceDTO.getPairingDate());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                device = new Device(deviceDTO);
                device.setId(rs.getInt(1));
            }
        }
        return device;
    }

    //  READ
    public Device findOne(int id) throws SQLException {

        String QUERY = "SELECT * FROM device WHERE id_device = ?;";
        Connection connection = dataSource.getConnection();
        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setUserId(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getTimestamp("pairing_date"));
            }
        }
        return device;
    }

    public List<Device> findByName(String name) throws SQLException {

        String QUERY = "SELECT * FROM device WHERE name LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Device> deviceList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, "%" + name + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setUserId(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getTimestamp("pairing_date"));
                deviceList.add(device);
            }
        }
        return deviceList;
    }

    public Device findByMacAddress(String macAddress) throws SQLException {

        String QUERY = "SELECT * FROM device WHERE mac_address = ?;";
        Connection connection = dataSource.getConnection();
        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, macAddress);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setUserId(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getTimestamp("pairing_date"));
            }
        }
        return device;
    }

    public List<Device> findByModel(String model) throws SQLException {

        String QUERY = "SELECT * FROM device WHERE model LIKE ?;";
        Connection connection = dataSource.getConnection();
        List<Device> deviceList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, "%" + model + "%");

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setUserId(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getTimestamp("pairing_date"));
                deviceList.add(device);
            }
        }
        return deviceList;
    }

    public List<Device> getByUserId(int id) throws SQLException {

        String QUERY = "SELECT * FROM device WHERE id_user = ?;";
        Connection connection = dataSource.getConnection();

        List<Device> deviceList = new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setUserId(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getTimestamp("pairing_date"));
                deviceList.add(device);
            }
        }
        return deviceList;
    }

    //  UPDATE
    public Device update(DeviceDTO deviceDTO) throws SQLException {
        String QUERY = "UPDATE `device` SET mac_address = ?, SET id_user = ?, SET name = ?, SET model = ?, SET pairing_date = ? WHERE id_device = ?;";
        Connection connection = dataSource.getConnection();
        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, deviceDTO.getMacAddress());
            preparedStatement.setInt(2, deviceDTO.getUserId());
            preparedStatement.setString(3, deviceDTO.getName());
            preparedStatement.setString(4, deviceDTO.getModel());
            preparedStatement.setTimestamp(5, deviceDTO.getPairingDate());
            preparedStatement.setInt(6, deviceDTO.getId());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            device = new Device(deviceDTO);
            device.setId(deviceDTO.getId());
        }
        return device;
    }

    //  DELETE
    public Device delete(int id) throws SQLException {
        String QUERY = "DELETE FROM `device` WHERE (`id_device` = ?);";
        Connection connection = dataSource.getConnection();
        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            device = findOne(id);
            preparedStatement.executeUpdate();
        }
        return device;
    }
}
