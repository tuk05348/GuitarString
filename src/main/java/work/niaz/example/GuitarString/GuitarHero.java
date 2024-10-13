package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Guitar Hero
 * Creates a playable Guitar with keyboard keys mapped to GuitarStrings
 */
public class GuitarHero extends InstrumentHero {
	
	public GuitarHero() {
		name = "Guitar";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			instruments[i] = new GuitarString(Tuning.calculateFreq(i));
		}
	}
	
	@Override
	void setupWindow() {
		StdDraw.setTitle(name + " Hero");
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.enableDoubleBuffering();	//enables real time animation
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);	//ensures a StdDraw window will always open when ran
        StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
	}
	
	public static void main(String[] args) {
		GuitarHero guitarHero = new GuitarHero();
		guitarHero.startSession();
	}

}
