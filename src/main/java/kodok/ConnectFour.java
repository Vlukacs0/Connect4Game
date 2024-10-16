package kodok;
import java.util.Random;
import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6; // Sorok száma
    private static final int COLUMNS = 7; // Oszlopok száma
    private static final char EMPTY = '.'; // Üres hely
    private static final char HUMAN = 'Y'; // Sárga játékos
    private static final char AI = 'R'; // Piros gépi játékos

    private char[][] board = new char[ROWS][COLUMNS]; // Tábla
    private Scanner scanner = new Scanner(System.in); // Felhasználói bemenet
    private Random random = new Random(); // Véletlenszám-generátor

    public ConnectFour() {
        // Tábla inicializálása üres helyekkel
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void playGame() {
        boolean isHumanTurn = true; // Humán játékos kezd

        while (true) {
            printBoard(); // Tábla kiírása
            if (isHumanTurn) {
                humanTurn(); // Humán játékos lépése
            } else {
                aiTurn(); // Gép játékos lépése
            }

            // Ellenőrizzük a győzelmet
            if (checkWin(isHumanTurn ? HUMAN : AI)) {
                printBoard();
                System.out.println((isHumanTurn ? "Humán" : "Gép") + " játékos nyert!");
                break;
            }

            // Ellenőrizzük, hogy a tábla tele van-e
            if (isBoardFull()) {
                printBoard();
                System.out.println("Döntetlen!");
                break;
            }

            isHumanTurn = !isHumanTurn; // Váltás a játékosok között
        }
    }

    private void humanTurn() {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (a-g): ");
            char input = scanner.next().charAt(0);
            column = input - 'a'; // Oszlop index kiszámítása
        } while (column < 0 || column >= COLUMNS || !dropPiece(column, HUMAN));
    }

    private void aiTurn() {
        int column;
        do {
            column = random.nextInt(COLUMNS); // Véletlen oszlop választása
        } while (!dropPiece(column, AI));
        System.out.println("A gép az '" + (char)('a' + column) + "' oszlopba rakott.");
    }

    private boolean dropPiece(int column, char piece) {
        // Ellenőrizzük, hogy a korong lehelyezhető-e az oszlopban
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY) {
                board[i][column] = piece; // Korong elhelyezése
                return true;
            }
        }
        return false; // Oszlop tele van
    }

    private boolean checkWin(char piece) {
        // Ellenőrizzük a nyerési feltételeket
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    private boolean checkHorizontalWin(char piece) {
        // Vízszintes nyerés ellenőrzése
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
        // Függőleges nyerés ellenőrzése
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
        // Balra le átlós nyerés ellenőrzése
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLUMNS - 4; c++) {
                if (board[r][c] == piece && board[r + 1][c + 1] == piece &&
                        board[r + 2][c + 2] == piece && board[r + 3][c + 3] == piece) {
                    return true;
                }
            }
        }
        // Jobbra le átlós nyerés ellenőrzése
        for (int r = 0; r <= ROWS - 4; r++) {
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
        // Ellenőrizzük, hogy a tábla tele van-e
        for (int c = 0; c < COLUMNS; c++) {
            if (board[0][c] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        // Tábla kiírása a konzolra
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("a b c d e f g"); // Oszlopok jelölése
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour(); // Játék példányosítása
        game.playGame(); // Játék indítása
    }
}
