package logic;

/**
 * 
 * Map used in the first game level
 *
 */
public class DungeonMap extends Map{
	
	/**
	 * Constructor. Initializes the map matrix to the default 10x10 Dungeon map
	 */
  public DungeonMap(){
	height = 10;
	width = 10;
    mapMatrix = new char[height][width];
    char[][] tempMatrix = {
              {'X','X','X','X','X','X','X','X','X','X'},
              {'X','.','.','.','I','.','X','.','.','X'},
              {'X','X','X','.','X','X','X','.','.','X'},
              {'X','.','I','.','I','.','X','.','.','X'},
              {'X','X','X','.','X','X','X','.','.','X'},
              {'I','.','.','.','.','.','.','.','.','X'},
              {'I','.','.','.','.','.','.','.','.','X'},
              {'X','X','X','.','X','X','X','X','.','X'},
              {'X','.','I','.','I','.','X','k','.','X'},
              {'X','X','X','X','X','X','X','X','X','X'}
          };
   setMap(tempMatrix);
  }

  /**
   * Opens the map's doors (changes the doors' representation from 'I' to 'S')
   */
  public void openDoors(){
    mapMatrix[5][0] = 'S';
    mapMatrix[6][0] = 'S';
  }
}
