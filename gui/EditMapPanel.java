package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EditMapPanel extends GraphicPanel implements MouseListener, MouseMotionListener {
	
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

		char[][] tempMap = window.getCurrentMap();
		
		for(int i = 0; i < tempMap.length; i++) {
			for(int j = 0; j < tempMap[i].length; j++) 
				g.drawImage(getCurrentImage(tempMap[i][j]), IMG_SIZE*j, IMG_SIZE*i, IMG_SIZE, IMG_SIZE, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		
		if(isValidPress(clickX, clickY))
			window.addCurrentChar(clickX, clickY);
		else
			window.setStatusMessage("You can't change the map on or outside the closing walls.");
	}
	
	public boolean isValidPress(int x, int y) {
		int mapX = x / IMG_SIZE;
		int mapY = y / IMG_SIZE;

		int mapHeight = window.getMapHeight();
		int mapWidth = window.getMapWidth();

		if(isOnWall(mapX, mapY, mapHeight, mapWidth))
			return false;
		
		if(isOutsideWall(mapX, mapY, mapHeight, mapWidth))
			return false;
		
		return true;
	}
	
	public boolean isOnWall(int mapX, int mapY, int mapHeight, int mapWidth) {
		
		if((mapX % (mapWidth-1)) == 0)
			return true;
		
		if((mapY % (mapHeight-1)) == 0)
			return true;
		
		return false;
	}
	
	public boolean isOutsideWall(int mapX, int mapY, int mapHeight, int mapWidth) {
		if(mapX > (mapWidth-1))
			return true;
		
		if(mapY > (mapHeight-1))
			return true;
		
		return false;
	}

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseDragged(MouseEvent e) { }

	@Override
	public void mouseMoved(MouseEvent e) { }
}
