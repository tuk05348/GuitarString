package work.niaz.example.GuitarString;

import edu.princeton.cs.algs4.*;

public class GuitarHeroLite {
	public static void main(String[] args) {

        // create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(1.05956, 3.0);  
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        
		StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
        
        while (true) {
            // check if the user has typed a key; if so, process it   
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if      (key == 'a') { stringA.vibrate(); }
                else if (key == 'c') { stringC.vibrate(); }
            }

            // compute the superposition of samples
            double sample = stringA.sample() + stringC.sample();

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step   
            stringA.tic();
            stringC.tic();
        }
     }
}
