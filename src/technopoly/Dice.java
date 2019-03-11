package technopoly;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author fearg
 *
 */
public class Dice {

	public final static int MIN_ROLL = 2;
	public final static int MAX_ROLL = 12;
	
	private int value;
	
	/**
	 * default constructor
	 */
	public Dice() {

	}

	/**
	 * constructor with arguments
	 * @param value
	 */
	public Dice(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param gameBoard
	 * @return
	 */
	public int generateRoll() throws InputMismatchException {
		int roll = 0;
		Scanner scanner = new Scanner(System.in);
		String answer = "";
		
		
		while(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			// ask player if they want to roll
			System.out.println("Would you like to roll the dice? (y/n)");
			answer = scanner.nextLine();
			
			//if yes, then roll the dice
			if(answer.equalsIgnoreCase("y")) {
				//generate the random integer between our min and max
				roll = (int) ((Math.random() * (MAX_ROLL - MIN_ROLL)) + MIN_ROLL);
				System.out.println("\nYou have rolled a " + roll);
			}
			//if no, then quit the game
			else if(answer.equalsIgnoreCase("n")){
				System.out.println("Are you sure? (y/n) This will quit the game!");
				answer  = scanner.nextLine();
				if(answer.equalsIgnoreCase("y")) {
					roll = -1;
				}
				else {
					answer = "";
				}
				
			}
			//otherwise, ask them to repeat their answer with a valid input
			else {
				
				throw new InputMismatchException();
			}
		}
		
		return roll;
	}
	
		 

}
