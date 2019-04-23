package battleship;

import java.util.*;
import java.util.Random;

public class Ocean {
	protected Ship[][] ships = new Ship[10][10];
	protected int shotsFired;
	protected int hitCount;
	protected int shipsSunk;
	protected Battleship battleShip = new Battleship();
	protected Cruiser cruiser1 = new Cruiser();
	protected Cruiser cruiser2 = new Cruiser();
	protected Destroyer destroyer1 = new Destroyer();
	protected Destroyer destroyer2 = new Destroyer();
	protected Destroyer destroyer3 = new Destroyer();
	protected Submarine submarine1 = new Submarine();
	protected Submarine submarine2 = new Submarine();
	protected Submarine submarine3 = new Submarine();
	protected Submarine submarine4 = new Submarine();
	protected String[][] printArray = new String[11][11];
	protected ArrayList<String> hitArrayList = new ArrayList<String>();

	/**
	 * This is the constructor
	 */
	public Ocean() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}

	/**
	 * This method helps randomly place all ships
	 */
	public void placeAllShipsRandomly() {
		// create a ship list
		Ship[] shipList = { battleShip, cruiser1, cruiser2, 
				destroyer1, destroyer2, destroyer3, submarine1, submarine2,
				submarine3, submarine4 };
		// place the ship in the ship list one by one
		for (int i = 0; i < 10; i++) {
			boolean placeBoolean = false;
			// the loop will not end only if the placeBoolean is true
			while (placeBoolean == false) {
				Random rand = new Random();
				int value1 = rand.nextInt(10);
				int value2 = rand.nextInt(10);
				int valueBoolean = rand.nextInt(2);
				boolean horizontalOrNot = false;
				if (valueBoolean == 0) {
					horizontalOrNot = true;
				} 
				else {
					horizontalOrNot = false;
				}
				boolean checkBoolean = shipList[i].okToPlaceShipAt(value1, 
						value2, horizontalOrNot, this);
				if (checkBoolean == true) {
					shipList[i].placeShipAt(value1, value2, horizontalOrNot, this);
				}
				placeBoolean = checkBoolean;
			}
		}
	}

	/**
	 * This method helps determine whether there is a ship on the certain location
	 * @param row
	 * @param column
	 * @return the boolean which check is certain location contains a ship
	 */
	public boolean isOccupied(int row, int column) {
		if (this.getShipArray()[row][column] instanceof EmptySea) {
			return false;
		} 
		else {
			return true;
		}
	}

	/**
	 * This method helps determine whether certain location contains a ship and
	 * isn't sunk
	 * @param row
	 * @param column
	 * @return boolean which check certain location contains a ship and is not sunk
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		// create a hitArray list which will record the hit history
		String location = row + "," + column;
		hitArrayList.add(location);
		Ship whichShip = this.getShipArray()[row][column];
		// when the certain spot contains a ship
		if (this.isOccupied(row, column) == true) {
			// when the ship is not sunk and satisfy the shootAt method in the ship class
			if (whichShip.isSunk() == false && whichShip.shootAt(row, column) == true) {
				hitCount++;
				// when the ship sunk, the number of shipsSunk will add 1
				if (whichShip.isSunk() == true) {
					shipsSunk++;
				}
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

	/**
	 * This method helps get the total shots fired
	 * @return the total shots fired
	 */
	public int getShotsFired() {
		return shotsFired;
	}

	/**
	 * This function helps get the to total effective shoot
	 * @return the total effective shoot
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * This method helps get the total ships sunk
	 * @return the number of total ships sunk
	 */
	public int getShipsSunk() {
		return shipsSunk;
	}

	/**
	 * This function helps check whether the game is over
	 * @return the boolean which represents the game is over or not
	 */
	public boolean isGameOver() {
		// if the number of shipsSunk equals to 10, then the game is over
		int numberShipsSunk = this.getShipsSunk();
		if (numberShipsSunk == 10) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * This method helps get the ships array
	 * @return the ships array
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * This method helps print out the output type we want
	 */
	public void print() {
		// create a new String array for output print
		String[] hitArray = hitArrayList.toArray(new String[hitArrayList.size()]);
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				// fill the print array with "."
				printArray[i][j] = ".";
				// change the first row with the number represents the row of game array
				printArray[0][j] = "" + (j - 1);
				// change the column row with the number represents the column of game array
				printArray[i][0] = "" + (i - 1);
				printArray[0][0] = "O";
				for (int k = 0; k < hitArray.length; k++) {
					// get each element of the hitArray, which is the shot history
					String[] parts = hitArray[k].split(",");
					String partA = parts[0];
					String partB = parts[1];
					// change the coordinate string to two integers
					int rowOfString = Integer.valueOf(partA);
					int columnOfString = Integer.valueOf(partB);
					// replace the "." at the certain location of the printArray
					// with the correspond ship type at the certain location of shipArray
					printArray[rowOfString + 1][columnOfString + 1] = 
							this.getShipArray()[rowOfString][columnOfString].toString();
				}
				// print the printArray
				System.out.print(printArray[i][j] + "  ");
			}
			System.out.println();
		}

	}
}
