package logic;

public class Suspicious extends Guard{
	protected boolean invertedDirection; //To see if he's following the path in the normal or inverse direction

	public Suspicious(int startX, int startY) {
		super(startX, startY);
		invertedDirection = false;
	}

	public void updatePosition(){
		int invertMovement = generator.nextInt(10);
		if(invertMovement < 4){ //40% chance of inverting movement (had 50% but he turned too much and didn't pose a real threat)
				invertedDirection = !invertedDirection;
				if(invertedDirection){
					if(--movementIndex < 0) //Restart movement pattern
						movementIndex = movements.length-1;
				}
				else{
					if(++movementIndex == movements.length) //Restart movement pattern
						movementIndex = 0;
				}
		}

		int nextGuardMovement[] = movements[movementIndex];
		if(invertedDirection){
			posX -= nextGuardMovement[0];
			posY -= nextGuardMovement[1];
			if(--movementIndex < 0) //Restart movement pattern
				movementIndex = movements.length-1;
		}
		else{
			posX += nextGuardMovement[0];
			posY += nextGuardMovement[1];
			if(++movementIndex == movements.length) //Restart movement pattern
				movementIndex = 0;
		}
	}
}
