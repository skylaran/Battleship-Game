package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ship battleship1;
	Ship cruiser1;
	Ship destroyer1;
	Ship submarine1;
	Ship emptysea1;
	Ocean ocean;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		battleship1 = new Battleship(4);
		cruiser1 = new Cruiser(3);
		destroyer1 = new Destroyer(2);
		submarine1 = new Submarine(1);
		emptysea1 = new EmptySea();
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		
		assertEquals(4,battleship1.getLength());
		assertEquals(3,cruiser1.getLength());
		assertEquals(2,destroyer1.getLength());
		assertEquals(1,submarine1.getLength());
		assertEquals(1,emptysea1.getLength());
	}

	@Test
	void testGetBowRow() {
		// set bow row
		battleship1.setBowRow(5);
		cruiser1.setBowRow(2);
		destroyer1.setBowRow(3);
		submarine1.setBowRow(0);
		emptysea1.setBowRow(9);
		
		assertEquals(5,battleship1.getBowRow());
		assertEquals(2,cruiser1.getBowRow());
		assertEquals(3,destroyer1.getBowRow());
		assertEquals(0,submarine1.getBowRow());
		assertEquals(9,emptysea1.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		// set bow column
		battleship1.setBowColumn(3);
		cruiser1.setBowColumn(6);
		destroyer1.setBowColumn(1);
		submarine1.setBowColumn(7);
		emptysea1.setBowColumn(4);
		
		assertEquals(3,battleship1.getBowColumn());
		assertEquals(6,cruiser1.getBowColumn());
		assertEquals(1,destroyer1.getBowColumn());
		assertEquals(7,submarine1.getBowColumn());
		assertEquals(4,emptysea1.getBowColumn());		
	}

	@Test
	void testGetHit() {
		// place destroyer, submarine
		destroyer1.placeShipAt(8, 2, true, ocean);
		submarine1.placeShipAt(0, 9, false, ocean);
		emptysea1.placeShipAt(0, 0, true, ocean);
		
		// shoot the destroyer 
		destroyer1.shootAt(8, 1);
		assertTrue(destroyer1.getHit()[1]);
		
		// shoot the submarine
		submarine1.shootAt(0, 9);
		assertTrue(submarine1.getHit()[0]);
		
		// shoot the empty sea
		assertFalse(emptysea1.getHit()[0]);
	}

	@Test
	void testIsHorizontal() {
		// set bow orientation
		battleship1.setHorizontal(true);
		cruiser1.setHorizontal(false);
		destroyer1.setHorizontal(true);
		submarine1.setHorizontal(false);
		emptysea1.setHorizontal(true);
		
		assertEquals(true, battleship1.isHorizontal());
		assertEquals(false, cruiser1.isHorizontal());
		assertEquals(true, destroyer1.isHorizontal());
		assertEquals(false, submarine1.isHorizontal());
		assertEquals(true, emptysea1.isHorizontal());
	}

	@Test
	void testSetBowRow() {
		// set bow row
		battleship1.setBowRow(5);
		cruiser1.setBowRow(4);
		destroyer1.setBowRow(3);
		submarine1.setBowRow(2);
		emptysea1.setBowRow(1);
		
		assertEquals(5,battleship1.getBowRow());
		assertEquals(4,cruiser1.getBowRow());
		assertEquals(3,destroyer1.getBowRow());
		assertEquals(2,submarine1.getBowRow());
		assertEquals(1,emptysea1.getBowRow());		
	}

	@Test
	void testSetBowColumn() {
		// set bow column
		battleship1.setBowColumn(1);
		cruiser1.setBowColumn(2);
		destroyer1.setBowColumn(3);
		submarine1.setBowColumn(4);
		emptysea1.setBowColumn(5);
		
		assertEquals(1,battleship1.getBowColumn());
		assertEquals(2,cruiser1.getBowColumn());
		assertEquals(3,destroyer1.getBowColumn());
		assertEquals(4,submarine1.getBowColumn());
		assertEquals(5,emptysea1.getBowColumn());
		}

	@Test
	void testSetHorizontal() {
		// set bow orientation
		battleship1.setHorizontal(false);
		cruiser1.setHorizontal(false);
		destroyer1.setHorizontal(false);
		submarine1.setHorizontal(false);
		emptysea1.setHorizontal(false);
		
		assertEquals(false, battleship1.isHorizontal());
		assertEquals(false, cruiser1.isHorizontal());
		assertEquals(false, destroyer1.isHorizontal());
		assertEquals(false, submarine1.isHorizontal());
		assertEquals(false, emptysea1.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		// place the ship
		battleship1.placeShipAt(5, 3, true, ocean);
		
		// check the position (5,4) (7,5) (4,0)
		// the result should be false, true, false
		assertEquals(false,submarine1.okToPlaceShipAt(5, 4, true, ocean));
		assertEquals(true,destroyer1.okToPlaceShipAt(7, 5, false, ocean));
		assertEquals(false,cruiser1.okToPlaceShipAt(4, 0, true, ocean));
	}

	@Test
	void testPlaceShipAt() {
		// place the battleship vertically
		battleship1.placeShipAt(5, 3, false, ocean);
		assertEquals(5,battleship1.getBowRow());
		assertEquals(3,battleship1.getBowColumn());	
		assertEquals(false,battleship1.isHorizontal());
		
		// check the position (5,3)(4,3)(3,3)(2,3)
		// return battleship if battleship is placed there
		assertEquals("battleship",ocean.getShipArray()[5][3].getShipType());
		assertEquals("battleship",ocean.getShipArray()[4][3].getShipType());
		assertEquals("battleship",ocean.getShipArray()[3][3].getShipType());
		assertEquals("battleship",ocean.getShipArray()[2][3].getShipType());
		
		// place cruiser horizontally
		cruiser1.placeShipAt(4, 7, true, ocean);
		assertEquals(4,cruiser1.getBowRow());
		assertEquals(7,cruiser1.getBowColumn());	
		assertEquals(true,cruiser1.isHorizontal());
		
		// check the position (4,6)(4,7)(4,8)
		// return cruiser if cruiser is placed there
		assertEquals("cruiser",ocean.getShipArray()[4][7].getShipType());
		assertEquals("cruiser",ocean.getShipArray()[4][6].getShipType());
		assertEquals("cruiser",ocean.getShipArray()[4][5].getShipType());	
		
	}

	@Test
	void testShootAt() {
		// place cruiser, submarine horizontally
		cruiser1.placeShipAt(8, 9, true, ocean);
		submarine1.placeShipAt(1, 0, true, ocean);
		
		// shoot the cruiser at position (8,7) (8,8)
		cruiser1.shootAt(8, 7);
		cruiser1.shootAt(8, 8);
		
		// check the hit array
		assertArrayEquals(new boolean[] {false,true,true},cruiser1.getHit());
		
		// shoot an empty sea
		assertFalse(emptysea1.shootAt(1,0));
		assertEquals("-",emptysea1.toString());
		
		// shoot a sunk ship
		submarine1.shootAt(1, 0);
		assertFalse(submarine1.shootAt(1, 0));
		assertEquals("s",submarine1.toString());
	}

	@Test
	void testIsSunk() {
		// place a battleship
		battleship1.placeShipAt(9, 5, false, ocean);
		
		// shoot the battleship
		battleship1.shootAt(9, 5);
		battleship1.shootAt(8,5);
		battleship1.shootAt(7, 5);
		battleship1.shootAt(6, 5);
		
		// check if the battleship is sunk
		assertTrue(battleship1.isSunk());
		
		// shoot an empty sea;
		ocean.getShipArray()[9][0].shootAt(9, 0);
		assertFalse(ocean.getShipArray()[9][0].isSunk());
	}
	
	@Test
	void testToString() {
		//  place a submarine, a destroyer and empty sea
		submarine1.placeShipAt(0, 0, true, ocean);
		destroyer1.placeShipAt(1, 8, false, ocean);
		emptysea1.placeShipAt(0, 2, true, ocean);
		
		// shoot the submarine
		submarine1.shootAt(0, 0);
		assertEquals("s",submarine1.toString());
		
		// shoot the destroyer
		destroyer1.shootAt(0, 8);
		assertEquals("x",destroyer1.toString());
		
		// shoot empty sea
		emptysea1.shootAt(0, 2);
		assertEquals("-",emptysea1.toString());
	}

}
