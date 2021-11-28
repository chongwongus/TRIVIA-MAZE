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
	 * Method for the GUI to see the room array for the questions
	 * Note: There might be a better way of doing this, so this might be temporary
	 * @return myMaze
	 */
	public Room[][] getMyMaze(){
		return this.myMaze;
	}
	
	/**
	 * initialize all the Rooms in the Maze array. Starts indexing at 1 since we
	 * have set [0,0] and [ROW+1, COL+1] as our outer boundaries. Sets the X and Y
	 * coordinates of the individual rooms in the Maze.
	 */
	public void initializeRooms() {
		for (int i = 1; i <= myCol; i++) {
			for (int j = 1; j <= myRow; j++) {
				myMaze[i][j] = new Room();
				myMaze[i][j].setX(j);
				myMaze[i][j].setY(i);
			}
		}
	}

	/**
	 * initializes all trivia Questions into Rooms. Uses methods from QuestionDB to
	 * create the list.
	 */
	public void initializeRoomQuestions() {
		List<Question_Answer> allTriviaQ = new ArrayList<>();
		Stream.of(QuestionDB.createMultipleChoiceTrivia(), QuestionDB.createTrueFalseTrivia())
				.forEach(allTriviaQ::addAll);
		int listIndex = 0;

		// shuffling allTriviaQ
		Collections.shuffle(allTriviaQ);

		/**
		 * Note the != null is checking for edge cases since the 2D array will
		 * initialize all values originally as null (I think).
		 * 
		 * The next if statements are to check if the previously created rooms have
		 * doors in that direction. If it does, then create a REFERENCE to the previous
		 * door otherwise make a new door.
		 * 
		 * If I'm understanding correctly too I made a mistake because the two if
		 * statements checking for if it has a door to the North and West (Bottom two if
		 * statements nested) will never need to be checked since we're making doors
		 * starting from the top left moving to the right down.
		 */
		for (int i = 1; i <= myCol; i++) {
			for (int j = 1; j <= myRow; j++) {

				if (myMaze[i - 1][j] != null) {
					if (myMaze[i - 1][j].hasDoor("South")) {
						myMaze[i][j].createExistingDoor("North", myMaze[i - 1][j].getDoor("South"));
					} else {
						myMaze[i][j].createDoor("North", allTriviaQ.get(listIndex));
						listIndex++;
					}
				}

				if (myMaze[i][j - 1] != null) {
					if (myMaze[i][j - 1].hasDoor("East")) {
						myMaze[i][j].createExistingDoor("West", myMaze[i][j - 1].getDoor("East"));
					} else {
						myMaze[i][j].createDoor("West", allTriviaQ.get(listIndex));
						listIndex++;
					}
				}

				if (myMaze[i + 1][j] != null) {
					if (myMaze[i + 1][j].hasDoor("North")) {
						myMaze[i][j].createExistingDoor("South", myMaze[i + 1][j].getDoor("North"));
					} else {
						myMaze[i][j].createDoor("South", allTriviaQ.get(listIndex));
						listIndex++;
					}
				}

				if (myMaze[i][j + 1] != null) {
					if (myMaze[i][j + 1].hasDoor("West")) {
						myMaze[i][j].createExistingDoor("East", myMaze[i][j + 1].getDoor("West"));
					} else {
						myMaze[i][j].createDoor("East", allTriviaQ.get(listIndex));
						listIndex++;
					}
				}
			}
		}
	}
}
