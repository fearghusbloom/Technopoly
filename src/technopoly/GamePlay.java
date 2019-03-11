/**
 * 
 */
package technopoly;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * @author fearg
 *
 */
public class GamePlay {

	//static variables to hold constants for game setup
	public static int START_DEVELOPERS = 60;
	public static int START_POSITION = 1;
	public static int START_OWNER = 0;
	public static int START_PROJECTS = 0;
	public static boolean START_PRODUCT = false;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//declare and initialize field objects
		Field f1 = new Field(Fields.WEBDEVELOPMENT.toString(), START_OWNER);
		Field f2 = new Field(Fields.COMPUTERGAMEDEVELOPMENT.toString(), START_OWNER);
		Field f3 = new Field(Fields.ROBOTICS.toString(), START_OWNER);
		Field f4 = new Field(Fields.CYBERSECURITY.toString(), START_OWNER);
		
		//add the field objects to an arraylist of type field
		ArrayList<Field> gameFields = new ArrayList<Field>();
		gameFields.add(f1);
		gameFields.add(f2);
		gameFields.add(f3);
		gameFields.add(f4);
		
		//declare and initialize square objects
		Square s1 = new Square(1, "COMPILE AND RUN TIME");
		Square s2 = new Area(2, Areas.HTML.toString(), f1, 6, 5, 20, START_OWNER, START_PROJECTS, START_PRODUCT, 1, 2, 3, 9, 25);
		Square s3 = new Area(3, Areas.CSS.toString(), f1, 6, 6, 24, START_OWNER, START_PROJECTS, START_PRODUCT, 2, 3, 5, 12, 30);
		Square s4 = new Area(4, Areas.GAMELOGIC.toString(), f2, 14, 10, 40, START_OWNER, START_PROJECTS, START_PRODUCT, 8, 12, 20, 30, 40);
		Square s5 = new Area(5, Areas.GRAPHICS.toString(), f2, 14, 10, 40, START_OWNER, START_PROJECTS, START_PRODUCT, 8, 14, 24, 34, 45);
		Square s6 = new Area(6, Areas.THREEDDESIGN.toString(), f2, 16, 12, 48, START_OWNER, START_PROJECTS, START_PRODUCT, 10, 16, 26, 36, 50);
		Square s7 = new Square(7, "FREE WIFI");
		Square s8 = new Area(8, Areas.MOBILITY.toString(), f3, 26, 15, 60, START_OWNER, START_PROJECTS, START_PRODUCT, 14, 24, 48, 72, 90);
		Square s9 = new Area(9, Areas.SENSORS.toString(), f3, 26, 16, 64, START_OWNER, START_PROJECTS, START_PRODUCT, 14, 26, 52, 75, 95);
		Square s10 = new Area(10, Areas.MANIPULATION.toString(), f3, 28, 18, 72, START_OWNER, START_PROJECTS, START_PRODUCT, 16, 28, 56, 90, 110);
		Square s11 = new Area(11, Areas.RISKANALYTICS.toString(), f4, 40, 22, 90, START_OWNER, START_PROJECTS, START_PRODUCT, 28, 40, 80, 130, 160);
		Square s12 = new Area(12, Areas.BACKUPDATAANDRECOVERY.toString(), f4, 45, 25, 100, START_OWNER, START_PROJECTS, START_PRODUCT, 30, 45, 90, 155, 200);
		
		//create a square array with the game's squares
		ArrayList<Square> gameSquares = new ArrayList<Square>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12));
		
		//declare board object and instantiate it
		Board gameBoard = new Board(gameSquares);
		
		//display the board 
		gameBoard.displayBoard();
		
		//begin calling game-play methods
		boolean playGame;
		playGame = gameBoard.startGame1();
		
		
		//methods will be called only if the game has been started
		if(playGame == true) {
			
			int i = 0;
			int numOfPlayers = 0;
			
			//repeat the numberOfPlayers method while invalid number is being entered
			while(i == 0) {
				try {
					//get the number of players taking part
					numOfPlayers = gameBoard.numberOfPlayers();

					//cancel the while loop if the try has succeeded
					i += 1;
				}
				catch(IllegalArgumentException ill) {
					System.err.println("The number of players entered was not between 2 and 4.");
				}
			}
				
					//get the players names
					String[] players = gameBoard.playerNames(numOfPlayers);
					
					//create the player object and store them in a player array
					Player[] playersInGame = gamePlayers(players, numOfPlayers);
					
					//check details
					displayPlayerDetails(playersInGame, gameSquares);
					
					//default value for endgame is false, so that the game will begin
					boolean endGame = false;
					
					//while the game is not over, call all play methods
					while(endGame == false) {
						
						//integer variable to keep track of whose turn it is
						int playerTracker = 0;
						
						//while loop to iterate through the players and move onto the next player when another player has finished their go
						while(playerTracker < numOfPlayers) {
							
							//tell the player it is their turn
							playersInGame[playerTracker].declareTurn();
							
							//tell the player that they own a field, and ask whether they would 
							//like to develop it. only gets called if they own a field
							playersInGame[playerTracker].askToDevelop(gameSquares, gameBoard);
							
							//instantiate the dice and generate a roll
							Dice d = new Dice();
							try {
								int move = d.generateRoll();
								
								//if move is -1 it means the player has chosen to quit the game
								//so we will have to set endGame to true and also break the for loop
								// will also have to increment i to break the while loop
								if(move == -1) {
									playersInGame[playerTracker].setDevelopers(0);
									endGame = gameBoard.quitGame(playersInGame);
									i += 1;
									break;
								}
							 
								//move forward the number of squares defined by the roll
								playersInGame[playerTracker].movePosition(move, gameSquares);
							}
							catch (InputMismatchException ex) {
								System.err.println("Please enter y or n");
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								continue;
							}
							
							//record the new position so it can be used
							int newPosition = playersInGame[playerTracker].getpositionOnBoard();
						
							//only carry out rest of the move's methods if player is not on Compile & Run Time or Free Wifi
							if(newPosition != 1 && newPosition != 7 ) {
								
								//check if the area landed on is owned or not
								boolean ownedArea = gameBoard.getBoardSquares().get(newPosition - 1).checkIfOwned(playersInGame[playerTracker]);
								
								//if area is unowned then ask player if they want to invest
								//otherwise, they are going to have to give developers to another player
								if(ownedArea == false) {
									//check the player has sufficient developers first
									boolean canInvest = gameBoard.sufficientDevelopersForArea(playersInGame[playerTracker], gameBoard.getBoardSquares().get(newPosition - 1));
									
									//if true then they have the option to invest
									if(canInvest == true) {
										//ask the player to invest and record the answer in invested
										boolean invested = playersInGame[playerTracker].investInArea(gameSquares);
										
										//if player chooses to invest then the setOwnerID method must be called
										if(invested == true) {
											gameBoard.getBoardSquares().get(newPosition - 1).setOwnerID(playerTracker + 1);
										}
									}
									
								}
								//if the area is already owned, current player will have developers subtracted, owner will have developers added
								//the subtractDevelopers method could call the goBankrupt method, which should then end the game by
								//updating the value of the endGame boolean
								else if(playersInGame[playerTracker].getPlayerID() != gameBoard.getBoardSquares().get(newPosition - 1).getOwnerID()){
									
									//check how much is owed
									int amountOwed = gameBoard.getBoardSquares().get(newPosition - 1).getTotalDeveloperFee();
									//check the ID of the owner
									int ownerID = gameBoard.getBoardSquares().get(newPosition - 1).getOwnerID();
									
									//tell the player who the owner of the area is
									System.out.println(playersInGame[ownerID - 1].getPlayerName() + " owns this area");
									
									//call the subtract and add developer methods
									endGame = playersInGame[playerTracker].subtractDevelopers(amountOwed);
									playersInGame[ownerID - 1].addDevelopers(amountOwed);
									
									//if endGame is called 
									if(endGame == true) {
										gameBoard.declareWinners(playersInGame);
										i += 1;
										break;
									}
									else {
										//tell the player who he has just paid, and how much
										System.out.println("\nYou have just paid " + playersInGame[ownerID - 1].getPlayerName() + " " + amountOwed + " developers");
									}
								}
								
								//try to update the owners of all the fields at the end of every go
								try {
									updateFieldsOwnedList(gameFields, playersInGame, playerTracker, gameBoard);
								}
								catch(IllegalArgumentException illArg1){
									System.out.println("field update threw error");
								}
								
								//update the board with the new field owners
								//declare board object again and instantiate it with the new field owners
								gameBoard = new Board(gameSquares);
							}
							
							//before the updated details are displayed, we want to add a thread
							//so that the player can see the game's messages about the conclusion of their go
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							//display the board's details
							gameBoard.displayBoard();
							
							//display the details of all the players
							displayPlayerDetails(playersInGame, gameSquares);
				
							//increment playerTracker variable so that the next player's move begins
							playerTracker++;
							
							//if the last player in the game has just had their move then reset playerTracker 
							//to 0 so that the loop goes back to the first player
							if(playerTracker == numOfPlayers) {
								playerTracker = 0;
							}
						}
					}
				
			}
		}
		
	
	
	/**
	 * method to setup the game's players and store them in an array of type player
	 * @param playerNames
	 * @param numberOfPlayers
	 * @return
	 */
	public static Player[] gamePlayers(String[] playerNames, int numberOfPlayers) {
		Player[] playerArray = new Player[numberOfPlayers];
		
		for(int i = 0; i < numberOfPlayers; i++) {
			ArrayList<Field> fields = new ArrayList<Field>();
			ArrayList<Square> areas = new ArrayList<Square>();
			
			playerArray[i] = new Player(i + 1, playerNames[i], START_DEVELOPERS, START_POSITION, fields, areas);
		}
		
		return playerArray;
	}
	
	/**
	 * 
	 * @param gameFields
	 * @param playersInGame
	 * @param playerTracker
	 * @param gameBoard
	 */
	public static void updateFieldsOwnedList(ArrayList<Field> gameFields, Player[] playersInGame, int playerTracker, Board gameBoard) {
		for(Field field : gameFields) {
			field.updateFieldsOwnerID(gameBoard.getBoardSquares());
			
			//add any new field ownerships to the players' fieldsOwned array lists
			if(field.getOnwerID() == playersInGame[playerTracker].getPlayerID()) {
				
				//check that field hasn't already been added
				int alreadyOwned = 0;
				for(Field playersField : playersInGame[playerTracker].getFieldsOwned()) {
					if(playersField.getName().equalsIgnoreCase(field.getName())) {
						//if field already added set alreadyOwned = 1
						alreadyOwned += 1;
					}
				}
				//if already owned is still 0 then the field wasn't in the players field list
				if(alreadyOwned == 0) {
					playersInGame[playerTracker].addField(field);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param players
	 * @param gameSquares
	 */
	public static void displayPlayerDetails(Player[] players, ArrayList<Square> gameSquares) {
		for(Player player : players) {
			player.displayDetails(gameSquares);
		}
	}

}
