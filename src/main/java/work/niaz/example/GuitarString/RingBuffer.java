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
	private int first, last, length; //index trackers and actual length
	
	/**
	 * Constructor to create a new Ring Buffer data structure with a given size
	 * 
	 * @param capacity size of the ring buffer
	 */
	
	public RingBuffer(int capacity) { //ring buffer constructor
		ringBuffer = new double[capacity + 1]; //array of double with given capacity
		first = 0; //index trackers set to zero
		last = 0;
		length = capacity;
	}
	
	/**
	 * Calculates the number of items currently in the buffer
	 * 
	 * @return number of items currently in buffer
	 */
	public int size() {
		return last - first;
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
		if(this.size() == length) {
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
		if(this.isFull()) {
			throw new IllegalStateException("Buffer is full.");
		}
		else if(last == length) {
			last = 0;
		}
		ringBuffer[last] = x;
		last++;
	}
	
	/**
	 * Removes an item from the front of the ring buffer
	 * 
	 * @return the double removed from the ring buffer
	 * @throws EmptyRingBufferException if one attempts to dequeue from an empty buffer
	 */
	
	public double dequeue() {
		if(this.isEmpty()) {
			throw new NoSuchElementException("Buffer is empty.");
		}
		first++;
		return ringBuffer[first-1];
	}
	
	/**
	 * Returns item from the front of the buffer but does not remove it
	 * 
	 * @return the double from the front of the buffer
	 */
	public double peek() {
		if(this.isEmpty()) {
			throw new NoSuchElementException("Buffer is empty.");
		}
		return ringBuffer[first];
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getLast() {
		return last;
	}
}
