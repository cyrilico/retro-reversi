package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.DungeonLevel;
import logic.Game;

public class NewGameInfo extends JFrame {

	private JPanel contentPane;
	private JTextField ogreNumberInput;
	private JComboBox<String> guardPersonalityChooser;
	private JLabel lblGameStarterInfo;
	private WindowKeep window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameInfo frame = new NewGameInfo();
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
	public NewGameInfo() {
		setFrameSettings();
		createGuardChooserComboBox();
		createOgreNumberChooserInput();
		createStartGameButton();
	}
	
	private void setFrameSettings(){
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void createGuardChooserComboBox(){
		guardPersonalityChooser = new JComboBox<String>();
		guardPersonalityChooser.setBounds(181, 48, 161, 27);
		guardPersonalityChooser.addItem("Rookie");
		guardPersonalityChooser.addItem("Drunken");
		guardPersonalityChooser.addItem("Suspicious");
		contentPane.add(guardPersonalityChooser);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(31, 51, 130, 18);
		lblGuardPersonality.setForeground(Color.BLACK);
		lblGuardPersonality.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		contentPane.add(lblGuardPersonality);
	}
	
	private void createOgreNumberChooserInput(){
		ogreNumberInput = new JTextField();
		ogreNumberInput.setBounds(183, 9, 65, 27);
		ogreNumberInput.setColumns(10);
		contentPane.add(ogreNumberInput);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(31, 6, 110, 33);
		lblNumberOfOgres.setForeground(Color.BLACK);
		lblNumberOfOgres.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		contentPane.add(lblNumberOfOgres);
	}
	
	private void createStartGameButton(){
		lblGameStarterInfo = new JLabel("Please enter the game options");
		lblGameStarterInfo.setBounds(31, 149, 311, 16);
		contentPane.add(lblGameStarterInfo);

		JButton btnStartGame = new JButton("Start game!");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInformationAndStart();
			}
		});
		btnStartGame.setBounds(121, 87, 130, 50);
		contentPane.add(btnStartGame);
	}
	
	private void getInformationAndStart(){
		String guardType = (String) guardPersonalityChooser.getSelectedItem(); int nOgres;
		try {
			nOgres = Integer.parseInt(ogreNumberInput.getText());
		}
		catch(NumberFormatException ex) {
			lblGameStarterInfo.setText("Don't be silly, enter a number!"); return;
		}

		if(nOgres < 1 || nOgres > 5) {
			lblGameStarterInfo.setText("Number of ogres must be 1-5"); return;
		}
		window.setGame(new Game(nOgres, guardType, new DungeonLevel(guardType)));
		lblGameStarterInfo.setText("");
		window.requestFocus();
		window.frame.setEnabled(true);
		window.gamePanel.requestFocusInWindow();
		window.setStatusMessage("Press the keyboard arrows to move the hero.");
	}

	public NewGameInfo(WindowKeep window) {
		this();
		this.window = window;
	}
}
