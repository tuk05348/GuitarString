package work.niaz.example.GuitarString;

/**
 * Guitar String API
 * Simulates the behavior of a guitar string when plucked by using the Ring Buffer API
 */

public class GuitarString {
	
	private int samplingRate = 44100;
	private RingBuffer ringBuffer;
	private int simTime;

	public GuitarString(double frequency) { //create a new GuitarString with a given frequency
		//divide the sampling rate by frequency and round up to the nearest integer
		ringBuffer = new RingBuffer((int) Math.ceil(samplingRate/frequency));
	}
	
	public GuitarString(double[] init) { //create a new GuitarString with a given array
		ringBuffer = new RingBuffer(init.length); //create ring buffer
		for(int i=0; i<init.length; i++) { //initialize ring buffer with the array
			try {
				ringBuffer.enqueue(init[i]);
			} catch (FullRingBufferException e) {
				System.out.println("Buffer is full, unable to enqueue to buffer.");;
			}
		}
	}
	
	void pluck() {
		
	}
	
	void tic() {
		
	}
	
	double sample() {
		return 0;
	}
	
	int time() {
		return 0;
	}
}
