import java.util.Scanner;
import java.util.Random;

// NOTE: Coordinates are always in the order [y,x], where top left corner is the system's origin

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
        {
            {'X','X','X','X','X','X','X','X','X'},
            {'I','.','.','.','O','.','.','k','X'},
            {'X','.','.','.','.','.','.','.','X'},
            {'X','.','.','.','.','.','.','.','X'},
            {'X','.','.','.','.','.','.','.','X'},
            {'X','.','.','.','.','.','.','.','X'},
            {'X','.','.','.','.','.','.','.','X'},
            {'X','H','.','.','.','.','.','.','X'},
            {'X','X','X','X','X','X','X','X','X'}
        }    
    };
    public int guardMovIndex;
    public int[][] guardMovements = {  
        {0,-1},
        {1,0},
        {1,0},
        {1,0},
        {1,0},
        {0,-1},
        {0,-1},
        {0,-1},
        {0,-1},
        {0,-1},
        {0,-1},
        {1,0},
        {0,1},
        {0,1},
        {0,1},
        {0,1},
        {0,1},
        {0,1},
        {0,1},
        {-1,0},
        {-1,0},
        {-1,0},
        {-1,0},
        {-1,0}
    };
    public Random generator;

    public int[] playerPosition = {1,1}; //Initial position (level 1)
    public int[] guardPosition = {1,8}; //Initial position
    public int[] ogrePosition = {1,4};

    public Game() {
        gameStatus = GameState.RUNNING;
        level = 0;
        guardMovIndex = 0;

        generator = new Random(); 
    }

    public void openDoors(int level){
        if(level == 0) {
            maps[0][5][0] = 'S';
            maps[0][6][0] = 'S';
        }
        else if(level == 1)
            maps[1][1][0] = 'S';
    }

    public void playerHasLost(int level){
        if(level == 0) {
            int playerX = playerPosition[1];
            int playerY = playerPosition[0];
            if(maps[0][playerY][playerX] == 'G' ||
                    maps[0][playerY-1][playerX] == 'G' ||
                    maps[0][playerY+1][playerX] == 'G' ||	
                    maps[0][playerY][playerX-1] == 'G' ||
                    maps[0][playerY][playerX+1] == 'G')
                gameStatus = GameState.LOST;
        }
        else if(level == 1) {
            //TO DO: If Ogre is on the key its representation is $ but it can still capture the player. Check position
            int playerX = playerPosition[1];
            int playerY = playerPosition[0];
            if(maps[1][playerY][playerX] == '0' ||
                    maps[1][playerY-1][playerX] == '0' ||
                    maps[1][playerY+1][playerX] == '0' ||	
                    maps[1][playerY][playerX-1] == '0' ||
                    maps[1][playerY][playerX+1] == '0')
                gameStatus = GameState.LOST;
        }
    }

    //Convert user input to vector corresponding to desired player movement
    public int[] charToMovement(char input){
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

    public void moveGuard() {
        int nextGuardMovement[] = guardMovements[guardMovIndex];
        maps[0][guardPosition[0]][guardPosition[1]] = '.';

        guardPosition[0] += nextGuardMovement[0];
        guardPosition[1] += nextGuardMovement[1];
        maps[0][guardPosition[0]][guardPosition[1]] = 'G';

        if(++guardMovIndex == guardMovements.length) //Restart movement pattern
            guardMovIndex = 0;
    }

    public int[] randomMovement() {
        int randomY = generator.nextInt(3) - 1;
        int randomX = generator.nextInt(3) - 1;

        int[] result = {randomY, randomX};
        return result;
    }

    public void moveOgre() {
        boolean isLeavingKey = false;
        if(ogrePosition[0] == 1 && ogrePosition[1] == 7)
            isLeavingKey = true;

        if(isLeavingKey)
            maps[1][ogrePosition[0]][ogrePosition[1]] = 'k';
        else
            maps[1][ogrePosition[0]][ogrePosition[1]] = '.';

        //Generate random integers between -1 and 1;
        int randomY;
        int randomX;
        int[] currentRandom;
        do {
            currentRandom = randomMovement();
            randomY = currentRandom[0];
            randomX = currentRandom[1];
        } while(maps[1][ogrePosition[0]+randomY][ogrePosition[1]+randomX] == 'X');

        ogrePosition[0] += randomY; 
        ogrePosition[1] += randomX;

        if(ogrePosition[0] == 1 && ogrePosition[1] == 7) //Checking if ogre's new position is on top of key
            maps[1][ogrePosition[0]][ogrePosition[1]] = '$';
        else
            maps[1][ogrePosition[0]][ogrePosition[1]] = '0'; //Default case
    }

    public void updatePosition(int[] movement, int level) {
        int dy = movement[0];
        int dx = movement[1];

        int playerY = playerPosition[0];
        int playerX = playerPosition[1];
        char currentChar = maps[level][playerY+dy][playerX+dx];
        switch(currentChar) {
            case '.':
            case 'k':
            case 'S':
                maps[level][playerY][playerX] = '.';
                maps[level][playerY+dy][playerX+dx] = 'H';
                if(currentChar == 'k')
                    openDoors(level);
                else if(currentChar == 'S') {
                    if(level == 0) {
                        this.level = 1;

                        //Level 1 initial position
                        playerPosition[0] = 7;
                        playerPosition[1] = 1;
                        showMap(1);
                        System.out.print("\n\n");
                    }
                    else //level 1
                        gameStatus = GameState.WON;
                    return; /*No need to update anything else.
                             *Also, avoids 'checkIfPlayerLost' checking on negative indexes (no elements to the left)
                             */
                }
                break;
            default:
                dy = 0;
                dx = 0;
                break;
        }

        //Update player's position based on movement vector ([0,0] if invalid key pressed)
        playerPosition[0] += dy;
        playerPosition[1] += dx;
        if(level == 0)
            moveGuard();
        else if(level == 1)
            moveOgre();

        playerHasLost(level); //Checks if guard is in player's surroundings, updating gameStatus attribute if necessary
    }

    public void showMap(int level) {
        for(char[] line : maps[level]){
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
            g1.updatePosition(g1.charToMovement(userInput), g1.level);
            g1.showMap(g1.level);
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
