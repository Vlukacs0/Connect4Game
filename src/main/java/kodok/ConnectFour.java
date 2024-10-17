package kodok;

import java.util.Random;
import java.util.Scanner;

public class ConnectFour {
    private final GameBoard gameBoard;
    private static final char HUMAN = 'Y'; // Sárga korong
    private static final char AI = 'R';    // Piros korong

    public ConnectFour(int rows, int columns) {
        this.gameBoard = new GameBoard(rows, columns);
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Írd be a játékos nevét: ");
        String playerName = scanner.nextLine();
        Player human = new Player(playerName, HUMAN);
        Player ai = new Player("Gép", AI);
        Player currentPlayer = human;

        while (true) {
            gameBoard.printBoard();
            Move move;
            if (currentPlayer.equals(human)) {
                move = humanTurn(scanner);
            } else {
                move = aiTurn();
            }
            gameBoard.dropPiece(move.column(), currentPlayer.symbol());

            if (gameBoard.checkWin(currentPlayer.symbol())) {
                gameBoard.printBoard();
                System.out.println(currentPlayer.name() + " nyert!");
                break;
            }

            if (gameBoard.isBoardFull()) {
                gameBoard.printBoard();
                System.out.println("Döntetlen!");
                break;
            }

            currentPlayer = (currentPlayer.equals(human) ? ai : human);
        }
    }

    private Move humanTurn(Scanner scanner) {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (a-" + (char) ('a' + gameBoard.getColumns() - 1) + "): ");
            char input = scanner.next().charAt(0);
            column = input - 'a';
        } while (column < 0 || column >= gameBoard.getColumns() || !gameBoard.isColumnAvailable(column));
        return new Move(column, HUMAN);
    }

    private Move aiTurn() {
        Random random = new Random();
        int column;
        do {
            column = random.nextInt(gameBoard.getColumns());
        } while (!gameBoard.isColumnAvailable(column));
        System.out.println("A gép az '" + (char) ('a' + column) + "' oszlopba rakott.");
        return new Move(column, AI);
    }
}

