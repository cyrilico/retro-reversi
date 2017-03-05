package logic;

public abstract class Level {
    protected enum LevelState {
        RUNNING, LOST, WON
    }

    protected boolean heroHasKey;
    protected LevelState levelStatus;
    protected int levelIndex;
    
    public Level() {
      heroHasKey = false;
      levelStatus = LevelState.RUNNING;
    }

    protected Map map;
    protected Hero hero; /* There's always this hero... */

    /* ... but villains come in all shapes and sizes depending on the level, so we'll only declare them in their respective level */

    //Game class transforms key received into movement vector and sends that information to the function
    //Updates every dynamic element's position
    public abstract void updatePositions(int[] input);

    //Checks if any enemy is in the hero's surroundings and changes level state accordingly if so
    public abstract void checkIfHeroCaptured();

    /* Returns a copy of the game map with all the characters placed */
    public abstract char[][] getLevelMatrix();

    public abstract Level getNextLevel();
    
    public int getIndex(){
    	return levelIndex;
    }

    public boolean hasWon() {
        return levelStatus == LevelState.WON;
    }

    public boolean hasLost() {
        return levelStatus == LevelState.LOST;
    }
    
    public int[] getHeroCoordinates() {
    	return hero.getCoordinates();
    }
    
    public char getHeroRep() {
    	return hero.getRepresentation();
    }
}
