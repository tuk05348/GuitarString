package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class FrequencyVisualizer {
	private double prevX;
	private double prevY;
	private String keyboard;
	private int timeOffset;
	private int canvasLength;
	
	public FrequencyVisualizer(String keyboard, int canvasLength) {
		this.keyboard = keyboard;
		this.prevX = 0;
		this.prevY = 0;
		this.timeOffset = canvasLength;
		this.canvasLength = canvasLength;
	}
	
	
	public void plot(double time, double sample) {
		StdDraw.line(prevX, prevY, time, sample);
		StdDraw.show();
		prevX = time;
		prevY = sample;
	}
	
	public void clearCanvas(double time) {
		if(time - this.timeOffset > this.canvasLength) {	//if we hit the edge of the canvas, clear the canvas and increase the time offset
			this.timeOffset += this.canvasLength;
			StdDraw.clear();
			StdDraw.text(50, -0.25, "Keyboard Mapping: " + this.keyboard);
		}
	}
}
