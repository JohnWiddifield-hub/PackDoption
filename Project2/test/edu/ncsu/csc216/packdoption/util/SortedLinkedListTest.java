package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the SortedLinkedList class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class SortedLinkedListTest {

	/**
	 * This class tests the hashcode function for proper hashCode generation of this class/object
	 */
	@Test
	public void testHashCode() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("One");
		
		assertEquals(list.hashCode(), list.hashCode());
	}

	/**
	 * This method tests the isEmpty method for proper reporting of emtpy and non empty lists
	 */
	@Test
	public void testIsEmpty() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		assertTrue(list.isEmpty());
		list.add("Hey");
		assertFalse(list.isEmpty());
	}

	/**
	 * This method tests the contains method for proper reporting of if the element is contained in the list
	 */
	@Test
	public void testContains() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Hey");
		list.add("No");
		assertTrue(list.contains("No"));
		assertTrue(list.contains("Hey"));
	}

	/**
	 * Tests the add method for proper addition of the element to the list
	 */
	@Test
	public void testAdd() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		list.add("Orange");
		list.add("Banana");
		list.add("Grape");
		list.add("DragonFruit");
		assertEquals(list.get(0), "Apple");
		assertEquals(list.get(1), "Banana");
		assertEquals(list.get(2), "DragonFruit");
		assertEquals(list.get(3), "Grape");
		assertEquals(list.get(4), "Orange");
		assertEquals(list.size(), 5);
		try {
			list.add("Apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(list.size(), 5);
		}
		
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(list.size(), 5);
		}
		
	}

	/**
	 * Tests the remove method for proper removal of an element from the list as well as reporting
	 */
	@Test
	public void testRemove() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		list.add("Orange");
		list.add("Banana");
		list.remove(2);
		assertEquals(list.size(), 2);
		list.remove(0);
		assertEquals(list.size(), 1);
		list.remove(0);
		assertEquals(list.size(), 0);
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 0);
		}
		
		try {
			list.add("Hey");
			list.remove(6);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		
		SortedLinkedList<String> list2 = new SortedLinkedList<String>();
		list2.add("Apple");
		list2.add("Orange");
		list2.add("Banana");
		list2.remove(1);
		assertEquals(list2.indexOf("Orange"), 1);
		assertEquals(list2.indexOf("Apple"), 0);
		
		
	}

	/**
	 * This method tests the indexOf method for proper reporting of the index of the specified element
	 */
	@Test
	public void testIndexOf() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		list.add("Orange");
		list.add("Banana");
		assertEquals(list.indexOf("Apple"), 0);
		assertEquals(list.indexOf("Orange"), 2);
		assertEquals(list.indexOf("Banana"), 1);
		assertEquals(list.indexOf(null), -1);
		SortedLinkedList<String> list2 = new SortedLinkedList<String>();
		assertEquals(list2.indexOf("Apple"), -1);
	}

	/**
	 * Tests the equals method for proper reporting of equivalent lists and non equivalent lists
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsObject() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		SortedLinkedList<String> list2 = new SortedLinkedList<String>();
		list2.add("Apple");
		
		assertTrue(list.equals(list2));
		assertFalse(list.equals("heyo"));
		//assertFalse(list.equals(null));
		assertFalse(list.equals(new SortedLinkedList<String>()));
	}
	
	/**
	 * This method tests the toString method for proper generation of a String representation of this
	 * object. -A\n-B\
	 */
	@Test
	public void testToString() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		assertEquals(list.toString(), "-Apple");
		list.add("Banana");
		assertEquals(list.toString(), "-Apple\n-Banana");
		list.add("Orange");
		assertEquals(list.toString(), "-Apple\n-Banana\n-Orange");
		list.add("Pear");
		assertEquals(list.toString(), "-Apple\n-Banana\n-Orange\n-Pear");
	}

	/**
	 * This tests the iterator method for proper iteration of the LinkedList
	 */
	@Test
	public void testIterator() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Orange");
		SimpleListIterator<String> cur = list.iterator();
		assertTrue(cur.hasNext());
		assertEquals(cur.next(), "Apple");
		assertTrue(cur.hasNext());
		assertEquals(cur.next(), "Banana");
		assertTrue(cur.hasNext());
		assertEquals(cur.next(), "Orange");
		assertEquals(cur.hasNext(), false);
		
		try {
			cur.next();
			fail();
		} catch (NoSuchListElementException e) {
			assertTrue(!cur.hasNext());
		}
		
	}

}
