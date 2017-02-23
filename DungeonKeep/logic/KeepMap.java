package logic;

public class DungeonMap extends Map{
  public DungeonMap(){
    mapMatrix = new char[9][9];
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

  public abstract Map getNextLevel(){
    return null;
  }

  public abstract void openDoors(){
    mapMatrix[1][0] = 'S';
  }

  public abstract char[][] getCurrentPlan(){
    char[][] plant = new char[9][9];
    int index = 0;
    for(char[] line : mapMatrix)
      plant[index++] = (char[])line.clone();
  }
}
