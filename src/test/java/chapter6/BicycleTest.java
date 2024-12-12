package chapter6;

import chapter6.vehicle.Bicycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BicycleTest {
    private Bicycle bicycle;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        bicycle = new Bicycle();
        bicycle.setRegistrationNumber("BIKE123");
        bicycle.setBrand("Giant");
        bicycle.setModel("Defy");
        bicycle.setVinNumber("GIANT82340");
        bicycle.setOwner("Alice");
        bicycle.setPowerSource("Pedals");

        // Перенаправляем System.out на наш ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    // Восстановление стандартного вывода обратно на System.out после теста
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testGetters() {
        assertEquals("BIKE123", bicycle.getRegistrationNumber());
        assertEquals("Giant", bicycle.getBrand());
        assertEquals("Defy", bicycle.getModel());
        assertEquals("GIANT82340", bicycle.getVinNumber());
        assertEquals("Alice", bicycle.getOwner());
        assertEquals("Pedals", bicycle.getPowerSource());
    }

    @Test
    void testRefuel() {
        bicycle.refuel();
        // Проверяем, что в outContent содержится ожидаемая строка
        assertEquals("Велосипед не требует заправки.\r\n", outContent.toString());
    }

    @Test
    void testRepair() {
        bicycle.repair();
        assertEquals("Ремонт велосипеда.\r\n", outContent.toString());
    }

    @Test
    void testService() {
        bicycle.service();
        assertEquals("Обслуживание велосипеда.\r\n", outContent.toString());
    }

    @Test
    void testPassTechnicalInspection() {
        bicycle.passTechnicalInspection();
        assertEquals("Велосипед проходит техосмотр.\r\n", outContent.toString());
    }
}
