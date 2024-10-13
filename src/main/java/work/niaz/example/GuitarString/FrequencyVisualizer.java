package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class FrequencyVisualizer {
	private double prevX;
	private double prevY;
	
	public FrequencyVisualizer() {
		prevX = 0;
		prevY = 0;
	}
	
	public void plot(double time, double sample) {
		StdDraw.line(prevX, prevY, time, sample);
		StdDraw.show();
		prevX = time;
		prevY = sample;
	}
}
