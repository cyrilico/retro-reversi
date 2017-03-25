package logic;

/**
 * Holds information about a game map
 *
 */
public abstract class Map implements java.io.Serializable{
	/**
	 * The map's height (in number of cells)
	 */
	protected int height;
	/**
	 * The map's width (in number of cells)
	 */
	protected int width;
	/**
	 * Char matrix where the map will be stored at
	 */
	protected char[][] mapMatrix;
	/**
	 * Sets the map matrix to a specific map (actually a clone of the specified)
	 * @param newMap Map to be replicated to mapMatrix
	 * @see mapMatrix
	 */
	protected void setMap(char[][] newMap) {
		int index = 0;
		for(char[] line : newMap)
			mapMatrix[index++] = (char[])line.clone();
	}
	/**
	 * Constructor. Does nothing.
	 */
	public Map() {}
	/**
	 * Gets the element at a specific position in the map's matrix. Assumes values are in range
	 * @param x Element's x-axis position
	 * @param y Element's y-axis position
	 * @return Element at specified coordinates
	 */
	public char elementAt(int x, int y) {
		return mapMatrix[y][x];
	}
	/**
	 * Opens the map's doors. Different for each map has the door(s) is/are not always on the same place
	 */
	public abstract void openDoors();
	
	/**
	 * Gets a copy of the current map's plant
	 * 
	 * @return char matrix containing a copy of the plant
	 */
	public char[][] getCurrentPlan(){
		char[][] plant = new char[height][width];
		int index = 0;
		for(char[] line : mapMatrix)
			plant[index++] = (char[])line.clone();

		return plant;
	}
}
