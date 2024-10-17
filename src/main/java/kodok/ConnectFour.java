package kodok;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class ConnectFour {
    protected static final char EMPTY = '.';
    private int rows;
    private int columns;

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private Player human;
    private Player ai;
    private GameState gameState; // Definálva

    public ConnectFour(int rows, int columns) {
        if (rows < 4 || columns < 4 || rows > 12 || columns > 12 || columns > rows) {
            throw new IllegalArgumentException("A sorok (N) és oszlopok (M) mérete 4 <= M <= N <= 12 kell legyen.");
        }
        this.rows = rows;
        this.columns = columns;
        this.human = new Player("Ember", 'Y');
        this.ai = new Player("Gép", 'R');
    }

    public char getEmptySymbol() {
        return EMPTY;
    }

    public void playGame() {
        System.out.print("Kérlek, add meg a játékállás fájl nevét (vagy nyomj Entert az új játékhoz): ");
        String filePath = scanner.nextLine();

        try {
            if (!filePath.isEmpty()) {
                gameState = GameState.loadFromFile(filePath); // Itt töltjük be a GameState-t
            } else {
                gameState = new GameState(rows, columns);
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
            return;
        }

        Player currentPlayer = human;

        while (true) {
            gameState.printBoard();
            Move move;

            if (currentPlayer == human) {
                move = humanTurn();
            } else {
                move = aiTurn();
            }

            gameState.dropPiece(move);

            if (checkWin(currentPlayer.getSymbol())) {
                gameState.printBoard();
                System.out.println(currentPlayer.getName() + " nyert!");
                break;
            }

            if (gameState.isBoardFull()) {
                gameState.printBoard();
                System.out.println("Döntetlen!");
                break;
            }

            currentPlayer = (currentPlayer == human ? ai : human);
        }
    }

    private Move humanTurn() {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (a-" + (char) ('a' + columns - 1) + "): ");
            char input = scanner.next().charAt(0);
            column = input - 'a';
        } while (column < 0 || column >= columns || !isColumnAvailable(column));
        return new Move(column, human.getSymbol());
    }

    private Move aiTurn() {
        int column;
        do {
            column = random.nextInt(columns);
        } while (!isColumnAvailable(column));
        System.out.println("A gép az '" + (char) ('a' + column) + "' oszlopba rakott.");
        return new Move(column, ai.getSymbol());
    }

    private boolean isColumnAvailable(int column) {
        return gameState.isColumnAvailable(column);
    }

    private boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    private boolean checkHorizontalWin(char piece) {
        // Implementáld a horizontális nyerés ellenőrzését
        return false; // Alapértelmezett visszatérési érték
    }

    private boolean checkVerticalWin(char piece) {
        // Implementáld a vertikális nyerés ellenőrzését
        return false; // Alapértelmezett visszatérési érték
    }

    private boolean checkDiagonalWin(char piece) {
        // Implementáld az átlós nyerés ellenőrzését
        return false; // Alapértelmezett visszatérési érték
    }
}
