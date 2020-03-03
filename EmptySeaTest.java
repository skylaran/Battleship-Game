package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptySeaTest {
    EmptySea emptySea;
	@BeforeEach
	void setUp() throws Exception {
		this.emptySea = new EmptySea();
	}

	@Test
	void testGetShipType() {
		assertEquals("empty",emptySea.getShipType());
	}

	@Test
	void testShootAt() {
		assertEquals(false,emptySea.shootAt(4,4));
		//test the hit array
		assertEquals(true,emptySea.getHit()[0]);
	}

	@Test
	void testIsSunk() {
		assertEquals(false,emptySea.isSunk());
	}

	@Test
	void testToString() {
		//the empty sea that not been shot before
		assertEquals(".",emptySea.toString());
		//shoot one empty sea, then test
		assertEquals(false,emptySea.shootAt(4,4));
		assertEquals("-",emptySea.toString());
	}
}
