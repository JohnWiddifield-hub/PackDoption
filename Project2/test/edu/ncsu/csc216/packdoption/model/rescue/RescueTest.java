package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.io.PackDoptionReader;
import edu.ncsu.csc216.packdoption.model.animals.Animal;
import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This tests the Rescue class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class RescueTest {

		Rescue rescue = new Rescue("Rescue");
		SortedLinkedList<Note> notes = new SortedLinkedList<Note>();
		Cat cat = new Cat("Boots", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat2 = new Cat("Tigger", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Date today = new Date(1, 6, 2000);
	/**
	 * This method tests the hashcode function for proper hashCode generation of this class/object
	 */
	@Test
	public void testHashCode() {
		Rescue rescu = new Rescue("Rescue");
		Rescue rescu2 = new Rescue("Rescue");
		Rescue rescu3 = new Rescue("bebop");
		assertEquals(rescu.hashCode(), rescu2.hashCode());
		assertTrue(rescu2.hashCode() != rescu3.hashCode());
	}

	/**
	 * This tests the constructor for proper creation of Rescue objects
	 */
	@Test
	public void testRescue() {
		Rescue rescu = new Rescue("Rescue");
		assertEquals(rescu.getName(), "Rescue");
		
		
	}


	/**
	 * This tests the addAnimal method for proper addition of an animal to the specified Rescue
	 */
	@Test
	public void testAddAnimal() {
		Rescue rescu = new Rescue("Rescue");
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		rescu.addAnimal(cat);
		assertEquals(rescu.getAnimal(0), cat);
		
		assertFalse(rescu.addAnimal(cat));
	}

	/**
	 * This tests the getAnimal method when using a name and a birthday for proper retrieval of an animal from the rescue
	 */
	@Test
	public void testGetAnimalStringDate() {
		Rescue rescu = new Rescue("Rescue");
		
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		
		Cat cat7 = new Cat("Peyton", new Date(9, 8, 2012), Size.SMALL, true, true, notes,
				new Date(1, 5, 2013));
		rescu.addAnimal(cat);
		rescu.addAnimal(cat7);
		
		assertEquals(rescu.getAnimal("Boots", new Date(1, 1, 2000)), cat);
		assertEquals(rescu.getAnimal("Peyton", new Date(9, 8, 2012)), cat7);
	}

	/**
	 * This Tests the compareTo method for proper comparison of a Rescue to another
	 */
	@Test
	public void testCompareTo() {
		Rescue rescu = new Rescue("Rescue");
		Rescue rescu2 = new Rescue("Rescue");
		Rescue rescu3 = new Rescue("bebop");
		assertEquals(rescu.compareTo(rescu2), 0);
		assertTrue(rescu.compareTo(rescu3) != 0);
	}

	/**
	 * Tests the contains method for proper reporting if the Rescue contains the parameter
	 */
	@Test
	public void testContains() {
		Rescue rescu = new Rescue("Rescue");
		
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		Cat cat4 = new Cat("Gypsy", new Date(1, 1, 2000), Size.SMALL, true, true, notes,
				new Date(1, 5, 2000));
		Cat cat3 = new Cat("Boots", new Date(1, 1, 2001), Size.SMALL, true, true, notes,
				new Date(1, 5, 2001));
		rescu.addAnimal(cat);

		
		assertTrue(rescu.contains(cat));
		assertFalse(rescu.contains(cat3));
		assertFalse(rescu.contains(cat4));
	}

	/**
	 * Tests the addNote method for proper adding of notes to Animals
	 */
	@Test
	public void testAddNote() {
		Rescue rescu = new Rescue("Rescue");
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		rescu.addAnimal(cat);
		assertTrue(rescu.addNote(cat, new Note(new Date(1, 1, 2000), "Birthday!!!!")));
		
		try {
			rescu.addNote(null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(rescu.getAnimal(0).getNotes().size(), 2);
		}
		
		try {
			rescu.addNote(cat, new Note(new Date(1, 1, 2000), "Birthday!!!!"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(rescu.getAnimal(0).getNotes().size(), 2);
		}
	}

	/**
	 * This tests the setAdoptionInfo method for proper setting of a Animal's adoption info
	 */
	@Test
	public void testSetAdoptionInfo() {
		Rescue rescu = new Rescue("Rescue");
	
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		
		rescu.addAnimal(cat);
		rescu.setAdoptionInfo(cat, true, new Date(1, 6, 2000), "Chandler Saunders");
		assertEquals(rescu.getAnimal(0).getOwner(), "Chandler Saunders");
		assertEquals(rescu.getAnimal(0).getDateAdopted(), new Date(1, 6, 2000));
		assertEquals(rescu.getAnimal(0).adopted(), true);
		
		try {
			Animal anim = null;
			rescu.setAdoptionInfo(anim, false, new Date(1, 6, 2000), "Chandler");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(rescu.numAnimals() == 1);
		}
		
	}

	/**
	 * This tests the numAnimals method for proper reporting of the number of animals in a rescue
	 */
	@Test
	public void testNumAnimals() {
		notes.add(new Note(new Date(1, 2, 2000), "First day!"));
		rescue.addAnimal(cat);
		
		assertEquals(rescue.numAnimals(), 1);
	}

	/**
	 * This tests the numAnimalsAdopted method for proper reporting of the number of animals that are adopted
	 */
	@Test
	public void testNumAnimalsAdopted() {
		rescue.addAnimal(cat);
		assertEquals(rescue.numAnimalsAdopted(), 0);
	}

	/**
	 * This tests the numAnimalsAvailable method for proper reporting of the number of animals that are not adopted
	 */
	@Test
	public void testNumAnimalsAvailable() {
		rescue.addAnimal(cat);
		assertEquals(rescue.numAnimalsAvailable(), 1);
	}

	/**
	 * This tests the animalsAvailable method for proper reporting of animals that are not adopted
	 */
	@Test
	public void testAnimalsAvailable() {
		rescue.addAnimal(cat);
		assertEquals(rescue.animalsAvailable().get(0), cat);
	}

	/**
	 * This tests the availableCats method for proper reporting of Cats that are not adopted
	 */
	@Test
	public void testAvailableCats() {
		rescue.addAnimal(cat);
		assertEquals(rescue.availableCats().get(0), cat);
	}

	/**
	 * This tests the availableDogs method for proper reporting of Dogs that are not adopted
	 */
	@Test
	public void testAvailableDogs() {
		rescue.addAnimal(cat);
		assertEquals(rescue.availableDogs().size(), 0);
	}

	/**
	 * This tests the animalsAdopted method for proper reporting of all adopted animals in the rescue
	 */
	@Test
	public void testAnimalsAdopted() {
		rescue.addAnimal(cat);
		assertEquals(rescue.animalsAdopted().size(), 0);
	}

	/**
	 * This method tests the availableAnimalsDayRange method for proper reporting of  specified available animals between the ranges
	 */
	@Test
	public void testAvailableAnimalsDayRange() {
		rescue.addAnimal(cat);
		assertEquals(rescue.availableAnimalsDayRange(today, 0, 2).size(), 1);
	}

	/**
	 * This method tests the availableAnimalsDayRange method for proper reporting of  specified available animals between the ages
	 */
	@Test
	public void testAvailableAnimalsAge() {
		rescue.addAnimal(cat);
		assertEquals(rescue.availableAnimalsAge(today, 0, 2).size(), 1);
		
		RescueList list = PackDoptionReader.readRescueListFile("test-files/Rescues.txt");
		Rescue r = list.getRescue(0);
		
		assertEquals(r.availableAnimalsAge(new Date(11, 21, 2019), 5, 14).size(), 1);
		assertEquals(r.availableAnimalsAge(new Date(11, 21, 2019), 0, 14).size(), 1);
		
	}

	/**
	 * This method tests the equals method for proper reporting of equivalent and non equivalent Rescues
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsObject() {
		assertFalse(rescue == null);
		assertFalse(rescue.equals(""));
		assertTrue(rescue.equals(rescue));
		assertFalse(rescue.equals(new Rescue("Rescue2")));
	}

	/**
	 * This method tests the toString method for proper generation of a String representation of this
	 * object.
	 */
	@Test
	public void testToString() {
		assertEquals(rescue.toString(), "Rescue");
		assertFalse(rescue.toString().contentEquals("Rescue2"));
	}

	/**
	 * This tests the getAnimalsAsArray method for proper retrieval of a Rescue's Animals as an array
	 */
	@Test
	public void testGetAnimalsAsArray() {
		rescue.addAnimal(cat);
		assertEquals(rescue.getAnimalsAsArray(today)[0][0], "Boots");
		assertEquals(rescue.getAnimalsAsArray(today)[0][1], "Cat");
		assertEquals(rescue.getAnimalsAsArray(today)[0][2], "1/1/2000");
		assertEquals(rescue.getAnimalsAsArray(today)[0][3], "0");
		assertEquals(rescue.getAnimalsAsArray(today)[0][4], "YOUNG");
		assertEquals(rescue.getAnimalsAsArray(today)[0][5], "No");
		assertEquals(rescue.getAnimalsAsArray(today)[0][6], "1");
		
	}

	/**
	 * This tests the getAppointmentsAsArray method for proper retrieval of a Rescue's Appointments as an array
	 */
	@Test
	public void testGetAppointmentsAsArray() {
		rescue.addAnimal(cat);
		rescue.addAnimal(cat2);
		rescue.addAppointment(cat);
		rescue.addAppointment(cat2);
		assertEquals(rescue.getAppointmentsAsArray(today)[0][0], "Boots");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][1], "Cat");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][2], "1/1/2000");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][3], "0");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][4], "YOUNG");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][5], "No");
		assertEquals(rescue.getAppointmentsAsArray(today)[0][6], "1");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][0], "Tigger");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][1], "Cat");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][2], "1/1/2000");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][3], "0");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][4], "YOUNG");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][5], "No");
		assertEquals(rescue.getAppointmentsAsArray(today)[1][6], "1");
	}

	/**
	 * Tests the addAppointment method for proper adding and reporting of appointments to a rescue
	 */
	@Test
	public void testAddAppointment() {
		try {
			rescue.addAppointment(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(rescue.getAppointments().size(), 0);
		}
		rescue.addAnimal(cat);
		rescue.addAnimal(cat2);
		rescue.addAppointment(cat);
//		assertFalse(rescue.addAppointment(cat));
	}

}
