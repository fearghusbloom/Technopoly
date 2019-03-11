package technopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	// rule constants
	public final static int BOARD_POSITION_MIN = 1;
	public final static int BOARD_POSITION_MAX = 12;
	public final static int ID_MINIMUM = 1;
	public final static int ID_MAXIMUM = 4;

	// declaration of instance variables
	private int playerID;
	private String playerName;
	private int developers;
	private int positionOnBoard;
	private ArrayList<Field> fieldsOwned;
	private ArrayList<Square> areasOwned;

	/**
	 * default constructor
	 */
	public Player() {

	}

	/**
	 * constructor with all arguments business rule for position on board is set
	 * 
	 * @param playerID
	 * @param playerName
	 * @param developers
	 * @param positionOnBoard
	 */
	public Player(int playerID, String playerName, int developers, int positionOnBoard, ArrayList<Field> fieldsOwned,
			ArrayList<Square> areasOwned) {
		this.setPlayerID(playerID);
		this.playerName = playerName;
		this.developers = developers;
		this.setpositionOnBoard(positionOnBoard);
		this.fieldsOwned = fieldsOwned;
		this.areasOwned = areasOwned;
	}

	/**
	 * 
	 * @return
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * 
	 * @param playerID
	 * @throws IllegalArgumentException
	 */
	public void setPlayerID(int playerID) throws IllegalArgumentException {
		if(playerID >= ID_MINIMUM && playerID <= ID_MAXIMUM) {
			this.playerID = playerID;
		}
		else {
			throw new IllegalArgumentException("player id must be between " + ID_MINIMUM + " and " + ID_MAXIMUM);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * 
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 
	 * @return
	 */
	public int getDevelopers() {
		return developers;
	}

	/**
	 * 
	 * @param developers
	 */
	public void setDevelopers(int developers) {
		this.developers = developers;
	}

	/**
	 * 
	 * @return
	 */
	public int getpositionOnBoard() {
		return positionOnBoard;
	}

	/**
	 * 
	 * @param positionOnBoard
	 * @throws IllegalArgumentException
	 */
	public void setpositionOnBoard(int positionOnBoard) throws IllegalArgumentException {
		if (positionOnBoard >= BOARD_POSITION_MIN && positionOnBoard <= BOARD_POSITION_MAX) {
			this.positionOnBoard = positionOnBoard;
		} else {
			throw new IllegalArgumentException("board position must be between defined min and max");
		}
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Field> getFieldsOwned() {
		return fieldsOwned;
	}

	/**
	 * 
	 * @param fieldsOwned
	 */
	public void setFieldsOwned(ArrayList<Field> fieldsOwned) {
		this.fieldsOwned = fieldsOwned;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Square> getAreasOwned() {
		return areasOwned;
	}

	/**
	 * 
	 * @param areasOwned
	 */
	public void setAreasOwned(ArrayList<Square> areasOwned) {
		this.areasOwned = areasOwned;
	}

	/**
	 * 
	 * @param squares
	 */
	public void displayDetails(ArrayList<Square> squares) {
		System.out.println("====================================");
		System.out.println("Player ID: " + this.playerID);
		System.out.println("Player Name: " + this.playerName);
		System.out.println("\nDevelopers Owned: " + this.developers);
		System.out.println("\nPosition on Board: " + squares.get(this.positionOnBoard - 1).getName());
		System.out.println("Areas owned: ");
		for(Square area : this.areasOwned) {	
			System.out.println(area.getName());
		}
		System.out.println("Fields owned: " );
		for(Field field : this.fieldsOwned) {
			System.out.println(field.getName());
		}
		System.out.println("====================================");
	}

	/**
	 * declareTurn tells the current player that it is their go.
	 */
	public void declareTurn() {
		System.out.println(this.playerName + ", it is your turn.");
	}

	/**
	 * movePosition takes the dice's most recent value as a parameter and moves the current player forward
	 * that number of places on the board. If the player passes Compile and Run Time then passCompileAndRunTime
	 * is called.
	 * @param diceValue
	 * @param squares
	 * @return
	 */
	public int movePosition(int diceValue, ArrayList<Square> squares) {
		if (this.positionOnBoard + diceValue > 12) {
			this.positionOnBoard = (this.positionOnBoard + diceValue) - BOARD_POSITION_MAX;
			// and pass compile and run time
			if(this.positionOnBoard > 1) {
				passCompileAndRunTime();
			}
			
		} else {
			this.positionOnBoard += diceValue;
		}

		System.out.println("\nYou have landed on " + squares.get(this.positionOnBoard - 1).getName());
		return this.positionOnBoard;
	}

	/**
	 * passCompileAndRunTime adds 20 developers to the current player's resources. 
	 */
	public void passCompileAndRunTime() {
		this.developers += 20;
		System.out.println("You have passed Compile and Run Time and collected another 20 developers.");
	}

	/**
	 * investInArea checks whether the current area is unowned. If it is, the player is asked whether they want
	 * to invest in the area. If they answer y, they are prompted again, and if yes is once again the answer, 
	 * then they invest in the area and the amount it costs is subtracted from their pool of developers.
	 * @param squares
	 * @return
	 */
	public boolean investInArea(ArrayList<Square> squares) {

		boolean invested = false;

		if (this.positionOnBoard != 1 && this.positionOnBoard != 7) {
			Scanner scanner = new Scanner(System.in);

			String answer = "";

			System.out.println(
					"\nWould you like to invest in " + squares.get(this.positionOnBoard - 1).getName() + "? (y/n)");

			while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {

				answer = scanner.nextLine();

				if (answer.equalsIgnoreCase("y")) {
					int validAnswerCheck = 0;
					
					while(validAnswerCheck == 0) {
						
						System.out.println("Are you sure? (y/n)");
						String answer1 = scanner.nextLine();
						
						if (answer1.trim().equalsIgnoreCase("y")) {
							int amount = squares.get(this.positionOnBoard - 1).getAreaCost();

							// call the subtract developers method with endGame as false
							subtractDevelopers(amount);

							//tell the player how much they have just spent
							System.out.println("You have just invested " + amount + " developers, and have " + this.getDevelopers() + " developers left.");
							
							// add area to player's portfolio by calling the addArea method
							this.addArea(squares.get(this.positionOnBoard - 1));

							invested = true;
							validAnswerCheck += 1;
						} 
						else if (answer1.trim().equalsIgnoreCase("n")) {
							investInArea(squares);
							validAnswerCheck += 1;
						} 
						else {
							System.err.println("Please type y/n.");
						}
					}
					

				} 
				else if (answer.trim().equalsIgnoreCase("n")) {
					System.out.println("You have chosen not to invest in the area.");
	
				} 
				else {
					System.err.println("Please type y/n.");
					investInArea(squares);
				}
			}
		}
		return invested;
	}

	/**
	 * addDevelopers adds the specified amount of developers to the player's resources.
	 * @param amount
	 */
	public void addDevelopers(int amount) {
		this.developers += amount;
	}

	/**
	 * subtractDevelopers removes the specified amount of developers from the player's resources.
	 * If their pool of developers reaches 0 or below then the goBankrupt method is called. 
	 * @param amount
	 */
	public boolean subtractDevelopers(int amount) {
		this.developers -= amount;
		boolean bankrupt = false;

		if (this.developers <= 0) {
			System.out.println("\nOh no! You've lost all your developers! You lose! Loser!\n");
			bankrupt = goBankrupt();
		}
		return bankrupt;
	}

	/**
	 * goBankrupt method sets endGame variable to true and prints a message to the screen.
	 * @param endGame
	 * @return
	 */
	public boolean goBankrupt() {
		System.out.println("The game has finished because a player is bankrupt.");
		return true;
	}

	/**
	 * addArea adds an area to the player's area portfolio
	 * @param area
	 */
	public void addArea(Square area) {
		this.areasOwned.add(area);
	}
	
	/**
	 * addField adds a field to the player's field portfolio
	 * @param field
	 */
	public void addField(Field field) {
		this.fieldsOwned.add(field);
	}

	/**
	 * askToDevelop checks whether the current player owns any fields.
	 * If they do, the fields they own are listed and the player is asked which of them, if any,
	 * they would like to develop. Once they have chosen their field, they are shown the areas in this field
	 * and the level of development currently present in each of the areas. They are then asked to specify which
	 * area they would like to develop. Their answer is recorded and passed as an argument to the develop()
	 * method below.
	 * 
	 * @param gameSquares
	 * @param gameBoard
	 */
	public void askToDevelop(ArrayList<Square> gameSquares, Board gameBoard) {
		if (this.fieldsOwned.isEmpty() == false) {
			System.out.println("You own the following fields: ");
			for (Field field : this.fieldsOwned) {
				System.out.println(field.getName());
			}
			Scanner scanner = new Scanner(System.in);
			String answer = "";
			int check = 0;

			while (check == 0) {
				System.out.println(
						"Would you like to develop in one of these fields? If yes, type the name of the field. If no, type n.");
				answer = scanner.nextLine();

				if(!answer.trim().equalsIgnoreCase("n")) {
					for (Field field : this.fieldsOwned) {
						if (answer.trim().equalsIgnoreCase(field.getName())) {
							System.out.println("The areas in this field and their current development status are:");

							for (Square area : this.areasOwned) {
								if (area.getField().getName().equalsIgnoreCase(field.getName())) {
									String product = "no";
									if (area.isHasProduct() == true) {
										product = "one";
									}
									System.out.println(area.getName() + ", " + area.getProjectsInvestedInArea()
											+ " projects, " + product + " product.");
								}
							}
							System.out.println(
									"Would you like to develop in one of these areas? If yes, type the name of the area. If no, type n");
							answer = scanner.nextLine();
							
							Square chosenArea = new Square();
							//get the area
							for(Square area : gameSquares) {
								if(answer.trim().equalsIgnoreCase(area.getName())) {
									chosenArea = area;
								}
							}

							if (!answer.equalsIgnoreCase("n")) {
								String answer1 = "";
								System.out.println("Are you sure? (y/n) A project on this area will cost you " + chosenArea.getProjectCost() + " developers, and a product will cost " + chosenArea.getProductCost() + " developers.");
								answer1 = scanner.nextLine();

								//if yes, call develop method and break the while loop
								if (answer1.trim().equalsIgnoreCase("y")) {
									develop(answer, gameBoard);
									check += 1;
								} 
								//if no, ask them again whether they would like to developer an a field
								else if (answer1.trim().equalsIgnoreCase("n")) {
									check = 0;
									System.out.println("Ok, you don't want to develop " + answer);
								}
							}
						}
					}
				}
				else if(answer.trim().equalsIgnoreCase("n")) {
						System.out.println("Ok, so you've chosen not to develop any of your fields.");
						check += 1;
				}
			}		
		}
	}
	

	/**
	 * if this method is called from the above method, it means the player has specified an area of theirs
	 * that they wish to develop. The area will be checked for pre-existing projects and products. If there are
	 * less than 3 projects developed on the area then another project will be developed, if the player has 
	 * sufficient developers. If there are already three projects present then a product will be developed, again
	 * providing that the player has sufficient developers available.
	 * @param areaName
	 * @param gameBoard
	 */
	public void develop(String areaName, Board gameBoard) {
		for (Square area : this.areasOwned) {

			if (areaName.equalsIgnoreCase(area.getName())) {

				// if less than 3 projects set project + 1
				if (area.getProjectsInvestedInArea() < 3) {
					
					boolean affordProject = gameBoard.sufficientDevelopersForProject(this, area);
					
					if(affordProject == true) {
						area.setProjectsInvestedInArea(area.getProjectsInvestedInArea() + 1);
						subtractDevelopers(area.getProjectCost());
						System.out.println("You have developed a new project on " + area.getName());
						System.out.println("You have invested " + area.getProjectCost() + " developers in this project, and have " + this.getDevelopers() + " developers left");
					
					}
				}

				// if 3 projects then set product
				else if (area.getProjectsInvestedInArea() == 3) {
					
					boolean affordProduct = gameBoard.sufficientDevelopersForProduct(this, area);
					
					if(affordProduct == true) {
						area.setHasProduct(true);
						subtractDevelopers(area.getProductCost());
						System.out.println("You have developed a product on " + area.getName());
						System.out.println("You have invested " + area.getProductCost() + " developers in this product, and have " + this.getDevelopers() + " developers left");
					
					}
				}
			}

		}
	}
}
