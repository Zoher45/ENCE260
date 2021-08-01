package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
* The CommanLine class is where the game can be played in the console
* It was constructed for the purpose of testing the functionality for the game.
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class CommandLine {
	
	/**
	 * The used for user input
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * The instance of the GameEnvironment
	 */
	private GameEnvironment gameState;
	
	/**
	 * The constructor for the command line
	 */
	public CommandLine() {
		
		//Store  information in appropriate locations.
		//Construct the GameEnvironment
		
		String getName = askName();//Ask the user the name 
		getWelcome();
		int getDays = askDaysRemaining();//Ask the user the days 
		int getShip = askForShip();; // Ask the user the ship
		gameState = new GameEnvironment(getName, getDays, getShip);
		
	}
	
	/**
	 * Welcome screen, prints the information about the game
	 */
	public void getWelcome() {
		
		System.out.println("The main goal of this game is to make as much profit as you can!\n");
		System.out.println("There are five Islands, each with unique advantages and disadvantages");
		System.out.println("You can travel to each island but there is a chance of an event to occur!\n");
		System.out.println("When you want to buy/sell items or to travel, it will have a number next to it.");
		System.out.println("For Example- 'Gold Bar' [1] the 1 indicates to enter 1 to purchase this item.\n");
		System.out.println("As you progress through the game make sure to upgrade to help you survive!");
		System.out.println("While traveling or buying keep an eye on your maintenance cost and the money you have remaining");
		System.out.println("If you don't have enough days left or enough money to travel, the game will end\n");
		System.out.println("Good luck on your journey! \n");
		
	}
	
	/**
	 * Runs the game and class the necessary methods to keep the logic progressing
	 */
	public void startGame() {
		
		//keep the game moving until player cannot play anymore
		
		while (gameState.getPlayer().getDaysRemaining() > 0 && gameState.getStatus() == 100) {
			
			int gottenMenuItem = getIslandMenu();
			
			gameState.endClauseCheck();
			
			if (gottenMenuItem == 1) {
				
				//get the island shop
				System.out.println("Welcome to " + gameState.getIsland().getIslandName() + " shop");
				
				getShopMenu(true);
					
			}
			
			else if (gottenMenuItem == 2) {
				
				// get the map
				getMapDetails();
				
			}
			
		}
		
		int profit = gameState.getPlayer().getMoney() - gameState.getStartingMoney();
		
		int score = (int) (profit) / (gameState.getPlayer().getDaysChosen() - gameState.getPlayer().getDaysRemaining());
		
		if(score < 0) {
			
			score = 0;
			
		}
		
		//Game over screen
		System.out.println("\n==============================GAMEOVER=================================");
		System.out.println(gameState.getEndMessage());
		System.out.println("Player - " + gameState.getPlayer().getName());
		System.out.println("Days selected: " + gameState.getPlayer().getDaysChosen());
		System.out.println("Total day survived:  " + (gameState.getPlayer().getDaysChosen() - gameState.getPlayer().getDaysRemaining()));
		System.out.println("Profit made $" + profit);
		System.out.println("Player's Ship: " + gameState.getShip().getShipName());
		System.out.println("Score : " + score);
		System.out.println("Thanks for playing!");
		
	}
	
	/**
	 * Ask user for their name making sure that the length is in hte boundary
	 * Check if the name only contains letters
	 */
	public String askName() {
		
		String name;
		
		while(true) {
			
			System.out.println("Enter a player name");
			
			String userName = scan.nextLine(); 
			
			boolean found = true;
			
			try {
				
				if (userName.length() < 3  | userName.length() > 15) {
					
					found = false;
					
					throw new LengthException("Please make sure to have have username between 3 - 15 characters");	
					
				}
	
				for(char c : userName.toCharArray()) {
					
					if (!Character.isLetter(c)) {
						
						found = false;
						
						throw new CharacterException("Please only use letters for the username");
						
					}
					
				}
				
					
			}
			catch(LengthException e) {
				
				System.out.println(e.getMessage() + "\n");
				
				
			}
			catch(CharacterException e) {
				
				System.out.println(e.getMessage() + "\n");
				
			}
			finally{
				
				if (found == true) {
					
					name = userName;
					
					System.out.println();
					System.out.println("Welcome " + userName + "\n");
					
					break;
				}
			}
		}
		return name;
	}
	
	/**
	 *Ask for the days the player wants to player for
	 */
	
	public int askDaysRemaining(){
		
		System.out.println("Enter the days you would like to play for (20-50 days) ");
		
		int returnedValue = inputChecker(20, 50);
		
		System.out.println("You have chosen " + returnedValue + " days" + "\n");
		
		return returnedValue;
		
	}
	
	
	/**
	 *Ask for what ship the player wants to use
	 */
	public int askForShip() {
		
		getShips();
		
		System.out.println("All ships have unique advantages and disadvantages so pick carefully!");
		System.out.println("Enter the ship number associated with the ship name");
		
		int playerSelection = inputChecker(1, 4);
		
		return playerSelection;
		
	}
	
	/**
	 *Ask for what the player wants to do next
	 */
	 public int askNextAction() {
		 
		 	getMenu();
		 	
		 	int gottenValue = inputChecker(1, 6);
		 	
		 	return gottenValue;

	 }
	
	 /**
	  *Used to find the amount the player will buy for some item
	  * 
	  * @param gotMax the max amount of stock that item can be bought for
	  *
	  * @return the amount the player selected
	  */
	public int askForItem(int gotMax) {
		System.out.println();
		System.out.println("To purchase or to sell, enter the number corresponding to the item name");
		System.out.println("Enter 0 to exit to main menu");
		
	 	int gottenValue = inputChecker(0, gotMax);
	 	
	 	return gottenValue;
	 	
	}
	
	 /**
	  *Used to check if what the player has chosen is in the boundary
	  * 
	  * @param minBoundary the minimum of the boundary
	  * @param maxBoundary the maximum of the boundary
	  *
	  * @return in of what the player has bought
	  */
	public int inputChecker(int minBoundary, int maxBoundary) {
	
		boolean found = false;
		
		int inputNumber = 0;
		
		while (found != true) {
			
			//Ask the user for a input 
			String userInput = scan.nextLine();
			
			try {
				
				//try to turn input into integer
				inputNumber = Integer.parseInt(userInput);
				
				if (inputNumber >= minBoundary && inputNumber <= maxBoundary) {
					
					//used to end the while loop
					found = true;
					
				}
				else {
					
					//throw error as input not in bound
					throw new InvalidInputException("Please choose a between " + minBoundary +" to " + maxBoundary);		
				}
				
			}
			
			catch (InvalidInputException e) {
				
					System.out.println(e.getMessage() + "\n");
				
			}
			
			catch (Exception e) {
				
				System.out.println("Please enter a valid number" + "\n");
				
			}
			finally {
				
				if (found == true) {
					
					//check if found is true
					//if true break while loop
					break;
				}
				
			}
			
		}
		
		return inputNumber;
		
	}
	
	/**
	 *This method what is used to control the main menu
	 *
	 *@return returns int to call the next action player can take
	 *
	 */
	public int getIslandMenu() {
		
		int returnedValue = askNextAction();
		
		int choosenMenuItem = 0;
			
		if(returnedValue == 1) {
			
			// get the island shop
			choosenMenuItem = 1;
			
		}
		
		else if (returnedValue == 2) {
			
			//get the map
			choosenMenuItem = 2;
				
		}
		
		else if(returnedValue == 3){
			
			//get the ship data
			getCurrentShipData();
			
		}
		
		else if(returnedValue == 4) {
			//player stats
			getplayerStats();
			
			
				
		}
		else if(returnedValue == 5) {
			
			//get current inventory
			System.out.println();
			getShipInventory();
			
		}
		
		else {
			
			// display Island properties
			for (String i : gameState.getIsland().getIslandProperties()) {
				
				System.out.println(i + " \n");
				
			}
			
			System.out.println();
			
			
		}
		
		return choosenMenuItem;
		
	}
	
	/**
	 *Gets the shop menu which displays what the user can buy and sell
	 *
	 *@param shopBuy boolean to check if this is the first time the shop is opened
	 *
	 */
	public void getShopMenu(boolean shopBuy) {
		
		System.out.println(gameState.getIsland().getIslandName() + " shop");
		System.out.println("Current money $ " + gameState.getPlayer().getMoney());
		System.out.println("Current storage remaining " + gameState.getShip().getStorageRemaining() + " units");
		
		//The current options available (initially 5 options as player can only buy 5 items)
		int gottenMax = 5;
		
		getAllItems();
		
		//If shopBuy is true then display what the shop is currently buying
		if (shopBuy == true) {
		
			getBuyItems();
			gameState.setPlayerItems();
			
		}
		
		//If inventory isn't empty then gottenMax is changed max options the player can do
		if(gameState.getPlayerCanSell().isEmpty() == false){
			
			gottenMax = getPlayerSell();
			
		}
		
		//ask for which item to buy or sell
		int returnedValue = askForItem(gottenMax);
	
		//if player entered 0 go to main screen
		if (returnedValue == 0 ){
			
			gameState.checkForEmpty();
			
			System.out.println("Thanks for visitng the " + gameState.getIsland().getIslandName() + " shop");
			System.out.println();
			
		}
		
		else {
			
			//Ask player how much to buy or sell (according to stock)
			askStockAmount(returnedValue);
			
			getShopMenu(false);
			
		}
		
	}
	
	/**
	 *This method is used to ask the amount of stock the player wants to buy
	 *
	 *@param itemNumber what item the player has selected
	 *
	 */
	public void askStockAmount(int itemNumber){
		
		//If itemNumber <= 5 then the player has chosen to buy item from the store
		if (itemNumber <= 5) {
			
			int stock = gameState.getItem(itemNumber).getItemMaxBuy();
			
			String name = gameState.getItem(itemNumber).getItemName();
			

			if (stock == 0) {
				
				System.out.println("Sorry but item: " + name + " is currently of Stock" );
				System.out.println("=========================================================================");
			}
			
			else {
				
				System.out.println("You have choosen: " + name);
				System.out.println("Enter purchase amount (current stock " + stock + ")");
				
				//get player input and make sure the input is in the boundary
				int gottenValue = inputChecker(0, stock);
				
				if(gottenValue > 0) {
					
					String message;
					
					if(gameState.getIsland().getIslandName().equals("Serpent Island")) {
						
						message = gameState.setUpgradeOrder(itemNumber, gottenValue);
						
					}
					
					else {
						
						message = gameState.setPurchaseOrder(itemNumber, gottenValue);
						
					}
					
				 	System.out.println(message);
				 	System.out.println("=========================================================================");
				 	
			 	}
				
			}
			
		}
		
		//The player wants to sell an item
		else {
			
			List <Object> currentPlayerSellItem = gameState.getSpecificStorageItem(itemNumber);
			
			int stock = (int) currentPlayerSellItem.get(2) / (int) currentPlayerSellItem.get(5);
			
			String name = (String) currentPlayerSellItem.get(0);
			
			float percentage = (float) currentPlayerSellItem.get(1)  * (float) (gameState.getIsland().getIslandPercentage()) / 100;
			
			//what the island will buy the item for per unit
			int sellPrice = (int) percentage + (int)(float) currentPlayerSellItem.get(1);
			
			if (stock == 0){
				
				System.out.println("Sorry you can not sell item: " + name + " beacuse there is no stock remaining " );
				System.out.println("=========================================================================");
				
			}
			
			else {
				
				System.out.println();
				System.out.println("You have Choosen to sell item: " + name);
				System.out.println("The shop is prepared to buy it for $" + sellPrice  + " per item");
				System.out.println("Enter amount you would like to sell (current stock " + stock + ")");
				
				int gottenValue = inputChecker(0, stock);
				
				gameState.setSellOrder(itemNumber, gottenValue, sellPrice);
				
				System.out.println("Order Complete!");
			 	System.out.println("=========================================================================");
			 	
			}
			
		}

	}
	
	/**
	 *Gets the main menu
	 */
	public void getMenu (){
		
		System.out.println("Current options:");
		System.out.println(" - Enter 1 to go to the Island shop");
		System.out.println(" - Enter 2 to see map");
		System.out.println(" - Enter 3 to see current ship details");
		System.out.println(" - Enter 4 to see player status");
		System.out.println(" - Enter 5 to see current inventory");
		System.out.println(" - Enter 6 to see the unique properties of this Island");
		System.out.println();
		
	}
	
	/**
	 *Gets the ship the user can choose from
	 */
	public void getShips() {
		
		System.out.println("This is the Black Pearl [1]");
		System.out.println(" -Crew members: 17 (max amount is 24)");
		System.out.println(" -Storage: 200 units");
		System.out.println(" -Speed factor: 18 knots");
		System.out.println(" -Starting defense is 45% of ships capabilities");
		System.out.println();
		
		System.out.println("This is the Flying Nimbus [2]");
		System.out.println(" -Crew members: 9 (max amount is 15)");
		System.out.println(" -Storage: 120 units");
		System.out.println(" -Speed factor: 22 knots");
		System.out.println(" -Starting defense is 50% of ships capabilities");
		System.out.println();
		
		System.out.println("This is the Miss Fortune [3]");
		System.out.println(" -Crew members: 20 (max amount is 28)");
		System.out.println(" -Storage: 260 units");
		System.out.println(" -Speed factor: 15 knots");
		System.out.println(" -Starting defense is 41% of ships capabilities");
		System.out.println();
		
		System.out.println("This is the Jolly Roger [4]");
		System.out.println(" -Crew members: 27 (max amount is 35)");
		System.out.println(" -Storage: 340 units");
		System.out.println(" -Speed factor: 10 knots");
		System.out.println(" -Starting defense is 38% of ships capabilities");
		System.out.println();
		
	}
	
	/**
	 *gets the current ship data
	 */
	public void getCurrentShipData() {
		
		System.out.println("Current Ship Details:");
		System.out.println(" -Ship name: " + gameState.getShip().getShipName());
		System.out.println(" -Crew members: " + gameState.getShip().getShipCrew() + " (max amount is " + gameState.getShip().getMaxCrew()+ ")");
		System.out.println(" -Maintenance cost per day (Cost required to travel per day): $" + gameState.getShip().getMaintenanceCost());
		System.out.println(" -Storage: " + gameState.getShip().getStorageRemaining() + " units");
		System.out.println(" -Speed factor: " + gameState.getShip().getShipSpeed() + " Knots");
		System.out.println(" -Defense is " + String.format("%.2f", gameState.getShip().getShipDefence()) + "% of ships capabilities");
		System.out.println(" -Ship endurance: " + gameState.getShip().getShipEndurance() + "%");
		System.out.println(" -Current Upgrades - \n ");
		
		//If there is upgrades on the ship the then display it
		if(gameState.getShip().getUpgrades().size() > 0) {
			
			int total = 0;
			
			int storage = 0;
			
			for (int i = 0; i < gameState.getShip().getUpgrades().size();i++) {
				
				List<Object> each = gameState.getShip().getUpgrades().get(i);
	
				if (each.get(0) != "Crew member") {
					
					System.out.println(" -Item:  " + each.get(0));
					System.out.println(" -Price per item $"   + each.get(1));
					System.out.println(" -Stock: " + (int) each.get(2)/ (int) each.get(5));
					System.out.println(" -Bought at Serpent Island");
					
				}
				
				else {
					
					System.out.println(each.get(0));
					System.out.println(" -Signing bonus $"   + each.get(1));
					System.out.println(" -Current members - " + gameState.getShip().getShipCrew());
					System.out.println(" -Members origin - Serpent Island");
					
				}
				
				total += (int) each.get(4);
				
				storage += (int) each.get(2);
				
				System.out.println();
	
			}
			
			//Print the total cost amount and storage used
			System.out.println("========= Upgrades Summary =============");
			System.out.println("Total amount spent $" + total);
			System.out.println("Total storage taken- " + storage + " units" + "\n");
			
		}
	
	}
	
	/**
	 *Get the player status
	 */
	public void getplayerStats() {
		
		System.out.println("Current player statistics");
		System.out.println(" - Player Name: " + gameState.getPlayer().getName() );
		System.out.println(" - Days Remaining: " + gameState.getPlayer().getDaysRemaining());
		System.out.println(" - Current Money: " + gameState.getPlayer().getMoney());
		System.out.println();
		
	}
	
	/**
	 *Displays the item the player can buy with relevant information
	 *
	 *@param currentItem item the player can buy
	 *@param menuNumber what number the player should choose to buy the current item
	 */
	public void getBuyItem(Item currentItem, int menuNumber) {
		
		System.out.println();
		System.out.println("Name: "+ currentItem.getItemName() + " [" + menuNumber + "]" );
		System.out.println("Description: " + currentItem.getItemDescription());
		System.out.println("Storage Size: " + currentItem.getIitemSize());
		System.out.println("Item Rarity: " + currentItem.getItemRarity());
		System.out.println("Stock: " + currentItem.getItemMaxBuy());
		System.out.println("Price Per Item $ " + currentItem.getItemPrice());
		System.out.println();
		
		
	}
	
	/**
	 *What the shop currently buys
	 */
	public void getSellItem(String currentItem) {
		
		System.out.println("Name: " + currentItem);
		System.out.println();
		
	}
	
	/**
	 *Print what the shop can currently buys
	 */
	public void getBuyItems() {
		
		System.out.println("Current Items the Shop is Buying");
		
		String[] item = gameState.getIsland().getBuyingCollection();
		
		getSellItem(item[0]);
		getSellItem(item[1]);
		getSellItem(item[2]);
		getSellItem(item[3]);
		getSellItem(item[4]);
		

	}
	
	/**
	 *calls the display method for each item the player can buy from the current shop
	 *
	 */
	public void getAllItems() {
		
		System.out.println("Current Items the Shop is Selling");
		
		getBuyItem(gameState.getIsland().getSellItemOne(), 1);
		getBuyItem(gameState.getIsland().getSellItemTwo(), 2);
		getBuyItem(gameState.getIsland().getSellItemThree(), 3);
		getBuyItem(gameState.getIsland().getSellItemFour(), 4);
		getBuyItem(gameState.getIsland().getSellItemFive(), 5);
		
		System.out.println();
		
	}
	
	/**
	 *Display the current ship inventory
	 *
	 */
	public void getShipInventory() {
		
		ArrayList<List<Object>> shipInventory =  gameState.getShip().getCurrentAllItems();
		
		int totalStorage = 0;
		
		int totalAmountSpent = 0;
		
		if(shipInventory.size() > 0) {
			
			System.out.println("Current items on Ship" + "\n");
			
			for(int i = 0; i < shipInventory.size(); i++) {
				
				List<Object> each = shipInventory.get(i);
				
				totalStorage += (int) each.get(2);
				
				totalAmountSpent += (int) (float) each.get(1) * (int) each.get(2) / (int) each.get(5);
				
				System.out.println("Item: " + each.get(0));
				System.out.println("Bought for $ " + each.get(1) + " per item");
				System.out.println("Total storage taken: " + each.get(2));
				System.out.println("Stock: " + (int) each.get(2) / (int) each.get(5)  + " (" + each.get(5) + " per item)");
				System.out.println("Bought at: " + each.get(3));
				System.out.println("Total amount spent on item: $" + each.get(4));
				System.out.println();
				
			}
			
			//prints a summary of inventory
			System.out.println("===================Summary======================");
			System.out.println("Total amount spent $" + totalAmountSpent);
			System.out.println("Total storage taken- " + totalStorage + " units" + "\n");
			
		}
		
		else {
			
			System.out.println("Currently no item in inventory!" + "\n");
			
		}
		
	}
	
	/**
	 *display what player can sell with relevant information
	 *
	 */
	public int getPlayerSell() {
		
		ArrayList<int[]> playerCanSell = gameState.getPlayerCanSell();
		
		ArrayList<List<Object>> shipStorage = gameState.getShip().getCurrentAllItems();
		
		System.out.println("You can currently sell;");
		
		int counter = 0;
		
		for(int i = 0; i < playerCanSell.size(); i++) {
			
			int[] each = playerCanSell.get(i);
			
			List<Object> item = shipStorage.get(each[0]);
			
			counter += 1;
			
			System.out.println("Item: " + item.get(0) + " [" + each[1] + "]");
			System.out.println("Bought for $ " + item.get(1) + " per item");
			System.out.println("Total storage taken: " + item.get(2));
			System.out.println("Stock: " + (int) item.get(2) / (int) item.get(5)  + " (" + item.get(5) + " per item)");
			System.out.println("Bought at: " + item.get(3));
			System.out.println("Total amount spent on item: $" + item.get(4));
			System.out.println();
			
			
		}
		
		return playerCanSell.get(counter-1)[1];
		
	}
	
	/**
	 *display the current route
	 *
	 *@param route int array list that has information about the current route
	 *@param option the input the player must give to go to island where that route takes
	 *
	 */
	public void getRouteStats(int[] route, int option){
		
		String islandName = gameState.getIsland (route);

			
		System.out.println("To go to " + islandName + "[" + option +"]");
		System.out.println("Distance is " + route[0] + " km");
		System.out.println("Expected Time of Arrival is within " + route[1] + " days" );
		System.out.println("Chance of encountering bad weather is " + route[2]  + " %");
		System.out.println("Chance of encountering pirates is " + route[3]  + " %");
		System.out.println("Chance of encountering drowning sailors  is " + route[2]  + " %");
		System.out.println("Total cost to travel to this Island $ " + (gameState.getShip().getMaintenanceCost() * route[1]));
		System.out.println();
		
	}
	
	/**
	 *Get the routes the player can take and call getRouteStats method
	 *
	 */
	public void getMapDetails() {
		
		System.out.println("Current Map Details" + "\n");
		
		getRouteStats(gameState.getIsland().getRoute().getRouteA(), 1);
		getRouteStats(gameState.getIsland().getRoute().getRouteB(), 2);
		getRouteStats(gameState.getIsland().getRoute().getRouteC(), 3);
		getRouteStats(gameState.getIsland().getRoute().getRouteD(), 4);
		
		System.out.println();
		System.out.println("Enter the number associated with the map name");
		System.out.println("Enter 0 to go back to the main menu");
		
		//ask player for which route to take
		int playerSelection = inputChecker(0, 4);
		
		if (playerSelection > 0){
			
			int[] islandNumber;
			
			if (playerSelection == 1) {
				
					islandNumber = gameState.getIsland().getRoute().getRouteA();
					
				}
			
			else if (playerSelection == 2) {
				
					islandNumber = gameState.getIsland().getRoute().getRouteB();
					
			}
			
			else if (playerSelection == 3) {
				
					islandNumber = gameState.getIsland().getRoute().getRouteC();
					
				}
			
			else {
				
					islandNumber = gameState.getIsland().getRoute().getRouteD();	
					
				}
			
			int cost = gameState.getPlayer().getMoney() - (gameState.getShip().getMaintenanceCost() * islandNumber[1]);
			
			if(cost >= 0 && gameState.getPlayer().getDaysRemaining() >= islandNumber[1]) {
				
				String canGo = gameState.checkIslandTraversal(islandNumber);
				
				if(canGo.equals("Pirates!")) {
					
					//Event pirates occurred
					System.out.println(canGo);
					System.out.println("\nyou and your crew were attacked by pirates");
					System.out.println("A dice rolling game was played, two dices were rolled randomly.");
					System.out.println("The ship with the highest deffense gets to roll an addtional dice. The highest total wins!");	
					System.out.println("Looks like " + gameState.getEvent().getWinner());
					System.out.println("=============================Results===================================");
					System.out.println(gameState.getEvent().getMessages().get(0));
					System.out.println(gameState.getEvent().getMessages().get(1));
					System.out.println();
					
					//check status of the current game
					if(gameState.getStatus() == 100) {
						
						System.out.println("The Total amount the trip cost $" + gameState.getrouteTrip());
						System.out.println("======================================================================= \n");
						
					}
					
				}
				
				else {
					
					//Display the summary of the trip
					System.out.println(canGo);
					System.out.println("The Total amount the trip cost $" + gameState.getrouteTrip());
					System.out.println("======================================================================= \n");
					
				}
				
			}
			
			else {
				String message = "Sorry you don't have enough days left to travel to this island! \n";
				
				if(cost < 0) {
					
					message ="Sorry you don't have enough money to travel to this island! \n" ;
					
				}
				
				System.out.println(message);
				
			}
		
		}
		
	}
	

	public static void main(String[] args) {
		
		CommandLine newPlayer = new CommandLine();
		
		newPlayer.startGame();
		
	}
	
}
