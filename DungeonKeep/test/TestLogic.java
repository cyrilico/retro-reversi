package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import logic.*;

public class TestLogic {
	
	/* TESTDUNGEON LEVEL TESTING */
	
	@Test
	public void TestGuardInitialValues() {
		Level testLevel = new TestDungeonLevel();
        Game game = new Game(testLevel);
        
        Guard guard = ((TestDungeonLevel) testLevel).getGuard();
        if(guard == null)
        	fail("Guard is null");
       
        assertFalse(guard.hasCaughtHero(1, 1)); //Hero's initial position
        assertEquals('G', guard.getRepresentation());
        int startingPosition[] = {3, 1}; 
        assertArrayEquals(startingPosition, guard.getCoordinates());  
	}

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
	public void testMoveHeroIntoWall() {
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
	public void testMoveHeroIntoGuard() {
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
        if(currentPlan == null)
        	fail("currentPlan is null");
        
        assertEquals('S', currentPlan[2][0]);
        assertEquals('S', currentPlan[3][0]);
	}
	
	@Test
	public void testHeroProgresses() {
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
	
	/* TESTKEEP LEVEL TESTING */
	
	@Test
	public void TestOgreInitialValues() {
		Level testLevel = new TestKeepLevel(false);
        Game game = new Game(testLevel);
        
        Ogre ogre = ((TestKeepLevel) testLevel).getOgre();
       
        assertFalse(ogre.clubIsOnKey());
        assertFalse(ogre.isNearHero(1, 7)); //Hero's initial position
        assertFalse(ogre.hasCaughtHero(1, 7)); //Hero's initial position
        assertEquals(0, ogre.isStunned());

        int clubOffset[] = ogre.getClubOffset();
        assertTrue(clubOffset[0] == 0 && clubOffset[1] == 0);
	}
	
	@Test
	public void TestHeroMovesIntoOgre() {
        Game game = new Game(new TestKeepLevel(false));
        //Hero starts at (1,1) and Ogre is at (3,3)
        game.updateGame('d');
        game.updateGame('d');
        game.updateGame('s');

        assertFalse(game.isRunning());
        String finalMessage = game.finalMessage();
        if(finalMessage == null)
        	fail("finalMessage is null");

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
        assertEquals(game.isRunning(), true);
	}
	
	@Test
	public void TestHeroMovesIntoDoorWithoutKey(){
		Level testLevel = new TestKeepLevel(false);
        Game game = new Game(testLevel);
        game.updateGame('a');
        
        int[] expectedPosition = {1,1};
        assertArrayEquals(expectedPosition, ((TestKeepLevel) testLevel).getHeroCoordinates());
        char[][] currentPlan = testLevel.getLevelMatrix();
        if(currentPlan == null)
        	fail("currentPlan is null");

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
        if(currentPlan == null)
        	fail("currentPlan is null");

        assertEquals(currentPlan[1][0], 'S');
        assertTrue(game.isRunning());
	}
	
	@Test
	public void TestHeroEscapes() {
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
        if(currentPlan == null)
        	fail("currentPlan is null");

        assertEquals(currentPlan[1][0], 'S');
        assertTrue(game.isRunning());
        game.updateGame('a');
        assertFalse(game.isRunning());
        assertEquals("You've escaped from all levels!", game.finalMessage());
	}
	
	@Test(timeout = 1000)
	public void TestOgreRandomMovement() {
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
	
	/* 'NORMAL' DUNGEON LEVEL TESTING */
	
	@Test
	public void TestDungeonHeroMovement() {
		Level testLevel = new DungeonLevel();
        Game game = new Game(testLevel);
        
        int[] startingPosition = {1,1};
        assertArrayEquals(startingPosition, testLevel.getHeroCoordinates());
        
        game.updateGame('d');
        game.updateGame('s'); //This one isn't valid
        game.updateGame('d');

        int[] endingPosition = {3,1};
        assertArrayEquals(endingPosition, testLevel.getHeroCoordinates());
	}
	
	@Test
	public void TestDungeonHeroInitialValues() {
		Level testLevel = new DungeonLevel();
        Game game = new Game(testLevel);
        
        Guard guard = ((DungeonLevel)testLevel).getGuard();
        if(guard == null)
        	fail("Guard is null");
        
        assertFalse(guard.hasCaughtHero(1, 1)); //Hero's initial position
        assertEquals('G', guard.getRepresentation());
	}
	
	@Test
	public void TestDungeonGuardMovement() {
		Level testLevel = new DungeonLevel();
        Game game = new Game(testLevel);
        
        int[] startingPosition = {8,1};
        assertArrayEquals(startingPosition, ((DungeonLevel)testLevel).getGuardCoordinates());
        
        game.updateGame('d');
        game.updateGame('s'); //This one isn't valid
        game.updateGame('d');
        
        int[] endingPosition = ((DungeonLevel)testLevel).getGuardCoordinates();

        assertFalse((startingPosition[0] == endingPosition[0]) && (startingPosition[1] == endingPosition[1]));
	}
	
	@Test
	public void TestDungeonGetMatrix() {
		Level testLevel = new DungeonLevel();
        Game game = new Game(testLevel);
		
        char[][] currentPlan = testLevel.getLevelMatrix();
        if(currentPlan == null)
        	fail("currentPlan is null");	
	}

	@Test
	public void TestDungeonNextLevel() {
		Level testLevel = new DungeonLevel();
		Game game = new Game(testLevel);

		if(testLevel.getNextLevel() == null)
			fail("Next level is null");
	}
	
	/* 'NORMAL' KEEP LEVEL TESTING */
	
	@Test(timeout = 1000)
	public void TestKeepHeroMovement() {
		Level testLevel = new KeepLevel();
        Game game = new Game(testLevel);
        
        int[] startingPosition = {1,7};
        assertArrayEquals(startingPosition, testLevel.getHeroCoordinates());
        
        for(int i = 0; i < 3 /*3 movements*/; i++){
        	game.updateGame('d');
        	if(!game.isRunning())
        		return; //Captured
        }

        int[] endingPosition = {4,7};
        assertArrayEquals(endingPosition, testLevel.getHeroCoordinates());
	}
	
	@Test
	public void TestKeepHeroRepresentation() {
		Level testLevel = new KeepLevel();
        Game game = new Game(testLevel);	
        
        assertEquals('A', testLevel.getHeroRep());
	}
	
	@Test
	public void TestKeepOgreInitialValues() {
		Level testLevel = new KeepLevel();
		Game game = new Game(testLevel);

		ArrayList<Ogre> ogres = ((KeepLevel) testLevel).getOgres();

		for(Ogre elem : ogres) {
			assertFalse(elem.clubIsOnKey());
			assertFalse(elem.isNearHero(1, 7)); //Hero's initial position
			assertFalse(elem.hasCaughtHero(1, 7)); //Hero's initial position
			assertEquals(0, elem.isStunned());
			assertEquals('0', elem.getRepresentation());

			int clubOffset[] = elem.getClubOffset();
			assertTrue(clubOffset[0] == 0 && clubOffset[1] == 0);
		}
	}
	
	@Test
	public void TestKeepGetMatrix() {
		Level testLevel = new KeepLevel();
        Game game = new Game(testLevel);
		
        char[][] currentPlan = testLevel.getLevelMatrix();
        if(currentPlan == null)
        	fail("currentPlan is null");	
	}

	@Test
	public void TestKeepNextLevel() {
		Level testLevel = new KeepLevel();
		Game game = new Game(testLevel);

		if(testLevel.getNextLevel() != null)
			fail("Next level is not null");
	}
	
	@Test
	public void TestOgreRepresentation() {
		Level testLevel = new KeepLevel();
		Game game = new Game(testLevel);
		
		int counter = 1000; //Number of movements
		
		while(counter-- > 0 && game.isRunning()) {
			game.updateGame('x'); //Hero doesn't move but ogres do
			ArrayList<Ogre> ogres = ((KeepLevel) testLevel).getOgres();
			
			for(Ogre elem : ogres) {
				if(elem.isStunned() != 0)
					assertEquals('8', elem.getRepresentation());
				
				int coordinates[] = elem.getCoordinates();
				if(coordinates[0] == 1 && coordinates[1] == 7) //Ogre is on key
					assertEquals('$', elem.getRepresentation());
			}
		}
	}

/* TEST GAME CLASS */

	@Test
	public void TestGameAttributes() {
		Level testLevel = new TestDungeonLevel();
		Game game = new Game(testLevel);
		
		if(game.getCurrentMatrix() == null)
			fail("Current matrix is null");
		
		if(game.finalMessage() == null)
			fail("Final message is null");
	}
}	
		
	