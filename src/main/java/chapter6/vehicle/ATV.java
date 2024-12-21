package chapter6.vehicle;

public class ATV extends Vehicle implements IVehicleService {

    public ATV(String registrationNumber, String brand, String model, String vinNumber, String owner, String powerSource) {
        super(registrationNumber, brand, model, vinNumber, owner, powerSource);
    }

    @Override
    public void refuel() {
        System.out.println("Заправка квадроцикла топливом.");
    }

    @Override
    public void repair() {
        System.out.println("Ремонт квадроцикла.");
    }

    @Override
    public void service() {
        System.out.println("Обслуживание квадроцикла.");
    }

    @Override
    public void passTechnicalInspection() {
        System.out.println("Квадроцикл проходит техосмотр.");
    }
}
