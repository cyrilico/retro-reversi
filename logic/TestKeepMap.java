package logic;
/**
 * Map used for Unit testing purposes simulating a smaller version of the second level map
 *
 */
public class TestKeepMap extends Map{
	/**
	 * Constructor. Initializes the map matrix to the predefined map
	 */
	public TestKeepMap(){
		width = 6;
		height = 6;

		mapMatrix = new char[6][6];
		char[][] tempMatrix = {
				{'X','X','X','X','X','X'},
				{'I','.','.','.','k','X'},
				{'X','.','.','.','.','X'},
				{'X','.','.','.','.','X'},
				{'X','.','.','.','.','X'},
				{'X','X','X','X','X','X'}
		};
		setMap(tempMatrix);
	}
	/**
	 * Opens the map's doors (changes the doors' representation from 'I' to 'S')
	 */
	public void openDoors(){
		mapMatrix[1][0] = 'S';
	}
}
