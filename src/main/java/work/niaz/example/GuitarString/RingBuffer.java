package work.niaz.example.GuitarString;

public class RingBuffer {
	
	private double[] ringBuffer;
	private int first, last;
	
	public RingBuffer(int capacity) {
		this.ringBuffer = new double[capacity];
		this.first = 0;
		this.last = 0;
	}
	
	int size() {
		return 0;
	}
	
	boolean isEmpty() {
		return false;
	}
	
	boolean isFull() {
		return false;
	}
	
	void enqueue(double x) {
		ringBuffer[this.last] = x;
		this.last++;
	}
	
	double dequeue() {
		return ringBuffer[this.first];
	}
	
	double peek() {
		return this.ringBuffer[this.first];
	}
}
