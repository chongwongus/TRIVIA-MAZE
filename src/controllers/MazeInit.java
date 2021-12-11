package controllers;

import javax.swing.JTextField;

import models.*;

/**
 * Initializes the maze and manages the components of the maze
 * @author Roland Hanson
 *
 */
public class MazeInit {
	
	Maze myMaze;
	//private String myLastDirection;
	Door myDoor;
	Player myPlayer = new Player();
	Room[][] myRooms;
	
	public MazeInit() {
		
	}
	
	public MazeInit(final Maze theMaze, final Door theDoor, final Player thePlayer, final Room[][] theRooms) {
		myMaze = theMaze;
		myDoor = theDoor;
		myPlayer = thePlayer;
		myRooms = theRooms;
	}
	
	public Maze getMaze() {
		return this.myMaze;
	}
	
	public Player getPlayer() {
		return this.myPlayer;
	}
	
	public Door getDoor() {
		return this.myDoor;
	}
	
	public void initialize() {
		myMaze.initializeRooms();
		myMaze.initializeRoomQuestions();
		myRooms = myMaze.getMyMaze();
		myPlayer.setLocation(1, 1);
	}
	
	/**
	 * Sets up the size of the maze based on what was
	 * entered into the text fields
	 * Note: Any number entered that is greater than 4 causes an exception to be thrown
	 * This is most likely because of our database size since thats when the error occurs  
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
	 * Checks to see if there is a door given the player's current location
	 * @param theDirection
	 * @return
	 */
	public boolean doorChk(final String theDirection) {
		if (myRooms[myPlayer.getLocationY()][myPlayer.getLocationX()].hasDoor(theDirection)) {
			myDoor = myRooms[myPlayer.getLocationY()][myPlayer.getLocationX()].getDoor(theDirection);
			return true;
		} 
		return false;
	}
	
	public void doorBtnInit() {
		
	}
	
	
}
