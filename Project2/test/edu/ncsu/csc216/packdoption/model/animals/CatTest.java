package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This tests the Cat class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class CatTest {

	/**
	 * Tests the getAgeCategory method for proper retrieval of the cat's age category
	 */
	@Test
	public void testGetAgeCategory() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000)); 
		assertEquals(cat.getAgeCategory(new Date(12, 1, 2001)), AgeCategory.YOUNG);
		assertEquals(cat.getAgeCategory(new Date(12, 1, 2005)), AgeCategory.ADULT);
		assertEquals(cat.getAgeCategory(new Date(12, 1, 2010)), AgeCategory.SENIOR);
	}

	/**
	 * Tests the getAnimalAsArray method for proper retrieval of the Dogs descriptive array
	 */
	@Test
	public void testGetAnimalAsArray() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000)); 
		String[] arr = new String[7];
		arr[0] = "Boots";
		arr[1] = "Cat";
		arr[2] = "1/1/2000";
		arr[3] = "19";
		arr[4] = "SENIOR";
		arr[5] = "No";
		arr[6] = "7248";
		String[] output = cat.getAnimalAsArray(new Date(11, 14, 2019));
		
		assertEquals(arr[0], output[0]);
		assertEquals(arr[1], output[1]);
		assertEquals(arr[2], output[2]);
		assertEquals(arr[3], output[3]);
		assertEquals(arr[4], output[4]);
		assertEquals(arr[5], output[5]);
		assertEquals(arr[6], output[6]);
		
		try {
			cat.getAnimalAsArray(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(cat.getName(), "Boots");
		}
		
		Cat cat2 = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000)); 
		String[] arr2 = new String[7];
		arr2[0] = "Boots";
		arr2[1] = "Cat";
		arr2[2] = "1/1/2000";
		arr2[3] = "7";
		arr2[4] = "ADULT";
		arr2[5] = "No";
		arr2[6] = "2868";
		String[] output2 = cat2.getAnimalAsArray(new Date(11, 14, 2007));
		
		assertEquals(arr2[0], output2[0]);
		assertEquals(arr2[1], output2[1]);
		assertEquals(arr2[2], output2[2]);
		assertEquals(arr2[3], output2[3]);
		assertEquals(arr2[4], output2[4]);
		assertEquals(arr2[5], output2[5]);
		assertEquals(arr2[6], output2[6]);
	}

	/**
	 * Tests the long constructor for a proper construction of a Cat
	 */
	@Test
	public void testCatStringDateSizeBooleanBooleanSortedLinkedListOfNoteDateBooleanDateString() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), true, new Date(1, 10, 2000), "Billy"); 
		assertEquals(cat.getName(), "Boots");
		assertEquals(cat.getBirthday().toString(), "1/1/2000");
		assertEquals(cat.getSize(), Size.SMALL);
		assertEquals(cat.isHouseTrained(), true);
		assertEquals(cat.isGoodWithKids(), true);
		assertEquals(cat.getNotes().get(0), notes.get(0));
		assertEquals(cat.getDateEnterRescue().toString(), "1/5/2000");
		assertEquals(cat.adopted(), true);
		assertEquals(cat.getDateAdopted().toString(), "1/10/2000");
		assertEquals(cat.getOwner(), "Billy");
		
	}

	/**
	 * Tests the short constructor for a proper construction of a Cat
	 */
	@Test
	public void testCatStringDateSizeBooleanBooleanSortedLinkedListOfNoteDate() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000)); 
		assertEquals(cat.getName(), "Boots");
		assertEquals(cat.getBirthday().toString(), "1/1/2000");
		assertEquals(cat.getSize(), Size.SMALL);
		assertEquals(cat.isHouseTrained(), true);
		assertEquals(cat.isGoodWithKids(), true);
		assertEquals(cat.getNotes().get(0), notes.get(0));
		assertEquals(cat.getDateEnterRescue().toString(), "1/5/2000");
		
		
	}

}
