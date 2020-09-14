package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This class represents a Cat using an extension of the Animal class for its implementation. 
 * 
 * @author John Widdifield
 *
 */
public class Dog extends Animal {

	/**Enumeration for different types of breeds of dogs*/
	public enum Breed { BEAGLE, BULLDOG, FRENCH_BULLDOG, GERMAN_SHEPHERD, POINTER_GERMAN_SHORTHAIRED, POODLE, RETRIEVER_GOLDEN, RETRIEVER_LABRADOR, ROTTWEILER, YORKSHIRE_TERRIER, MIXED, OTHER }

	/** The Dog's breed */
	public Breed breed;
	
	/**
	 * This constructs a cat with adoption data
	 * 
	 * @param name			Name of the dog
	 * @param birthday		The dogs birthday as a Date
	 * @param size			Size of the dog
	 * @param houseTrained	Boolean for if the dog is House trained or not
	 * @param goodWithKids	Boolean for if the dog is good with kids
	 * @param notes			All of the dogs notes
	 * @param dateEnterRescue	 Date that the dog entered the rescue
	 * @param adopted			Boolean for if the dog is adopted
	 * @param dateAdopted		Date that the dog was adopted, null if cat isn't adopted
	 * @param owner				The dogs owner's name
	 * @param breed				Breed of the dog
	 * @throws IllegalArgumentException if any of the following are true: name is null
				name is whitespace only
				name contains \n or ,
				birthday is null
				size is null
				notes is null
				dateEnterRescue is null
				dateEnterRescue is before birthday
				adopted is false but dateAdopted or owner is non-null
				adopted is true but dateAdopted or owner is null
				dateAdopted is before dateEnterRescue
				owner is whitespace only
				owner contains \n or ,
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes,
			Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner, Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
		setBreed(breed);
	}
	
	/**
	 * This constructs a cat with adoption data
	 * 
	 * @param name			Name of the dog
	 * @param birthday		The dogs birthday as a Date
	 * @param size			Size of the dog
	 * @param houseTrained	Boolean for if the dog is House trained or not
	 * @param goodWithKids	Boolean for if the dog is good with kids
	 * @param notes			All of the dogs notes
	 * @param dateEnterRescue	 Date that the dog entered the rescue
	 * @param breed 		Breed of the Dog
	 * @throws IllegalArgumentException if any of the following are true: name is null
				name is whitespace only
				name contains \n or ,
				birthday is null
				size is null
				notes is null
				dateEnterRescue is null
				dateEnterRescue is before birthday
				breed is null
	 */
	public Dog(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes,
			Date dateEnterRescue, Breed breed) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
		setBreed(breed);
	}

	/**
	 * Gets the animals age category based on certain factors such as type and size of the animal.
	 * 
	 * @param today		today's Date
	 * @return			The age category of the specific animal this is an instance of
	 * @throws 			IllegalArgumentException if today is null or if today is before birthday
	 */
	@Override
	public AgeCategory getAgeCategory(Date today) {
		if(today == null || super.getBirthday().daysTo(today) < 0) {
			throw new IllegalArgumentException();
		} else if(getSize() == Size.SMALL && getBirthday().yearsTo(today) < 4) {
			return AgeCategory.YOUNG;
		} else if(getSize() == Size.SMALL && getBirthday().yearsTo(today) < 9) {
			return AgeCategory.ADULT;
		} else if(getSize() == Size.SMALL && getBirthday().yearsTo(today) >= 9) {
			return AgeCategory.SENIOR;
		} else if(getSize() == Size.MEDIUM) {
			if(getBirthday().yearsTo(today) < 3) {
				return AgeCategory.YOUNG;
			} else if(getBirthday().yearsTo(today) < 9) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		} else if(getSize() == Size.LARGE) {
			if(getBirthday().yearsTo(today) < 3) {
				return AgeCategory.YOUNG;
			} else if(getBirthday().yearsTo(today) < 6) {
				return AgeCategory.ADULT;
			} else {
				return AgeCategory.SENIOR;
			}
		}
		return null;
	}

	/**
	 * Gets the animal information as an array with 7 elements which are name, type, birthday, age, age category, 
	 * adopted (yes or no), and day's in rescue
	 * 
	 * @param today 	Today's Date
	 * @return			An array of strings representing different information as listed in the order of the method description
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	@Override
	public String[] getAnimalAsArray(Date today) {
		String[] arr = new String[7];
		if(today == null || super.getBirthday().daysTo(today) < 0) {
			throw new IllegalArgumentException();
		} else {
			arr[0] = getName();
			arr[1] = "Dog";
			arr[2] = getBirthday().toString();
			arr[3] = Integer.toString(getAge(today));
			if(getAgeCategory(today) == AgeCategory.YOUNG) {
				arr[4] = "YOUNG";
			} else if(getAgeCategory(today) == AgeCategory.ADULT) {
				arr[4] = "ADULT";
			} else {
				arr[4] = "SENIOR";
			}
			if(adopted()) {
				arr[5] = "Yes";
			} else {
				arr[5] = "No";
			}
			if(adopted()) {
				arr[6] = "";
			} else {
				arr[6] = Integer.toString(super.getDateEnterRescue().daysTo(today));
			}
		}
		return arr;
	}
	
	/**
	 * Gets the breed of the current instance of Dog
	 * 
	 * @return The breed of the Dog
	 */
	public Breed getBreed() {
		return breed;
	}
	
	/**
	 * Sets the breed of the dog
	 * @param breed Breed you would like to set the dog to
	 */
	public void setBreed(Breed breed) {
		if(breed == null) {
			throw new IllegalArgumentException();
		} else {
			this.breed = breed;
		}
	}
	
	/**
	 * Gets a string for file writing
	 * @return returns the string to be used by the PackDoptionWriter
	 */
	public String getIOString() {
		String rtrn = "";
		if(!this.adopted()) {
			rtrn = "* " + "Dog," + this.getName() + "," + this.getBirthday().toString() + "," + 
					this.getSize().toString() + "," + this.isHouseTrained() + "," + this.isGoodWithKids() + ","
					+ this.getDateEnterRescue().toString() + "," + this.getBreed().toString() + "," + "NOTES";
		} else {
			rtrn = "* " + "Dog," + this.getName() + "," + this.getBirthday().toString() + "," + 
					this.getSize().toString() + "," + this.isHouseTrained() + "," + this.isGoodWithKids() + ","
					+ this.getDateEnterRescue().toString() + "," + "true" + "," + this.getDateAdopted().toString() 
					+ "," + this.getOwner() + "," + this.getBreed().toString() + "," + "NOTES";
		}
		for(int i = 0; i < this.getNotes().size(); i++) {
			rtrn = rtrn + "," + this.getNotes().get(i).toString();
		}
		return rtrn;
	}
}