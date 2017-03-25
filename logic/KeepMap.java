package logic;

/**
 * The default map for the second game level
 *
 */
public class KeepMap extends Map {
	/**
	 * Constructor. Initializes the map matrix with the default Keep map
	 */
	public KeepMap() {
		height = 9;
		width = 9;
		mapMatrix = new char[height][width];
		char[][] tempMatrix = {
				{'X','X','X','X','X','X','X','X','X'},
				{'I','.','.','.','.','.','.','k','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','.','.','.','.','.','.','.','X'},
				{'X','X','X','X','X','X','X','X','X'}
		};
		setMap(tempMatrix);
	}
	
	
	/**
	 * Opens the map's doors (changes the doors' representation from 'I' to 'S')
	 */
	public void openDoors() {
		mapMatrix[1][0] = 'S';
	}
}
