package src.io;

import src.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class ShelfHandler {
    private static final char CSV_SEPARATOR = ',';

    private GameShelf games;
    private final String filePath;

    public ShelfHandler(String filePath, GameShelf games) {
        if (filePath.isEmpty() || filePath == null) 
            throw new IllegalArgumentException("file path must be filled");
        this.filePath = filePath.trim();
        this.games = games;
    }

    public boolean load() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Game tmp = parseCsv(line);
                games.add(tmp);
            }
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public boolean save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(games.toCsv(CSV_SEPARATOR));
            return true;
        } catch(IOException ioe) {
            return false;
        }
    }

    /**
     * Parse csv line data to new Game Obj
     * @param line (String)
     * @return (Game)
     */
    private Game parseCsv(String line) {
        String[] gameData = line.trim().split(String.valueOf(CSV_SEPARATOR));
        String[] consoles = Arrays.copyOfRange(gameData, 3, gameData.length - 1);
        return new Game(gameData[0], parseConsoles(consoles), Double.valueOf(gameData[1]));
    }

    /**
     * Match String values with Console Enum
     * @param arr
     * @return
     */
    private static Consoles[] parseConsoles(String[] arr) {
        Consoles[] devices = new Consoles[arr.length];
        for (int i = 0; i < arr.length; i++) {
            devices[i] = getCorrespondigConsole(arr[i]);
        }
        return devices;
    }

    private static Consoles getCorrespondigConsole(String str) {
        if (str.isEmpty() || str == null) 
            throw new IllegalArgumentException("str must be filled");
        for (Consoles console : Consoles.values()) {
            if (console.getName().equals(str))
                return console;
        }
        return null;
    }

}
