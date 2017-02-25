package logic;

public class Drunken extends Guard{
	protected boolean invertedDirection; //To see if he's following the path in the normal or inverse direction

	public Drunken(int startX, int startY) {
		super(startX, startY);
		invertedDirection = false;
	}

	public void updatePosition(){
		int fallAsleep = generator.nextInt(10);
		if(fallAsleep < 3) //Only a 30% chance of falling (or remaining) asleep. If wanna change in the future, do it here and/or in the line above
			setRepresentation('g');
		else{
			if(getRepresentation() == 'g'){ //Was asleep
				setRepresentation('G');
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
}
