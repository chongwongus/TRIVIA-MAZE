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
	
	public String getQuestion() {
		return this.question;
	}
	public String getAnswer() {
		return this.answer;
	}
	
	public void printChoices() {
		int i;
		for(i = 0; i < choices.length; i++) {
			System.out.println(i + ") " + choices[i]);
		}
	}
}
