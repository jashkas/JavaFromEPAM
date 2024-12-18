package chapter14.VariableA;

import java.io.*;
import java.net.*;

public class ChatClient {
    private final String hostname;
    private final int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    // Метод подключения к серверу
    public void connect() throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    // Метод для отправки сообщения
    public void sendMessage(String message) {
        out.println(message);
    }

    // Метод для получения сообщений
    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    // Метод для закрытия соединения
    public void disconnect() throws IOException {
        socket.close();
    }
}
