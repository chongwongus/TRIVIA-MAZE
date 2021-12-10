package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Door;

/**
 * The room to hold all the doors in the Maze
 * @author Jason Hsu
 *
 */
public final class Room implements Serializable {

	private int myX;
	private int myY;
	private Map<String, Door> myDoorMap; 
	private char myId;
	
	public Room() {
		myDoorMap = new HashMap<String, Door>();
	}
	
	/**
	 * Room to hold doors and know its own position in the Maze
	 * @param theX x coordinate
	 * @param theY y coordinate
	 */
	public Room(int theX, int theY) {
		this();
		myX = theX;
		myY = theY;
		
	}
	
	/**
	 * Creates a new door based on the question and answer
	 * @param theDirection String direction key
	 * @param theRoomTrivia Question_Answer attached to the door.
	 */
	public void createDoor(String theDirection, Question_Answer theRoomTrivia) {
		myDoorMap.put(theDirection, new Door(theRoomTrivia));
	}
	
	/**
	 * Uses a reference to a previously created door.
	 * @param theDirection String direction key
	 * @param theDoor Existing door 
	 */
	public void createExistingDoor(String theDirection, Door theDoor) {
		myDoorMap.put(theDirection, theDoor);
	}
	
	public Door getDoor(String theDirection) {
		return myDoorMap.get(theDirection);
	}
	
	public boolean hasDoor(String theDirection) {
		return myDoorMap.containsKey(theDirection);
	}
	
	public int getX() {
		return this.myX;
	}
	public int getY() {
		return this.myY;
	}
	
	public Door[] getDoors() {
		Door[] doors = new Door[myDoorMap.size()];
		
		for (Map.Entry<String, Door> pair : myDoorMap.entrySet()) {
			int i = 0;
			doors[i] = pair.getValue();
			i++;
		}
		
		return doors;
	}
	
	public char getId() {
		return this.myId;
	}
	
	public void setX(int theX) {
		this.myX = theX;
	}
	
	public void setY(int theY) {
		this.myY = theY;
	}
	
	public void setId(char theId) {
		this.myId = theId;
	}
	
}
