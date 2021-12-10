package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Door;

public final class Room implements Serializable {

	private int myX;
	private int myY;
	private Map<String, Door> myDoorMap; 
	
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
	public Room(int theX, int theY) {
		this();
		myX = theX;
		myY = theY;
		
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
	
	public Door[] getDoors() {
		Door[] doors = new Door[myDoorMap.size()];
		
		for (Map.Entry<String, Door> pair : myDoorMap.entrySet()) {
			int i = 0;
			doors[i] = pair.getValue();
			i++;
		}
		
		return doors;
	}
	
	public void setX(int theX) {
		this.myX = theX;
	}
	public void setY(int theY) {
		this.myY = theY;
	}
	
}
