package models;

import java.io.Serializable;

import controllers.*;

public class SaveData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Player playerData;
	public Maze mazeData;
}
