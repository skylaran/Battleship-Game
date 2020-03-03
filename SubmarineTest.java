package battleship;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubmarineTest {
	Submarine submarine;
	@BeforeEach
	void setUp() throws Exception {
		this.submarine = new Submarine(1);
	}

	@Test
	void testGetShipType() {
		assertEquals("submarine", submarine.getShipType());
	}


}
