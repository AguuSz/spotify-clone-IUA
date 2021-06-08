package main.java.dto;

public class DeviceDTO {

    private String macAddress;
    private int idUser;
    private String name;
    private String model;
    private String pairingDate;

    public DeviceDTO (String macAddress, int idUser, String name, String model, String pairingDate) {
        this.macAddress = macAddress;
        this.idUser = idUser;
        this.name = name;
        this.model = model;
        this.pairingDate = pairingDate;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getPairingDate() {
        return pairingDate;
    }

    public void setPairingDate(String paringDate) {
        this.pairingDate = paringDate;
    }
}
