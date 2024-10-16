package work.niaz.example.GuitarString;

import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class WindowManager {
	
	private int windowHeight;
	private int windowLength;
	private int xScale;
	private int yScale;
	private Color graphColor;
	private String note;
	
	public WindowManager(int xMax, int yMax, String note) {
		this.xScale = xMax;
		this.yScale = yMax;
		this.note = note;
	}
	
	public void createWindow() {
		StdDraw.setXscale(0, xScale);
		StdDraw.setYscale(-yScale, yScale);
		StdDraw.setPenRadius(0.001);
		StdDraw.text(xScale/2, -yScale/2, note);
	}
	
	public int getXScale() {
		return xScale;
	}

}
