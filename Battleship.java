package battleship;

/**
 * Describes the ship class extended class, battleship
 * @author Keyao An , Haoyu Han
 *
 */
public class Battleship extends Ship {

	// instance variables
	private String shiptype = "battleship";
	
	// constructor
	public Battleship(int length) {
		super(4);
	}
	
	// methods	
	@Override
	public String getShipType() {
		return shiptype;
	}

}
