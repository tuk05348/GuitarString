package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class FrequencyVisualizer {
	private double prevX;
	private double prevY;
	private double offset;
	private String keyboard;
	private Instrument[] instruments;
	
	public FrequencyVisualizer(String keyboard, Instrument[] instruments) {
		this.prevX = 0;
		this.prevY = 0;
		this.offset = 0;
		this.keyboard = keyboard;
		this.instruments = instruments;
	}
	
	public void vibrateOnKeyPress() {
		if (StdDraw.hasNextKeyTyped()) {	//if a key is pressed, play the instrument that corresponds to the key
            char key = StdDraw.nextKeyTyped();
            int i = keyboard.indexOf(key);
            if(i != -1) {
            	instruments[i].vibrate();
            }
        }
	}
	
	public void plot(double time, double sample) {
		StdDraw.line(prevX, prevY, time, sample);
		StdDraw.show();
		prevX = time;
		prevY = sample;
	}
}
