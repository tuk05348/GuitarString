package work.niaz.example.GuitarString;

import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdAudio;

/**
 * PolyphonicMusicPlayer
 * Plays two or more audio files simultaneously to create polyphonic music
 * Supports either a melody and a harmony (homophonic music), or the same melody played multiple times with an offset (polyphonic music)
 */
public class PolyphonicMusicPlayer {
	
	private long delay; //delay in seconds between playing the multiple audio files
	private int totalCount; //total number of audio files
	private String melodyFileString; //name of the audio file that is the melody
	private String harmonyFileString; //name of the audio file that is the harmony
	private boolean polyCheck = false; //boolean to check if the music is polyphonic or homophonic
	
	/**
	 * Constructor for if the music to be played is one melody played multiple times with an offset
	 * @param delay delay in seconds between the start of each melody being played
	 * @param totalCount total number of melodies to play
	 * @param audioFileString name of the audio file to which we play music from
	 */
	public PolyphonicMusicPlayer(long delay, int totalCount, String audioFileString) {
		this.delay = delay; //assign parameters to the given values in the constructor
		this.totalCount = totalCount;
		this.melodyFileString = audioFileString;
		this.polyCheck = true; //flip polyphonic check to true, music is polyphonic
	}
	
	/**
	 * Constructor to play homophonic music, two audio files are given, a melody and a harmony to support it
	 * @param delay delay in seconds between the start of the melody and start of the harmony
	 * @param melodyFileString name of the melody audio file
	 * @param harmonyFileString name of the harmony audio file
	 */
	public PolyphonicMusicPlayer(long delay, String melodyFileString, String harmonyFileString) {
		this.delay = delay; //delay parameter is set to given delay
		this.totalCount = 2; //total count is set to 2 as there are always 2 audio files in this constructor
		this.melodyFileString = melodyFileString; //melody and harmony are set
		this.harmonyFileString = harmonyFileString;
	}
	
	/**
	 * Plays audio tracks at the same time using StdAudio to create audio threads that run in the background
	 * Supports both polyphonic and homophonic music
	 */
	public void play() {
		if(this.polyCheck) { //Check if the music is polyphonic, if so
			for(int i=0; i<totalCount; i++) { //for the total number of tracks, play each track in the background
				StdAudio.playInBackground(melodyFileString);
				try {
					TimeUnit.SECONDS.sleep(delay); //delay after starting each track
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else { //if the music is homophonic
			StdAudio.playInBackground(melodyFileString); //play the melody in the background
			try {
				TimeUnit.SECONDS.sleep(delay); //delay for the given delay
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			StdAudio.playInBackground(harmonyFileString); //play the harmony in the background next
		}
		
	}
	
	/**
	 * Test the polyphonic music player by playing an audio track multiple times with a delay
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		PolyphonicMusicPlayer polyphonicMusicPlayer = new PolyphonicMusicPlayer(8, 3, "frere.wav");
		polyphonicMusicPlayer.play();
	}
}
