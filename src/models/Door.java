package models;

/**
 * Unlocked or locked depending on the outcome of the users answer 
 * @author Roland Hanson
 *
 */
public class Door {
	
	boolean isCorrect;
	
	public Door() {
		
	}
	
	public boolean isLocked() {
		if (isCorrect == false) {
			return true;
		}
		return false;
	}
	
}
