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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double sample() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return 0;
	}

}
