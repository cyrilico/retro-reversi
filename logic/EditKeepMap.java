package logic;

public class EditKeepMap extends Map {

	public EditKeepMap(char[][] map) {
		height = map.length;
		width = map[0].length;

		mapMatrix = new char[height][width];
		setMap(map);
	}

	@Override
	public void openDoors() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(elementAt(j,i) == 'I')
					mapMatrix[i][j] = 'S';
 			}
		}
	}

}
