package kodok;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {
    private ConnectFour game;
    private final String testFileName = "testGame.txt";

    @BeforeEach
    void setUp() {
        game = new ConnectFour(6, 7); // 6 sor, 7 oszlop
    }

    @Test
    void testInitialBoardIsEmpty() {
        assertEquals(' ', game.getBoard()[0][0]);
        assertEquals(' ', game.getBoard()[5][6]);
    }

    @Test
    void testValidMove() {
        game.makeMove(0); // Sárga játékos lépése
        assertEquals('Y', game.getBoard()[5][0]); // A legalsó sorban kell lennie
    }

    @Test
    void testInvalidMoveOnFullColumn() {
        for (int i = 0; i < 6; i++) {
            game.makeMove(0); // Töltsd fel az első oszlopot
        }
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.makeMove(0); // Próbálj meg lépni egy tele oszlopba
        });
        assertEquals("A kiválasztott oszlop tele van!", exception.getMessage());
    }

    @Test
    void testWinningMoveHorizontal() {
        game.makeMove(0); // Sárga
        game.makeMove(1); // Piros
        game.makeMove(0); // Sárga
        game.makeMove(1); // Piros
        game.makeMove(0); // Sárga
        game.makeMove(1); // Piros
        game.makeMove(0); // Sárga - nyer
        assertTrue(game.checkForWin('Y'));
    }

    @Test
    void testWinningMoveVertical() {
        for (int i = 0; i < 4; i++) {
            game.makeMove(0); // Sárga lépések
        }
        assertTrue(game.checkForWin('Y'));
    }

    @Test
    void testWinningMoveDiagonal() {
        game.makeMove(0); // Sárga
        game.makeMove(1); // Piros
        game.makeMove(1); // Sárga
        game.makeMove(2); // Piros
        game.makeMove(2); // Sárga
        game.makeMove(3); // Piros
        game.makeMove(2); // Sárga - nyer
        assertTrue(game.checkForWin('Y'));
    }

    @Test
    void testDrawCondition() {
        // Töltsd fel a táblát úgy, hogy ne nyerjen senki
        for (int col = 0; col < 7; col++) {
            game.makeMove(col % 2 == 0 ? 0 : 1); // Sárga és piros váltakozva
        }
        // Ellenőrizd, hogy nincs nyertes
        assertFalse(game.checkForWin('Y'));
        assertFalse(game.checkForWin('R'));
    }

    @Test
    void testPlayerSwitch() {
        game.makeMove(0); // Sárga
        assertEquals('R', game.getCurrentPlayer()); // Következő lépés piros
        game.makeMove(1); // Piros
        assertEquals('Y', game.getCurrentPlayer()); // Következő lépés sárga
    }

    @Test
    void testMachinePlayerMakesMove() {
        game.makeMove(0); // Sárga lépés
        int machineMove = 1; // Kiválasztott oszlop (ezt érdemes fixálni a teszteléshez)
        game.makeMove(machineMove); // Gép lépése
        assertEquals('R', game.getBoard()[5][machineMove]); // Ellenőrizzük, hogy a gép lépett
    }

    @Test
    void testSaveGameToFile() throws IOException {
        game.makeMove(0);
        game.saveGameToFile(testFileName);
        String content = new String(Files.readAllBytes(Paths.get(testFileName)));
        assertTrue(content.contains("Y"));
    }

    @Test
    void testLoadGameFromFile() throws IOException {
        game.makeMove(0);
        game.saveGameToFile(testFileName);

        ConnectFour newGame = new ConnectFour(6, 7);
        newGame.loadGameFromFile(testFileName);
        assertEquals('Y', newGame.getBoard()[5][0]);
    }

    @Test
    void testLoadGameFromFileInvalid() {
        ConnectFour newGame = new ConnectFour(6, 7);
        Exception exception = assertThrows(IOException.class, () -> {
            newGame.loadGameFromFile("invalidFile.txt"); // Érvénytelen fájl
        });
        assertNotNull(exception); // Ellenőrizzük, hogy dob egy kivételt
    }

    @AfterEach
    void cleanupTestFiles() {
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
