package logic;

public class TestKeepMap extends Map{
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

	public void openDoors(){
		mapMatrix[1][0] = 'S';
	}
}
