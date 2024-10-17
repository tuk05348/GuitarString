package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdDraw;

public class ElectricGuitarHero implements InstrumentHero {
	
	private String name;
	private String keyboard;
	private Instrument[] instruments;
	
	public ElectricGuitarHero() {
		name = "Elecric Guitar Hero";
		keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		instruments = new Instrument[keyboard.length()]; //Create an array of instruments, interface let's us plug and play any instrument
		for(int i=0; i<keyboard.length(); i++) {
			instruments[i] = new ElectricGuitarString(Tuning.calculateFreq(i));
		}
	}
	
	@Override
	public void startSession() {
		WindowManager windowManager = new WindowManager
				.Builder(100, 0.5)
				.setName(name)
				.setNote("Keyboard Mapping:" + keyboard)
				.setCanvasDimensions(1000, 400)
				.setGraphColor(StdDraw.RED)
				.setPenRadius(0.001)
				.build();
		MusicSimulator musicSimulator = new MusicSimulator(keyboard, instruments, windowManager.returnNoteState());
		musicSimulator.play();
	}

	public static void main(String[] args) {
		ElectricGuitarHero electricGuitarHero = new ElectricGuitarHero();
		electricGuitarHero.startSession();
	}

}
