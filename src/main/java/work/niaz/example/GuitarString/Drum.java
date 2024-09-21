package work.niaz.example.GuitarString;

import java.util.Random;

/**
 * Drum
 * Simulates the behavior of a drum when beat using the Ring Buffer API
 * and by applying the Karplus Strong algorithm to simulate its sound
 * Implements the Instrument Interface
 */
public class Drum implements Instrument {
	
	private static int SAMPLING_RATE = 44100; //sampling rate
	private static double DECAY_FACTOR = 1;	//decay factor
	private Random random;
	private RingBuffer ringBuffer; //internal ring buffer
	private int simTime; //simulation time tracker
	
	/**
	 * Constructor to create a drum with a given frequency
	 * 
	 * @param frequency the frequency the drum should play at
	 */
	public Drum(double frequency) {	//create a new drum with a given frequency
		ringBuffer = new RingBuffer((int) Math.ceil(((double) SAMPLING_RATE)/frequency));
		while(!ringBuffer.isFull()) { //initialize it with zeroes
			ringBuffer.enqueue(0);
		}
		random = new Random();
	}

	/**
	 * Initialize the internal ring buffer of the drum with random values between -0.5 and 0.5
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
	 * Step the simulation one step forward and apply the Karplus-Strong operation specific to a drum
	 * Take the average of the first two samples, randomly flip its sign, add it to the end, and remove the first sample
	 * Decay factor for a drum is 1
	 */
	@Override
	public void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		int num = random.nextBoolean() ? 1 : -1;
		item *= num;
		ringBuffer.enqueue(item * 0.5 * DECAY_FACTOR); //enqueue average of two samples multiplied by decay factor of 1
		simTime++; //step simTime
		
	}

	/**
	 * Return the first value of the internal ring buffer
	 * 
	 * @return the first value of the internal ring buffer
	 */
	@Override
	public double sample() {
		return ringBuffer.peek();
	}

	/**
	 * Return the current time in the instrument simulation
	 * 
	 * @return the current time in the instrument simulation
	 */
	@Override
	public int time() {
		return simTime;
	}

}
