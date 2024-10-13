package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class DrumHero extends InstrumentHero {

	public DrumHero() {
		name = "Drum";
		keyboard = "qw45yuio-=xc";
		instruments = new Instrument[keyboard.length()];
		for(int i=0; i < 2*keyboard.length(); i += 2) {
			instruments[i/2] = new Drum(Tuning.calculateFreq(i));
		}
	}
	
	@Override
	void setupWindow() {
		StdDraw.setTitle(name + " Hero");
		StdDraw.setCanvasSize(400, 400);
		StdDraw.enableDoubleBuffering();	//enables real time animation
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);	//ensures a StdDraw window will always open when ran
        StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
	}
	
	public static void main(String[] args) {
		DrumHero drumHero = new DrumHero();
		drumHero.startSession();
	}

}
