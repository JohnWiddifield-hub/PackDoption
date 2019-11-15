package edu.ncsu.csc216.packdoption.util;

/**
 * An implementation of the sortedList interface with a data structure of linked Nodes
 * 
 * @author John Widdifield
 *
 * @param <E>
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {

	/** Head Node of the linkedList */
	public Node<E> head;
	private int size;
	
	/** 
	 * Constructs a new SortedLinkedList 
	 */
	public SortedLinkedList() {
		head = new Node<E>(null, null);
		size = 0;
	}
	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(E e) {
		Node<E> current;
		current = head;
		for(int i = 0; i < size; i++) {
			if(current.value.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Adds the specified element to list in sorted order
	 *
	 * @param e element to be appended to this list
	 * @return true (as specified by {@link Collection#add})
	 * @throws NullPointerException     if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	@Override
	public boolean add(E e) {
		if(e == null) {
			throw new NullPointerException();
		} else if(this.contains(e)) {
			throw new IllegalArgumentException();
		} else if(this.size() == 0) {
			head = new Node<E>(e, null);
			size++;
			return true;
		} else if(e.compareTo(head.value) < 0){
			head = new Node<E>(e, head);
			size++;
			return true;
		} else if(this.size() > 0){
			Node<E> current = head;
			while (current.next != null && e.compareTo(current.next.value) > 0) {
				current = current.next;
			}
			Node<E> newNode = new Node<E>(e, current.next);
			current.next = newNode;
			size++;
			return true;
		}
		return false;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<E> current = head;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.value;
		}
	}

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
	@Override
	public E remove(int index) {
		E temp = this.get(index);
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else if(index > 0 && index < this.size() - 2){
			Node<E> current = head;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		} else if(index == 0) {
			head = head.next;
		} else if(index == this.size() - 1) {
			Node<E> current = head;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = null;
		}
		size--;
		return temp;
	}

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
	@Override
	public int indexOf(E e) {
		Cursor curse = new Cursor();
		int cntr = 0;
		if(e == null) {
			return -1;
		}
		do {
			if(curse.next().equals(e)) {
				return cntr;
			}
			cntr++;
		} while(curse.hasNext());
		return -1;
	}

	/**
	 * Tests two SortedLinkedLists for equality
	 * 
	 * @param o Object to test for equality with this instance
	 * 
	 * @return true if the lists are equal, false if they are not
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(o instanceof SortedLinkedList) {
			if(o.toString().contentEquals(this.toString())){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Generates a hashCode representation for the current instance
	 * 
	 * @return hashCode representation for the current instance as an int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}
	
	/**
	 * Gets a string representation of the current SortedLinkedList
	 * 
	 * @return string representation of the current SortedLinkedList
	 */
	@Override
	public String toString() {
		if(this.size() == 0) {
			return "";
		} else if(this.size() == 1) {
			return "-" + head.value.toString();
		} else if(this.size() == 2) {
			return "-" + head.value.toString() + "\n-" + head.next.value.toString();
		}
		String output = "";
		Cursor iter = new Cursor();
		output = "-" + iter.next().toString();
		while(iter.hasNext()) {
			iter.next();
			output = output + "\n" + "-" + iter.current.value.toString();
			
		}
		return output;
	}
	
	/**
	 * Gets an instance of Cursor as an iterator for the list.
	 * 
	 * @return a Cursor object as an iterator for the list
	 */
	public SimpleListIterator<E> iterator() {
		return new Cursor();
	}
	/**
	 * Node to be used as a chainlink/LinkNode in the LinkedList structure 
	 * @author John Widdifield
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		
		/** Value for the node */
		E value = null;
		/** pointer to the next node in the list */
		Node<E> next = null;
		
		/**
		 * Creates a note using the given value and the reference to the next node in the list.
		 * 
		 * @param val Value you would like the node to contain
		 * @param next Reference to the next node in the list
		 */
		public Node(E val, Node<E> next) {
			this.value = val;
			this.next = next;
		}

		/**
		 * Generates a hashCode representation of this instance's node
		 * 
		 * @return hashCode representation of the Node for this instance
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/**
		 * Tests two nodes for equality
		 * 
		 * @param Node to test for equality
		 * 
		 * @return true if the nodes are equal, false if they are not
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Node<E> other = (Node<E>) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
		
		
		
	}
	
	/**
	 * This provides a cursor for iterating forward through the list without changing the list.
	 * 
	 * @author John Widdifield
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {
		/** The current Node */
		public Node<E> current = null;
		private boolean isFirstCall;
		/**
		 * Creates a cursor for iterating
		 */
		public Cursor() {
			current = head;
			isFirstCall = true;
		}

		/**
		 * Are there elements in the collection that have not been visited?
		 *
		 * @return true if yes, false if all elements have been visited
		 */
		@Override
		public boolean hasNext() {
			return !(current.next == null);
		}

		/**
		 * Answers the question: "What is the next element in the collection to be
		 * visited?" This method also advances the iterator to the following element.
		 * 
		 * @throws NoSuchListElementException if the list has already been traversed.
		 * Exception message: "No element available with call to next."
		 *
		 * @return the next element in the collection to be visited
		 */
		@Override
		public E next() {
			if(isFirstCall) {
				isFirstCall = false;
				return head.value;
			}
			if(!hasNext()) {
				throw new NoSuchListElementException("No element available with call to next.");
			} else {
				E temp = current.next.value;
				current = current.next;
				return temp;
			}
		}

	}
}
