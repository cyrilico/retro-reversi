package logic;

public class Game {
	protected enum GameState {
        RUNNING, LOST, WON
    }

	protected GameState gameStatus;
	protected int level;
	protected Map map;
	protected Hero hero;
	protected Guard guard;
	protected Ogre[] ogres;

	public Game() {
		gameStatus = GameState.RUNNING;
		level = 0;
		map = new DungeonMap();
		hero = new Hero(1,1);
		guard = new Rookie(8,1);
		ogres = null;
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

	public String sendMap(){
	}

	private void advanceLevel(){
		if((map = map.getNextLevel()) == null)
			gameStatus = GameState.WON;
		else{
			level++;
			hero.setCoordinates(1, 7);
			ogres = new Ogre[1];
			ogres[0] = new Ogre(4,1);
		}
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
				advanceLevel();
				return; /*No need to update anything else.
				 		*Also, avoids 'playerHasLot' checking on negative indexes (no elements to the left)
				 		*/
			}
			break;
		case 'I':
			if(level == 1)
				map.openDoors(); //Could just use openDoors(1) but whatever
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
