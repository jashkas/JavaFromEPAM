package chapter6;

import chapter6.vehicle.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private Car car;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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

        // Перенаправляем System.out на наш ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    // Восстановление стандартного вывода обратно на System.out после теста
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testGetters() {
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
        car.refuel();
        // Проверяем, что в outContent содержится ожидаемая строка
        assertEquals("Заправка автомобиля топливом.\r\n", outContent.toString());
    }

    @Test
    public void testRepair() {
        car.repair();
        assertEquals("Ремонт автомобиля.\r\n", outContent.toString());
    }

    @Test
    public void testService() {
        car.service();
        assertEquals("Обслуживание автомобиля.\r\n", outContent.toString());
    }

    @Test
    public void testPassTechnicalInspection() {
        car.passTechnicalInspection();
        assertEquals("Автомобиль проходит техосмотр.\r\n", outContent.toString());
    }
}
