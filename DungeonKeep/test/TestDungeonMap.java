package test;

import logic.Map;

public class TestDungeonMap extends Map {
    public TestDungeonMap(){
        mapMatrix = new char[5][5];
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

    public char[][] getCurrentPlan(){
        char[][] plant = new char[5][5];
        int index = 0;
        for(char[] line : mapMatrix)
            plant[index++] = (char[])line.clone();

        return plant;
    }
}
