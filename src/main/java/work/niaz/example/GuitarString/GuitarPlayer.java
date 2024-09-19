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

		for(int i=0; i<args[0].length(); i++) {
			int j = keyboard.indexOf(args[0].charAt(i));
			guitarStrings[j].vibrate();
			for(int w = 0; w < 88200; w++) {
				StdAudio.play(guitarStrings[j].sample());
				guitarStrings[j].tic();
			}
		}
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}
}
