package work.niaz.example.GuitarString;


public class InstrumentHeroFactory {
	public InstrumentHero createInstrumentHero(String type) {
		if(type.equalsIgnoreCase("guitar")) {
			return new GuitarHero();
		}
		else if(type.equalsIgnoreCase("band")){
			return new BandHero();
		}
		else if(type.equalsIgnoreCase("drum")) {
			return new DrumHero();
		}
		else if(type.equalsIgnoreCase("harp")) {
			return new HarpHero();
		}
		else if(type.equalsIgnoreCase("electricguitarhero")) {
			return new ElectricGuitarHero();
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
