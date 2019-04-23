package battleship;

public class Ship {
	private int bowRow;
	private int bowColumn;
	protected int length;
	private boolean horizontal;
	private boolean[] hit = { false, false, false, false };

	/**
	 * This getter will help get the length of each ship
	 * @return the length of each type of the ship
	 */
	public int getLength() {
		return length;
	}

	/**
	 * This getter helps get the row of bow of each ship
	 * @return the row number of bow of each type of the ship
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * This getter helps get the column of bow of each ship
	 * @return the column number of bow of each type of the ship
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * This getter helps get the hit array of each ship
	 * @return the boolean hit array of each type of the ship
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * This getter helps get the direction of each ship
	 * @return the boolean which represents the direction of each type of the ship
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * This getter helps get the type of each ship
	 * @return the type name of each type of each ship in string
	 */
	public String getShipType() {
		return "";
	}

	/**
	 * This setter helps each ship set the row location of the row
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * This setter helps each ship set the column location of the row
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * This setter helps each ship set its direction
	 * @param horizontal
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * This methods helps identify whether the ship type is OK to place at certain location
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return the boolean that represents whether the ship can place at certain location
	 */

	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		int lengthOfShip = this.getLength();
		int rowStart = row;
		int columnStart = column;
		int rowEnd = 0;
		int columnEnd = 0;
		int falseCounter = 0;
		// in case the start of the bow locate at left edge
		if (row == 0) {
			rowStart = row + 1;
		}
		if (column == 0) {
			columnStart = column + 1;
		}
		// when the direction of the ship is horizontal
		if (horizontal == true) {
			columnEnd = column + lengthOfShip - 1;
			// in case the end of the bowl locate out of scale
			if (columnEnd > 9) {
				return false;
			} 
			else {
				// in case the end of the bow locate at the bottom edge
				if (columnEnd == 9) {
					columnEnd = columnEnd - 1;
				}
				// in case the end of the bow locate at the right edge
				if (row == 9) {
					rowEnd = row;
				} 
				else {
					rowEnd = row + 1;
				}
				// check whether the adjacent spot is all empty sea
				for (int i = columnStart - 1; i < columnEnd + 2; i++) {
					for (int j = rowStart - 1; j < rowEnd + 1; j++) {
						if (!(ocean.getShipArray()[j][i] instanceof EmptySea)) {
							falseCounter++;
						}
					}
				}
			}
		}
		// considering the same cases when the direction of the ship is vertical
		else {
			rowEnd = row + lengthOfShip - 1;
			if (rowEnd > 9) {
				return false;
			} 
			else {
				if (rowEnd == 9) {
					rowEnd = rowEnd - 1;
				}
				if (column == 9) {
					columnEnd = column;
				} 
				else {
					columnEnd = column + 1;
				}
				for (int k = rowStart - 1; k < rowEnd + 2; k++) {
					for (int p = columnStart - 1; p < columnEnd + 1; p++) {
						if (!(ocean.getShipArray()[k][p] instanceof EmptySea)) {
							falseCounter++;
						}
					}
				}
			}
		}
		if (falseCounter == 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * This method helps place each type of the ship at certain location
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// set the bow role column and the direction of the ship
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		int lengthOfShip1 = this.getLength();
		if (horizontal == true) {
			for (int i = column; i < (column + lengthOfShip1); i++) {
				ocean.getShipArray()[row][i] = this;
			}
		} 
		else {
			for (int j = row; j < (row + lengthOfShip1); j++) {
				ocean.getShipArray()[j][column] = this;
			}
		}
	}

	/**
	 * This method helps check whether certain ship is sunk or not
	 * @return the boolean which represents the ship is sunk
	 */
	public boolean isSunk() {
		int lengthOfShip2 = this.getLength();
		int falseCounter1 = 0;
		boolean[] hitSunk = this.getHit();
		// if the length of the ship is n, less than four, automatically change the
		// the index from n to 3 of the boolean arrayList to true
		if (lengthOfShip2 < 4) {
			for (int i = lengthOfShip2; i < 4; i++) {
				hitSunk[i] = true;
			}
		}
		// if all elements of the arrayList is true, the ship sunk; otherwise not.
		for (int j = 0; j < lengthOfShip2; j++) {
			if (hitSunk[j] == false) {
				falseCounter1++;
			}
		}
		if (falseCounter1 == 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * This method helps check is the ship in the certain location and isn't sunk
	 * @param row
	 * @param column
	 * @return the boolean that checks whether the ship is the certain location and isn't sunk
	 */
	public boolean shootAt(int row, int column) {
		int rowOfBow = this.getBowRow();
		int columnOfBow = this.getBowColumn();
		int lengthOfShip3 = this.getLength();
		boolean horizontalOrNot = this.isHorizontal();
		// make sure the ship is not sunk
		if (this.isSunk() == false) {
			if (horizontalOrNot == true) {
				if (row == rowOfBow) {
					if (columnOfBow <= column && column <= columnOfBow + lengthOfShip3) {
						// change the hit array when shoot at the certain location
						hit[column - columnOfBow] = true;
						return true;
					} 
					else {
						return false;
					}
				} 
				else {
					return false;
				}
			}
			// same strategy when the direction of the ship is vertical
			else {
				if (column == columnOfBow) {
					if (rowOfBow <= row && row <= rowOfBow + lengthOfShip3) {
						hit[row - rowOfBow] = true;
						return true;
					} 
					else {
						return false;
					}
				} 
				else {
					return false;
				}
			}

		} 
		else {
			return false;
		}
	}

	/**
	 * This method override the toString method and will helps print the output type we want
	 */
	@Override
	// override toString method
	public String toString() {
		if (this.isSunk() == true) {
			return "x";
		} 
		else {
			return "S";
		}
	}
}
