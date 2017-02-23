package logic;

import java.util.Random;

public abstract class Character {
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
		int[] result = {posY, posX};
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
}
