package work.niaz.example.GuitarString;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class WindowManager {
	
	//required parameters
	private int xScale;
	private int yScale;
	//optional parameters
	private String name;
	private String note;
	private Boolean noteCheck = false;
	private int windowHeight;
	private int windowLength;
	private Color graphColor;
	
	public static class Builder{
		private int xScale;
		private int yScale;
		//optional parameters
		private String name;
		private String note;
		private Boolean noteCheck;
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
		
		public WindowManager build() {
			return new WindowManager(this);
		}
		
		public void setDefaultValues() {
			StdDraw.point(0, 0);
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
	}
	
	public int getXScale() {
		return xScale;
	}
	
	public String getNote() {
		return note;
	}
	
	public WindowState returnNoteState() {
		return new WindowState(this.noteCheck, note);
	}
	
	public void close() {
		StdDraw.close();
	}

}
