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
	public static void save(Serializable data, String fileName) throws Exception {
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			oos.writeObject(data);
		}
	}

	/**
	 * Loads data
	 * 
	 * @param fileName
	 * @return ois.readObject()
	 * @throws Exception
	 */
	public static Object load(String fileName) throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
			return ois.readObject();
		}
	}

}
