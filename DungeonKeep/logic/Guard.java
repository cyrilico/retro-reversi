package logic;

public abstract class Guard extends Character{
	protected int[][] movements = { //All movements are in the form [y,x]!
	        {0,-1},
	        {1,0},
	        {1,0},
	        {1,0},
	        {1,0},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {0,-1},
	        {1,0},
	        {0,1},
	        {0,1},
	        {0,1},
	        {0,1},
	        {0,1},
	        {0,1},
	        {0,1},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {-1,0},
	        {-1,0}
	    };
	protected int movementIndex; //To know what is his next movement

	public Guard(int startX, int startY) {
		super(startX, startY, 'G');
		
		/* In comment because I don't know if (but I think that) all guard's routine is the same (not counting movement inversions) */
		/* movements = new int[routine.length][2];
		int index = 0;
		for(int[] movement : routine)
			movements[index++] = (int[])movement.clone();
		*/
		
		movementIndex = 0;
	}
	
	public abstract void updateGuardPosition(Map map); //Different update for each kind of guard. Also, assures this class will never be used by itself, like desired

}
