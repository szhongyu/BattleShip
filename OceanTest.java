package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class OceanTest {
	Ocean oceanTest = new Ocean();
	Battleship battleShip = new Battleship();
	Cruiser cruiser1 = new Cruiser();
	Cruiser cruiser2 = new Cruiser();
	Destroyer destroyer1 = new Destroyer();
	Destroyer destroyer2 = new Destroyer();
	Destroyer destroyer3 = new Destroyer();
	Submarine submarine1 = new Submarine();
	Submarine submarine2 = new Submarine();
	Submarine submarine3 = new Submarine();
	Submarine submarine4 = new Submarine();
	EmptySea emptySea = new EmptySea();

	@Test
	public void testOceanInt() {
		assertEquals("default shots fired is 0", 0, oceanTest.shotsFired);
		assertEquals("default hit count is 0", 0, oceanTest.hitCount);
		assertEquals("default ship sunk is 0", 0, oceanTest.shipsSunk);
	}

	@Test
	public void testOceanArray() {
		String expected = "empty";
		String actual = oceanTest.ships[0][0].getShipType();
		assertEquals("every spot of the ship array should be empty sea", expected, actual);
		String actual2 = oceanTest.ships[5][5].getShipType();
		assertEquals("every spot of the ship array should be empty sea", expected, actual2);
		String actual3 = oceanTest.ships[3][4].getShipType();
		assertEquals("every spot of the ship array should be empty sea", expected, actual3);
	}

	@Test
	public void testPlaceAllShipsRandomlyNumberOfTotalShips() {
		oceanTest.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				oceanTest.shootAt(i, j);
			}
		}
		int expected = 10;
		int actual = oceanTest.getShipsSunk();
		assertEquals("The total number of ships is 10", expected, actual);
	}

	@Test
	public void testPlaceAllShipsRandomlyNumberOfEachShips() {
		oceanTest.placeAllShipsRandomly();
		int battleShipCounter = 0;
		int cruiserCounter = 0;
		int destroyerCounter = 0;
		int submarineCounter = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (oceanTest.getShipArray()[i][j].getShipType() == "battleship") {
					battleShipCounter++;
				}
				if (oceanTest.getShipArray()[i][j].getShipType() == "cruiser") {
					cruiserCounter++;
				}
				if (oceanTest.getShipArray()[i][j].getShipType() == "destroyer") {
					destroyerCounter++;
				}
				if (oceanTest.getShipArray()[i][j].getShipType() == "submarine") {
					submarineCounter++;
				}
			}
		}
		battleShipCounter = battleShipCounter / 4;
		cruiserCounter = cruiserCounter / 3;
		destroyerCounter = destroyerCounter / 2;
		submarineCounter = submarineCounter / 1;
		assertEquals("The number of battleship is 1", 1, battleShipCounter);
		assertEquals("The number of cruiser is 2", 2, cruiserCounter);
		assertEquals("The number of destroyer is 3", 3, destroyerCounter);
		assertEquals("The number of submarine is 4", 4, submarineCounter);
	}

	@Test
	public void testIsOccupied() {
		// ship is occupied on the certain location (test parts except the bow of ship)
		battleShip.placeShipAt(5, 5, true, oceanTest);
		cruiser1.placeShipAt(8, 6, true, oceanTest);
		boolean expected = true;
		boolean actual = oceanTest.isOccupied(5, 8);
		assertEquals("on (5,8) ship is occupied", expected, actual);
		// ship is occupied on the certain location (test the row of the ship)
		boolean expected2 = true;
		boolean actual2 = oceanTest.isOccupied(8, 6);
		assertEquals("on (8,6) ship is occupied", expected2, actual2);
		// ship is not occupied on the certain location
		boolean expected3 = false;
		boolean actual3 = oceanTest.isOccupied(0, 1);
		assertEquals("on (0,1) ship is not occupied", expected3, actual3);
	}

	@Test
	public void testShootAt() {
		// no ship on the certain location
		battleShip.placeShipAt(5, 5, true, oceanTest);
		cruiser1.placeShipAt(0, 0, false, oceanTest);
		boolean expected = false;
		boolean actual = oceanTest.shootAt(0, 8);
		assertEquals("no ship on (0,8)", expected, actual);
		// ship on the certain location and is not sunk
		boolean expected2 = true;
		boolean actual2 = oceanTest.shootAt(5, 7);
		assertEquals("battleShip is on (5,7) and not sunk", expected2, actual2);
		battleShip.shootAt(5, 5);
		battleShip.shootAt(5, 6);
		// ship on the certain location and after current shoot, it will sink
		boolean expected3 = true;
		boolean actual3 = oceanTest.shootAt(5, 8);
		assertEquals("battleShip is on (5,8) and not sunk", expected3, actual3);
		// ship on the certain location but is sunk
		boolean expected4 = false;
		boolean actual4 = oceanTest.shootAt(5, 8);
		assertEquals("battleShip is on (5,8) and not sunk", expected4, actual4);
	}

	@Test
	public void testGetShotsFired() {
		// no shoots
		int expected = 0;
		int actual = oceanTest.getShotsFired();
		assertEquals("no shots fired", expected, actual);
		oceanTest.shootAt(5, 5);
		// shot one times
		int expected1 = 1;
		int actual1 = oceanTest.getShotsFired();
		assertEquals("one shots fired", expected1, actual1);
		oceanTest.shootAt(5, 5);
		oceanTest.shootAt(5, 6);
		// shot multiple times
		int expected2 = 3;
		int actual2 = oceanTest.getShotsFired();
		assertEquals("one shots fired", expected2, actual2);

	}

	// problem
	@Test
	public void testGetHitCount() {
		battleShip.placeShipAt(5, 5, true, oceanTest);
		// no shoots
		int expected = 0;
		int actual = oceanTest.getHitCount();
		assertEquals("no shots fired", expected, actual);
		oceanTest.shootAt(0, 1);
		oceanTest.shootAt(5, 5);
		// two shoots with one hit
		int expected2 = 1;
		int actual2 = oceanTest.getHitCount();
		assertEquals("One hit count", expected2, actual2);
		oceanTest.shootAt(5, 5);
		oceanTest.shootAt(5, 6);
		oceanTest.shootAt(5, 7);
		oceanTest.shootAt(5, 8);
		// multiple hits
		int expected3 = 5;
		int actual3 = oceanTest.getHitCount();
		assertEquals("five hits count", expected3, actual3);
		// hits will not increase after ship is sunk
		oceanTest.shootAt(5, 8);
		int expected4 = 5;
		int actual4 = oceanTest.getHitCount();
		assertEquals("five hits count because the ship is sunk", expected4, actual4);

	}

	@Test
	public void testGetShipSunk() {
		// no ship sunk
		int expected = 0;
		int actual = oceanTest.getShipsSunk();
		assertEquals("no ships sunk", expected, actual);
		battleShip.placeShipAt(5, 5, true, oceanTest);
		oceanTest.shootAt(5, 5);
		oceanTest.shootAt(5, 6);
		oceanTest.shootAt(5, 7);
		oceanTest.shootAt(5, 8);
		// one ship sunk
		int expected2 = 1;
		int actual2 = oceanTest.getShipsSunk();
		assertEquals("one ship sunk", expected2, actual2);
		submarine1.placeShipAt(0, 0, true, oceanTest);
		oceanTest.shootAt(0, 0);
		// multiple ships sunk
		int expected3 = 2;
		int actual3 = oceanTest.getShipsSunk();
		assertEquals("two ship sunk", expected3, actual3);
	}

	@Test
	public void testIsGameOverNotOver() {
		// no shoots made, game is not over
		boolean expected = false;
		boolean actual = oceanTest.isGameOver();
		assertEquals("Game is not over, no ships are sunk", expected, actual);
		assertEquals("no ships sunk", expected, actual);
		battleShip.placeShipAt(5, 5, true, oceanTest);
		oceanTest.shootAt(5, 5);
		oceanTest.shootAt(5, 6);
		oceanTest.shootAt(5, 7);
		oceanTest.shootAt(5, 8);
		// one ship sunk, game is not over
		boolean expected2 = false;
		boolean actual2 = oceanTest.isGameOver();
		assertEquals("Game is not over, one ship is sunk", expected2, actual2);
	}

	@Test
	public void testIsGameOverOver() {
		// all ships sunk, game is over
		oceanTest.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				oceanTest.shootAt(i, j);
			}
		}
		boolean expected = true;
		boolean actual = oceanTest.isGameOver();
		assertEquals("every spot is shoot, the game is over", expected, actual);
	}

	@Test
	public void testGetShipArray() {
		// test the size of the rows and columns
		int expectedRow = 10;
		int actualRow = oceanTest.getShipArray().length;
		assertEquals("oceanTest has ten row", expectedRow, actualRow);
		int expectedColumn = 10;
		int actualColumn = oceanTest.getShipArray()[0].length;
		assertEquals("oceanTest has 10 columns", expectedColumn, actualColumn);
		// check whether the type of ship on the certain location is correct
		battleShip.placeShipAt(5, 5, true, oceanTest);
		String expectedString = "battleship";
		String actualString = oceanTest.getShipArray()[5][5].getShipType();
		String actualString2 = oceanTest.getShipArray()[5][6].getShipType();
		String actualString3 = oceanTest.getShipArray()[5][7].getShipType();
		String actualString4 = oceanTest.getShipArray()[5][8].getShipType();
		assertEquals("on (5,5) is battleship", expectedString, actualString);
		assertEquals("on (5,6) is battleship", expectedString, actualString2);
		assertEquals("on (5,7) is battleship", expectedString, actualString3);
		assertEquals("on (5,8) is battleship", expectedString, actualString4);
		String expectedString2 = "empty";
		String actualString5 = oceanTest.getShipArray()[5][9].getShipType();
		String actualString6 = oceanTest.getShipArray()[0][0].getShipType();
		assertEquals("on (5,9) is empty sea", expectedString2, actualString5);
		assertEquals("on (0,0) is empty sea", expectedString2, actualString6);
	}
}
