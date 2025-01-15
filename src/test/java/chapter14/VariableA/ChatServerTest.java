package chapter14.VariableA;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private static final int TEST_PORT = 8071;

    @Test
    @DisplayName("Тест: Проверка подключения клиента")
    void testClientConnection() throws IOException {
        Thread serverThread = new Thread(() -> ChatServer.main(new String[]{}));
        serverThread.setDaemon(true);
        serverThread.start();

        // Ожидаем инициализацию сервера
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Server initialization interrupted.");
        }

        try (Socket clientSocket = new Socket("127.0.0.1", TEST_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Проверка сообщения о вводе имени клиента
            String serverMessage = in.readLine();
            assertEquals("Введите ваше имя:", serverMessage);

            // Отправляем имя пользователя
            out.println("TestClient");
            serverMessage = in.readLine();
            assertTrue(serverMessage.contains("TestClient вошел в чат."));
        }
    }
}
