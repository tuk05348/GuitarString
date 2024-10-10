package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public abstract class InstrumentHero {
	protected String keyboard;
	protected String name;
	protected Instrument[] instruments;
	abstract void setUpVisualizer();
	
	public void play() {
		setUpVisualizer();
		while(true) {
			//variables for drawing lines for animation
	        double prevX = 0;
	        double prevY = 0;
	        double timeOffset = 0;
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
			
			if(time/1000 - timeOffset > 100) {	//if we hit the edge of the canvas, clear the canvas and increase the time offset
				StdDraw.clear();
				timeOffset += 100;
				StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
			}
			
			if(time%1000 == 0) {	//every 1000 iterations of the loop
				//draw line on off-screen canvas, x-axis is time, the y-axis is the sound sample
				StdDraw.line(prevX, prevY, time/1000 - timeOffset, sample);	
				StdDraw.show();	//draw line to on-screen canvas
				prevX = time/1000 - timeOffset;	//set prevX and prevY to the point on the end of the line
				prevY = sample;
			}
			
		}
	}
}
