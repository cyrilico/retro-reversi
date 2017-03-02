package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.*;

public class TestKeepLogic {

	@Test
	public void TestHeroMovesIntoOgre() {
        Game game = new Game(new TestKeepLevel());
        //Hero starts at (1,1) and Ogre is at (3,3)
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('s');

        assertFalse(game.isRunning());
        assertEquals(game.finalMessage(),"You've been captured!");
	}
	
	@Test
	public void TestHeroMovesIntoLever(){
		Level testLevel = new TestKeepLevel();
        Game game = new Game(testLevel);
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('d');
        
        assertEquals('K', ((TestKeepLevel) testLevel).getHeroRep());
	}
	
	@Test
	public void TestHeroMovesIntoDoorWithoutKey(){
		Level testLevel = new TestKeepLevel();
        Game game = new Game(testLevel);
        game.updateGame('a');
        
        int[] expectedPosition = {1,1};
        assertArrayEquals(expectedPosition, ((TestKeepLevel) testLevel).getHeroCoordinates());
        char[][] currentPlan = testLevel.getLevelMatrix();
        assertEquals(currentPlan[1][0], 'I');
        assertTrue(game.isRunning());
	}
	
	@Test
	public void TestHeroMovesIntoDoorWithKey(){
		Level testLevel = new TestKeepLevel();
        Game game = new Game(testLevel);
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('d');
        
        assertEquals('K', ((TestKeepLevel) testLevel).getHeroRep());
        
        game.updateGame('a');
        game.updateGame('a');
        game.updateGame('a');
        game.updateGame('a');
        
        int[] expectedPosition = {1,1};
        assertArrayEquals(expectedPosition, ((TestKeepLevel) testLevel).getHeroCoordinates());
        char[][] currentPlan = testLevel.getLevelMatrix();
        assertEquals(currentPlan[1][0], 'S');
        assertTrue(game.isRunning());
	}
	
	@Test
	public void TestHeroEscapes(){
		Level testLevel = new TestKeepLevel();
        Game game = new Game(testLevel);
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('d');
        
        assertEquals('K', ((TestKeepLevel) testLevel).getHeroRep());
        
        game.updateGame('a');
        game.updateGame('a');
        game.updateGame('a');
        game.updateGame('a');
        
        int[] expectedPosition = {1,1};
        assertArrayEquals(expectedPosition, ((TestKeepLevel) testLevel).getHeroCoordinates());
        char[][] currentPlan = testLevel.getLevelMatrix();
        assertEquals(currentPlan[1][0], 'S');
        assertTrue(game.isRunning());
        game.updateGame('a');
        assertFalse(game.isRunning());
        assertEquals("You've escaped from all levels!", game.finalMessage());
        
	}
}
