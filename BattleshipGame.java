package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		boolean checker = ocean.isGameOver();
		Scanner scanner = new Scanner(System.in);
		// the loop will not end until the game is over
		while (checker == false) {
			ocean.print();
			System.out.println("Please give me the row you want to shoot: ");
			int numOne = scanner.nextInt();
			// check whether the input of row number in the correct range
			while (numOne > 9 || numOne < 0) {
				System.out.println("input not in the correct range, pleanse give another");
				numOne = scanner.nextInt();
			}
			System.out.println("Please give me the column you want to shoot: ");
			int numTwo = scanner.nextInt();
			// check whether the input of column number in the correct range
			while (numTwo > 9 || numTwo < 0) {
				System.out.println("input not in the correct range, please give another");
				numTwo = scanner.nextInt();
			}
			boolean shoot = ocean.shootAt(numOne, numTwo);
			Ship[][] gameArray = ocean.getShipArray();
			if (shoot == true) {
				System.out.println("Hit!");
			} 
			else {
				System.out.println("Miss!");
			}
			// make sure "hit" information only shows once
			if (gameArray[numOne][numTwo].isSunk() == true && shoot == true) {
				System.out.println("You just sank a ship - " + 
						gameArray[numOne][numTwo].getShipType());
			}
			checker = ocean.isGameOver();
		}
		scanner.close();
		// Once the game is over, print the array and other output we want
		if (checker == true) {
			ocean.print();
			System.out.println("Congradulations! You win!!");
			System.out.println("Your total shoots are " + ocean.getShotsFired());
			System.out.println(ocean.getShotsFired() + 
					" steps are required to finish this game");
			System.out.println("The effective hit is " + ocean.getHitCount());
		}
	}
}