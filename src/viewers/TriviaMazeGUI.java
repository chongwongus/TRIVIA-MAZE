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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controllers.*;
import models.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * GUI for Trivia Maze (currently WIP) Built using windowbuilder
 * @author Roland Hanson
 * @version 0.8.3
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
	DoorButton myDBtnN;
    DoorButton myDBtnE;
    DoorButton myDBtnS;
    DoorButton myDBtnW;
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
	
	static Maze myMaze;
	static MazeInit myMazeInit;
	private String myLastDirection;
	static Door myDoorChk;
	static Player myPlayer = new Player();
	static Room[][] myRoomChk;
	static JRadioButton myRdbtnChoiceA;
	static JRadioButton myRdbtnChoiceB;
	static JRadioButton myRdbtnChoiceC;
	static JRadioButton myRdbtnChoiceD;
	static JLabel myLblQuestion;
	static JLabel myLblResult;
	static JTextField myTxtFieldRow;
	static JTextField myTxtFieldCol;
	JLabel myLblPlayerControls;
	JLabel myLblInstructions;
	JLabel myLblOfRows;
	JLabel myLblOfColumns;

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
		
		myDBtnN = new DoorButton();
        myDBtnN.setVisible(false);
        myDBtnN.addActionListener(new northDoorInfo());
        myDBtnN.setBounds(350, 67, 50, 25);
        myContentPane.add(myDBtnN);

        myDBtnE = new DoorButton();
        myDBtnE.setVisible(false);
        myDBtnE.addActionListener(new eastDoorInfo());
        myDBtnE.setBounds(380, 101, 50, 25);
        myContentPane.add(myDBtnE);

        myDBtnS = new DoorButton();
        myDBtnS.setVisible(false);
        myDBtnS.addActionListener(new southDoorInfo());
        myDBtnS.setBounds(350, 135, 50, 25);
        myContentPane.add(myDBtnS);

        myDBtnW = new DoorButton();
        myDBtnW.setVisible(false);
        myDBtnW.addActionListener(new westDoorInfo());
        myDBtnW.setBounds(320, 101, 50, 25);
        myContentPane.add(myDBtnW);
		
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
		myMntmAbout.addActionListener(new MyMntmAboutActionListener());
		myMnHelp.add(myMntmAbout);
		
		myMntmInstructions = new JMenuItem("Instructions");
		myMntmInstructions.addActionListener(new MyMntmInstructionsActionListener());
		myMnHelp.add(myMntmInstructions);
		
		myMntmCheats = new JMenuItem("Cheats");
		myMntmCheats.addActionListener(new MyMntmCheatsActionListener());
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
		
		myLblResult = new JLabel("Correct!");
		myLblResult.setHorizontalAlignment(SwingConstants.CENTER);
		myLblResult.setBounds(236, 263, 159, 14);
		myLblResult.setVisible(false);
		myContentPane.add(myLblResult);
		
		myTxtFieldRow = new JTextField();
		myTxtFieldRow.setBounds(210, 249, 86, 20);
		myContentPane.add(myTxtFieldRow);
		myTxtFieldRow.setColumns(10);
		
		myTxtFieldCol = new JTextField();
		myTxtFieldCol.setBounds(337, 249, 86, 20);
		myContentPane.add(myTxtFieldCol);
		myTxtFieldCol.setColumns(10);
		
		myLblPlayerControls = new JLabel("Player Controls");
		myLblPlayerControls.setBounds(537, 42, 89, 14);
		myContentPane.add(myLblPlayerControls);
		
		myLblInstructions = new JLabel("Please enter your desired size for the maze (Default is 4x4)");
		myLblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		myLblInstructions.setBounds(143, 131, 351, 31);
		myContentPane.add(myLblInstructions);
		
		myLblOfRows = new JLabel("# of Rows");
		myLblOfRows.setHorizontalAlignment(SwingConstants.CENTER);
		myLblOfRows.setBounds(210, 278, 86, 14);
		myContentPane.add(myLblOfRows);
		
		myLblOfColumns = new JLabel("# of Columns");
		myLblOfColumns.setHorizontalAlignment(SwingConstants.CENTER);
		myLblOfColumns.setBounds(337, 278, 86, 14);
		myContentPane.add(myLblOfColumns);
		myLblQuestion.setVisible(false);
		
	}
	
	/**
	 * Checks to see if there is a door given the player's current location
	 * @param theDirection
	 * @return
	 */
	private static boolean doorChk(final String theDirection) {
		if (myRoomChk[myPlayer.getLocationY()][myPlayer.getLocationX()].hasDoor(theDirection)) {
			myDoorChk = myRoomChk[myPlayer.getLocationY()][myPlayer.getLocationX()].getDoor(theDirection);
			return true;
		} 
		return false;
	}
	
	/**
	 * Sets up a question from a door (based on the players location) to be shown in the GUI 
	 * @param theDirection
	 */
	private static void doorSetup(final String theDirection) {
		myDoorChk = myMazeInit.getDoor();
		String [] choices = myDoorChk.getChoices();
		myLblQuestion.setText(myDoorChk.getQuestion());
		myLblQuestion.setVisible(true);
		myLblResult.setVisible(false);
		myLblResult.setText("Correct!");
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
	 * Displays the maze
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
			for (int i = 27; i < myMaze.getMyRow() * bxWidth; i += bxWidth) {
				for (int j = 0; j < myMaze.getMyCol() * bxHeight; j += bxHeight) {
					g.drawRect(i, j, bxWidth, bxHeight);
				}
			}
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
				if (myMazeInit.doorChk("North") && !myMazeInit.getDoor().isLocked()) {
					if (!myDoorChk.hasPassedThrough()) {
						doorSetup("North");
					}
					myPlayer.moveNorth();
					myLastDirection = "North";
					myMazePanel.repaint();
				} else {
					myLblResult.setText("Nope, can't go that way!");
					myMazePanel.repaint();
				}
			} else {
				myLblResult.setText("Nope, can't go that way!");
				myMazePanel.repaint();
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
				if (myMazeInit.doorChk("East") && !myMazeInit.getDoor().isLocked()) {
					doorSetup("East");
					myPlayer.moveEast();
					myLastDirection = "East";
					myMazePanel.repaint();
				} else {
					myLblResult.setText("Nope, can't go that way!");
					myMazePanel.repaint();
				}
			} else {
				myLblResult.setText("Nope, can't go that way!");
				myMazePanel.repaint();
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
				if (myMazeInit.doorChk("South") && !myMazeInit.getDoor().isLocked()) {
					doorSetup("South");
					myPlayer.moveSouth();
					myLastDirection = "South";
					myMazePanel.repaint();
				} else {
					myLblResult.setText("Nope, can't go that way!");
					myMazePanel.repaint();
				}
			} else {
				myLblResult.setText("Nope, can't go that way!");
				myMazePanel.repaint();
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
				if (myMazeInit.doorChk("West") && !myMazeInit.getDoor().isLocked()) {
					doorSetup("West");
					myPlayer.moveWest();
					myLastDirection = "West";
					myMazePanel.repaint();
				} else {
					myLblResult.setText("Nope, can't go that way!");
					myMazePanel.repaint();
				}
			} else {
				myLblResult.setText("Nope, can't go that way!");
				myMazePanel.repaint();
			}
		}
	}
	
	/**
     * Checks the door to the North
     * 
     * @author Jason Hsu
     *
     */
    private class northDoorInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (myMazeInit.doorChk("North")) {
                doorSetup("North");
                myMazePanel.repaint();
            }
        }
    }

    /**
     * Checks the door to the East
     * 
     * @author Jason Hsu
     *
     */
    private class eastDoorInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (myMazeInit.doorChk("East")) {
                doorSetup("East");
                myMazePanel.repaint();
            }
        }
    }

    /**
     * Checks the door to the South
     * 
     * @author Jason Hsu
     *
     */
    private class southDoorInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (myMazeInit.doorChk("South")) {
                doorSetup("South");
                myMazePanel.repaint();
            }
        }
    }

    /**
     * Checks the door to the West
     * 
     * @author Jason Hsu
     *
     */
    private class westDoorInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (myMazeInit.doorChk("West")) {
                doorSetup("West");
                myMazePanel.repaint();
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
			myMazeInit = new MazeInit(myMaze, myDoorChk, myPlayer, myRoomChk);
			myMazeInit.setMazeSize(myTxtFieldCol, myTxtFieldCol);
			myMazeInit.initialize();
			myMaze = myMazeInit.getMaze();
			myPlayer = myMazeInit.getPlayer();
			myLblInstructions.setVisible(false);
			myLblOfColumns.setVisible(false);
			myLblOfRows.setVisible(false);
			myTxtFieldCol.setVisible(false);
			myTxtFieldRow.setVisible(false);
			myBtnStart.setVisible(false);
			myMazePanel.setVisible(true);
			myDBtnN.setVisible(true);
            myDBtnE.setVisible(true);
            myDBtnS.setVisible(true);
            myDBtnW.setVisible(true);
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
					myPlayer.moveReversal(myLastDirection);
					myLblResult.setText("Wrong!");
					myMazePanel.repaint();
				}
				myRdbtnChoiceA.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
				myLblResult.setVisible(true);
			} else if (myRdbtnChoiceB == e.getSource()) {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceB.getText())) {
					myPlayer.moveReversal(myLastDirection);
					myLblResult.setText("Wrong!");
					myMazePanel.repaint();
				} 
				myRdbtnChoiceB.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
				myLblResult.setVisible(true);
			} else if (myRdbtnChoiceC == e.getSource()) {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceC.getText())) {
					myPlayer.moveReversal(myLastDirection);
					myLblResult.setText("Wrong!");
					myMazePanel.repaint();
				} 
				myRdbtnChoiceC.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
				myLblResult.setVisible(true);
			} else {
				if (!myDoorChk.checkAnswer(myRdbtnChoiceD.getText())) {
					myPlayer.moveReversal(myLastDirection);
					myLblResult.setText("Wrong!");
					myMazePanel.repaint();
				}
				myRdbtnChoiceD.setSelected(false);
				myRdbtnChoiceA.setVisible(false);
				myRdbtnChoiceB.setVisible(false);
				myRdbtnChoiceC.setVisible(false);
				myRdbtnChoiceD.setVisible(false);
				myLblQuestion.setVisible(false);
				myLblResult.setVisible(true);
			}
		}
	}
	
	/**
	 * Displays the about window
	 * @author Roland Hanson
	 *
	 */
	private class MyMntmAboutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Trivia Maze \nCreated by: Roland Hanson, Jason Hsu, and Richard Le"
					, "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Displays the instructions
	 * @author Roland Hanson
	 *
	 */
	private class MyMntmInstructionsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Click on the start button to start the maze \n"
					+ "Use the N,S,E,and W buttons to move the player \nTo progress "
					+ "in the maze, answer the given trivia questions correctly \nTo win, get to the exit "
					+ "without trapping yourself by answering question incorrectly", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Shows the cheat menu (Currently has no cheats)
	 * @author Roland Hanson
	 *
	 */
	private class MyMntmCheatsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}