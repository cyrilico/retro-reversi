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

public class WindowKeep {

	private JFrame frame;
	private JInternalFrame iframe;
	protected Game game = null;

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
		frame.setBounds(100, 100, 638, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		iframe = new JInternalFrame("New Game Info Selector");
		iframe.setBounds(111, 122, 411, 199);
		frame.getContentPane().add(iframe);
		iframe.getContentPane().setLayout(null);
		iframe.setVisible(false);

		JComboBox<String> guardPersonalityChooser = new JComboBox<String>();
		guardPersonalityChooser.setBounds(176, 12, 225, 31);
		iframe.getContentPane().add(guardPersonalityChooser);
		guardPersonalityChooser.addItem("Rookie");
		guardPersonalityChooser.addItem("Drunken");
		guardPersonalityChooser.addItem("Suspicious");

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(10, 19, 132, 16);
		iframe.getContentPane().add(lblGuardPersonality);
		lblGuardPersonality.setForeground(Color.BLACK);
		lblGuardPersonality.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));

		JTextField ogreNumberInput = new JTextField();
		ogreNumberInput.setBounds(176, 54, 127, 19);
		iframe.getContentPane().add(ogreNumberInput);
		ogreNumberInput.setColumns(10);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(10, 55, 411, 17);
		iframe.getContentPane().add(lblNumberOfOgres);
		lblNumberOfOgres.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		lblNumberOfOgres.setForeground(Color.BLACK);
		
		JLabel lblGameStarterInfo = new JLabel("");
		lblGameStarterInfo.setBounds(12, 108, 217, 47);
		iframe.getContentPane().add(lblGameStarterInfo);

		JLabel lblStatus = new JLabel("You can start a new game.");
		lblStatus.setName("lblStatus");
		lblStatus.setBounds(38, 415, 357, 16);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frame.getContentPane().add(lblStatus);

		JPanel gamePanel = new GamePanel(this);
		gamePanel.setBounds(26, 116, 369, 287);
		frame.getContentPane().add(gamePanel);
		gamePanel.requestFocusInWindow();
		
		JButton btnStartGame = new JButton("Start game!");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String guardType = (String) guardPersonalityChooser.getSelectedItem();

				int nOgres;

				try {
					nOgres = Integer.parseInt(ogreNumberInput.getText());
				}
				catch(NumberFormatException ex) {
					lblGameStarterInfo.setText("Don't be silly, enter a number!");
					return;
				}

				if(nOgres < 1 || nOgres > 5) {
					lblGameStarterInfo.setText("Number of ogres must be 1-5");
					return;
				}

				game = new Game(nOgres, guardType, new DungeonLevel(guardType));
				lblGameStarterInfo.setText("");
				iframe.setVisible(false);
				gamePanel.repaint();
				gamePanel.requestFocusInWindow();
				
				lblStatus.setText("Press the keyboard arrows to move the hero.");
			}
		});
		btnStartGame.setBounds(247, 130, 142, 25);
		iframe.getContentPane().add(btnStartGame);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iframe.requestFocusInWindow();
				iframe.moveToFront();
				iframe.setVisible(true);
			}
		});

		btnNewGame.setBounds(442, 129, 117, 29);
		frame.getContentPane().add(btnNewGame);

		JButton btnNewBuexittton = new JButton("Exit");
		btnNewBuexittton.setBounds(442, 357, 117, 29);
		btnNewBuexittton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnNewBuexittton);
	}

	public void finishGame(){
		JLabel gameStatus = (JLabel) frame.getContentPane().getComponent(1);
		gameStatus.setText(game.finalMessage());
		game = null;
	}
}
