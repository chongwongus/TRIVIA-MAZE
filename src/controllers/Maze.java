package controllers;

import java.util.Scanner;

import models.Room;

public final class Maze {
	
	private int rmNum;
	private Room[][] mazeRms;
	
	Scanner input = new Scanner(System.in); // For console implementation
	
	/**
	 * Sets up the Maze
	 */
	public Maze() {
		int x = 4;
		int y = 4;
		System.out.println("Please enter amount of rooms (n x n) for the maze (Default is 4 x 4): ");
		while (input.hasNext()) {
			x = input.nextInt();
			y = input.nextInt();
		}
		mazeRms = new Room[x][y];
		
	}
	
	
}
