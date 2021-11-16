package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

/**
 * Sets up the question Database
 * Note: Don't know if this class is necessary, this class may be temporary
 * @author Roland Hanson, Richard Le
 *
 */
public class QuestionDB {
	
	public QuestionDB() {
		
	}
	
	private Connection connect() {
		String url = "jdbc:sqlite:sqlDBs/TriviaMazeDB.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	
	public void selectMC() {
		String sql = "SELECT Question, Choice1, Choice2, Choice3, Choice4, Answer FROM TriviaMC";
		
		try (Connection conn = this.connect(); 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			// loop thru result set
			while(rs.next()) {
				System.out.println(rs.getString("Question") + "\t" +
								   rs.getString("Choice1") + "\t" + 
								   rs.getString("Choice2") + "\t" + 
								   rs.getString("Choice3") + "\t" + 
								   rs.getString("Choice4") + "\t" + 
								   rs.getString("Answer"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectTF() {
		String sql = "SELECT Question, CHOICE1, CHOICE2, Answer FROM TriviaTF";
		
		try (Connection conn = this.connect(); 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			// loop thru result set
			while(rs.next()) {
				System.out.println(
								   rs.getString("Question") + "\t" +
								   rs.getString("CHOICE1") + "\t" + 
								   rs.getString("CHOICE2") + "\t" + 
								   rs.getString("Answer"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void main(String[] theArgs) {
		QuestionDB qdb = new QuestionDB();
		System.out.println("Reading M.C. Questions . . .");
		qdb.selectMC();
		System.out.println("Reading T.F. Questions . . .");
		qdb.selectTF();
	}
		
}
