package src.models;

import java.util.ArrayList;
import java.util.List;
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

    public Game getByName(String name) {
        return new Game(
            games.stream()
                 .filter(game -> game.getName().equals(name))
                 .findFirst()
                 .orElse(null)
        );
    }

    public int getNumberOfGames() {
        return games.size();
    }

    @Override
    public String toCsv(char separator) {
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
            throw new IllegalStateException("cant print if there is no game");
        StringBuilder sb = new StringBuilder();
        games.forEach(game -> {
            sb.append(game.toString());
            sb.append("\n\n");
        });
        return sb.toString();
    }
    
}
