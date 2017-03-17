package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener{
	private BufferedImage hero_nokey;
	private BufferedImage hero_withkey;
	private BufferedImage guard_awake;
	private BufferedImage guard_asleep;
	private BufferedImage ogre_normal;
	private BufferedImage ogre_stunned;
	private BufferedImage club;
	private BufferedImage wall;
	private BufferedImage floor;
	private BufferedImage door_closed;
	private BufferedImage door_open;
	private BufferedImage key;
	
	private WindowKeep window;
	
	public GamePanel(WindowKeep window){
		this.window = window;
		addKeyListener(this);
		try{
			//Assumes program is being executed in an Eclipse project which has a src folder (reasonable?)
			hero_nokey = ImageIO.read(new File("src/img/hero_nkey.png"));
			hero_withkey = ImageIO.read(new File("src/img/hero_wkey.png"));
			guard_awake = ImageIO.read(new File("src/img/guard.png"));
			guard_asleep = ImageIO.read(new File("src/img/guard_asleep.png"));
			ogre_normal = ImageIO.read(new File("src/img/ogre.png"));
			wall = ImageIO.read(new File("src/img/wall.png"));
			floor = ImageIO.read(new File("src/img/floor.png"));
			door_open = ImageIO.read(new File("src/img/door_open.png"));
			door_closed = ImageIO.read(new File("src/img/door_closed.png"));
			key = ImageIO.read(new File("src/img/key.png"));
			club = ImageIO.read(new File("src/img/club.png"));
		}
		catch(IOException e){
			System.out.println("ERROR: Couldn't read all necessary images");
			System.exit(-1);
		}
	}
	
	private BufferedImage getCurrentImage(char currentChar){
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
	
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);

		for(int y = 0; y < 12; y++) {
			int x;
			for(x = 0; x < 15; x++)
				g.drawImage(floor, x*25, y*25, 25, 25, null);
			x = 0;
		}
		
		if(window.game == null) return;
		String currentMap = window.game.getCurrentMatrix();

		int k = 0, j = 0;

		for(int i = 0; i < currentMap.length(); i++) {
			if(currentMap.charAt(i) != '\n')
				g.drawImage(getCurrentImage(currentMap.charAt(i)), 25*(k++), 25*j, 25, 25, null);
			else {
				k=0;
				j++;
			}
		}
	}
	
	public void keyPressed(KeyEvent e) { 
		if(window.game == null) return;
		switch(e.getKeyCode()){ 
			case KeyEvent.VK_UP: window.game.updateGame('w'); repaint(); break; 
			case KeyEvent.VK_DOWN: window.game.updateGame('s'); repaint(); break;  
			case KeyEvent.VK_LEFT: window.game.updateGame('a'); repaint(); break; 
			case KeyEvent.VK_RIGHT: window.game.updateGame('d'); repaint(); break;
			default: window.game.updateGame('x'); repaint(); break; //Invalid movement input however game is still updated
		}
		
		if(!window.game.isRunning())
			window.finishGame();
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}
