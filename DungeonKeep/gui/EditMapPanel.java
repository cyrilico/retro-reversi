package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class EditMapPanel extends GraphicPanel implements MouseListener {
	
	protected EditMapWindow window;

	/**
	 * Create the panel.
	 */
	public EditMapPanel(EditMapWindow window) {
		super();
		this.window = window;
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		int maxHeight = EditMapWindow.getMaxHeight();
		int maxWidth = EditMapWindow.getMaxWidth();
		
		for(int y = 0; y < maxHeight; y++) {
			int x;
			for(x = 0; x < maxWidth; x++)
				g.drawImage(floor, x*IMG_SIZE, y*IMG_SIZE, IMG_SIZE, IMG_SIZE, null);
			x = 0;
		}

		int k = 0, j = 0;
		String currentMap = window.getCurrentMap();

		for(int i = 0; i < currentMap.length(); i++) {
			if(currentMap.charAt(i) != '\n')
				g.drawImage(getCurrentImage(currentMap.charAt(i)), IMG_SIZE*(k++), IMG_SIZE*j, IMG_SIZE, IMG_SIZE, null);
			else {
				k=0;
				j++;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
