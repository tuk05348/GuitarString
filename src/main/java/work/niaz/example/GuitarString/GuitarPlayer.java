package work.niaz.example.GuitarString;

import java.util.HashSet;

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
		HashSet<Instrument> played = new HashSet<>();

		for(int i=0; i<args[0].length(); i++) {
			int j = keyboard.indexOf(args[0].charAt(i));
			guitarStrings[j].vibrate();
			played.add(guitarStrings[j]);
			for(int w = 0; w < 44100; w++) {
				double sample = 0;
				for (Instrument instrument : played) {
					sample += instrument.sample();
				}
				StdAudio.play(sample);
				for (Instrument instrument : played) {
					instrument.tic();
				}
			}
		}
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}
}
