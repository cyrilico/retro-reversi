package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends GraphicPanel implements KeyListener{

	protected WindowKeep window;

	public GamePanel(WindowKeep window) {
		super();
		this.window = window;
		addKeyListener(this);
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
