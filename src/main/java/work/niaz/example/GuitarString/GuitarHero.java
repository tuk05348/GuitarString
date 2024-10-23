package work.niaz.example.GuitarString;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.JoinRowSet;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Guitar Hero
 * Creates a playable Guitar with keyboard keys mapped to GuitarStrings
 */
public class GuitarHero implements InstrumentHero {
	
	private String name;
	private String keyboard;
	private Instrument[] instruments;
	
	public GuitarHero() {
		name = "Guitar Hero";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			instruments[i] = new GuitarString(Tuning.calculateFreq(i));
		}
	}
	
	public GuitarHero(String providedKeyboard) {
		name = "Guitar Hero";
		keyboard = providedKeyboard;
		List<Instrument> intermediary = new ArrayList<Instrument>();
		for(int i=0; i<keyboard.length(); i++) {
			if(keyboard.charAt(i) != '_') {
				intermediary.add(new GuitarString(Tuning.calculateFreq(i)));
			}
		}
		instruments = new Instrument[intermediary.size()];
		intermediary.toArray(instruments);
	}
	
	@Override
	public void startSession() {
		WindowManager windowManager = new WindowManager
				.Builder(100, 0.5)
				.setName(name)
				.setNote("Keyboard Mapping:" + keyboard)
				.setCanvasDimensions(1000, 400)
				.setGraphColor(StdDraw.BLUE)
				.setPenRadius(0.001)
				.build();
		MusicSimulator musicSimulator = new MusicSimulator(keyboard, instruments, windowManager.returnNoteState());
		musicSimulator.play();
	}
	
	public static void main(String[] args) {
		GuitarHero guitarHero = new GuitarHero("q___________________________________ ");
		guitarHero.startSession();
	}

}
