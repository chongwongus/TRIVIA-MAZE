package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import models.Room;
import models.QuestionDB;
import models.Question_Answer;

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
	
	public int getMyRow() {
		return this.myRow;
	}
	public int getMyCol() {
		return this.myCol;
	}
	
	/**
	 * initialize all the Rooms in the Maze array. 
	 * Starts indexing at 1 since we have set [0,0] and [ROW+1, COL+1]
	 * as our outer boundaries.
	 * Sets the X and Y coordinates of the individual rooms in the Maze.
	 */
	public void initializeRooms() {
		for(int i = 1; i < myRow; i++) {
			for(int j = 1; j < myCol; j++) {
				myMaze[i][j] = new Room();
				myMaze[i][j].setX(i);
				myMaze[i][j].setY(j);
			}
		}
	}
	
	/**
	 * initializes all trivia Questions into Rooms.
	 * Uses methods from QuestionDB to create the list.
	 */
	public void initializeRoomQuestions() {
		List<Question_Answer> allTriviaQ = new ArrayList<>();
		Stream.of(QuestionDB.createMultipleChoiceTrivia(), QuestionDB.createTrueFalseTrivia()).forEach(allTriviaQ::addAll);
		int listIndex = 0;

		//shuffling allTriviaQ
		Collections.shuffle(allTriviaQ);
		
		for(int i = 1; i < myRow; i++) {
			for(int j = 1; j < myCol; j++) {
				myMaze[i][j].createDoor(allTriviaQ.get(listIndex));
				listIndex++;
			}
		}

	}
	
}
