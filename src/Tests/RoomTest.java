package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Room;
import models.Door;
import models.Question_Answer;

public class RoomTest extends TestCase {

	private Room myRoom;
	
	public void testConstructor() {
		myRoom = new Room(1,3);
		assertEquals(1, myRoom.getX());
		assertEquals(3, myRoom.getY());
		
	}
	
	public void testCreateDoor() {
		myRoom = new Room(1,3);
		String testDirection = "North";
		final String[] testChoices = {"These", "Are", "Test", "Choices"};
		Question_Answer QA = new Question_Answer("Test Question", testChoices, "Test Answer");
		myRoom.createDoor(testDirection, QA);
		
		assertEquals(true, myRoom.hasDoor(testDirection));
	}
	
	public void testCreateExistingDoor() {
		myRoom = new Room(1,3);
		String testDirection = "North";
		
		final String[] testChoices = {"These", "Are", "Test", "Choices"};
		Question_Answer QA = new Question_Answer("Test Question", testChoices, "Test Answer");
		
		Door testDoor = new Door(QA);
		
		myRoom.createExistingDoor(testDirection, testDoor);
		
		assertEquals(true, myRoom.hasDoor(testDirection));
	}

}
