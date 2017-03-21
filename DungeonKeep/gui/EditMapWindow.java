package gui;

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

public class EditMapWindow extends JFrame {

	private JPanel contentPane;
	private int mapWidth;
	private int mapHeight;
	private String currentMap = "XXXXX\nX...X\nX...X\nX...X\nXXXXX\n";
	WindowKeep window;
	EditMapPanel mapPanel;
	
	private static final int MIN_HEIGHT = 5;
	private static final int MAX_HEIGHT = 12;

	private static final int MIN_WIDTH = 5;
	private static final int MAX_WIDTH = 15;

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
		mapPanel.setBounds(28, 86, MAX_WIDTH * 25 + 50, MAX_HEIGHT * 25);
		contentPane.add(mapPanel);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(28, 401, 544, 16);
		contentPane.add(lblStatus);
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
}
