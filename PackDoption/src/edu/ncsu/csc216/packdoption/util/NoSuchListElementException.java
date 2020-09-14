package edu.ncsu.csc216.packdoption.util;

/**
 * Creates a custom exception for use when there is no such element in a list, allows for a custom message
 * 
 * @author John Widdifield
 *
 */
public class NoSuchListElementException extends RuntimeException {

    /** ID used for serialization */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new NoSuchListElementException by passing the passed-in message to a new Exception.
     * @param message the message for the exception
     */
    public NoSuchListElementException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new NoSuchListElementException using the default message of "No such element in list."
     */
    public NoSuchListElementException() {
        this("No such element in list.");
    }
}
