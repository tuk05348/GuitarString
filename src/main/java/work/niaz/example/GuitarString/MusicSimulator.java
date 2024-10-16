package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class MusicSimulator {

	private FrequencyVisualizer frequencyVisualizer;
	private String keyboard;
	private Instrument[] instruments;
 	
	public MusicSimulator(String keyboard, int xScale, Instrument[] instruments) {
		this.keyboard = keyboard;
		this.instruments = instruments;
		frequencyVisualizer = new FrequencyVisualizer(this.keyboard, xScale);
	}
	
	public void startSession() {
		double timeOffset = 0;
		while(true) {
	        double sample = 0;
	        
	        if (StdDraw.hasNextKeyTyped()) {	//if a key is pressed, play the instrument that corresponds to the key
	            char key = StdDraw.nextKeyTyped();
	            int i = keyboard.indexOf(key);
	            if(i != -1) {
	            	instruments[i].vibrate();
	            }
	        }
			
			for (Instrument g : instruments) {	//calculate superposition of samples of all instruments
				sample += g.sample();
			}
			
			StdAudio.play(sample);	//use StdAudio to play the sample
			
			for (Instrument g : instruments) {	//move time forward one tic and do the Karplus-Strong averaging
				g.tic();
			}
			
			double time = instruments[0].time();	//get current simulation time
			
			frequencyVisualizer.clearCanvas(time);
			
			if(time%1000 == 0) {	//every 1000 iterations of the loop
				//draw line on off-screen canvas, x-axis is time, the y-axis is the sound sample
				frequencyVisualizer.plot(time/1000 - timeOffset, sample);
			}
		}
	}
}
