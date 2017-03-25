package logic;

/**
 * A type of Guard that randomly changes his movement's direction because he can't even trust his shadow
 *
 */
public class Suspicious extends Guard{
	private static int chanceOfInverting = 4;
	/**
	 * If set to true, then the Suspicious guard is moving in the opposite direction to the default one
	 */
	protected boolean invertedDirection;
	/**
	 * Constructor. Puts the Suspicious guard in his initial position
	 * @param startX Suspicious' initial x-axis position
	 * @param startY Suspicious' initial y-axis position
	 */
	public Suspicious(int startX, int startY) {
		super(startX, startY);
		invertedDirection = false;
	}
	/**
	 * Updates the Suspicious guard's position based on his movement behavior.
	 * - First, it generates a random number to see if he will invert his movement's direction (seting the flag accordingly)
	 * - After, he just moves according to the latest calculated pattern
	 * @see invertedDirection
	 */
	public void updatePosition(){
		int invertMovement = generator.nextInt(10);
		if(invertMovement < chanceOfInverting){ //40% chance of inverting movement (had 50% but he turned too much and didn't pose a real threat)
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
