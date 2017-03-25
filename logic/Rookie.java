package logic;

/**
 * A type of Guard that always follows the movement pattern with no exceptions
 *
 */
public class Rookie extends Guard{
	/**
	 * Constructor. Puts Rookie guard at his starting position
	 * @param startX Rookie's initial x-axis position
	 * @param startY Rookie's initial y-axis position
	 */
	public Rookie(int startX, int startY) {
		super(startX, startY);
	}
	/**
	 * Updates the Rookie's movement based on his behavior. In this case, it simply checks what's the next predicted movement on the pattern and executes it
	 */
	public void updatePosition(){
		   	int nextGuardMovement[] = movements[movementIndex];
				posX += nextGuardMovement[0];
				posY += nextGuardMovement[1];

        if(++movementIndex == movements.length) //Restart movement pattern
            movementIndex = 0;
	}
}
