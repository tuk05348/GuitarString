/**
 * 
 */
package work.niaz.example.GuitarString;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 
 */
public class PolyphonicGuitarPlayer {
	
	private double delay;
	private int totalCount;
	private String filename;
	private ScheduledExecutorService schedulerExecutorService;
	
	public PolyphonicGuitarPlayer(double delay, int totalCount, String filename) {
		this.delay = delay;
		this.totalCount = totalCount;
		this.filename = filename;
		schedulerExecutorService = Executors.newScheduledThreadPool(totalCount);
	}
	
	public void play() {
		
	}
}
