package logic;

/**
 * Guard, the first level's enemy
 *
 */
public abstract class Guard extends Character{
	/**
	 * The Guard's pre-determined set of movements. Depending on the Guard type, they can be done in a reverse order
	 */
	protected int[][] movements = {
	        {-1,0},
	        {0,1},
	        {0,1},
	        {0,1},
	        {0,1},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {0,1},
	        {1,0},
	        {1,0},
	        {1,0},
	        {1,0},
	        {1,0},
	        {1,0},
	        {1,0},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {0,-1}
	    };
	
	/**
	 * Holds the index to the Guard's next movement in his routine
	 */
	protected int movementIndex;
	
	/**
	 * Constructor. Initializes with default representation 'G' and movementIndex at 0
	 * 
	 * @param startX Guard's initial x-axis position
	 * @param startY Guard's initial y-axis position
	 * @see movementIndex
	 */
	public Guard(int startX, int startY) {
		super(startX, startY, 'G');

		movementIndex = 0;
	}
	
	/**
	 * Checks if Guard has caught the Hero.
	 * 
	 * @param heroX Hero's x-axis position
	 * @param heroY Hero's y-axis position
	 * @return true if Guard is not asleep and is near Hero, false otherwise
	 * @see isNearHero
	 */
	public boolean hasCaughtHero(int heroX, int heroY){
		return representation == 'G' && isNearHero(heroX, heroY);
	}
	
	/**
	 * Gets the Guard's movement index to check his next movement. Used mainly for Unit testing
	 * @return Guard's movementIndex
	 * @see movementIndex
	 */
	public int getMovIndex() {
		return movementIndex;
	}
	
	/**
	 * Updates the Guard's position. Different for each type of Guard as each one has a different behavior
	 */
	public abstract void updatePosition();
}
