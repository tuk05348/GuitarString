package work.niaz.example.GuitarString;

import java.util.ArrayList;
import java.util.List;

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
	
	public DrumHero(String providedKeyboard) {
		name = "Drum Hero";
		keyboard = providedKeyboard;
		List<Instrument> intermediary = new ArrayList<Instrument>();
		for(int i=0; i<keyboard.length(); i++) {
			if(keyboard.charAt(i) != '_') {
				intermediary.add(new Drum(Tuning.calculateFreq(i)));
			}
		}
		instruments = intermediary.toArray(new Instrument[0]);
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
