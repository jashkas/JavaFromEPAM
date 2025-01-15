package chapter14.VariableB;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private int playerId;
    private GameLogic game;

    public ServerThread(Socket socket, int playerId, GameLogic game) {
        this.socket = socket;
        this.playerId = playerId;
        this.game = game;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("Добро пожаловать, игрок " + playerId + "! Ждите второго игрока...");

            // Ждём второго игрока
            while (!game.isGameReady()) {
                Thread.sleep(100);
            }

            out.println("Игра началась! Ваш ход первый.");
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (game.isPlayerTurn(playerId)) {
                    // Передача хода в логику
                    String response = game.makeMove(playerId, inputLine);
                    out.println(response);

                    if (game.isGameOver()) {
                        out.println("Игра окончена! " + game.getWinner());
                        break;
                    }
                } else {
                    out.println("Сейчас не ваш ход, подождите...");
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка в потоке для игрока " + playerId + ": " + e.getMessage());
        }
    }
}
