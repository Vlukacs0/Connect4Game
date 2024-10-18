package kodok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState(6, 7);
    }

    @Test
    void testInitialBoard() {
        char[][] board = gameState.getBoard();
        for (char[] row : board) {
            for (char cell : row) {
                assertEquals(ConnectFour.EMPTY, cell); // Minden cellának üresnek kell lennie
            }
        }
    }

    @Test
    void testDropPiece() {
        Move move = new Move(0, 'Y');
        gameState.dropPiece(move);
        assertEquals('Y', gameState.getBoard()[5][0]); // Az utolsó sorba kell esnie
    }

    @Test
    void testIsBoardFull() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                gameState.dropPiece(new Move(i, 'Y'));
            }
        }
        assertTrue(gameState.isBoardFull()); // A tábla telített
    }

    // Itt több tesztet is hozzáadhatsz, például a fájl beolvasására és mentésére
}
