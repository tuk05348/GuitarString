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
		/*
		 * Capacity is one higher because of cyclic wrap-around, one cell will always be left empty so we can avoid
		 * the ambiguity of first and last equalling each other, it could mean an empty or full buffer. We need an
		 * empty buffer so we amend the full buffer case so that it technically stops one cell early.
		 * The user is "tricked" because they get a ring buffer of their chosen capacity, it's just implemented to be
		 * one slot bigger.
		 */
		ringBuffer = new double[capacity + 1]; //array of double with given capacity plus one 
		first = 0; //index trackers set to zero
		last = 0;
		length = capacity; //the real, usable length of the ring buffer.
	}
	
	/**
	 * Calculates the number of items currently in the buffer
	 * 
	 * @return number of items currently in buffer
	 */
	public int size() {
		if(first > last) { //check for cyclic wrap-around
			return last - first + length + 1; //last minus first = number of empty slots, add length to get size
		}
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
	 * @throws IllegalStateException if a double is enqueued to a full buffer
	 */
	
	public void enqueue(double x) {
		if(this.isFull()) { //throw an exception if full
			throw new IllegalStateException("Buffer is full.");
		}
		else if(last == length+1) { //wrap around if last equals length
			last = 0;
		}
		ringBuffer[last] = x; //add item, increment last
		last++;
	}
	
	/**
	 * Removes an item from the front of the ring buffer
	 * 
	 * @return the double removed from the ring buffer
	 * @throws NoSuchElementException if one attempts to dequeue from an empty buffer
	 */
	
	public double dequeue() {
		if(this.isEmpty()) {
			throw new NoSuchElementException("Buffer is empty.");
		}
		first++; //increment first
		if(first == length+1) { //wrap around if it equals length
			first = 0;
			return ringBuffer[length]; //return item that was removed
		}
		return ringBuffer[first-1];
	}
	
	/**
	 * Returns item from the front of the buffer but does not remove it
	 * 
	 * @return the double from the front of the buffer
	 * @throws NoSuchElementException if peek is called on an empty ring buffer
	 */
	public double peek() {
		if(this.isEmpty()) { //throw an exception if empty
			throw new NoSuchElementException("Buffer is empty.");
		}
		return ringBuffer[first]; //return first item in buffer
	}
	
	/**
	 * Returns the index of the least-recently inserted item. Testing method only. 
	 * 
	 * @return the value of the first index tracker
	 */
	public int getFirst() {
		return first;
	}
	
	/**
	 * Returns the index that is one more than the most recently inserted item. Testing method only.
	 * 
	 * @return the value of the last index tracker
	 */
	public int getLast() {
		return last;
	}
	
	public double[] getRingBuffer() {
		return ringBuffer;
	}
	
	public int getLength() {
		return length;
	}
}
