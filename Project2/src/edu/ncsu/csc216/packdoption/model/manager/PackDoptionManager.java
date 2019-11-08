package edu.ncsu.csc216.packdoption.model.manager;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * This class manages the model pieces of the PackDoption system and
 * utilizes the Singleton patter assuring only one instance is being used
 * as multiple instances are not to be used
 *  
 * @author John Widdifield
 */
public class PackDoptionManager {

	/** Name of the file you are using for this instance */
	private String filename;
	/** Whether or not the data has changed from the last save */
	private boolean changed;
	
	private static PackDoptionManager instance;
	
	
	/**
	 * Constructs a singleton object of the PackDoptionManager
	 */
	private PackDoptionManager() {
		
	}
	
	/**
	 * Gets this instance of the PackDoptionManager
	 * @return this instance of the PackDoptionManager
	 */
	public static PackDoptionManager getInstance() {
		return null;
	}
	
	/**
	 * Creates a new list of rescues
	 */
	public void newList() {
		return;
	}
	
	/**
	 * Gets the boolean value for changed
	 * 
	 * @return true if the data has changed since the last save, false if not
	 */
	public boolean isChanged() {
		return false;
	}
	
	/**
	 * Gets the name of the file that this PackDoptionManager instance is using
	 * 
	 * @return name of the file that is being used by this class
	 */
	public String getFilename() {
		return null;
		
	}
	
	/**
	 * Sets the name of the file that this PackDoptionManager instance will be using
	 * 
	 * @return name of the file that is being used by this class
	 * @throws IllegalArgumentException if the filename is null or an string of whitespace
	 */
	public void setFilename(String filename) {
		
	}
	
	/**
	 * Sets the value of the changed boolean field which represents the save state of the file
	 * @param isChanged boolean value you want to represent the change state, true if the data has changed 
	 * since last save, false if not
	 */
	public void setChanged(boolean isChanged) {
		
	}
	
	/**
	 * Loads the formatted file with the passed in file name
	 * @param filename Formatted file you wish to load from
	 * @throws IllegalArgumentException if any errors occur while reading the file
	 */
	public void loadFile(String filename) {
		
	}
	
	/**
	 * Writes a formatted file with the passed in file name
	 * @param filename Formatted file you wish to write to
	 * @throws IllegalArgumentException if any errors occur while writing the file
	 */
	public void saveFile(String filename) {
		
	}
	
	/**
	 * Gets the list of rescues that is being used by the PackDoptionManager
	 * @return RescueList of all the rescues currently loaded in to the PackDoptionManager
	 */
	public RescueList getRescueList() {
		return null;
	}
}
