package work.niaz.example.GuitarString;

import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

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
	 * Updates the delay value with a new one
	 * 
	 * @param delay how long the new delay should be in seconds
	 */
	public void updateDelay(long delay) {
		this.delay = delay;
	}
	
	/**
	 * Updates the melody with a new audio file
	 * 
	 * @param audioFileString new audio file to use for melody
	 */
	public void updateMelody(String audioFileString) {
		this.melodyFileString = audioFileString;
	}
	
	/**
	 * Updates the existing harmony file with a new audio file
	 * 
	 * @param audioFileString new audio file to use for harmony
	 */
	public void updateHarmony(String audioFileString) {
		this.harmonyFileString = audioFileString;
	}
	
	/**
	 * Converts a polyphonic track to a homophonic track with one melody and one harmony by adding a harmony and a new delay
	 * 
	 * @param audioFileString new audio file for the harmony track
	 * @param delay new delay between melody and harmony
	 */
	public void addHarmony(String audioFileString, long delay) {
		this.polyCheck = false;
		this.delay = delay;
		this.totalCount = 2;
		this.harmonyFileString = audioFileString;
	}
	
	/**
	 * Converts a homophonic track to a polyphonic track with one melody played multiple times with a new delay added
	 * 
	 * @param totalCount total number of melodies to be played
	 * @param delay new delay for the melodies to be offset
	 */
	public void removeHarmony(int totalCount, long delay) {
		this.polyCheck = true;
		this.delay = delay;
		this.totalCount = totalCount;
	}
	/**
	 * Saves the current polyphonic music of the Player object to a file
	 * File name must include .wav
	 * @param mixFileString filename to save polyphonic music to, must include .wav format
	 */
	public void save(String mixFileString) {
		int delay = (int) (this.delay * 44100) - 1; //Convert delay from seconds to samples, subtract 1 for zero-indexing
		double[] mix; //create an array of double for the mixed samples
		double[] melody = StdAudio.read(melodyFileString); //read the melody
		if(this.polyCheck) { //if polyphonic, mix the same melody multiple times
			mix = mixMelodyMultipleTimes(melody, delay);
		}
		else { //if homophonic mix the melody and harmony
			double[] harmony = StdAudio.read(harmonyFileString); //read the harmony file
			mix = mixMelodyAndHarmony(melody, harmony, delay);
		}
		StdAudio.save(mixFileString, mix); //save the mixed array of samples to a wav file
	}
	
	/**
	 * Mixes the same melody multiple times with the given delay to save polyphonic music to a file
	 * @param melody array of double of the samples of the melody file
	 * @param delay delay in number of samples
	 * @return array of double of new samples of the melody mixed with itself multiple times
	 */
	private double[] mixMelodyMultipleTimes(double[] melody, int delay) {
		int length = (totalCount * melody.length) - ((totalCount - 1) * (melody.length - delay));
		double[] mix = new double[length]; //create a new array of double
		int start = 0; //index where each successive melody starts
		for(int i=0; i<totalCount; i++) { //mix the melody the given number of times
			for(int j=0; j<melody.length; j++) {
				mix[j+start] += melody[j]; //add the value of melody to each index in mix to support mixing and superposition of samples
			}
			start += delay; //increment the start by delay which allows for mixing as we iterate over shared indices
		}
		return mix;
	}
	
	/**
	 * Mix a melody and harmony together to create homophonic music
	 * @param melody array of double of samples of the melody
	 * @param harmony array of double of samples of the harmony
	 * @param delay number of samples by which to delay
	 * @return array of double of the mixed samples of the melody and harmony
	 */
	private double[] mixMelodyAndHarmony(double[] melody, double[] harmony, int delay) {
		double[] mix; //create the array of double
		if (melody.length < (harmony.length + delay)){ //check if the melody is shorter than the harmony plus the delay
			mix = new double[harmony.length + delay]; //if so, the new array will be the harmony length and delay as they fully contain it
			for(int i=0; i < mix.length; i++) { //iterate across array
				if(i < delay) { //before delay add only melody
					mix[i] = melody[i];
				}
				else if((i >= delay) && (i < melody.length)) { //between delay and length of melody add melody and harmony
					mix[i] = melody[i] + harmony[i - delay]; //offset harmony by delay so we can start harmony index at zero
				}
				else { //after the melody is finished, add only harmony
					mix[i] = harmony[i - delay];
				}
			}
		}
		else { //if the melody is equal or longer than the delay plus harmony
			mix = new double[melody.length]; //create new array same length as melody
			for(int i=0; i < mix.length; i++) { //iterate across new array
				if(i < delay) { //if less than delay add only melody
					mix[i] = melody[i];
				}
				else if((i >= delay) && (i < harmony.length-1+delay)) { //if equal/greater than delay and between harmony
					//length of harmony minus one to get index of harmony, add delay to get index relative to melody
					mix[i] = melody[i] + harmony[i - delay]; //add melody and harmony
				}
				else { //once harmony finishes, add only melody
					mix[i] = melody[i];
				}
			}
		}
		return mix; //return the new array
	}
	
	/**
	 * Test the polyphonic music player by playing an audio track multiple times with a delay
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		PolyphonicMusicPlayer polyphonicMusicPlayer = new PolyphonicMusicPlayer(8, 3, "frere.wav");
		//polyphonicMusicPlayer.play();
		polyphonicMusicPlayer.save("mix5.wav");
	}
}
