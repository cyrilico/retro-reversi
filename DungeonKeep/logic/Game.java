package logic;

public class Game {
	protected enum GameState {
        RUNNING, LOST, WON
    }

		protected GameState gameStatus;
    protected Level level;

	public Game(Level level) {
		gameStatus = GameState.RUNNING;
		this.level = level;
	}
	
	public int getCurrentLevelIndex(){
		return level.getIndex();
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
            System.out.println("You've been captured!");
            break;
        case WON:
            System.out.println("You've escaped from all levels!");
            break;
        default:
            System.out.println("How did you end up here?!"); //If it reaches this case... screw this, I'm out!
            break;
        }
	}

	public String getCurrentMatrix() {
        String result = "\n";
        char[][] matrix = level.getLevelMatrix();

        for(char[] line : matrix) {
					for(char element : line)
            	result += element;
            result += '\n';
        }

				return result;
    }

	private void advanceLevel() {
		System.out.println("You escaped this level. Let's see what awaits you next...");
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
}
