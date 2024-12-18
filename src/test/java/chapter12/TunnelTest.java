package chapter12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TunnelTest {
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
    void passTunnel_shouldPassThroughTunnel1() throws InterruptedException {
        Train train = new Train(tunnel, "1");
        tunnel.passTunnel(train);
        assertEquals("Поезд 1 вошел в Туннель 1\r\n", outContent.toString(), "Вывод метода passTunnel_shouldPassThroughTunnel1 не соответствует ожидаемому");
    }

    @Test
    void passTunnel_shouldPassThroughTunnel2WhenTunnel1IsBusy() throws InterruptedException {
        Train train1 = new Train(tunnel, "1");
        Train train2 = new Train(tunnel, "2");

        Thread thread1 = new Thread(() -> {
            try {
                tunnel.passTunnel(train1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread.sleep(100);

        tunnel.passTunnel(train2);
        String expectedText = "Поезд 1 вошел в Туннель 1\r\n" +
                "Поезд 2 вошел в Туннель 1\r\n";
        assertEquals(expectedText, outContent.toString(), "Вывод метода passTunnel_shouldPassThroughTunnel2WhenTunnel1IsBusy не соответствует ожидаемому");
    }

    @Test
    void passTunnel_shouldRedirectTrainWhenWaitingTimeExceeded() throws InterruptedException {
        Train train = new Train(tunnel, "1");
        tunnel.passTunnel(train);
        tunnel.passTunnel(train);
        tunnel.passTunnel(train);
        String expectedText = "Поезд 1 вошел в Туннель 1\r\n" +
                "Поезд 1 вошел в Туннель 1\r\n" +
                "Поезд 1 вошел в Туннель 1\r\n";
        assertEquals(expectedText, outContent.toString(), "Вывод метода passTunnel_shouldRedirectTrainWhenWaitingTimeExceeded не соответствует ожидаемому");
    }
}
