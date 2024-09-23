package work.niaz.example.GuitarString;

/**
 * Tuning class to hold methods with formulas to calculate frequencies for playable notes for instruments
 */
public final class Tuning {
	
	private Tuning() {};
	/**
	 * Calculates frequency using equal temperament
	 * 
	 * @param interval interval of the 12 tone octave of the frequency we want
	 * @return Frequency associated with that key using equal temperament formula
	 */
	public static double calculateFreq(int interval) {
		return 440 * Math.pow(1.05956, interval - 24);
	}
}
