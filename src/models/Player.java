package models;

import java.io.Serializable;

/**
 * Walks through the maze
 * 
 * @author Roland Hanson
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	private int myPlayerX;
	private int myPlayerY;

	/**
	 * Default constructor
	 */
	public Player() {

	}

	/**
	 * Initializes player location
	 * 
	 * @param x
	 * @param y
	 */
	public Player(final int x, final int y) {
		myPlayerX = x;
		myPlayerY = y;
	}

	/**
	 * Undoes the players last move
	 * 
	 * @param theDirection
	 */
	public void moveReversal(final String theDirection) {
		switch (theDirection) {

		case "North":
			setLocation(myPlayerX, myPlayerY + 1);
			break;

		case "South":
			setLocation(myPlayerX, myPlayerY - 1);
			break;

		case "East":
			setLocation(myPlayerX - 1, myPlayerY);
			break;

		case "West":
			setLocation(myPlayerX + 1, myPlayerY);
			break;

		}
	}

	/**
	 * Sets the player location
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocation(final int x, final int y) {
		myPlayerX = x;
		myPlayerY = y;
	}

	/**
	 * Returns the player's x value
	 * 
	 * @return
	 */
	public int getLocationX() {
		return this.myPlayerX;
	}

	/**
	 * Returns the player's y value
	 * 
	 * @return
	 */
	public int getLocationY() {
		return this.myPlayerY;
	}
}
