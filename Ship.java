package battleship;

/**
 * This abstract class describes the characteristics common to all ships
 * @author Keyao An, Haoyu Han
 *
 */
public abstract class Ship {
	// instance variables

	// row that contains the front of the ship
	private int bowRow;

	// column that contains the front of the ship
	private int bowColumn;

	private int length;

	private boolean horizontal;

	//An Array of 4 booleans that indicate whether part of ships has been hit or not
	private boolean[] hit;

	// constructor
	public Ship(int length) {
		this.length = length;

		// initialize the hit array
		this.hit = new boolean[length];
		for (int i = 0; i < this.length; i++) {
			hit[i] = false;
		}
	}

	// methods

	// Getters
	/**
	 * Returns the ship length
	 * @return ship length
	 */
	public int getLength() {

		return this.length;
	}

	/**
	 * Returns the row corresponding to the position of the bow
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow() {

		return this.bowRow;
	}

	/**
	 * Returns the bow column location
	 * @return the bow column
	 */
	public int getBowColumn() {

		return this.bowColumn;
	}

	/**
	 * Returns the hit array
	 * @return the hit array
	 */
	public boolean[] getHit() {

		return this.hit;
	}

	/**
	 * Returns whether the ship is horizontal or not
	 * @return this.horizontal
	 */
	public boolean isHorizontal() {

		return this.horizontal;
	}

	// Setters
	/**
	 * Sets the value of bowRow
	 * @param row 
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Sets the value of bow Column
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Returns the type of ship as a String. Every specific type of Ship 
	 * (e.g.BattleShip, Cruiser, etc.) has to override and implement 
	 * this method and return the corresponding ship type.
	 * @return specific type of Ship  
	 */
	public abstract String getShipType();

	// other methods
	/**
	 * Based on the given row, column, and orientation, 
	 * returns true if it's okay to put a ship of this length with its bow in this location
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		boolean OkayToPlaceShip = true;

		/*
		 *  the right method 
		 */
		if (horizontal == true) {
			if(column - this.length + 1 < 0) {
				OkayToPlaceShip = false;
			} 
			else {
				// if the front at edge, it will raise exception
				// and since the exception doesn't do anything to your checking process
				// we could seem it as true
				outerloop1:
					for (int i = row - 1; i <= row + 1; i ++) {
						for(int j = column + 1; j >= column - this.length; j--) {
							try {
								if(ocean.isOccupied(i, j)== true) {
									OkayToPlaceShip = false;
									break outerloop1;
								}
								else {
									OkayToPlaceShip = true;
								}
							}catch(Exception e){
								OkayToPlaceShip = true;
							}
						}
					}
			}
		}
		else if (horizontal == false) {
			if (row - this.length + 1 < 0) {
				OkayToPlaceShip = false;
			}
			else {
				outerloop2:
					for(int i = row + 1; i >= row - this.length; i--) {
						for(int j = column - 1; j <= column + 1; j++) {
							try {
								if(ocean.isOccupied(i, j)== true) {
									OkayToPlaceShip = false;
									break outerloop2;
								}
								else {
									OkayToPlaceShip = true;
								}
							}catch(Exception e) {
								OkayToPlaceShip = true;
							}

						}
					}
			}

		}

		return OkayToPlaceShip;
	}

	/**
	 * Place the ships in the ocean.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// set instance variables
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);

		// put a reference to the ship in each of more locations in the ships array 
		// in the Ocean object.
		if(this.okToPlaceShipAt(row, column, horizontal, ocean) == true) {
			if (horizontal == true) {
				int i = row;

				for (int j = column; j > column - this.length; j--) {

					// put the boat inside the ship array
					ocean.getShipArray()[i][j] = this;
				}
			}
			else if(horizontal == false) {
				int j = column;

				for (int i = row; i > row - this.length; i--) {
					ocean.getShipArray()[i][j] = this;
				}
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column,
	 * and the ship hasn’t been sunk, mark that part of the ship as “hit”
	 * (in the hit array, index 0 indicates the bow) 
	 * and return true; otherwise return false
	 * @param row
	 * @param column
	 * @return true if there's an afloat ship, otherwise false
	 */
	public boolean shootAt(int row, int column) {

		if(this.horizontal == true) {
			if(row == this.bowRow && column > this.bowColumn- this.length 
					&& column <= this.bowColumn && this.isSunk() == false) {
				this.hit[this.bowColumn-column] = true;
				return true;
			}
			else {
				return false;
			}
		}		
		else {
			if(column == this.bowColumn && row > this.bowRow - this.length 
					&& row <= this.bowRow && this.isSunk() == false) {
				this.hit[this.bowRow - row] = true;
				return true;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * Check if the ship is Sunk.
	 * @return true if every part of the ship has been hit, false otherwise
	 */
	public boolean isSunk() {
		// local variable
		boolean Sunk = true;

		// check the hit array, and if one of them is false
		// return false
		for (int i = 0; i < this.length; i++) {
			if (this.hit[i] == false) {
				Sunk = false;
				break;
			}
			else {
				Sunk = true;
			}
		}

		return Sunk;
	}

	/**
	 * Returns one of the strings “battleship”, “cruiser”, “destroyer”,
	 * or “submarine”, as appropriate.
	 */
	@Override
	public String toString() {
		/**
		 * update methods
		 */
		String string = null;
		for(int i = 0; i < this.length; i++) {
			if(this.isSunk()) {
				string = "s";
			}
			else if(hit[i] == true ) {
				string = "x";
				break;
			}
			else {
				string = "B";
			}
		}
		return string;

	}
}
