package chapter6.vehicle;

public class Car implements Vehicle {
    private String registrationNumber;
    private String brand;
    private String model;
    private String vinNumber;
    private String owner;
    private String powerSource;

    // Специфические свойства
    private boolean hasAirConditioning;

    @Override
    public String getRegistrationNumber() { return registrationNumber; }
    @Override
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    @Override
    public String getBrand() { return brand; }
    @Override
    public void setBrand(String brand) { this.brand = brand; }

    @Override
    public String getModel() { return model; }
    @Override
    public void setModel(String model) { this.model = model; }

    @Override
    public String getVinNumber() { return vinNumber; }
    @Override
    public void setVinNumber(String vinNumber) { this.vinNumber = vinNumber; }

    @Override
    public String getOwner() { return owner; }
    @Override
    public void setOwner(String owner) { this.owner = owner; }

    @Override
    public String getPowerSource() { return powerSource; }
    @Override
    public void setPowerSource(String powerSource) { this.powerSource = powerSource; }

    @Override
    public void refuel() {
        System.out.println("Заправка автомобиля топливом.");
    }

    @Override
    public void repair() {
        System.out.println("Ремонт автомобиля.");
    }

    @Override
    public void service() {
        System.out.println("Обслуживание автомобиля.");
    }

    @Override
    public void passTechnicalInspection() {
        System.out.println("Автомобиль проходит техосмотр.");
    }

    public boolean hasAirConditioning() { return hasAirConditioning; }
    public void setHasAirConditioning(boolean hasAirConditioning) { this.hasAirConditioning = hasAirConditioning; }
}
