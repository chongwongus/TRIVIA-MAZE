package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Door;

/**
 * The room to hold all the doors in the Maze
 * 
 * @author Jason Hsu
 *
 */
public final class Room implements Serializable {

	private int myX;
	private int myY;
	private Map<String, Door> myDoorMap;
	private Door myDoor;
	private char myId;

	/**
	 * Default constructor
	 */
	public Room() {
		myDoorMap = new HashMap<String, Door>();
		myDoor = new Door();
	}

	/**
	 * Room to hold doors and know its own position in the Maze
	 * 
	 * @param theX x coordinate
	 * @param theY y coordinate
	 */
	public Room(final int theX, final int theY) {
		this();
		myX = theX;
		myY = theY;

	}

	/**
	 * Creates a new door based on the question and answer
	 * 
	 * @param theDirection  String direction key
	 * @param theRoomTrivia Question_Answer attached to the door.
	 */
	public void createDoor(final String theDirection, final Question_Answer theRoomTrivia) {
		myDoorMap.put(theDirection, new Door(theRoomTrivia));
	}

	/**
	 * Creates a question for the room's door
	 * 
	 * @param theRoomTrivia
	 */
	public void createSingle(final Question_Answer theRoomTrivia) {
		myDoor.setQA(theRoomTrivia);
	}

	/**
	 * Uses a reference to a previously created door.
	 * 
	 * @param theDirection String direction key
	 * @param theDoor      Existing door
	 */
	public void createExistingDoor(final String theDirection, final Door theDoor) {
		myDoorMap.put(theDirection, theDoor);
	}

	/**
	 * Returns the door given the direction
	 * 
	 * @param theDirection
	 * @return door from myDoorMap
	 */
	public Door getDoor(final String theDirection) {
		return myDoorMap.get(theDirection);
	}

	/**
	 * Returns the room class's door
	 * 
	 * @return myDoor
	 */
	public Door getRealDoor() {
		return myDoor;
	}

	/**
	 * Checks if the room has a door in the given direction
	 * 
	 * @param theDirection
	 * @return true if there is a door, false otherwise
	 */
	public boolean hasDoor(final String theDirection) {
		return myDoorMap.containsKey(theDirection);
	}

	/**
	 * Returns the room's x value
	 * 
	 * @return
	 */
	public int getX() {
		return this.myX;
	}

	/**
	 * Returns the room's y value
	 * 
	 * @return
	 */
	public int getY() {
		return this.myY;
	}

	/**
	 * Returns the doors that are in the room
	 * 
	 * @return
	 */
	public Door[] getDoors() {
		Door[] doors = new Door[myDoorMap.size()];

		for (Map.Entry<String, Door> pair : myDoorMap.entrySet()) {
			int i = 0;
			doors[i] = pair.getValue();
			i++;
		}

		return doors;
	}

	/**
	 * Returns the room's ID
	 * 
	 * @return myID
	 */
	public char getId() {
		return this.myId;
	}

	/**
	 * Sets the room's x value to the given x value
	 * 
	 * @param theX
	 */
	public void setX(final int theX) {
		this.myX = theX;
	}

	/**
	 * Sets the room's y value to the given y value
	 * 
	 * @param theY
	 */
	public void setY(final int theY) {
		this.myY = theY;
	}

	/**
	 * Sets the room's ID to the given ID
	 * 
	 * @param theId
	 */
	public void setId(final char theId) {
		this.myId = theId;
	}

}
