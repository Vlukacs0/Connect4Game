package kodok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {
    private ConnectFour game;

    @BeforeEach
    void setUp() {
        game = new ConnectFour(7, 6); // 7 sor, 6 oszlop
    }

    @Test
    void testInitialGameState() {
        assertNotNull(game.getGameState());
        assertEquals(7, game.getGameState().getBoard().length);
        assertEquals(6, game.getGameState().getBoard()[0].length);
    }

    @Test
    void testDropPiece() {
        Move move = new Move(0, 'Y');
        game.getGameState().dropPiece(move);
        assertEquals('Y', game.getGameState().getBoard()[6][0]); // Az utolsó sorba kell esnie
    }
    @Test
    void testWinningCondition() {
        // Nyerés vízszintesen
        game.getGameState().dropPiece(new Move(0, 'Y'));
        game.getGameState().dropPiece(new Move(1, 'Y'));
        game.getGameState().dropPiece(new Move(2, 'Y'));
        game.getGameState().dropPiece(new Move(3, 'Y'));

        assertTrue(game.checkWin('Y')); // A 'Y' játékosnak nyernie kell
    }
}

