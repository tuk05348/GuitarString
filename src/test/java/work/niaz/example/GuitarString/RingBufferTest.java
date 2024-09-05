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
    public void testSize() { //test size of buffer after enqueuing and dequeuing
    	enqueueMultiplier(5);
    	dequeueMultiplier(3);
        assertEquals(2, ringBuffer.size(), "Size of buffer should equal number of items currently in buffer");
    }
    
	@Test
	public void testPeek() { //test seeing what the first element of the buffer is
		assertEquals(0, ringBuffer.peek(), 0.0001, "First element returned should equal 0 for an empty ring buffer.");
	}
	
	@Test
	public void testEnqueue() { //test enqueuing
		ringBuffer.enqueue(1);
		assertEquals(1, ringBuffer.peek(), 0.0001, "After adding element in empty ring, first element should be 1");
	}
	
	@Test
	public void testDequeueReturn() {
		ringBuffer.enqueue(1);
		double item = ringBuffer.dequeue(); //test checking the value of what dequeue returns
		assertEquals(1, item, 0.0001, "Returned item should equal item that was placed at front");
	}
	
	@Test
	public void testDequeue() {
		enqueueMultiplier(2);
		ringBuffer.dequeue(); //test that dequeue removes the item
		assertEquals(1, ringBuffer.peek(), 0.0001, "First element should be 1 after dequeue");
	}
	  
    @Test
    public void testCyclicWrapAroundLast() {
    	enqueueMultiplier(10); //add 10 elements
    	ringBuffer.dequeue(); //remove an element from the front
    	ringBuffer.enqueue(11); //add a new element to the front
    	dequeueMultiplier(9); //remove 9 elements, only element remaining should be the front element
    	assertEquals(11, ringBuffer.peek(), 0.0001, "First element should be 11");
    }
    
    @Test
    public void testCyclicWrapAroundFirst() {
    	enqueueMultiplier(10); //add 10 elements
    	dequeueMultiplier(9); //remove 9 elements
    	enqueueMultiplier(2); //add 2 elements, both should wrap around to the front
    	dequeueMultiplier(2); //remove 2 elements, leaving one element at the front
    	assertEquals(1, ringBuffer.peek(), 0.0001, "Element at front should equal 1 because of wrap around");
    	
    }
    
    @Test
    public void testCyclicWrapAroundSize() {
    	enqueueMultiplier(10); //add 10 elements
    	dequeueMultiplier(8); //remove 8 elements
    	enqueueMultiplier(2); //add 2 elements, 4 should be in buffer
    	assertEquals(4, ringBuffer.size(), 0.0001, "Size should equal four.");
    }
    
    @Test
    public void testIsEmpty() {
    	enqueueMultiplier(9); //add 10 elements
    	dequeueMultiplier(8); //remove 9 elements
    	assertEquals(false, ringBuffer.isEmpty(), "Ringbuffer should not be empty");
    }
    
    @Test
    public void testIsFull() {
    	enqueueMultiplier(9); //add 10 elements
    	ringBuffer.dequeue(); //remove one element
    	assertEquals(false, ringBuffer.isFull(), "Ringbuffer should not be full");
    }
    
    @Test
    public void testEnqueueOnFull()  {
    	enqueueMultiplier(10); //add 10 elements, attempt to add another and check if exception is thrown
    	IllegalStateException exception = assertThrows(IllegalStateException.class, () -> ringBuffer.enqueue(11));
    	assertEquals("Buffer is full.", exception.getMessage());
    }
    
    @Test
    public void testDequeueOnFull()  { //attempt to dequeue an empty buffer and check if exception is thrown
    	NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> ringBuffer.dequeue());
    	assertEquals("Buffer is empty.", exception.getMessage());
    }

}
