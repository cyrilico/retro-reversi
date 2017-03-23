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
	private JFrame editMapFrame;

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
		frame = new JFrame();

		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 590, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		newGameFrame = new NewGameInfo(this);
		editMapFrame = new EditMapWindow(this);

		JLabel lblStatus = new JLabel("You can start a new game.");
		lblStatus.setName("lblStatus");
		lblStatus.setBounds(36, 322, 357, 16);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frame.getContentPane().add(lblStatus);

		JPanel gamePanel = new GamePanel(this);
		gamePanel.setBounds(26, 23, 369, 287);
		frame.getContentPane().add(gamePanel);
		gamePanel.requestFocusInWindow();

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				newGameFrame.requestFocusInWindow();
				newGameFrame.setVisible(true);
			}
		});

		btnNewGame.setBounds(442, 60, 117, 29);
		frame.getContentPane().add(btnNewGame);

		JButton btnNewBuexittton = new JButton("Exit");
		btnNewBuexittton.setBounds(442, 243, 117, 29);
		btnNewBuexittton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewBuexittton);
		
		JButton btnEditMap = new JButton("Edit Map");
		btnEditMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				editMapFrame.requestFocusInWindow();
				editMapFrame.setVisible(true);
			}
		});
		btnEditMap.setBounds(442, 97, 117, 29);
		frame.getContentPane().add(btnEditMap);
		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game == null)
					return;

				try {
					FileOutputStream fileOut = new FileOutputStream("src/GameSession.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(game);
					out.close();
					fileOut.close();
					lblStatus.setText("Game Session saved in src/GameSession.ser");
				}catch(IOException i) {
					lblStatus.setText("Couldn't save current game session!");
				}
				
				frame.getContentPane().getComponent(1).requestFocusInWindow();
			}
		});
		btnSaveGame.setBounds(442, 138, 117, 25);
		frame.getContentPane().add(btnSaveGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game newGame;
				try {
					FileInputStream fileIn = new FileInputStream("src/GameSession.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					newGame = (Game) in.readObject();
					in.close();
					fileIn.close();
				}catch(IOException i) {
					lblStatus.setText("Couldn't load game session!");
					return;
				}catch(ClassNotFoundException c) {
					System.out.println("Game class not found");
					return;
				}
				
				setGame(newGame);
				frame.getContentPane().getComponent(1).requestFocusInWindow();
			}
		});
		btnLoadGame.setBounds(442, 174, 117, 25);
		frame.getContentPane().add(btnLoadGame);
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
		JLabel gameStatus = (JLabel) frame.getContentPane().getComponent(0);
		gameStatus.setText(str);
	}

	public void finishGame(){
		JLabel gameStatus = (JLabel) frame.getContentPane().getComponent(0);
		gameStatus.setText(game.finalMessage());
		game = null;
	}
}
