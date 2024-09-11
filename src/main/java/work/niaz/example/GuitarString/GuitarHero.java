package work.niaz.example.GuitarString;

public class GuitarHero {
	
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";		
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}

}
