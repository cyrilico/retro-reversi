package logic;

import java.util.Random;

/**
 * 
 * The Character class, superclass to every dynamic element in the game
 *
 */
public abstract class Character implements java.io.Serializable{
	/**
	 * The element's representation in-game
	 */
	protected char representation;
	/**
	 * The element's x-axis position's value
	 */
	protected int posX;
	/**
	 * The element's y-axis position's value
	 */
	protected int posY;
	/**
	 * A random number generator to be used by enemies whose movement is not certain
	 */
	protected Random generator;
	
	/**
	 * Constructor for the class. Initializes every field
	 * 
	 * @param x Character's initial x-axis position
	 * @param y Character's initial y-axis position
	 * @param rep Character's representation (may or not be changed later on)
	 */
	public Character(int x, int y, char rep) {
		posX = x;
		posY = y;
		representation = rep;
		generator = new Random();
	}
	
	/**
	 * Gets a Character's coordinates
	 * 
	 * @return int array containing the Character's position in the form [x-axis,y-axis]
	 */
	public int[] getCoordinates(){
		int[] result = {posX, posY};
		return result;
	}
	
	/**
	 * Places a Character in specific coordinates
	 * 
	 * @param x Character's new x-axis position's value
	 * @param y Character's new y-axis position's value
	 */
	public void setCoordinates(int x, int y){
		posX = x;
		posY = y;
	}
	
	/**
	 * Gets the Character's current representation
	 * 
	 * @return char containing Character's representation
	 */
	public char getRepresentation(){
		return representation;
	}
	
	/**
	 * Changes a Character's representation
	 * 
	 * @param newRep char containing the Character's new representation
	 */
	public void setRepresentation(char newRep){
		representation = newRep;
	}
	
	/**
	 * Checks if the Character (an enemy) is in an adjacent position to the Hero
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the same or a near cell (diagonals don't count), false otherwise
	 */
	public boolean isNearHero(int heroX, int heroY) {
		return 	 	sameCell(heroX, heroY) ||
					toTheLeft(heroX, heroY) ||
					toTheRight(heroX, heroY) ||
					below(heroX, heroY) ||
					up(heroX, heroY);
				 
	}
	
	/**
	 * Checks if the Character (an enemy) is in the same cell as the Hero
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the same cell, false otherwise
	 */
	public boolean sameCell(int heroX, int heroY){
		return posX == heroX && posY == heroY;
	}
	
	/**
	 * Checks if the Character (an enemy) is in the cell to the Hero's left
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the cell to the left, false otherwise
	 */
	public boolean toTheLeft(int heroX, int heroY){
		return posX == heroX-1 && posY == heroY;
	}
	
	/**
	 * Checks if the Character (an enemy) is in the cell to the Hero's right
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the cell to the right, false otherwise
	 */
	public boolean toTheRight(int heroX, int heroY){
		return posX == heroX+1 && posY == heroY;
	}
	
	/**
	 * Checks if the Character (an enemy) is in the cell below the Hero
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the cell below, false otherwise
	 */
	public boolean below(int heroX, int heroY){
		return posX == heroX && posY == heroY-1;
	}
	
	/**
	 * Checks if the Character (an enemy) is in the cell above the Hero
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if is in the cell above, false otherwise
	 */
	public boolean up(int heroX, int heroY){
		return posX == heroX && posY == heroY+1;
	}
	
}
