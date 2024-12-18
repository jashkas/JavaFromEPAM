package chapter12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Tunnel tunnel;

    @BeforeEach
    void setUp() {
        tunnel = new Tunnel(2000);
        // Перенаправляем System.out на наш ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }
    // Восстановление стандартного вывода обратно на System.out после теста
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void run_shouldPassThroughTunnel() throws InterruptedException {
        Train train = new Train(tunnel, "1");
        train.start();
        train.join();
        assertEquals("Поезд 1 вошел в Туннель 1\r\n", outContent.toString(), "Вывод метода run_shouldPassThroughTunnel не соответствует ожидаемому");
    }

    @Test
    void testToString_shouldReturnTrainName() {
        Train train = new Train(tunnel, "1");
        assertEquals("Поезд 1", train.toString());
    }
}
