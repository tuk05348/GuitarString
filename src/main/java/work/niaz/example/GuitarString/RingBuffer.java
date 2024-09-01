package work.niaz.example.GuitarString;

public class RingBuffer {
	
	private double[] ringBuffer;
	private int first, last;
	
	public RingBuffer(int capacity) {
		ringBuffer = new double[capacity];
		first = 0;
		last = 0;
	}
	
	int size() {
		return last - first;
	}
	
	boolean isEmpty() {
		return false;
	}
	
	boolean isFull() {
		return false;
	}
	
	void enqueue(double x) {
		if(last == ringBuffer.length) {
			last = 0;
			ringBuffer[last] = x;
		}
		else {
			ringBuffer[last] = x;
			last++;
		}
	}
	
	double dequeue() {
		if(first == ringBuffer.length) {
			first = 0;
			return ringBuffer[ringBuffer.length - 1];
		}
		else {
			first++;
			return ringBuffer[first-1];
		}
	}
	
	double peek() {
		return ringBuffer[first];
	}
	
	int getFirstIndex() {
		return first;
	}
	
	int getLastIndex() {
		return last;
	}
}
