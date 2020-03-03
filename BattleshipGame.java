package battleship;

import java.util.Scanner;

/**
 * This is the main class, containing the main method
 * @author Keyao An , Haoyu Han
 *
 */
public class BattleshipGame {

	public static void main(String[] args) {

		// create an instance
		Ocean ocean = new Ocean();
		
		// place the ships randomly
		ocean.placeAllShipsRandomly();
		ocean.print();
		
		// use scanner to get the console input
		Scanner in = new Scanner(System.in);
		
		// while the game is not over, keep get the input position and shoot.
		while(!ocean.isGameOver()) {
			// get the input position to fire			
			int row = in.nextInt();
			int column = in.nextInt();
			
			// shoot the given location.
			// if the input is out of range, get a new input.
			try {
				ocean.shootAt(row, column);				
			}catch (Exception e) {
				System.out.println("The input should in the range of 10. Please try again.");
				continue;
			}
			// print the result
			ocean.print();
			System.out.println();
			
		}
		// Congratulation!
		System.out.println("*********************************************************");
		System.out.println("*****Congratulation! You shoot down all 10 ships!!!!*****");
		System.out.println("*********************************************************");	
		in.close();
		
	}

}
