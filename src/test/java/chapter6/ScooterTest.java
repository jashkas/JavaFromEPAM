package chapter6;

import chapter6.vehicle.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ScooterTest {
    private Scooter scooter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        scooter = new Scooter();
        scooter.setRegistrationNumber("SC123456");
        scooter.setBrand("Vespa");
        scooter.setModel("Primavera");
        scooter.setVinNumber("WVWZZZ1JZXW000123");
        scooter.setOwner("Alice Johnson");
        scooter.setPowerSource("Battery");

        // Перенаправляем System.out на наш ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testGetters() {
        assertEquals("SC123456", scooter.getRegistrationNumber());
        assertEquals("Vespa", scooter.getBrand());
        assertEquals("Primavera", scooter.getModel());
        assertEquals("WVWZZZ1JZXW000123", scooter.getVinNumber());
        assertEquals("Alice Johnson", scooter.getOwner());
        assertEquals("Battery", scooter.getPowerSource());
    }

    @Test
    public void testRefuel() {
        scooter.refuel();
        assertEquals("Зарядка аккумулятора самоката.\r\n", outContent.toString());
    }

    @Test
    public void testRepair() {
        scooter.repair();
        assertEquals("Ремонт самоката.\r\n", outContent.toString());
    }

    @Test
    public void testService() {
        scooter.service();
        assertEquals("Обслуживание самоката.\r\n", outContent.toString());
    }

    @Test
    public void testPassTechnicalInspection() {
        scooter.passTechnicalInspection();
        assertEquals("Самокат проходит техосмотр.\r\n", outContent.toString());
    }
}
