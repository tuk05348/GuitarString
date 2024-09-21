package work.niaz.example.GuitarString;

/**
 * HarpString
 * 
 * Simulates the behavior of a harp string when plucked using the Ring Buffer API
 * and by applying the Karplus-Strong Algorithm to simulate its sound
 * Implements the Instrument Interface
 */
public class HarpString implements Instrument {
	
	private int SAMPLING_RATE = 44100; //sampling rate
	private RingBuffer ringBuffer; //internal ring buffer
	private int simTime; //simulation time tracker
	
	/**
	 * Constructor to create a HarpString with a given frequency
	 * 
	 * @param frequency the frequency the harp string should play
	 */
	public HarpString(double frequency) {
		//double the size of the ring buffer to account for resonance
		//divide sampling rate by frequency, round up and double it
		int capacity = 2 * (int) Math.ceil(((double) SAMPLING_RATE)/frequency);
		ringBuffer = new RingBuffer(capacity);
		while(!ringBuffer.isFull()) { //initialize ring buffer with zeroes
			ringBuffer.enqueue(0);
		}
	}

	/**
	 * Initialize the internal ring buffer of the harp with random values between 0.5 and -0.5
	 */
	@Override
	public void vibrate() {
		while(!ringBuffer.isEmpty()) { //replace all existing items with random values
			ringBuffer.dequeue();
		}
		while(!ringBuffer.isFull()) { //if the buffer isn't full, fill in the rest of the buffer with random values
			ringBuffer.enqueue(Math.random() - 0.5);
		}		
	}

	/**
	 * Step the simulation one step forward and apply the Karplus-Strong operation
	 * Multiply the average of the first two samples with the decay factor -0.997 and delete the first sample
	 */
	@Override
	public void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		ringBuffer.enqueue(item * 0.5 * -0.997); //enqueue average of two samples multiplied by decay factory
		simTime++; //step simTime	
	}

	/**
	 * Returns the first value of the internal ring buffer
	 * 
	 * @return first value of the internal ring buffer
	 */
	@Override
	public double sample() {
		return ringBuffer.peek();
	}

	/**
	 * Returns the current time in the instrument simulation
	 * 
	 * @return current time in instrument simulation
	 */
	@Override
	public int time() {
		return simTime;
	}

}
