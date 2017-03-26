package logic;

/**
 * Game class, the one that holds all the pieces about the current session
 *
 */
public class Game implements java.io.Serializable{
	/**
	 * Holds information about the current session (RUNNING, LOST or WON)
	 *
	 */
	protected enum GameState {
        RUNNING, LOST, WON
    }
	/**
	 * Instance of GameState enumeration that actually stores current game state
	 */
	protected GameState gameStatus;
	/**
	 * Game level being currently played
	 */
    protected Level level;
    /**
     * Number of ogres present at KeepLevel (not-zero when game is ran through GUI)
     */
    protected static int nOgres = 0;
    /*
     * The type of guard present at DungeonLevel (not-null when game is ran through GUI)
     */
    protected static String guardType = null;
    /**
     * Start the game at a specific level
     * @param level Level to start the game at
     */
	public Game(Level level) {
		gameStatus = GameState.RUNNING;
		this.level = level;
	}
	/**
	 * Start the game at a specific level, initializing static fields
	 * @param nOgres Number of desired ogres at KeepLevel
	 * @param guardType Desired type of guard at DungeonLevel
	 * @param level Level to start the game at
	 */
	public Game(int nOgres, String guardType, Level level) {
		this(level);
		Game.nOgres = nOgres;
		Game.guardType = guardType;
	}
	/**
	 * Gets the current level's unique index. Used for Unit testing purposes only
	 * @return current level's unique index
	 */
	public int getCurrentLevelIndex(){
		return level.getIndex();
	}

	private int[] charToMovement(char input){
        int[] result = new int[2];
        switch(input){
            case 'a':
                result[0]--; break; //[-1,0] - left
            case 'w':
                result[1]--; break; //[0,-1] - up
            case 's':
                result[1]++; break;//[0,1] - down
            case 'd':
                result[0]++; break;//[1,0] - right
            default: break;//[0,0] - invalid movement key
        }
        return result;
    }
	/**
	 * Returns an unique message according to the final game state (WON or LOST)
	 * @return "You've been captured!" if LOST, "You've escaped from all levels!" if WON
	 * @see GameState
	 */
	public String finalMessage() {
        switch(gameStatus){
        case LOST:
            return "You've been captured!";
        case WON:
            return "You've escaped from all levels!";
        default:
            return "How did you end up here?!";
        }
	}
	/**
	 * Gets the current level's matrix and converts it to a String. Used for CLI and Unit testing
	 * @return String containing current level status 
	 */
	public String getCurrentMatrix() {
		String result = "";
		char[][] matrix = level.getLevelMatrix();

		for(char[] line : matrix) {
			for(char element : line)
				result += element;
			result += '\n';
		}

		return result;
	}
	
	/**
	 * Gets the current level's matrix and converts it to a String. Used for GUI
	 * @return String containing current level status 
	 */
	public String getCurrentMatrixGUI() {
		String result = "";
		char[][] matrix = level.getLevelMatrixGUI();

		for(char[] line : matrix) {
			for(char element : line)
				result += element;
			result += '\n';
		}

		return result;
	}

	private void advanceLevel() {
		Level newLevel = level.getNextLevel();
		if(newLevel == null) //No more levels
			gameStatus = GameState.WON;
		else
      level = newLevel;
	}
	/**
	 * Converts the user's input to a movement vector and calls the level's update function
	 * @param userInput Key pressed for next desired Hero movement
	 */
	public void updateGame(char userInput) {
		int[] nextHeroMovement = charToMovement(userInput);

		level.updatePositions(nextHeroMovement);
		if(level.hasWon())
			advanceLevel();
		else if(level.hasLost())
			gameStatus = GameState.LOST;
	}
	/**
	 * Checks if game is still going
	 * @return true if gameStatus is RUNNING, false otherwise
	 * @see gameStatus
	 */
	public boolean isRunning() {
		return gameStatus == GameState.RUNNING;
	}
	/**
	 * Gets the static field corresponding to the number of ogres desired in KeepLevel
	 * @return static field nOgres
	 * @see nOgres
	 */
	public static int getnOgres() {
		return nOgres;
	}
	/**
	 * Gets the static field corresponding to the guard type desired in DungeonLevel
	 * @return static field guardType
	 * @see guardType
	 */
	public static String getGuardType() {
		return guardType;
	}
}
