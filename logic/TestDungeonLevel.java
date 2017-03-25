package logic;


/** 
 * Imitation of the first game level. Used for Unit testing purposes only.
 */
public class TestDungeonLevel extends Level {
	/* 
     * The level's enemy 
     */
    Guard guard;
    /**
     * Constructor that specifies the type of Guard that will be present
     * @param guardType Guard that will patrol the current session
     */
    public TestDungeonLevel(String guardType) {
        super();
        
        levelIndex = 2;
        map = new TestDungeonMap();

        /*Create level's characters*/
        //The hero
        switch(guardType) {
        case "Rookie":
        	guard = new Rookie(3,1);
        	break;
        case "Drunken":
        	guard = new Drunken(3,1);
        	break;
        case "Suspicious":
        	guard = new Suspicious(3,1);
        	break;
        }
        
        hero = new Hero(1,1);
    }
    /**
     * Default constructor. Initializes all fields
     */
    public TestDungeonLevel() {
        super();
        
        levelIndex = 2;
        map = new TestDungeonMap();

        /*Create level's characters*/
        //The hero
        hero = new Hero(1,1);

        //The guard
        guard =  new Rookie(3,1);

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

        checkIfHeroCaptured();
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
     * Gets the current session' Guard
     * @return level's Guard
     */
    public Guard getGuard() {
    	return guard;
    }
    /**
     * Gets the Hero's current position
     * @return int array containing Hero's coordinates
     */
    public int[] getHeroCoordinates() {
        int[] result = hero.getCoordinates();
        return result;
    }
    /**
     * Gets the nextLevel. In this class, it is only used for Unit testing purposes to assure the transition is indeed made
     * @return default KeepLevel
     * @see KeepLevel
     */
    public Level getNextLevel() {
        return new KeepLevel();
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

        switch(currentChar) {
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
}
