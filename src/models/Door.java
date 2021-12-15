package models;

/**
 * Unlocked or locked depending on the outcome of the users answer 
 * @author Roland Hanson, Jason Hsu
 *
 */
public class Door {
	
	private Question_Answer myQA;
	private boolean myDoorLock;
	private boolean myOpenDoor;
	private boolean myDoorPass;
	
	public Door() {
		myOpenDoor = false;
		myDoorPass = false;
		myDoorLock = false;
	}
	
	public Door(Question_Answer theQA) {
		this();
		myQA = new Question_Answer(theQA.getQuestion(), theQA.getChoices(), theQA.getAnswer());
	}
	
	public boolean isLocked() {
		return myDoorLock;
	}
	
	public boolean checkAnswer(String theAnswer) {
		if (myQA.getAnswer().equals(theAnswer)) {
			myOpenDoor = true;
			myDoorPass = true;
		}else{
			myDoorLock = true;
		}
		return this.myOpenDoor;
	}
	
	public String[] getChoices() {
		return myQA.getChoices();
	}
	
	public String getQuestion() {
		return myQA.getQuestion();
	}
	
	public String getAnswer() {
		return myQA.getAnswer();
	}
	
	public void setQA(Question_Answer theQA) {
		myQA = theQA;
	}
	
	/**
	 * Checks if a door has been passed through before
	 * So far only works one way
	 * @return myDoorPass
	 */
	public boolean hasPassedThrough() {
		return myDoorPass;
	}
	
	public boolean isOpen() {
		return myOpenDoor;
	}
	
}
