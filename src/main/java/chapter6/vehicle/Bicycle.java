package chapter6.vehicle;

public class Bicycle extends Vehicle implements IVehicleService {
    public Bicycle() {
        super();
    }

    public Bicycle(String registrationNumber, String brand, String model, String vinNumber, String owner, String powerSource) {
        super(registrationNumber, brand, model, vinNumber, owner, powerSource);
    }

    @Override
    public void refuel() {
        System.out.println("Велосипед не требует заправки.");
    }

    @Override
    public void repair() {
        System.out.println("Ремонт велосипеда.");
    }

    @Override
    public void service() {
        System.out.println("Обслуживание велосипеда.");
    }

    @Override
    public void passTechnicalInspection() {
        System.out.println("Велосипед проходит техосмотр.");
    }
}
