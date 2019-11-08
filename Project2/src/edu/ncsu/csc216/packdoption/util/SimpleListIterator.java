package edu.ncsu.csc216.packdoption.util;

/**
 * Represents a cursor into a list that can move forward.
 * 
 * @author Jo Perry
 *
 * @param <E> List element type
 */
public interface SimpleListIterator<E extends Comparable<E>> {
	/**
	 * Are there elements in the collection that have not been visited?
	 *
	 * @return true if yes, false if all elements have been visited
	 */
	boolean hasNext();

	/**
	 * Answers the question: "What is the next element in the collection to be
	 * visited?" This method also advances the iterator to the following element, or
	 * throws NoSuchListElementException if the list has already been traversed.
	 * Exception message: "No element available with call to next."
	 *
	 * @return the next element in the collection to be visited
	 */
	E next();
}