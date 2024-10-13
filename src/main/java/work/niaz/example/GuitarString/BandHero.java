package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class BandHero extends InstrumentHero {
	
	public BandHero() {
		name = "Band";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()];
		for(int i=0; i<keyboard.length(); i++) {
			if(i < 13 && i%3 == 0) {
				instruments[i] = new Drum(Tuning.calculateFreq(i));
			}
			else if(i < 13 && i%3 != 0) {
				instruments[i] = new GuitarString(Tuning.calculateFreq(i));
			}
			else if(i >= 13 && i < 25) {
				instruments[i] = new GuitarString(Tuning.calculateFreq(i));
			}
			else {
				instruments[i] = new HarpString(Tuning.calculateFreq(i));
			}
		}
	}

	@Override
	void setupWindow() {
		StdDraw.setTitle(name + " Hero");
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.enableDoubleBuffering();	//enables real time animation
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);	//ensures a StdDraw window will always open when ran
        StdDraw.text(50, -0.25, "Keyboard Mapping: " + keyboard);
	}
	
	public static void main(String[] args) {
		BandHero bandHero = new BandHero();
		bandHero.startSession();
	}

}
