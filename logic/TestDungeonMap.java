package logic;

/**
 * Map used for Unit testing purposes simulating a smaller version of the first level map
 *
 */

public class TestDungeonMap extends Map {
	/**
	 * Constructor. Initializes the map matrix to the predefined map
	 */
    public TestDungeonMap(){
    	width = 5;
    	height = 5;
    	
        mapMatrix = new char[height][width];
        char[][] tempMatrix = {
                {'X','X','X','X','X'},
                {'X','.','.','.','X'},
                {'I','.','.','.','X'},
                {'I','k','.','.','X'},
                {'X','X','X','X','X'},
        };
        setMap(tempMatrix);
    }
    /**
	 * Opens the map's doors (changes the doors' representation from 'I' to 'S')
	 */
    public void openDoors(){
        mapMatrix[2][0] = 'S';
        mapMatrix[3][0] = 'S';
    }
}
