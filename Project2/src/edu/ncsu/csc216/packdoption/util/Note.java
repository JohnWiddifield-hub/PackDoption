package edu.ncsu.csc216.packdoption.util;

/**
 * This class represents a note as a date and a message.  
 * 
 * @author John Widdifield
 *
 */
public class Note implements Comparable<Note> {

	/** Message of the note */
	private String message;
	/** Date of the note */
	private Date date;
	
	/**
	 * Constructs a note with a date and message
	 * @param date date to be included in the note
	 * @param note message to be included in the note
	 */
	public Note(Date date, String note) {
		
	}
	
	/**
	 * gets the date of the note
	 * @return date of the note
	 */
	public Date getDate() {
		return null;
	}
	
	/**
	 * gets the message of the note
	 * @return String representing the message in the note
	 */
	public String getMessage() {
		return null;
	}

	/**
	 * Compares a note to another note.
	 * @param o Note to compare with
	 * @return returns 0 if they have the same contents, a negative number if o is less than this.Note, positive
	 * if o is greater than this.Note
	 */
	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Converts the Note into a string in the format "M/D/YYYY Message"
	 * 
	 * @return String representation of the note
	 */
	@Override
	public String toString() {
		return null;
	}

	/**
	 * Converts the Note into a hashcode representation
	 * 
	 * @return hashCode representation of the Note
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	/** 
	 * Tests two notes for equality
	 * 
	 * @return true if the Notes are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
	
}
