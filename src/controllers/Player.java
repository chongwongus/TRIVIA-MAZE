package controllers;

/**
 * Walks through the maze
 * @author Roland Hanson
 *
 */
public class Player {
	
	private int myPlayerX;
	private int myPlayerY;
	
	/**
	 * Default constructor
	 */
	public Player() {

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
	
	public void setLocation(final int x, final int y) {
		myPlayerX = x;
		myPlayerY = y;
	}
	
	public int getLocationX() {
		return this.myPlayerX;
	}
	
	public int getLocationY() {
		return this.myPlayerY;
	}

	
}
