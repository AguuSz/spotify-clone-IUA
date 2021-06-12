package main.java.models;

import main.java.dto.DeviceDTO;

import java.sql.Date;
import java.util.Objects;

public class Device {
    private int id;
    private String macAddress;
    private int userId;
    private String name;
    private String model;
    private Date pairingDate;

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

    public Date getPairingDate() {
        return pairingDate;
    }

    public void setPairingDate(Date paringDate) {
        this.pairingDate = paringDate;
    }
}
