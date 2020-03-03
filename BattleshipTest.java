package battleship;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BattleshipTest {
	Battleship battleship;

	@BeforeEach
	void setUp() throws Exception {
		this.battleship = new Battleship(4);
	}

	@Test
	void testGetShipType() {
		String shipType;
		shipType = battleship.getShipType();
		assertEquals("battleship",shipType);
	}

}
