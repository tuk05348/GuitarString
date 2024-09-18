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
