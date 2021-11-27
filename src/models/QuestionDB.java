package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

/**
 * Sets up the question Database
 * @author Roland Hanson, Richard Le
 *
 */
public class QuestionDB {
	
	public QuestionDB() {
		
	}
	
	private static Connection connect() {
		String url = "jdbc:sqlite:sqlDBs/TriviaMazeDBSQLite.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	
	/**
	 * Creates a list from the Multiple Choice questions table.
	 * Connects to database, then loops through the specified table.
	 * While looping, parses each question, choice, and answer into a Question_Answer,
	 * adding each unique one to the list.
	 * 
	 * @param <Question_Answer>
	 * @return list, a List of Question_Answer
	 */
	public static List<Question_Answer> createMultipleChoiceTrivia() {
		List<Question_Answer> list = new ArrayList<>();
		
		String sql = "SELECT Question, Choice1, Choice2, Choice3, Choice4, Answer FROM TriviaMC";
		
		try (Connection conn = connect(); 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			// loop thru result set
			while(rs.next()) {
				String question = rs.getString("Question");
				String answer = rs.getString("Answer");
				String choice1 = rs.getString("Choice1");
				String choice2 = rs.getString("Choice2");
				String choice3 = rs.getString("Choice3");
				String choice4 = rs.getString("Choice4");
				
				String[] choices = {choice1, choice2, choice3, choice4};
				Question_Answer QA = new Question_Answer(question, choices, answer);
				list.add(QA);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/**
	 * Creates a list from the True False questions table.
	 * Connects to database, then loops through the specified table.
	 * While looping, parses each question, choice, and answer into a Question_Answer,
	 * adding each unique one to the list.
	 * 
	 * @param <Question_Answer>
	 * @return list, a List of Question_Answer
	 */
	public static List<Question_Answer> createTrueFalseTrivia() {
		List<Question_Answer> list = new ArrayList<>();
		
		String sql = "SELECT Question, CHOICE1, CHOICE2, Answer FROM QuestionTF";
		
		try (Connection conn = connect(); 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			// loop thru result set
			while(rs.next()) {
				String question = rs.getString("Question");
				String answer = rs.getString("Answer");
				String choice1 = rs.getString("Choice1");
				String choice2 = rs.getString("Choice2");
				
				String[] choices = {choice1, choice2};
				Question_Answer QA = new Question_Answer(question, choices, answer);
				list.add(QA);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
		
}
