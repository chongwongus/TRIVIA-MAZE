package Tests;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import models.Door;
import models.Question_Answer;

public class DoorTest extends TestCase {

	private Door myDoor;
	
	public void testConstructor() {
		final Door d = new Door();
		assertEquals(false, d.isOpen());
		
		final String[] testChoices = {"These", "Are", "Test", "Choices"};
		String testString = "";
		
		for (int i = 0; i < testChoices.length; i++) {
			testString += testChoices[i];
		}
		
		final Question_Answer QA = new Question_Answer("Test Question", testChoices, 
														"Test Answer");
		final Door d2 = new Door(QA);
		assertEquals(false, d2.isOpen());
		assertEquals("Test Question", d2.getQuestion());
		
		
		final String[] checkChoices = d2.getChoices();
		
		for (int i = 0; i < checkChoices.length; i++) {
			assertEquals(testChoices[i], checkChoices[i]);
		}
		
		assertEquals("Test Answer", d2.getAnswer());
		
	}
	
	public void testCheckAnswer() {
		String testAnswer = "Test Answer";
		
		final String[] testChoices = {"These", "Are", "Test", "Choices"};
		final Question_Answer QA = new Question_Answer("Test Question", testChoices, 
				"Test Answer");
		
		myDoor = new Door(QA);
		assertEquals(true, myDoor.checkAnswer(testAnswer));
	}
	
}
