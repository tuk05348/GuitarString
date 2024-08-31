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
	RingBuffer ringbuffer = new RingBuffer(capacity);

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
		assertEquals("First element returned should equal zero for an empty ring buffer", 0, ringbuffer.peek(), 0.0001);
	}
	
	@Test
	public void testEnqueue() {
		ringbuffer.enqueue(1);
		assertEquals("After adding element in empty ring, first element should be 1", 1, ringbuffer.peek(), 0.0001);
	}
	
	@Test
	public void testDequeueReturn() {
		ringbuffer.enqueue(1);
		double item = ringbuffer.dequeue();
		assertEquals("Returned item should equal item that was placed at front", 1, item, 0.0001);
	}

}
