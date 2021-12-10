package models;

public class Question_Answer {
	private String myQuestion;
	private String[] myChoices;
	private String myAnswer;
	
	public Question_Answer() {
		
	}
	
	public Question_Answer(String theQuestion, String theChoices[], String theAnswer) {
		myQuestion = theQuestion;
		myAnswer = theAnswer;
		myChoices = new String[theChoices.length];
		
		for (int i = 0; i < theChoices.length; i++) {
			myChoices[i] = theChoices[i];
		}
	}
	
	public void setQuestion(String theQuestion) {
		this.myQuestion = theQuestion;
	}
	
	public void setAnswer(String theAnswer) {
		this.myAnswer = theAnswer;
	}
	
	public void setChoices(String[] theChoices) {
		for (int i = 0; i < theChoices.length; i++) {
			myChoices[i] = theChoices[i];
		}
	}
		
	public String getQuestion() {
		return this.myQuestion;
	}
	public String getAnswer() {
		return this.myAnswer;
	}
	
	public String[] getChoices() {
		return this.myChoices;
	}
	
	public void printChoices() {
		int i;
		for(i = 0; i < myChoices.length; i++) {
			System.out.println(i + ") " + myChoices[i]);
		}
	}

}
