package logic;

/**
 * A type of Guard that can randomly fall asleep and invert the pre-determined path's direction when he wakes up
 *
 */
public class Drunken extends Guard{
	/**
	 * If set to true, then the Drunken guard is moving in the opposite direction to the default one
	 */
	protected boolean invertedDirection;
	private boolean sleep;
	
	/**
	 * Constructor. Initializes all fields and puts Drunken guard at it's starting position
	 * 
	 * @param startX Drunken guard's initial x-axis position
	 * @param startY Drunken guard's initial y-axis position
	 */
	public Drunken(int startX, int startY) {
		super(startX, startY);
		invertedDirection = false;
		sleep = false;
	}
	
	/**
	 * Updates the Drunken guard's position based on his movement behavior.
	 * - First, it generates a random number and depending on its value he can go (or stay) sleeping
	 * - If he's not sleeping and just woke up, another number is generated to see if he will invert his movement's direction
	 * - If neither of the previous apply, he just moves according to his last known pattern
	 */
	public void updatePosition(){
		int fallAsleep = generator.nextInt(10);
		if(fallAsleep < 3) { //Only a 30% chance of falling (or remaining) asleep. If wanna change in the future, do it here and/or in the line above
			setRepresentation('g');
			sleep = true;
		}
		else{
			if(sleep){ //Was asleep
				sleep = false;
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
	
	/**
	 * Checks if Drunken guard is currently sleeping
	 * 
	 * @return true if he indeed isn't moving, false otherwise
	 */
	public boolean isSleep() {
		return sleep;
	}
}
