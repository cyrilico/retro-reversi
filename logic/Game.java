package logic;

public class Game implements java.io.Serializable{
	protected enum GameState {
        RUNNING, LOST, WON
    }

	protected GameState gameStatus;
    protected Level level;

    protected static int nOgres = 0;
    protected static String guardType = null; //Default values

	public Game(Level level) {
		gameStatus = GameState.RUNNING;
		this.level = level;
	}
	
	public Game(int nOgres, String guardType, Level level) {
		this(level);
		Game.nOgres = nOgres;
		Game.guardType = guardType;
	}
	
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

	private void advanceLevel() {
		Level newLevel = level.getNextLevel();
		if(newLevel == null) //No more levels
			gameStatus = GameState.WON;
		else
      level = newLevel;
	}

	public void updateGame(char userInput) {
		int[] nextHeroMovement = charToMovement(userInput);

		level.updatePositions(nextHeroMovement);
		if(level.hasWon())
			advanceLevel();
		else if(level.hasLost())
			gameStatus = GameState.LOST;
	}

	public boolean isRunning() {
		return gameStatus == GameState.RUNNING;
	}

	public static int getnOgres() {
		return nOgres;
	}

	public static String getGuardType() {
		return guardType;
	}
}
