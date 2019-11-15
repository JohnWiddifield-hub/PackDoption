package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This tests the animal class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class AnimalTest {


	/**
	 * This tests the addNote method for proper adding of notes to an Animal
	 */
	@Test
	public void testAddNote() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
	
		SortedLinkedList<Note> notes2 = new SortedLinkedList<Note>();
		notes2.add(new Note(new Date(1, 2, 2000), "First day!"));
		notes2.add(new Note(new Date(1, 10, 2000), "new note"));
		cat.addNote(new Note(new Date(1, 10, 2000), "new note"));
		assertEquals(cat.getNotes(), notes2);
	}

	/**
	 * This tests the Equals method for proper realization of equal animals
	 */
	@Test
	public void testEqualsAndHash() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat2 = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat3 = new Cat("Boots2", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat4 = new Cat("Boots", new Date(1, 1, 2001), Size.SMALL, true, true, notes,
				new Date(1, 5, 2001));
		
		assertTrue(cat.equals(cat));
		assertFalse(cat.equals(cat3));
		assertTrue(cat.equals(cat2));
		assertFalse(cat.equals(cat4));
		assertEquals(cat.hashCode(), cat2.hashCode());
		assertTrue(cat.hashCode() != cat3.hashCode());
		//test
	}

	/**
	 * This method tests the compareTo method for proper comparison of Animals
	 */
	@Test
	public void testCompareTo() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat2 = new Cat("Boots", new Date(1, 1, 2001), Size.SMALL, true, true, notes,
				new Date(1, 5, 2001));
		Cat cat3 = new Cat("Boots2", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		
		assertTrue(cat.compareTo(cat2) != 0);
		assertTrue(cat.compareTo(cat3) != 0);
		assertTrue(cat.compareTo(cat) == 0);
	}

	/**
	 * This method tests the toString method for proper generation of a String representation of this
	 * object.
	 */
	@Test
	public void testToString() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		assertEquals(cat.toString(), "Boots (1/1/2000)" + "\n" + "-1/2/2000 First day!");
	}

	/**
	 * This method tests the getAge method for proper retrieval of the animal's age
	 */
	@Test
	public void testGetAge() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		try {
			cat.getAge(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
	}

	/**
	 * Tests the getDaysAvailableForAdoption method for proper retrieval of days available for adoption
	 */
	@Test
	public void testGetDaysAvailableForAdoption() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		assertEquals(cat.getDaysAvailableForAdoption(new Date(1, 10, 2000)), 5);
		
		try {
			cat.getDaysAvailableForAdoption(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
	}

}
