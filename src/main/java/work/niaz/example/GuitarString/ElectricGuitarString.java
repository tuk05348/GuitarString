/**
 * 
 */
package work.niaz.example.GuitarString;

/**
 * ElectricGuitarString
 * Implements an Electric Guitar String by modifying the Karplus-Strong algorithm to account for distortion and feedback
 */
public class ElectricGuitarString implements Instrument {

	private static int SAMPLING_RATE = 44100; //sampling rate
	private static double DECAY_FACTOR = 0.994; //decay factor
	private RingBuffer ringBuffer; //internal ring buffer
	private int simTime; //internal simulation time tracker
	
	/**
	 * Constructor to create an electric guitar at a specific frequency
	 * 
	 * @param frequency the frequency at which the electric guitar string plays at
	 */
	public ElectricGuitarString(double frequency) { //create a new GuitarString with a given frequency
		//divide the sampling rate by frequency and round up to the nearest integer
		ringBuffer = new RingBuffer((int) Math.ceil(((double) SAMPLING_RATE)/frequency));
		while(!ringBuffer.isFull()) { //Initialize the guitar string buffer with zeroes
			ringBuffer.enqueue(0);
		}
	}
	
	/**
	 * Randomize the contents of the internal ring buffer of the electric guitar string with values between -0.5 and 0.5
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
	 * Step the simulation one step forward and apply the Karplus Strong update, add the average of the first 
	 * two samples to the end of the ring buffer, remove the first sample
	 * Modify the update by distorting the added average
	 */
	@Override
	public void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		item *= 0.5;
		ringBuffer.enqueue(((1.97 * item) - distortion(item)) * DECAY_FACTOR); //enqueue average of two samples multiplied by decay factor
		//System.out.println(ringBuffer.peek());
		simTime++; //step simTime
	}
	
	/**
	 * Return the first value in the internal ring buffer of the electric guitar string
	 * 
	 * @return the value at the head of the internal ring buffer
	 */
	@Override
	public double sample() {
		return ringBuffer.peek();
	}
	
	/**
	 * Returns the current time in the electric guitar string simulation
	 * 
	 * @return simTime the current time in the simulation
	 */
	@Override
	public int time() {
		return simTime;
	}
	
	/**
	 * Distort a given sample using a soft clipping function provided by Charles Sullivan in his paper
	 * 
	 * @param sample the sample to distort
	 * @return the distortion of the sample
	 */
	public double distortion(double sample) {
		if(sample >= 0.5) { //if greater than 0.5, return positive threshold
			return 0.1;
		}
		else if((sample < 0.5) && (sample > -0.5)) { //if between 0.5 and -0.5, return sample minus sample to third power divided by 3
			return sample - (Math.pow(sample, 3)/3);
		}
		else { //if less than 0.5, return negative threshold
			return -0.1;
		}
	}

}
