package chapter14.VariableA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer {
    private static final int PORT = 8071;
    private static List<ChatServer.ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер чата запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // ожидание подключения
                System.out.println("Подключился новый клиент: " + clientSocket.getInetAddress().getHostName());

                // Создаем обработчик для нового клиента
                ChatServer.ClientHandler clientHandler = new ChatServer.ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start(); // стартуем поток
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    // Метод для отправки сообщения всем клиентам
    public static synchronized void broadcastMessage(String message, ChatServer.ClientHandler sender) {
        for (ChatServer.ClientHandler client : clients) {
            if (client != sender) { // не отправляем сообщение самому отправителю
                client.sendMessage(message);
            }
        }
    }

    // Метод удаления клиента из списка при его отключении
    public static synchronized void removeClient(ChatServer.ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Клиент отключился: " + clientHandler.getClientName());
    }

    // Класс для обработки клиента
    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintStream out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Получение имени клиента
                out.println("Введите ваше имя:");
                clientName = in.readLine();
                System.out.println(clientName + " вошел в чат.");
                broadcastMessage(clientName + " вошел в чат.", this);

                // Чтение сообщений клиента и пересылка их другим
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(clientName + ": " + message);
                    broadcastMessage(clientName + ": " + message, this);
                }
            } catch (IOException e) {
                System.err.println("Ошибка подключения к клиенту: " + e.getMessage());
            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ChatServer.removeClient(this); // Удаляем клиента из списка
                broadcastMessage(clientName + " покинул чат.", this);
            }
        }

        // Отправка сообщения клиенту
        public void sendMessage(String message) {
            out.println(message);
        }

        public String getClientName() {
            return clientName;
        }
    }
}