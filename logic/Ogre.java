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

	public boolean hasCaughtHero(int heroX, int heroY) {
		return (clubSameCell(heroX, heroY) || clubToTheLeft(heroX, heroY) || 
				clubToTheRight(heroX, heroY) || clubBelow(heroX, heroY) || clubUp(heroX, heroY));
	}
	
	public boolean clubSameCell(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY;
	}
	
	public boolean clubToTheLeft(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX-1 && clubY == heroY;
	}
	
	public boolean clubToTheRight(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX+1 && clubY == heroY;
	}
	
	public boolean clubBelow(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY-1;
	}
	
	public boolean clubUp(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY+1;
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
