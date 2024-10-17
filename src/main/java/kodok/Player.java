package kodok;

public class Player {
    private final String name;
    private final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String name() {
        return name;
    }

    public char symbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Player)) return false;
        Player player = (Player) obj;
        return symbol == player.symbol;
    }
}


