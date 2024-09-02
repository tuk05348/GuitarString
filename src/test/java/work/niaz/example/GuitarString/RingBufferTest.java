package work.niaz.example.GuitarString;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for simple App.
 */
public class RingBufferTest 
{
	int capacity = 10;
	RingBuffer ringBuffer;
	
	@BeforeAll
	public static void setUpClass() {
		
	}
	
	@AfterAll
	public static void tearDownClass() {
		
	}
	
	@BeforeEach
	public void setup() {
		ringBuffer = new RingBuffer(capacity);
	}
	
	@AfterEach
	public void tearDown() {
	}
	
    @Test
    public void testSize()
    {
    	for(int i=1; i<6; i++) {
    		ringBuffer.enqueue(i);
    	}
        assertEquals(5, ringBuffer.size(), "Size of buffer should equal number of items inserted");
    }
    
	@Test
	public void testPeek() {
		assertEquals(0, ringBuffer.peek(), 0.0001, "First element returned should equal zero for an empty ring buffer");
	}
	
	@Test
	public void testEnqueue() {
		ringBuffer.enqueue(1);
		assertEquals(1, ringBuffer.peek(), 0.0001, "After adding element in empty ring, first element should be 1");
	}
	
	@Test
	public void testDequeueReturn() {
		ringBuffer.enqueue(1);
		double item = ringBuffer.dequeue();
		assertEquals(1, item, 0.0001, "Returned item should equal item that was placed at front");
	}
	
	@Test
	public void testDequeue() {
		ringBuffer.enqueue(1);
		ringBuffer.enqueue(2);
		ringBuffer.dequeue();
		assertEquals(2, ringBuffer.peek(), 0.0001, "First element should be 2 after dequeue");
	}
	  
    @Test
    public void testCyclicWrapAroundLast() {
    	for(int i=0; i<10; i++) { //Fill up buffer
    		ringBuffer.enqueue(i);
    	}
    	ringBuffer.dequeue(); //Remove first element
    	ringBuffer.enqueue(11); //Add a new element
    	assertEquals(0, ringBuffer.getLastIndex(), 0.0001, "Last should be 0");
    }
    
    @Test
    public void testCyclicWrapAroundFirst() {
    	for(int i=0; i<10; i++) { //Add 10 elements
    		ringBuffer.enqueue(i);
    	}
    	for(int i=0; i<9; i++) { //Remove first 9 elements
    		ringBuffer.dequeue();
    	}
    	for(int i=0; i<2; i++) { //Add 2 more elements which wrap around
    		ringBuffer.enqueue(i);
    	}
    	for(int i=0; i<2; i++) { //Remove 2 elements, first should wrap around
    		ringBuffer.dequeue();
    	}
    	assertEquals(1, ringBuffer.peek(), 0.0001, "Element at front should equal 1 because of wrap around");
    	
    }
    
    @Test
    public void testCyclicWrapAroundSize() {
    	for(int i=0; i<10; i++) { //Add 10 elements
    		ringBuffer.enqueue(i);
    	}
    	for(int i=0; i<8; i++) { //Remove first 8 elements
    		ringBuffer.dequeue();
    	}
    	for(int i=0; i<2; i++) { //Add 2 more elements which wrap around
    		ringBuffer.enqueue(i);
    	}
    	assertEquals(4, ringBuffer.size(), 0.0001, "Size should equal four.");
    }
    
    @Test
    public void testIsEmpty() {
    	for(int i=0; i<10; i++) { //add 10 elements
    		ringBuffer.enqueue(i);
    	}
    	for(int i=0; i<9; i++) { //remove 9 elements
    		ringBuffer.dequeue();
    	}
    	assertEquals(false, ringBuffer.isEmpty(), "Ringbuffer should not be empty");
    }
    
    @Test
    public void testIsFull() {
    	for(int i=0; i<10; i++) {
    		ringBuffer.enqueue(i);
    	}
    	ringBuffer.dequeue();
    	assertEquals(false, ringBuffer.isFull(), "Ringbuffer should not be full");
    }
    
    @Test
    public void testEnqueueOnFull() {
    	for(int i=0; i<10; i++) {
    		ringBuffer.enqueue(i);
    	}
    	
    	FullRingBufferException exception = assertThrows(FullRingBufferException.class, () -> ringBuffer.enqueue(11));
    	assertEquals("Buffer is full.", exception.getMessage());
    }

}
