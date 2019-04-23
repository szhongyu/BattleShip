package battleship;

public class Destroyer extends Ship {
	public Destroyer() {
		this.length = 2;
	}

	@Override
	public String getShipType() {
		return "destroyer";
	}

}
