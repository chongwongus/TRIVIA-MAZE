package models;

/**
 * A class to hold questions, answers and answer choices.
 * 
 * @author Jason Hsu
 *
 */
public class Question_Answer {
	private String myQuestion;
	private String[] myChoices;
	private String myAnswer;

	/**
	 * Default constructor
	 */
	public Question_Answer() {

	}

	/**
	 * Constructs a question for the maze
	 * 
	 * @param theQuestion the question
	 * @param theChoices  the answer choices
	 * @param theAnswer   the answer
	 */
	public Question_Answer(String theQuestion, String theChoices[], String theAnswer) {
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myChoices = new String[theChoices.length];

		for (int i = 0; i < theChoices.length; i++) {
			myChoices[i] = theChoices[i];
		}
	}

	/**
	 * Sets the question to the given question
	 * 
	 * @param theQuestion
	 */
	public void setQuestion(String theQuestion) {
		this.myQuestion = theQuestion;
	}

	/**
	 * Sets the question's answer to the given answer
	 * 
	 * @param theAnswer
	 */
	public void setAnswer(String theAnswer) {
		this.myAnswer = theAnswer;
	}

	/**
	 * Sets a question's choices to the given choices
	 * 
	 * @param theChoices
	 */
	public void setChoices(String[] theChoices) {
		for (int i = 0; i < theChoices.length; i++) {
			myChoices[i] = theChoices[i];
		}
	}

	/**
	 * Returns the question
	 * 
	 * @return myQuestion
	 */
	public String getQuestion() {
		return this.myQuestion;
	}

	/**
	 * Returns the answer
	 * 
	 * @return myAnswer
	 */
	public String getAnswer() {
		return this.myAnswer;
	}

	/**
	 * Returns the choices for a question
	 * 
	 * @return myChoices
	 */
	public String[] getChoices() {
		return this.myChoices;
	}
}
