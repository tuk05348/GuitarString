package work.niaz.example.GuitarString;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class WindowManager {
	
	//required parameters
	private double xScale;
	private double yScale;
	//optional parameters
	private String name;
	private String note;
	private Boolean noteCheck = false;
	private int windowHeight;
	private int windowLength;
	private Color graphColor;
	private double penRadius;
	
	public static class Builder{
		private double xScale;
		private double yScale;
		//optional parameters
		private String name;
		private String note;
		private Boolean noteCheck;
		private int windowHeight;
		private int windowLength;
		private Color graphColor;
		private double penRadius;
		
		public Builder(double xMax, double yMax) {
			StdDraw.enableDoubleBuffering();
			this.xScale = xMax;
			this.yScale = yMax;
			StdDraw.setXscale(0, xScale);
			StdDraw.setYscale(-yScale, yScale);
			setDefaultValues();
		}
		
		public Builder setName(String name) {
			this.name = name;
			StdDraw.setTitle(name);
			return this;
		}
		
		public Builder setNote(String note) {
			this.note = note;
			StdDraw.text(xScale/2, -yScale/2, note);
			noteCheck = true;
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
		
		public Builder setPenRadius(double penRadius) {
			this.penRadius = penRadius;
			StdDraw.setPenRadius(penRadius);
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
		noteCheck = builder.noteCheck;
		windowHeight = builder.windowHeight;
		windowLength = builder.windowLength;
		graphColor = builder.graphColor;
		penRadius = builder.penRadius;
	}
	
	public WindowState returnNoteState() {
		return new WindowState(this.noteCheck, this.note, this.xScale, this.yScale);
	}
	
	public void close() {
		StdDraw.close();
	}

}
