package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
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
	 * Tests the long constructor for animal
	 */
	@Test
	public void testConstructor() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), true, new Date(1, 10, 2000), "Billy");
		assertEquals(cat.getName(), "Boots");
		assertEquals(cat.getBirthday().toString(), "1/1/2000");
		assertEquals(cat.getSize(), Size.SMALL);
		assertEquals(cat.isGoodWithKids(), true);
		assertEquals(cat.isHouseTrained(), true);
		assertEquals(cat.getNotes(), notes);
		assertEquals(cat.getDateEnterRescue().toString(), "1/5/2000");
		assertEquals(cat.adopted(), true);
		assertEquals(cat.getDateAdopted().toString(), "1/10/2000");
		assertEquals(cat.getOwner(), "Billy");
		assertEquals(cat.getAgeCategory(new Date(1, 11, 2000)), AgeCategory.YOUNG);
		Date date = new Date(2, 2, 2001);
		assertEquals(cat.getAge(date), 1);
		
		try {
			cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
					new Date(1, 5, 2000), true, null, "Billy");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		
		try {
			cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
					new Date(1, 5, 2000), true, new Date(1, 10, 2000), null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		
		try {
			cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
					new Date(1, 5, 2000), true, new Date(1, 10, 2000), null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		cat.setDateEnteredRescue(new Date(1, 3, 2000));
		cat.setSize(Size.MEDIUM);
		cat.setName("Boots2");
		assertEquals(cat.getDateEnterRescue(), new Date(1, 3, 2000));
		assertEquals(cat.getSize(), Size.MEDIUM);
		assertEquals(cat.getName(), "Boots2");
		
		
	}

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
		try {
		cat.addNote(new Note(new Date(1, 10, 2000), "new note"));
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		SortedLinkedList<Note> notes3 = new SortedLinkedList<Note>();
		notes3.add(new Note(new Date(9, 8, 2012), "Birthday!!!"));
		Cat cat2 = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes3,
				new Date(1, 5, 2000));
		cat2.addNote(new Note(new Date(2, 10, 2013), "ADOPTED!!!!"));
		System.out.println(cat2.getNotes().toString());
		try {
		cat.addNote(null);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		try {
		cat.setDateEnteredRescue(null);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		
		try {
		cat.setName(null);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		
		try {
		cat.setDateEnteredRescue(null);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		try {
		cat.setSize(null);
		fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
	}

	/**
	 * This tests the Equals method for proper realization of equal animals
	 */
	@SuppressWarnings("unlikely-arg-type")
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
		assertFalse(cat.equals(null));
		assertFalse(cat.equals(4));
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
