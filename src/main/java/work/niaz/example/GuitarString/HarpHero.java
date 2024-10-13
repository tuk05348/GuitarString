package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class HarpHero extends InstrumentHero {

	public HarpHero() {
		name = "Harp";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			instruments[i] = new HarpString(Tuning.calculateFreq(i));
		}
	}
	
	@Override
	void setupWindow() {
		StdDraw.setTitle(name + " Hero");
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.enableDoubleBuffering();	//enables real time animation
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);	//ensures a StdDraw window will always open when ran
        StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);		
	}
	
	public static void main(String[] args) {
		HarpHero harpHero = new HarpHero();
		harpHero.startSession();
	}

}
