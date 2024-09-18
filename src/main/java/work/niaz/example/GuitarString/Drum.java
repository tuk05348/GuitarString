package work.niaz.example.GuitarString;

public class Drum implements Instrument {
	
	private int SAMPLING_RATE = 44100; //sampling rate
	private RingBuffer ringBuffer;
	private int simTime;
	
	public Drum(double frequency) {
		ringBuffer = new RingBuffer((int) Math.ceil(((double) SAMPLING_RATE)/frequency));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public double sample() {
		return ringBuffer.peek();
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return simTime;
	}

}
