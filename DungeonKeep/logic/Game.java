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

	public void finalMessage(){
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

	public String sendMap(){}

	private void advanceLevel(){
		if((map = map.getNextLevel()) == null)
			gameStatus = GameState.WON;
		else
            level = leve.getNextLevel();
	}

    public void updateGame(char userInput) {
        int[] nextHeroMovement = charToMovement(userInput);

        level.updateLevel(nextHeroMovement);
        if(level.hasWon())
            advanceLevel();
    }

    public void playerHasLost(){
    	int[] heroCoordinates = hero.getCoordinates();
        if(level == 0) {
            if(map.enemyInSurroundings(heroCoordinates[1],heroCoordinates[0],'G'))
                gameStatus = GameState.LOST;
        }
        else if(level == 1) {
            if(map.enemyInSurroundings(heroCoordinates[1],heroCoordinates[0],'0') ||
            	map.enemyInSurroundings(heroCoordinates[1],heroCoordinates[0],'$') ||
            	map.enemyInSurroundings(heroCoordinates[1],heroCoordinates[0],'*')) //Respectively: ogre, ogre while in key position, ogre's club
            gameStatus = GameState.LOST;
        }
    }

	public boolean isRunning(){
		return gameStatus == GameState.RUNNING;
	}
}
