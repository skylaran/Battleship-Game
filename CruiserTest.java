package battleship;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CruiserTest {
    Cruiser cruiser;
	@BeforeEach
	void setUp() throws Exception {
		this.cruiser = new Cruiser(3);
	}

	@Test
	void testGetShipType() {
		assertEquals("cruiser", cruiser.getShipType());
	}


}
