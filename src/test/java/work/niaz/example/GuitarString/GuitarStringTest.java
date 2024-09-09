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
		public void testSample() {
			assertEquals(0.2, guitarString.sample(), "Sample should return 0.2");
		}
	}
}
