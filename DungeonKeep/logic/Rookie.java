package logic;

public class Rookie extends Guard{

	public Rookie(int startX, int startY) {
		super(startX, startY);
	}

	public void updateGuardPosition(){
        int nextGuardMovement[] = movements[movementIndex];

        posY += nextGuardMovement[0];
        posX += nextGuardMovement[1];
                
        if(++movementIndex == movements.length) //Restart movement pattern
            movementIndex = 0;
	}
}
