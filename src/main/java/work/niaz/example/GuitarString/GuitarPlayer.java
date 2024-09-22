package work.niaz.example.GuitarString;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import com.sun.tools.javac.launcher.Main;

import edu.princeton.cs.algs4.StdAudio;

public class GuitarPlayer {
	public static void main(String[] args) {
		URI path = null;
		try {
			path = GuitarPlayer.class.getResource("/stairway.txt").toURI();
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Files.lines(Paths.get(path)).forEachOrdered(s -> play(s));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}
	
	public static void play(String line) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		
				Instrument[] guitarStrings = new Instrument[37];
				for(int i=0; i<keyboard.length(); i++) {
					guitarStrings[i] = new GuitarString(calculateFreq(i));
				}
				HashSet<Instrument> played = new HashSet<>();
		
				for(int i=0; i<line.length(); i++) {
					int j = keyboard.indexOf(line.charAt(i));
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
}
