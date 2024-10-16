package work.niaz.example.GuitarString;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class WindowManager {
	
	//required parameters
	private int xScale;
	private int yScale;
	private String note;
	//optional parameters
	private int windowHeight;
	private int windowLength;
	private Color graphColor;
	
	public static class Builder{
		private int xScale;
		private int yScale;
		private String note;
		//optional parameters
		private int windowHeight;
		private int windowLength;
		private Color graphColor;
		
		
		public Builder(int xMax, int yMax) {
			this.xScale = xMax;
			this.yScale = yMax;
			StdDraw.enableDoubleBuffering();
			StdDraw.setXscale(0, xScale);
			StdDraw.setYscale(-yScale, yScale);
			setDefaultValues();
		}
		
		public Builder setNote(String note) {
			this.note = note;
			StdDraw.text(xScale/2, -yScale/2, note);
			return this;
		}
		
		public Builder setCanvasDimensions(int windowHeight, int windowLength) {
			this.windowHeight = windowHeight;
			this.windowLength = windowLength;
			StdDraw.setCanvasSize(this.windowHeight, this.windowLength);
			return this;
		}
		
		public Builder setGraphColor(Color color) {
			this.graphColor = color;
			StdDraw.setPenColor(color);
			return this;
		}
		
		public WindowManager build() {
			return new WindowManager(this);
		}
		
		public void setDefaultValues() {
			StdDraw.setCanvasSize();
			StdDraw.setPenRadius();
			StdDraw.setPenColor();
		}
	}
	
	private WindowManager(Builder builder) {
		xScale = builder.xScale;
		yScale = builder.yScale;
		note = builder.note;
		windowHeight = builder.windowHeight;
		windowLength = builder.windowLength;
		graphColor = builder.graphColor;
	}
	
	public int getXScale() {
		return xScale;
	}

}
