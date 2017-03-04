package logic;

/* NOTE: Although this is an independent level, it is highly based on the 'real' KeepLevel, with the only changes being:
 * - The map (it is a smaller one, defined in TestKeepMap.java)
 * - The ogre, which movement is based on a boolean flag (which can be easily added to the 'real' level altering very few lines of code)
 * - The characters' starting positions
 * 
 * In order not to modify the original content these levels were created. Due to their similarity in the game functionalities, it
 * is expected that these are a good telling if the game logic is working well or not
 */

public class TestKeepLevel extends Level {
	
	/* The villains for the level */
    Ogre ogre;
    boolean ogreMoves;

    public TestKeepLevel(boolean ogreMoves) {

        super();
        this.ogreMoves = ogreMoves;
        levelIndex = 1;
        map = new TestKeepMap();

        /*Create level's characters*/
        //The hero
        hero = new Hero(1,1);
        hero.setRepresentation('A');
        //The ogre
        ogre = new Ogre(3,3);
    }

    public void checkIfHeroCaptured(){
        int[] heroCoordinates = hero.getCoordinates();
        if(ogre.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
                levelStatus = LevelState.LOST;
    }

    public void checkIfHeroStuns() {
    	int[] heroCoordinates = hero.getCoordinates();

    	if(ogre.isStunned() != 0) { //If the ogre is stun, update the stun counter
    		ogre.updateStun();
    		ogre.setRepresentation('8');
    	}
    	else if(ogre.isNearHero(heroCoordinates[0],heroCoordinates[1])) {
    		ogre.setStun(); //Can't just use updateStun because the ogre may already be stunned
    		ogre.setRepresentation('8');
    	}
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
                heroHasKey = true;
                hero.setRepresentation('K');
            case '.':
                break;
            case 'I':
                if(heroHasKey)
                    map.openDoors(); /* No break statement because while he's opening the door, he's still. No break forces dx=dy=0, like we want */
            default: /* currentChar == 'X' so we can't move through */
                dy = 0;
                dx = 0;
        }

        hero.setCoordinates(heroX+dx, heroY+dy);
        
        if(ogreMoves){
        //Update the villains' position
        //Update the ogre's position
            int ogreX = ogre.getCoordinates()[0];
            int ogreY = ogre.getCoordinates()[1];
            int[] nextMovement;
generateOgreMovement:
            do{
                nextMovement = ogre.getNextMovement();
                currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
                switch(currentChar) {
                    case 'k':
                        ogre.setRepresentation('$');
                        break generateOgreMovement;
                    case '.':
                        ogre.setRepresentation('0');
                        break generateOgreMovement;
                    default:
                        break;
                }
            } while(true); //Risky...

            ogre.setCoordinates(ogreX+nextMovement[0],ogreY+nextMovement[1]);
            //Re-get coordinates so club isn't looking for a new position based on old ones
            ogreX = ogre.getCoordinates()[0];
            ogreY = ogre.getCoordinates()[1];

            //Update the ogre's club's position
generateClubMovement:
            do {
                nextMovement = ogre.getNextClubMovement();
                currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
                switch(currentChar) {
                    case 'k':
                        ogre.putClubOnKey();
                        break generateClubMovement;
                    case '.':
                        ogre.removeClubFromKey();
                        break generateClubMovement;
                    default:
                        break;
                }
            } while(true); //Risky...

            ogre.setClubOffset(nextMovement[0],nextMovement[1]);
        }

        //checkIfHeroStuns();
        checkIfHeroCaptured();
    }

    public char[][] getLevelMatrix() {
        char[][] matrix = map.getCurrentPlan();

        //Draw hero
        int[] heroCoordinates = hero.getCoordinates();
        int heroX = heroCoordinates[0];
        int heroY = heroCoordinates[1];
        matrix[heroY][heroX] = hero.getRepresentation();

        //Draw ogre
            int[] ogreCoordinates = ogre.getCoordinates();
            int[] ogreClubOffsetCoordinates = ogre.getClubOffset();
            int ogreX = ogreCoordinates[0];
            int ogreClubX = ogreX + ogreClubOffsetCoordinates[0];
            int ogreY = ogreCoordinates[1];
            int ogreClubY = ogreY + ogreClubOffsetCoordinates[1];
            matrix[ogreY][ogreX] = ogre.getRepresentation();
            matrix[ogreClubY][ogreClubX] = (ogre.clubIsOnKey() ? '$' : '*');

        return matrix;
    }
    
    public int[] getHeroCoordinates() {
        int[] result = hero.getCoordinates();
        return result;
    }
    
    public int[] getOgreCoordinates(){
    	return ogre.getCoordinates();
    }
    
    public int[] getOgreClubCoordinates(){
    	int[] ogreCoo = getOgreCoordinates();
    	int[] offset = ogre.getClubOffset();
    	ogreCoo[0] += offset[0];
    	ogreCoo[1] += offset[1];
    	return ogreCoo;
    }
    
    public char getHeroRep(){
    	return hero.getRepresentation();
    }

    public Level getNextLevel() {
        return null;
    }
}

