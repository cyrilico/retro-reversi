package test;

import logic.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDungeonLogic {

	@Test
    public void testMoveHeroIntoFreeCell() {
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        game.updateGame('s');

        int[] endingPosition = {1,2};
        assertArrayEquals(endingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
    }
	
	@Test
	public void testMoveHeroIntoWall(){
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        game.updateGame('a');
        game.updateGame('w');

        int[] endingPosition = {1,1};
        assertArrayEquals(endingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());

	}
	
	@Test
	public void testMoveHeroIntoGuard(){
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        game.updateGame('d');

        assertEquals(game.isRunning(), false);

	}
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        game.updateGame('s');
        game.updateGame('a');

        int[] endingPosition = {1,2};
        assertArrayEquals(endingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        assertEquals(game.isRunning(), true);
	}
	
	@Test
	public void testMoveHeroIntoLever() {
        Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        game.updateGame('s');
        game.updateGame('s');

        int[] endingPosition = {1,3};
        assertArrayEquals(endingPosition, ((TestDungeonLevel) testLevel).getHeroCoordinates());
        assertEquals(game.isRunning(), true);
        char[][] currentPlan = testLevel.getLevelMatrix();
        assertEquals('S', currentPlan[2][0]);
        assertEquals('S', currentPlan[3][0]);
	}
	
	@Test
	public void testHeroProgresses(){
        Game game = new Game(new TestDungeonLevel());
        //Remember: Each level has an unique index: 2 is for TestDungeonLevel
        assertEquals(2, game.getCurrentLevelIndex());
        game.updateGame('s');
        game.updateGame('s');
        game.updateGame('a');

        assertEquals(game.isRunning(), true);
        //1 is the KeepLevel's unique index
        assertEquals(1, game.getCurrentLevelIndex());
	}

}