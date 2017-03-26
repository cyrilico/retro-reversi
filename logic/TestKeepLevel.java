package logic;

/**
 *  Imitation of the second game level. Used for Unit testing purposes only.
 */

public class TestKeepLevel extends Level {
	
	/**
	 *  The level's enemy
	 */
    Ogre ogre;
    /**
     * Flag that controls if the ogre moves or not
     */
    boolean ogreMoves;
    /**
     * Constructor. Initializes all fields
     * @param ogreMoves Initial ogre movement flag state
     * @see ogreMoves
     */
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
    /**
     * Checks if an Ogre is in position to catch the hero. Changes level state accordingly if so
     */
    public void checkIfHeroCaptured(){
        int[] heroCoordinates = hero.getCoordinates();
        if(ogre.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
                levelStatus = LevelState.LOST;
    }
    /**
	 * Checks if the Hero is an adjacent position to an Ogre and stuns it if so
	 */
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
    /**
     * Updates the Hero and Ogre's (if flag is set) positions based on user input and/or their respective behavior
     * 
     * @param input user input containing desired Hero's next movement
     * @see updateHero
     */
    public void updatePositions(int[] input) {
    	char currentChar = map.elementAt(hero.getCoordinates()[0]+input[0], hero.getCoordinates()[1]+input[1]);
    	updateHero(input[0],input[1],currentChar);
    	
        if(ogreMoves){
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

        checkIfHeroCaptured();
    }
    /**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
	public char[][] getLevelMatrix() {
		char[][] matrix = map.getCurrentPlan();
		drawHero(matrix);
		drawOgre(matrix, false);
		return matrix;
	}

	private void drawHero(char[][] mapClone){
		int[] heroCoordinates = hero.getCoordinates();
		int heroX = heroCoordinates[0];
		int heroY = heroCoordinates[1];
		mapClone[heroY][heroX] = hero.getRepresentation();
	}

	private void drawOgre(char[][] mapClone, boolean gui){
		int[] ogreCoordinates = ogre.getCoordinates();
		int[] ogreClubOffsetCoordinates = ogre.getClubOffset();
		int ogreX = ogreCoordinates[0];
		int ogreClubX = ogreX + ogreClubOffsetCoordinates[0];
		int ogreY = ogreCoordinates[1];
		int ogreClubY = ogreY + ogreClubOffsetCoordinates[1];
		mapClone[ogreY][ogreX] = ogre.getRepresentation();
		mapClone[ogreClubY][ogreClubX] = (ogre.clubIsOnKey() ? '$' : '*');
	}
    
	/**
     * Gets the Hero's current position
     * @return int array containing Hero's coordinates
     */
    public int[] getHeroCoordinates() {
        int[] result = hero.getCoordinates();
        return result;
    }
    /**
     * Gets the Ogre's current position
     * @return int array containing Ogre's coordinates
     */
    public int[] getOgreCoordinates(){
    	return ogre.getCoordinates();
    }
    /**
     * Gets the Ogre's club's current position (not the offset)
     * @return int array containing Ogre's club's coordinates
     */
    public int[] getOgreClubCoordinates(){
    	int[] ogreCoo = getOgreCoordinates();
    	int[] offset = ogre.getClubOffset();
    	ogreCoo[0] += offset[0];
    	ogreCoo[1] += offset[1];
    	return ogreCoo;
    }
    /**
     * Gets the level's ogre
     * @return Current session's ogre
     */
    public Ogre getOgre() {
    	return ogre;
    }
    /**
     * Gets the Hero's current representation
     * @return char containing Hero's representation
     */
    public char getHeroRep(){
    	return hero.getRepresentation();
    }
    /**
     * Gets the next level.
     * @return null since there are no levels after this one
     */
    public Level getNextLevel() {
        return null;
    }

    /**
     * Moves the hero to his next desired position if said movement is valid
     * @param x Desired offset to current x-axis position
     * @param y Desired offset to current y-axis position
     * @param currentChar Current element at next desired position
     * @see updatePositions
     */
	public void updateHero(int x, int y, char currentChar) {
        int dx = x;
        int dy = y;

        int heroX = hero.getCoordinates()[0];
        int heroY = hero.getCoordinates()[1];


        switch(currentChar) {
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
            default:
                dy = 0;
                dx = 0;
        }

        hero.setCoordinates(heroX+dx, heroY+dy);
		
	}
	/**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
	public char[][] getLevelMatrixGUI() {
		char[][] matrix = map.getCurrentPlan();
		drawHero(matrix);
		drawOgre(matrix, true);
		return matrix;
	}
}

