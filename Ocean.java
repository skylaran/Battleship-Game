package battleship;

import java.util.Random;

/**
 * This class contains a 10x10 array of Ships
 * represents an ocean.
 * @author Keyao An , Haoyu Han
 *
 */
public class Ocean {
	
	// instance variables
	
	// This array determines the location of ships
	private Ship[][] ships = new Ship[10][10];
	
	// the total number of shots fired
	private int shotsFired = 0;
	
	// the total number of shots hit on ship
	private int hitCount = 0;
	
	// the number of ships sunk
	// 10 ships in all
	private int shipsSunk = 0;
	
	// constructor
	public Ocean() {
		for(int i = 0; i< 10 ;i++) {
			for(int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				
				// set the initial EmptySea BowRow and BowColumn
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
			}
		}
	}
	
	// methods
	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 * Place larger ships before smaller ones
	 * or you may end up with no legal place to put a large ship. 
	 */
	public void placeAllShipsRandomly() {
	
		/*
		 *  method to place the ship randomly
		 */
		
		// first place the battleship
		Battleship battleship = new Battleship(4);
		placeShipHelper(battleship,this);
		
		// second place the cruiser
		Cruiser cruiser1 = new Cruiser(3);
		Cruiser cruiser2 = new Cruiser(3);
		placeShipHelper(cruiser1,this);
		placeShipHelper(cruiser2,this);
		
		// third place the destroyers
		Destroyer destroyer1 = new Destroyer(2);
		Destroyer destroyer2 = new Destroyer(2);
		Destroyer destroyer3 = new Destroyer(2);
		placeShipHelper(destroyer1,this);
		placeShipHelper(destroyer2,this);
		placeShipHelper(destroyer3,this);

		// finally place the submarine
		Submarine submarine1 = new Submarine(1);
		Submarine submarine2 = new Submarine(1);
		Submarine submarine3 = new Submarine(1);
		Submarine submarine4 = new Submarine(1);
		placeShipHelper(submarine1,this);
		placeShipHelper(submarine2,this);
		placeShipHelper(submarine3,this);
		placeShipHelper(submarine4,this);
		
	}
	
	/**
	 * Place the ship randomly
	 * @param ship
	 * @param ocean
	 */
	public void placeShipHelper(Ship ship, Ocean ocean) {
		// local variables
		// generates random row, column
		Random rand = new Random();
		int row = rand.nextInt(9);
		int column = rand.nextInt(9);
		boolean horizontal = rand.nextBoolean();
		
		// check the initial location is capable to place the ship
		if (ship.okToPlaceShipAt(row, column, horizontal, ocean)) {
			ship.placeShipAt(row, column, horizontal, ocean);
		}
		else {
			while(!ship.okToPlaceShipAt(row, column, horizontal, ocean)) {
				// give a new location and orientation
				row = rand.nextInt(9);
				column = rand.nextInt(9);
				horizontal = rand.nextBoolean();
				
				// check if the new position is capable to place the ship
				if(ship.okToPlaceShipAt(row, column, horizontal, ocean)) {
					ship.placeShipAt(row, column, horizontal, ocean);
					break;
				}
			}
		}
	}
	
	
	/**
	 * if the location contains a ship, return true
	 * false otherwise
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isOccupied(int row, int column) {
		
		if (ships[row][column].getShipType() != "empty") {
			return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * Returns true if the given location contains a”real”ship,still afloat,(not Empty sea)
	 * false if it does not.
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean shootAt(int row, int column) {
		if(ships[row][column].isSunk()) {
			shotsFired += 1;
			return false;
		}
		else {
			ships[row][column].shootAt(row, column);
		
			if (ships[row][column].getShipType() != "empty" &&
					ships[row][column].isSunk() != true) {
				hitCount += 1;
				shotsFired += 1;
				return true;
			}
			else if (ships[row][column].isSunk()) {
				shotsFired += 1;
				hitCount += 1;
				shipsSunk +=1 ;
				return true;
			}
			else {
				shotsFired += 1;
				return false;
			}
			
		}
		

	}
	
	/**
	 * Returns the number of shots fired (in the game)
	 * @return shotsFired
	 */
	public int getShotsFired() {
		
		return shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in the game). 
	 * All hits are counted,not just the first time a given square is hit.
	 * @return hitCount
	 */
	public int getHitCount() {
		
		return hitCount;
	}
	
	/**
	 * Returns the number of ships sunk(in the game)
	 * @return shipsSunk
	 */
	public int getShipsSunk() {
		
		return shipsSunk;
	}
	
	/**
	 * Returns true if all ships have been sunk,otherwise false
	 * @return
	 */
	public boolean isGameOver() {
		if (this.shipsSunk == 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * The methods in the Ship class that take an Ocean parameter need to
	 * look at the contents of this array
	 * The placeShipsAt() method needs to modify it
	 * @return array of ships
	 */
	public Ship[][] getShipArray(){
		// return the ships array		
		return ships;
	}
	
	/**
	 * Prints the Ocean
	 * The row numbers and the columns number should be displayed along the edge
	 * This method is called within the BattleshipGame class.
	 */
	public void print() {
		// todo here
		
		// print the row numbers
		for (int i = 0; i <= 10 ; i++) {
			if (i == 0) {
				System.out.print("  ");
			}else {
				System.out.print( " "+ (i-1)  + " ");
			}	
		}
		
		// print the initial ocean with the column number
		for (int row = 0; row < 10; row ++) {
			System.out.println();
			System.out.print(row + " ");
			for(int column = 0; column <= 9; column++) {
				if (ships[row][column].isHorizontal()) {
					if(ships[row][column].getHit()[ships[row][column].getBowColumn() - column]) {
						System.out.print(" "+ this.getShipArray()[row][column] + " ");
					}
					else{
						System.out.print(" "+ "." + " ");
					}
				}
				else {
					if(ships[row][column].getHit()[ships[row][column].getBowRow() - row]) {
						System.out.print(" "+ this.getShipArray()[row][column] + " ");
					}
					else {
						System.out.print(" "+ "." + " ");
					}
				}

			}
		}
		
		// print the fired location
		System.out.println();
		System.out.println("Shoots fired: " + this.getShotsFired());
		System.out.println("Total hits: " + this.getHitCount());
		System.out.println("Total ships sunk: " + this.getShipsSunk());
		if (!this.isGameOver()) {
			System.out.print("Now, Enter row,column: ");			
		}
	}
}
