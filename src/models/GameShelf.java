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
     * Searchs game by it's name
     * @param name (String)
     * @return (Game)
     */
    public Game getByName(String name) {
        return new Game(
            games.stream()
                 .filter(game -> game.getName().equals(name))
                 .findFirst()
                 .orElse(null)
        );
    }

    /**
     * Returns all the games names whose name start with an specific letter
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
