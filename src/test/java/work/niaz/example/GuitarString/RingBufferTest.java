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
}
