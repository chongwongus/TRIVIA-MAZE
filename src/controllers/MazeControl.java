package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.*;
import viewers.TriviaMazeGUI;

/**
 * Initializes and manages the components of the maze
 * 
 * @author Roland Hanson
 *
 */
public class MazeControl {

	Maze myMaze /* = TriviaMazeGUI.myMaze */;
	static Door myDoor;
	Player myPlayer = new Player();
	Room[][] myRooms;

	/**
	 * Default constructor
	 */
	public MazeControl() {

	}

	/**
	 * Constructs the MazeControl class for the GUI
	 * 
	 * @param theMaze
	 * @param theDoor
	 * @param thePlayer
	 * @param theRooms
	 */
	public MazeControl(final Maze theMaze, final Door theDoor, final Player thePlayer, final Room[][] theRooms) {
		myMaze = theMaze;
		myDoor = theDoor;
		myPlayer = thePlayer;
		myRooms = theRooms;
	}

	/**
	 * Returns
	 * 
	 * @return myMaze
	 */
	public Maze getMaze() {
		return this.myMaze;
	}

	/**
	 * 
	 * @return myPlayer
	 */
	public Player getPlayer() {
		return this.myPlayer;
	}

	/**
	 * 
	 * @return myDoor
	 */
	public Door getDoor() {
		return this.myDoor;
	}

	/**
	 * 
	 * @return myRooms
	 */
	public Room[][] getRooms() {
		return this.myRooms;
	}

	/**
	 * Initializes the maze
	 */
	public void initialize() {
		int x = 0;
		int y = 0;
		myMaze.initializeRooms();
		myMaze.initializeRoomQuestions();
		x = ThreadLocalRandom.current().nextInt(1, myMaze.getMyRow() + 1);
		y = ThreadLocalRandom.current().nextInt(1, myMaze.getMyRow() + 1);
		myRooms = myMaze.getMyMaze();
		myPlayer.setLocation(x, y);
	}

	/**
	 * Sets up the size of the maze based on what was entered into the text fields
	 */
	public void setMazeSize(final JTextField txtRow, final JTextField txtCol) {
		if (isInt(txtRow.getText()) && isInt(txtCol.getText())) {
			int row = Integer.parseInt((txtRow.getText()));
			int col = Integer.parseInt((txtCol.getText()));
			if (row > 4 && col > 4) {
				myMaze = new Maze(row, col);
			}
		} else {
			myMaze = new Maze();
		}
	}

	/**
	 * Checks if a String can be parsed into an int
	 * 
	 * @param theString
	 * @return
	 */
	private static boolean isInt(final String theString) {
		try {
			Integer.parseInt(theString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Sets up a question from a door (based on the players location) to be shown in
	 * the GUI
	 * 
	 * @param theDirection
	 * 
	 */
	private static void doorSetup(final String theDirection) {
		myDoor = TriviaMazeGUI.myMazeCon.getDoor();
		String[] choices = myDoor.getChoices();
		TriviaMazeGUI.myLblQuestion.setText(myDoor.getQuestion());
		TriviaMazeGUI.myLblQuestion.setVisible(true);
		TriviaMazeGUI.myLblResult.setVisible(false);
		TriviaMazeGUI.myLblResult.setText("Correct!");
		if (choices.length == 2) {
			TriviaMazeGUI.myRdbtnChoiceA.setText(choices[0]);
			TriviaMazeGUI.myRdbtnChoiceB.setText(choices[1]);
			TriviaMazeGUI.myRdbtnChoiceA.setVisible(true);
			TriviaMazeGUI.myRdbtnChoiceB.setVisible(true);
			TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
		} else {
			TriviaMazeGUI.myRdbtnChoiceA.setText(choices[0]);
			TriviaMazeGUI.myRdbtnChoiceB.setText(choices[1]);
			TriviaMazeGUI.myRdbtnChoiceC.setText(choices[2]);
			TriviaMazeGUI.myRdbtnChoiceD.setText(choices[3]);
			TriviaMazeGUI.myRdbtnChoiceA.setVisible(true);
			TriviaMazeGUI.myRdbtnChoiceB.setVisible(true);
			TriviaMazeGUI.myRdbtnChoiceC.setVisible(true);
			TriviaMazeGUI.myRdbtnChoiceD.setVisible(true);
		}
		TriviaMazeGUI.myContentPane.repaint();
	}

	/**
	 * Sets the result label to "Correct!" when passing through previously cleared
	 * rooms
	 */
	private static void correctPass() {
		TriviaMazeGUI.myLblQuestion.setVisible(false);
		TriviaMazeGUI.myLblResult.setVisible(true);
		TriviaMazeGUI.myLblResult.setText("Correct!");
		TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
		TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
		TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
		TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
	}

	/**
	 * Checks to see if there is a door to the north of the player's current
	 * location
	 * 
	 * @return true if a door is present
	 */
	public boolean doorChkN() {
		myDoor = myRooms[myPlayer.getLocationY() - 1][myPlayer.getLocationX()].getRealDoor();
		return true;
	}

	/**
	 * Checks to see if there is a door to the east of the player's current location
	 * 
	 * @return true if a door is present
	 */
	public boolean doorChkE() {
		myDoor = myRooms[myPlayer.getLocationY()][myPlayer.getLocationX() + 1].getRealDoor();
		return true;
	}

	/**
	 * Checks to see if there is a door to the south of the player's current
	 * location
	 * 
	 * @return true if a door is present
	 */
	public boolean doorChkS() {
		myDoor = myRooms[myPlayer.getLocationY() + 1][myPlayer.getLocationX()].getRealDoor();
		return true;
	}

	/**
	 * Checks to see if there is a door to the west of the player's current location
	 * 
	 * @return true if a door is present
	 */
	public boolean doorChkW() {
		myDoor = myRooms[myPlayer.getLocationY()][myPlayer.getLocationX() - 1].getRealDoor();
		return true;
	}

	/**
	 * Checks if the player found the exit
	 * 
	 */
	private void checkEnd() {
		if (TriviaMazeGUI.myMazeCon.getRooms()[myPlayer.getLocationY()][myPlayer.getLocationX()].getId() == 'E') {
			TriviaMazeGUI.myLblResult.setText("You found the exit!");
			playWinSound();
			TriviaMazeGUI.myLblResult.setVisible(true);
			TriviaMazeGUI.myLblQuestion.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
		}
	}

	/**
	 * Checks if the player's health is zero
	 * 
	 * @param theHealth
	 * 
	 */
	private void chkHealth(final JProgressBar theHealth) {
		if (theHealth.getValue() == 0) {
			TriviaMazeGUI.myLblResult.setText("You ran out of health!");
			playLoseSound();
			TriviaMazeGUI.myLblResult.setVisible(true);
			TriviaMazeGUI.myLblQuestion.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
			TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
			TriviaMazeGUI.myBtnN.setEnabled(false);
			TriviaMazeGUI.myBtnE.setEnabled(false);
			TriviaMazeGUI.myBtnS.setEnabled(false);
			TriviaMazeGUI.myBtnW.setEnabled(false);
		}
	}

	/**
	 * Checks to see if the player is trapped
	 */
	private void chkTrapped() {
		int doors = 0;
		if (myPlayer.getLocationY() > 1) {
			if (TriviaMazeGUI.myMazeCon.doorChkN() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
				doors++;
			}
		}
		if (myPlayer.getLocationX() < TriviaMazeGUI.myMaze.getMyRow()) {
			if (TriviaMazeGUI.myMazeCon.doorChkE() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
				doors++;
			}
		}
		if (myPlayer.getLocationY() < TriviaMazeGUI.myMaze.getMyCol()) {
			if (TriviaMazeGUI.myMazeCon.doorChkS() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
				doors++;
			}
		}
		if (myPlayer.getLocationX() > 1) {
			if (TriviaMazeGUI.myMazeCon.doorChkW() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
				doors++;
			}
		}
		if (doors == 0) {
			playLoseSound();
			TriviaMazeGUI.myLblResult.setText("You're trapped!");
			TriviaMazeGUI.myBtnN.setEnabled(false);
			TriviaMazeGUI.myBtnE.setEnabled(false);
			TriviaMazeGUI.myBtnS.setEnabled(false);
			TriviaMazeGUI.myBtnW.setEnabled(false);
		}
	}

	/**
	 * Plays the incorrect answer sound when called Code from stackoverflow
	 * (modified)
	 * 
	 */
	private void playHitSound() {
		try {
			File file = new File("Incorrect.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/**
	 * Plays the correct answer sound when called Code from stackoverflow (modified)
	 * 
	 */
	private void playCorrectSound() {
		try {
			File file = new File("Correct.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/**
	 * Plays the win sound when called Code from stackoverflow (modified)
	 * 
	 */
	private void playWinSound() {
		try {
			File file = new File("Win.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/**
	 * Plays the lose sound when called Code from stackoverflow (modified)
	 * 
	 */
	private void playLoseSound() {
		try {
			File file = new File("Lose.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/**
	 * Makes the player go north
	 * 
	 * @author Roland Hanson
	 * 
	 */
	public class GoNorth implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (myPlayer.getLocationY() > 1) {
				if (TriviaMazeGUI.myMazeCon.doorChkN() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {

					if (!TriviaMazeGUI.myMazeCon.getDoor().hasPassedThrough()) {
						doorSetup("North");
						TriviaMazeGUI.myBtnN.setEnabled(false);
						TriviaMazeGUI.myBtnE.setEnabled(false);
						TriviaMazeGUI.myBtnS.setEnabled(false);
						TriviaMazeGUI.myBtnW.setEnabled(false);
					} else {
						correctPass();
					}

					myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() - 1);

					TriviaMazeGUI.myLastDirection = "North";
					checkEnd();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
					TriviaMazeGUI.myMazePanel.repaint();
				}
			} else {
				TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
				TriviaMazeGUI.myMazePanel.repaint();
			}
		}
	}

	/**
	 * Makes the player go east
	 * 
	 * @author Roland Hanson
	 * 
	 */
	public class GoEast implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationX() < TriviaMazeGUI.myMaze.getMyRow()) {
				if (TriviaMazeGUI.myMazeCon.doorChkE() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
					if (!TriviaMazeGUI.myMazeCon.getDoor().hasPassedThrough()) {
						doorSetup("East");
						TriviaMazeGUI.myBtnN.setEnabled(false);
						TriviaMazeGUI.myBtnE.setEnabled(false);
						TriviaMazeGUI.myBtnS.setEnabled(false);
						TriviaMazeGUI.myBtnW.setEnabled(false);
					} else {
						correctPass();
					}

					myPlayer.setLocation(myPlayer.getLocationX() + 1, myPlayer.getLocationY());
					TriviaMazeGUI.myLastDirection = "East";
					checkEnd();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
					TriviaMazeGUI.myMazePanel.repaint();
				}
			} else {
				TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
				TriviaMazeGUI.myMazePanel.repaint();
			}
		}
	}

	/**
	 * Makes the player go south
	 * 
	 * @author Roland Hanson
	 * 
	 */
	public class GoSouth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationY() < TriviaMazeGUI.myMaze.getMyCol()) {
				if (TriviaMazeGUI.myMazeCon.doorChkS() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
					if (!TriviaMazeGUI.myMazeCon.getDoor().hasPassedThrough()) {
						doorSetup("South");
						TriviaMazeGUI.myBtnN.setEnabled(false);
						TriviaMazeGUI.myBtnE.setEnabled(false);
						TriviaMazeGUI.myBtnS.setEnabled(false);
						TriviaMazeGUI.myBtnW.setEnabled(false);
					} else {
						correctPass();
					}

					myPlayer.setLocation(myPlayer.getLocationX(), myPlayer.getLocationY() + 1);
					TriviaMazeGUI.myLastDirection = "South";
					checkEnd();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
					TriviaMazeGUI.myMazePanel.repaint();
				}
			} else {
				TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
				TriviaMazeGUI.myMazePanel.repaint();
			}
		}
	}

	/**
	 * Makes the player go west
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class GoWest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myPlayer.getLocationX() > 1) {
				if (TriviaMazeGUI.myMazeCon.doorChkW() && !TriviaMazeGUI.myMazeCon.getDoor().isLocked()) {
					if (!TriviaMazeGUI.myMazeCon.getDoor().hasPassedThrough()) {
						doorSetup("West");
						TriviaMazeGUI.myBtnN.setEnabled(false);
						TriviaMazeGUI.myBtnE.setEnabled(false);
						TriviaMazeGUI.myBtnS.setEnabled(false);
						TriviaMazeGUI.myBtnW.setEnabled(false);
					} else {
						correctPass();
					}
					myPlayer.setLocation(myPlayer.getLocationX() - 1, myPlayer.getLocationY());
					TriviaMazeGUI.myLastDirection = "West";
					checkEnd();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
					TriviaMazeGUI.myMazePanel.repaint();
				}
			} else {
				TriviaMazeGUI.myLblResult.setText("Nope, can't go that way!");
				TriviaMazeGUI.myMazePanel.repaint();
			}
		}
	}

	/**
	 * Checks to see if the selected choice is correct Also resets display for the
	 * next door
	 * 
	 * @author Roland Hanson
	 * 
	 */
	public class AnswerChk implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TriviaMazeGUI.myBtnN.setEnabled(true);
			TriviaMazeGUI.myBtnE.setEnabled(true);
			TriviaMazeGUI.myBtnS.setEnabled(true);
			TriviaMazeGUI.myBtnW.setEnabled(true);

			if (TriviaMazeGUI.myRdbtnChoiceA == e.getSource()) {
				if (!myDoor.checkAnswer(TriviaMazeGUI.myRdbtnChoiceA.getText())) {
					myPlayer.moveReversal(TriviaMazeGUI.myLastDirection);
					TriviaMazeGUI.myLblResult.setText("Wrong!");
					playHitSound();
					TriviaMazeGUI.myHealth = TriviaMazeGUI.myHealthBar.getValue() - 25;
					TriviaMazeGUI.myHealthBar.setValue(TriviaMazeGUI.myHealth);
					chkHealth(TriviaMazeGUI.myHealthBar);
					chkTrapped();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					playCorrectSound();
				}
				TriviaMazeGUI.myRdbtnChoiceA.setSelected(false);
				TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
				TriviaMazeGUI.myLblQuestion.setVisible(false);
				TriviaMazeGUI.myLblResult.setVisible(true);
			} else if (TriviaMazeGUI.myRdbtnChoiceB == e.getSource()) {
				if (!myDoor.checkAnswer(TriviaMazeGUI.myRdbtnChoiceB.getText())) {
					myPlayer.moveReversal(TriviaMazeGUI.myLastDirection);
					TriviaMazeGUI.myLblResult.setText("Wrong!");
					playHitSound();
					TriviaMazeGUI.myHealth = TriviaMazeGUI.myHealthBar.getValue() - 25;
					TriviaMazeGUI.myHealthBar.setValue(TriviaMazeGUI.myHealth);
					chkHealth(TriviaMazeGUI.myHealthBar);
					chkTrapped();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					playCorrectSound();
				}
				TriviaMazeGUI.myRdbtnChoiceB.setSelected(false);
				TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
				TriviaMazeGUI.myLblQuestion.setVisible(false);
				TriviaMazeGUI.myLblResult.setVisible(true);
			} else if (TriviaMazeGUI.myRdbtnChoiceC == e.getSource()) {
				if (!myDoor.checkAnswer(TriviaMazeGUI.myRdbtnChoiceC.getText())) {
					myPlayer.moveReversal(TriviaMazeGUI.myLastDirection);
					TriviaMazeGUI.myLblResult.setText("Wrong!");
					playHitSound();
					TriviaMazeGUI.myHealth = TriviaMazeGUI.myHealthBar.getValue() - 25;
					TriviaMazeGUI.myHealthBar.setValue(TriviaMazeGUI.myHealth);
					chkHealth(TriviaMazeGUI.myHealthBar);
					chkTrapped();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					playCorrectSound();
				}
				TriviaMazeGUI.myRdbtnChoiceC.setSelected(false);
				TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
				TriviaMazeGUI.myLblQuestion.setVisible(false);
				TriviaMazeGUI.myLblResult.setVisible(true);
			} else {
				if (!myDoor.checkAnswer(TriviaMazeGUI.myRdbtnChoiceD.getText())) {
					myPlayer.moveReversal(TriviaMazeGUI.myLastDirection);
					TriviaMazeGUI.myLblResult.setText("Wrong!");
					playHitSound();
					TriviaMazeGUI.myHealth = TriviaMazeGUI.myHealthBar.getValue() - 25;
					TriviaMazeGUI.myHealthBar.setValue(TriviaMazeGUI.myHealth);
					chkHealth(TriviaMazeGUI.myHealthBar);
					chkTrapped();
					TriviaMazeGUI.myMazePanel.repaint();
				} else {
					playCorrectSound();
				}
				TriviaMazeGUI.myRdbtnChoiceD.setSelected(false);
				TriviaMazeGUI.myRdbtnChoiceA.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceB.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceC.setVisible(false);
				TriviaMazeGUI.myRdbtnChoiceD.setVisible(false);
				TriviaMazeGUI.myLblQuestion.setVisible(false);
				TriviaMazeGUI.myLblResult.setVisible(true);
			}
		}
	}

	/**
	 * Exits the program
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class ExitGame implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(JFrame.EXIT_ON_CLOSE);
		}
	}

	/**
	 * Initializes the maze once the start button is pressed
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class StartGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Initialize maze & player
			TriviaMazeGUI.myMazeCon = new MazeControl(myMaze, TriviaMazeGUI.myDoorChk, TriviaMazeGUI.myPlayer,
					TriviaMazeGUI.myRoomChk);
			TriviaMazeGUI.myMazeCon.setMazeSize(TriviaMazeGUI.myTxtFieldCol, TriviaMazeGUI.myTxtFieldCol);
			TriviaMazeGUI.myMazeCon.initialize();
			TriviaMazeGUI.myHealth = TriviaMazeGUI.myHealthBar.getValue();
			TriviaMazeGUI.myMaze = TriviaMazeGUI.myMazeCon.getMaze();
			myPlayer = TriviaMazeGUI.myMazeCon.getPlayer();
			TriviaMazeGUI.myLblInstructions.setVisible(false);
			TriviaMazeGUI.myLblOfColumns.setVisible(false);
			TriviaMazeGUI.myLblOfRows.setVisible(false);
			TriviaMazeGUI.myTxtFieldCol.setVisible(false);
			TriviaMazeGUI.myTxtFieldRow.setVisible(false);
			TriviaMazeGUI.myBtnStart.setVisible(false);
			TriviaMazeGUI.myMazePanel.setVisible(true);
			TriviaMazeGUI.myMazePanel.repaint();
			TriviaMazeGUI.myLblOfRows.setVisible(false);
		}
	}

	/**
	 * Saves the game by calling the SaveLoad class
	 * 
	 * @author Roland Hanson, Richard Le
	 * 
	 */
	public class SaveMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String wd = System.getProperty("user.dir");

			JFileChooser fc = new JFileChooser(wd);
			fc.setFileFilter(new FileNameExtensionFilter(".bin", "bin"));
			int rc = fc.showDialog(TriviaMazeGUI.myMntmSave, "Save");

			if (rc == JFileChooser.APPROVE_OPTION) {
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
	 * Loads the game via .bin file serialization thru calling SaveLoad static
	 * method load()
	 * 
	 * @author Richard Le
	 *
	 */
	public class LoadMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String wd = System.getProperty("user.dir");

			JFileChooser fc = new JFileChooser(wd);
			fc.setFileFilter(new FileNameExtensionFilter(".bin", "bin"));
			int rc = fc.showDialog(TriviaMazeGUI.myMntmLoad, "Load");

			if (rc == JFileChooser.APPROVE_OPTION) {
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
	 * Displays the about window
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class AboutMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Trivia Maze \nCreated by: Roland Hanson, Jason Hsu, and Richard Le",
					"About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Displays the instructions
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class InstructionsMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Click on the start button to start the maze \n"
							+ "Use the N,S,E,and W buttons to move the player \nTo progress "
							+ "in the maze, answer the given trivia questions correctly \nTo win, get to the exit "
							+ "before your health runs out by answering questions incorrectly",
					"Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Shows the cheat menu
	 * 
	 * @author Roland Hanson
	 *
	 */
	public class CheatsMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "No cheats here! :)", "Cheats", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
