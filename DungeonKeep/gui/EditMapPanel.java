package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class EditMapPanel extends GraphicPanel implements MouseListener {
	
	protected EditMapWindow window;
	private String currentMap = "XXXXX\nX...X\nX...X\nX...X\nXXXXX\n";

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

		for(int y = 0; y < 12; y++) {
			int x;
			for(x = 0; x < 15; x++)
				g.drawImage(floor, x*25, y*25, 25, 25, null);
			x = 0;
		}

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
