package chapter6.vehicle;

public class Motorcycle extends Vehicle implements IVehicleService {
    public Motorcycle() {
        super();
    }

    public Motorcycle(String registrationNumber, String brand, String model, String vinNumber, String owner, String powerSource) {
        super(registrationNumber, brand, model, vinNumber, owner, powerSource);
    }

    @Override
    public void refuel() {
        System.out.println("Заправка мотоцикла топливом.");
    }

    @Override
    public void repair() {
        System.out.println("Ремонт мотоцикла.");
    }

    @Override
    public void service() {
        System.out.println("Обслуживание мотоцикла.");
    }

    @Override
    public void passTechnicalInspection() {
        System.out.println("Мотоцикл проходит техосмотр.");
    }
}
