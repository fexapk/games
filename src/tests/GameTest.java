package src.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.models.Game;
import src.models.Consoles;

public class GameTest {
    Game testGame;
    @Before
    public void setUp() {
        Consoles[] devices =
            {Consoles.PLAY_STATION,
             Consoles.NINTENDO_SWITCH};
        testGame 
            = new Game("Lego: Harry Potter", 
                        devices, 22.99);
    }

    @Test
    public void csvLineIsGeneratedCorrectly() {
        String expectedCsvLine =
            "Lego: Harry Potter,22.99,PlayStation_5,Switch";
        assertEquals(
            expectedCsvLine, 
            testGame.toCsv(',')
        );
    }
}
