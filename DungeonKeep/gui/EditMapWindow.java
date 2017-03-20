package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class EditMapWindow extends JFrame {

	private JPanel contentPane;
	private int mapWidth;
	private int mapHeight;
	WindowKeep window;

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
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(28, 25, 61, 16);
		contentPane.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(28, 58, 61, 16);
		contentPane.add(lblHeight);
		
		JSpinner spinnerHeight = new JSpinner();
		spinnerHeight.setBounds(85, 20, 48, 26);
		contentPane.add(spinnerHeight);
		
		JSpinner spinnerWidth = new JSpinner();
		spinnerWidth.setBounds(85, 53, 48, 26);
		contentPane.add(spinnerWidth);
		
		JPanel gamePanel = new EditMapPanel(this);
		gamePanel.setBounds(28, 102, 369, 287);
		contentPane.add(gamePanel);
		gamePanel.requestFocusInWindow();
	}
	
	public EditMapWindow(WindowKeep window) {
		this();
		this.window = window;
	}
}
