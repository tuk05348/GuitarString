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
	public void testPeek() {
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> ringBuffer.peek());
		assertEquals("Buffer is empty.", exception.getMessage());
	}
}
