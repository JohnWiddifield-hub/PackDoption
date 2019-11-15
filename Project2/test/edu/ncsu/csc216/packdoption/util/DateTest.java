package edu.ncsu.csc216.packdoption.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the Date class for proper implementation
 * 
 * @author John Widdifield
 *
 */
	public class DateTest {

	/**
	 * This method tests the hashcode function for proper hashCode generation of this class/object
	 */
	@Test
	public void testHashCode() {
		Date date1 = new Date(1, 1, 2000);
		Date date2 = new Date(1, 1, 2000);
		Date date3 = new Date(2, 2, 2000);
		assertEquals(date1.hashCode(), date2.hashCode());
		assertFalse(date1.hashCode() == date3.hashCode());
		assertTrue(date1.equals(date2));
	}

	/**
	 * Tests the Date constructor using 3 ints for params for proper creation of a Date
	 */
	@Test
	public void testDateIntIntInt() {
		Date date3;
		try {
			Date date1 = new Date(8, 20, 2018);
			Date date2 = new Date(11, 14, 2019);
			assertEquals(date1.getDay(), 20);
			assertEquals(date1.getMonth(), 8);
			assertEquals(date1.getYear(), 2018);
			assertEquals(date2.getDay(), 14);
			assertEquals(date2.getMonth(), 11);
			assertEquals(date2.getYear(), 2019);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		try {
			date3 = new Date(0, 1, 2019);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date");
		}
		
		try {
			date3 = new Date(12, 32, 2019);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date");
		}
		
		try {
			date3 = new Date(12, 20, 1999);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date");
		}
		
		date3 = new Date(1, 1, 2000);
		assertEquals(date3.getDay(), 1);
	}

	/**
	 * Tests the Date constructor using a String for param for proper creation of a Date
	 */
	@Test
	public void testDateString() {
		Date date = new Date(1, 1, 2000);
		Date date2 = new Date("1/1/2000");
		assertEquals(date.toString(), "1/1/2000");
		assertTrue(date.equals(date2));
	}

	/**
	 * Tests the isValidDate method for proper reporting of valid Dates using the 3 Int params
	 */
	@Test
	public void testIsValidDateIntIntInt() {
		assertFalse(Date.isValidDate(1, 50, 2000));
		assertFalse(Date.isValidDate(2, 50, 2000));
		assertFalse(Date.isValidDate(3, 50, 2000));
		assertFalse(Date.isValidDate(4, 50, 2000));
		assertFalse(Date.isValidDate(5, 50, 2000));
		assertFalse(Date.isValidDate(6, 50, 2000));
		assertFalse(Date.isValidDate(7, 50, 2000));
		assertFalse(Date.isValidDate(8, 50, 2000));
		assertFalse(Date.isValidDate(9, 50, 2000));
		assertFalse(Date.isValidDate(10, 50, 2000));
		assertFalse(Date.isValidDate(12, 50, 2000));
		assertFalse(Date.isValidDate(11, 50, 2000));
		assertTrue(Date.isValidDate(1, 15, 2000));
		assertTrue(Date.isValidDate(2, 15, 2000));
		assertTrue(Date.isValidDate(3, 15, 2000));
		assertTrue(Date.isValidDate(4, 15, 2000));
		assertTrue(Date.isValidDate(5, 15, 2000));
		assertTrue(Date.isValidDate(6, 15, 2000));
		assertTrue(Date.isValidDate(7, 15, 2000));
		assertTrue(Date.isValidDate(8, 15, 2000));
		assertTrue(Date.isValidDate(9, 15, 2000));
		assertTrue(Date.isValidDate(10, 15, 2000));
		assertTrue(Date.isValidDate(11, 15, 2000));
		assertTrue(Date.isValidDate(12, 15, 2000));
		assertTrue(Date.isValidDate(2, 29, 2020));
		assertFalse(Date.isValidDate(2, 29, 2021));
		assertFalse(Date.isValidDate(2, 30, 2021));
		
	}

	/**
	 * Tests the compareTo method for proper comparison of Dates
	 */
	@Test
	public void testCompareTo() {
		Date date1 = new Date(1, 1, 2000);
		Date date2 = new Date(1, 2, 2000);
		assertTrue(date1.compareTo(date2) > 0);
		assertTrue(date2.compareTo(date1) < 0);
		assertTrue(date1.compareTo(date1) == 0);
	}

	/**
	 * This tests the daysTo method for proper reporting of days untill a certian Date
	 */
	@Test
	public void testDaysTo() {
		Date date1 = new Date(1, 1, 2000);
		Date date2 = new Date(1, 2, 2000);
		Date date3 = new Date(2, 2, 2000);
		Date date4 = new Date(3, 2, 2000);
		Date date5 = new Date(4, 2, 2000);
		Date date6 = new Date(5, 2, 2000);
		Date date7 = new Date(6, 2, 2000);
		Date date8 = new Date(7, 2, 2000);
		Date date9 = new Date(8, 2, 2000);
		Date date10 = new Date(9, 2, 2000);
		Date date11 = new Date(10, 2, 2000);
		Date date12 = new Date(11, 2, 2000);
		Date date13 = new Date(12, 2, 2000);
		
		assertEquals(date1.daysTo(date2), 1);
		assertEquals(date3.daysTo(date4), 28);
		assertEquals(date4.daysTo(date5), 31);
		assertEquals(date5.daysTo(date6), 30);
		assertEquals(date6.daysTo(date7), 31);
		assertEquals(date7.daysTo(date8), 30);
		assertEquals(date8.daysTo(date9), 31);
		assertEquals(date9.daysTo(date10), 31);
		assertEquals(date10.daysTo(date11), 30);
		assertEquals(date11.daysTo(date12), 31);
		assertEquals(date12.daysTo(date13), 30);
		assertEquals(date13.daysTo(date13), 0);
		
		Date date14 = new Date(1, 2, 2001);
		assertEquals(date1.daysTo(date14), 366);
		
	}

	/**
	 * This method tests the yearsTo method for proper reporting of Years to a certain Date
	 */
	@Test
	public void testYearsTo() {
		Date date1 = new Date(1, 1, 2000);
		Date date2 = new Date(1, 2, 2000);
		Date date3 = new Date(2, 2, 2000);
		Date date4 = new Date(3, 2, 2000);
		Date date5 = new Date(3, 2, 2001);
		Date date6 = new Date(3, 1, 2002);
		Date date7 = new Date(6, 2, 2000);
		Date date8 = new Date(7, 2, 2000);
		Date date9 = new Date(8, 2, 2000);
		Date date10 = new Date(9, 2, 2000);
		Date date11 = new Date(10, 2, 2000);
		Date date12 = new Date(11, 2, 2000);
		Date date13 = new Date(12, 2, 2000);
		
		assertEquals(date1.yearsTo(date2), 0);
		assertEquals(date3.yearsTo(date4), 0);
		assertEquals(date4.yearsTo(date5), 1);
		assertEquals(date5.yearsTo(date6), 0);
		assertEquals(date6.yearsTo(date7), -1);
		assertEquals(date7.yearsTo(date8), 0);
		assertEquals(date8.yearsTo(date9), 0);
		assertEquals(date9.yearsTo(date10), 0);
		assertEquals(date10.yearsTo(date11), 0);
		assertEquals(date11.yearsTo(date12), 0);
		assertEquals(date12.yearsTo(date13), 0);
		assertEquals(date13.yearsTo(date13), 0);
		
		Date date14 = new Date(1, 2, 2005);
		assertEquals(date1.yearsTo(date14), 5);
	}

}
