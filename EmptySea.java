package battleship;

/**
 * Describes the ship class extended class, empty sea
 * @author Keyao An , Haoyu Han
 *
 */
public class EmptySea extends Ship {

	// instance variables
	private String shiptype = "empty";
	
	// constructor
	public EmptySea() {
		super(1);
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}
	
	@Override
	public boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		if (this.getHit()[0]) {
			return "-";
		}
		else {
			return ".";
		}

	}
	
	@Override
	public String getShipType() {
		return shiptype;
	}

}
