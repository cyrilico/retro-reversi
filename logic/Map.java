package logic;

public abstract class Map implements java.io.Serializable{
	protected int height;
	protected int width;
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

	public abstract void openDoors();
	
	public char[][] getCurrentPlan(){
		char[][] plant = new char[height][width];
		int index = 0;
		for(char[] line : mapMatrix)
			plant[index++] = (char[])line.clone();

		return plant;
	}
}
