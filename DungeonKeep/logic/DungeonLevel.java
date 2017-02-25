package logic;

public class DungeonLevel extends Level {
    public DungeonLevel() {
        //Set initial map
        map = new DungeonMap();

        //Set initial status
        levelStatus = LevelState.RUNNING;

        //Set characters
        hero = new Hero(1,1);
        guards = new Guard[1];
        guards[0] = new Rookie(8,1);
        ogres = null;
    }

    //Input is already processed by charToMovement
    public void updatePositions(int[] input) {
        int dx = input[0];
        int dy = input[1];

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];

        char currentChar = map.elementAt(heroX+dx, heroY+dy);

        switch(currentChar) {
            case '.':
            case 'k':
            case 'S':
                //Check if hero is on top of key
                if(currentChar == 'k')
                    map.openDoors();
                //Check if hero is on top of open door
                if(currentChar == 'S') {
                    levelStatus = LevelState.WON;
                    return;
                }
            default:
                dy = 0;
                dx = 0;
        }

        //Update the hero's position
        hero.setCoordinates(heroX+dx, heroY+dy);

        //Update the villains' position
        for (Guard elem : guards) 
            elem.updateGuardPosition();
        for(Ogre elem : ogres) {
            boolean validMovement = false;
            do {
                int[] nextPosition = elem.nextPosition();
                if(map.elementAt(nextPosition[0],nextPosition[1]) == '.')
                    validMovement = true;
            } while(!validMovement);
           elem.setCoordinates(nextPosition[0], nextPosition[1]); 
        }
    }

    public void updateCollisions() {
        boolean hasCollision = false;

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];

        for(Guard elem : guards) { 
            if(enemyInSurroundings(heroX, heroY, elem.getRepresentation()))
                    hasCollision = true;
        }
        for(Ogre elem: ogres) {
            if(enemyInSurroundings(heroX, heroY, elem.getRepresentation()))
                    hasCollision = true;
        }

        if(hasCollision)
        levelStatus = LevelState.LOST;
    }

    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        int[] heroCoordinates = hero.getCoordinates();
        int heroX = heroCoordinates[0];
        int heroY = heroCoordinates[1];
        matrix[heroX][heroY] = hero.getRepresentation();

        //Draw villains
        for(Guard elem : guards) 
            matrix[elem.getCoordinateX()][elem.getCoordinateY()] = elem.getRepresentation();
        for(Ogre elem : ogres) 
            matrix[elem.getCoordinateX()][elem.getCoordinateY()] = elem.getRepresentation();

        return matrix;
    }

    public Level getNextLevel() {
        return null;
    }
}


