package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the Note class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class NoteTest {

	/**
	 * This method tests the hashcode function for proper hashCode generation of this class/object
	 */
	@Test
	public void testHashCodeEquals() {
		Note note1 = new Note(new Date(1, 1, 2000), "a note");
		Note note2 = new Note(new Date(1, 1, 2000), "a note");
		assertTrue(note1.equals(note2));
		assertTrue(note1.hashCode() == note2.hashCode());
	}

	/**
	 * This method tests the 
	 */
	@Test
	public void testNote() {
		Note note1 = new Note(new Date(1, 1, 2000), "a note");
		assertEquals(note1.getMessage(), "a note");
		assertEquals(note1.getDate().toString(), "1/1/2000");
	}

	/**
	 * This method tests the compareTo method for proper comparison of Notes
	 */
	@Test
	public void testCompareTo() {
		Note note1 = new Note(new Date(1, 1, 2000), "a note");
		Note note2 = new Note(new Date(1, 1, 2000), "a note");
		assertEquals(note1.compareTo(note2), 0);
		Note note3 = new Note(new Date(1, 1, 2001), "a note");
		assertTrue(note3.compareTo(note2) > 0);
		Note note4 = new Note(new Date(1, 1, 2001), "a note2");
		assertTrue(note3.compareTo(note4) < 0);
	}

	/**
	 * This method tests the toString method for proper generation of a String representation of this
	 * object.
	 */
	@Test
	public void testToString() {
		Note note1 = new Note(new Date(1, 1, 2001), "a note");
		assertEquals(note1.toString(), "1/1/2001 a note");
	}

}
