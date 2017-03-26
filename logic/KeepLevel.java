package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * The second game level
 *
 */
public class KeepLevel extends Level {

	/**
     *  Used to randomly choose the number of ogres the level if user didn't specify 
     */
	Random ogreGenerator;
	/**
	 *  The level's enemies
	 */
	ArrayList<Ogre> ogres;
	/**
	 * Constructor where user specifies how many ogres there will be. All will start at the same position
	 * @param nOgres Number of ogres to be present
	 */
	public KeepLevel(int nOgres) {
		super();

		levelIndex = 1;
		map = new KeepMap();

		/*Create level's characters*/
		//The hero
		hero = new Hero(1,7);
		hero.setRepresentation('A');
		//The ogres
		ogreGenerator = new Random();

		int numberOfOgres = nOgres;

		ogres = new ArrayList<Ogre>();
		for(int i = 0; i < numberOfOgres; i++)
			ogres.add(new Ogre(4,1));   	
	}
	/**
	 * Constructor that specifies all elements for the current level. Used when the KeepLevel comes from a user's creation in the map editor
	 * @param ogres Ogres for the map (do not have to be all in the same starting position)
	 * @param hero Hero for the level
	 * @param map Level Map directly from map editor
	 */
	public KeepLevel(ArrayList<Ogre> ogres, Hero hero, Map map) {
		levelIndex = 1;
		this.map = map;
		this.ogres = ogres;
		this.hero = hero;
	}
	/**
	 * Default constructor. Initializes the default level, with the hero at the default position and a random number of ogres (within a certain limit)
	 */
	public KeepLevel() {
		super();

		levelIndex = 1;
		map = new KeepMap();

		/*Create level's characters*/
		//The hero
		hero = new Hero(1,7);
		hero.setRepresentation('A');
		//The ogres
		ogreGenerator = new Random();

		int numberOfOgres = ogreGenerator.nextInt(2)+1; //1 or 2 ogres, starting in the same position (tried with 3 and 4, nearly impossible to escape)

		ogres = new ArrayList<Ogre>();
		for(int i = 0; i < numberOfOgres; i++)
			ogres.add(new Ogre(4,1));
	}
	/**
     * Checks if an Ogre is in position to catch the hero. Changes level state accordingly if so
     */
	public void checkIfHeroCaptured(){
		int[] heroCoordinates = hero.getCoordinates();
		for(Ogre ogre : ogres) {
			if(ogre.hasCaughtHero(heroCoordinates[0],heroCoordinates[1]))
				levelStatus = LevelState.LOST;
		}
	}
	/**
	 * Checks if the Hero is an adjacent position to an Ogre and stuns it if so
	 */
	public void checkIfHeroStuns() {
		int[] heroCoordinates = hero.getCoordinates();

		for(Ogre ogre : ogres) {
			if(ogre.isStunned() != 0) { //If the ogre is stun, update the stun counter
				ogre.updateStun();
				ogre.setRepresentation('8');
			}
			else if(ogre.isNearHero(heroCoordinates[0],heroCoordinates[1])) {
				ogre.setStun(); //Can't just use updateStun because the ogre may already be stunned
				ogre.setRepresentation('8');
			}
		}
	}
	/**
     * Updates the Hero and Ogres' positions based on user input and/or their respective behavior
     * 
     * @param input user input containing desired Hero's next movement
     * @see updateHero
     */
	public void updatePositions(int[] input) {
		//Update the hero's position
		int dx = input[0];
		int dy = input[1];

		int heroX = hero.getCoordinates()[0];
		int heroY = hero.getCoordinates()[1];

		char currentChar = map.elementAt(heroX+dx, heroY+dy);

		updateHero(dx, dy, currentChar);

		//Update the villains' position
		for(Ogre ogre : ogres) {
			//Update the ogre's position
			int ogreX = ogre.getCoordinates()[0];
			int ogreY = ogre.getCoordinates()[1];
			int[] nextMovement;
			generateOgreMovement:
				do{
					nextMovement = ogre.getNextMovement();
					currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
					switch(currentChar) {
					case 'k':
						ogre.setRepresentation('$');
						break generateOgreMovement;
					case '.':
						ogre.setRepresentation('0');
						break generateOgreMovement;
					default:
						break;
					}
				} while(true); //Risky...

			ogre.setCoordinates(ogreX+nextMovement[0],ogreY+nextMovement[1]);
			//Re-get coordinates so club isn't looking for a new position based on old ones
			ogreX = ogre.getCoordinates()[0];
			ogreY = ogre.getCoordinates()[1];

			//Update the ogre's club's position
			generateClubMovement:
				do {
					nextMovement = ogre.getNextClubMovement();
					currentChar = map.elementAt(ogreX+nextMovement[0],ogreY+nextMovement[1]);
					switch(currentChar) {
					case 'k':
						ogre.putClubOnKey();
						break generateClubMovement;
					case '.':
						ogre.removeClubFromKey();
						break generateClubMovement;
					default:
						break;
					}
				} while(true); //Risky...

			ogre.setClubOffset(nextMovement[0],nextMovement[1]);
		}

		checkIfHeroStuns();
		checkIfHeroCaptured();
	}
	/**
     * Moves the hero to his next desired position if said movement is valid
     * @param dx Desired offset to current x-axis position
     * @param dy Desired offset to current y-axis position
     * @param currentChar Current element at next desired position
     * @see updatePositions
     */
	public void updateHero(int dx, int dy, char currentChar) {
		int heroX = hero.getCoordinates()[0];
		int heroY = hero.getCoordinates()[1];
		
		switch(currentChar) {
		case 'S':
			levelStatus = LevelState.WON;
			return; /* Avoids checking for enemies on negative indexes */
		case 'k':
			heroHasKey = true;
			hero.setRepresentation('K');
		case '.':
			break;
		case 'I':
			if(heroHasKey)
				map.openDoors(); /* No break statement because while he's opening the door, he's still. No break forces dx=dy=0, like we want */
		default:
			dy = 0;
			dx = 0;
		}

		hero.setCoordinates(heroX+dx, heroY+dy);	
	}
	/**
	 * Gets the current session's Ogres. Used for Unit testing purposes only
	 * @return level's Ogre ArrayList
	 */
	public ArrayList<Ogre> getOgres() {
		@SuppressWarnings("unchecked")
		ArrayList<Ogre> clone = (ArrayList<Ogre>)ogres.clone();
		return clone;
	}
	/**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
	public char[][] getLevelMatrix() {
		char[][] matrix = map.getCurrentPlan();
		drawHero(matrix);
		drawOgres(matrix, false);
		return matrix;
	}
	/**
	 * Draws the hero in the map
	 * @param mapClone Map where the hero will be drawn at
	 */
	private void drawHero(char[][] mapClone){
		int[] heroCoordinates = hero.getCoordinates();
		int heroX = heroCoordinates[0];
		int heroY = heroCoordinates[1];
		mapClone[heroY][heroX] = hero.getRepresentation();
	}
	/**
	 * Draws the ogres in the map
	 * @param mapClone Map where the ogres will be drawn at
	 * @param gui If set to true, whenever the representation is $ sets it back to 0
	 */
	private void drawOgres(char[][] mapClone, boolean gui){
		for(Ogre ogre : ogres){
			int[] ogreCoordinates = ogre.getCoordinates();
			int[] ogreClubOffsetCoordinates = ogre.getClubOffset();
			int ogreX = ogreCoordinates[0];
			int ogreClubX = ogreX + ogreClubOffsetCoordinates[0];
			int ogreY = ogreCoordinates[1];
			int ogreClubY = ogreY + ogreClubOffsetCoordinates[1];
			mapClone[ogreY][ogreX] = (gui && ogre.getRepresentation() == '$') ? '0' : ogre.getRepresentation();
			mapClone[ogreClubY][ogreClubX] = (gui || (!gui && !ogre.clubIsOnKey())) ? '*' : '$';
		}
	}
	/**
     * Gets the level following the current one
     * @return null, since this is the last level
     */
	public Level getNextLevel() {
		return null;
	}
	/**
     *  Returns a copy of the game map with all the characters placed
     *  @return char matrix with current game situation
     */
	public char[][] getLevelMatrixGUI() {
		char[][] matrix = map.getCurrentPlan();
		drawHero(matrix);
		drawOgres(matrix, true);
		return matrix;
	}
}
