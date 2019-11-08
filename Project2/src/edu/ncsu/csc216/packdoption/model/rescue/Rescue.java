package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.util.ArrayListQueue;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This class represents a an animal Rescue that will contain many animals 
 * and vetrinarian appointments for those animals
 * 
 * @author John Widdifield
 *
 */
public class Rescue implements Comparable<Rescue> {

	/** name of the Rescue */
	private String name;
	/** List of all animals in the rescue */
	private SortedLinkedList<Animal> animals;
	/** List of all animal vetrinarian appointments */
	private ArrayListQueue<Animal> vetAppointments;
	
	/**
	 * Constructs a new rescue with the provided name
	 * @param name	Name of the new rescue
	 * @throws IllegalArgumentException (1) name is null, (2) name is whitespace only, or 
	 * (3) name contains \n. name should be trimmed of leading and/or trailing whitespace.
	 */
	public Rescue(String name) {
		
	}
	
	/**
	 * Gets the name of the rescue
	 * @return name of the rescue as a string
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * Adds an animal to the Rescue
	 * 
	 * @param animal animal to add
	 * @return true if animal was added properly false if not
	 * @throws IllegalArgumentException if animal is null
	 */
	public boolean addAnimal(Animal animal) {
		return false;
	}
	
	/**
	 * Gets the animal from/at the index provided
	 * @param idx index you would like to get an animal from
	 * @return Animal at the indicated index
	 * @throws IndexOutOfBoundsException if idx is negative or greater than size - 1.
	 */
	public Animal getAnimal(int idx) {
		return null;
	}

	/**
	 * Gets the animal from/at the index provided
	 * 
	 * @param name name of the animal you would like to get
	 * @param birthday Date of birth of the animal you would like to receive
	 * @return Animal with the matching name and birthday
	 * @throws IllegalArgumentException if (1) name is null or (2) birthday is null.
	 */
	public Animal getAnimal(String name, Date birthday) {
		return null;
	}
	
	/**
	 * Compares two rescues based on name
	 * @param o Rescue you would like to compare to 
	 * @return 0 if equal, positive if o is greater than this negative if less
	 */
	@Override
	public int compareTo(Rescue o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Tells you if the rescue contains a specific animal
	 * @param animal  Animal you would like to check if the rescue contains
	 * @return true if the rescue contains the animal, false if not
	 */
	public boolean contains(Animal animal) {
		return false;
	}
	
	/**
	 * Adds a note to the specified animal in the rescue
	 * 
	 * @param animal animal you would like to add a note to
	 * @param note	Note you would like to add to the animal
	 * @return true if the note was added successfully, false if not
	 * @Throws IllegalArgumentException if animal is null, note is null, 
	 * the animal’s notes already contains note.
	 */
	public boolean addNote(Animal animal, Note note) {
		return false;
	}
	
	/**
	 * Sets adoption information for animal if animal is in the rescue. 
	 * Does nothing if animal is not in the rescue
	 * 
	 * @param animal Animal you would like to set the adoption info for
	 * @param isAdopted	isAdopted boolean value to set for the animal, true if adopted false if not
	 * @param dateAdopted	Date the animal was adopted
	 * @param owner	Owners name as a string
	 * @Throws IllegalArgumentException if animal is null. See Animal.setAdoptionInfo() for other cases when IllegalArgumentException is thrown.
	 */
	public void setAdoptionInfo(Animal animal, boolean isAdopted, Date dateAdopted, String owner) {
	
	}
	
	/**
	 * Gets the total number of animals in a rescue
	 * 
	 * @return number of animals in the rescue
	 */
	public int numAnimals() {
		return 0;
	}
	
	/**
	 * Gets the number of animals that have been adopted 
	 * @return number of adopted animals
	 */
	public int numAnimalsAdopted() {
		return 0;
	}
	
	/**
	 * Gets the number of animals that have not been adopted 
	 * @return number of available animals
	 */
	public int numAnimalsAvailable() {
		return 0;
	}
	
	/**
	 * Gets all of the animals that are available for adoption
	 * 
	 * @return SortedLinkedList of Animals that are available for adoption
	 */
	public SortedLinkedList<Animal> animalsAvailable(){
		return null;
	}
	
	/**
	 * Gets all of the cats that are available for adoption
	 * 
	 * @return SortedLinkedList of cats that are available for adoption
	 */
	public SortedLinkedList<Animal> availableCats(){
		return null;
	}
	
	/**
	 * Gets all of the dogs that are available for adoption
	 * 
	 * @return SortedLinkedList of dogs that are available for adoption
	 */
	public SortedLinkedList<Animal> availableDogs(){
		return null;
	}
	
	/**
	 * Gets all of the Animals that are adopted
	 * 
	 * @return SortedLinkedList of animals that are adopted
	 */
	public SortedLinkedList<Animal> animalsAdopted(){
		return null;
	}
	
	/**
	 * Gets all of the animals in the rescue that are available and have been available between the
	 * min and max days (inclusive) 
	 * @param today Todays Date
	 * @param min minimum days the animal has been available to search for
	 * @param max maximum days the animal has been available to search for
	 * @return SortedLinkedList of all animals in the rescue that are available for adoption 
	 * and have been available between the min and max days
	 * @Throws IllegalArgumentException if (1) today is null, (2) today is before one of the 
	 * animal’s dateEnterRescue, (3) max is less than min, or (4) min is less than zero
	 */
	public SortedLinkedList<Animal> availableAnimalsDayRange(Date today, int min, int max){
		return null;
	}
	
	/**
	 * Gets a SortedLinkedList of all animals in rescue that are available for adoption 
	 * (have not been adopted) and are between min and max years old (inclusive)
	 * 
	 * @param today	Todays date
	 * @param min	Minimum age that the animal must be to be in the list
	 * @param max 	Maximum age that the animal must be to be in the list
	 * @return a SortedLinkedList of the animals in the rescue that are between the min 
	 * and max years old (inclusive)
	 * @throws IllegalArgumentException if (1) today is null, (2) today is before one 
	 * of the animal’s birthday, (3) max is less than min, or (4) min is less than zero.
	 */
	public SortedLinkedList<Animal> availableAnimalsAge(Date today, int min, int max){
		return null;
	}

	/**
	 * Generates a hashCode for the rescue based on its name
	 * @return integer value that is a hashCode for the Rescue
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Compares this rescue with an object for equality
	 * @param obj object to compare with this rescue
	 * @return false if the object and rescue aren't the 
	 * same, true if the object is an equivalent rescue (Based on name)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rescue other = (Rescue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * Converts the rescue to a string representation
	 * 
	 * @return String representation of the rescue
	 */
	@Override
	public String toString() {
		return null;
	}
	
	/**
	 * Gets the animals in the rescue as an array
	 * @param today Today's Date
	 * @return a 2D array of string representing all animals in 
	 * rescue. First dimension is the animal (in sorted order). 
	 * Second dimension are the different elements: Name, Type (Dog or Cat), 
	 * Birthday, Age, Age Category, Adopted (Yes or No), Days in Rescue (if adopted 
	 * then empty string). If the list is empty, an empty 2D array is returned.
	 */
	public String[][] getAnimalsAsArray(Date today) {
		return null;
	}
	
	/**
	 * Gets the animal appointments in the rescue as a 2D array
	 * @param today Today's Date
	 * @return a 2D array of string representing all animals in rescue. 
	 * First dimension is the animal (in sorted order). Second dimension 
	 * are the different elements: Name, Type (Dog or Cat), Birthday, Age, 
	 * Age Category, Adopted (Yes or No), Days in Rescue (if adopted then empty 
	 * string). If the list is empty, an empty 2D array is returned.
	 */
	public String[][] getAppointmentsAsArray(Date today) {
		return null;
	}
	
	/**
	 * Adds an appointment to the Rescue
	 * @param animal animal you would like to add a new appointment for
	 * @return true if the appointment was added successfully, false if not
	 * @throws NullPointerException if animal is null.
	 */
	public boolean addAppointment(Animal animal) {
		return false;
	}
	
	/**
	 * Gets the appointments that are set within the rescue as an ArrayListQueue
	 * @return ArrayListQueue of all appointments in the rescue
	 */
	public ArrayListQueue<Animal> getAppointments() {
		return null;
	}


}
	
	
