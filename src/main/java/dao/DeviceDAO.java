package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.models.Device;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

    //  CREATE
    public Device create(Device device) throws SQLException {
        String QUERY = "INSERT INTO Device (mac_address, id_user, name, model, pairing_date) " +
                "VALUES( ?, ?, ?, ?, ? );";
        Connection connection = dataSource.getConnection();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, device.getMacAddress());
            preparedStatement.setInt(2, device.getIdUser());
            preparedStatement.setString(3, device.getName());
            preparedStatement.setString(4, device.getModel());
            preparedStatement.setString(5, device.getPairingDate());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                device.setId(rs.getInt(1));
            }

        }

        return device;
    }

    //  READ
    public Device getOne(int id) throws SQLException {

        String QUERY = "SELECT id_device, mac_address, id_user, name, model, pairing_date " +
                "FROM device " +
                "WHERE id_device = ?;";
        Connection connection = dataSource.getConnection();
        Device device = null;

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, String.valueOf(id));

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setIdUser(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getString("pairing_date"));
            }

        }

        return device;
    }

    public List<Device> list() throws SQLException {

        String QUERY = "SELECT id_device, mac_addres, name, model, pairing_date " +
                "FROM device;";
        Connection connection = dataSource.getConnection();
        List<Device> deviceList= new ArrayList<>();

        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setId(rs.getInt("id_device"));
                device.setMacAddress(rs.getString("mac_address"));
                device.setIdUser(rs.getInt("id_user"));
                device.setName(rs.getString("name"));
                device.setModel(rs.getString("model"));
                device.setPairingDate(rs.getString("pairing_date"));
                deviceList.add(device);
            }

        }

        return deviceList;
    }

    //TODO
}
