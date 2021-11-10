package Structures;

public class Question_Answer {
	
	private String question;
	private String[] choices;
	private String answer;
	
	public void setQuestion(String theQuestion) {
		this.question = theQuestion;
	}
	
	public void setAnswer(String theAnswer) {
		this.answer = theAnswer;
	}
	
	public void setChoices(String[] theQuestions) {
		for(int i = 0; i < theQuestions.length; i++) {
			this.choices[i] = theQuestions[i];
		}
	};
	
	public String getQuestion() {
		return this.question;
	}
	public String getAnswer() {
		return this.answer;
	}
	
	public String getChoices() {
		String myChoices = "";
		
		for(int i = 0; i < choices.length; i++) {
			myChoices += (i + 1) + " " + choices[i] + "\n";
		}
		
		return myChoices;
	}
}
