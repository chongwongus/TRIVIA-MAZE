package project;

public final class Room {
	private Question_Answer myQA = new Question_Answer();
	private String userAnswer;
	
	
	/**
	 * This is a test constructor TO BE CHANGED later on.
	 */
	
	public Room() {
		
		String[] theChoices = {"1. Because.", "2. Why not?", "3. Life", "4. 42"};
		
		myQA.setQuestion("Test Q: Why?");
		myQA.setChoices(theChoices);
		myQA.setAnswer("4. 42");
	}
	
	public void displayRoom() {
		System.out.println("Welcome to room # (insert number)");
		System.out.println("In order to proceed, you must answer this question correctly.");
		System.out.println(myQA.getQuestion());
		myQA.printChoices();
	}
	
	private boolean isCorrect() {
		if (userAnswer == myQA.getAnswer())
				return true;
		else return false;
	}
}
