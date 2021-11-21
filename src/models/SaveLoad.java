package models;

import java.io.*;

/**
 * Saves and loads the game with serialization
 * Unsure if this needs to be it's own class or not
 * Code is VERY WIP
 * @author Roland Hanson
 *
 */
public class SaveLoad {

	public static void main(String[] args) {
		String fileName = "Save.txt";
		
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			// This is where we would do the actual serialization
			// Will add once we know what is actually being serialized
			
		} catch(IOException e) {
			System.out.println("Something went wrong with the serialization!");
		}
		
	}
	
	
	
}
