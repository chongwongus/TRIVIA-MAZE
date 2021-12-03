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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controllers.Maze;
import controllers.Player;
import controllers.SaveData;
import models.*;
import java.awt.event.ActionListener;
import java.io.File;
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
	
	private static JPanel myContentPane;
	JButton myBtnN;
	JButton myBtnE;
	JButton myBtnS;
	JButton myBtnW;
	JMenuBar myMenuBar;
	JMenu myMnFile;
	JMenuItem myMntmSave;
	JMenuItem myMntmLoad;
	JMenuItem myMntmExit;
	JMenu myMnHelp;
	JMenuItem myMntmAbout;
	JMenuItem myMntmInstructions;
	JMenuItem myMntmCheats;
	JPanel myMazePanel;
	JButton myBtnStart;
	
	Maze myMaze = new Maze();
	private String myLastDirection;
	static Door myDoorChk;
	static Player myPlayer = new Player();
	static Room[][] myRoomChk;
	static JRadioButton myRdbtnChoiceA;
	static JRadioButton myRdbtnChoiceB;
	static JRadioButton myRdbtnChoiceC;
	static JRadioButton myRdbtnChoiceD;
	static JLabel myLblQuestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaMazeGUI frame = new TriviaMazeGUI();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 716, 395);
		myContentPane = new JPanel();
		myContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(myContentPane);
		myContentPane.setLayout(null);
		
		myBtnN = new JButton("N");
		myBtnN.addActionListener(new goNorth());
		myBtnN.setBounds(556, 67, 50, 23);
		myContentPane.add(myBtnN);
		
		myBtnE = new JButton("E");
		myBtnE.addActionListener(new goEast());
		myBtnE.setBounds(586, 101, 50, 23);
		myContentPane.add(myBtnE);
		
		myBtnS = new JButton("S");
		myBtnS.addActionListener(new goSouth());
		myBtnS.setBounds(556, 135, 50, 23);
		myContentPane.add(myBtnS);
		
		myBtnW = new JButton("W");
		myBtnW.addActionListener(new goWest());
		myBtnW.setBounds(526, 101, 50, 23);
		myContentPane.add(myBtnW);
		
		myMenuBar = new JMenuBar();
		myMenuBar.setBounds(0, 0, 700, 22);
		myContentPane.add(myMenuBar);
		
		myMnFile = new JMenu("File");
		myMenuBar.add(myMnFile);
		
		myMntmSave = new JMenuItem("Save");
		myMntmSave.addActionListener(new saveMaze());
		myMnFile.add(myMntmSave);
		
		myMntmLoad = new JMenuItem("Load");
		myMntmLoad.addActionListener(new loadMaze());
		myMnFile.add(myMntmLoad);
		
		myMntmExit = new JMenuItem("Exit");
		myMntmExit.addActionListener(new exitGame());
		myMnFile.add(myMntmExit);
		
		myMnHelp = new JMenu("Help");
		myMenuBar.add(myMnHelp);
		
		myMntmAbout = new JMenuItem("About");
		myMnHelp.add(myMntmAbout);
		
		myMntmInstructions = new JMenuItem("Instructions");
		myMnHelp.add(myMntmInstructions);
		
		myMntmCheats = new JMenuItem("Cheats");
		myMnHelp.add(myMntmCheats);
		
		myMazePanel = new mazePanel();
		myMazePanel.setBounds(10, 33, 416, 195);
		myContentPane.add(myMazePanel);
		myMazePanel.setVisible(false);
		
		myBtnStart = new JButton("Start!");
		myBtnStart.addActionListener(new startGame());
		myBtnStart.setBounds(269, 181, 89, 23);
		myContentPane.add(myBtnStart);
		
		myRdbtnChoiceA = new JRadioButton("Choice A");
		myRdbtnChoiceA.addActionListener(new RdbtnChoiceActionListener());
		myRdbtnChoiceA.setBounds(429, 222, 271, 23);
		myContentPane.add(myRdbtnChoiceA);
		myRdbtnChoiceA.setVisible(false);
		
		myRdbtnChoiceB = new JRadioButton("Choice B");
		myRdbtnChoiceB.addActionListener(new RdbtnChoiceActionListener());
		myRdbtnChoiceB.setBounds(429, 248, 271, 23);
		myContentPane.add(myRdbtnChoiceB);
		myRdbtnChoiceB.setVisible(false);
		
		myRdbtnChoiceC = new JRadioButton("Choice C");
		myRdbtnChoiceC.addActionListener(new RdbtnChoiceActionListener());
		myRdbtnChoiceC.setBounds(429, 274, 271, 23);
		myContentPane.add(myRdbtnChoiceC);
		myRdbtnChoiceC.setVisible(false);
		
		myRdbtnChoiceD = new JRadioButton("Choice D");
		myRdbtnChoiceD.addActionListener(new RdbtnChoiceActionListener());
		myRdbtnChoiceD.setBounds(429, 300, 271, 23);
		myContentPane.add(myRdbtnChoiceD);
		myRdbtnChoiceD.setVisible(false);
		
		myLblQuestion = new JLabel("Question");
		myLblQuestion.setBounds(10, 274, 413, 23);
		myContentPane.add(myLblQuestion);
		myLblQuestion.setVisible(false);
		
	}
	
	/**
	 * Checks to see if there is a door given the player's current location
	 * @param theDirection
	 * @return
	 */
	private static boolean doorChk(final String theDirection) {
		if (myRoomChk[myPlayer.getLocationX()][myPlayer.getLocationY()].hasDoor(theDirection)) {
			myDoorChk = myRoomChk[myPlayer.getLocationX()][myPlayer.getLocationY()].getDoor(theDirection);
			return true;
		} 
		return false;
	}
	
	/**
	 * Sets up a question from a door (based on the players location) to be shown in the GUI 
	 * @param theDirection
	 */
	private static void doorSetup(final String theDirection) {
		//myDoorChk = myRoomChk[myPlayer.getLocationX()][myPlayer.getLocationY()].getDoor(theDirection);
		String [] choices = myDoorChk.getChoices();
		myLblQuestion.setText(myDoorChk.getQuestion());
		myLblQuestion.setVisible(true);
		if (choices.length == 2) {
				myRdbtnChoiceA.setText(choices[0]);
				myRdbtnChoiceB.setText(choices[1]);
				myRdbtnChoiceA.setVisible(true);
				myRdbtnChoiceB.setVisible(true);
		} else {
				myRdbtnChoiceA.setText(choices[0]);
				myRdbtnChoiceB.setText(choices[1]);
				myRdbtnChoiceC.setText(choices[2]);
				myRdbtnChoiceD.setText(choices[3]);
				myRdbtnChoiceA.setVisible(true);
				myRdbtnChoiceB.setVisible(true);
				myRdbtnChoiceC.setVisible(true);
				myRdbtnChoiceD.setVisible(true);
		}
		myContentPane.repaint();
		
	}
	
	/**
	 * Undoes the players last move
	 * @param theDirection
	 */
	private static void moveReversal(final String theDirection) {
		switch (theDirection) {
		
		case "North":
			myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() + 1);
			break;
		
		case "South":
			myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() - 1);
			break;
			
		case "East":
			myPlayer.setLocation(myPlayer.getLocationX() - 1, myPlayer.getLocationY());
			break;
			
		case "West":
			myPlayer.setLocation(myPlayer.getLocationX() + 1, myPlayer.getLocationY());
			break;
		
		}
	}
	
	/**
	 * Displays the maze
	 * Note: Boundaries are currently not correct
	 * This is because of how the maze is being displayed
	 * This must be fixed later because if you make the player go too far
	 * It triggers a NullPointerException
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
				if (doorChk("North") && !myDoorChk.isLocked()) {
					doorSetup("North");
					myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() - 1);
					myLastDirection = "North";
					myMazePanel.repaint();
				}
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
				if (doorChk("East") && !myDoorChk.isLocked()) {
				doorSetup("East");
				myPlayer.setLocation(myPlayer.getLocationX() + 1, myPlayer.getLocationY());
				myLastDirection = "East";
				myMazePanel.repaint();
				}
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
				if (doorChk("South") && !myDoorChk.isLocked()) {
				doorSetup("South");
				myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() + 1);
				myLastDirection = "South";
				myMazePanel.repaint();
				}
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
				if(doorChk("West") && !myDoorChk.isLocked()) {
				doorSetup("West");
				myPlayer.setLocation(myPlayer.getLocationX() - 1, myPlayer.getLocationY());
				myLastDirection = "West";
				myMazePanel.repaint();
				}
			}
		}
	}
	
	/**
	 * Saves the game by calling the SaveLoad class
	 * @author Roland Hanson, Richard Le
	 *
	 */
	private class saveMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String wd = System.getProperty("user.dir");
			
			JFileChooser fc = new JFileChooser(wd);
			fc.setFileFilter(new FileNameExtensionFilter(".bin", "bin"));
			int rc = fc.showDialog(myMntmSave, "Save");
			
			if(rc == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String fileName = file.getAbsolutePath() + ".bin";
				
				SaveData data = new SaveData();
				data.playerData = myPlayer;
				data.mazeData = myMaze;
				
				try {
					SaveLoad.save(data, fileName);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Loads the game via .bin file serialization 
	 * thru calling SaveLoad static method load()
	 * 
	 * DOES NOT WORK, need to fix gameplay first
	 * @author Richard Le
	 *
	 */
	private class loadMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String wd = System.getProperty("user.dir");
			
			JFileChooser fc = new JFileChooser(wd);
			fc.setFileFilter(new FileNameExtensionFilter(".bin", "bin"));
			int rc = fc.showDialog(myMntmLoad, "Load");
			
			if(rc == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String fileName = file.getAbsolutePath();
				
				try {
					SaveData data = (SaveData) SaveLoad.load(fileName);
					myPlayer.setLocation(data.playerData.getLocationX(), data.playerData.getLocationY());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
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
			myBtnStart.setVisible(false);
			myMazePanel.setVisible(true);
			myMazePanel.repaint();
		}
	}
	
	/**
	 * Checks to see if the selected choice is correct
	 * Also resets display for the next door
	 * @author Roland Hanson
	 *
	 */
	private class RdbtnChoiceActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myRdbtnChoiceA == e.getSource()) {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceA.getText())) {
					moveReversal(myLastDirection);
					myMazePanel.repaint();
				}
				myRdbtnChoiceA.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
			} else if (myRdbtnChoiceB == e.getSource()) {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceB.getText())) {
					moveReversal(myLastDirection);
					myMazePanel.repaint();
				} 
				myRdbtnChoiceB.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
			} else if (myRdbtnChoiceC == e.getSource()) {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceC.getText())) {
					moveReversal(myLastDirection);
					myMazePanel.repaint();
				} 
				myRdbtnChoiceC.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
			} else {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceD.getText())) {
					moveReversal(myLastDirection);
					myMazePanel.repaint();
				}
				myRdbtnChoiceD.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
			}
		}
	}
	
}