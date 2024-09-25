/**
 * 
 */
package work.niaz.example.GuitarString;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.princeton.cs.algs4.StdAudio;

/**
 * 
 */
public class PolyphonicMusicPlayer {
	
	private long delay;
	private int totalCount;
	private String audioFileString;
	
	public PolyphonicMusicPlayer(long delay, int totalCount, String audioFileString) {
		this.delay = delay;
		this.totalCount = totalCount;
		this.audioFileString = audioFileString;
	}
	
	public void play() {
		for(int i=0; i<totalCount; i++) {
			StdAudio.playInBackground(audioFileString);
			try {
				TimeUnit.SECONDS.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		PolyphonicMusicPlayer polyphonicMusicPlayer = new PolyphonicMusicPlayer(8, 3, "frere.wav");
		polyphonicMusicPlayer.play();
	}
}
