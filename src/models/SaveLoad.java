package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves and loads the game with serialization
 * 
 * @author Roland Hanson, Richard Le
 *
 */
public class SaveLoad {

	/**
	 * Saves data
	 * 
	 * @param data
	 * @param fileName
	 * @throws Exception
	 */
	public static void save(final Serializable theData, final String theFileName) throws Exception {
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(theFileName)))) {
			oos.writeObject(theData);
		}
	}

	/**
	 * Loads data
	 * 
	 * @param fileName
	 * @return ois.readObject()
	 * @throws Exception
	 */
	public static Object load(final String theFileName) throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(theFileName)))) {
			return ois.readObject();
		}
	}

}
