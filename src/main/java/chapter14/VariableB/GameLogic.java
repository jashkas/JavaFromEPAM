package chapter14.VariableB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameLogic {
    private String[][] player1Board;
    private String[][] player2Board;
    private int currentPlayer = 1;
    private final Lock lock = new ReentrantLock();
    private int playersConnected = 0;

    public GameLogic() {
        player1Board = createEmptyBoard();
        player2Board = createEmptyBoard();
    }

    private String[][] createEmptyBoard() {
        String[][] board = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = "-";
            }
        }
        return board;
    }

    public synchronized void addPlayer(int playerId) {
        playersConnected++;
    }

    public boolean isGameReady() {
        return playersConnected >= 2;
    }

    public synchronized boolean isPlayerTurn(int playerId) {
        return currentPlayer == playerId;
    }

    public synchronized String makeMove(int playerId, String move) {
        String[][] opponentBoard = (playerId == 1) ? player2Board : player1Board;

        String[] parts = move.split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (opponentBoard[row][col].equals("-")) {
            opponentBoard[row][col] = "X";
            currentPlayer = (playerId == 1) ? 2 : 1;
            return "Ход выполнен! Поле [" + row + "," + col + "] атаковано.";
        } else {
            return "Эта ячейка уже атакована!";
        }
    }

    public boolean isGameOver() {
        return checkBoard(player1Board) || checkBoard(player2Board);
    }

    private boolean checkBoard(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell.equals("-")) return false;
            }
        }
        return true;
    }

    public String getWinner() {
        if (!checkBoard(player1Board)) {
            return "Игрок 1 победил!";
        } else {
            return "Игрок 2 победил!";
        }
    }
}
