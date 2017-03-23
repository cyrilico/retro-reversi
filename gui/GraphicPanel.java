package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class GraphicPanel extends JPanel {
	protected BufferedImage hero_nokey;
	protected BufferedImage hero_withkey;
	protected BufferedImage guard_awake;
	protected BufferedImage guard_asleep;
	protected BufferedImage ogre_normal;
	protected BufferedImage ogre_stunned;
	protected BufferedImage club;
	protected BufferedImage wall;
	protected BufferedImage floor;
	protected BufferedImage door_closed;
	protected BufferedImage door_open;
	protected BufferedImage key;
	
	private HashMap<Character, BufferedImage> CHAR_TO_IMG = new HashMap<Character, BufferedImage>();
	
	protected static final int IMG_SIZE = 25; //Images are always squared

	/**
	 * Create the panel.
	 */
	public GraphicPanel() {
		try{
			hero_nokey = ImageIO.read(new File("src/img/hero_nkey.png"));
			hero_withkey = ImageIO.read(new File("src/img/hero_wkey.png"));
			guard_awake = ImageIO.read(new File("src/img/guard.png"));
			guard_asleep = ImageIO.read(new File("src/img/guard_asleep.png"));
			ogre_normal = ImageIO.read(new File("src/img/ogre.png"));
			ogre_stunned = ImageIO.read(new File("src/img/ogre_stunned.png"));
			wall = ImageIO.read(new File("src/img/wall.png"));
			floor = ImageIO.read(new File("src/img/floor.png"));
			door_open = ImageIO.read(new File("src/img/door_open.png"));
			door_closed = ImageIO.read(new File("src/img/door_closed.png"));
			key = ImageIO.read(new File("src/img/key.png"));
			club = ImageIO.read(new File("src/img/club.png"));
		}
		catch(IOException e) {
			System.out.println("ERROR: Couldn't read all necessary images"); System.exit(-1);
		}
		loadHashMap();
	}
	
	private void loadHashMap() {
		 CHAR_TO_IMG.put('X', wall);
		 CHAR_TO_IMG.put('.', floor);
		 CHAR_TO_IMG.put('H', hero_nokey);
		 CHAR_TO_IMG.put('A', hero_nokey);
		 CHAR_TO_IMG.put('K', hero_withkey);
		 CHAR_TO_IMG.put('G', guard_awake);
		 CHAR_TO_IMG.put('g', guard_asleep);
		 CHAR_TO_IMG.put('I', door_closed);
		 CHAR_TO_IMG.put('k', key);
		 CHAR_TO_IMG.put('0', ogre_normal);
		 CHAR_TO_IMG.put('8', ogre_stunned);
		 CHAR_TO_IMG.put('*', club);
		 CHAR_TO_IMG.put('$', club);
		 CHAR_TO_IMG.put('S', door_open);
	}
	
	protected BufferedImage getCurrentImage(char currentChar){
		BufferedImage result = CHAR_TO_IMG.get(new Character(currentChar));
		return result;
	}

	
	public static int getImgSize() {
		return IMG_SIZE;
	}

}
