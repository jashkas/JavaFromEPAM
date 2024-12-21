package chapter6.vehicle;

public class Car extends Vehicle implements IVehicleService {

    // Специфические свойства
    private boolean hasAirConditioning;

    public Car() {
        super();
    }

    public Car(String registrationNumber, String brand, String model, String vinNumber, String owner, String powerSource) {
        super(registrationNumber, brand, model, vinNumber, owner, powerSource);
    }

    public boolean hasAirConditioning() { return hasAirConditioning; }
    public void setHasAirConditioning(boolean hasAirConditioning) { this.hasAirConditioning = hasAirConditioning; }

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
}
