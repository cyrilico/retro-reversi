package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	
	protected static final int IMG_SIZE = 25; //Images are always squared

	/**
	 * Create the panel.
	 */
	public GraphicPanel() {
		try{
			//Assumes program is being executed in an Eclipse project which has a src folder (reasonable?)
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
			System.out.println("ERROR: Couldn't read all necessary images");
			System.exit(-1);
		}
	}
	
	protected BufferedImage getCurrentImage(char currentChar){
		BufferedImage result = null;
		switch(currentChar){
		case 'X':
			result = wall;
			break;
		case '.':
			result = floor;
			break;
		case 'H':
		case 'A':
			result = hero_nokey;
			break;
		case 'K':
			result = hero_withkey;
			break;
		case 'G':
			result = guard_awake;
			break;
		case 'g':
			result = guard_asleep;
			break;
		case 'I':
			result = door_closed;
			break;
		case 'k':
			result = key;
			break;
		case '0':
			result = ogre_normal;
			break;
		case '8':
			result = ogre_stunned;
			break;
		case '*':
		case '$':
			result = club;
			break;
		case 'S':
			result = door_open;
			break;
		}
		return result;
	}
	
	
	public static int getImgSize() {
		return IMG_SIZE;
	}

}
