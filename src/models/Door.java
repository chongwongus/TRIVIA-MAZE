package models;

/**
 * Unlocked or locked depending on the outcome of the users answer 
 * @author Roland Hanson
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
	
	/**
	 * WIP method to potentially check if a door has been passed through before
	 * Honestly might be pointless, because you can just call isLocked to check 
	 * the status of a previous door
	 * @return myDoorPass
	 */
	public boolean hasPassedThrough() {
		return myDoorPass;
	}
	
	
}
