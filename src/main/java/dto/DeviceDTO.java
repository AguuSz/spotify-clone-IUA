package main.java.dto;

import java.sql.Timestamp;

public class DeviceDTO {

    private int id;
    private String macAddress;
    private int userId;
    private String name;
    private String model;
    private Timestamp pairingDate;

    public DeviceDTO(String macAddress, int idUser, String name, String model, Timestamp pairingDate) {
        this.macAddress = macAddress;
        this.userId = idUser;
        this.name = name;
        this.model = model;
        this.pairingDate = pairingDate;
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
}
