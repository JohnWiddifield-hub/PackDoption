package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This class represents a Cat using an extension of the Animal class for its implementation. 
 * 
 * @author John Widdifield
 *
 */
public class Cat extends Animal {

	

	/**
	 * This constructs a cat with adoption data
	 * 
	 * @param name			Name of the cat
	 * @param birthday		The cats birthday as a Date
	 * @param size			Size of the cat
	 * @param houseTrained	Boolean for if the cat is House trained or not
	 * @param goodWithKids	Boolean for if the cat is good with kids
	 * @param notes			All of the cats notes
	 * @param dateEnterRescue	 Date that the cat entered the rescue
	 * @param adopted			Boolean for if the cat is adopted
	 * @param dateAdopted		Date that the cat was adopted, null if cat isn't adopted
	 * @param owner				The cats owner's name
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
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes,
			Date dateEnterRescue, boolean adopted, Date dateAdopted, String owner) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue, adopted, dateAdopted, owner);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This constructs a cat with adoption data
	 * 
	 * @param name			Name of the cat
	 * @param birthday		The cats birthday as a Date
	 * @param size			Size of the cat
	 * @param houseTrained	Boolean for if the cat is House trained or not
	 * @param goodWithKids	Boolean for if the cat is good with kids
	 * @param notes			All of the cats notes
	 * @param dateEnterRescue	 Date that the cat entered the rescue
	 * @param adopted			Boolean for if the cat is adopted
	 * @param dateAdopted		Date that the cat was adopted, null if cat isn't adopted
	 * @param owner				The cats owner's name
	 * @throws IllegalArgumentException if any of the following are true: name is null
				name is whitespace only
				name contains \n or ,
				birthday is null
				size is null
				notes is null
				dateEnterRescue is null
				dateEnterRescue is before birthday
	 */
	public Cat(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes,
			Date dateEnterRescue) {
		super(name, birthday, size, houseTrained, goodWithKids, notes, dateEnterRescue);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
