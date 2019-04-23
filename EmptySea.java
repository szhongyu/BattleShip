package battleship;

public class EmptySea extends Ship {

	public EmptySea() {
		this.length = 1;
	}

	@Override
	public boolean shootAt(int row, int column) {
		return false;
	}

	@Override
	public boolean isSunk() {
		return false;
	}

	@Override
	public String toString() {
		return "-";
	}

	public String getShipType() {
		return "empty";
	}
}
