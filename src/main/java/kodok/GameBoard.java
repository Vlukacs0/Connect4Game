package kodok;

import java.util.Arrays;

public class GameBoard {
    private final int rows;
    private final int columns;
    private static final char EMPTY = '.';
    private final char[][] board;

    public GameBoard(int rows, int columns) {
        if (rows < 4 || columns < 4 || rows > 12 || columns > 12 || columns > rows) {
            throw new IllegalArgumentException("A sorok (N) és oszlopok (M) mérete 4 <= M <= N <= 12 kell legyen.");
        }
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    public void dropPiece(int column, char piece) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY) {
                board[i][column] = piece;
                break;
            }
        }
    }

    public boolean isColumnAvailable(int column) {
        return board[0][column] == EMPTY;
    }

    public boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    private boolean checkHorizontalWin(char piece) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= columns - 4; c++) {
                if (board[r][c] == piece && board[r][c + 1] == piece &&
                        board[r][c + 2] == piece && board[r][c + 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin(char piece) {
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r <= rows - 4; r++) {
                if (board[r][c] == piece && board[r + 1][c] == piece &&
                        board[r + 2][c] == piece && board[r + 3][c] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(char piece) {
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c <= columns - 4; c++) {
                if (board[r][c] == piece && board[r + 1][c + 1] == piece &&
                        board[r + 2][c + 2] == piece && board[r + 3][c + 3] == piece) {
                    return true;
                }
            }
            for (int c = 3; c < columns; c++) {
                if (board[r][c] == piece && board[r + 1][c - 1] == piece &&
                        board[r + 2][c - 2] == piece && board[r + 3][c - 3] == piece) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int c = 0; c < columns; c++) {
            if (board[0][c] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("Oszlopok: " + String.join(" ", Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).limit(columns).toArray(String[]::new)));
    }

    public int getColumns() {
        return columns;
    }
}
