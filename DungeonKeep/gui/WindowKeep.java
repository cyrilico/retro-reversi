package gui;

import logic.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class WindowKeep {

	protected JFrame frame;
	protected Game game = null;
	private JFrame newGameFrame;

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
	}
	
	public void requestFocus() {
		newGameFrame.setVisible(false);
		frame.requestFocus();
		frame.getContentPane().getComponent(1).requestFocus();
	}
	
	public void setGame(Game game) {
		this.game = game;
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