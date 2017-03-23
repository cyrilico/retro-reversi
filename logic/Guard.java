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

	public boolean hasCaughtHero(int heroX, int heroY){
		return representation == 'G' && isNearHero(heroX, heroY);
	}
	
	public int getMovIndex() {
		return movementIndex;
	}
	
	public abstract void updatePosition(); //Different kinds of update for different kinds of guards
}
