package chapter14.VariableB;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    @Test
    public void testAddPlayer() {
        GameLogic gameLogic = new GameLogic();
        assertFalse(gameLogic.isGameReady());
        gameLogic.addPlayer(1);
        gameLogic.addPlayer(2);
        assertTrue(gameLogic.isGameReady());
    }

    @Test
    public void testMakeMoveAndGameOver() {
        GameLogic gameLogic = new GameLogic();
        gameLogic.addPlayer(1);
        gameLogic.addPlayer(2);

        gameLogic.makeMove(1, "0,0");
        gameLogic.makeMove(2, "1,1");

        assertFalse(gameLogic.isGameOver());

        gameLogic.makeMove(1, "0,1");
        gameLogic.makeMove(2, "2,2");

        gameLogic.makeMove(1, "0,2");
        assertFalse(gameLogic.isGameOver()); // Игра ещё не закончена
    }
}
