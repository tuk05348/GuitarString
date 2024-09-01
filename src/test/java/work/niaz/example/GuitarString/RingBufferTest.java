package work.niaz.example.GuitarString;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RingBufferTest 
{
	int capacity = 10;
	RingBuffer ringBuffer = new RingBuffer(capacity);

    /*@Test
    public void testSize()
    {
        assertEquals("Size of buffer should equal capacity", capacity, ringbuffer.size());
    }
    
    @Test
    public void testIsEmpty() {
    	assertEquals("Last buffer index should be ", capacity, capacity);
    }*/
	
	@Test
	public void testPeek() {
		assertEquals("First element returned should equal zero for an empty ring buffer", 0, ringBuffer.peek(), 0.0001);
	}
	
	@Test
	public void testEnqueue() {
		ringBuffer.enqueue(1);
		assertEquals("After adding element in empty ring, first element should be 1", 1, ringBuffer.peek(), 0.0001);
	}
	
	@Test
	public void testDequeueReturn() {
		ringBuffer.enqueue(1);
		double item = ringBuffer.dequeue();
		assertEquals("Returned item should equal item that was placed at front", 1, item, 0.0001);
	}
	
	@Test
	public void testDequeue() {
		ringBuffer.enqueue(1);
		ringBuffer.enqueue(2);
		ringBuffer.dequeue();
		assertEquals("First element should be 2 after dequeue", 2, ringBuffer.peek(), 0.0001);
	}

}
