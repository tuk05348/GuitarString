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
	private String melodyFileString;
	private String harmonyFileString;
	private boolean polyCheck = false;
	
	
	public PolyphonicMusicPlayer(long delay, int totalCount, String audioFileString) {
		this.delay = delay;
		this.totalCount = totalCount;
		this.melodyFileString = audioFileString;
		this.polyCheck = true;
	}
	
	public PolyphonicMusicPlayer(long delay, String melodyFileString, String harmonyFileString) {
		this.delay = delay;
		this.totalCount = 2;
		this.melodyFileString = melodyFileString;
		this.harmonyFileString = harmonyFileString;
	}
	
	public void play() {
		if(this.polyCheck) {
			for(int i=0; i<totalCount; i++) {
				StdAudio.playInBackground(melodyFileString);
				try {
					TimeUnit.SECONDS.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			StdAudio.playInBackground(melodyFileString);
			try {
				TimeUnit.SECONDS.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StdAudio.playInBackground(harmonyFileString);
		}
		
	}
	public static void main(String[] args) {
		PolyphonicMusicPlayer polyphonicMusicPlayer = new PolyphonicMusicPlayer(0, "frere.wav", "stairway.wav");
		polyphonicMusicPlayer.play();
	}
}
