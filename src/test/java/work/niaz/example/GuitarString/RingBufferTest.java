package work.niaz.example.GuitarString;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit tests for the Ring Buffer API.
 */

public class RingBufferTest 
{
	int capacity = 10; //create the ring buffer and set capacity
	RingBuffer ringBuffer;
	
	@BeforeAll
	public static void setUpClass() {
		
	}
	
	@AfterAll
	public static void tearDownClass() {
		
	}
	
	@BeforeEach
	public void setup() {
		ringBuffer = new RingBuffer(capacity); //for each test set up a new ring buffer object
	}
	
	@AfterEach
	public void tearDown() {
	}
	
	/*
	 * Helper Methods, do multiple enqueues and dequeues at once
	 */
	
	void enqueueMultiplier(int n)  {
		for(int i=0; i<n; i++) {
			ringBuffer.enqueue(i);
		}
	}
	
	void dequeueMultiplier(int n)  {
		for(int i=0; i<n; i++) {
			ringBuffer.dequeue();
		}
	}
	
	@Test
	public void testPeekOnEmptyBuffer() {
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> ringBuffer.peek());
		assertEquals("Buffer is empty.", exception.getMessage());
	}
	
	@Test
	public void testSizeOnEmptyBuffer() {
		assertEquals(0, ringBuffer.size(), 0.0001, "Size should be zero for an empty buffer.");
	}
	
	@Test
	public void testSizeOnNonEmptyBuffer() {
		ringBuffer.enqueue(1);
		assertEquals(1, ringBuffer.size(), 0.0001, "Size should be 1 after 1 enqueue.");
	}
	
	@Test
	public void testIsEmptyTrueForEmpty() {
		assertEquals(true, ringBuffer.isEmpty(), "Should return true as the ring buffer is empty.");
	}
	
	@Test
	public void testIsEmptyFalseForNonEmpty() {
		ringBuffer.enqueue(1);
		assertEquals(false, ringBuffer.isEmpty(), "Should return false as the ring buffer is not empty.");
	}
	
	@Test
	public void testIsFullTrueForFull() {
		enqueueMultiplier(10);
		assertEquals(true, ringBuffer.isFull(), "Should return true for full buffer.");
	}
	
	@Test
	public void testIsFullFalseForNotFull() {
		enqueueMultiplier(5);
		assertEquals(false, ringBuffer.isFull(), "Should return false for not full buffer.");
	}
	
	@Test
	public void testPeekOnNonEmptyBuffer() {
		ringBuffer.enqueue(1);
		assertEquals(1, ringBuffer.peek(), 0.0001, "Peek should return first element enqueued to buffer.");
	}
	
	@Test
	public void testEnqueue() {
		ringBuffer.enqueue(1);
		ringBuffer.enqueue(2);
		assertEquals(1,  ringBuffer.peek(), 0.0001, "Enqueuing should add an element, not overwrite the first.");
	}
	
	@Test
	public void testEnqueueOnFullBuffer() {
		enqueueMultiplier(10);
		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> ringBuffer.enqueue(11));
		assertEquals("Buffer is full.", exception.getMessage());
	}
	
	@Test
	public void testEnqueueCyclicWrapAround() {
		enqueueMultiplier(10);
		ringBuffer.dequeue();
		ringBuffer.enqueue(11);
		assertEquals(1, ringBuffer.getLast(), "Last index should be 1.");
	}
	
	@Test
	public void testDequeueReturn() {
		ringBuffer.enqueue(1);
		double item = ringBuffer.dequeue();
		assertEquals(1, item, 0.0001, "Dequeue should return item that was first.");
	}
	
	@Test
	public void testDequeueMoveForward() {
		ringBuffer.enqueue(1);
		ringBuffer.enqueue(2);
		ringBuffer.dequeue();
		assertEquals(2, ringBuffer.peek(), 0.0001, "Head of ring buffer should be at second element.");
	}
	
	@Test
	public void testDequeueOnEmptyBuffer() {
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> ringBuffer.dequeue());
		assertEquals("Buffer is empty.", exception.getMessage());
	}
	
	@Test
	public void testDequeueCyclicWrapAround() {
		enqueueMultiplier(10);
		dequeueMultiplier(9);
		enqueueMultiplier(2);
		dequeueMultiplier(2);
		assertEquals(1, ringBuffer.getFirst(), "First should equal zero, start of buffer.");
	}
	
	@Test
	public void testGetFirst() {
		enqueueMultiplier(2);
		ringBuffer.dequeue();
		assertEquals(1, ringBuffer.getFirst(), "First index should be 1.");
	}
	
	@Test
	public void testGetLast() {
		enqueueMultiplier(2);
		assertEquals(2, ringBuffer.getLast(), "Last index should be 2.");
	}
}
