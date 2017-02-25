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
		return representation == 'G' && //Can't catch him if you're asleep (looking at you, Drunken)!
					 (
					 	(posX == heroX && posY == heroY) || //They're on the same cell
						(posX == heroX-1 && posY == heroY) || //The guard is on the cell to the left of the hero
						(posX == heroX+1 && posY == heroY) || //The guard is on the cell to the right of the hero
						(posX == heroX && posY == heroY-1) || //The guard is on the cell above the hero
						(posX == heroX && posY == heroY+1) //The guard is on the cell below the hero
					 );
	}
	
	public abstract void updatePosition(); //Different kinds of update for different kinds of guards
}
