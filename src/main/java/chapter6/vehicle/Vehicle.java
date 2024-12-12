package chapter6.vehicle;

public interface Vehicle {
    String getRegistrationNumber();
    void setRegistrationNumber(String registrationNumber);

    String getBrand();
    void setBrand(String brand);

    String getModel();
    void setModel(String model);

    String getVinNumber();
    void setVinNumber(String vinNumber);

    String getOwner();
    void setOwner(String owner);

    String getPowerSource();
    void setPowerSource(String powerSource);

    // Обслуживание
    void refuel();
    void repair();
    void service();

    // Техосмотр
    void passTechnicalInspection();
}
