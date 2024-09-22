package work.niaz.example.GuitarString;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import edu.princeton.cs.algs4.StdAudio;

public class GuitarPlayer {
	private URI path;
	public GuitarPlayer(String filename) {
		try {
			path = GuitarPlayer.class.getResource("/" + filename).toURI();
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}
	
	public void playMusic() {
		try {
			Files.lines(Paths.get(path)).forEachOrdered(s -> play(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void play(String line) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		
		Instrument[] guitarStrings = new Instrument[37];
		for(int i=0; i<keyboard.length(); i++) {
			guitarStrings[i] = new GuitarString(calculateFreq(i));
		}
		HashSet<Instrument> played = new HashSet<>();

		for(int i=0; i<line.length(); i++) {
			int key = keyboard.indexOf(line.charAt(i));
			guitarStrings[key].vibrate();
			played.add(guitarStrings[key]);
			for(int j = 0; j < 44100; j++) {
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
	
	public static void main(String[] args) {
		GuitarPlayer guitarPlayer = new GuitarPlayer("star.txt");
		guitarPlayer.playMusic();
	}
}
