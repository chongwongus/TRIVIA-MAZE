package viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controllers.Maze;
import controllers.Player;
import models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

/**
 * GUI for Trivia Maze (currently WIP) Built using windowbuilder
 * Note: Direction movements still need to check the boundaries of the maze
 * @author Roland Hanson
 * @version 2.0
 */
public class TriviaMazeGUI extends JFrame {

	/**
	 * Generated serialUID
	 */
	private static final long serialVersionUID = 5085020715814436080L;
	
	private static JPanel contentPane;
	JButton btnN;
	JButton btnE;
	JButton btnS;
	JButton btnW;
	JMenuBar menuBar;
	JMenu mnFile;
	JMenuItem mntmSave;
	JMenuItem mntmLoad;
	JMenuItem mntmExit;
	JMenu mnHelp;
	JMenuItem mntmAbout;
	JMenuItem mntmInstructions;
	JMenuItem mntmCheats;
	JPanel mazePanel;
	JButton btnStart;
	
	Maze myMaze = new Maze();
	static Player myPlayer = new Player();
	static Room[][] myRoomChk;
	static JRadioButton rdbtnChoiceA;
	static JRadioButton rdbtnChoiceB;
	static JRadioButton rdbtnChoiceC;
	static JRadioButton rdbtnChoiceD;
	static JLabel lblQuestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaMazeGUI frame = new TriviaMazeGUI();
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
	public TriviaMazeGUI() {
		setTitle("Trivia Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnN = new JButton("N");
		btnN.addActionListener(new goNorth());
		btnN.setBounds(459, 67, 50, 23);
		contentPane.add(btnN);
		
		btnE = new JButton("E");
		btnE.addActionListener(new goEast());
		btnE.setBounds(484, 101, 50, 23);
		contentPane.add(btnE);
		
		btnS = new JButton("S");
		btnS.addActionListener(new goSouth());
		btnS.setBounds(459, 135, 50, 23);
		contentPane.add(btnS);
		
		btnW = new JButton("W");
		btnW.addActionListener(new goWest());
		btnW.setBounds(429, 101, 50, 23);
		contentPane.add(btnW);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 553, 22);
		contentPane.add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new saveMaze());
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new exitGame());
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);
		
		mntmCheats = new JMenuItem("Cheats");
		mnHelp.add(mntmCheats);
		
		mazePanel = new mazePanel();
		mazePanel.setBounds(10, 33, 416, 195);
		contentPane.add(mazePanel);
		mazePanel.setVisible(false);
		
		btnStart = new JButton("Start!");
		btnStart.addActionListener(new startGame());
		btnStart.setBounds(229, 181, 89, 23);
		contentPane.add(btnStart);
		
		rdbtnChoiceA = new JRadioButton("Choice A");
		rdbtnChoiceA.addActionListener(new RdbtnChoiceActionListener());
		rdbtnChoiceA.setBounds(429, 222, 109, 23);
		contentPane.add(rdbtnChoiceA);
		rdbtnChoiceA.setVisible(false);
		
		rdbtnChoiceB = new JRadioButton("Choice B");
		rdbtnChoiceB.addActionListener(new RdbtnChoiceActionListener());
		rdbtnChoiceB.setBounds(429, 248, 109, 23);
		contentPane.add(rdbtnChoiceB);
		rdbtnChoiceB.setVisible(false);
		
		rdbtnChoiceC = new JRadioButton("Choice C");
		rdbtnChoiceC.addActionListener(new RdbtnChoiceActionListener());
		rdbtnChoiceC.setBounds(429, 274, 109, 23);
		contentPane.add(rdbtnChoiceC);
		rdbtnChoiceC.setVisible(false);
		
		rdbtnChoiceD = new JRadioButton("Choice D");
		rdbtnChoiceD.addActionListener(new RdbtnChoiceActionListener());
		rdbtnChoiceD.setBounds(429, 300, 109, 23);
		contentPane.add(rdbtnChoiceD);
		rdbtnChoiceD.setVisible(false);
		
		lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(136, 278, 249, 45);
		contentPane.add(lblQuestion);
		lblQuestion.setVisible(false);
		
	}
	
	/**
	 * Checks to see if the there is a door and if it is locked or not
	 * If true, a question will be shown in the GUI
	 * Might need to separate this into two methods
	 */
	public static void chkDoor(final String theDirection) {
		if (myRoomChk[myPlayer.getLocationX()][myPlayer.getLocationY()].hasDoor(theDirection)) {
			Door doorChk = myRoomChk[myPlayer.getLocationX()][myPlayer.getLocationY()].getDoor(theDirection);
			String [] choices = doorChk.getChoices();
			if (doorChk.isOpen() == false) {
				lblQuestion.setText(doorChk.getQuestion());
				lblQuestion.setVisible(true);
				if (choices.length == 2) {
					rdbtnChoiceA.setText(choices[0]);
					rdbtnChoiceB.setText(choices[1]);
					rdbtnChoiceA.setVisible(true);
					rdbtnChoiceB.setVisible(true);
				} else {
					rdbtnChoiceA.setText(choices[0]);
					rdbtnChoiceB.setText(choices[1]);
					rdbtnChoiceC.setText(choices[2]);
					rdbtnChoiceD.setText(choices[3]);
					rdbtnChoiceA.setVisible(true);
					rdbtnChoiceB.setVisible(true);
					rdbtnChoiceC.setVisible(true);
					rdbtnChoiceD.setVisible(true);
				}
				contentPane.repaint();
			}
		}
	}
	
	/**
	 * Displays the maze
	 * Note: Boundaries are currently not correct
	 * This is because of how the maze is being displayed
	 * This must be fixed later
	 * @author Roland Hanson
	 *
	 */
	private class mazePanel extends JPanel{
		
		private mazePanel() {
			
		}
		
		public void paintComponent(Graphics g) {
			int bxWidth = 30;
			int bxHeight = 30;
			super.paintComponent(g);
			// Draws the maze
			for (int i = 0; i < 1 + myMaze.getMyRow() * bxWidth; i += bxWidth) {
				for (int j = 0; j < 1 + myMaze.getMyCol() * bxHeight; j += bxHeight) {
					g.drawRect(i, j, bxWidth, bxHeight);
				}
			}
			// Currently, the player displayed is based on window location NOT maze location
			// Some tweaking may need to be done, but otherwise the player is where they should be
			g.drawString("<O>", myPlayer.getLocationX() * bxWidth, myPlayer.getLocationY() * bxHeight);
		}
		
	}
	
	/**
	 * Makes the player go north
	 * @author Roland Hanson
	 *
	 */
	private class goNorth implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (myPlayer.getLocationY() > 1) {
				chkDoor("North");
				myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() - 1);
				mazePanel.repaint();
			}
		}
	}
	
	/**
	 * Makes the player go east
	 * @author Roland Hanson
	 *
	 */
	private class goEast implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationX() < myMaze.getMyRow()) {
				chkDoor("East");
				myPlayer.setLocation(myPlayer.getLocationX() + 1, myPlayer.getLocationY());
				mazePanel.repaint();
			}
		}
	}
	
	/**
	 * Makes the player go south
	 * @author Roland Hanson
	 *
	 */
	private class goSouth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationY() < myMaze.getMyCol() + 1) {
				chkDoor("South");
				myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() + 1);
				mazePanel.repaint();
			}
		}
	}
	
	/**
	 * Makes the player go west
	 * @author Roland Hanson
	 *
	 */
	private class goWest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationX() > 0) {
				chkDoor("West");
				myPlayer.setLocation(myPlayer.getLocationX() - 1, myPlayer.getLocationY());
				mazePanel.repaint();
			}
		}
	}
	
	/**
	 * Saves the game (would call the SaveLoad class once it's complete)
	 * @author Roland Hanson
	 *
	 */
	private class saveMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/**
	 * Exits the program
	 * @author Roland Hanson
	 *
	 */
	private class exitGame implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	/**
	 * Initializes the maze once the start button is pressed
	 * @author Roland Hanson
	 *
	 */
	private class startGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Initialize maze & player
			myMaze.initializeRooms();
			myMaze.initializeRoomQuestions();
			myRoomChk = myMaze.getMyMaze();
			myPlayer.setLocation(1, 1);
			btnStart.setVisible(false);
			mazePanel.setVisible(true);
			mazePanel.repaint();
		}
	}
	
	/**
	 * Checks to see if the selected choice is correct
	 * @author Roland Hanson
	 *
	 */
	private class RdbtnChoiceActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
}