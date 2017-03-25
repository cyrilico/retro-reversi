package logic;

/**
 * The game's main and playable character
 *
 */
public class Hero extends Character{
	/**
	 * Constructor. Sets the Hero's starting point
	 * @param startX Hero's initial x-axis position
	 * @param startY Hero's initial y-axis position
	 */
	public Hero(int startX, int startY) {
		super(startX, startY, 'H');
	}

}
