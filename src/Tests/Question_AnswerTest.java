package Tests;

import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Structures.Question_Answer;

public class Question_AnswerTest extends TestCase {
	Question_Answer myQA = null;
	
	public void setQuestionTest() {
		String myQuestion = "Does this test work?";
		myQA = new Question_Answer();
		myQA.setQuestion(myQuestion);
		assertEquals(myQuestion,myQA.getQuestion());
		
	}
	
	public void setAnswerTest() {
		String myAnswer = "Yes it does!";
		myQA = new Question_Answer();
		myQA.setAnswer(myAnswer);
		assertEquals(myAnswer,myQA.getAnswer());
	}
	
	public void setChoicesTest() {
		String[] myChoices = {"These", "Are", "A few", "Choices"};
		myQA = new Question_Answer();
		myQA.setChoices(myChoices);
		String testChoices = "1 These\n2 Are\n3 A few\n4 Choices\n";
		assertEquals(testChoices, myQA.getChoices());
	}
	
}