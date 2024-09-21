package work.niaz.example.GuitarString;

/**
 * Instrument interface defines the minimum methods required
 * to simulate a playable instrument using the Karplus-Strong algorithm
 */
public interface Instrument {
	
	/**
	 * Initializes an instrument with random values
	 */
	void vibrate();
	
	/**
	 * Applies the Karplus-Strong update to the instrument and moves time forward one step in the simulation
	 */
	void tic();
	
	/**
	 * Returns the value at the head of the internal ring buffer
	 * 
	 * @return value at the head of the ring buffer
	 */
	double sample();
	
	/**
	 * Returns the current time in the instrument simulation
	 * 
	 * @return current time in simulation
	 */
	int time();
}
