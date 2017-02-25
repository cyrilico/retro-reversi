package logic;

public class Ogre extends Character {
	protected int clubOffsetX;
	protected int clubOffsetY;
	
	public Ogre(int startX, int startY) {
		super(startX, startY, '0');
		clubOffsetX = 0;
		clubOffsetY = 0;
	}
	
	private int[] randomMovement() {
        int movementType = generator.nextInt(2);
        int randomY;
        int randomX;
        if(movementType == 0){ //Horizontal movement
          randomY = 0;
          do {
            randomX = generator.nextInt(3) - 1;
          } while(randomX == 0); //Just to make sure he does indeed move every time
        }
        else { //movementType == 1 ; Vertical movement
          do {
            randomY = generator.nextInt(3) - 1;
          } while(randomY == 0); //Just to make sure he does indeed move every time
          randomX = 0;
        }

        int[] result = {randomY, randomX};
        return result;
    }

    public int[] nextPosition() {
        int[] ogreCoordinates = getCoordinates();
        int[] nextMovement = randomMovement();    

        nextMovement[0] += ogreCoordinates[0];
        nextMovement[1] += ogreCoordinates[1];

        return nextMovement;
    }
	
    /*
    public void updatePosition(Map map) {
        // First, calculate the ogre's new position (but not updating it yet) 
        boolean isLeavingKey = false;
        int[] ogreCoordinates = getCoordinates();
        if(map.elementAt(ogreCoordinates[1], ogreCoordinates[0]) == '$')
            isLeavingKey = true;

        map.setElementAt(ogreCoordinates[1], ogreCoordinates[0], (isLeavingKey ? 'k' : '.'));

        //Generate random integers between -1 and 1;
        int[] nextRandomMovement;
        do {
            nextRandomMovement = randomMovement();    
        } while(map.elementAt(ogreCoordinates[1]+nextRandomMovement[1], ogreCoordinates[0]+nextRandomMovement[0]) == 'X' ||
                map.elementAt(ogreCoordinates[1]+nextRandomMovement[1], ogreCoordinates[0]+nextRandomMovement[0]) == 'I' ||
                map.elementAt(ogreCoordinates[1]+nextRandomMovement[1], ogreCoordinates[0]+nextRandomMovement[0]) == 'S');
        // About these last two: can't have him go to the door, that's our only way out! 

        // Now, swing his club of doom! 
        isLeavingKey = false;
        int clubY = posY + clubOffsetY;
        int clubX = posX + clubOffsetX;
        if(map.elementAt(clubX, clubY) == '$')
            isLeavingKey = true;

        map.setElementAt(clubX, clubY, (isLeavingKey ? 'k' : '.'));

        //Now that we've removed the club from its old position, update the ogre's position and swing it to a new one based on the updated coordinates
        posY += nextRandomMovement[0];
        posX += nextRandomMovement[1];

        if(map.elementAt(posX, posY) == 'k') //Checking if ogre's new position is on top of key
            map.setElementAt(7, 1, '$'); //key on level1 is at (x,y) = (7,1)
        else
            map.setElementAt(posX,posY,'0'); //Default case

        //Generate random integers between -1 and 1;
        int[] nextRandomSwing;
        do {
            nextRandomSwing = randomMovement();
        } while(map.elementAt(posX+nextRandomSwing[1], posY+nextRandomSwing[0]) == 'X' ||
                map.elementAt(posX+nextRandomSwing[1], posY+nextRandomSwing[0]) == 'I' ||
                map.elementAt(posX+nextRandomSwing[1], posY+nextRandomSwing[0]) == 'S'); //Could let him swing at the door and open it by force... Maybe later

        clubOffsetY = nextRandomSwing[0];
        clubOffsetX = nextRandomSwing[1];
        if(map.elementAt(posX+clubOffsetX, posY+clubOffsetY) == 'k') //Checking if club's new position is on top of key
            map.setElementAt(7, 1, '$');
        else
            map.setElementAt(posX+clubOffsetX, posY+clubOffsetY, '*'); //Default case
    }
    */
}
