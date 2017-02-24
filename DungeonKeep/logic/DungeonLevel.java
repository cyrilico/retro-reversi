package logic;

public class DungeonLevel extends Level {
    public DungeonLevel() {
        //Set initial map
        map = new DungeonLevel();

        //Set initial status
        levelStatus = LevelState.RUNNING;

        //Set characters
        hero = new Hero(1,1);
        guards = new Guard[1];
        guards[0] = new Rookie(8,1);
        ogres = null;
    }

    //Input is already processed by charToMovement
    public void updateLevel(int[] input) {
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
                    openDoors();
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

    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        matrix[hero.getCoordinates()[1], hero.getCoordinates()[0]] = hero.getRepresentation();

        //Draw villains
        for(Guard elem : guards) 
            matrix[elem.getCoordinates()[1], elem.getCoordinates()[0]] = elem.getRepresentation();
        for(Ogre elem : ogres) 
            matrix[elem.getCoordinates()[1], elem.getCoordinates()[0]] = elem.getRepresentation();
        //Missing club drawing in ogre's case
    
        return matrix;
}

}


