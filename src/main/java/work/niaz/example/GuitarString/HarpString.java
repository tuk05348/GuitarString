package work.niaz.example.GuitarString;

public class HarpString implements Instrument {
	
	private int SAMPLING_RATE = 44100; //sampling rate
	private RingBuffer ringBuffer;
	private int simTime;
	
	public HarpString(double frequency) {
		int capacity = 2 * (int) Math.ceil(((double) SAMPLING_RATE)/frequency);
		ringBuffer = new RingBuffer(capacity);
		while(!ringBuffer.isFull()) {
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
		ringBuffer.enqueue(item * 0.5 * -0.997); //enqueue average of two samples multiplied by decay factory
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

}
