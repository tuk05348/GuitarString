package work.niaz.example.GuitarString;

public class GuitarHeroFactory extends InstrumentHeroFactory {

	@Override
	protected InstrumentHero createInstrumentHero() {
		return new GuitarHero();
	}

	@Override
	protected InstrumentHero createInstrumentHero(String keyboard) {
		return new GuitarHero(keyboard);
	}

}
