package game;

import java.util.ArrayList;
import java.util.List;

/**
* The Ship class contains information about the ship status such as name, ship defense, speed, etc.
* This class also contains the players inventory and upgrades.
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class Ship{
	
	/**
	 * A String that represents the ships name
	 */
	private String shipName;

	/**
	 * A in that represents the ships crew
	 */
	private int shipCrew;
	
	/**
	 * A int that represents the ships storage
	 */
	private int shipStorage;
	
	/**
	 * A int that represents the ships speed
	 */
	private int  shipSpeed;
	
	/**
	 * A float that represents the ships defense
	 */
	private float shipDefence;
	
	/**
	 * A float that represents the ships endurance
	 */
	private float shipEndurance;
	
	/**
	 * A int that represents the ships max crew amount
	 */
	private int maxCrew;
	
	/**
	 * A int that represents the ships max cannon amount
	 */
	private int maxCannon;
	
	/**
	 * A int that represents the ships maintenance cost
	 */
	private int maintenanceCost;

	/**
	 * A double that represents the defense increase per upgrade purchase
	 */
	private double percentagePerItem;
	
	/**
	 * An array list that represents the ships upgrades
	 */
	private ArrayList<List<Object>> upgrades = new ArrayList<List<Object>>();
	
	/**
	 * An array list that represents the ships inventory
	 */
	private ArrayList<List<Object>> storage = new ArrayList<List<Object>>();
	
	/**
	 * @param name the ships name given
	 * @param crew is the current crew members the ship has 
	 * @param storage the storage amount the ship has
	 * @param speed the speed (in knots) the ship travels
	 * @param defense the current defense the ship has
	 * @param endurance the endurance percentage the ship has
	 * @param shipMaxCrew the max amount of crew members the ship can have
	 * @param shipMaxCannon the max amount of cannons the ship can have
	 */
	public Ship(String name, int crew, int storage, int speed, float defense, float endurance, int shipMaxCrew, int shipMaxCannon) {
		
		//store player information in appropriate locations
		shipName = name;
		shipCrew = crew;
		shipStorage = storage;
		shipSpeed = speed;
		shipDefence = defense;
		shipEndurance = endurance;
		maxCrew = shipMaxCrew;
		maxCannon = shipMaxCannon;
		maintenanceCost = 10*crew;
		percentagePerItem = (100 - defense) * 1/((shipMaxCrew-crew) *4 + shipMaxCannon);
		
	}


	/**
	 * Empty player storage
	 */
	public void emptyStorage() {
		
		storage = new ArrayList<List<Object>>();
		
	}
	
	/**
	 * @param totalStorage add to ship storage
	 */
	public void setStorageNumber(int totalStorage) {
		
		shipStorage += totalStorage;
		
	}
	
	/**
	 * @param position remove specific inventory
	 */
	public void setStorageEmpty(int position) {
		
		storage.remove(position);
		
	}
	
	
	/**
	 * @param item check upgrade and add to it if needed
	 */
	public void setAddUpgrade(List<Object> item) {
		
		//boolean to check if item not already on upgrades
		boolean checkShipUpgrades = false;
		
		//loop through the current upgrades
		//If item matches the current upgrade item in the ship then add to the stock
		//Do the necessary calculations to the defense, maintenance cost and storage
		for (int i = 0; i < upgrades.size(); i++) {
			
			if(upgrades.get(i).get(0).equals(item.get(0))) {
				
				checkShipUpgrades = true;
				
				maintenanceCost += ((int) item.get(2) / (int) item.get(5)) * (int) upgrades.get(i).get(6);
				
				shipDefence +=  ((int) item.get(2) / (int) item.get(5))   * percentagePerItem;
				
				int newStorage = (int) upgrades.get(i).get(2) +  (int) item.get(2);
				
				upgrades.get(i).set(2, newStorage);
				
				int newtotal = (int) upgrades.get(i).get(4) +  (int) item.get(4);
				
				upgrades.get(i).set(4, newtotal);
				
				if(upgrades.get(i).get(0).equals("Crew member")) {
					
					shipCrew += ((int) item.get(2) / (int) item.get(5));
					}
				
				}
			
			}

		//if not already on upgrades add to upgrades and do the necessary calculations
		if(checkShipUpgrades == false) {
			
				if(item.get(0).equals("Cannons")) {
					
					item.set(6, 5);
					
					maintenanceCost += 5 * ((int) item.get(2) / (int) item.get(5));
					
				}
				
				else if(item.get(0).equals("Crew member")) {
					
					item.set(6, 10);
					
					shipCrew = (int)item.get(2)/2  + shipCrew;
					
					maintenanceCost += 10 * ((int) item.get(2) / (int) item.get(5));
					
				}
				
				else if(item.get(0).equals("Guns")) {
					
					item.set(6, 2);
					
					maintenanceCost += 2 * ((int) item.get(2) / (int) item.get(5));
					
				}
				
				upgrades.add(item);
				
				shipDefence += percentagePerItem * ((int) item.get(2) / (int) item.get(5));
		}
		
	}
		
	
	
	
	/**
	 * @param item a list of properties about the item to be stored in the inventory
	 */
	public void setAddStorage(List<Object> item) {
		
		//boolean to check if item not already on inventory
		boolean checkShipStorage = false;
		
		//check the current inventory to see if item is already on the ship
		for (int i = 0; i < storage.size(); i++) {
			
			if(storage.get(i).get(0).equals(item.get(0)) && storage.get(i).get(3).equals(item.get(3))) {
				
				checkShipStorage = true;
				
				int newStorage = (int) storage.get(i).get(2) +  (int) item.get(2);
				
				storage.get(i).set(2, newStorage);
				
				int newtotal = (int) storage.get(i).get(4) +  (int) item.get(4);
				storage.get(i).set(4, newtotal);
				
			}
			
		}
		
		//if checkShipStorage no items found add to inventory
		if (checkShipStorage == false) {
			
			storage.add(item);
			
		}		
		
	}
	
	
	/**
	 * @param listNumber to find item in the inventory
	 * @param totalRemoval the amount to remove 
	 */
	public void setRemoveItem(int listNumber, int totalRemoval) {
		
		//Finds the amount remaining when inventory stock is subtracted by totalRemoval
		int remove = (int)storage.get(listNumber).get(2) - totalRemoval;
		
		//if remove is greater than 0 update inventory (by listNumber) stock else set to 0
		if(remove > 0) {
			
			storage.get(listNumber).set(2, remove);

		 }
		
		else {
			
			storage.get(listNumber).set(2, 0);

		} 
		
	}
	
	/**
	 * @return the inventory of the ship
	 */
	public ArrayList<List<Object>> getCurrentAllItems() {
		
		return storage;
		
	}
	
	/**
	 * @return the ship name
	 */
	public String getShipName() {
		
		return shipName;
		
	}
	
	/**
	 * @return the crew amount
	 */
	public int getShipCrew() {
		
		return shipCrew;
		
	}
	
	/**
	 * @return the storage amount remaining
	 */
	public int getStorageRemaining() {
		
		return shipStorage;
		
	}
	
	/**
	 * @return the ship speed
	 */
	public int getShipSpeed() {
		
		return shipSpeed;
		
	}
	
	/**
	 * @return the ship defense percentage
	 */
	public float getShipDefence() {
		
		return shipDefence;
		
	}
	
	/**
	 * @return the ship endurance
	 */
	public float getShipEndurance() {
		
		return shipEndurance;
		
	}
	
	/**
	 * @return the upgrades
	 */
	public ArrayList<List<Object>> getUpgrades(){
		
		return upgrades;
		
	}
	
	/**
	 * @return the max crew the ship can have
	 */
	public int getMaxCrew() {
		
		return maxCrew;
		
	}
	
	/**
	 * @return the max cannons the ship can have
	 */
	public int getMaxCannon() {
		
		return maxCannon;
		
	}
	
	/**
	 * @return the ship maintenance Cost
	 */
	public int getMaintenanceCost() {
		
		return maintenanceCost;
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
