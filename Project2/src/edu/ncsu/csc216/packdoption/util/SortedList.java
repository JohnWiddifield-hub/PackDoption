package edu.ncsu.csc216.packdoption.util;

/**
 * This interface is a subset of the java.util.List interface and requires that
 * elements be stored in sorted order based on Comparable. No duplicate items.
 * 
 * This interface is adapted from java.util.List.
 * 
 * @author Jessica Young Schmidt
 * 
 * @author David R. Wright
 *
 *         An ordered collection (also known as a sequence). The user of this
 *         interface has precise control over where in the list each element is
 *         inserted. The user can access elements by their integer index
 *         (position in the list), and search for elements in the list.
 *
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Collection
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Arrays#asList(Object[])
 * @see Collections#nCopies(int, Object)
 * @see Collections#EMPTY_LIST
 * @see AbstractList
 * @see AbstractSequentialList
 * @since 1.2
 * 
 * @param <E> List element type
 */
public interface SortedList<E extends Comparable<E>> {

	// Query Operations

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	boolean isEmpty();

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	boolean contains(E e);

	// Modification Operations

	/**
	 * Adds the specified element to list in sorted order
	 *
	 * @param e element to be appended to this list
	 * @return true (as specified by {@link Collection#add})
	 * @throws NullPointerException     if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	boolean add(E e);

	// Positional Access Operations

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	E get(int index);

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	E remove(int index);

	// Search Operations

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param e element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	int indexOf(E e);

}