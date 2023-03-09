package src.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import java.lang.StringBuilder;
import java.util.Arrays;

public class GameShelf implements CsvWriter {

    private List<Game> games;

    public GameShelf() {
        games = new ArrayList<>();
    } 

    public GameShelf(List<Game> gameList) {
        this.games = new ArrayList<>(gameList);
    }

    public GameShelf(Game[] gameList) {
        this.games = new ArrayList<>(Arrays.asList(gameList));
    }

    public boolean add(Game game) {
        return games.add(new Game(game));
    }

    public Game get(int index) {
        return new Game(games.get(index));
    }

    public void set(int index, Game game) {
        games.set(index, new Game(game));
    }

    /**
     * Searchs game by it's name, returns null if it does not exist in the list
     * @param name (String)
     * @return (Game)
     */
    public Game getByName(String name) {
        int gameIndex = getGameIndex(name);
        if (gameIndex == -1)
            return null;
        else
            return new Game(games.get(gameIndex));
    }

    public boolean removeByName(String name) {
        int gameIndex = getGameIndex(name);
        if (gameIndex == -1)
            return false;
        games.remove(gameIndex);
        return true;   
    }


    /**
     * Returns all the games names whose name start with an specific letter. 
     * Null is returned if there is no match
     * @param letter (char)
     * @return (String)
     */
    public String getByFirstLetter(char letter) {
        StringBuilder sb = new StringBuilder();
        games.forEach(game -> {
            char nameInital = 
                game.getName()
                    .toUpperCase()
                    .charAt(0);
            if (Character.toUpperCase(letter) == nameInital)
                sb.append(game.getName() + '\n');
        });
        if (sb.length() == 0)
            return null;
        return sb.toString();
    }

    public int getNumberOfGames() {
        return games.size();
    }

    public boolean contains(Game game) {
        return games.contains(game);
    }

    /**
     * Get's game index if there is not such game -1 is returned
     * @param name
     * @return
     */
    public int getGameIndex(String name) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getName().equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    @Override
    public String toCsv(char separator) {
        if (games.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        games.forEach(game -> {
            sb.append(game.toCsv(separator));
            sb.append('\n');
        });
        return sb.toString();
    }

    @Override
    public String toString() {
        if (games.isEmpty())
            return "empty";
        StringBuilder sb = new StringBuilder();
        games.forEach(game -> {
            sb.append(game.toString());
            sb.append("\n\n");
        });
        return sb.toString();
    }

    /**
     * Returns a list of all games names in a 3 column grid
     * @return (String)
     */
    public String getJustNames() {
        if (games.isEmpty()) 
            return null;
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, games.size())
                 .forEach(i -> {
                    String pos = "[" + (i+1) + "]";
                    sb.append(pos + games.get(i) + " ");
                    if (i % 3 == 0) {
                        sb.append('\n');
                    }
                 });
        return sb.toString();
    }
    
}
