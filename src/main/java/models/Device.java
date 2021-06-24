package models;

import dto.DeviceDTO;

import java.sql.Timestamp;

public class Device {
    private int id;
    private String macAddress;
    private int userId;
    private String name;
    private String model;
    private Timestamp pairingDate;

    public Device() {
    }

    public Device(DeviceDTO dto) {
        this.macAddress = dto.getMacAddress();
        this.id = dto.getUserId();
        this.name = dto.getName();
        this.model = dto.getModel();
        this.pairingDate = dto.getPairingDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Timestamp getPairingDate() {
        return pairingDate;
    }

    public void setPairingDate(Timestamp paringDate) {
        this.pairingDate = paringDate;
    }

    @Override
    public String toString() {
        return "\nDevice {" +
                "\n\tId = " + id +
                "\n\tMacAddress = " + macAddress +
                "\n\tUserId = " + userId +
                "\n\tName = " + name +
                "\n\tModel = " + model +
                "\n\tPairingDate = " + pairingDate + "\n}";
    }
}
