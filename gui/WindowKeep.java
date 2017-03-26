package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Game;

public class WindowKeep implements java.io.Serializable{

	protected JFrame frame;
	protected Game game = null;

	private JFrame newGameFrame;
	private EditMapWindow editMapFrame;
	
	private JLabel lblStatus;
	private JButton btnNewGame, btnNewBuexittton, btnEditMap, btnSaveGame, btnLoadGame;
	protected JPanel gamePanel;
	private JButton btnLeft, btnRight, btnUp, btnDown;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowKeep window = new WindowKeep();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.exit(-1);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowKeep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createFrame();
		newGameFrame = new NewGameInfo(this);
		editMapFrame = new EditMapWindow(this);
		createButtons();
		createGamePanel();
	}
	
	private void createFrame(){
		frame = new JFrame();

		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 590, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void createButtons(){
		createNewGameButton();
		createExitButton();
		createEditMapButton();
		createSaveButton();
		createLoadButton();
		createMovementButtons();
	}
	
	private void createNewGameButton(){
		btnNewGame = new JButton("Play Default");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				newGameFrame.requestFocusInWindow();
				newGameFrame.setVisible(true);
			}
		});

		btnNewGame.setBounds(433, 23, 135, 44);
		frame.getContentPane().add(btnNewGame);
	}
	
	private void createExitButton(){
		btnNewBuexittton = new JButton("Exit");
		btnNewBuexittton.setBounds(433, 322, 135, 29);
		btnNewBuexittton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewBuexittton);
	}
	
	private void createEditMapButton(){
		btnEditMap = new JButton("Play Custom Map");
		btnEditMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				editMapFrame.resizeMap();
				editMapFrame.requestFocusInWindow();
				editMapFrame.setVisible(true);
			}
		});
		btnEditMap.setBounds(433, 66, 135, 44);
		frame.getContentPane().add(btnEditMap);
	}
	
	private void createSaveButton(){
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game == null)
					return;
				try {
					FileOutputStream fileOut = new FileOutputStream("src/GameSession.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(game); out.close(); fileOut.close();
					lblStatus.setText("Game Session saved in src/GameSession.ser");
				}catch(IOException i) {
					lblStatus.setText("Couldn't save current game session!");
				}
				
				frame.getContentPane().getComponent(1).requestFocusInWindow();
			}
		});
		btnSaveGame.setBounds(433, 138, 135, 25);
		frame.getContentPane().add(btnSaveGame);
	}
	
	private void createLoadButton(){
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game newGame;
				try {
					FileInputStream fileIn = new FileInputStream("src/GameSession.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					newGame = (Game) in.readObject(); in.close(); fileIn.close();
				}catch(IOException i) {
					lblStatus.setText("Couldn't load game session!"); return;
				}catch(ClassNotFoundException c) {
					System.out.println("Game class not found"); return;
				}
				setGame(newGame);
				enableMovementButtons();
				frame.getContentPane().getComponent(1).requestFocusInWindow();
			}
		});
		btnLoadGame.setBounds(433, 174, 135, 25);
		frame.getContentPane().add(btnLoadGame);
	}
	
	private void createMovementButtons(){
		createUpButton();
		createDownButton();
		createLeftButton();
		createRightButton();
		
		disableMovementButtons();
	}
	
	private void createUpButton(){
		btnUp = new JButton("Up"); btnUp.setBounds(458, 211, 76, 25);
		frame.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('w');
				gamePanel.repaint(); gamePanel.requestFocusInWindow();

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());
					disableMovementButtons();
				}
			}
		});
	}
	
	private void createDownButton(){
		btnDown = new JButton("Down"); btnDown.setBounds(458, 285, 76, 25);
		frame.getContentPane().add(btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('s');
				gamePanel.repaint(); gamePanel.requestFocusInWindow();

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());
					disableMovementButtons();
				}
			}
		});
	}
	
	private void createLeftButton(){
		btnLeft = new JButton("Left"); btnLeft.setBounds(422, 248, 76, 25);
		frame.getContentPane().add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('a'); 
				gamePanel.repaint(); gamePanel.requestFocusInWindow();

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());
					disableMovementButtons();
				}
			}
		});
	}
	
	private void createRightButton(){
		btnRight = new JButton("Right"); btnRight.setBounds(502, 248, 76, 25);
		frame.getContentPane().add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('d');
				gamePanel.repaint(); gamePanel.requestFocusInWindow();

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());
					disableMovementButtons();
				}
			}
		});
	}
	
	private void disableMovementButtons(){
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
	}
	
	protected void enableMovementButtons(){
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
	}
	
	private void createGamePanel(){
		lblStatus = new JLabel("You can start a new game.");
		lblStatus.setName("lblStatus");
		lblStatus.setBounds(36, 322, 357, 16);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frame.getContentPane().add(lblStatus);

		gamePanel = new GamePanel(this);
		gamePanel.setBounds(26, 23, 369, 287);
		frame.getContentPane().add(gamePanel);
		
		gamePanel.requestFocusInWindow();
	}

	public void requestFocus() {
		newGameFrame.setVisible(false);
		editMapFrame.setVisible(false);
		frame.setVisible(true);
		frame.requestFocus();
		frame.getContentPane().getComponent(1).requestFocus();
	}

	public void setGame(Game game) {
		this.game = game;
		frame.getContentPane().getComponent(1).repaint();
	}

	public void setStatusMessage(String str) {
		lblStatus.setText(str);
	}

	public void finishGame(){
		lblStatus.setText(game.finalMessage());
		disableMovementButtons();
		game = null;
	}
}
