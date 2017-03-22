package logic;

public class KeepMap extends Map {

	private int width;
	private int height;

	public KeepMap() {

		width = 9;
		height = 9;

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

	public void openDoors() {
		mapMatrix[1][0] = 'S';
	}

	public char[][] getCurrentPlan(){
		char[][] plant = new char[height][width];
		int index = 0;
		for(char[] line : mapMatrix)
			plant[index++] = (char[])line.clone();

		return plant;
	}
}
