package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * A RescueList is a SortedLinkedList of Rescues.
 * 
 * @author John Widdifield
 *
 */
public class RescueList {

	/** SortedLinkedList of rescues to be used */
	public SortedLinkedList<Rescue> rescues;
	
	/**
	 * Constructs a new RescueList with an empty SortedLinkedList of Rescues
	 */
	public RescueList() {
		
	}
	
	/**
	 * Adds rescue to list of rescues
	 * @param rescue Rescue to add to the list
	 * @throws IllegalArgumentException if rescue is null or if 
	 * rescue is already in the list
	 */
	public void addRescue(Rescue rescue) {
		
	}
	
	
	/**
	 * Adds a Rescue with name to the list of rescues
	 * 
	 * @param name name of the Rescue to add
	 * @throws IllegalArgumentException if the name is null or 
	 * the name is whitespace only or if the name contains 
	 * \n or if the name is already taken by another Rescue in the list
	 */
	public void addRescue(String name) {
		
	}
	
	/**
	 * Gets a Rescue from the list at the given index
	 * @param idx	index to retrieve a rescue from
	 * @return Rescue at the given index
	 * @throws IndexOfBoundsException if idx is negative or greater than size - 1
	 */
	public Rescue getRescue(int idx) {
		return null;
	}
	
	/**
	 * Gets the number of Rescues in the list
	 * 
	 * @return number of rescues in the list as integers
	 */
	public int size() {
		return 0;
	}
}
