package work.niaz.example.GuitarString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import edu.princeton.cs.algs4.StdAudio;

public class GuitarPlayer {
	private Path path;
	public GuitarPlayer(String filename) {
		try {
			path = Paths.get(System.getProperty("user.dir") + "/" + filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void play() {
		try {
			Files.lines(path).forEachOrdered(s -> playLine(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playAndRecord(String audioFileString) {
		StdAudio.startRecording();
		try {
			Files.lines(path).forEachOrdered(s -> playLine(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		double[] audio = StdAudio.stopRecording();
		StdAudio.save(audioFileString, audio);
	}
	
	private void playLine(String line) {
		int samplingRate = 44100;
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		
		Instrument[] guitarStrings = new Instrument[37];
		for(int i=0; i<keyboard.length(); i++) {
			guitarStrings[i] = new GuitarString(Tuning.calculateFreq(i));
		}
		HashSet<Instrument> played = new HashSet<>();

		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i) == 'F') {
				samplingRate = 22050;
			}
			else if(line.charAt(i) == 'S') {
				samplingRate = 44100;
			}
			else if(line.charAt(i) == 'D') {
				for(int k=i+1; k<i+3; k++) {
					guitarStrings[keyboard.indexOf(line.charAt(k))].vibrate();
					played.add(guitarStrings[keyboard.indexOf(line.charAt(k))]);
				}
				i += 2;
			}
			else if(line.charAt(i) == 'C') {
				for(int k=i+1; k<i+4; k++) {
					guitarStrings[keyboard.indexOf(line.charAt(k))].vibrate();
					played.add(guitarStrings[keyboard.indexOf(line.charAt(k))]);
				}
				i += 3;
			}
			else {
				int key = keyboard.indexOf(line.charAt(i));
				guitarStrings[key].vibrate();
				played.add(guitarStrings[key]);
			}
			
			for(int j = 0; j < samplingRate; j++) {
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
		GuitarPlayer guitarPlayer = new GuitarPlayer("frere.txt");
		guitarPlayer.play();
	}
}
