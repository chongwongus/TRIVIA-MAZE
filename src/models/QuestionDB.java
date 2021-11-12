package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.sqlite.SQLiteDataSource;

/**
 * Sets up the question Database
 * Note: Don't know if this class is necessary, this class may be temporary
 * @author Roland Hanson
 *
 */
public class QuestionDB {

	SQLiteDataSource ds = null;
	
	public QuestionDB() {
		
	}
	
	public QuestionDB(Map <String, String> questions) {
		// Creates Question table
		String query = "CREATE TABLE IF NOT EXISTS questions ( " + 
				"QUESTION TEXT NOT NULL, " + 
				"ANSWER TEXT NOT NULL )";
		try ( Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement(); ) {
              int rv = stmt.executeUpdate( query );
              System.out.println( "executeUpdate() returned " + rv );
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
		
		// Note: This is temporary, we would most likely add questions into the DB file itself instead of manually through here
		String statement = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'What is the meaning of life?', '42' )";
		
		try ( Connection conn = ds.getConnection();
	              Statement stmt = conn.createStatement(); ) {
	            int rv = stmt.executeUpdate( statement );
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
		
		 query = "SELECT * FROM questions";

	        try ( Connection conn = ds.getConnection();
	              Statement stmt = conn.createStatement(); ) {
	            
	            ResultSet rs = stmt.executeQuery(query);
	            
	            //walk through each 'row' of results, grab data by column/field name
	            // and assign it to the questions map
	            while ( rs.next() ) {
	                String question = rs.getString( "QUESTION" );
	                String answer = rs.getString( "ANSWER" );
	                questions.put(question, answer);
	            }
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
		
	}
	
}
