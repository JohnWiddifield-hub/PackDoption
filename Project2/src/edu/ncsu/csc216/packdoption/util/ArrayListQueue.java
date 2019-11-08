package edu.ncsu.csc216.packdoption.util;

public class ArrayListQueue<E> implements Queue<E>{
	private E list[];
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayListQueue() {
		this.list = (E[]) new Object[10];
		this.size = 0;
	}
	/** 
	 * Adds a generic element to the queue.
	 *  
	 * @param element The element to add to the queue.
	 * @throws NullPointerException if the specified element is null. 
	 * 
	 */
	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * Removes the head element from the queue. 
	 * 
	 * @return The element which was removed.
	 * @throws NoSuchElementException if this queue is empty
	 */
	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Gets the next element in the queue
	 * 
	 * @return The element at the head of the queue.
	 * @throws NoSuchElementException if this queue is empty
	 */
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the size of the queue.
	 * 
	 * @return The size of the queue as an integer
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Tells the client when the queue is empty.
	 * 
	 * @return true if the queue is empty, false if the queue has atleast one element contained.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
