package edu.ncsu.csc216.packdoption.model.rescue;

import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
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
		if(name == null || name.isBlank() || name.contains("\n")) {
			throw new IllegalArgumentException();
		}
		this.name = name.trim();
		animals = new SortedLinkedList<Animal>();
		vetAppointments = new ArrayListQueue<Animal>();
	}
	
	/**
	 * Gets the name of the rescue
	 * @return name of the rescue as a string
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Adds an animal to the Rescue
	 * 
	 * @param animal animal to add
	 * @return true if animal was added properly false if not
	 * @throws IllegalArgumentException if animal is null
	 */
	public boolean addAnimal(Animal animal) {
		if(animal == null) {
			throw new IllegalArgumentException();
		} else if(animals.contains(animal)) {
			return false;
		} else {
			animals.add(animal);
			return true;
		}
	}
	
	/**
	 * Gets the animal from/at the index provided
	 * @param idx index you would like to get an animal from
	 * @return Animal at the indicated index
	 * @throws IndexOutOfBoundsException if idx is negative or greater than size - 1.
	 */
	public Animal getAnimal(int idx) {
		if(idx < 0 || idx > animals.size() - 1) {
			throw new IndexOutOfBoundsException();
		} else {
			return animals.get(idx);
		}
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
		if(name == null || birthday == null) {
			throw new IllegalArgumentException();
		} else {
			for(int i = 0; i < animals.size(); i++) {
				if(animals.get(i).getName().contentEquals(name) 
						&& animals.get(i).getBirthday().equals(birthday)) {
					return animals.get(i);
				} 
			} 
		return null;

		}
	}
	
	/**
	 * Compares two rescues based on name
	 * @param o Rescue you would like to compare to 
	 * @return 0 if equal, positive if o is greater than this negative if less
	 */
	@Override
	public int compareTo(Rescue o) {
		return this.getName().compareTo(o.getName());
	}
	
	/**
	 * Tells you if the rescue contains a specific animal
	 * @param animal  Animal you would like to check if the rescue contains
	 * @return true if the rescue contains the animal, false if not
	 */
	public boolean contains(Animal animal) {
		return animals.contains(animal);
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
		if(animal == null || note == null) {
			throw new IllegalArgumentException();
		} else if(!animals.contains(animal)) {
			return false;
		} else if(animals.get(animals.indexOf(animal)).getNotes().contains(note)) {
			throw new IllegalArgumentException();
		}
		animals.get(animals.indexOf(animal)).addNote(note);
		return true;
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
		if(animal == null){
			throw new IllegalArgumentException();
		} else if(!animals.contains(animal)) {
			return;
		} else {
			animals.get(animals.indexOf(animal)).setAdoptionInfo(isAdopted, dateAdopted, owner);
		}
	}
	
	/**
	 * Gets the total number of animals in a rescue
	 * 
	 * @return number of animals in the rescue
	 */
	public int numAnimals() {
		return animals.size();
	}
	
	/**
	 * Gets the number of animals that have been adopted 
	 * @return number of adopted animals
	 */
	public int numAnimalsAdopted() {
		int adoptedcntr = 0;
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).adopted()) {
				adoptedcntr++;
			}
		}
		return adoptedcntr;
	}
	
	/**
	 * Gets the number of animals that have not been adopted 
	 * @return number of available animals
	 */
	public int numAnimalsAvailable() {
		return animals.size() - this.numAnimalsAdopted();
	}
	
	/**
	 * Gets all of the animals that are available for adoption
	 * 
	 * @return SortedLinkedList of Animals that are available for adoption
	 */
	public SortedLinkedList<Animal> animalsAvailable(){
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(!animals.get(i).adopted()) {
				available.add(animals.get(i));
			}
		}
		return available;
	}
	
	/**
	 * Gets all of the cats that are available for adoption
	 * 
	 * @return SortedLinkedList of cats that are available for adoption
	 */
	public SortedLinkedList<Animal> availableCats(){
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i) instanceof Cat && !animals.get(i).adopted()) {
				available.add(animals.get(i));
			}
		}
		return available;
	}
	
	/**
	 * Gets all of the dogs that are available for adoption
	 * 
	 * @return SortedLinkedList of dogs that are available for adoption
	 */
	public SortedLinkedList<Animal> availableDogs(){
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i) instanceof Dog && !animals.get(i).adopted()) {
				available.add(animals.get(i));
			}
		}
		return available;
	}
	
	/**
	 * Gets all of the Animals that are adopted
	 * 
	 * @return SortedLinkedList of animals that are adopted
	 */
	public SortedLinkedList<Animal> animalsAdopted(){
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).adopted()) {
				available.add(animals.get(i));
			}
		}
		return available;
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
		if(today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).getDateEnterRescue().compareTo(today) > 0) {
				throw new IllegalArgumentException();
			}
		}
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(!animals.get(i).adopted()) {
				if(animals.get(i).getDaysAvailableForAdoption(today) >= min && 
						animals.get(i).getDaysAvailableForAdoption(today) <= max) {
					available.add(animals.get(i));
				}
			}
		}
		return available;
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
		if(today == null || max < min || min < 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).getDateEnterRescue().compareTo(today) > 0) {
				throw new IllegalArgumentException();
			}
		}
		SortedLinkedList<Animal> available = new SortedLinkedList<Animal>();
		for(int i = 0; i < animals.size(); i++) {
			if(!animals.get(i).adopted()) {
				if(animals.get(i).getAge(today) >= min && 
						animals.get(i).getAge(today) <= max) {
					available.add(animals.get(i));
				}
			}
		}
		return available;
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
		return this.getName();
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
		if(animals.isEmpty()) {
			return new String[0][0];
		} 
		
		String[][] arr = new String[animals.size()][7];
		for(int i = 0; i < animals.size(); i++) {
			
			arr[i][0] = animals.get(i).getName();
			if(animals.get(i) instanceof Dog) {
				arr[i][1] = "Dog";
			} else {
				arr[i][1] = "Cat";
			}
			arr[i][2] = animals.get(i).getBirthday().toString();
			arr[i][3] = Integer.toString(animals.get(i).getAge(today));
			if(animals.get(i).getAgeCategory(today) == AgeCategory.YOUNG) {
				arr[i][4] = "YOUNG";
			} else if(animals.get(i).getAgeCategory(today) == AgeCategory.ADULT) {
				arr[i][4] = "ADULT";
			} else {
				arr[i][4] = "SENIOR";
			}
			if(animals.get(i).adopted()) {
				arr[i][5] = "Yes";
			} else {
				arr[i][5] = "No";
			}
			if(animals.get(i).adopted()) {
				arr[i][6] = "";
			} else {
				arr[i][6] = Integer.toString(animals.get(i).getDateEnterRescue().daysTo(today));
			}
		}
		return arr;
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
		if(vetAppointments.isEmpty()) {
			return new String[0][0];
		} 
		
		String[][] arr = new String[vetAppointments.size()][7];
		int size = vetAppointments.size();
		ArrayListQueue<Animal> temp = new ArrayListQueue<Animal>();
		ArrayListQueue<Animal> temp2 = new ArrayListQueue<Animal>();
		ArrayListQueue<Animal> temp3 = new ArrayListQueue<Animal>();
		for(int i = 0; i < size; i++) {
			temp.add(vetAppointments.element());
			temp3.add(vetAppointments.element());
			temp2.add(vetAppointments.remove());
			
		}

		for(int i = 0; i < size; i++) {
			Animal ani = temp.remove();
			arr[i][0] = ani.getName();
			if(ani instanceof Dog) {
				arr[i][1] = "Dog";
			} else {
				arr[i][1] = "Cat";
			}
			arr[i][2] = ani.getBirthday().toString();
			arr[i][3] = Integer.toString(ani.getAge(today));
			if(ani.getAgeCategory(today) == AgeCategory.YOUNG) {
				arr[i][4] = "YOUNG";
			} else if(ani.getAgeCategory(today) == AgeCategory.ADULT) {
				arr[i][4] = "ADULT";
			} else {
				arr[i][4] = "SENIOR";
			}
			if(ani.adopted()) {
				arr[i][5] = "Yes";
			} else {
				arr[i][5] = "No";
			}
			if(ani.adopted()) {
				arr[i][6] = "";
			} else {
				arr[i][6] = Integer.toString(ani.getDateEnterRescue().daysTo(today));
			}
		}
		
		for(int i = 0; i < temp3.size(); i++) {
			vetAppointments.add(temp2.remove());
		}
		
		return arr;
	}

	/**
	 * Adds an appointment to the Rescue
	 * @param animal animal you would like to add a new appointment for
	 * @return true if the appointment was added successfully, false if not
	 * @throws NullPointerException if animal is null.
	 */
	public boolean addAppointment(Animal animal) {
		
		if(animal == null) {
			throw new NullPointerException();
		}
		
		if(animals.contains(animal)) {
			int size = vetAppointments.size();
			ArrayListQueue<Animal> temp = new ArrayListQueue<Animal>();
			ArrayListQueue<Animal> temp2 = new ArrayListQueue<Animal>();
			
			for(int i = 0; i < size; i++) {
				temp.add(vetAppointments.element());
				temp2.add(vetAppointments.remove());
				if(temp.element().equals(animal)) {
					return false;
				}
			}
			for(int i = 0; i < size; i++) {
				vetAppointments.add(temp2.remove());
			}
				vetAppointments.add(animal);
				return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets the appointments that are set within the rescue as an ArrayListQueue
	 * @return ArrayListQueue of all appointments in the rescue
	 */
	public ArrayListQueue<Animal> getAppointments() {
		return vetAppointments;
	}
	
	/**
	 * Gets the appointments that are set within the rescue as a string for file writing
	 * @return String representation of appointments for use with saving files
	 */
	public String getAppointmentsIOString() {
		int size = vetAppointments.size();
		String rtrn = "";
		Animal a;
		ArrayListQueue<Animal> temp = new ArrayListQueue<Animal>();
		ArrayListQueue<Animal> temp2 = new ArrayListQueue<Animal>();
		
		for(int i = 0; i < size; i++) {
			temp.add(vetAppointments.element());
			temp2.add(vetAppointments.remove());
			a = temp.remove();
			rtrn = rtrn + "- " + a.getName() + "," + a.getBirthday().toString() + "\n"; {	
			}
		}
		for(int i = 0; i < size; i++) {
			vetAppointments.add(temp2.remove());
		}
		return rtrn;
	}


}
	
	
