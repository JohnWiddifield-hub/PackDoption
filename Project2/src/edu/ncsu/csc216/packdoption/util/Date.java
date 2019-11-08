package edu.ncsu.csc216.packdoption.util;

/**
 * Represents a date as a month, a day, and a year (M/D/YYYY). M/D means one or two digits for 
 * each the month and the date. A valid date must have a year between 2000 and 2050 (inclusive). 
 * Date implements the Comparable interface.
 * 
 * @author John Widdifield
 *
 */
public class Date implements Comparable<Date> {
	/** The month of the date */
	private int month;
	/** The day of the date */
	private int day;
	/** The year of the date */
	private int year;
	
	/**
	 * Constructs a date based on the given month day and year
	 * @param month  Month for the date
	 * @param day Day for the date
	 * @param year Year for the date
	 * @throws IllegalArgumentException if the month day and year do not form a valid date
	 */
	public Date(int month, int day, int year) {
		return;
	}
	
	/**
	 * Constructs a date based on the given string which represents a date
	 * @param date  String which represents the date in the format (M/D/YYYY) M/D means one 
	 * or two digits for day and month
	 * 
	 * @throws IllegalArgumentException if date is not in the correct format or 
	 * the month day and year do not form a valid date
	 */
	public Date(String date) {
		return;
	}

	/**
	 * Gets the month of the date
	 * @return month of the date
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Gets the day of the date
	 * @return day of the date
	 */
	public int getDay() {
		return day;
	}

	/** 
	 * Gets the year of the date
	 * @return year of the date
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Tests to see if the date string is a valid date in the correct format
	 * @param date Date to test for validity
	 * @return true if the string is a valid date, false if not
	 */
	public static boolean isValidDate(String date){
		return false;
	}
	
	/**
	 * Tests to see if the date variables make a valid date
	 * @param month month to test for validity
	 * @param day to test for validity
	 * @param year to test for validity
	 * @return true if the string is a valid date, false if not
	 */
	public static boolean isValidDate(int month, int day, int year){
		return false;
	}
	
	/**
	 * Compares this instance's date to the passed parameter 
	 * 
	 * @param d date to test (preferrably a Date) for being before, equal to, or after this instance's date
	 * @return 0 if the date is equal to this instance's, positive if it is past this instances, and negative
	 * if the date is before this instance's
	 */
	@Override
	public int compareTo(Date d) {
		
		return 0;
	}
	
	/**
	 * Converts the date into a string (M/D/YYYY)
	 * 
	 * @return String representation of this instance of Date
	 */
	@Override
	public String toString() {
		return null;
	}

	/**
	 * Tells the client how many days from this instances date to the parameter's date
	 * 
	 * @param date date you would like the distance to in days
	 * @return how many days until this date equals the parameters, number will be negative if 
	 * date is before this.date
	 */
	public int daysTo(Date date) {
		return 0;
	}
	
	/**
	 * Creates a hashCode representation of this instance based on its month day and year.
	 * 
	 * @return The hashCode representation of this instance
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * Tests an object against this instance's Date for equality.
	 * 
	 * @return true if they are the same date, false if they are not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/**
	 * Tells the client how many days from this instances date to the parameter's date
	 * @param date date you would like the distance to in days
	 * @return how many days until this date equals the parameters, number will be negative if 
	 * date is before this.date
	 */
	public int yearsTo(Date date) {
		return 0;
		
	}
}
