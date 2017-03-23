package logic;

public class DungeonMap extends Map{
  public DungeonMap(){
    mapMatrix = new char[10][10];
    char[][] tempMatrix = {
              {'X','X','X','X','X','X','X','X','X','X'},
              {'X','.','.','.','I','.','X','.','.','X'},
              {'X','X','X','.','X','X','X','.','.','X'},
              {'X','.','I','.','I','.','X','.','.','X'},
              {'X','X','X','.','X','X','X','.','.','X'},
              {'I','.','.','.','.','.','.','.','.','X'},
              {'I','.','.','.','.','.','.','.','.','X'},
              {'X','X','X','.','X','X','X','X','.','X'},
              {'X','.','I','.','I','.','X','k','.','X'},
              {'X','X','X','X','X','X','X','X','X','X'}
          };
   setMap(tempMatrix);
  }

  public void openDoors(){
    mapMatrix[5][0] = 'S';
    mapMatrix[6][0] = 'S';
  }

  public char[][] getCurrentPlan(){
    char[][] plant = new char[10][10];
    int index = 0;
    for(char[] line : mapMatrix)
      plant[index++] = (char[])line.clone();

    return plant;
  }
}
