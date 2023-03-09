package src;

import src.models.*;
import src.io.*;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import javax.lang.model.util.ElementScanner14;

public class Main {
    public static void main(String[] args) {

        System.out.println("______Welcome to Game Archives______");
        boolean isSaved = false, isChanged = false;
        Scanner scan = new Scanner(System.in);

        GameShelf games = new GameShelf();
        ShelfHandler handler = new ShelfHandler("data/games.csv", games);

        if (handler.load()) {
            Messages.printSuccess("Games loaded");
        } else {
            Messages.printError("Something went wrong, try again later");
            System.exit(1);
        }
        boolean wantExit = false;
        do {
            System.out.println(MENU);
            String userOption = getLine(scan, MENU);

            switch (userOption) {
                case "show":
                    String gameNamesList = games.getJustNames();
                    if (gameNamesList == null)
                        Messages.printWarning("There are no games in the archive");
                    else 
                        System.out.println(gameNamesList);
                    break;
                case "select":
                    String userSearch = getLine(scan, "Game name");
                    Game userGame =  games.getByName(userSearch);
                    if (userGame != null) {
                        Messages.printSuccess("Game found!");
                        System.out.println(userGame);
                    } else {
                        suggestAutocomplete(userSearch, games);
                    }
                    break;
                case "add":
                    Game userAdd = getUserGame(scan);
                    if (games.contains(userAdd)) 
                        Messages.printWarning("Game already is in the archive");
                    else {
                        games.add(userAdd);
                        isChanged = true;
                        Messages.printSuccess("Game added successfully!");
                    }
                    break;
                case "del":
                    userSearch = getLine(scan, "Game name");
                    if (games.removeByName(userSearch)) {
                        Messages.printSuccess("Game deleted");
                    } else {
                        suggestAutocomplete(userSearch, games);
                    }
                    break;
                case "save":
            }
        } while (!wantExit);

    }

    public static Game getUserGame(Scanner scan) {
        String name = getLine(scan, "Name");
        double price = getUnsignedDouble(scan, "Price");
        Consoles[] gameConsoles = inputConsoles(scan);
        return new Game(name, gameConsoles, price);
    }

    public static void suggestAutocomplete(String userSearch, GameShelf games) {
        String similarGames = 
            games.getByFirstLetter(userSearch.charAt(0));
        if (similarGames != null) {
            Messages.printWarning("Did you meant to write");
            System.out.println(similarGames);
        } else {
            Messages.printWarning("There are no games with such name");
        }
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

    public static final String MENU = 
        "__________ARCHIVE_________\n" +
        "show   \t--show all\n" +
        "select \t--select by name\n" +
        "add    \t--add new game\n" +
        "del    \t--delete game\n" +
        "save   \t--save archive\n" +
        "exit   \t--exit archive\n";

}
