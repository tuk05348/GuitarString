package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
	
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		
		StdDraw.setTitle("Guitar Hero");
		StdDraw.setCanvasSize(1000, 400);
		StdDraw.enableDoubleBuffering();
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(-0.5, 0.5);
        StdDraw.point(0, 0);
        
        double prevX = 0;
        double prevY = 0;
        double timeOffset = 0;

		
		Instrument[] guitarStrings = new Instrument[37];
		for(int i=0; i<keyboard.length(); i++) {
			guitarStrings[i] = new GuitarString(calculateFreq(i));
		}
		
		while(true) {
			double sample = 0;
			if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key);
                if(i != -1) {
                	guitarStrings[i].vibrate();
                }
            }
			
			for (Instrument g : guitarStrings) {
				sample += g.sample();
			}
			
			StdAudio.play(sample);
			
			for (Instrument g : guitarStrings) {
				g.tic();
			}
			
			double time = guitarStrings[0].time();
			
			if(time/1000 - timeOffset > 100) {
				StdDraw.clear();
				timeOffset += 100;
			}
			
			if(time%1000 == 0) {
				StdDraw.line(prevX, prevY, time/1000 - timeOffset, sample);
				StdDraw.show();
				prevX = time/1000 - timeOffset;
				prevY = sample;
			}

		}
	}
	
	public static double calculateFreq(int index) {
		return 440 * Math.pow(1.05956, index - 24);
	}

}
