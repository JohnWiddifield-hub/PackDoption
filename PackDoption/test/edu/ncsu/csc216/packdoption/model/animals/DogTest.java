package edu.ncsu.csc216.packdoption.model.animals;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.AgeCategory;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This tests the Dog class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class DogTest {

	/**
	 * Tests the getAgeCategory method for proper retrieval of the dog's age category
	 */
	@Test
	public void testGetAgeCategory() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Dog cat = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat.getAgeCategory(new Date(1, 5, 2000)), AgeCategory.YOUNG);
		Dog cat2 = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat2.getAgeCategory(new Date(1, 5, 2005)), AgeCategory.ADULT);
		Dog cat3 = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat3.getAgeCategory(new Date(1, 5, 2010)), AgeCategory.SENIOR);
		Dog cat4 = new Dog("Boots", new Date(1, 1, 2000), Size.MEDIUM, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat4.getAgeCategory(new Date(1, 5, 2000)), AgeCategory.YOUNG);
		Dog cat5 = new Dog("Boots", new Date(1, 1, 2000), Size.MEDIUM, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat5.getAgeCategory(new Date(1, 5, 2004)), AgeCategory.ADULT);
		Dog cat6 = new Dog("Boots", new Date(1, 1, 2000), Size.MEDIUM, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat6.getAgeCategory(new Date(1, 5, 2010)), AgeCategory.SENIOR);
		Dog cat7 = new Dog("Boots", new Date(1, 1, 2000), Size.LARGE, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat7.getAgeCategory(new Date(1, 5, 2000)), AgeCategory.YOUNG);
		Dog cat8 = new Dog("Boots", new Date(1, 1, 2000), Size.LARGE, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat8.getAgeCategory(new Date(1, 5, 2005)), AgeCategory.ADULT);
		Dog cat9 = new Dog("Boots", new Date(1, 1, 2000), Size.LARGE, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat9.getAgeCategory(new Date(1, 5, 2007)), AgeCategory.SENIOR);
	}

	/**
	 * Tests the getAnimalAsArray method for proper retrieval of the Dogs descriptive array
	 */
	@Test
	public void testGetAnimalAsArray() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Dog cat = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		String[] arr = new String[7];
		arr[0] = "Boots";
		arr[1] = "Dog";
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
	}

	/**
	 * Tests the long constructor for a proper construction of a Dog
	 */
	@Test
	public void testDogStringDateSizeBooleanBooleanSortedLinkedListOfNoteDateBooleanDateStringBreed() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Dog cat = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), true, new Date(1, 10, 2000), "Billy", Breed.BEAGLE); 
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
		assertEquals(cat.getBreed(), Breed.BEAGLE);
	}


	/**
	 * Tests the short constructor for a proper construction of a Dog
	 */
	@Test
	public void testDogStringDateSizeBooleanBooleanSortedLinkedListOfNoteDateBreed() {
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Dog cat = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000), Breed.BEAGLE); 
		assertEquals(cat.getName(), "Boots");
		assertEquals(cat.getBirthday().toString(), "1/1/2000");
		assertEquals(cat.getSize(), Size.SMALL);
		assertEquals(cat.isHouseTrained(), true);
		assertEquals(cat.isGoodWithKids(), true);
		assertEquals(cat.getNotes().get(0), notes.get(0));
		assertEquals(cat.getDateEnterRescue().toString(), "1/5/2000");
		assertEquals(cat.adopted(), false);
		assertEquals(cat.getDateAdopted(), null);
		assertEquals(cat.getOwner(), null);
		assertEquals(cat.getBreed(), Breed.BEAGLE);
		
		Dog cat2 = null;
		try {
			cat2 = new Dog("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
					new Date(1, 5, 2000), null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(cat2, null);
		}
	}


}
