package main.java.dto;

import main.java.exception.ValidationException;
import main.java.utils.Validate;

import java.sql.Date;

public class DeviceDTO {

    private int id;
    private String macAddress;
    private int userId;
    private String name;
    private String model;
    private Date pairingDate;

    public DeviceDTO (String macAddress, int idUser, String name, String model, Date pairingDate) throws ValidationException {
        Validate.validateMacAddress(macAddress);
        this.macAddress = macAddress;
        Validate.validateId(userId);
        this.userId = idUser;
        this.name = Validate.validateString(name);
        this.model = Validate.validateString(model);
        this.pairingDate = Validate.validateDate(pairingDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidationException {
        Validate.validateId(id);
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) throws ValidationException {
        Validate.validateMacAddress(macAddress);
        this.macAddress = macAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) throws ValidationException {
        Validate.validateId(userId);
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        this.name = Validate.validateString(name);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ValidationException {
        this.model = Validate.validateString(model);
    }

    public Date getPairingDate() {
        return pairingDate;
    }

    public void setPairingDate(Date paringDate) throws ValidationException {
        this.pairingDate = Validate.validateDate(paringDate);
    }
}
