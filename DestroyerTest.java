package battleship;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyerTest {
    Destroyer destroyer;
	@BeforeEach
	void setUp() throws Exception {
		this.destroyer = new Destroyer(2);
	}

	@Test
	void testGetShipType() {
		assertEquals("destroyer", destroyer.getShipType());
	}


}
