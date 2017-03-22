package gui;

import logic.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMapWindow extends JFrame {

	private JPanel contentPane;
	private int mapWidth;
	private int mapHeight;
	private String currentMap = "XXXXX\nX...X\nX...X\nX...X\nXXXXX\n";
	private char currentChar;
	WindowKeep window;
	EditMapPanel mapPanel;
	
	private static final int MIN_HEIGHT = 5;
	private static final int MAX_HEIGHT = 12;

	private static final int MIN_WIDTH = 5;
	private static final int MAX_WIDTH = 15;
	
	private static final int MAP_START_X = 28;
	private static final int MAP_START_Y = 86;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMapWindow frame = new EditMapWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditMapWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mapHeight = 5;
		mapWidth = 5;
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(28, 20, 61, 16);
		contentPane.add(lblHeight);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(28, 47, 61, 16);
		contentPane.add(lblWidth);
		
		JSpinner spinnerHeight = new JSpinner();
		spinnerHeight.setBounds(85, 15, 48, 26);
		spinnerHeight.setModel(new SpinnerNumberModel(5, MIN_HEIGHT, MAX_HEIGHT, 1));
		contentPane.add(spinnerHeight);
		
		JSpinner spinnerWidth = new JSpinner();
		spinnerWidth.setBounds(85, 42, 48, 26);
		spinnerWidth.setModel(new SpinnerNumberModel(5, MIN_WIDTH, MAX_WIDTH, 1));
		contentPane.add(spinnerWidth);
		
		JPanel mapPanel = new EditMapPanel(this);
		mapPanel.setBounds(MAP_START_X, MAP_START_Y, MAX_WIDTH * 25, MAX_HEIGHT * 25);
		contentPane.add(mapPanel);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(28, 401, 544, 16);
		contentPane.add(lblStatus);
		
		JButton btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChar = 'A';
			}
		});
		btnHero.setBounds(455, 86, 117, 29);
		contentPane.add(btnHero);
		
		JButton btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChar = '0';
			}
		});
		btnOgre.setBounds(455, 116, 117, 29);
		contentPane.add(btnOgre);
		
		JButton btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChar = 'k';
			}
		});
		btnKey.setBounds(455, 146, 117, 29);
		contentPane.add(btnKey);
		
		JButton btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChar = 'X';
			}
		});
		btnWall.setBounds(455, 173, 117, 29);
		contentPane.add(btnWall);
		
		JButton btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChar = 'I';
			}
		});
		btnDoor.setBounds(455, 204, 117, 29);
		contentPane.add(btnDoor);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(455, 232, 117, 29);
		contentPane.add(btnDelete);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnStartGame.setBounds(455, 357, 117, 29);
		contentPane.add(btnStartGame);
		mapPanel.requestFocusInWindow();
		
		
		spinnerHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int input = (Integer) spinnerHeight.getValue();
				
				if(input < MIN_HEIGHT && input > MAX_HEIGHT) {
					lblStatus.setText("Please insert a height value between " + MIN_HEIGHT + " and " + MAX_HEIGHT + ".");
					return;
				}
				
				mapHeight = input;
				resizeMap();
			}
		});
		
		spinnerWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int input = (Integer) spinnerWidth.getValue();
				
				if(input < MIN_WIDTH && input > MAX_WIDTH) {
					lblStatus.setText("Please insert a width value between " + MIN_WIDTH + " and " + MAX_WIDTH + ".");
					return;
				}
				
				mapWidth = input;
				resizeMap();
			}
		});
	}
	
	public EditMapWindow(WindowKeep window) {
		this();
		this.window = window;
	}
	
	public void resizeMap() {
		String temp = "";
		
		for(int i = 0; i < mapHeight; i++) {
			if(i == 0 || i == mapHeight - 1) 
				temp += String.join("" , Collections.nCopies(mapWidth, new String("X"))) + "\n";
			else 
				temp += "X" + String.join("" , Collections.nCopies(mapWidth-2, new String("."))) + "X\n";
		}
		
		setCurrentMap(temp);
		contentPane.getComponent(4).repaint();
	}
	
	public String getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(String str) {
		currentMap = str;
	}

	public static int getMaxHeight() {
		return MAX_HEIGHT;
	}

	public static int getMaxWidth() {
		return MAX_WIDTH;
	}

	public static int getMapStartX() {
		return MAP_START_X;
	}

	public static int getMapStartY() {
		return MAP_START_Y;
	}
	
	public char getCurrentChar() {
		return currentChar;
	}
	
	public void addCurrentChar(int x, int y) {
		
		if(currentChar == 'N')
			return;

		int mapX = x / 25;
		int mapY = y / 25;
	
		char[] temp = currentMap.toCharArray();
		
		char toReplace = temp[mapX + ( (mapWidth + 1) * mapY )];
		if(toReplace == 'X') {
			setStatusMessage("Please choose a spot without a wall.");
			return; //If it returns here, it doesn't reset the currentChar, 
					//giving the user a chance to try to place the icon without reclicking the button
		}
			
		temp[mapX + ( (mapWidth + 1) * mapY )] = currentChar;
		
		currentMap = String.valueOf(temp);
		resetCurrentChar();
		contentPane.getComponent(4).repaint();
	}
	
	public void resetCurrentChar() {
		currentChar = 'N';
	}
	
	public void setStatusMessage(String str) {
		JLabel gameStatus = (JLabel) contentPane.getComponent(5);
		gameStatus.setText(str);
	}
	
}
