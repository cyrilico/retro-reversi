package DungeonKeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.*;
import cli.*;

public class TestDungeonGameLogic {

    @Test
    public void testMoveHeroIntoFreeCell() {
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, testLevel.getHeroCoordinates());
        game.updateGame('d');

        int[] endingPosition = {2,1};
        assertArrayEquals(endingPosition, testLevel.getHeroCoordinates());
    }
}
