package test;

import static org.junit.Assert.*;
import java.util.Random;

import org.junit.Test;

import logic.*;

public class TestKeepLogic {

	@Test
	public void TestHeroMovesIntoOgre() {
        Game game = new Game(new TestKeepLevel(false));
        //Hero starts at (1,1) and Ogre is at (3,3)
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('s');

        assertFalse(game.isRunning());
        assertEquals(game.finalMessage(),"You've been captured!");
	}
	
	@Test
	public void TestHeroMovesIntoLever(){
		Level testLevel = new TestKeepLevel(false);
        Game game = new Game(testLevel);
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('d');
        
        assertEquals('K', ((TestKeepLevel) testLevel).getHeroRep());
	}
	
	@Test
	public void TestHeroMovesIntoDoorWithoutKey(){
		Level testLevel = new TestKeepLevel(false);
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
		Level testLevel = new TestKeepLevel(false);
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
		Level testLevel = new TestKeepLevel(false);
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
	
	@Test(timeout = 1000)
	public void TestOgreRandomMovement(){
		Level testLevel = new TestKeepLevel(true);
        Game game = new Game(testLevel);
        
        boolean movedLeft, movedUp, movedRight, movedDown, movedToAFreeCell;
        boolean validMovement;
        int amountOfMovements = 5; //To test
        char nextMovement;
        Random generator = new Random();
        
        do{
        	int[] oldOgreCoor = ((TestKeepLevel)testLevel).getOgreCoordinates();
        	
        	switch(generator.nextInt(4)){
        	case 0:
        		nextMovement = 'a';
        		break;
        	case 1:
        		nextMovement = 'w';
        		break;
        	case 2:
        		nextMovement = 'd';
        		break;
        	case 3:
        		nextMovement = 's';
        		break;
        	default:
        		nextMovement = 'i'; //Invalid movement. Should never reach here, but...
        		break;
        	}
        	
        	game.updateGame(nextMovement);

        	int[] newOgreCoor = ((TestKeepLevel)testLevel).getOgreCoordinates();
        	int[] newClubCoor = ((TestKeepLevel)testLevel).getOgreClubCoordinates();

        	//Assert ogre moved
        	assertFalse(oldOgreCoor[0] == newOgreCoor[0] && oldOgreCoor[1] == newOgreCoor[1]);

        	//Assert ogre moved in a desired direction
        	movedLeft = newOgreCoor[0]-oldOgreCoor[0] == -1 && oldOgreCoor[1] == newOgreCoor[1];
        	movedRight = newOgreCoor[0]-oldOgreCoor[0] == 1 && oldOgreCoor[1] == newOgreCoor[1];
        	movedUp = newOgreCoor[0] == oldOgreCoor[0] && newOgreCoor[1]-oldOgreCoor[1] == -1;
        	movedDown = newOgreCoor[0] == oldOgreCoor[0] && newOgreCoor[1]-oldOgreCoor[1] == 1;
        	movedToAFreeCell = testLevel.getLevelMatrix()[newOgreCoor[1]][newOgreCoor[0]] != 'X';

        	validMovement = movedToAFreeCell && (movedLeft || movedRight || movedUp || movedDown);
        	assertTrue(validMovement);

        	//Assert club moved in a desired direction
        	movedLeft = newClubCoor[0]-newOgreCoor[0] == -1 && newClubCoor[1] == newOgreCoor[1];
        	movedRight = newClubCoor[0]-newOgreCoor[0] == 1 && newClubCoor[1] == newOgreCoor[1];
        	movedUp = newClubCoor[0] == newOgreCoor[0] && newClubCoor[1]-newOgreCoor[1] == -1;
        	movedDown = newClubCoor[0] == newOgreCoor[0] && newClubCoor[1]-newOgreCoor[1] == 1;
        	movedToAFreeCell = testLevel.getLevelMatrix()[newClubCoor[1]][newClubCoor[0]] != 'X';

        	validMovement = movedToAFreeCell && (movedLeft || movedRight || movedUp || movedDown);
        	assertTrue(validMovement);
        }while(--amountOfMovements > 0 && game.isRunning());
	}
}
