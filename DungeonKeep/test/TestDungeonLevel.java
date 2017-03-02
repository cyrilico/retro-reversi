package test;

import logic.*;
import java.util.Random;

public class TestDungeonLevel extends Level {
    /* To randomly choose a type of guard for the level */
    Random guardGenerator;
    /* The villains for the level */
    Guard guard;

    public TestDungeonLevel() {

        super();
        
        levelIndex = 2;
        map = new TestDungeonMap();

        /*Create level's characters*/
        //The hero
        hero = new Hero(1,1);

        //The guard
        guard =  new Rookie(3,1);

    }

    public void checkIfHeroCaptured(){
        int[] heroCoordinates = hero.getCoordinates();
        if(guard.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
            levelStatus = LevelState.LOST;
    }

    public void updatePositions(int[] input) {
        //Update the hero's position
        int dx = input[0];
        int dy = input[1];

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];

        char currentChar = map.elementAt(heroX+dx, heroY+dy);

        switch(currentChar) { //Checking what is present in the cell the hero wants to move to
            case 'S':
                levelStatus = LevelState.WON;
                return; /* Avoids checking for enemies on negative indexes */
            case 'k':
                map.openDoors(); /* No need to use the heroHasKey attribute on this level since the hero doesn't change his representation and doors are open right away */
            case '.':
                break;
            default: /* currentChar == 'X' || currentChar = 'I' so we can't move through */
                dy = 0;
                dx = 0;
        }

        hero.setCoordinates(heroX+dx, heroY+dy);

        //Update the villains' position
        //guard.updatePosition();

        checkIfHeroCaptured();
    }

    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        int[] heroCoordinates = hero.getCoordinates();
        int heroX = heroCoordinates[0];
        int heroY = heroCoordinates[1];
        matrix[heroY][heroX] = hero.getRepresentation();

        //Draw villains
        int[] guardCoordinates = guard.getCoordinates();
        int guardX = guardCoordinates[0];
        int guardY = guardCoordinates[1];
        matrix[guardY][guardX] = guard.getRepresentation();
        return matrix;
    }

    public int[] getHeroCoordinates() {
        int[] result = hero.getCoordinates();
        return result;
    }

    public Level getNextLevel() {
        return new KeepLevel();
    }
}
