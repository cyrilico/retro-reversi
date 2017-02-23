package logic;

public class Rookie extends Guard{
	
	public Rookie(int startX, int startY) {
		super(startX, startY);
	}
	
	public void updateGuardPosition(Map map){
        int nextGuardMovement[] = movements[movementIndex];
        
        map.setElementAt(posX, posY, '.');
        posY += nextGuardMovement[0];
        posX += nextGuardMovement[1];
        map.setElementAt(posX, posY, 'G');
        
        if(++movementIndex == movements.length) //Restart movement pattern
            movementIndex = 0;
	}
}
