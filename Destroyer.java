package battleship;

/**
 * Describes the ship class extended class, destroyer
 * @author Keyao An , Haoyu Han
 *
 */
public class Destroyer extends Ship {

	// instance variables
	private String shiptype = "destroyer";
	
	// constructor
	public Destroyer(int length) {
		super(2);
	}
	
	@Override
	public String getShipType() {
		return shiptype;
	}

}
