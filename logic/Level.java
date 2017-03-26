package logic;

/**
 * Holds all the information necessary about a generic level
 *
 */
public abstract class Level implements java.io.Serializable{

	/**
	 * Enumeration that holds information about the current level situation (still going, lost or won)
	 *
	 */
    protected enum LevelState {
        RUNNING, LOST, WON
    }
    /**
     * Holds information about whether the hero has collected the level key or not
     */
    protected boolean heroHasKey;
    /**
     * Holds informations about the current level situation
     */
    protected LevelState levelStatus;
    /**
     * An unique index. Used for Unit testing purposes only
     */
    protected int levelIndex;
    /**
     * Constructor. Initializes fields and sets level state as RUNNING
     * @see LevelState
     */
    public Level() {
      heroHasKey = false;
      levelStatus = LevelState.RUNNING;
    }
    /**
     * The level's map
     */
    protected Map map;
    /**
     * The game's hero (present on all levels)
     */
    protected Hero hero;
    
    /**
     * Updates every dynamic element's position based on user input and/or their respective behavior
     * 
     * @param input user input containing desired Hero's next movement
     * @see updateHero
     */
    public abstract void updatePositions(int[] input);
    /**
     * Moves the hero to his next desired position if said movement is valid
     * @param dx Desired offset to current x-axis position
     * @param dy Desired offset to current y-axis position
     * @param currentChar Current element at next desired position
     * @see updatePositions
     */
    public abstract void updateHero(int dx, int dy, char currentChar);

    /**
     * Checks if any enemy is in the hero's surroundings and changes level state accordingly if so
     */
    public abstract void checkIfHeroCaptured();

    /**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
    public abstract char[][] getLevelMatrix();
    /**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
    public abstract char[][] getLevelMatrixGUI();
    /**
     * Gets the level following the current one
     * @return next game level
     */
    public abstract Level getNextLevel();
    /**
     * Gets the level's unique index. Used for Unit testing purposes only
     * @return level's levelIndex
     * @see levelIndex
     */
    public int getIndex(){
    	return levelIndex;
    }
    /**
     * Checks if the Hero has successfully escaped the current level
     * @return true if LevelState is WON, false otherwise
     * @see LevelState
     */
    public boolean hasWon() {
        return levelStatus == LevelState.WON;
    }
    /**
     * Checks if the Hero has been captured and, therefore, has lost the current level
     * @return true if LevelState is LOST, false otherwise
     */
    public boolean hasLost() {
        return levelStatus == LevelState.LOST;
    }
    /**
     * Gets the Hero's current position
     * @return int array containing Hero's current coordinates
     */
    public int[] getHeroCoordinates() {
    	return hero.getCoordinates();
    }
    /**
     * Gets the Hero's current representation
     * @return char containing Hero's representation at that moment
     */
    public char getHeroRep() {
    	return hero.getRepresentation();
    }
}
