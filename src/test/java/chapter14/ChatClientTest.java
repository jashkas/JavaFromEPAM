package chapter14;

import chapter14.VariableA.ChatClient;
import chapter14.VariableA.ChatServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ChatClientTest {
    private ChatServer server;
    private ExecutorService executor;
    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;

    @BeforeEach
    void setUp() {
        // Запуск сервера в фоновом режиме
        server = new ChatServer(1234);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Даем время серверу для старта
        try {
            Thread.sleep(100); // В реальных условиях лучше использовать механизмы синхронизации
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }

        // Подключаемся как клиент
        try {
            clientSocket = new Socket("localhost", 1234);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            fail("Ошибка подключения: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.stop();
        executor.shutdownNow();
    }

    @Test
    void testClientConnection() {
        assertTrue(clientSocket.isConnected(), "Клиент успешно подключен");
    }

    @Test
    void testClientSendAndReceive() {
        // Отправка сообщения от клиента
        String testMessage = "Привет, сервер!";
        out.println(testMessage);

        // Ожидание приема сообщения на стороне клиента
        String response = in.nextLine();
        assertTrue(response.contains(testMessage), "Ожидаемое сообщение получено");
    }
}
