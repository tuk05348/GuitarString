package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Guitar Hero
 * Creates a playable Guitar with keyboard keys mapped to GuitarStrings
 */
public class GuitarHero extends InstrumentHero {
	
	public GuitarHero() {
		
	}
	
	/**
	 * Main method of GuitarHero, runs a loop to play notes associated with the keyboard and animate their frequencies in real time
	 * 
	 * @param args Command line arguments (unnecessary for this program)
	 */
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; //string to hold mapping of keyboard characters to musical notes
		
		//StdDraw set up
		StdDraw.setTitle("Guitar Hero");
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.enableDoubleBuffering();	//enables real time animation
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);	//ensures a StdDraw window will always open when ran
        StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
        
        //variables for drawing lines for animation
        double prevX = 0;
        double prevY = 0;
        double timeOffset = 0;

		
		Instrument[] guitarStrings = new Instrument[37]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			guitarStrings[i] = new GuitarString(Tuning.calculateFreq(i));
		}
		
		//while loop for playing sound
		while(true) {
			double sample = 0;
			if (StdDraw.hasNextKeyTyped()) {	//if a key is pressed, play the instrument that corresponds to the key
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key);
                if(i != -1) {
                	guitarStrings[i].vibrate();
                }
            }
			
			for (Instrument g : guitarStrings) {	//calculate superposition of samples of all instruments
				sample += g.sample();
			}
			
			StdAudio.play(sample);	//use StdAudio to play the sample
			
			for (Instrument g : guitarStrings) {	//move time forward one tic and do the Karplus-Strong averaging
				g.tic();
			}
			
			double time = guitarStrings[0].time();	//get current simulation time
			
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

	@Override
	void setUpVisualizer() {
		// TODO Auto-generated method stub
		
	}

}
