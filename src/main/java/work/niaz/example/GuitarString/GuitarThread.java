package work.niaz.example.GuitarString;

public class GuitarThread implements Runnable {
	private GuitarPlayer guitarPlayer;
	
	public GuitarThread(String filenameString) {

		guitarPlayer = new GuitarPlayer(filenameString);
	}
	
	@Override
	public void run() {
		guitarPlayer.play();
	}
}
