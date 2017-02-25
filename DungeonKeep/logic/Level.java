package logic;

public abstract class Level {
    protected enum LevelState {
        RUNNING, LOST, WON
    }

    protected LevelState levelStatus;
    public Level() {}

    protected Map map;
    protected Hero hero; //There's always a hero

    //For now, this works, maybe later add a superclass Villain to store all villains inside the same structure
    protected Guard[] guards;
    protected Ogre[] ogres;

    public abstract void updatePositions(int[] input);
    public abstract void updateCollisions();
    //With proper modulation, can getLevelMatrix be the same for all levels?
    public abstract char[][] getLevelMatrix();    
    public abstract Level getNextLevel();


    public boolean enemyInSurroundings(int x, int y, char rep) {
        if(map.elementAt(x+1,y) == rep ||
                map.elementAt(x-1,y) == rep || 
                map.elementAt(x,y+1) == rep || 
                map.elementAt(x,y-1) == rep)
            return true;

        return false;
    }

    public boolean hasWon() {
        return levelStatus == LevelState.WON;
    }
    public boolean hasLost() {
        return levelStatus == LevelState.LOST;
    }
}
