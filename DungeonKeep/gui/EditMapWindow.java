package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.DungeonLevel;
import logic.EditKeepMap;
import logic.Game;
import logic.Hero;
import logic.KeepLevel;
import logic.Level;
import logic.Map;
import logic.Ogre;

public class EditMapWindow extends JFrame {

	private JPanel contentPane;
	private int mapWidth;
	private int mapHeight;
	private char[][] currentMap = {
									{'X','X','X','X','X'},
									{'X','.','.','.','X'},
									{'X','.','.','.','X'},
									{'X','.','.','.','X'},
									{'X','X','X','X','X'},
									};
	
	private char currentChar;
	protected WindowKeep window;
	private EditMapPanel mapPanel;
	
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
				currentChar = '.';
			}
		});
		btnDelete.setBounds(455, 232, 117, 29);
		contentPane.add(btnDelete);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!allElementsPresent()){
					lblStatus.setText("Can't have the map without all its elements!");
					return; //User can't start game if hasn't placed all elements yet
				}
				
				System.out.println(getCharPosition('A')[0] + " - " + getCharPosition('A')[1]);
				if(!(validMap(getInitialisedVisitMap(), getCharPosition('A')[0], getCharPosition('A')[1], 'I') &&
					 validMap(getInitialisedVisitMap(), getCharPosition('A')[0], getCharPosition('A')[1], 'k'))){
					lblStatus.setText("Can't have an unnescapable map!");
					return; //Map must be "winnable" (hero must be able to reach a door and the key)
				}
				
				int[] heroPos = getCharPosition('A');
				Hero hero = new Hero(heroPos[0], heroPos[1]);
				
				ArrayList<Ogre> ogres = new ArrayList<Ogre>();
				int ogreX, ogreY;
				do{
					int result[] = getCharPosition('0');
					ogreX = result[0];
					ogreY = result[1];
					//System.out.println("Current ogre:" + ogreX + " - " + ogreY);
					if(ogreX != -1)
						ogres.add(new Ogre(ogreX, ogreY));
				}while(ogreX != -1);
				
				Map editedMap = new EditKeepMap(currentMap);
				Level editedLevel = new KeepLevel(ogres, hero, editedMap);
				Level dungeonLevel = new DungeonLevel(editedLevel);
				
				window.setGame(new Game(dungeonLevel));
				window.requestFocus();
				window.frame.setEnabled(true);
				window.setStatusMessage("Press the keyboard arrows to move the hero.");
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
	
	private boolean[][] getInitialisedVisitMap(){
		boolean[][] visitMap = new boolean[mapHeight][mapWidth];
		for(int i = 0; i < visitMap.length; i++){
			for(int j = 0; j < visitMap[i].length; j++)
				visitMap[i][j] = false;
		}
		return visitMap;
	}
	
	private boolean validMap(boolean[][] vMap, int startX, int startY, char goal){
		vMap[startY][startX] = true;
		if(currentMap[startY][startX] == goal)
			return true;
		else if(currentMap[startY][startX] == 'X' || (currentMap[startY][startX] == 'I' && goal != 'I'))
			return false;
		else{
			boolean testUp, testLeft, testDown, testRight;
			testUp = !vMap[startY-1][startX];
			testDown = !vMap[startY+1][startX];
			testLeft = !vMap[startY][startX-1];
			testRight = !vMap[startY][startX+1];
			return (testRight ? validMap(vMap, startX+1, startY, goal) : false) ||
					(testUp ? validMap(vMap, startX, startY-1, goal) : false) ||
					(testLeft ? validMap(vMap, startX-1, startY, goal) : false) ||
					(testDown ? validMap(vMap, startX, startY+1, goal) : false);
		}
	}
	
	private boolean allElementsPresent(){
		return elementIsPresent('A') &&
				elementIsPresent('0') &&
				elementIsPresent('I') &&
				elementIsPresent('k');
	}
	
	private boolean elementIsPresent(char element){
		Character c1 = new Character(element);
		for(char[] line : currentMap){
			for(char c : line){
				if(c1.equals(new Character(c)))
					return true;
			}
		}
		return false;
	}
	
	public EditMapWindow(WindowKeep window) {
		this();
		this.window = window;
	}
	
	public void resizeMap() {
		char[][] temp = new char[mapHeight][mapWidth];
		
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
				if( i % (mapHeight-1) == 0 || j % (mapWidth-1) == 0) 
					temp[i][j] = 'X';
				else
					temp[i][j] = '.';
			}
		}	
		
		setCurrentMap(temp);
		contentPane.getComponent(4).repaint();
	}
	
	public char[][] getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(char[][] map) {
		currentMap = map;
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
		
		char toReplace = currentMap[mapY][mapX];

		if(toReplace == 'X' && currentChar != '.') {
			setStatusMessage("Please choose a spot without a wall.");
			return; //If it returns here, it doesn't reset the currentChar, 
			//giving the user a chance to try to place the icon without reclicking the button
		}
			
		currentMap[mapY][mapX] = currentChar;
		
		resetCurrentChar();
		contentPane.getComponent(4).repaint();
		//TEST PRINT
		for(char[] line : currentMap){
			for(char c : line)
				System.out.print(c);
			System.out.println("");
		}
	}
	
	public void resetCurrentChar() {
		currentChar = 'N';
	}
	
	public void setStatusMessage(String str) {
		JLabel gameStatus = (JLabel) contentPane.getComponent(5);
		gameStatus.setText(str);
	}
	
	public int[] getCharPosition(char icon) {
		int[] result = new int[2];
		result[0] = -1; //default value
		
		IterateMap:
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
				if(currentMap[i][j] == icon) {
					if(icon == '0')
						currentMap[i][j] = '.';
					result[1] = i;
					result[0] = j;
					break IterateMap;
				}
			}
		}
		
		return result;
	}
	
}
