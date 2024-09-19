package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarPlayer {
	public static void main(String[] args) {
		
		System.out.println(args[0]);
		
		if(args.length < 1) {
			throw new IllegalArgumentException("Too few arguments.");
		}
		
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		
		Instrument[] guitarStrings = new Instrument[37];
		for(int i=0; i<keyboard.length(); i++) {
			guitarStrings[i] = new GuitarString(calculateFreq(i));
		}
		
		char c = args[0].charAt(0);
		int i = keyboard.indexOf(c);
		guitarStrings[i].vibrate();
		while(true) {
			StdAudio.play(guitarStrings[i].sample());
			guitarStrings[i].tic();
		}
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}
}
