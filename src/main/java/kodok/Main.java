package kodok;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sorok és oszlopok kérése
        System.out.print("Kérlek, add meg a táblázat sorainak számát (N): ");
        int rows = scanner.nextInt();
        scanner.nextLine(); // Olvassuk el a sortörést

        System.out.print("Kérlek, add meg a táblázat oszlopainak számát (M): ");
        int columns = scanner.nextInt();
        scanner.nextLine(); // Olvassuk el a sortörést

        ConnectFour game = new ConnectFour(rows, columns);

        // Játékállás fájlnevének kérése és betöltése
        System.out.print("Kérlek, add meg a játékállás fájl nevét (vagy nyomj Entert az új játékhoz): ");
        String filePath = scanner.nextLine().trim();

        // Fájl betöltése, ha van megadott fájl
        if (!filePath.isEmpty()) {
            try {
                game.getGameState().loadFromFile(filePath); // Játékállás betöltése
            } catch (IOException e) {
                System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
                return; // Kilépünk, ha a fájl beolvasása nem sikerült
            }
        }

        game.playGame(); // Játék indítása

        // Játék végén a pálya állapotának mentése
        System.out.print("Kérlek, add meg a fájl nevét, ahová a játék állapotát szeretnéd menteni: ");
        String saveFilePath = scanner.nextLine();

        try {
            game.getGameState().saveToFile(saveFilePath); // Játékállás mentése
            System.out.println("A pálya sikeresen kiírva a '" + saveFilePath + "' fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba a fájl kiírása közben: " + e.getMessage());
        }

        scanner.close(); // Scanner bezárása
    }
}
