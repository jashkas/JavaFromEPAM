package chapter6.vehicle;

public interface IVehicleService {
    // Обслуживание
    void refuel();
    void repair();
    void service();

    // Техосмотр
    void passTechnicalInspection();
}
