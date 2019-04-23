package battleship;

public class Battleship extends Ship {
	public Battleship() {
		this.length = 4;
	}

	@Override
	public String getShipType() {
		return "battleship";
	}
}
