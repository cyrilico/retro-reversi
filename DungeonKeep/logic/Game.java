package logic;

public class Game {
	protected enum GameState {
        RUNNING, LOST, WON
    }
	
	protected GameState gameStatus;
	protected int level;
	protected Map map;
	protected Hero hero;
	protected Guard guard; //Saving it as a guard because of possible change of guard type throughout the game? According to the guide
	protected Ogre ogre; //Initialize only when player gets to level1
	
	public Game() {
		gameStatus = GameState.RUNNING;
		level = 0;
		map = new Map();
		hero = new Hero(1,1);
		guard = new Rookie(8,1);
	}
	
	private int[] charToMovement(char input){
        int[] result = new int[2];
        switch(input){
            case 'a':
                result[1]--; //[0,-1] - left
                break;
            case 'w':
                result[0]--; //[-1,0] - up
                break;
            case 's':
                result[0]++; //[1,0] - down
                break;
            case 'd':
                result[1]++; //[0,1] - right
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
	
	public void printMap(){
		map.print();
	}
	
	private void advanceLevel(){
		level = 1;
		hero.setCoordinates(1, 7);
		ogre = new Ogre(4,1);
		map.setNextLevel();
	}
	
	public void updateGame(char userInput){
		/* Updating hero's position (TODO: Change this to hero class) */
		int[] nextHeroMovement = charToMovement(userInput);
		int dy = nextHeroMovement[0];
		int dx = nextHeroMovement[1];
		int[] heroCoordinates = hero.getCoordinates();
		char currentChar = map.elementAt(heroCoordinates[1]+dx, heroCoordinates[0]+dy);
		switch(currentChar) {
		case '.':
		case 'k':
		case 'S':
			map.setElementAt(heroCoordinates[1], heroCoordinates[0], '.');;
			if(currentChar == 'k'){
				if(level == 1){
					hero.setCollectedKey();
					hero.setRepresentation('K');
				}
				else
					map.openDoors(level); //Could just use openDoors(0) but whatever
			}
			else if(currentChar == 'S') {
				if(level == 0)
					advanceLevel();
				else //level 1
					gameStatus = GameState.WON;
				return; /*No need to update anything else.
				 		*Also, avoids 'playerHasLot' checking on negative indexes (no elements to the left)
				 		*/
			}
			break;
		case 'I':
			if(level == 1)
				map.openDoors(level); //Could just use openDoors(1) but whatever
			//No break statement because player shouldn't move yet:
			// -This is the move he spends opening the door if he's on level1
			// -Can't move through a closed door if he's on level0 or level1
			//The lack of break statement causes the code below to be executed, which prevents player movement
		default:
			dy = 0;
			dx = 0;
			break;
		}

		//Draw hero
		map.setElementAt(heroCoordinates[1]+dx, heroCoordinates[0]+dy, hero.getRepresentation());

		//Update player's position based on movement vector ([0,0] is added if invalid key pressed)
		hero.setCoordinates(heroCoordinates[1]+dx, heroCoordinates[0]+dy);
		if(level == 0)
			guard.updateGuardPosition(map);
		else if(level == 1)
			ogre.updatePosition(map);

		playerHasLost(); //Checks if enemy is in player's surroundings, updating gameStatus attribute if necessary
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
