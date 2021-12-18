package models;

/**
 * Unlocked or locked depending on the outcome of the users answer
 * 
 * @author Roland Hanson, Jason Hsu
 *
 */
public class Door {

	private Question_Answer myQA;
	private boolean myDoorLock;
	private boolean myOpenDoor;
	private boolean myDoorPass;

	/**
	 * Default constructor
	 */
	public Door() {
		myOpenDoor = false;
		myDoorPass = false;
		myDoorLock = false;
	}

	/**
	 * Sets up the door with a question and answer
	 * 
	 * @param theQA
	 */
	public Door(final Question_Answer theQA) {
		this();
		myQA = new Question_Answer(theQA.getQuestion(), theQA.getChoices(), theQA.getAnswer());
	}

	/**
	 * Checks to see if the door is locked
	 * 
	 * @return myDoorLock
	 */
	public boolean isLocked() {
		return myDoorLock;
	}

	/**
	 * Checks if the given answer is the correct answer
	 * 
	 * @param theAnswer
	 * @return true if correct, false otherwise
	 */
	public boolean checkAnswer(final String theAnswer) {
		if (myQA.getAnswer().equals(theAnswer)) {
			myOpenDoor = true;
			myDoorPass = true;
		} else {
			myDoorLock = true;
		}
		return this.myOpenDoor;
	}

	/**
	 * Returns the choices for the door
	 * 
	 * @return myQA.getChoices()
	 */
	public String[] getChoices() {
		return myQA.getChoices();
	}

	/**
	 * Returns the question for the door
	 * 
	 * @return myQA.getQuestion()
	 */
	public String getQuestion() {
		return myQA.getQuestion();
	}

	/**
	 * Returns the answer for the door
	 * 
	 * @return myQA.getAnswer()
	 */
	public String getAnswer() {
		return myQA.getAnswer();
	}

	/**
	 * Sets up the door with a question and answer
	 * 
	 * @param theQA
	 */
	public void setQA(final Question_Answer theQA) {
		myQA = theQA;
	}

	/**
	 * Checks if a door has been passed through before
	 * 
	 * @return myDoorPass
	 */
	public boolean hasPassedThrough() {
		return myDoorPass;
	}

	/**
	 * Checks if the door is open
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return myOpenDoor;
	}

}
