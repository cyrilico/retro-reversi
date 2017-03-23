package logic;

public class Ogre extends Character {
	protected int clubOffsetX;
	protected int clubOffsetY;
	protected boolean clubOnKey; //To know when to draw the club as * or $ (note that the ogre itself has 'char representation' from Character)
    protected int isStunned; //0 if not stunned, [1,2] if stunned. Resets to 0 when stun is over.

	public Ogre(int startX, int startY) {
		super(startX, startY, '0');
		clubOffsetX = 0;
		clubOffsetY = 0;
		clubOnKey = false;
	}

	public int[] getClubOffset(){
		int[] result = {clubOffsetX, clubOffsetY};
		return result;
	}

	public void setClubOffset(int newOffsetX, int newOffsetY){
		clubOffsetX = newOffsetX;
		clubOffsetY = newOffsetY;
	}

	public void putClubOnKey(){
		clubOnKey = true;
	}

	public void removeClubFromKey(){
		clubOnKey = false;
	}

	public boolean clubIsOnKey(){
		return clubOnKey;
	}

    public boolean isNearHero(int heroX, int heroY) {
		boolean ogreNearHero = (posX == heroX && posY == heroY) || //They're on the same cell
		 											 (posX == heroX-1 && posY == heroY) || //The ogre is on the cell to the left of the hero
		 											 (posX == heroX+1 && posY == heroY) || //The ogre is on the cell to the right of the hero
		 										 	 (posX == heroX && posY == heroY-1) || //The ogre is on the cell above the hero
		 										 	 (posX == heroX && posY == heroY+1); //The ogre is on the cell below the hero
        return ogreNearHero;
    }

	public boolean hasCaughtHero(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		boolean clubNearHero = (clubX == heroX && clubY == heroY) || //They're on the same cell
		 											 (clubX == heroX-1 && clubY == heroY) || //The club is on the cell to the left of the hero
		 											 (clubX == heroX+1 && clubY == heroY) || //The club is on the cell to the right of the hero
		 										 	 (clubX == heroX && clubY == heroY-1) || //The club is on the cell above the hero
		 										 	 (clubX == heroX && clubY == heroY+1); //The club is on the cell below the hero

		return clubNearHero;
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

        int[] result = {randomX, randomY};
        return result;
    }

    public int isStunned() {
        return isStunned;
    }

    public void setStun() {
        isStunned = 1;
    }

    public void updateStun() {
        isStunned = (isStunned+1) % 3;
    }

    public int[] getNextMovement() { 
        int[] empty = new int[2];
        if(isStunned != 0) //If stunned, ogre doesn't move
            return empty; 

        //Generate random integers between -1 and 1;
        return randomMovement();
    }

    public int[] getNextClubMovement() { 
        //Generate random integers between -1 and 1;
        return randomMovement();
    }
}
