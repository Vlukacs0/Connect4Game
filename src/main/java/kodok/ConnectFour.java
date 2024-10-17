package kodok;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '.';
    private static final char HUMAN = 'Y';
    private static final char AI = 'R';

    private char[][] board = new char[ROWS][COLUMNS];
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public ConnectFour() {
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    public void playGame() {
        System.out.print("Írd be a játékos nevét: ");
        String playerName = scanner.nextLine();
        Player human = new Player(playerName, HUMAN);
        Player ai = new Player("Gép", AI);
        Player currentPlayer = human;

        while (true) {
            printBoard();
            Move move;
            if (currentPlayer.equals(human)) {
                move = humanTurn();
            } else {
                move = aiTurn();
            }
            dropPiece(move);

            if (checkWin(currentPlayer.symbol())) {
                printBoard();
                System.out.println(currentPlayer.name() + " nyert!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Döntetlen!");
                break;
            }

            currentPlayer = (currentPlayer.equals(human) ? ai : human);
        }
    }

    private Move humanTurn() {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (a-g): ");
            char input = scanner.next().charAt(0);
            column = input - 'a';
        } while (column < 0 || column >= COLUMNS || !isColumnAvailable(column));
        return new Move(column, HUMAN);
    }

    private Move aiTurn() {
        int column;
        do {
            column = random.nextInt(COLUMNS);
        } while (!isColumnAvailable(column));
        System.out.println("A gép az '" + (char) ('a' + column) + "' oszlopba rakott.");
        return new Move(column, AI);
    }

    private void dropPiece(Move move) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][move.column()] == EMPTY) {
                board[i][move.column()] = move.symbol();
                break;
            }
        }
    }

    private boolean isColumnAvailable(int column) {
        return board[0][column] == EMPTY;
    }

    private boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    private boolean checkHorizontalWin(char piece) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c <= COLUMNS - 4; c++) {
                if (board[r][c] == piece && board[r][c + 1] == piece &&
                        board[r][c + 2] == piece && board[r][c + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin(char piece) {
        for (int c = 0; c < COLUMNS; c++) {
            for (int r = 0; r <= ROWS - 4; r++) {
                if (board[r][c] == piece && board[r + 1][c] == piece &&
                        board[r + 2][c] == piece && board[r + 3][c] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(char piece) {
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLUMNS - 4; c++) {
                if (board[r][c] == piece && board[r + 1][c + 1] == piece &&
                        board[r + 2][c + 2] == piece && board[r + 3][c + 3] == piece) {
                    return true;
                }
            }
            for (int c = 3; c < COLUMNS; c++) {
                if (board[r][c] == piece && board[r + 1][c - 1] == piece &&
                        board[r + 2][c - 2] == piece && board[r + 3][c - 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int c = 0; c < COLUMNS; c++) {
            if (board[0][c] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("a b c d e f g");
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.playGame();
    }
}
