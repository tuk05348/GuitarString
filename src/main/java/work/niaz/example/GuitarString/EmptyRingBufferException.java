package work.niaz.example.GuitarString;

/**
 * Exception class for when a dequeue operation is attempted on an empty ring buffer
 */

public class EmptyRingBufferException extends Exception {
	/**
	 * Constructs a new EmptyRingBufferException with a given error message
	 * 
	 * @param e Error message string describing the state of the ring buffer
	 */
	public EmptyRingBufferException(String e) {
		super(e);
	}
}
