import java.util.Scanner;
import java.util.Random;

// NOTE: Coordinates are always in the order [y,x], where top left corner is the system's origin

public class Game {
    public enum GameState {
        RUNNING, LOST, WON
    }

    public GameState gameStatus;
    public int level;
    public boolean hasKey; //Used in level 1 to control level exit and player char
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
    public int[] ogreClubOffset = {0,0}; //Offset relating to ogre's position (ex: if it is [-1,0], then it is below the ogre)

    public Game() {
        gameStatus = GameState.RUNNING;
        level = 0;
        hasKey = false;
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

    //Detect if an enemy element is in the hero's surroundings (to use in next function)
    public boolean enemyInSurroundings(int level, char enemy){
      int playerX = playerPosition[1];
      int playerY = playerPosition[0];
      return  maps[level][playerY][playerX] == enemy ||
              maps[level][playerY-1][playerX] == enemy ||
              maps[level][playerY+1][playerX] == enemy ||
              maps[level][playerY][playerX-1] == enemy ||
              maps[level][playerY][playerX+1] == enemy;
    }

    public void playerHasLost(int level){
        if(level == 0) {
            if(enemyInSurroundings(0,'G'))
                gameStatus = GameState.LOST;
        }
        else if(level == 1) {
            if(enemyInSurroundings(1,'0') || enemyInSurroundings(1,'$') || enemyInSurroundings(1,'*')) //Respectively: ogre, ogre while in key position, ogre's club
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
        int movementType = generator.nextInt(2);
        int randomY;
        int randomX;
        if(movementType == 0){ //Horizontal movement
          randomY = 0;
          do{
            randomX = generator.nextInt(3) - 1;
          }while(randomX == 0); //Just to make sure he does indeed move every time
        }
        else{ //movementType == 1 ; Vertical movement
          do{
            randomY = generator.nextInt(3) - 1;
          }while(randomY == 0); //Just to make sure he does indeed move every time
          randomX = 0;
        }

        int[] result = {randomY, randomX};
        return result;
    }

    public void moveOgre() {
        /* First, calculate the ogre's new position (but not updating it yet) */
        boolean isLeavingKey = false;
        if(maps[1][ogrePosition[0]][ogrePosition[1]] == '$')
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
        } while(maps[1][ogrePosition[0]+randomY][ogrePosition[1]+randomX] == 'X' ||
                maps[1][ogrePosition[0]+randomY][ogrePosition[1]+randomX] == 'I' ||
                maps[1][ogrePosition[0]+randomY][ogrePosition[1]+randomX] == 'S'); /* About these last two: can't have him go to the door, that's our only way out! :[ */

        /* Now, swing his club of doom! */
        isLeavingKey = false;
        int clubY = ogrePosition[0] + ogreClubOffset[0];
        int clubX = ogrePosition[1] + ogreClubOffset[1];
        if(maps[1][clubY][clubX] == '$')
            isLeavingKey = true;

        if(isLeavingKey)
          maps[1][clubY][clubX] = 'k';
        else
          maps[1][clubY][clubX] = '.';

        //Now that we've removed the club from its old position, update the ogre's position and swing it to a new one based on the updated coordinates
        ogrePosition[0] += randomY;
        ogrePosition[1] += randomX;

        if(maps[1][ogrePosition[0]][ogrePosition[1]] == 'k') //Checking if ogre's new position is on top of key
            maps[1][1][7] = '$';
        else
            maps[1][ogrePosition[0]][ogrePosition[1]] = '0'; //Default case

        //Generate random integers between -1 and 1;
        int offsetY;
        int offsetX;
        int[] currentOffset;
        do {
            currentOffset = randomMovement();
            offsetY = currentOffset[0];
            offsetX = currentOffset[1];
        } while(maps[1][ogrePosition[0]+offsetY][ogrePosition[1]+offsetX] == 'X' ||
                maps[1][ogrePosition[0]+offsetY][ogrePosition[1]+offsetX] == 'I' ||
                maps[1][ogrePosition[0]+offsetY][ogrePosition[1]+offsetX] == 'S'); //Could let him swing at the door and open it by force... Maybe later

        ogreClubOffset[0] = offsetY;
        ogreClubOffset[1] = offsetX;
        if(maps[1][ogrePosition[0]+ogreClubOffset[0]][ogrePosition[1]+ogreClubOffset[1]] == 'k') //Checking if club's new position is on top of key
            maps[1][1][7] = '$';
        else
            maps[1][ogrePosition[0]+ogreClubOffset[0]][ogrePosition[1]+ogreClubOffset[1]] = '*'; //Default case
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
                if(currentChar == 'k'){
                    if(level == 1)
                      hasKey = true;
                    else
                      openDoors(level); //Could just use openDoors(0) but whatever
                }
                else if(currentChar == 'S') {
                    if(level == 0) {
                        this.level = 1;
                        //Set Level 1 initial position
                        playerPosition[0] = 7;
                        playerPosition[1] = 1;
                    }
                    else //level 1
                        gameStatus = GameState.WON;
                    return; /*No need to update anything else.
                             *Also, avoids 'checkIfPlayerLost' checking on negative indexes (no elements to the left)
                             */
                }
                break;
            case 'I':
                if(level == 1)
                  openDoors(level); //Could just use openDoors(1) but whatever
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
        maps[level][playerY+dy][playerX+dx] = (hasKey && level == 1) ? 'K' : 'H';

        //Update player's position based on movement vector ([0,0] if invalid key pressed)
        playerPosition[0] += dy;
        playerPosition[1] += dx;
        if(level == 0)
            moveGuard();
        else if(level == 1)
            moveOgre();

        playerHasLost(level); //Checks if enemy is in player's surroundings, updating gameStatus attribute if necessary
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
