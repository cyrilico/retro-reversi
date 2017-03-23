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
	
	private boolean isNearHero(int heroX, int heroY){
		return 	 	sameCell(heroX, heroY) || //They're on the same cell
					toTheLeft(heroX, heroY) || //The guard is on the cell to the left of the hero
					toTheRight(heroX, heroY) || //The guard is on the cell to the right of the hero
					below(heroX, heroY) || //The guard is on the cell above the hero
					up(heroX, heroY); //The guard is on the cell below the hero
				 
	}
	
	private boolean sameCell(int heroX, int heroY){
		return posX == heroX && posY == heroY;
	}
	
	private boolean toTheLeft(int heroX, int heroY){
		return posX == heroX-1 && posY == heroY;
	}
	
	private boolean toTheRight(int heroX, int heroY){
		return posX == heroX+1 && posY == heroY;
	}
	
	private boolean below(int heroX, int heroY){
		return posX == heroX && posY == heroY-1;
	}
	
	private boolean up(int heroX, int heroY){
		return posX == heroX && posY == heroY+1;
	}
	
	public int getMovIndex(){
		return movementIndex;
	}
	
	public abstract void updatePosition(); //Different kinds of update for different kinds of guards
}
