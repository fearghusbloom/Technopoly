/**
 * 
 */
package technopoly;

import java.util.Scanner;

import technopoly.Field;
import technopoly.Player;
import technopoly.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

/**
 * @author fearg
 *
 */
public class Board {

	private ArrayList<Square> boardSquares;

	/**
	 * 
	 */
	public Board() {

	}

	/**
	 * constructor with args
	 * 
	 * @param boardSquares
	 */
	public Board(ArrayList<Square> boardSquares) {
		this.boardSquares = boardSquares;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Square> getBoardSquares() {
		return boardSquares;
	}

	/**
	 * 
	 * @param boardSquares
	 */
	public void setBoardSquares(ArrayList<Square> boardSquares) {
		this.boardSquares = boardSquares;
	}

	/**
	 * method to display all the board's squares and their details
	 */
	public void displayBoard() {
		for (Square square : boardSquares) {
			square.displayAll();
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean startGame1() throws InputMismatchException {
		boolean start = false;
		Scanner scanner = new Scanner(System.in);

		String answer = "";
		int i = 0;

		while (i == 0) {

			while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
				try {
					System.out.println("Would you like to begin a new game of Technopoly? (y/n)");
					answer = scanner.nextLine();

					if (answer.trim().equalsIgnoreCase("y")) {
						start = true;
						i += 1;
					} else if (answer.trim().equalsIgnoreCase("n")) {
						System.out.println("Maybe next time then.");
						i += 1;
					} else {
						throw new InputMismatchException();
					}
				} catch (InputMismatchException ex) {
					System.err.println("Please type y/n.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return start;
	}


	/**
	 * 
	 * @return
	 */
	public int numberOfPlayers() throws IllegalArgumentException {
		int numberOfPlayers = 0;

		Scanner scanner = new Scanner(System.in);
		int answer = 0;

		while (answer != 2 && answer != 3 && answer != 4) {
			System.out.println("How many players are taking part? (Enter number between 2 and 4)");
			try {
				answer = scanner.nextInt();
			}catch(InputMismatchException input) {
				System.err.println("not an integer value");
			}

			if (answer == 2 || answer == 3 || answer == 4) {
				numberOfPlayers = answer;
			} else {
				// System.out.println("Please enter an integer value between 2 and 4");
				throw new IllegalArgumentException("not between  2 and 4");
			}
		}
		return numberOfPlayers;
	}

	/**
	 * 
	 * @param numPlayers
	 * @return
	 */
	public String[] playerNames(int numPlayers) {
		String[] playerNames = new String[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			Scanner scanner1 = new Scanner(System.in);
			String attemptedName = "";

			System.out.println("What is the name of player" + (i + 1) + "?");
			attemptedName = scanner1.next();

			for (int k = i - 1; k >= 0; k--) {
				while (attemptedName.equalsIgnoreCase(playerNames[k])) {
					System.err.println(
							"I'm sorry but there is already a player with that name.\nPlease enter a new name:");
					attemptedName = scanner1.next();
				}
			}
			playerNames[i] = attemptedName;
		}
		return playerNames;
	}

	/**
	 * 
	 * @param player
	 * @param area
	 * @return
	 */
	public boolean sufficientDevelopersForArea(Player player, Square area) {
		boolean sufficientDevs = false;

		if (player.getDevelopers() > area.getAreaCost()) {
			System.out.println("You can afford this area.");
			sufficientDevs = true;
		} else {
			System.out.println("Sorry, you can't afford this area :-( ");
		}

		return sufficientDevs;
	}

	/**
	 * 
	 * @param player
	 * @param area
	 * @return
	 */
	public boolean sufficientDevelopersForProject(Player player, Square area) {
		boolean sufficientDevs = false;

		if (player.getDevelopers() >= area.getProjectCost()) {
			sufficientDevs = true;
		} else {
			System.out.println("Sorry, you can't afford to invest in a project in this area. ");
		}

		return sufficientDevs;
	}

	/**
	 * 
	 * @param player
	 * @param area
	 * @return
	 */
	public boolean sufficientDevelopersForProduct(Player player, Square area) {
		boolean sufficientDevs = false;

		if (player.getDevelopers() >= area.getProductCost()) {
			sufficientDevs = true;
		} else {
			System.out.println("Sorry, you can't afford to invest in a product in this area. ");
		}

		return sufficientDevs;
	}

	/**
	 * 
	 * @param players
	 * @return
	 */
	public boolean quitGame(Player[] players) {
		System.out.println("You have chosen to quit the game. Goodbye for now.");
		
		declareWinners(players);
		return true;
	}

	/**
	 * 
	 * @param endGame
	 * @param players
	 */
	public void declareWinners(Player[] players) {
		int[] playersRemainingDevelopers = new int[players.length];

		for (int i = 0; i < players.length; i++) {
			playersRemainingDevelopers[i] = players[i].getDevelopers();
		}

		Arrays.sort(playersRemainingDevelopers);

		System.out.println("\nThe remaining player(s) final developer pools are as follows:");
		for (Player player : players) {
			
			if (player.getDevelopers() > 0) {
				System.out.println(player.getPlayerName() + ": " + player.getDevelopers());
			}
		}
		
		for(Player player : players) {
			if (player.getDevelopers() == playersRemainingDevelopers[players.length - 1]) {
				System.out.println("\n" + player.getPlayerName() + " is the winner!! wooooooooooo");
			}
		}
		
	}
}
