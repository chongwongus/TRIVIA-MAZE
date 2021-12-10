package Tests;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import models.Room;
import models.Door;
import models.QuestionDB;
import models.Question_Answer;
import models.Maze;

public class MazeTest extends TestCase{
	
	private Maze myMaze;
	
	public void testConstructor () {
		myMaze = new Maze();
		assertEquals(4, myMaze.getMyRow());
		assertEquals(4, myMaze.getMyCol());
		
		myMaze = new Maze(5, 5);
		assertEquals(5, myMaze.getMyRow());
		assertEquals(5, myMaze.getMyCol());
	}
	
	public void testInitializeRooms() {
		myMaze = new Maze();
		myMaze.initializeRooms();
		
		for (int i = 1; i <= myMaze.getMyRow(); i++) {
			for (int j = 1; j <= myMaze.getMyCol(); j++) {
				assertEquals(j, myMaze.getMyMaze()[i][j].getX());
				assertEquals(i, myMaze.getMyMaze()[i][j].getY());
			}
		}
	}
	
	public void testInitializeRoomQuestions() {
		myMaze = new Maze(4,4);
		myMaze.initializeRooms();
		myMaze.initializeRoomQuestions();
		
		// DB checking
		Door[] testDoors = myMaze.getMyMaze()[1][1].getDoors();
		String checkQuestion = testDoors[0].getQuestion();
		String checkAnswer = testDoors[0].getAnswer();
		String[] checkChoices = testDoors[0].getChoices();
		
		System.out.println(checkQuestion);
		
		for (int i = 0; i < checkChoices.length; i++) {
			System.out.println(checkChoices[i]);
		}
		
		System.out.println(checkAnswer);
		
		// Testing if it creates the same door.
		myMaze = new Maze(4,4);
		myMaze.initializeRooms();
		myMaze.initializeRoomQuestions();
		
		Door testDoor1 = myMaze.getMyMaze()[1][1].getDoor("East");
		Door testDoor2 = myMaze.getMyMaze()[1][2].getDoor("West");
		assertEquals(testDoor1,testDoor2);
		

	}
}
