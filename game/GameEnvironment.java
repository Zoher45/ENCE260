package game;

import java.util.ArrayList;
import java.util.List;

/**
* The GameEnvironment stores the locations of the related classes. This is the main class that runs the processes as the game progresses.
* Any actions performed by the player will be passes to this class which checks processes the request and gives feedback if required.
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GameEnvironment {
	
	/**
	 * The current player information
	 */
	private Player newPlayer;
	
	/**
	 * The current ship information
	 */
	private Ship currentShip;
	
	/**
	 * The current island information
	 */
	private Island currentIsland;
	
	/**
	 * The island information for sus Island
	 */
	private Island susIsland;
	
	/**
	 * The island information for pirate Island
	 */
	private Island pirateIsland;
	
	/**
	 * The island information for treasure Island
	 */
	private Island treasureIsland;

	/**
	 * The island information for skull Island
	 */
	private Island skullIsland;
	
	/**
	 * The island information for serpent Island
	 */
	private Island serpentIsland;
	
	/**
	 * The event that occurred when the player travels
	 */
	private Events currentEvent;
	
	/**
	 * Array list that stores the index of items the player can sell
	 */
	private ArrayList<int[]> playerCanSell = new ArrayList<int[]>();
	
	/**
	 * Used to track the status of the game
	 */
	private int status;
	
	/**
	 * The starting money the player starts with
	 */
	private int startingMoney;
	

	/**
	 *Int to store the route player took (used to set the island)
	 */
	private int routeTrip;
	
	/**
	 * End message of the game
	 */
	private String endMessage = "No more days remaining!";
	
	

	
	/**
	 * A constructor that sets the required classes
	 * @param name player name
	 * @param daysChosen player chosen days
	 * @param shipChosen the chosen ship 
	 */
	public GameEnvironment(String name, int daysChosen, int shipChosen) {
		
		//Store  information in appropriate locations.
		//Call the set methods for the islands, set the current and set the player information
		
		setShip(shipChosen);
		newPlayer = new Player(name, daysChosen, startingMoney);
		status = 100;
		setSusIsland();
		setPirateIsland();
		setSkullIsland();
		setTreasureIsland();
		setSerpentIsland();
		setIslands(1);
		
	}
	
	/**
	 * Checks to see if the player can still continue with the game.
	 * If player has enough money/can sell to continue traveling.
	 * If the player doesn't meet those requirements end the game.
	 */
	public void endClauseCheck() {
		
		//set the player items with the the item the shop currently buys
		setPlayerItems();
		
		int total = newPlayer.getMoney();
		
		//Get the items from player inventory, examine what the player can sell in the current island store and save the total amount.
		
		ArrayList<List<Object>> shipStorage = currentShip.getCurrentAllItems();
		
		for(int i = 0; i < playerCanSell.size(); i++) {
			
			int[] each = playerCanSell.get(i);
			
			List<Object> item = shipStorage.get(each[0]);
			
			int pricePerItem = (int)(((float) item.get(1) * (float) (currentIsland.getIslandPercentage()) / 100) + (float) item.get(1));
			
			total +=  pricePerItem * ((int) item.get(2) / (int) item.get(5));
			
		}
		
		//For each route from the how long it will take to travel
		
		int daysRouteA = currentIsland.getRoute().getRouteA()[1];
		int daysRouteB = currentIsland.getRoute().getRouteB()[1];
		int daysRouteC = currentIsland.getRoute().getRouteC()[1];
		int daysRouteD = currentIsland.getRoute().getRouteD()[1];
		
		//the cost of traveling per day
		int cost = currentShip.getMaintenanceCost();
		
		//player days remaining
		int daysRemaining = newPlayer.getDaysRemaining();
		
	
		//check if the total amount calculated is sufficient enough to travel to any routes
		//If not then end set status to 0 (end game) and save the end message
		if(total < (daysRouteA * cost)  && total < (daysRouteB* cost) && total < (daysRouteC * cost) && total < (daysRouteD * cost)) {
			
			status = 0;
			
			endMessage = "Not enough money left to travel!";
			
		}
		
		//check if the player has enough days remaining to travel to at least one island from the routes given
		if(daysRemaining < (daysRouteA)  && daysRemaining < (daysRouteB) && daysRemaining < (daysRouteC) && daysRemaining < (daysRouteD)) {
			
			status = 0;
			
			endMessage = "No more days remaining!";
			
		}
			
	}
	
	/**
	 * check all items in the inventory, if stock is empty then delete it from the inventory.
	 */
	public void checkForEmpty() {
		
		int loopCounter = currentShip.getCurrentAllItems().size()-1;
		
	
		for(int i = loopCounter; i >= 0; i --) {
	
			if((int) currentShip.getCurrentAllItems().get(i).get(2) == 0) {
		
				
				getShip().setStorageEmpty(i);
				
			}
			
		}
		
	}
	
	/**
	 * If the player can travel it will start an Event and do necessary actions associated with that event.
	 * 
	 * @param routeSelected a collection of integers that have information about the route the player has selected
	 * 
	 * @return the message of which event occurred
	 */
	public String checkIslandTraversal(int[] routeSelected) {
		
			String message = "";
				
			//reduce the player money by the cost of travel
			newPlayer.setMoney(-(currentShip.getMaintenanceCost() * routeSelected[1]));
				
			//reduce player days by the time required to travel
			newPlayer.setDays(routeSelected[1]);
				
			//create the event with the chances of each event
			currentEvent = new Events(routeSelected[2],routeSelected[3], routeSelected[4]);
			
			//Get which event occurred 
			String eventOccured = currentEvent.eventPicker();
				
			//If pirates occurred then run the dice rolling game
			if (eventOccured.equals("Pirates!")) {
					
				message = "Pirates!";
					
				//The total inventory amount
				int totalItemAmount = 0;
					
				ArrayList<List<Object>> shipInventory =  currentShip.getCurrentAllItems();
					
				for(int i =0; i< shipInventory.size(); i++) {
						
					totalItemAmount += (int) shipInventory.get(i).get(4);

				}
					
				//Launch the dice rolling game
				currentEvent.gameLaunch((int) currentShip.getShipDefence(), (int) (Math.random() * 100), totalItemAmount);
				
				//if pirates won set status to 0 (end game)
				if (currentEvent.getWinnerAction() == false) {
						
					status = 0;
					endMessage = "";
					
				}
					
				//Pirates let the player go, empty the player storage
				else if (currentEvent.getWinner() != "YOU WON! \n \n "){
						
						currentShip.emptyStorage();
				}
					
			}
				
			//The event gotten was either bad weather or pirates
			else {
				
				int eventCost = newPlayer.getMoney() + currentEvent.getCost();
					
				if(eventCost >= 0) {
						
					message = eventOccured;
						
					if(currentEvent.getCost() < 0) {
							
						//weather damage reduced by the ship endurance
						int weatherCal = (int) (currentEvent.getCost() * (((100 - currentShip.getShipEndurance())/100)));
							
						message +=  (-1 * weatherCal + " repair fee.");
						
						newPlayer.setMoney(weatherCal);
						
					}
						
					else {
						
						newPlayer.setMoney(currentEvent.getCost());
							
					}
				
				}
					
			}
				
			//set the new island
			setIslands(routeSelected[5]);
				
			//the cost of the trip with the event cost
			routeTrip = (currentShip.getMaintenanceCost() * routeSelected[1]) + currentEvent.getCost() * -1;
				
			//check to see if the player can still play
			endClauseCheck();
									
			return message;
			
		}

	
	/**
	 * @param shipSelection set the player ship
	 */
	private void setShip(int shipSelection) {
		
		 if(shipSelection == 1) {
			 
			 currentShip = new Ship("Black Pearl", 17, 200, 18, 45, 25, 24, 5);
			 
			 startingMoney = 5000;
			 
			}
		 
		else if (shipSelection == 2) {
			
			currentShip = new Ship("Flying Nimbus", 9, 120, 22, 50, 35, 15, 3);
				
			startingMoney = 2000;
				
			}
		 
		else if (shipSelection == 3) {
			
			currentShip = new Ship("Miss Fortune", 20, 260, 15, 41, 20, 28, 6);
			
			startingMoney = 7000;
			
			}
		 
		else {
			
			currentShip = new Ship("Jolly Roger", 27, 340, 10, 38, 15, 35, 8);
			
			startingMoney = 10000;
			}
		 
	}
	


	/**
	 * @param islandPosition set the current island
	 */
	public void setIslands(int islandPosition) {

		// set the current island to susIsland and replenish the stock
		if (islandPosition == 1) { 

			int[] itemStocks = {5, 5, 5, 5, 5};
			
			susIsland.resetItems(itemStocks);
			
			currentIsland = susIsland;
			
		}
		
		// set the current island to pirateIsland and replenish the stock
		else if (islandPosition == 2) {
			
			int[] itemStocks = {10, 5, 6, 4, 4};
			
			pirateIsland.resetItems(itemStocks);
			
			currentIsland = pirateIsland;

		}
		
		// set the current island to skullIsland and replenish the stock
		else if (islandPosition == 3) {
			
			int[] itemStocks = {15, 10, 9, 4, 15};
			
			skullIsland.resetItems(itemStocks);
			
			currentIsland = skullIsland;
			
		}
		
		// set the current island to treasureIsland and replenish the stock
		else if (islandPosition == 4) {
			
			int[] itemStocks = {8, 5, 6, 4, 9};
			
			treasureIsland.resetItems(itemStocks);
			
			currentIsland = treasureIsland;
			
		}
		
		// set the current island to serpentIsland and replenish the stock
		else {
			
			currentIsland = serpentIsland;
			
		}

	}
	
	/**
	 *Set properties and information of Sus Island
	 */
	private void setSusIsland() {
		
		String[] islandProperties  = {
				
				"Sus Island", "This island has rich soil and are main exporter of fresh produce", 
				"This island generally buys meats and precious metal", 
				"Selling Items ($ per item)", "Banana Crate ($42)", "Coconut Crate ($33)", "Feijoa Crate ($36)", "Mango Crate ($41)", "Pineapple Crate ($39)",
				"Buying Items ($ per item)", "Tuna Crate ($87)", "Catfish Crate ($47)", "Rabbit Crate ($45)", "Gold Bar ($5250)", "Silver Bar ($3500)"
		};
		
		//Items the Island Shop sells
		
		Item banana = new Item("Banana Crate", 42, "Fresh bananas grown from this island. ", 4, "Common", 5);	
		Item coconut = new Item("Coconut Crate", 33, "Fresh coconuts grown form this island ", 2, "Common", 5);
		Item feijoa = new Item("Feijoa Crate", 36, "Fresh feijoas grown from this island ", 4, "common",  5);
		Item mango = new Item("Mango Crate", 41, "Fresh mangos grown from this island ", 4, "Common", 5);
		Item pineapple = new Item("Pineapple Crate", 39, "Fresh pineapples grown from this island ", 4, "common", 5);
		
		Item[] susSellItems = {banana,coconut,feijoa,mango,pineapple};
		
		//Items the Island buys from the player
		String[] susBuyItems = {"Tuna Crate", "Catfish Crate", "Rabbit Crate", "Gold Bar", "Silver Bar"};
		
		
		ArrayList<int[]> collectiveRoutes = new ArrayList<int[]>();
		
		//The routes the player can take from the current island
		int[] routeCProperties = {1200, getItemTimeTaken(1200), 10, 20, 25, 2}; //Pirate Island
		int[] routeDProperties = {900, getItemTimeTaken(900), 25, 10, 15, 3}; //Skull Island
		int[] routeAProperties = {1400, getItemTimeTaken(1400), 10, 15, 10, 4}; //TreasureIsland
		int[] routeBProperties = {1700, getItemTimeTaken(1700), 25, 10, 15, 5}; // serpent Island
		
		//Add the routes to the array list
		collectiveRoutes.add(routeCProperties);
		collectiveRoutes.add(routeDProperties);
		collectiveRoutes.add(routeAProperties);
		collectiveRoutes.add(routeBProperties);
		
		//Constructor for susIsland
		susIsland = new Island("Sus Island", islandProperties, susBuyItems, collectiveRoutes, 75);
		
		//set susIsland items
		susIsland.setItems(susSellItems);
		
	}

	/**
	 *Set properties and information of Pirate Island
	 *Same for all Island
	 */
	private void setPirateIsland() {;
	
		String[] islandProperties  = {
				
				"Pirate Island"," This Island is the main exporter of meat", 
				"This island generally buys metal, metal ores and fruit crates",
				"Selling Items ($ per item)", "Chicken Crate ($30)", "Rabbit Crate ($26)", "Squid Crate ($40)", "Catfish Crate ($27)", "Tuna Crate ($50)",
				"Buying Items ($ per item)", "Gold Bar ($5100)", "Banana Crate ($71)", "Pineapple Crate ($66)", "Platinum Ore ($1020)", "Bronze Ore ($680)"
				
		};
		
		Item chicken = new Item("Chicken Crate", 30, "Caged free chickens ", 2, "Common", 10);	
		Item rabbit = new Item("Rabbit Crate", 26, "Bred and grown rabbits from the island", 2, "Common", 5);
		Item squid = new Item("Squid Crate", 40, "Squids are an exquisite delicacy", 4, "unique",  6);
		Item catfish = new Item("Catfish Crate", 27, "Catfish caught from local farmers", 4, "Common", 4);
		Item tuna = new Item("Tuna Crate", 50, "A saltwater fish caught off the coasts of this island ", 4, "unique", 4);
		
		Item[] pirateSellItems = {chicken, rabbit, squid, catfish, tuna};
		
		String[] pirateBuyItems = {"Gold Bar", "Banana Crate", "Pineapple Crate", "Platinum Ore", "Bronze Ore"};
		
		ArrayList<int[]> collectiveRoutes = new ArrayList<int[]>();
		
		int[] routeCProperties = {1200, getItemTimeTaken(1200), 10, 20, 25, 1}; //SUS Island
		int[] routeEProperties = {1400, getItemTimeTaken(1400), 30, 20, 12, 3}; //Skull Island
		int[] routeIProperties = {1500, getItemTimeTaken(1500), 25, 35, 15, 4}; //TreasureIsland
		int[] routeHProperties = {3000, getItemTimeTaken(3000), 35, 20, 10, 5}; // serpent Island
		
		collectiveRoutes.add(routeCProperties);
		collectiveRoutes.add(routeEProperties);
		collectiveRoutes.add(routeIProperties);
		collectiveRoutes.add(routeHProperties);
		
		pirateIsland = new Island("Pirate Island", islandProperties, pirateBuyItems, collectiveRoutes, 70);
		
		pirateIsland.setItems(pirateSellItems);
		
	}
	
	/**
	 *Set properties and information of Skull Island
	 *Same for all Island
	 */
	private void setSkullIsland() {
		
		String[] islandProperties  = {
				
				"Skull Island","This Island is the main exporter of metal ores, most metal resources are from this island", 
				"This island generally buys precious metal, fruit and meat crates",
				"Selling Items ($ per item)", "Bronze Ore ($400)", "Silver Ore ($900)", "Platinum Ore ($600)", "Gold Ore ($1300)", "Lead Ore ($500)",
				"Buying Items ($ per item)", "Squid Crate ($64)", "Chicken Crate ($48)", "Gold Bar ($4800)", "Feijoa Crate ($57)", "Coconut Crate ($52)"
				
		};
		
		Item bronzeOre = new Item("Bronze Ore", 400, "Bronze is an alloy consisting primarily of copper", 4, "Common", 15);	
		Item SilverOre = new Item("Silver Ore", 900, "Silver ore is extracted from the ground", 4, "unique", 10);	
		Item PlatinumOre = new Item("Platinum Ore", 600, "Platinum Ore such as sperrylite and cooperite ", 4, "unique",  9);
		Item GoldOre = new Item("Gold Ore", 1300, "Gold ore contians composits of gold", 4, "rare", 4);
		Item LeadOre = new Item("Lead Ore", 500, "Lead ore contians composits of lead ", 5, "common", 15);
		
		Item[] skullSellItems = {bronzeOre, SilverOre, PlatinumOre, GoldOre, LeadOre};
		
		String[] skullBuItems = {"Squid Crate", "Chicken Crate", "Gold Bar", "Feijoa Crate", "Coconut Crate"};

		ArrayList<int[]> collectiveRoutes = new ArrayList<int[]>();
		
		int[] routeEProperties = {1400, getItemTimeTaken(1400), 30, 20, 12, 2}; //pirate Island
		int[] routeDProperties = {900, getItemTimeTaken(900), 25, 10, 15, 1}; //Sus Island
		int[] routeFProperties = {3000, getItemTimeTaken(3000), 15, 25, 10, 4}; //TreasureIsland
		int[] routeJProperties = {1400, getItemTimeTaken(1400), 40, 35, 20, 5}; // serpent Island
		
		collectiveRoutes.add(routeEProperties);
		collectiveRoutes.add(routeDProperties);
		collectiveRoutes.add(routeFProperties);
		collectiveRoutes.add(routeJProperties);
		
		skullIsland = new Island("Skull Island", islandProperties, skullBuItems, collectiveRoutes, 60);
		
		skullIsland.setItems(skullSellItems);
		
	}
	
	/**
	 *Set properties and information of Treasure Island
	 *Same for all Island
	 */
	private void setTreasureIsland() {
		
		String[] islandProperties  = {
				
				"Treasure Island", "Treasure Island is a refinery, refined metal resources (metal bars) are from this island", 
				"This island generally buys metal ores and vegtable crates",
				"Selling Items ($ per item)", "Bronze Bar ($1000)", "Silver Bar ($2000)", "Platinum Bar ($1700)", "Gold Bar ($3000)", "Lead Bar ($1200)",
				"Buying Items ($ per item)", "Lead Ore ($825)", "Platinum Ore ($990)", "Silver Ore ($1485)", "Gold Ore ($2145)", "Coconut Crate ($54)"
				
		};
		
		Item bronzeBar   = new Item("Bronze Bar", 1000, "Bronze bar refined at this island", 3, "Common", 8);	
		Item silverBar   = new Item("Silver Bar", 2000, "Silver bar refined at this island", 3, "unique", 5);	
		Item platinumBar  = new Item("Platinum Bar", 1700, "Platinum bar refined at this island", 3, "unique",  6);
		Item goldBar     = new Item("Gold Bar", 3000, "Gold bar refined at this island", 3, "rare", 4);
		Item leadBar = new Item("Lead Bar", 1200, "Lead bar refined at this island", 3, "common", 9);
		
		Item[] treasureSellItems = {bronzeBar, silverBar, platinumBar, goldBar, leadBar};
		
		String[] treasureBuItems = {"Lead Ore", "Platinum Ore", "Silver Ore", "Gold Ore", "Coconut Crate"};
		
		ArrayList<int[]> collectiveRoutes = new ArrayList<int[]>();
		
		int[] routeIProperties = {1500, getItemTimeTaken(1500), 25, 35, 15, 2}; //pirate Island
		int[] routeFProperties = {3000, getItemTimeTaken(3000), 15, 25, 10, 3}; //skull Island
		int[] routeAProperties = {1400, getItemTimeTaken(1400), 10, 15, 15, 1}; //Sus Island
		int[] routeGProperties = {1200, getItemTimeTaken(1200), 40, 35, 20, 5}; // serpent Island
		
		collectiveRoutes.add(routeIProperties);
		collectiveRoutes.add(routeFProperties);
		collectiveRoutes.add(routeAProperties);
		collectiveRoutes.add(routeGProperties);
		
		treasureIsland = new Island("Treasure Island", islandProperties, treasureBuItems, collectiveRoutes, 65);
		
		treasureIsland.setItems(treasureSellItems);
		
	}
	
	/**
	 *Set properties and information of Serpent Island
	 *Same for all Island except this island only sell upgrades for the player
	 */
	private void setSerpentIsland() {

		String[] islandProperties  = {
				
				"Serpent Island", "Serpent Island specializes on ship upgrades", 
				"This island generally buys metal bars and fruit crates",
				"Selling Items ($ per item)", "Cannons ($600)", "Crew member ($300)",  "Guns ($60)", "Swords ($40)", "Shields ($30)",
				"Buying Items ($ per item)", "Feijoa Crate ($63)", "Mango Crate ($71)", "Platinum Bar ($2975)", "Lead Bar ($2100)", "Bronze Bar ($1750)"
				
		};
		
		//Item - upgrades the player can have
		Item cannons = new Item("Cannons", 600, " Cannon is a large gun that fires heavy metal shells or other projectiles (maintance cost $5 per day)", 7, "Common", currentShip.getMaxCannon());	
		Item crew = new Item("Crew member", 300, "Crew member to help incase of pirate attack! (maintance cost $10 per day)", 2, "-", currentShip.getMaxCrew() - currentShip.getShipCrew());
		Item guns = new Item("Guns", 60, "Fire projectile to enemies (maintance cost $2 per day)", 1, "common",  currentShip.getMaxCrew() - currentShip.getShipCrew());
		Item swords = new Item("Swords", 40, "Melee weapon intended for cutting or thrusting ", 1, "Common", currentShip.getMaxCrew() - currentShip.getShipCrew());
		Item shields = new Item("Shields", 30, "A broad piece of defensive armor carried on the arm", 1, "common", currentShip.getMaxCrew() - currentShip.getShipCrew());
		
		Item[] serpentSellItems = {cannons, crew, guns, swords, shields};
		
		String[] serpentBuItems = {"Feijoa Crate", "Mango Crate", "Platinum Bar", "Lead Bar", "Bronze Bar"};
		
		ArrayList<int[]> collectiveRoutes = new ArrayList<int[]>();
		
		int[] routeHProperties = {6000, getItemTimeTaken(6000), 35, 10, 10, 2}; // pirate Island
		int[] routeJProperties = {2900, getItemTimeTaken(2900), 40, 25, 20, 3}; // Skull Island
		int[] routeGProperties = {2500, getItemTimeTaken(2500), 40, 35, 20, 4}; // Treasure Island Island
		int[] routeBProperties = {3500, getItemTimeTaken(3500), 25, 10, 15, 1}; // Sus Island
		
		collectiveRoutes.add(routeHProperties);
		collectiveRoutes.add(routeJProperties);
		collectiveRoutes.add(routeGProperties);
		collectiveRoutes.add(routeBProperties);
		
		serpentIsland = new Island("Serpent Island", islandProperties, serpentBuItems, collectiveRoutes, 75);
		
		serpentIsland.setItems(serpentSellItems);
	}
	
	
	/**
	 *Set the upgrade purchase order 
	 * 
	 * @param item is a index of the item the player has purchased 
	 * @param purchseAmount the amount the player wants to buy for 
	 *  
	 * @return feedback about the transaction
	 */
	public String setUpgradeOrder(int item, int purchseAmount) {
		
		String message = "";
		
		Item boughtItem = getItem(item);
		
		int totalCost = (int) (boughtItem.getItemPrice() * purchseAmount);
		
		int totalStorage = (int) (boughtItem.getIitemSize() * purchseAmount);
		
		//if player cant buy return message
		if (newPlayer.getMoney() - totalCost < 0) {
			
			message = "Sorry you dont have enough money to purchase!";
			
		}
		
		//if not enough storage return message
		else if (currentShip.getStorageRemaining()  - totalStorage < 0) {
			
			message = "Sorry not enough storage!";
			
		}
		
		//Order is made and appropriate calculations are done.
		//Item is added to ship inventory
		
		else {
			
			message = "Order Complete!";
			
			boughtItem.setStock(purchseAmount);
			
			newPlayer.setMoney(-totalCost);
			
			currentShip.setStorageNumber(-totalStorage);
			
			List<Object> storeItem = new ArrayList <Object>();
			
			//Store item properties
			storeItem.add(boughtItem.getItemName());
			storeItem.add(boughtItem.getItemPrice());
			storeItem.add(totalStorage);
			storeItem.add(currentIsland.getIslandName());
			storeItem.add(totalCost);
			storeItem.add(boughtItem.getIitemSize());
			storeItem.add(0);
			
			currentShip.setAddUpgrade(storeItem);
		}
		
		return message;
	}
	
	
	/**
	 *Set the inventory purchase order 
	 * 
	 * @param item is a index of the item the player has purchased 
	 * @param purchseAmount the amount the player wants to buy for 
	 *  
	 * @return feedback about the transaction
	 */
	public String setPurchaseOrder(int item, int purchseAmount) {
		
		String message = "";
		
		Item boughtItem = getItem(item);
		
		int totalCost = (int) (boughtItem.getItemPrice() * purchseAmount);
		
		int totalStorage = (int) (boughtItem.getIitemSize() * purchseAmount);

		if (newPlayer.getMoney() - totalCost < 0) {
			
			message = "Sorry you dont have enough money to purchase!";
			
		}
		
		else if (currentShip.getStorageRemaining()  - totalStorage < 0) {
			
			message = "Sorry not enough storage!";
			
		}
		
		//Order is made and appropriate calculations are done.
		//Item is added to ship inventory
		
		else {
			
			message = "Order Complete!";
			
			boughtItem.setStock(purchseAmount);
			
			newPlayer.setMoney(-totalCost);
			
			currentShip.setStorageNumber(-totalStorage);
			
			List<Object> storeItem = new ArrayList <Object>();
			
			//Store item properties 
			storeItem.add(boughtItem.getItemName());
			storeItem.add(boughtItem.getItemPrice());
			storeItem.add(totalStorage);
			storeItem.add(currentIsland.getIslandName());
			storeItem.add(totalCost);
			storeItem.add(boughtItem.getIitemSize());
			
			currentShip.setAddStorage(storeItem);
		}
		
		return message;
	}
	
	
	/**
	 *Set sell order when the players sells an item
	 * 
	 * @param item is a index of the item the player has purchased 
	 * @param purchseAmount the amount the player wants to buy for 
	 * @param soldPrice the amount that the item was sold for
	 * 
	 */
	public void setSellOrder(int item, int purchseAmount, int soldPrice) {
		
		//get the item from the inventory
		 List<Object>  soldItem = getSpecificStorageItem(item);
		 
		 //Do necessary calculations to storage and money and set it accordingly 
		 int totalCost = soldPrice * purchseAmount;
		 
		 int totalStorage = (int) soldItem.get(5) * purchseAmount;
		 
		 newPlayer.setMoney(totalCost);
		 
		 currentShip.setStorageNumber(totalStorage);
		 
		 int position = 0;
		 
		 ArrayList<int[]> currentitems = getPlayerCanSell();
		 
		 //get the specific item index from the inventory
		 for (int i = 0; i < currentitems.size(); i++) {
			 
			 int[] each = currentitems.get(i);
			 
			 if (each[1] == item) {
				 
				 position = each[0];
				 
			 }
			 
		 }
		 
		 //remove the item
		 currentShip.setRemoveItem(position, totalStorage);
		 
	}
	
	/**
	 *This method goes through what the player can sell
	 *It checks what the island is selling and matches it with the inventory
	 */
	public void setPlayerItems() {
		
		//get current items and what shop is selling 
		
		ArrayList<List<Object>> playerItems = currentShip.getCurrentAllItems();
		ArrayList<int[]> playerCanSellCurrrent = new ArrayList<int[]>();
		String[] item = currentIsland.getBuyingCollection();
		
		//Used as a index to check input when the player sells
		int counter = 0;
		
		//Run a for loop on all the inventory items and match the inventory item to what the shop is selling
		//Save the index where the item is located in the inventory
		
		for(int i = 0; i < playerItems.size(); i++) {
			
			String name = (String) playerItems.get(i).get(0);
			
			if (name.equals(item[0]) || name.equals(item[1]) || name.equals(item[2]) || name.equals(item[3])  || name.equals(item[4])) {
				
				int[] saveIndex = {i, counter+6};
				
				counter += 1;
				
				playerCanSellCurrrent.add(saveIndex);
			}
			
		}
		
		playerCanSell = playerCanSellCurrrent;
		
	}
	

	/**
	 * @return the instance of the currentIsland
	 */
	public Island getIsland() {
		
		return currentIsland;
		
	}
	
	/**
	 * @return the instance of newPlayer
	 */
	public Player getPlayer() {
		
		return newPlayer;
		
	}
	
	/**
	 * @return the instance of currentShip
	 */
	public Ship getShip() {
		
		return currentShip;
		
	}
	
	/**
	 * @return the current status
	 */
	public int getStatus() {
		
		return status;
		
	}
	
	/**
	 * Given an item it will match the the integer given with the item to return
	 * 
	 * @param ItemNumber is used to find which item to process with
	 * 
	 * @return the Item that needs to be processed
	 */
	public Item getItem(int ItemNumber) {
		
		if(ItemNumber == 1) {
			
			return currentIsland.getSellItemOne();
			
		}
		
		else if (ItemNumber == 2) {
			
			return currentIsland.getSellItemTwo();
			
		}
		
		else if (ItemNumber == 3) {
			
			return currentIsland.getSellItemThree();
			
		}
		
		else if (ItemNumber == 4) {
			
			return currentIsland.getSellItemFour();
			
		}
		
		else {
			
			return currentIsland.getSellItemFive();
		}
		
	}
	
	
	/**
	 * Given an integer that represents the index used as a placeholder when player selects an item to sell
	 * it will find the specific item and return it
	 * 
	 * @param checker is used to find which item to process with
	 * 
	 * @return the Item that needs to be processed
	 */
	public List<Object> getSpecificStorageItem(int checker) {
		
		ArrayList<int[]> playerCanSell = getPlayerCanSell();
		
		ArrayList<List<Object>> shipStorage = getShip().getCurrentAllItems();
		
		List <Object> item = new ArrayList <Object>();
		
		for(int i = 0; i < playerCanSell.size(); i++) {
			
			int[] each = playerCanSell.get(i);
			
			if(checker == each[1]) {
				
				item = shipStorage.get(each[0]);
				
				return item;
				
			}
			
		}
		
		return item; //Should never be returned
	}

	

	/**
	 * for each route, this method calculates the time taken. Given distance it will use the ship speed to calculate the time
	 * 
	 * @param distance is used to calculate the time taken.
	 * 
	 * @return the time taken to travel, in days
	 */
	private int getItemTimeTaken(int distance) {
		
		int timeTaken = (int) (distance / currentShip.getShipSpeed())/24;
		
		return timeTaken;
		
	}
	
	
	/**
	 * Given an integer list from routes, it will find the island name
	 * 
	 * @param route is used to find the island name
	 * 
	 * @return the route island name
	 */
	public String  getIsland (int[] route) {
		
		String islandName = "";
		
		if(route[5] == 1) {
			
			islandName = "Sus Island";
			
		}
		
		else if(route[5] == 2) {
			
			islandName = "Pirate Island ";
			
		}
		
		else if(route[5] == 3) {
			
			islandName = "Skull Island ";
			
		}
		
		else if(route[5] == 4) {
			
			islandName = "Treasure Island ";
			
		}
		else if(route[5] == 5) {
			
			islandName = "Serpent Island ";
			
		}
		
		return islandName;
	}
	
	
	/**
	 * @return the int array list of item location in the inventory and selling key from inventory 
	 */
	public ArrayList<int[]> getPlayerCanSell(){
		
		return playerCanSell;
		
	}
	
	/**
	 * @return the int of the route taken cost
	 */
	public int  getrouteTrip() {
		
		return routeTrip;
		
	}
	
	/**
	 * @return the instance of the event
	 */
	public Events getEvent() {
		
		return currentEvent;
		
	}
	
	/**
	 * @return the end message for the game
	 */
	public String getEndMessage() {
		
		return endMessage;
		
	}
	
	/**
	 * @return the starting money the player started with
	 */
	public int getStartingMoney() {
		
		return startingMoney;
		
	}
	
	/**
	 * @return instance of sus Island
	 */
	public Island getSusIsland() {
		
		return susIsland;
		
	}
	
	/**
	 * @return instance of pirate Island
	 */
	public Island getPirateIsland() {
		
		return pirateIsland;
		
	}
	
	/**
	 * @return instance of treasure Island
	 */
	public Island getTreasureIsland() {
		
		return treasureIsland;
		
	}
	
	/**
	 * @return instance of skull Island
	 */
	public Island getSkullIsland() {
		
		return skullIsland;
		
	}
	
	/**
	 * @return instance of serpent Island
	 */
	public Island getSerpentIsland() {
		
		return serpentIsland;
		
	}
	
}
