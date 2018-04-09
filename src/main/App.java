package main;

import java.util.Scanner;

public class App {
    public static void main(String... args) {
        Reader scannerReader = () -> { Scanner scanner = new Scanner(System.in); return scanner.nextLine();};
        Game.enterGame(scannerReader, new Printer());
    }
}
