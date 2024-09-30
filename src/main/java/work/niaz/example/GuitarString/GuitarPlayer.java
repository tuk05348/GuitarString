package work.niaz.example.GuitarString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import edu.princeton.cs.algs4.StdAudio;

/**
 * GuitarPlayer
 * Plays a a text file of a sequence of notes with a 37 note Guitar built using the GuitarString API
 */
public class GuitarPlayer {
	private Path path; //file path of music file
	
	/**
	 * Constructor to create a GuitarPlayer that reads a file to play music
	 * 
	 * @param filename name of the .txt file to read from, should be in the root of the working directory of the project
	 */
	public GuitarPlayer(String filename) {
		try { //try to assign a path to the file in the working directory
			path = Paths.get(System.getProperty("user.dir") + "/" + filename);
		} catch (Exception e) { //catch the exception
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Play the sequence of notes in the file line by line
	 */
	public void play() {
		try { //play the note sequence line by line
			Files.lines(path).forEachOrdered(s -> playLine(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * PLays the sequence of notes from the file line by line and records the audio to a .wav file
	 * 
	 * @param audioFileString name of the .wav file to record the audio to
	 */
	public void playAndRecord(String audioFileString) {
		StdAudio.startRecording(); //start recording
		try { //play note sequence line by line
			Files.lines(path).forEachOrdered(s -> playLine(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		double[] audio = StdAudio.stopRecording(); //stop recording and store audio samples in an array of double
		StdAudio.save(audioFileString, audio); //use the array of audio samples to save the recording to a file 
	}
	
	/**
	 * Plays a string of a sequence of notes
	 * 
	 * @param line string of the current line of the text file containing sequence of notes to read from
	 */
	private void playLine(String line) {
		int samplingRate = 44100; //sampling rate to play it
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; //string of characters mapped to notes
		
		Instrument[] guitarStrings = new Instrument[37]; //Create a 37 note instrument, in this case a guitar
		for(int i=0; i<keyboard.length(); i++) { //use equal temperament to assign notes
			guitarStrings[i] = new GuitarString(Tuning.calculateFreq(i));
		}
		HashSet<Instrument> played = new HashSet<>(); //Keep a hashset of notes that were played so we don't have to tic all 37

		for(int i=0; i<line.length(); i++) { //Play each note character by character
			if(line.charAt(i) == 'F') { //Check for the 'F' tag, fast tempo, decrease sampling rate
				samplingRate = 11025;
			}
			else if(line.charAt(i) == 'S') { //Check for the 'S' tag, slow tempo, return sampling rate to standard
				samplingRate = 44100;
			}
			else if(line.charAt(i) == 'D') { //Check for the 'D' tag, dyad, vibrate two notes at the same time
				for(int k=i+1; k<i+3; k++) { //vibrate the next 2 notes
					int key = keyboard.indexOf(line.charAt(k));
					guitarStrings[key].vibrate();
					played.add(guitarStrings[key]);
				}
				i += 2; //increment i by 2
			}
			else if(line.charAt(i) == 'C') { //Check for the 'C' tag, chord, vibrate three notes at the same time
				for(int k=i+1; k<i+4; k++) { //vibrate the next 3 notes
					int key = keyboard.indexOf(line.charAt(k));
					guitarStrings[key].vibrate();
					played.add(guitarStrings[key]);
				}
				i += 3; //increment i by 3
			}
			else { //Vibrate a single note
				int key = keyboard.indexOf(line.charAt(i)); //get the note that was played from the keyboard mapping
				guitarStrings[key].vibrate(); //vibrate the note
				played.add(guitarStrings[key]); //add it to the set of played notes
			}
			
			for(int j = 0; j < samplingRate; j++) { //play each sample for 441000 iterations
				double sample = 0;
				for (Instrument instrument : played) { //for each played note add up the samples to get the superposition
					sample += instrument.sample();
				}
				StdAudio.play(sample); //play the sample
				for (Instrument instrument : played) { //tic each played instrument forward step
					instrument.tic();
				}
			}
		}
	}
	
	/**
	 * Test the guitar player by playing a text file of notes from the root of the working directory
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		GuitarPlayer guitarPlayer = new GuitarPlayer("star.txt"); //this plays "Twinkle Twinkle Little Star"
		guitarPlayer.play();
	}
}
