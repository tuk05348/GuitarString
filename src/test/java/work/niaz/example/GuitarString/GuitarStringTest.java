package work.niaz.example.GuitarString;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
