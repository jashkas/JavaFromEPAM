package chapter6.vehicle;

public class Scooter extends Vehicle implements IVehicleService {
    public Scooter() {
        super();
    }

    public Scooter(String registrationNumber, String brand, String model, String vinNumber, String owner, String powerSource) {
        super(registrationNumber, brand, model, vinNumber, owner, powerSource);
    }

    @Override
    public void refuel() {
        System.out.println("Зарядка аккумулятора самоката.");
    }

    @Override
    public void repair() {
        System.out.println("Ремонт самоката.");
    }

    @Override
    public void service() {
        System.out.println("Обслуживание самоката.");
    }

    @Override
    public void passTechnicalInspection() {
        System.out.println("Самокат проходит техосмотр.");
    }
}
