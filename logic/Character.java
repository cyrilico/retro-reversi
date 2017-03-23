package logic;

import java.util.Random;

public abstract class Character implements java.io.Serializable{
	protected char representation;
	protected int posX;
	protected int posY;
	protected Random generator;

	public Character(int x, int y, char rep) {
		posX = x;
		posY = y;
		representation = rep;
		generator = new Random();
	}

	public int[] getCoordinates(){
		int[] result = {posX, posY};
		return result;
	}

	public void setCoordinates(int x, int y){
		posX = x;
		posY = y;
	}

	public char getRepresentation(){
		return representation;
	}

	public void setRepresentation(char newRep){
		representation = newRep;
	}
	
	public boolean isNearHero(int heroX, int heroY) {
		return 	 	sameCell(heroX, heroY) || //They're on the same cell
					toTheLeft(heroX, heroY) || //The guard is on the cell to the left of the hero
					toTheRight(heroX, heroY) || //The guard is on the cell to the right of the hero
					below(heroX, heroY) || //The guard is on the cell above the hero
					up(heroX, heroY); //The guard is on the cell below the hero
				 
	}
	
	public boolean sameCell(int heroX, int heroY){
		return posX == heroX && posY == heroY;
	}
	
	public boolean toTheLeft(int heroX, int heroY){
		return posX == heroX-1 && posY == heroY;
	}
	
	public boolean toTheRight(int heroX, int heroY){
		return posX == heroX+1 && posY == heroY;
	}
	
	public boolean below(int heroX, int heroY){
		return posX == heroX && posY == heroY-1;
	}
	
	public boolean up(int heroX, int heroY){
		return posX == heroX && posY == heroY+1;
	}
	
}
