package logic;

public abstract class Map {
	protected char[][] mapMatrix;

	protected void setMap(char[][] newMap) {
		int index = 0;
		for(char[] line : newMap)
			mapMatrix[index++] = (char[])line.clone();
	}

	public Map() {}

	public char elementAt(int x, int y) {
		return mapMatrix[y][x];
	}

	public abstract Map getNextLevel();
	public abstract void openDoors();
	public abstract char[][] getCurrentPlan();
}
