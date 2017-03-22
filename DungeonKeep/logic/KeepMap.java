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

	public KeepMap(String map) {

		char currentChar = 'X';
		int counter;
		for(counter = 0; currentChar != '\n'; counter++)
			currentChar = map.charAt(counter);

		width = counter - 1;
		height = map.length() / (width + 1);
		char[][] tempMatrix = new char[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++)
				tempMatrix[height][width] = map.charAt(i + (j * (width + 1)));
		}
		
		setMap(tempMatrix);
	}

		public void openDoors(){
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
