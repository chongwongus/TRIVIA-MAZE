package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves and loads the game with serialization
<<<<<<< HEAD
=======
 * 
>>>>>>> origin/RolandHansonDev
 * @author Roland Hanson, Richard Le
 *
 */
public class SaveLoad {

	public static void save(Serializable data, String fileName) throws Exception {
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			oos.writeObject(data);
		}
	}
	
	public static Object load(String fileName) throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
			return ois.readObject();
		}
	}
	
}
