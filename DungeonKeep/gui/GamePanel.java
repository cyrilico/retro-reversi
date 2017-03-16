package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel{
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
		try{
			hero_nokey = ImageIO.read(new File("src/hero.png"));
			guard_awake = ImageIO.read(new File("src/guard.png"));
			ogre_normal = ImageIO.read(new File("src/ogre.png"));
			wall = ImageIO.read(new File("src/wall.png"));
			floor = ImageIO.read(new File("src/floor.png"));
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
		case 'K':
			result = hero_nokey;
			break;
		case 'G':
		case 'g':
			result = guard_awake;
			break;
		case 'I':
			result = wall;
			break;
		case 'k':
			result = floor;
			break;
		case '0':
		case '8':
			result = ogre_normal;
			break;
		case '*':
			result = ogre_normal;
			break;
		case 'S':
			result = floor;
			break;
		}
		return result;
	}
	
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		String currentMap = window.game.getCurrentMatrix();
		int lineSize = currentMap.indexOf('\n');
		System.out.println(lineSize);
		int k = 0, j = 0;
		for(int i = 0; i < currentMap.length(); i++){
			if(currentMap.charAt(i) != '\n')
				g.drawImage(getCurrentImage(currentMap.charAt(i)), 25*(k++), 25*j, 25, 25, null);
			else{
				k=0;
				j++;
			}
		}
	} 
}
