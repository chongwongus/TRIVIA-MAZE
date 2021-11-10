package Structures;
public final class Room {
	private Question_Answer myQA = new Question_Answer();
	private String userAnswer;
	
	
	/**
	 * This is a test constructor TO BE CHANGED later on.
	 * Should read in from SQLite DB to put in question, choices, and answer.
	 */
	public Room() {
		
		String[] theChoices = {"1. Because.", "2. Why not?", "3. Life", "4. 42"};
		
		myQA.setQuestion("Test Q: Why?");
		myQA.setChoices(theChoices);
		myQA.setAnswer("4. 42");
	}
	
	public Question_Answer getRoomQA() {
		return this.myQA;
	}
	
	public void displayRoom() {
		System.out.println("Welcome to room # (insert number)");
		System.out.println("In order to proceed, you must answer this question correctly.");
		System.out.println(myQA.getQuestion());
		System.out.println(myQA.getChoices());
	}
	
	private boolean isCorrect() {
		if (userAnswer == myQA.getAnswer())
				return true;
		else return false;
	}
}