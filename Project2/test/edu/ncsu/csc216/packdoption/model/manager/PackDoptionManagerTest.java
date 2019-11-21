package edu.ncsu.csc216.packdoption.model.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.packdoption.view.ui.PackDoptionGUI;

/**
 * This tests the PackDoptionManager class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class PackDoptionManagerTest {

	/**
	 * Tests the getInstance method for proper getting of the instance of PackDoptionManager
	 */
	@Test
	public void testGetInstance() {
		assertFalse(PackDoptionManager.getInstance().isChanged());
	}

	/**
	 * Tests the newList method for proper resetting to a new list
	 */
	@Test
	public void testNewList() {
		PackDoptionManager.getInstance().loadFile("test-files/Rescues.txt");
		assertEquals(PackDoptionManager.getInstance().getRescueList().size(), 2);
		PackDoptionManager.getInstance().newList();
		assertEquals(PackDoptionManager.getInstance().getRescueList().size(), 0);
	}


	/**
	 * Tests the getFilename method for proper retrieval of the PackDoptionManager's filename
	 */
	@Test
	public void testGetSetFilename() {
		PackDoptionManager.getInstance().setFilename("test-files/Rescues.txt");
		assertEquals(PackDoptionManager.getInstance().getFilename(), "test-files/Rescues.txt");
		
		try {
			PackDoptionManager.getInstance().setFilename(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(PackDoptionManager.getInstance().getFilename(), "test-files/Rescues.txt");
		}
	}

	/**
	 * Tests the setChanged method for proper setting of the changed boolean field
	 */
	@Test
	public void testSetChanged() {
		PackDoptionManager.getInstance().setChanged(true);
		assertEquals(PackDoptionManager.getInstance().isChanged(), true);
	}

	/**
	 * Tests the loadFile method for proper loading of rescues from a file
	 */
	@Test
	public void testLoadFile() {
		try {
			PackDoptionManager.getInstance().loadFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertFalse(PackDoptionManager.getInstance().isChanged());
		}
	}

	/**
	 * Tests the saveFile method for proper saving of Rescues to a file
	 */
	@Test
	public void testSaveLoadFile() {
		PackDoptionManager.getInstance().loadFile("test-files/Rescues.txt");
		PackDoptionManager.getInstance().saveFile("test-files/Rescues.txt");
		PackDoptionManager.getInstance().loadFile("test-files/Rescues.txt");
		assertEquals(PackDoptionManager.getInstance().getRescueList().size(), 2);
		
		try {
			PackDoptionManager.getInstance().saveFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertFalse(PackDoptionManager.getInstance().isChanged());
		}
		
		PackDoptionGUI gui = new PackDoptionGUI();
		assertNotNull(gui);
	}


}
