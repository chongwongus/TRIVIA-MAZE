package controllers;

/**
 * Walks through the maze
 * Attempting the Singleton pattern, might change later
 * @author Roland Hanson
 *
 */
public class Player {

	private static Player uniqueInstance;
	
	private int myPlayerX;
	private int myPlayerY;
	
	/**
	 * Default constructor
	 */
	private Player() {

	}
	
	/**
	 * Initializes player location
	 * @param x
	 * @param y
	 */
	public Player(final int x, final int y) {
		myPlayerX = x;
		myPlayerY = y;
	}
	
	private static Player getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Player();
		}
		return uniqueInstance;
	}
	
	private void setLocation(final int x, final int y) {
		myPlayerX = x;
		myPlayerY = y;
	}
	
	private String getLocation() {
		return myPlayerX + ", " + myPlayerY;
	}
	
}
