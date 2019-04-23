package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
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
	public void testGetLength() {
		int expected = 4;
		int actual = battleShip.getLength();
		assertEquals("The Length of battleship is 4", expected, actual);
		int expected2 = 3;
		int actual2 = cruiser1.getLength();
		assertEquals("The Length of cruiser is 3", expected2, actual2);
		int expected3 = 2;
		int actual3 = destroyer1.getLength();
		assertEquals("The Length of destroyer is 2", expected3, actual3);
	}

	@Test
	public void testGetBowRow() {
		battleShip.placeShipAt(5, 6, true, oceanTest);
		int expected = 5;
		int actual = battleShip.getBowRow();
		assertEquals("The bow row of battleship should be 5", expected, actual);
		cruiser1.placeShipAt(0, 0, false, oceanTest);
		int expected2 = 0;
		int actual2 = cruiser1.getBowRow();
		assertEquals("The bow row of cruiser1 should be 0", expected2, actual2);
		submarine1.placeShipAt(6, 5, true, oceanTest);
		int expected3 = 6;
		int actual3 = submarine1.getBowRow();
		assertEquals("The bow row of submarine1 should be 6", expected3, actual3);
	}

	@Test
	public void testGetBowColumn() {
		battleShip.placeShipAt(5, 6, true, oceanTest);
		int expected = 6;
		int actual = battleShip.getBowColumn();
		assertEquals("The bow column of battleship should be 6", expected, actual);
		cruiser1.placeShipAt(0, 0, false, oceanTest);
		int expected2 = 0;
		int actual2 = cruiser1.getBowColumn();
		assertEquals("The bow column of cruiser1 should be 0", expected2, actual2);
		submarine1.placeShipAt(6, 5, true, oceanTest);
		int expected3 = 5;
		int actual3 = submarine1.getBowColumn();
		assertEquals("The bow column of submarine1 should be 6", expected3, actual3);
	}

	@Test
	public void testGetHit() {
		// if the ship is not be hit
		boolean[] expected = { false, false, false, false };
		boolean[] actual = battleShip.getHit();
		assertEquals("boolean array should be same as the prigin one", expected[0], actual[0]);
		assertEquals("boolean array should be same as the prigin one", expected[1], actual[1]);
		assertEquals("boolean array should be same as the prigin one", expected[2], actual[2]);
		assertEquals("boolean array should be same as the prigin one", expected[3], actual[3]);
		// the length of the ship is less than 4;
		cruiser1.placeShipAt(5, 5, true, oceanTest);
		cruiser1.shootAt(5, 6);
		boolean[] expected2 = { false, true, false, true };
		boolean[] actual2 = cruiser1.getHit();
		assertEquals("first element should be false", expected2[0], actual2[0]);
		assertEquals("second element should be true", expected2[1], actual2[1]);
		assertEquals("third element should be false", expected2[2], actual2[2]);
		assertEquals("last element should be true", expected2[3], actual2[3]);
		// the length of the ship is 4 and get hit
		battleShip.placeShipAt(0, 0, true, oceanTest);
		battleShip.shootAt(0, 1);
		boolean[] expected3 = { false, true, false, false };
		boolean[] actual3 = battleShip.getHit();
		assertEquals("first element should be false", expected3[0], actual3[0]);
		assertEquals("second element should be true", expected3[1], actual3[1]);
		assertEquals("third element should be false", expected3[2], actual3[2]);
		assertEquals("last element should be false", expected3[3], actual3[3]);
	}

	@Test
	public void testIsHorizontal() {
		battleShip.placeShipAt(5, 6, true, oceanTest);
		boolean expected = true;
		boolean actual = battleShip.isHorizontal();
		assertEquals("battleship is horizontal", expected, actual);
		cruiser1.placeShipAt(0, 0, false, oceanTest);
		boolean expected2 = false;
		boolean actual2 = cruiser1.isHorizontal();
		assertEquals("cruiser1 is not horizontal", expected2, actual2);
		submarine1.placeShipAt(6, 5, true, oceanTest);
		boolean expected3 = true;
		boolean actual3 = submarine1.isHorizontal();
		assertEquals("submarine1 is horizontal", expected3, actual3);
	}

	@Test
	public void testGetShipType() {
		String expected = "battleship";
		String actual = battleShip.getShipType();
		assertEquals("the type of battleShip is battleship", expected, actual);
		String expected2 = "cruiser";
		String actual2 = cruiser2.getShipType();
		assertEquals("the type of cruiser2 is cruiser", expected2, actual2);
		// test empty sea
		String expected3 = "empty";
		String actual3 = emptySea.getShipType();
		assertEquals("the type of emptySea is empty", expected3, actual3);
	}

	@Test
	public void testSetBowRow() {
		battleShip.setBowRow(5);
		int expected = 5;
		int actual = battleShip.getBowRow();
		assertEquals("the bow row should equals to 5", expected, actual);
		cruiser1.setBowRow(0);
		int expected2 = 0;
		int actual2 = cruiser1.getBowRow();
		assertEquals("the bow row should equals to 0", expected2, actual2);
		submarine1.setBowRow(0);
		int expected3 = 0;
		int actual3 = submarine1.getBowRow();
		assertEquals("the bow row should equals to 0", expected3, actual3);
	}

	@Test
	public void testSetBowColumn() {
		battleShip.setBowColumn(5);
		int expected = 5;
		int actual = battleShip.getBowColumn();
		assertEquals("the bow column should equals to 5", expected, actual);
		cruiser1.setBowColumn(0);
		int expected2 = 0;
		int actual2 = cruiser1.getBowColumn();
		assertEquals("the bow column should equals to 0", expected2, actual2);
		submarine1.setBowColumn(0);
		int expected3 = 0;
		int actual3 = submarine1.getBowColumn();
		assertEquals("the bow column should equals to 0", expected3, actual3);
	}

	@Test
	public void testSetHorizontal() {
		battleShip.setHorizontal(true);
		boolean expected = true;
		boolean actual = battleShip.isHorizontal();
		assertEquals("battleShip should be horizontal", expected, actual);
		cruiser1.setHorizontal(false);
		boolean expected2 = false;
		boolean actual2 = cruiser1.isHorizontal();
		assertEquals("cruiser1 should be horizontal", expected2, actual2);
		submarine1.setHorizontal(true);
		boolean expected3 = true;
		boolean actual3 = submarine1.isHorizontal();
		assertEquals("submarine1 should be horizontal", expected3, actual3);
	}

	@Test
	public void testOkToPlaceShipAt() {
		// no ship in the oceanTest
		boolean expected = true;
		boolean actual = battleShip.okToPlaceShipAt(5, 5, true, oceanTest);
		assertEquals("We can put any ship in the empty sea", expected, actual);
		battleShip.placeShipAt(5, 5, false, oceanTest);
		boolean expected2 = false;
		boolean actual2 = cruiser1.okToPlaceShipAt(5, 4, true, oceanTest);
		assertEquals("We can't put cruiser1 in that location since the battleShip is naerby", expected2, actual2);
		cruiser1.placeShipAt(0, 2, true, oceanTest);
		boolean expected3 = true;
		boolean actual3 = submarine1.okToPlaceShipAt(0, 0, true, oceanTest);
		assertEquals("submarine1 is not adjacent to cruiser1", expected3, actual3);
		// outside the scale
		boolean expected4 = false;
		boolean actual4 = cruiser2.okToPlaceShipAt(8, 8, true, oceanTest);
		assertEquals("can't place the ship out of ocean scale", expected4, actual4);
		// row outside the scale
		boolean expected5 = false;
		boolean actual5 = cruiser2.okToPlaceShipAt(8, 10, true, oceanTest);
		assertEquals("can't place the ship out of ocean scale", expected5, actual5);
	}

	@Test
	public void testPlaceShipAt() {
		// horizontal
		battleShip.placeShipAt(5, 5, true, oceanTest);
		String expected = "battleship";
		String actual = oceanTest.getShipArray()[5][5].getShipType();
		String actual1 = oceanTest.getShipArray()[5][6].getShipType();
		String actual2 = oceanTest.getShipArray()[5][7].getShipType();
		String actual3 = oceanTest.getShipArray()[5][8].getShipType();
		assertEquals("on (5,5) should be battleShip", expected, actual);
		assertEquals("on (5,6) should be battleShip", expected, actual1);
		assertEquals("on (5,7) should be battleShip", expected, actual2);
		assertEquals("on (5,8) should be battleShip", expected, actual3);
		// vertical
		cruiser1.placeShipAt(6, 2, false, oceanTest);
		String expected2 = "cruiser";
		String actual4 = oceanTest.getShipArray()[6][2].getShipType();
		String actual5 = oceanTest.getShipArray()[7][2].getShipType();
		String actual6 = oceanTest.getShipArray()[8][2].getShipType();
		assertEquals("On (6,2) should be cruiser", expected2, actual4);
		assertEquals("On (7,2) should be cruiser", expected2, actual5);
		assertEquals("On (8,2) should be cruiser", expected2, actual6);
		// place ship at the corner/edge
		submarine1.placeShipAt(0, 0, true, oceanTest);
		String expected3 = "submarine";
		String expected4 = "empty";
		String actual7 = oceanTest.getShipArray()[0][0].getShipType();
		String actual8 = oceanTest.getShipArray()[0][1].getShipType();
		assertEquals("on (0,0) should be submarine", expected3, actual7);
		assertEquals("on (0,1) should be empty sea", expected4, actual8);
	}

	@Test
	public void testIsSunk() {
		// not sunk
		battleShip.placeShipAt(5, 5, true, oceanTest);
		battleShip.shootAt(5, 5);
		battleShip.shootAt(5, 6);
		boolean expected = false;
		boolean actual = battleShip.isSunk();
		assertEquals("battleShip is not sunk", expected, actual);
		// sunk
		cruiser1.placeShipAt(0, 0, true, oceanTest);
		cruiser1.shootAt(0, 0);
		cruiser1.shootAt(0, 1);
		cruiser1.shootAt(0, 2);
		boolean expected2 = true;
		boolean actual2 = cruiser1.isSunk();
		assertEquals("cruiser1 is sunk", expected2, actual2);
		// length less than 1
		submarine1.placeShipAt(8, 7, false, oceanTest);
		submarine1.shootAt(8, 7);
		boolean expected3 = true;
		boolean actual3 = submarine1.isSunk();
		assertEquals("submarine1 is sunk", expected3, actual3);
	}

	@Test
	public void testShootAt() {
		// ship not on that location
		battleShip.placeShipAt(5, 5, true, oceanTest);
		boolean expected = false;
		boolean actual = battleShip.shootAt(0, 5);
		assertEquals("battleShip is not on the shoot location", expected, actual);
		// ship on the location and ship is not sink
		boolean expected2 = true;
		boolean actual2 = battleShip.shootAt(5, 6);
		assertEquals("battleShip is on the shoot location and not sink", expected2, actual2);
		// ship sunk
		battleShip.shootAt(5, 5);
		battleShip.shootAt(5, 7);
		battleShip.shootAt(5, 8);
		boolean expected3 = false;
		boolean actual3 = battleShip.shootAt(5, 6);
		assertEquals("battleShip sunk", expected3, actual3);

	}

	@Test
	public void testToString() {
		// test string of the ship if it is not sunk
		String expected = "S";
		String actual = battleShip.toString();
		assertEquals("battleShip isn't sunk, return 'S'", expected, actual);
		submarine1.placeShipAt(5, 5, true, oceanTest);
		submarine1.shootAt(5, 5);
		// test string of the ship if it is sunk
		String expected2 = "x";
		String actual2 = submarine1.toString();
		assertEquals("submarine is sunk, return 'x'", expected2, actual2);
		// test string if it is empty sea
		String expected3 = "-";
		String actual3 = emptySea.toString();
		assertEquals("empty sea should return '-' ", expected3, actual3);

	}

}
