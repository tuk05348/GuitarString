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

    @Test
    public void testSize()
    {
        assertEquals("Size of buffer should equal capacity", capacity, ringbuffer.size());
    }
}
