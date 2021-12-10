package Tests;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import models.Question_Answer;

/**
 * Tests the question and answer choices
 * @author Jason Hsu
 *
 */
public class Question_AnswerTest extends TestCase {

	private Question_Answer myQA;
	
	public void testConstructor() {
		myQA = new Question_Answer();
		final String[] testChoices = {"These", "Are", "Test", "Choices"};
		myQA.setQuestion("Test Question");
		myQA.setChoices(testChoices);
		myQA.setAnswer("Test Answer");
		
		final String[] checkChoices = myQA.getChoices();
		
		for (int i = 0; i < myQA.getChoices().length; i++) {
			assertEquals(testChoices[i], checkChoices[i]);
		}
	}
}
