package logic;

public abstract class Guard extends Character{
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
	protected int movementIndex; //To know what is his next movement

	public Guard(int startX, int startY) {
		super(startX, startY, 'G');

		movementIndex = 0;
	}

	public abstract void updateGuardPosition(); //Different update for each kind of guard. Also, assures this class will never be used by itself, like desired

}
