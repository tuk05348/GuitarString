/**
 * 
 */
package work.niaz.example.GuitarString;

/**
 * 
 */
public class ElectricGuitarString implements Instrument {

	private static int SAMPLING_RATE = 44100; //sampling rate
	private static double DECAY_FACTOR = 0.994; //decay factor
	private RingBuffer ringBuffer; //internal ring buffer
	private int simTime; //internal simulation time tracker
	
	public ElectricGuitarString(double frequency) { //create a new GuitarString with a given frequency
		//divide the sampling rate by frequency and round up to the nearest integer
		ringBuffer = new RingBuffer((int) Math.ceil(((double) SAMPLING_RATE)/frequency));
		while(!ringBuffer.isFull()) { //Initialize the guitar string buffer with zeroes
			ringBuffer.enqueue(0);
		}
	}
	
	@Override
	public void vibrate() {
		while(!ringBuffer.isEmpty()) { //replace all existing items with random values
			ringBuffer.dequeue();
		}
		while(!ringBuffer.isFull()) { //if the buffer isn't full, fill in the rest of the buffer with random values
			ringBuffer.enqueue(Math.random() - 0.5);
		}		
	}

	@Override
	public void tic() {
		double item = ringBuffer.dequeue() + ringBuffer.peek(); //order matters, dequeue first, then peek, to get first two samples
		ringBuffer.enqueue(item * 0.5 * DECAY_FACTOR); //enqueue average of two samples multiplied by decay factory
		simTime++; //step simTime
	}

	@Override
	public double sample() {
		return ringBuffer.peek();
	}

	@Override
	public int time() {
		return simTime;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
