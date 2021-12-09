package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public final class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	private int myX;
	private int myY;
	private Map<String, Door> myDoorMap; 
<<<<<<< HEAD
	private Door door;
	private boolean isLocked;
=======
>>>>>>> origin/RolandHansonDev
	
	public Room() {
		myDoorMap = new HashMap<String, Door>();
	}
	
	/**
	 * I'm thinking of using the row and col to figure out what position the room is in
	 * to determine how many doors are needed (e.g corner edge needs 2, free needs 4, side
	 * needs 3. 
	 * @param theX
	 * @param theY
	 * @param theRow 
	 * @param theCol
	 */
	public Room(int theX, int theY, int theRow, int theCol) {
		this();
		myX = theX;
		myY = theY;
		isLocked = false;
	}
	public void setLock() {
		if (!door.isOpen()) {
			isLocked = true;
		}
	}
	public boolean isLocked() {
		return isLocked;
	}
	
	public void createDoor(String theDirection, Question_Answer theRoomTrivia) {
		myDoorMap.put(theDirection, new Door(theRoomTrivia));
	}
	
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
	public void setX(int theX) {
		this.myX = theX;
	}
	public void setY(int theY) {
		this.myY = theY;
	}
	
}
