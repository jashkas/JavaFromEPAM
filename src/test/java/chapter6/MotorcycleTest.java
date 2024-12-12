package chapter6;

import chapter6.vehicle.Motorcycle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MotorcycleTest {
    private Motorcycle motorcycle;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        motorcycle = new Motorcycle();
        motorcycle.setRegistrationNumber("MOTO123");
        motorcycle.setBrand("Honda");
        motorcycle.setModel("CBR600RR");
        motorcycle.setVinNumber("JH2PC400X5M201234");
        motorcycle.setOwner("Jane Smith");
        motorcycle.setPowerSource("Gasoline");

        // Redirect System.out to our ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testGetters() {
        assertEquals("MOTO123", motorcycle.getRegistrationNumber());
        assertEquals("Honda", motorcycle.getBrand());
        assertEquals("CBR600RR", motorcycle.getModel());
        assertEquals("JH2PC400X5M201234", motorcycle.getVinNumber());
        assertEquals("Jane Smith", motorcycle.getOwner());
        assertEquals("Gasoline", motorcycle.getPowerSource());
    }

    @Test
    public void testRefuel() {
        motorcycle.refuel();
        assertEquals("Заправка мотоцикла топливом.\r\n", outContent.toString());
    }

    @Test
    public void testRepair() {
        motorcycle.repair();
        assertEquals("Ремонт мотоцикла.\r\n", outContent.toString());
    }

    @Test
    public void testService() {
        motorcycle.service();
        assertEquals("Обслуживание мотоцикла.\r\n", outContent.toString());
    }

    @Test
    public void testPassTechnicalInspection() {
        motorcycle.passTechnicalInspection();
        assertEquals("Мотоцикл проходит техосмотр.\r\n", outContent.toString());
    }
}
