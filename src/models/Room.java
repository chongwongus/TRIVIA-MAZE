package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Door;

public final class Room {

	private int myX;
	private int myY;
	private Map<String, Door> myDoorMap; 
	private Door door;
	
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
		
	}
	
	public void createDoor(Question_Answer roomTrivia) {
		door = new Door(roomTrivia);
	}
	public Door getDoor() {
		return this.door;
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
