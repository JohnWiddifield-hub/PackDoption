package edu.ncsu.csc216.packdoption.util;

import java.util.Scanner;

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
		if(!isValidDate(month, day, year)) {
			throw new IllegalArgumentException("Invalid date");
		} else {
			this.month = month;
			this.day = day;
			this.year = year;
		}
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
		if(!isValidDate(date)) {
			throw new IllegalArgumentException("Invalid date");
		} else if(date.isEmpty()) {
			throw new IllegalArgumentException("Invalid date");
		} else {
			Scanner lineScan = new Scanner(date);
			lineScan.useDelimiter("/");
			int tempMonth = Integer.parseInt(lineScan.next());
			int tempDay = Integer.parseInt(lineScan.next());
			int tempYear = Integer.parseInt(lineScan.next());
			lineScan.close();
			
			this.month = tempMonth;
			this.day = tempDay;
			this.year = tempYear;
		}
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
		int tempMonth;
		int tempDay;
		int tempYear;
		try {
		Scanner lineScan = new Scanner(date);
		lineScan.useDelimiter("/");
		tempMonth = Integer.parseInt(lineScan.next());
		tempDay = Integer.parseInt(lineScan.next());
		tempYear = Integer.parseInt(lineScan.next());
		lineScan.close();
		} catch (Exception e) {
			return false;
		}
		return isValidDate(tempMonth, tempDay, tempYear);
	}
	
	/**
	 * Tests to see if the date variables make a valid date
	 * @param month month to test for validity
	 * @param day to test for validity
	 * @param year to test for validity
	 * @return true if the string is a valid date, false if not
	 */
	public static boolean isValidDate(int month, int day, int year){
		if(year < 2000 || year > 2050  || month > 12) {
			return false;
		} else if(month < 1 || day < 1) {
			return false;
		} else if(month == 1 && day > 31) {
			return false;
		} else if(month == 2 && day > 29) {
			return false;
		} else if(month == 3 && day > 31) {
			return false;
		} else if(month == 4 && day > 30) {
			return false;
		} else if(month == 5 && day > 31) {
			return false;
		} else if(month == 6 && day > 30) {
			return false;
		} else if(month == 7 && day > 31) {
			return false;
		} else if(month == 8 && day > 31) {
			return false;
		} else if(month == 9 && day > 30) {
			return false;
		} else if(month == 10 && day > 31) {
			return false;
		} else if(month == 11 && day > 30) {
			return false;
		} else if(month == 12 && day > 31) {
			return false;
		} else return !(month == 2 && year % 4 != 0 && day == 29);
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
		return this.daysTo(d);
	}
	
	/**
	 * Converts the date into a string (M/D/YYYY)
	 * 
	 * @return String representation of this instance of Date
	 */
	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	/**
	 * Tells the client how many days from this instances date to the parameter's date
	 * 
	 * @param date date you would like the distance to in days
	 * @return how many days until this date equals the parameters, number will be negative if 
	 * date is before this.date
	 */
	public int daysTo(Date date) {
		
		long thisTime = 0;
		long dateTime = 0;
		
		thisTime += this.day;
		if(this.month == 2) {
			thisTime += 31;
		} else if(this.month == 3) {
			thisTime += 59;
		} else if(this.month == 4) {
			thisTime += 90;
		} else if(this.month == 5) {
			thisTime += 120;
		} else if(this.month == 6) {
			thisTime += 151;
		} else if(this.month == 7) {
			thisTime += 181;
		} else if(this.month == 8) {
			thisTime += 212;
		} else if(this.month == 9) {
			thisTime += 243;
		} else if(this.month == 10) {
			thisTime += 273;
		} else if(this.month == 11) {
			thisTime += 304;
		} else if(this.month == 12) {
			thisTime += 334;
		} 
		thisTime += this.year * 365;
		
		dateTime += date.getDay();
		if(date.getMonth() == 2) {
			dateTime += 31;
		} else if(date.getMonth() == 3) {
			dateTime += 59;
		} else if(date.getMonth() == 4) {
			dateTime += 90;
		} else if(date.getMonth() == 5) {
			dateTime += 120;
		} else if(date.getMonth() == 6) {
			dateTime += 151;
		} else if(date.getMonth() == 7) {
			dateTime += 181;
		} else if(date.getMonth() == 8) {
			dateTime += 212;
		} else if(date.getMonth() == 9) {
			dateTime += 243;
		} else if(date.getMonth() == 10) {
			dateTime += 273;
		} else if(date.getMonth() == 11) {
			dateTime += 304;
		} else if(date.getMonth() == 12) {
			dateTime += 334;
		} 
		dateTime += date.getYear() * 365;
		
		return (int)(dateTime - thisTime);
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
		int thisTime = 0;
		int dateTime = 0;
		
		thisTime += this.day;
		if(this.month == 2) {
			thisTime += 31;
		} else if(this.month == 3) {
			thisTime += 59;
		} else if(this.month == 4) {
			thisTime += 90;
		} else if(this.month == 5) {
			thisTime += 120;
		} else if(this.month == 6) {
			thisTime += 151;
		} else if(this.month == 7) {
			thisTime += 181;
		} else if(this.month == 8) {
			thisTime += 212;
		} else if(this.month == 9) {
			thisTime += 243;
		} else if(this.month == 10) {
			thisTime += 273;
		} else if(this.month == 11) {
			thisTime += 304;
		} else if(this.month == 12) {
			thisTime += 334;
		} 
		thisTime += this.year * 365;
		
		dateTime += date.getDay();
		if(date.getMonth() == 2) {
			dateTime += 31;
		} else if(date.getMonth() == 3) {
			dateTime += 59;
		} else if(date.getMonth() == 4) {
			dateTime += 90;
		} else if(date.getMonth() == 5) {
			dateTime += 120;
		} else if(date.getMonth() == 6) {
			dateTime += 151;
		} else if(date.getMonth() == 7) {
			dateTime += 181;
		} else if(date.getMonth() == 8) {
			dateTime += 212;
		} else if(date.getMonth() == 9) {
			dateTime += 243;
		} else if(date.getMonth() == 10) {
			dateTime += 273;
		} else if(date.getMonth() == 11) {
			dateTime += 304;
		} else if(date.getMonth() == 12) {
			dateTime += 334;
		} 
		dateTime += date.getYear() * 365;
		
		int diffTime = (dateTime - thisTime);
		return diffTime / 365;
	}
}
