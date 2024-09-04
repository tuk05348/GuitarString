package work.niaz.example.GuitarString;

/**
 * Exception class for when one attempts to enqueue to a full ring buffer
 */

public class FullRingBufferException extends Exception {
	/**
	 * Constructs a FullRingBufferException with a given error message
	 * 
	 * @param e Error message string describing the state of the ring buffer
	 */
	public FullRingBufferException(String e) {
		super(e);
	}
}
