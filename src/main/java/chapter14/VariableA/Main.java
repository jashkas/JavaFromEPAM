package chapter14.VariableA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Сервер
        ChatServer server = new ChatServer(1234);
        // Запуск сервера
        ExecutorService executor = Executors.newSingleThreadExecutor();
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

        Socket clientSocket = null;
        // Подключаемся как клиент
        try {
            clientSocket = new Socket("localhost", 1234);
        } catch (IOException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }








        // Закрытие и отключение
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
}
