package edu.ncsu.csc216.packdoption.model.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * This tests the PackDoptionReader class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class PackDoptionReaderTest {

	/**
	 * Tests the readRescueListFile method for proper reading of Rescues from a save file
	 */
	@Test
	public void testReadRescueListFile() {
		PackDoptionReader reader = new PackDoptionReader();
		assertNotNull(reader);
		
		RescueList list = PackDoptionReader.readRescueListFile("test-files/Rescues.txt");
		assertEquals(list.size(), 2);
	}

}
