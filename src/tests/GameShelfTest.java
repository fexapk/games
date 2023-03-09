package src.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.models.Consoles;
import src.models.Game;
import src.models.GameShelf;

public class GameShelfTest {
    Game testGame, testGame2;
    GameShelf testShelf;

    @Before
    public void setUp() {
        testGame = 
            new Game(
                    "name", 
                    new Consoles[]{Consoles.PLAY_STATION},
                    10
                );
        testGame2 = 
            new Game(
                "name2",
                new Consoles[]{Consoles.XBOX},
                12
            );
        testShelf = new GameShelf(new Game[] {testGame,testGame2});
    }

    @Test 
    public void ParseEachGameCsvLineToWholeString() {
        final char SEPARATOR = ',';
        final String EXPECTED = testGame.toCsv(SEPARATOR)
            + '\n' + testGame2.toCsv(SEPARATOR) + '\n';
        assertEquals(EXPECTED, testShelf.toCsv(SEPARATOR));

    }
}
