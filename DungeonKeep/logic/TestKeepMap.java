package logic;

public class TestKeepMap extends Map{
  public TestKeepMap(){
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

  public char[][] getCurrentPlan(){
    char[][] plant = new char[6][6];
    int index = 0;
    for(char[] line : mapMatrix)
      plant[index++] = (char[])line.clone();

    return plant;
  }
}
