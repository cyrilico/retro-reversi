package gui;

import logic.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JToggleButton;

public class WindowKeep {

	private JFrame frame;
	private JTextField ogreNumberInput;
	private Game game = null;

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
					e.printStackTrace();
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
		frame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 638, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(38, 24, 132, 16);
		lblNumberOfOgres.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		lblNumberOfOgres.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNumberOfOgres);

		ogreNumberInput = new JTextField();
		ogreNumberInput.setBounds(182, 19, 86, 26);
		frame.getContentPane().add(ogreNumberInput);
		ogreNumberInput.setColumns(10);

		JLabel lblStatus = new JLabel("You can start a new game.");
		lblStatus.setBounds(38, 415, 357, 16);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		frame.getContentPane().add(lblStatus);

		JTextArea gameZone = new JTextArea();
		gameZone.setFont(new Font("Courier New", Font.PLAIN, 18));
		gameZone.setBounds(26, 116, 369, 287);
		frame.getContentPane().add(gameZone);

		JComboBox guardPersonalityChooser = new JComboBox();
		guardPersonalityChooser.setBounds(182, 73, 132, 27);
		guardPersonalityChooser.addItem(new String("Rookie"));
		guardPersonalityChooser.addItem(new String("Drunken"));
		guardPersonalityChooser.addItem(new String("Suspicious"));
		frame.getContentPane().add(guardPersonalityChooser);

		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(453, 202, 95, 29);
		frame.getContentPane().add(btnUp);

		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(407, 243, 95, 29);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(499, 243, 95, 29);
		frame.getContentPane().add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(453, 284, 95, 29);
		frame.getContentPane().add(btnDown);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String guardType = (String) guardPersonalityChooser.getSelectedItem();

				int nOgres;

				try {
				nOgres = Integer.parseInt(ogreNumberInput.getText());
				}
				catch(NumberFormatException ex) {
					lblStatus.setText("Don't be silly, enter a number!");
					return;
				}

				if(nOgres < 1 || nOgres > 5) {
					lblStatus.setText("Invalid number of ogres! Insert an integer from 1 to 5");
					return;
				}

				game = new Game(nOgres, guardType, new DungeonLevel(guardType));

				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnDown.setEnabled(true);
				btnUp.setEnabled(true);

				lblStatus.setText("Press the movement buttons to move the hero.");
				gameZone.setText(game.getCurrentMatrix());
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

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(38, 77, 132, 16);
		lblGuardPersonality.setForeground(Color.WHITE);
		lblGuardPersonality.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		frame.getContentPane().add(lblGuardPersonality);

		//Buttons action handlers
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('w');
				gameZone.setText(game.getCurrentMatrix());

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());

					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
				}
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('s');
				gameZone.setText(game.getCurrentMatrix());
				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());

					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
				}
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('d');
				gameZone.setText(game.getCurrentMatrix());

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());

					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
				}
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.updateGame('a');
				gameZone.setText(game.getCurrentMatrix());

				if(!game.isRunning()) {
					lblStatus.setText(game.finalMessage());

					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
				}
			}
		});
	}
}
