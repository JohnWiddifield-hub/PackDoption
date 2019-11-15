package edu.ncsu.csc216.packdoption.model.animals;

import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This class represents an animal with partial implementation due to child classes which should specify which animal
 * they are creating
 * 
 * @author John Widdifield
 *
 */
public abstract class Animal implements Comparable<Animal> {
	
	/** Name of the Animal */
	private String name;
	/** Boolean for if the Animal is house Trained */
	private boolean houseTrained;
	/** Boolean for if the Animal is good with kids */
	private boolean goodWithKids;
	/** Boolean for if the animal is adopted */
	private boolean adopted;
	/** Name of the owner as a String */
	private String owner;
	/** Date of the Animal's Birthday */
	private Date birthday;
	/** Date that the Animal was Adopted */
	private Date dateAdopted;
	/** Size of the Animals */
	private Size size;
	/** Notes for the Animal */
	private SortedLinkedList<Note> notes;
	/** Date that the Animal entered the rescue if the animal was rescued */
	private Date dateEnterRescue;
	
	/** Enumeration for Age categories YOUNG, ADULT, SENIOR */
	public enum AgeCategory { YOUNG, ADULT, SENIOR }
	/** Enumeration for Sizes SMALL, MEDIUM, LARGE */
	public enum Size { SMALL, MEDIUM, LARGE }
	
	/**
	 * Constructs an Animal with adoption information
	 * 
	 * @param name			name of the animal
	 * @param birthday		the animal's birthday
	 * @param size 			the size of the animal
	 * @param houseTrained	is the animal house trained? true if so, false if not
	 * @param goodWithKids	is the animal good with kids? true if so, false if not
	 * @param notes			Notes for the animal
	 * @param dateEnterRescue Date the animal entered the rescue
	 * @param adopted		is the animal adopted? true if yes, false if no
	 * @param dateAdopted	Date the animal was adopted
	 * @param owner			the name of the owner
	 * 
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
	Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes, Date dateEnterRescue, 
	       boolean adopted, Date dateAdopted, String owner){
		
		setName(name);
		setBirthday(birthday);
		setSize(size);
		setHouseTrained(houseTrained);
		setGoodWithKids(goodWithKids);
		setNotes(notes);
		setDateEnteredRescue(dateEnterRescue);
		setAdoptionInfo(adopted, dateAdopted, owner);
	}
	       
	/**
	 * Constructs an Animal with adoption information
	 * 
	 * @param name			name of the animal
	 * @param birthday		the animal's birthday
	 * @param size 			the size of the animal
	 * @param houseTrained	is the animal house trained? true if so, false if not
	 * @param goodWithKids	is the animal good with kids? true if so, false if not
	 * @param notes			Notes for the animal
	 * @param dateEnterRescue Date the animal entered the rescue
	 * 
	 * @throws IllegalArgumentException if any of the following are true: name is null
				name is whitespace only
				name contains \n or ,
				birthday is null
				size is null
				notes is null
				dateEnterRescue is null
				dateEnterRescue is before birthday
	 */
	Animal(String name, Date birthday, Size size, boolean houseTrained, boolean goodWithKids, SortedLinkedList<Note> notes, Date dateEnterRescue){
		setName(name);
		setBirthday(birthday);
		setSize(size);
		setHouseTrained(houseTrained);
		setGoodWithKids(goodWithKids);
		setNotes(notes);
		setDateEnteredRescue(dateEnterRescue);
		setAdoptionInfo(false, null, null);
	}
	
	/**
	 * Sets the adoption info for the animal including if they are adopted, 
	 * the date they were adopted, and the owners name
	 * @param adopted true if they are adopted false if not
	 * @param dateAdopted 	if they were adopted the Date they were, if not make this field null
	 * @param owner			the owner's name as a string
	 * 
	 * @throws IllegalArgumentException if one or more items from from this list are true:
	 * 			adopted is false but dateAdopted or owner is non-null
				adopted is true but dateAdopted or owner is null
				dateAdopted is before dateEnterRescue
				owner is whitespace only
				owner contains \n or ,
	 */
	public void setAdoptionInfo(boolean adopted, Date dateAdopted, String owner) {
		if(adopted) {
			if(owner == null || dateAdopted == null) {
				throw new IllegalArgumentException();
			}
		} else {
			if(owner != null || dateAdopted != null) {
				throw new IllegalArgumentException();
			}
		}
		this.setAdopted(adopted);
		this.setDateAdopted(dateAdopted);
		this.setOwner(owner);
	}
	
	/**
	 * Sets the animals size
	 * @param size size enum you would like to set the animals size to.
	 * @throws IllegalArgumentException if size is null
	 */
	public void setSize(Size size) {
		if(size == null) {
			throw new IllegalArgumentException(); 
		} else {
			this.size = size;
		}
	}
	
	/**
	 * Adds a note to the animals list of notes.
	 * 
	 * @param note note you would like to add for the animal
	 * @return false if the note was not added properly true if the note was added properly
	 * @throws IllegalArgumentException if notes is null or the note is already included in the list
	 */
	public boolean addNote(Note note) {
		if(note == null || notes.contains(note)) {
			throw new IllegalArgumentException();
		} 
			notes.add(note);
			return true;
	}

	/**
	 * Generates a hashCode value for an animal 
	 * 
	 * @return hashCode representation of the animal
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Tests two Animals for equality based on name and birthday
	 * 
	 * @param obj Object to test for equality with the instance of Animal you are calling from
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (!birthday.equals(other.birthday)) {
			return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Compare 2 animals and returns 0 if they are the same, a negative
	 * number if the animal is less, and positive if the animal is greater than
	 * 
	 * @param other Animal you would like to compare the current instance to
	 * @returns 0 if they are the same, a negative
	 * number if the animal is less, and positive if the animal is greater than
	 */
	@Override
	public int compareTo(Animal other) {
		if(this.birthday.compareTo(other.birthday)!= 0) {
			return birthday.compareTo(other.birthday);
		} else {
			return this.getName().compareTo(other.getName());
		}
	}
	
	/** 
	 * Converts the animal into a string representation of itself
	 * @return String representation of the Animal.
	 */
	@Override
	public String toString() {
		return this.name + " (" + birthday.toString() + ")" + "\n" + notes.toString();
	}
	
	/**
	 * Gets the age of the animal
	 * 
	 * @param today Today's Date
	 * @return The age of the animal in years as integers
	 */
	public int getAge(Date today) {
		if(today == null || today.daysTo(dateEnterRescue) > 0) {
			throw new IllegalArgumentException();
		}
		return birthday.yearsTo(today);
	}
	
	/**
	 * Gets the number of days that the Animal has been available for adoption
	 * @param today Today's Date
	 * @return number of days that the Animal has been available for adoption in integers
	 */
	public int getDaysAvailableForAdoption(Date today) {
		if(adopted) {
			return -1;
		} else if(today == null || dateEnterRescue.daysTo(today) < 0) {
			throw new IllegalArgumentException();
		} else {
			return this.getDateEnterRescue().daysTo(today);
		}
	}
	
	
	/**
	 * Gets the name of the animal
	 * 
	 * @return name name of the animal
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the animal
	 * 
	 * @param name name of the animal
	 * @throws IllegalArgumentException if name is null, name is whitespace only, 
	 * name contains \n or ,
	 */
	public void setName(String name) {
		if(name == null || name.isBlank() || name.contains("\n") || name.contains(",")) {
			throw new IllegalArgumentException();
			
		} else {
			this.name = name.trim();
		}
	}

	/**
	 * Tells you if the animal is house trained
	 * 
	 * @return true if the animal is house trained, false
	 */
	public boolean isHouseTrained() {
		return houseTrained;
	}

	/**
	 * Sets the animals house trained status
	 * 
	 * @param houseTrained true if you want to set the animal to house trained false if not
	 */
	public void setHouseTrained(boolean houseTrained) {
		this.houseTrained = houseTrained;
	}

	/**
	 * Tells you if the animal is good with kids
	 * 
	 * @return true if the animal is good with kids false if not
	 */
	public boolean isGoodWithKids() {
		return goodWithKids;
	}

	/**
	 * Sets the animals good with kids status to true or false
	 * 
	 * @param goodWithKids true if you want to set the animal to good with kids false if not
	 */
	public void setGoodWithKids(boolean goodWithKids) {
		this.goodWithKids = goodWithKids;
	}

	/**
	 * Tells you if the animal is adopted
	 * 
	 * @return false if the animal isn't adopted true if they are
	 */
	public boolean adopted() {
		return adopted;
	}

	/**
	 * Sets the animals adopted status
	 * 
	 * @param adopted true if you wish to set the animals adopted status to adopted, false if not
	 */
	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	/**
	 * gets the owner's name
	 * 
	 * @return The owners name as a string
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner's name.
	 * @param owner name you would like to assign to the owner
	 * @throws IllegalArgumentException if owner is whitespace only owner contains \n or ,
	 */
	public void setOwner(String owner) {
		if(owner == null) {
			this.owner = owner;
			return;
		}
		if(owner.isBlank() || owner.contains("\n") || owner.contains(",")) {
			throw new IllegalArgumentException();
		}
		this.owner = owner;
	}

	/**
	 * Gets the birthday of the animal
	 * 
	 * @return the animals birthday as a Date
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Sets the animal's birthday
	 * 
	 * @param birthday Date you would like to assign the animals birthday to
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the date that the animal was adopted if they were
	 * 
	 * @return null if the animal wasn't adopted, but if they were it returns the Date they were 
	 */
	public Date getDateAdopted() {
		return dateAdopted;
	}

	/**
	 * Sets the Date that the animal was adopted.
	 * 
	 * @param dateAdopted Date you would like to assign to the adoption Date
	 * @throws IllegalArgumentException if date adopted is before dateEnterRescue
	 */
	public void setDateAdopted(Date dateAdopted) {
		if(dateAdopted == null) {
			this.dateAdopted = dateAdopted;
			return;
		}
		if(dateAdopted.daysTo(dateEnterRescue) > 0) {
			throw new IllegalArgumentException();
		}
		this.dateAdopted = dateAdopted;
	}

	/**
	 * Gets all of the animal's notes in sorted order
	 * @return SortedLinkedList of Notes on the animal
	 */
	public SortedLinkedList<Note> getNotes() {
		return notes;
	}

	/** 
	 * Sets the animal's notes
	 * 
	 * @param notes SortedLinkedList of Note you would like to assign to the animal
	 */
	public void setNotes(SortedLinkedList<Note> notes) {
		this.notes = notes;
	}

	/**
	 * gets the date the animal entered the rescue
	 * 
	 * @return the Date that the animal entered the rescue
	 */
	public Date getDateEnterRescue() {
		return dateEnterRescue;
	}

	/**
	 * Sets the date that the animal entered the rescue
	 * @param dateEnteredRescue	the Date that the animal entered the rescue
	 */
	public void setDateEnteredRescue(Date dateEnteredRescue) {
		if(dateEnteredRescue == null || dateEnteredRescue.daysTo(birthday) > 0) {
			throw new IllegalArgumentException();
		}
		this.dateEnterRescue = dateEnteredRescue;
	}

	/**
	 * Gets the Size of the animal
	 * 
	 * @return Size of the animal
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * Gets the animals age category based on certain factors such as type and size of the animal.
	 * 
	 * @param today		today's Date
	 * @return			The age category of the specific animal this is an instance of
	 * @throws 			IllegalArgumentException if today is null or if today is before birthday
	 */
	public abstract AgeCategory getAgeCategory(Date today);
	
	/**
	 * Gets the animal information as an array with 7 elements which are name, type, birthday, age, age category, 
	 * adopted (yes or no), and day's in rescue
	 * 
	 * @param today 	Today's Date
	 * @return			An array of strings representing different information as listed in the order of the method description
	 * @throws IllegalArgumentException if today is null or today is before birthday
	 */
	public abstract String[] getAnimalAsArray(Date today);
	
}
