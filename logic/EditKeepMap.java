package logic;


/**
 * 
 * Custom map created by user, used in the second game level
 *
 */
public class EditKeepMap extends Map {
	/**
	 * Constructor. Initializes map matrix according to map created by user
	 * 
	 * @param map Map created by user for the level and to be played on
	 */
	public EditKeepMap(char[][] map) {
		height = map.length;
		width = map[0].length;

		mapMatrix = new char[height][width];
		setMap(map);
	}

	/**
	 * Opens the map's doors (changes the doors' representation from 'I' to 'S')
	 */
	public void openDoors() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(elementAt(j,i) == 'I')
					mapMatrix[i][j] = 'S';
 			}
		}
	}

}
