package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the ArrayListQueue class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class ArrayListQueueTest {

	/**
	 * Tests the ArrayListQueue Constructor for proper creating of a queue.
	 */
	@Test
	public void testArrayListQueue() {
		ArrayListQueue<String> q = new ArrayListQueue<String>();
		assertEquals(q.size(), 0);
	}

	/**
	 * Tests the add function for proper addition and reporting of added objects to the queue
	 */
	@Test
	public void testAdd() {
		ArrayListQueue<String> q = new ArrayListQueue<String>();
		q.add("One");
		q.add("two");
		q.add("Three");
		q.add("Four");
		q.add("Five");
		q.add("Six");
		q.add("Seven");
		q.add("Eight");
		q.add("Nine");
		q.add("Ten");
		q.add("Eleven");
		q.add("Twelve");
		assertEquals(q.element(), "One");
		assertEquals(q.size(), 12);
		assertEquals(q.remove(), "One");
		assertEquals(q.remove(), "two");
		assertEquals(q.remove(), "Three");
		assertEquals(q.remove(), "Four");
		assertEquals(q.size(), 8);
		assertEquals(q.remove(), "Five");
		assertEquals(q.remove(), "Six");
		assertEquals(q.remove(), "Seven");
		assertEquals(q.remove(), "Eight");
		assertEquals(q.remove(), "Nine");
		assertEquals(q.remove(), "Ten");
		assertEquals(q.remove(), "Eleven");
		assertEquals(q.remove(), "Twelve");
		assertEquals(q.size(), 0);
		
		try {
			q.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(q.size(), 0);
		}
		
	}

	/**
	 * Tests the remove function for proper removal and reporting of removed objects to the queue
	 */
	@Test
	public void testRemove() {
		ArrayListQueue<String> q = new ArrayListQueue<String>();
		try {
			q.remove();
			fail();
		} catch (NoSuchListElementException e) {
			assertEquals(q.size(), 0);
		}
	}

	/**
	 * Tests the element function for proper retrieval of an element
	 */
	@Test
	public void testElement() {
		ArrayListQueue<String> q = new ArrayListQueue<String>();
		try {
			q.element();
			fail();
		} catch (NoSuchListElementException e) {
			assertEquals(q.size(), 0);
		}
	}


	/**
	 * Tests the isEmpty method for proper reporting of empty and non empty queues
	 */
	@Test
	public void testIsEmpty() {
		ArrayListQueue<String> q = new ArrayListQueue<String>();
		assertTrue(q.isEmpty());
		q.add("One");
		assertFalse(q.isEmpty());
	}

}
