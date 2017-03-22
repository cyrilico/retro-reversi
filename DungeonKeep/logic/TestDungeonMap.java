package logic;

public class TestDungeonMap extends Map {
    public TestDungeonMap(){
    	width = 5;
    	height = 5;
    	
        mapMatrix = new char[height][width];
        char[][] tempMatrix = {
                {'X','X','X','X','X'},
                {'X','.','.','.','X'},
                {'I','.','.','.','X'},
                {'I','k','.','.','X'},
                {'X','X','X','X','X'},
        };
        setMap(tempMatrix);
    }

    public void openDoors(){
        mapMatrix[2][0] = 'S';
        mapMatrix[3][0] = 'S';
    }
}
