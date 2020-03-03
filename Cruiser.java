package battleship;

/**
 * Describes the ship class extended class, cruiser
 * @author Keyao An , Haoyu Han
 *
 */
public class Cruiser extends Ship {

	// instructor
	private String shiptype = "cruiser";
	
	// constructor
	public Cruiser(int length) {
		super(3);
	}
	
	@Override
	public String getShipType() {
		return shiptype;
	}

}
