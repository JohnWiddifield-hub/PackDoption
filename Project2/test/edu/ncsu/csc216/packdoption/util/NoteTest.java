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
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testHashCodeEquals() {
		Note note1 = new Note(new Date(1, 1, 2000), "a note");
		Note note2 = new Note(new Date(1, 1, 2000), "a note");
		assertTrue(note1.equals(note2));
		assertTrue(note1.hashCode() == note2.hashCode());
		assertFalse(note1 == null);
		assertFalse(note1.equals(new Date(1, 1, 2000)));
		assertFalse(note1.equals(1));
		Note note3 = new Note(new Date(1, 1, 2000), "a note2");
		assertFalse(note3.equals(note2));
		assertFalse(note2.equals(note3));
	}

	/**
	 * This method tests the 
	 */
	@Test
	public void testNote() {
		Note note2 = null;
		Note note1 = new Note(new Date(1, 1, 2000), "a note");
		assertEquals(note1.getMessage(), "a note");
		assertEquals(note1.getDate().toString(), "1/1/2000");
		
		try {
			note2 = new Note(null, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid note");
		}
		assertEquals(note2, null);
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