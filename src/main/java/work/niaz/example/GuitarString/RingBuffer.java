package work.niaz.example.GuitarString;

public class RingBuffer {
	
	private double[] ringBuffer;
	
	public RingBuffer(int capacity) {
		this.ringBuffer = new double[capacity];
	}
	
	int size() {
		return ringBuffer.length;
	}
	
	boolean isEmpty() {
		return false;
	}
	
	boolean isFull() {
		return false;
	}
	
	void enqueue(double x) {
		
	}
	
	double dequeue() {
		return 0;
	}
	
	double peek() {
		return 0;
	}
}
