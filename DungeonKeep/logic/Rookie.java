package logic;

public class Rookie extends Guard{

	public Rookie(int startX, int startY) {
		super(startX, startY);
	}

	public void updatePosition(){
		   	int nextGuardMovement[] = movements[movementIndex];
				posX += nextGuardMovement[0];
				posY += nextGuardMovement[1];

        if(++movementIndex == movements.length) //Restart movement pattern
            movementIndex = 0;
	}
}
