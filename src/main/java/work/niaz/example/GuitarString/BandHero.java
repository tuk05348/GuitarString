package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class BandHero implements InstrumentHero {
	
	private String name;
	private String keyboard;
	private Instrument[] instruments;
	
	public BandHero() {
		name = "Band Hero";
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
	public void startSession() {
		WindowManager windowManager = new WindowManager
				.Builder(100, 0.5)
				.setName(name)
				.setNote("Keyboard Mapping:" + keyboard)
				.setCanvasDimensions(1000, 400)
				.setGraphColor(StdDraw.GREEN)
				.setPenRadius(0.001)
				.build();
		MusicSimulator musicSimulator = new MusicSimulator(keyboard, instruments, windowManager.returnNoteState());
		musicSimulator.play();
	}
	
	public static void main(String[] args) {
		BandHero bandHero = new BandHero();
		bandHero.startSession();
	}

}
