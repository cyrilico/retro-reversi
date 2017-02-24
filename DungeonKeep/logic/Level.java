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

    public abstract void updateLevel(int[] input);
    //With proper modulation, can getLevelMatrix be the same for all levels?
    public abstract car[][] getLevelMatrix();    
    public abstract void openDoors();
    public abstract Level getNextLevel();

    public boolean hasWon() {
        return levelStatus == LevelState.WON;
    }
    public boolean hasLast() {
        return levelStatus == LevelState.LOST;
    }
}
