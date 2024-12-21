package chapter14.VariableA;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    private final int port;
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket;
    private volatile boolean running = false;

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                if (!running) {
                    break;
                }
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
                broadcastMessage("Клиент подключился: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Закрываем все клиентские соединения
        for (ClientHandler client : clients) {
            client.closeConnection();
        }
        clients.clear();
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    broadcastMessage("Сообщение от " + socket.getInetAddress() + ": " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        void closeConnection() {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                clients.remove(this);
                broadcastMessage("Клиент отключился: " + socket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    }
}
