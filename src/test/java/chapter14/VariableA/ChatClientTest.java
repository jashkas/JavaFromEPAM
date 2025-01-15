package chapter14.VariableA;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ChatClientTest {
    private static final int TEST_PORT = 8072;

    @Test
    @DisplayName("Тест: Проверка отправки и получения сообщения от сервера")
    void testSendAndReceiveMessage() throws IOException {
        // Запуск серверного сокета для теста
        Thread mockServerThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT);
                 Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                out.println("Введите ваше имя:");
                String clientName = in.readLine(); // Получаем имя клиента
                out.println(clientName + " вошел в чат.");

                String message = in.readLine(); // Читаем сообщение от клиента
                out.println("Эхо: " + message); // Отправляем эхо-ответ

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mockServerThread.setDaemon(true);
        mockServerThread.start();

        // Ждем инициализации сервера
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        // Симулируем работу клиента
        try (Socket socket = new Socket("127.0.0.1", TEST_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            assertEquals("Введите ваше имя:", in.readLine());
            out.println("TestClient");

            String welcomeMessage = in.readLine();
            assertEquals("TestClient вошел в чат.", welcomeMessage);

            // Отправка тестового сообщения
            out.println("Привет, сервер!");
            String echoMessage = in.readLine();
            assertEquals("Эхо: Привет, сервер!", echoMessage);
        }
    }
}
