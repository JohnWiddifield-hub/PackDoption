package edu.ncsu.csc216.packdoption.model.rescue;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the RescueList class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class RescueListTest {

	/**
	 * Tests the RescueList constructor for proper creation of a RescueList
	 */
	@Test
	public void testRescueList() {
		RescueList list = new RescueList();
		list.addRescue("Rescue");
		assertTrue(list.size() == 1);
	}

	/**
	 * Tests the addRescue method for proper adding and reporting of a rescue to the rescue list
	 */
	@Test
	public void testAddRescueRescue() {
		RescueList list = new RescueList();
		try {
			list.addRescue("");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 0);
		}
		
		try {
			list.addRescue("Rescue");
			list.addRescue("Rescue");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 1);
		}
		
		try {
			list.addRescue(new Rescue("name"));
			list.addRescue(new Rescue("name"));
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 2);
		}
		
		try {
			list.addRescue(new Rescue(null));
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(list.size() == 2);
		}
		
	}

	/**
	 * Tests the getRescue method for proper retrieval of a Rescue from the list
	 */
	@Test
	public void testGetRescue() {
		RescueList list = new RescueList();
		list.addRescue("Rescue");
		try {
			list.getRescue(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		
		try {
			list.getRescue(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		
		assertEquals(list.getRescue(0), new Rescue("Rescue"));
	}


}
