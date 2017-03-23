package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class GamePanel extends GraphicPanel implements KeyListener{

	protected WindowKeep window;
	private static HashMap<Integer,Character> getMov = new HashMap<Integer, Character>();
	static {
		getMov.put(KeyEvent.VK_UP, 'w');
		getMov.put(KeyEvent.VK_DOWN, 's');
		getMov.put(KeyEvent.VK_LEFT, 'a');
		getMov.put(KeyEvent.VK_RIGHT, 'd');
	}

	public GamePanel(WindowKeep window) {
		super();
		this.window = window;
		addKeyListener(this);
	}

	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);

		paintFloor(g);

		if(window.game == null) return;

		paintGame(g);
	}

	private void paintFloor(Graphics g) {
		for(int y = 0; y < 12; y++) {
			int x;
			for(x = 0; x < 15; x++)
				g.drawImage(floor, x*25, y*25, 25, 25, null);
			x = 0;
		}
	}
	
	private void paintGame(Graphics g) {
		int k = 0, j = 0;
		String currentMap = window.game.getCurrentMatrix();
		
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
		
		window.game.updateGame(getMov.getOrDefault(e.getKeyCode(), 'x'));
		repaint();
		
		if(!window.game.isRunning())
			window.finishGame();
	}

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}
