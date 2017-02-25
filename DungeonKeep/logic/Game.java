package logic;

public class Game {
	protected enum GameState {
        RUNNING, LOST, WON
    }

	protected GameState gameStatus;
    protected Level level;

	public Game() {
		gameStatus = GameState.RUNNING;
		level = new DungeonLevel();
	}

	private int[] charToMovement(char input){
        int[] result = new int[2];
        switch(input){
            case 'a':
                result[0]--; //[-1,0] - left
                break;
            case 'w':
                result[1]--; //[0,-1] - up
                break;
            case 's':
                result[1]++; //[0,1] - down
                break;
            case 'd':
                result[0]++; //[1,0] - right
                break;
            default: //[0,0] - invalid movement key
                break;
        }
        return result;
    }

	public void finalMessage() {
        switch(gameStatus){
        case LOST:
            System.out.println("Captured!");
            break;
        case WON:
            System.out.println("Escaped!");
            break;
        default:
            System.out.println("How did you end up here?!"); //If it reaches this case... screw this, I'm out!
            break;
        }
	}

	public String getCurrentMatrix() {
        String result = "\n";
        char[][] matrix = level.getLevelMatrix(); 

        for(char[] elem : matrix) { 
            result += new String(elem);
            result += '\n';
        }
        return result;
    }

	private void advanceLevel() {
		if(level.getNextLevel() == null)
			gameStatus = GameState.WON;
		else
            level = level.getNextLevel();
	}

    public void updateGame(char userInput) {
        int[] nextHeroMovement = charToMovement(userInput);

        level.updatePositions(nextHeroMovement);
        level.updateCollisions();
        if(level.hasWon())
            advanceLevel();
    }

	public boolean isRunning() {
		return gameStatus == GameState.RUNNING;
	}
}
