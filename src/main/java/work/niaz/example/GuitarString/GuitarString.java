package work.niaz.example.GuitarString;

/**
 * Guitar String API
 * Simulates the behavior of a guitar string when plucked by using the Ring Buffer API
 */

public class GuitarString {
	
	private int samplingRate = 44100; //sampling rate
	private RingBuffer ringBuffer;
	private int simTime;

	public GuitarString(double frequency) { //create a new GuitarString with a given frequency
		//divide the sampling rate by frequency and round up to the nearest integer
		ringBuffer = new RingBuffer((int) Math.ceil(samplingRate/frequency));
	}
	
	public GuitarString(double[] init) { //create a new GuitarString with a given array
		ringBuffer = new RingBuffer(init.length + 1); //create ring buffer
		for(int i=0; i<init.length; i++) { //initialize ring buffer with the array
				ringBuffer.enqueue(init[i]);
		}
	}
	
	/**
	 * Randomize the ring buffer of the guitar string with random values between 0.5 and -0.5
	 */
	void pluck() {
		for(int i=0; i<ringBuffer.size(); i++) { //replace all existing items with random values
			ringBuffer.dequeue();
			ringBuffer.enqueue(Math.random() - 0.5);
		}
		while(!ringBuffer.isFull()) { //if the buffer isn't full, fill in the rest of the buffer with random values
			ringBuffer.enqueue(Math.ceil(Math.random() - 0.5));
		}
	}
	
	/**
	 * Step the simulation one unit forward and add to the end of the buffer the average of the first two
	 * samples multiplied by a decay factor of 0.994, dequeue the first sample.
	 */
	void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		ringBuffer.enqueue(item * 0.5 * 0.994); //enqueue average of two samples multiplied by decay factory
		simTime++; //step simTime
	}
	
	/**
	 * Return the first value in the ring buffer of the guitar string
	 * 
	 * @return the value at the head of the ring buffer
	 */
	double sample() {
		return ringBuffer.peek();
	}
	
	/**
	 * Return how many times tic has been called
	 * 
	 * @return the number of times tic has been called
	 */
	int time() {
		return simTime;
	}
}
