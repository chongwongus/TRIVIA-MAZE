package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Room {

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
	public Room(int theX, int theY, int theRow, int theCol) {
		this();
		myX = theX;
		myY = theY;
		
	}
	
}
