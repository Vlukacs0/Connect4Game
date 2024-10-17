package kodok;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kérlek, add meg a táblázat sorainak számát (N): ");
        int rows = scanner.nextInt();
        System.out.print("Kérlek, add meg a táblázat oszlopainak számát (M): ");
        int columns = scanner.nextInt();

        ConnectFour game = new ConnectFour(rows, columns);
        game.playGame();
    }
}
