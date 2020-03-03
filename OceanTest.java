package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {
	
	Ocean ocean;
	Ship battleship2;
	Ship cruiser2;
	Ship destroyer2;
	Ship submarine2;
	Ship emptysea2;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
		battleship2 = new Battleship(4);
		cruiser2 = new Cruiser(3);
		destroyer2 = new Destroyer(2);
		submarine2 = new Submarine(1);
		emptysea2 = new EmptySea();		
	}


	@Test
	void testIsOccupied() {
		// place a battleship in the ocean
		battleship2.placeShipAt(7, 4, true, ocean);
		
		// check the position (7, 6) (7,4) (7,3) (6,2)
		// the position contains the battleship should return true
		// the position (7, 6) (6, 2) should return false
		assertFalse(ocean.isOccupied(7, 6));
		assertTrue(ocean.isOccupied(7, 4));
		assertTrue(ocean.isOccupied(7, 3));		
		assertFalse(ocean.isOccupied(6, 2));		
	}

	@Test
	void testShootAt() {
		// place a battleship, a cruiser, a destroyer and a submarine 
		battleship2.placeShipAt(1, 3, true, ocean);
		cruiser2.placeShipAt(4, 6, false, ocean);
		destroyer2.placeShipAt(6, 3, true, ocean);
		submarine2.placeShipAt(8, 6, false, ocean);
		
		// shoot at submarine
		ocean.shootAt(8, 6);
		
		// check the shots fired, hit counts, ships sunk count
		// since the place where we shot contains a submarine
		// hit count and sunk count increase 1 
		assertEquals(1, ocean.getShotsFired());
		assertEquals(1,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		
		// shoot the sunk ship should return false
		// and the hit count and ships sunk count don't change
		assertFalse(ocean.shootAt(8, 6));
		assertEquals(2, ocean.getShotsFired());
		assertEquals(1,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());		
		
		// shoot empty sea
		assertFalse(ocean.shootAt(4, 1));
		assertEquals(3, ocean.getShotsFired());
		assertEquals(1,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		
		// shoot a body cruiser
		assertTrue(ocean.shootAt(3, 6));
		assertEquals(4, ocean.getShotsFired());
		assertEquals(2,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		
		// check the cruiser's hit array
		assertArrayEquals(new boolean[] {false, true, false},cruiser2.getHit());
		
	}

	@Test
	void testGetShotsFired() {
		//test the original shotsFired
		assertEquals(0, ocean.getShotsFired());
		//After 4 shots
		ocean.shootAt(4, 5);
		ocean.shootAt(3, 5);
		ocean.shootAt(2, 5);
		ocean.shootAt(4, 7);
		assertEquals(4, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		//test the original hits
		assertEquals(0, ocean.getHitCount());
		//place a battleship in the sea
		battleship2.placeShipAt(6, 8, true, this.ocean);
		//shot the ship third times
		ocean.shootAt(6, 8);
		ocean.shootAt(6, 7);
		ocean.shootAt(6, 7);
		ocean.shootAt(8, 8);
		assertEquals(3, ocean.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		//test the original ship
		assertEquals(0, ocean.getShipsSunk());
		//place a battleship in the sea
		battleship2.placeShipAt(8, 8, true, this.ocean);
		//shot down this battleship
		ocean.shootAt(8, 8);
		ocean.shootAt(8, 7);
		ocean.shootAt(8, 6);
		ocean.shootAt(8, 5);
		assertEquals(1, ocean.getShipsSunk());
	}

	@Test
	void testIsGameOver() {
		//test only shot down one battleship, check whether the game is over or not
		battleship2.placeShipAt(8, 8, true, this.ocean);
		ocean.shootAt(8, 8);
		ocean.shootAt(8, 7);
		ocean.shootAt(8, 6);
		ocean.shootAt(8, 5);
		assertEquals(false, ocean.isGameOver());
		//use place ship randomly to place 10 ships randomly
		ocean.placeAllShipsRandomly();
		//use loop to shot all the points in the sea, then test whether the game is over or not
		for(int row = 0; row < 10; row++) {
			for(int column = 0; column < 10; column++) {
					ocean.shootAt(row, column);
					if(ocean.getShipsSunk() == 10) {
						assertEquals(true, ocean.isGameOver());
					}else {
						assertEquals(false, ocean.isGameOver());
					}
				}
			}		
	}

	@Test
	void testGetShipArray() {
		//test in the empty sea
		assertEquals("empty",ocean.getShipArray()[1][1].getShipType());
		//put a battles ship in the sea, then test
		battleship2.placeShipAt(8, 8, true, this.ocean);
		assertEquals("battleship",ocean.getShipArray()[8][8].getShipType());
		assertEquals("battleship",ocean.getShipArray()[8][7].getShipType());
		assertEquals("battleship",ocean.getShipArray()[8][6].getShipType());
		assertEquals("battleship",ocean.getShipArray()[8][5].getShipType());		
	}

}
