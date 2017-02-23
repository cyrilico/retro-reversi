package logic;

public class Hero extends Character{
	protected boolean hasKey; //Only needed for level1
	
	public Hero(int startX, int startY) {
		super(startX, startY, 'H');
		hasKey = false;
	}
	
	public void setCollectedKey(){
		hasKey = true;
	}
	
	public boolean hasKey(){
		return hasKey;
	}

}
