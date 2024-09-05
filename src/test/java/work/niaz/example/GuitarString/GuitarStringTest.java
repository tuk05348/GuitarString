package work.niaz.example.GuitarString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GuitarStringTest {
	
	GuitarString guitarString;
	
	@BeforeAll
	public static void setUpClass() {
		
	}
	
	@AfterAll
	public static void tearDownClass() {
		
	}
	
	@BeforeEach
	public void setup() {
		guitarString = new GuitarString(440);
	}
	
	@AfterEach
	public void tearDown() {
	}
	
	@Test
	void testSample() {
		assertEquals(0, guitarString.sample(), 0.0001, "First element of an empty guitar string buffer should be 0.");
	}
	
	@Nested
	public class AlternateConstructor {
		@BeforeEach
		public void setup() {
			double[] freq = {0.2, 0.4, 0.3};
			guitarString = new GuitarString(freq);
		}
		
		@AfterEach
		public void tearDown() {
		}
		
		@Test
		public void testTic() {
			guitarString.tic();
			guitarString.tic();
			guitarString.tic();
			assertEquals(0.994 * 0.5 * (0.2+0.4), guitarString.sample(), 0.0001, "Sample should equal average of first two decayed");
		}
	}
}
