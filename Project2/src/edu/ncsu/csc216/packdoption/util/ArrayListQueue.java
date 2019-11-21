package edu.ncsu.csc216.packdoption.util;

/**
 * This class creates a queue implementation of an arrayList
 * @author John Widdifield
 *
 * @param <E> Generic data type
 */
public class ArrayListQueue<E> implements Queue<E> {
	private E list[];
	private int size;
	private int capacity;
	
	/**
	 * Constructs an ArrayListQueue list 
	 */
	@SuppressWarnings("unchecked")
	public ArrayListQueue() {
		this.list = (E[]) new Object[10];
		this.size = 0;
		this.capacity = 10;
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
		if(element == null) {
			throw new NullPointerException();
		} else if(this.capacity == size) {
			@SuppressWarnings("unchecked")
			E tempArr[] = (E[]) new Object[capacity * 2];
			for(int i = 0; i < size; i++) {
				tempArr[i] = list[i];
			}
			capacity *= 2;
			list = tempArr;
			list[size] = element;
			size++;
			return true;
		} else {
			list[size] = element;
			size++;
			return true;
		}
	}

	/** 
	 * Removes the head element from the queue. 
	 * 
	 * @return The element which was removed.
	 * @throws NoSuchListElementException if the queue is empty
	 */
	@Override
	public E remove() {
		if(size == 0) {
			throw new NoSuchListElementException();
		}
		E temp = list[0];
		for(int i = 0; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size] = null;
		size--;
		return temp;
	}

	/** 
	 * Gets the next element in the queue
	 * 
	 * @return The element at the head of the queue.
	 * @throws NoSuchListElementException if the queue is empty
	 */
	@Override
	public E element() {
		if(size == 0) {
			throw new NoSuchListElementException();
		}
		return list[0];
	}
	
	/**
	 * Gets the size of the queue.
	 * 
	 * @return The size of the queue as an integer
	 */
	@Override
	public int size() {
		
		return size;
	}
	
	/**
	 * Tells the client when the queue is empty.
	 * 
	 * @return true if the queue is empty, false if the queue has atleast one element contained.
	 */
	@Override
	public boolean isEmpty() {
		
		return (size == 0);
	}

}
