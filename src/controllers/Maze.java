package controllers;

import java.util.Scanner;

import models.Room;

public final class Maze {
	
	private int myRow;
	private int myCol;
	private Room[][] myMaze;
	
	
	/**
	 * Sets up the Maze
	 */
	public Maze() {
		myRow = 3;
		myCol = 3;
		myMaze = new Room[myRow + 2][myCol + 2];
	}
	
	public Maze(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
		myMaze = new Room[myRow + 2][myCol + 2];
	}
	
	
	
}
