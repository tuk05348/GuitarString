/**
 * 
 */
package work.niaz.example.GuitarString;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class PolyphonicGuitarPlayer {
	
	private long delay;
	private int totalCount;
	private String filename;
	
	public PolyphonicGuitarPlayer(long delay, int totalCount, String filename) {
		this.delay = delay;
		this.totalCount = totalCount;
		this.filename = filename;
	}
	
	public void play() {

	}
	public static void main(String[] args) {
		PolyphonicGuitarPlayer polyphonicGuitarPlayer = new PolyphonicGuitarPlayer(8, 3, "frere.txt");
		polyphonicGuitarPlayer.play();
	}
}
