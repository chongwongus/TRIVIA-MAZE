package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Room {

	public Question_Answer myQA = new Question_Answer();
	public Map<String, String> qNA = new HashMap<>(); 
	public QuestionDB dataBase = new QuestionDB(qNA); 
	private ArrayList<String> questions = new ArrayList<>();
	
	/**
	 * Reads from Database and assigns the Q and A to myQA
	 */
	public Room() {
		questions.addAll(qNA.keySet());
		myQA.setQuestion(questions.get(0));
		myQA.setAnswer(qNA.get(myQA.getQuestion()));
	}
	
}
