package work.niaz.example.GuitarString;

/**
 * Guitar String API
 * Simulates the behavior of a guitar string when plucked by using the Ring Buffer API
 */

public class GuitarString {
	
	private int samplingRate = 44100;
	private RingBuffer ringBuffer;
	private int simTime;

	public GuitarString(double frequency) {
		ringBuffer = new RingBuffer((int) Math.ceil(samplingRate/frequency));
	}
	
	public GuitarString(double[] init) {
		ringBuffer = new RingBuffer(init.length);
		for(int i=0; i<init.length; i++) {
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
