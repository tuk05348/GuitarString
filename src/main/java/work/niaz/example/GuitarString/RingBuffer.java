package work.niaz.example.GuitarString;

import java.util.NoSuchElementException;

/**
 * Ring Buffer
 * 
 * Defines API for Ring Buffer Data Structure, supports enqueue and dequeue operations
 * Data wraps around when it reaches the "end" of the buffer back to the beginning
 * Made expressly for purpose of simulating the Karplus-Strong algorithm for sound synthesis
 * 
 */

public class RingBuffer {
	
	private double[] ringBuffer; //internal ring buffer implemented with array
	private int first, last; //index trackers
	
	/**
	 * Constructor to create a new Ring Buffer data structure with a given size
	 * 
	 * @param capacity size of the ring buffer
	 */
	
	public RingBuffer(int capacity) { //ring buffer constructor
		ringBuffer = new double[capacity]; //array of double with given capacity
		first = 0; //index trackers set to zero
		last = 0;
	}
	
	/**
	 * Calculates the number of items currently in the buffer
	 * 
	 * @return number of items currently in buffer
	 */
	public int size() {
		if(first > last) { //if buffer wraps around return the index difference plus length to get number of slots filled in
			return last - first + ringBuffer.length + 1; //add  1 for 0-indexing
		}
		return last - first; //if it hasn't wrapped around return the difference between indices
	}
	
	/**
	 * Checks if the ring buffer is empty
	 * 
	 * @return boolean denoting if buffer is empty or not
	 */
	public boolean isEmpty() {
		if(this.size() == 0) { 
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if ring buffer is full
	 * 
	 * @return boolean denoting if buffer is full or not
	 */
	public boolean isFull() {
		if(this.size() == ringBuffer.length) {
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a double to the end of ring buffer
	 * 
	 * @param x A double value representing a vibration of the string
	 * @throws FullRingBufferException if a double is enqueued to a full buffer
	 */
	
	public void enqueue(double x) {
		if(this.isFull()) { //if buffer is full throw exception
			throw new IllegalStateException("Buffer is full.");
		}
		else if(last == ringBuffer.length) { //if last is at the end of the buffer, wrap around by resetting last
			last = 0;
			ringBuffer[last] = x; //and add the double to the newly set last
		}
		else { //if last is not at end, fill empty slot with double and increment last
			ringBuffer[last] = x;
			last++;
		}
	}
	
	/**
	 * Removes an item from the front of the ring buffer
	 * 
	 * @return the double removed from the ring buffer
	 * @throws EmptyRingBufferException if one attempts to dequeue from an empty buffer
	 */
	
	public double dequeue() {
		if(this.isEmpty()) { //if buffer is empty throw an exception
			throw new NoSuchElementException("Buffer is empty.");
		}
		first++; //increment first
		if(first == ringBuffer.length) { //if first index tracker is at the end of the array, wrap around
			first = 0;
			return ringBuffer[ringBuffer.length - 1]; //return the last item
		}
		return ringBuffer[first-1]; //return the "removed" item previously at first
	}
	
	/**
	 * Returns item from the front of the buffer but does not remove it
	 * 
	 * @return the double from the front of the buffer
	 */
	public double peek() {
		return ringBuffer[first];
	}
	
}
