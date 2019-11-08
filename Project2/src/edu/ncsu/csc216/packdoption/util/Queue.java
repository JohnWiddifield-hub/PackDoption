package edu.ncsu.csc216.packdoption.util;

/**
 * This interface is a subset of the java.util.Queue interface.
 * 
 * This interface is adapted from java.util.Queue.
 * 
 * @author Sarah Heckman
 *
 * @param <E> List element type
 */
public interface Queue<E> {
	/**
	 * Inserts the specified element into this queue if it is possible to do so
	 * immediately without violating capacity restrictions, returning true upon
	 * success.
	 * 
	 * @param e element to add to queue
	 * @return true if add is successful
	 * @throws NullPointerException if the specified element is null and this queue
	 *                              does not permit null elements
	 */
	boolean add(E e);

	/**
	 * Retrieves and removes the head of this queue. This method differs from poll
	 * only in that it throws an exception if this queue is empty.
	 * 
	 * @return the head of this queue
	 * @throws NoSuchListElementException if this queue is empty
	 */
	E remove();

	/**
	 * Retrieves, but does not remove, the head of this queue. Throws an exception
	 * if this queue is empty.
	 * 
	 * @return the head of this queue
	 * @throws NoSuchListElementException if this queue is empty
	 */
	E element();

	/**
	 * Returns the number of elements in this collection.
	 * 
	 * @return the number of elements in this collection
	 */
	int size();

	/**
	 * Returns true if this collection contains no elements.
	 * 
	 * @return true if this collection contains no elements
	 */
	boolean isEmpty();
}