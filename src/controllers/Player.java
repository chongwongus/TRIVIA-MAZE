package controllers;

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

    private MazeInit myMazeInit;
    
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

	public void moveNorth() {
		setLocation(myPlayerX, myPlayerY - 1);
	}

    public void moveEast() {
        setLocation(myPlayerX + 1, myPlayerY);
    }

    public void moveSouth() {
        setLocation(myPlayerX, myPlayerY + 1);
    }

    public void moveWest() {
        setLocation(myPlayerX - 1, myPlayerY);
    }

    /**
	 * Undoes the players last move
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
    
}