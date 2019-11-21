package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This tests the animal class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class PackDoptionWriterTest {

		
	/**
	 * Tests the writeRescueListFile method for proper writing of Rescues to a save file
	 */
	@Test
	public void testWriteRescueFile() {
		RescueList list = new RescueList();
		list.addRescue("Ms. Wuf's Rescue");
		list.addRescue("NCSU Rescue");
		SortedLinkedList<Note> notes1 = new SortedLinkedList<Note>();
		SortedLinkedList<Note> notes2 = new SortedLinkedList<Note>();
		SortedLinkedList<Note> notes3 = new SortedLinkedList<Note>();
		notes1.add(new Note(new Date(3, 14, 2004), "Born in NC"));
		notes3.add(new Note(new Date(2, 20, 2019), "Born in VA"));
		notes3.add(new Note(new Date(7, 8, 2019), "Entered Shelter"));
		Cat lily = new Cat("Lily", new Date(5, 14, 2011), Size.SMALL,
				false, true, notes2, new Date(6, 23, 2011));
		list.getRescue(0).addAnimal(new Cat("Loki", new Date(3, 14, 2004), Size.SMALL,
				false, true, notes1, new Date(4, 8, 2004), true, new Date(5, 13, 2004), "Ethan and Family"));
		list.getRescue(0).addAnimal(lily);
		list.getRescue(0).addAnimal(new Cat("Leo", new Date(2, 20, 2019), Size.SMALL,
				true, false, notes3, new Date(7, 8, 2019), true, new Date(7, 26, 2019), "Mason and Family"));
		
		list.getRescue(0).addAppointment(lily);
		SortedLinkedList<Note> jackNotes = new SortedLinkedList<Note>();
		jackNotes.add(new Note(new Date(3, 9, 2001), "Born in NC"));
		SortedLinkedList<Note> dukeNotes = new SortedLinkedList<Note>();
		dukeNotes.add(new Note(new Date(2, 29, 2004), "Born in NC"));
		SortedLinkedList<Note> jackCatNotes = new SortedLinkedList<Note>();
		jackCatNotes.add(new Note(new Date(4, 21, 2011), "Born in VA"));
		jackCatNotes.add(new Note(new Date(8, 27, 2011), "Entered Shelter"));
		SortedLinkedList<Note> georgeNote = new SortedLinkedList<Note>();
		Dog jack = new Dog("Jack", new Date(3, 9, 2001), Size.LARGE, true, true, jackNotes, new Date(6, 1, 2001), Breed.POINTER_GERMAN_SHORTHAIRED); 
		Dog duke = new Dog("Duke", new Date(2, 29, 2004), Size.LARGE, true, true, dukeNotes, new Date(7, 21, 2004), true, new Date(7, 28, 2004), "Olivia and Family", Breed.ROTTWEILER);
		Cat jackCat = new Cat("Jack", new Date(4, 21, 2011), Size.MEDIUM, false, true, jackCatNotes, new Date(8, 27, 2011));
		Cat george = new Cat("George", new Date(6, 25, 2011), Size.MEDIUM, true, true, georgeNote, new Date(8, 12, 2011), true, new Date(8, 12, 2011), "James and Family");
		list.getRescue(1).addAnimal(jack);
		list.getRescue(1).addAnimal(duke);
		list.getRescue(1).addAnimal(jackCat);
		list.getRescue(1).addAnimal(george);
		list.getRescue(1).addAppointment(jack);
		list.getRescue(1).addAppointment(george);
		list.getRescue(1).addAppointment(duke);
		
		PackDoptionWriter.writeRescueFile("test-files/created_file.txt", list);
		checkFiles("test-files/created_file.txt", "test-files/Rescues.txt");
		
		try {
			PackDoptionWriter.writeRescueFile(null, list);
			fail();
		} catch(IllegalArgumentException e) {
			//make sure nothing changed
			checkFiles("test-files/created_file.txt", "test-files/Rescues.txt");
		}
	}
	
	
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
