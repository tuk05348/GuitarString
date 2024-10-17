package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class DrumHero implements InstrumentHero {

	private String name;
	private String keyboard;
	private Instrument[] instruments;
	
	public DrumHero() {
		name = "Drum Hero";
		keyboard = "qw45yuio-=xc";
		instruments = new Instrument[keyboard.length()];
		for(int i=0; i < 2*keyboard.length(); i += 2) {
			instruments[i/2] = new Drum(Tuning.calculateFreq(i));
		}
	}
	

	@Override
	public void startSession() {
		WindowManager windowManager = new WindowManager
				.Builder(100, 0.5)
				.setName(name)
				.setNote("Keyboard Mapping:" + keyboard)
				.setCanvasDimensions(400, 400)
				.setGraphColor(StdDraw.FUSCIA)
				.setPenRadius(0.001)
				.build();
		MusicSimulator musicSimulator = new MusicSimulator(keyboard, instruments, windowManager.returnNoteState());
		musicSimulator.play();
	}
	
	public static void main(String[] args) {
		DrumHero drumHero = new DrumHero();
		drumHero.startSession();
	}

}
