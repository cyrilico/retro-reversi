import java.util.Scanner;

public class Game {
	public enum GameState {
		RUNNING, LOST, WON
	}
	
	public GameState gameStatus;
	public int level;
    public char[][][] maps = {
                                {
                                    {'X','X','X','X','X','X','X','X','X','X'},
                                    {'X','H','.','.','I','.','X','.','G','X'},
                                    {'X','X','X','.','X','X','X','.','.','X'},
                                    {'X','.','I','.','I','.','X','.','.','X'},
                                    {'X','X','X','.','X','X','X','.','.','X'},
                                    {'I','.','.','.','.','.','.','.','.','X'},
                                    {'I','.','.','.','.','.','.','.','.','X'},
                                    {'X','X','X','.','X','X','X','X','.','X'},
                                    {'X','.','I','.','I','.','X','k','.','X'},
                                    {'X','X','X','X','X','X','X','X','X','X'}
                                },
                                    {/* Second level map */}    
    						};
	public int guardMovIndex;
	public int[][] guardMovements = {  {-1,0},
	                                   {0,1},
	                                   {0,1},
	                                   {0,1},
	                                   {0,1},
	                                   {-1,0},
	                                   {-1,0},
	                                   {-1,0},
	                                   {-1,0},
	                                   {-1,0},
	                                   {-1,0},
	                                   {0,1},
	                                   {1,0},
	                                   {1,0},
	                                   {1,0},
	                                   {1,0},
	                                   {1,0},
	                                   {1,0},
	                                   {1,0},
	                                   {0,-1},
	                                   {0,-1},
	                                   {0,-1},
	                                   {0,-1},
	                                   {0,-1}
									};
    public int[] playerPosition = {1,1}; //Initial position
    public int[] guardPosition = {8,1}; //Initial position

    public Game() {
    	gameStatus = GameState.RUNNING;
    	level = 0;
    	guardMovIndex = 0;
    }
    
    public void openDoors(int level){
    	if(level == 0){
    		maps[0][5][0] = 'S';
    		maps[0][6][0] = 'S';
    	}
    }
    
    public void playerHasLost(int level){
    	if(level == 0){
    		int playerX = playerPosition[0];
    		int playerY = playerPosition[1];
    		if(maps[0][playerY][playerX] == 'G' ||
    		   maps[0][playerY-1][playerX] == 'G' ||
    		   maps[0][playerY+1][playerX] == 'G' ||	
    		   maps[0][playerY][playerX-1] == 'G' ||
    		   maps[0][playerY][playerX+1] == 'G')
    			gameStatus = GameState.LOST;
    	}
    }
    
    //Convert user input to vector corresponding to desired player movement
	public int[] charToMovement(char input){
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
	
	public void moveGuard(){
		int nextGuardMovement[] = guardMovements[guardMovIndex];
		maps[0][guardPosition[1]][guardPosition[0]] = '.';
		guardPosition[0] += nextGuardMovement[0];
		guardPosition[1] += nextGuardMovement[1];
		maps[0][guardPosition[1]][guardPosition[0]] = 'G';
		if(++guardMovIndex == guardMovements.length) //Restart movement pattern
			guardMovIndex = 0;
	}
	
	public void updatePosition(int[] movement, int level){
		int dx = movement[0];
		int dy = movement[1];
		int playerY = playerPosition[1];
		int playerX = playerPosition[0];
		char currentChar = maps[level][playerY+dy][playerX+dx];
		switch(currentChar){
			case '.':
			case 'k':
			case 'S':
				maps[level][playerY][playerX] = '.';
				maps[level][playerY+dy][playerX+dx] = 'H';
				if(currentChar == 'k')
					openDoors(level);
				else if(currentChar == 'S'){
					gameStatus = GameState.WON;
					return; /*No need to update anything else.
					 		 *Also, avoids 'checkIfPlayerLost' checking on negative indexes (no elements to the left)
					 		 */
				}
				break;
			default:
				dx = 0;
				dy = 0;
				break;
		}
		
		//Update player's position based on movement vector ([0,0] if invalid key pressed)
		playerPosition[0] += dx;
		playerPosition[1] += dy;
		moveGuard();
		playerHasLost(level); //Checks if guard is in player's surroundings, updating gameStatus attribute if necessary
	}
	
    public void showMap(int mapIndex) {
        for(char[] line : maps[mapIndex]){
            for(char element : line)
                System.out.print(element);
            System.out.println("");
        }
    }

    public static void main(String[] args) {
    	Scanner buffer = new Scanner(System.in);
        Game g1 = new Game();
        char userInput; //Player movement input
        g1.showMap(0); //Show map before user presses a key (for now, only the first map so index 0)
        
        do {
			userInput = buffer.next().charAt(0);
			g1.updatePosition(g1.charToMovement(userInput), 0);
			g1.showMap(0);
			System.out.print("\n\n");
		} while(g1.gameStatus == GameState.RUNNING);
        
        switch(g1.gameStatus){
        	case LOST:
        		System.out.println("Captured!");
        		break;
        	case WON:
        		System.out.println("Escaped!");
        		break;
        	default:
        		System.out.println("How the fuck did you end up here");
        		break;
        }
        buffer.close();
    }
}
