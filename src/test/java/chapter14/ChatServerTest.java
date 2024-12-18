package chapter14;

import chapter14.VariableA.ChatServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private ChatServer server;
    private ExecutorService executor;

    @BeforeEach
    void setUp() {
        server = new ChatServer(1234);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Дождемся запуска сервера перед продолжением тестов
        try {
            Thread.sleep(100); // В реальных условиях лучше использовать механизмы синхронизации
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }

    @AfterEach
    void tearDown() {
        server.stop();
        executor.shutdownNow();
    }

    @Test
    void testServerCreation() {
        assertNotNull(server);
    }
}
