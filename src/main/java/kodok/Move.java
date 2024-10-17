package kodok;

public class Move {
    private final int column;
    private final char symbol;

    public Move(int column, char symbol) {
        this.column = column;
        this.symbol = symbol;
    }

    public int getColumn() {
        return column;
    }

    public char getSymbol() {
        return symbol;
    }
}


