package logic;

import java.util.Random;

/**
 * The first game level
 *
 */
public class DungeonLevel extends Level {

    /**
     *  Used to randomly choose a type of guard for the level if user didn't choose one 
     */
    protected Random guardGenerator;
    /* 
     * The level's enemy 
     */
    protected Guard guard;
    /**
     * Custom next level. Used in case user creates a new KeepLevel
     */
    protected Level nextLevel = null;
    /**
     * Constructor. Initializes all fields and sets next level according to user's creation
     * @param next Next Level, which was created using the editor
     */
    public DungeonLevel(Level next) {
    	this();
    	nextLevel = next;
    }
    /**
     * Default constructor. Initializes all fields
     */
    public DungeonLevel() {
    	super();

    	levelIndex = 0;
    	guardGenerator = new Random();
    	map = new DungeonMap();

    	/*Create level's characters*/
    	//The hero
    	hero = new Hero(1,1);
    	//The guard
    	int whichGuard = guardGenerator.nextInt(9);
    	/* 0,1,2 -> Rookie
    	 * 3,4,5 -> Drunken
    	 * 6,7,8 -> Suspicious
    	 */
    	if(whichGuard < 3)
    		guard = new Rookie(8,1);
    	else if(whichGuard < 6)
    		guard = new Drunken(8,1);
    	else
    		guard = new Suspicious(8,1);
    }
    /**
     * Constructor that specifies the type of Guard that will be present
     * @param guardType Guard that will patrol the current session
     */
    public DungeonLevel(String guardType) {

    	super();

    	levelIndex = 0;
    	map = new DungeonMap();

    	/*Create level's characters*/
    	//The hero
    	hero = new Hero(1,1);

    	switch(guardType) {
    	case "Rookie":
    		guard = new Rookie(8,1);
    		break;
    	case "Drunken":
    		guard = new Drunken(8,1);
    		break;
    	case "Suspicious":
    		guard = new Suspicious(8,1);
    		break;
    	}
    }
    /**
     * Checks if the Guard is in the hero's surroundings and changes level state accordingly if so
     */
    public void checkIfHeroCaptured(){
      int[] heroCoordinates = hero.getCoordinates();
       if(guard.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
        levelStatus = LevelState.LOST;
    }
    /**
     * Updates the Hero and Guard's positions based on user input and/or their respective behavior
     * 
     * @param input user input containing desired Hero's next movement
     * @see updateHero
     */
    public void updatePositions(int[] input) {
        int dx = input[0];
        int dy = input[1];

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];

        char currentChar = map.elementAt(heroX+dx, heroY+dy);
        
        updateHero(dx, dy, currentChar);

        guard.updatePosition();

        checkIfHeroCaptured();
    }
    /**
     * Moves the hero to his next desired position if said movement is valid
     * @param dx Desired offset to current x-axis position
     * @param dy Desired offset to current y-axis position
     * @param currentChar Current element at next desired position
     * @see updatePositions
     */
    public void updateHero(int dx, int dy, char currentChar) {
        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];
    	
        switch(currentChar) { //Checking what is present in the cell the hero wants to move to
        case 'S':
          levelStatus = LevelState.WON;
          return; /* Avoids checking for enemies on negative indexes */
        case 'k':
          map.openDoors(); /* No need to use the heroHasKey attribute on this level since the hero doesn't change his representation and doors are open right away */
        case '.':
          break;
        default: /* currentChar == 'X' || currentChar = 'I' so we can't move through */
            dy = 0;
            dx = 0;
        }
        
        hero.setCoordinates(heroX+dx, heroY+dy);
    }
    /**
     * Gets the Guard's current position
     * @return int array containing Guard's coordinates
     */
    public int[] getGuardCoordinates() {
    	return guard.getCoordinates();
    }
    /**
     * Gets the current session' Guard. Used for Unit testing purposes only
     * @return level's Guard
     */
    public Guard getGuard() {
    	return guard;
    }
    /**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        int[] heroCoordinates = hero.getCoordinates();
        int heroX = heroCoordinates[0];
        int heroY = heroCoordinates[1];
        matrix[heroY][heroX] = hero.getRepresentation();

        //Draw guard
        int[] guardCoordinates = guard.getCoordinates();
        int guardX = guardCoordinates[0];
        int guardY = guardCoordinates[1];
        matrix[guardY][guardX] = guard.getRepresentation();
        return matrix;
    }
    /**
     * Gets the level following the current one
     * @return default KeepLevel if user didn't initialize nextLevel field, otherwise returns that specific one
     * @see nextLevel
     */
    public Level getNextLevel() {
    	
    	if(nextLevel != null)
    		return nextLevel;
    	
    	if(Game.getnOgres() == 0)
    		return new KeepLevel();

        return new KeepLevel(Game.getnOgres());
    }
}
