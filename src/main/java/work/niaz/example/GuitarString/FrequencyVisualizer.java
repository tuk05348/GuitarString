package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class FrequencyVisualizer {
	private double prevX;
	private double prevY;
	private double timeOffset;
	private double xScale;
	private double yScale;

	public FrequencyVisualizer(double xScale, double yScale) {
		this.prevX = 0;
		this.prevY = 0;
		this.timeOffset = 0;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	public void plot(double time, double sample) {
		StdDraw.line(prevX, prevY, time - this.timeOffset, sample);
		StdDraw.show();
		prevX = time - this.timeOffset;
		prevY = sample;
	}
	
	public void clearCanvas(double time, String note) {
		if(time - this.timeOffset > this.xScale) {	//if we hit the edge of the canvas, clear the canvas and increase the time offset
			this.timeOffset += this.xScale;
			StdDraw.clear();
			StdDraw.text(xScale/2, -yScale/2, note);
		}
	}
}
