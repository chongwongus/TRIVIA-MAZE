package project;

public abstract class Question_Answer {
	private String question;
	private String[] choices;
	private String answer;
	
	public void setQuestion(String theQuestion) {
		this.question = theQuestion;
	}
	
	public void setAnswer(String theAnswer) {
		this.answer = theAnswer;
	}
	
	public abstract void setChoices();
	
	
}
