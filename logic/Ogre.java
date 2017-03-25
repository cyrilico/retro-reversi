package logic;

/**
 * Ogre, the second game level's enemy
 *
 */
public class Ogre extends Character {
	/**
	 * His club's x-axis offset relative to his position
	 */
	protected int clubOffsetX;
	/**
	 * His club's y-axis offset relative to his position
	 */
	protected int clubOffsetY;
	/**
	 * To know if his club on the key's position (used to control its char representation)
	 */
	protected boolean clubOnKey;
    private int isStunned; //0 if not stunned, [1,2] if stunned. Resets to 0 when stun is over.
    /**
     * Constructor. Puts Ogre in his initial position with his club retracted (both offsets set to 0)
     * @param startX Ogre's initial x-axis position
     * @param startY Ogre's initial y-axis position
     */
	public Ogre(int startX, int startY) {
		super(startX, startY, '0');
		clubOffsetX = 0;
		clubOffsetY = 0;
		clubOnKey = false;
	}
	/**
	 * Gets the club's current offset relative to the Ogre's current position
	 * @return int array containing both offset values
	 * @see clubOffsetX
	 * @see clubOffsetY
	 */
	public int[] getClubOffset(){
		int[] result = {clubOffsetX, clubOffsetY};
		return result;
	}
	/**
	 * Sets the clube's offset to specific values (assumes values are valid, as in an adjacent position to the Ogre)
	 * @param newOffsetX Club's new x-axis offset
	 * @param newOffsetY Club's new y-axis offset
	 */
	public void setClubOffset(int newOffsetX, int newOffsetY){
		clubOffsetX = newOffsetX;
		clubOffsetY = newOffsetY;
	}
	/**
	 * Raises the clubOnKey flag
	 * @see clubOnKey
	 */
	public void putClubOnKey(){
		clubOnKey = true;
	}
	/**
	 * Sets the clubOnKey flag back to false
	 * @see clubOnKey
	 */
	public void removeClubFromKey(){
		clubOnKey = false;
	}
	/**
	 * Checks if club is currently on the key's position through the clubOnKey flag
	 * @return true if clubOnKey is true, false otherwise
	 * @see clubOnKey
	 */
	public boolean clubIsOnKey(){
		return clubOnKey;
	}
	/**
	 * Checks if the Ogre's club has caught the Hero.
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is near Hero, false otherwise
	 * @see clubSameCell
	 * @see clubToTheLeft
	 * @see clubToTheRight
	 * @see clubUp
	 * @see clubBelow
	 */
	public boolean hasCaughtHero(int heroX, int heroY) {
		return (clubSameCell(heroX, heroY) || clubToTheLeft(heroX, heroY) || 
				clubToTheRight(heroX, heroY) || clubBelow(heroX, heroY) || clubUp(heroX, heroY));
	}
	/**
	 * Checks if the Ogre's club is in the same cell as the Hero
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is in the same cell, false otherwise
	 */
	public boolean clubSameCell(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY;
	}
	/**
	 * Checks if the Ogre's club is in the cell to the Hero's left
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is in cell to the left, false otherwise
	 */
	public boolean clubToTheLeft(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX-1 && clubY == heroY;
	}
	/**
	 * Checks if the Ogre's club is in the cell to the Hero's right
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is in cell to the right, false otherwise
	 */
	public boolean clubToTheRight(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX+1 && clubY == heroY;
	}
	/**
	 * Checks if the Ogre's club is in the cell below the Hero
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is in the cell below, false otherwise
	 */
	public boolean clubBelow(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY-1;
	}
	/**
	 * Checks if the Ogre's club is in the cell above the Hero
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Ogre's club is in the cell above, false otherwise
	 */
	public boolean clubUp(int heroX, int heroY) {
		int clubX = posX+clubOffsetX;
		int clubY = posY+clubOffsetY;
		return clubX == heroX && clubY == heroY+1;
	}

	private int[] randomMovement() {
        int movementType = generator.nextInt(2), randomY, randomX;
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
        int[] result = {randomX, randomY}; return result;
    }
	/**
	 * Checks if the Ogre is currently stunned
	 * @return Number of turns left in which Ogre will still have the current stun (can be 0, 1 or 2)
	 */
    public int isStunned() {
        return isStunned;
    }
    /**
     * Stuns the Ogre
     */
    public void setStun() {
        isStunned = 1;
    }
    /**
     * Updates the current stun status (number of turns left gets smaller by a unit)
     */
    public void updateStun() {
        isStunned = (isStunned+1) % 3;
    }
    /**
     * Gets (a possible) next random movement for the Ogre if he's not stunned or a void one if he's stunned
     * @return int array containing his possible next movement ([0,0] if currently stunned)
     */
    public int[] getNextMovement() { 
        int[] empty = new int[2];
        if(isStunned != 0) //If stunned, ogre doesn't move
            return empty; 

        return randomMovement();
    }
    /**
     * Gets (a possible) next random club swing
     * @return int array containing the next possible club offset in both directions
     */
    public int[] getNextClubMovement() { 
        return randomMovement();
    }
}
