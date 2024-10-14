package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public abstract class InstrumentHero {
	protected String keyboard;
	protected String name;
	protected Instrument[] instruments;
	protected FrequencyVisualizer visualizer;
	
	abstract void setupWindow();
	
	public void startSession() {
		setupWindow();
		visualizer = new FrequencyVisualizer(keyboard, instruments);
		//variables for drawing lines for animation
        double timeOffset = 0;

		while(true) {
	        double sample = 0;
			visualizer.vibrateOnKeyPress();
			
			for (Instrument g : instruments) {	//calculate superposition of samples of all instruments
				sample += g.sample();
			}
			
			StdAudio.play(sample);	//use StdAudio to play the sample
			
			for (Instrument g : instruments) {	//move time forward one tic and do the Karplus-Strong averaging
				g.tic();
			}
			
			double time = instruments[0].time();	//get current simulation time
			
			if(time/1000 - timeOffset > 100) {	//if we hit the edge of the canvas, clear the canvas and increase the time offset
				StdDraw.clear();
				timeOffset += 100;
				StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
			}
			
			if(time%1000 == 0) {	//every 1000 iterations of the loop
				//draw line on off-screen canvas, x-axis is time, the y-axis is the sound sample
				visualizer.plot(time/1000 - timeOffset, sample);
			}
			
		}
	}
}
