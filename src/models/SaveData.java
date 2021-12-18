package models;

import java.io.Serializable;

/**
 * Saves the data for the player and the maze
 * 
 * @author Richard Le
 *
 */
public class SaveData implements Serializable {

	private static final long serialVersionUID = 1L;

	public Player playerData;
	public Maze mazeData;
}
