package src;

import src.models.*;
import src.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("______Welcome to Game Archives______");
        GameShelf games = new GameShelf();
        ShelfHandler handler = new ShelfHandler("data/games.csv", games);
        if (handler.load()) {
            Messages.printSuccess("Games loaded");
            System.out.println(games);
        } else {
            Messages.printError("Something went wrong, try again later");
            System.exit(1);
        }

    }

    public static Game getUserGame(Scanner scan) {
        String name = getLine(scan, "Name");
        double price = getUnsignedDouble(scan, "Price");
        Consoles[] gameConsoles = inputConsoles(scan);
        return new Game(name, gameConsoles, price);
    }

    public static Consoles[] inputConsoles(Scanner scan) {
        Consoles[] gameConsoles = new Consoles[Consoles.values().length];
        for (int i = 0; i < gameConsoles.length; i++) {
            String instruction = Consoles.values()[0] 
                + "available for the game (y/n)";
            char userInput = getLine(scan, instruction).charAt(0);
            if (userInput == 'y' || userInput == 'Y') {
                gameConsoles[i] = Consoles.values()[i];
            } 
        }
        return gameConsoles;
    }

    public static double getDouble(Scanner scan, String text) {
        double number;
        while (true) {
            System.out.print(text + " > ");
            if (!scan.hasNextDouble()) {
                scan.nextLine();
                Messages.printWarning("Should be a number");
                continue;
            }
            number = scan.nextDouble();
            break;
        }
        return number;
    }

    public static double getUnsignedDouble(Scanner scan, String text) {
        double unsignedNumber = 0;
        while (true) {
            unsignedNumber = getDouble(scan, text);
            if (unsignedNumber < 0) {
                Messages.printWarning("Should be positive");
                continue;
            }
            break;
        }
        return unsignedNumber;
    }

    public static String getLine(Scanner scan, String text) {
        System.out.println(text + " > ");
        return scan.nextLine();
    }

}
