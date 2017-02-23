package logic;

public class Map {
	protected char[][] mapMatrix = {
            {'X','X','X','X','X','X','X','X','X','X'},
            {'X','H','.','.','I','.','X','.','G','X'},
            {'X','X','X','.','X','X','X','.','.','X'},
            {'X','.','I','.','I','.','X','.','.','X'},
            {'X','X','X','.','X','X','X','.','.','X'},
            {'I','.','.','.','.','.','.','.','.','X'},
            {'I','.','.','.','.','.','.','.','.','X'},
            {'X','X','X','.','X','X','X','X','.','X'},
            {'X','.','I','.','I','.','X','k','.','X'},
            {'X','X','X','X','X','X','X','X','X','X'}
        };
	
	public Map() {
	}
	
	public char elementAt(int x, int y){
		return mapMatrix[y][x];
	}
	
	public void setElementAt(int x, int y, char newElement){
		mapMatrix[y][x] = newElement;
	}
	
	public void setNextLevel(){
		mapMatrix = new char[9][9];
		char[][] tempMatrix = {
	            {'X','X','X','X','X','X','X','X','X'},
	            {'I','.','.','.','O','.','.','k','X'},
	            {'X','.','.','.','.','.','.','.','X'},
	            {'X','.','.','.','.','.','.','.','X'},
	            {'X','.','.','.','.','.','.','.','X'},
	            {'X','.','.','.','.','.','.','.','X'},
	            {'X','.','.','.','.','.','.','.','X'},
	            {'X','H','.','.','.','.','.','.','X'},
	            {'X','X','X','X','X','X','X','X','X'}
	        };
		int index = 0;
		for(char[] line : tempMatrix)
			mapMatrix[index++] = (char[])line.clone();
	}
	
	public void openDoors(int level){ //Need to know level to know which doors to open
		if(level == 0){
			setElementAt(0,5,'S');
			setElementAt(0,6,'S');
		}
		else //level == 1
			setElementAt(0,1,'S');
	}
	
	public boolean enemyInSurroundings(int playerX, int playerY, char enemy){
		return  mapMatrix[playerY][playerX] == enemy ||
	            mapMatrix[playerY-1][playerX] == enemy ||
	            mapMatrix[playerY+1][playerX] == enemy ||
	            mapMatrix[playerY][playerX-1] == enemy ||
	            mapMatrix[playerY][playerX+1] == enemy;
	}

	public void print(){ /*TODO: Maybe delete this, override toString as suggested in guide and use it instead?*/
		for(char[] line : mapMatrix){
            for(char element : line)
                System.out.print(element);
            System.out.println("");
        }
        System.out.print("\n\n");
	}
}
