package work.niaz.example.GuitarString;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;

public class HarpHero implements InstrumentHero {

	private String name;
	private String keyboard;
	private Instrument[] instruments;
	
	public HarpHero() {
		name = "Harp Hero";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			instruments[i] = new HarpString(Tuning.calculateFreq(i));
		}
	}
	
	public HarpHero(String providedKeyboard) {
		name = "Harp Hero";
		keyboard = providedKeyboard;
		List<Instrument> intermediary = new ArrayList<Instrument>();
		for(int i=0; i<keyboard.length(); i++) {
			if(keyboard.charAt(i) != '_') {
				intermediary.add(new HarpString(Tuning.calculateFreq(i)));
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
				.setCanvasDimensions(1000, 400)
				.setGraphColor(StdDraw.ORANGE)
				.setPenRadius(0.001)
				.build();
		MusicSimulator musicSimulator = new MusicSimulator(keyboard, instruments, windowManager.returnNoteState());
		musicSimulator.play();
	}
	
	public static void main(String[] args) {
		HarpHero harpHero = new HarpHero();
		harpHero.startSession();
	}

}
