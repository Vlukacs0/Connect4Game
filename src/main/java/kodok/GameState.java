package kodok;

public class GameState {
    private final char[][] board;
    private final int rows;
    private final int columns;
    private final Player human;
    private final Player ai;
    private Player currentPlayer;

    public GameState(int rows, int columns, String humanName) {
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        this.human = new Player(humanName, 'Y');
        this.ai = new Player("Gép", 'R');
        this.currentPlayer = human;
        resetBoard();
    }

    private void resetBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                board[r][c] = '.';
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public Player getHuman() {
        return human;
    }

    public Player getAI() {
        return ai;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer.equals(human) ? ai : human);
    }

    public boolean isBoardFull() {
        for (int c = 0; c < columns; c++) {
            if (board[0][c] == '.') {
                return false;
            }
        }
        return true;
    }
}
