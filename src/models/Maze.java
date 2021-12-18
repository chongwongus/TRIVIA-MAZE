package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import models.Room;
import models.QuestionDB;
import models.Question_Answer;

/**
 * The Maze the player will play on to hold rooms that hold doors that hold questions.
 * @author Jason Hsu
 *
 */
public final class Maze implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private int myRow;
	private int myCol;
	private Room[][] myMaze;
	private List<Question_Answer> allTriviaQ;


	/**
	 * Default constructor, sets up the Maze
	 */
	public Maze() {
		myRow = 4;
		myCol = 4;
		myMaze = new Room[myRow + 2][myCol + 2];
	}

	/**
	 * Sets up the maze based on the given row and column values
	 * @param theRow
	 * @param theCol
	 */
	public Maze(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
		myMaze = new Room[myRow + 2][myCol + 2];
	}

	/**
	 * Returns the amount of rows in the maze
	 * @return
	 */
	public int getMyRow() {
		return this.myRow;
	}

	/**
	 * Returns the amount of columns in the maze
	 * @return
	 */
	public int getMyCol() {
		return this.myCol;
	}
	
	/**
	 * Method for the GUI to see the room array for the questions
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
		
		setStartExit();
	}
	
	/**
	 * Sets up the exit location
	 */
	private void setStartExit() {
		int randomStartRow = (int)(Math.random() * myRow) + 1;
		int randomStartCol = (int)(Math.random() * myCol) + 1;
		
		myMaze[randomStartRow][randomStartCol].setId('S');
		
		int randomExitRow = (int)(Math.random() * myRow) + 1;
		int randomExitCol = (int)(Math.random() * myCol) + 1;
		
		while (randomExitRow == randomStartRow && randomExitRow == randomStartRow) {
			randomExitRow = (int)(Math.random() * myRow) + 1;
			randomExitCol = (int)(Math.random() * myCol) + 1;
		}
		
		myMaze[randomExitRow][randomExitCol].setId('E');
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

		Collections.shuffle(allTriviaQ);

		for (int i = 1; i <= myCol; i++) {
			for (int j = 1; j <= myRow; j++) {
				myMaze[i][j].createSingle(allTriviaQ.get(listIndex));
				listIndex++;
			}
		}
	}
}
