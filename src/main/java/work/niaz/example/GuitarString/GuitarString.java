package work.niaz.example.GuitarString;

/**
 * Guitar String API
 * Simulates the behavior of a guitar string when plucked by using the Ring Buffer API
 * Implements the Instrument Interface
 */

public class GuitarString implements Instrument {
	
	private static int SAMPLING_RATE = 44100; //sampling rate
	private static double DECAY_FACTOR = 0.994; //decay factor
	private RingBuffer ringBuffer; //internal ring buffer
	private int simTime; //internal simulation time tracker
	
	/**
	 * Constructor to create a new GuitarString using a given frequency
	 * 
	 * @param frequency the frequency the GuitarString should play at
	 */
	public GuitarString(double frequency) { //create a new GuitarString with a given frequency
		//divide the sampling rate by frequency and round up to the nearest integer
		ringBuffer = new RingBuffer((int) Math.ceil(((double) SAMPLING_RATE)/frequency));
		while(!ringBuffer.isFull()) { //Initialize the guitar string buffer with zeroes
			ringBuffer.enqueue(0);
		}
	}
	
	/**
	 * Constructor to create a new GuitarString using an array of doubles to initialize the internal ring buffer with
	 * For testing purposes
	 * 
	 * @param init array of doubles that are the starting values of the GuitarString's ring buffer
	 */
	public GuitarString(double[] init) { //create a new GuitarString with a given array
		ringBuffer = new RingBuffer(init.length + 1); //create ring buffer
		for(int i=0; i<init.length; i++) { //initialize ring buffer with the array
				ringBuffer.enqueue(init[i]);
		}
	}
	
	/**
	 * Randomize the ring buffer of the guitar string with random values between 0.5 and -0.5
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
	 * Step the simulation one unit forward and add to the end of the buffer the average of the first two
	 * samples multiplied by a decay factor of 0.994, dequeue the first sample.
	 */
	@Override
	public void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		ringBuffer.enqueue(item * 0.5 * DECAY_FACTOR); //enqueue average of two samples multiplied by decay factory
		simTime++; //step simTime
	}
	
	/**
	 * Return the first value in the ring buffer of the guitar string
	 * 
	 * @return the value at the head of the ring buffer
	 */
	@Override
	public double sample() {
		return ringBuffer.peek();
	}
	
	/**
	 * Return how many times tic has been called
	 * 
	 * @return the number of times tic has been called
	 */
	@Override
	public int time() {
		return simTime;
	}
	
}
