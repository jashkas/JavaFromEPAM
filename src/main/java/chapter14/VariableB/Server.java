package chapter14.VariableB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Сервер запущен...");
        GameLogic game = new GameLogic();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            int playerId = 1;

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Игрок " + playerId + " подключился.");

                ServerThread playerThread = new ServerThread(clientSocket, playerId, game);
                playerThread.start();

                game.addPlayer(playerId);

                playerId++;
                if (playerId > 2) break; // Ограничиваем игру на двух игроков
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }
}
