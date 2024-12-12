package chapter6;

import chapter6.vehicle.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setRegistrationNumber("123ABC");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setVinNumber("1HGCM82633A123456");
        car.setOwner("John Doe");
        car.setPowerSource("Gasoline");
        car.setHasAirConditioning(true);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("123ABC", car.getRegistrationNumber());
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals("1HGCM82633A123456", car.getVinNumber());
        assertEquals("John Doe", car.getOwner());
        assertEquals("Gasoline", car.getPowerSource());
        assertTrue(car.hasAirConditioning());
    }

    @Test
    public void testRefuel() {
        car.refuel(); // Тут можно сделать проверку, если метод изменяет состояние объекта
    }

    @Test
    public void testRepair() {
        car.repair(); // Аналогично, если метод изменяет состояние объекта
    }

    @Test
    public void testService() {
        car.service(); // Аналогично, если метод изменяет состояние объекта
    }

    @Test
    public void testPassTechnicalInspection() {
        car.passTechnicalInspection(); // Аналогично, если метод изменяет состояние объекта
    }
}
