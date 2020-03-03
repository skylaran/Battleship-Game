package battleship;

/**
 * Describes the ship class extended class, submarine
 * @author Keyao An , Haoyu Han
 *
 */
public class Submarine extends Ship {

	// instance variables
	private String shiptype = "submarine";
	
	// constructor
	public Submarine(int length) {
		super(1);
	}
	
	@Override
	public String getShipType() {
		return shiptype;
	}

}
