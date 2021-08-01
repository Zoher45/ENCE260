package game;

import java.util.ArrayList;

/**
* The Island class is where information and properties about the island is stored
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/

public class Island extends Shop{
	
	/**
	 * A String that contains the island name
	 */
	private String islandName;
	
	/**
	 * A list of strings that contains the island properties name
	 */
	private String[] islandProperties;
	
	
	/**
	 * A list of strings that contains what the island buys 
	 */
	private String[] buyingCollection;
	
	/**
	 * An instance of the routes of which the player can travel
	 */
	private Route islandRoutes;

	/**
	 * The percentage the island will buy items for (extra than the player paid for)
	 */
	private int islandBuyPercentage;
	
	
	/**
	 * @param name name of island
	 * @param properties list of properties of the island
	 * @param buyingItems list of what the island buys
	 * @param routes from which the player can travel to another island
	 * @param islandPercentageGiven extra percentage of the item the shop buys for
	 */
	public Island(String name, String[] properties, String[] buyingItems, ArrayList<int[]> routes, int islandPercentageGiven) {
		
		//Store's island details in appropriate location
		islandName = name;
		islandProperties = properties;
		buyingCollection = buyingItems;
		islandRoutes = new Route(routes);
		islandBuyPercentage = islandPercentageGiven;
	}
	
	
	/**
	 * @return routes of the island
	 */
	public Route getRoute() {
		
		return islandRoutes;
		
	}

	/**
	 * @return the name of the island
	 */
	public String getIslandName() {
		
		return islandName;
		
	}
	
	/**
	 * @return the properties of the island
	 */
	public String[] getIslandProperties() {
		
		return islandProperties;
		
	}
	
	/**
	 * @return the buying collection of the island
	 */
	public String[] getBuyingCollection() {
		
		return buyingCollection;
		
	}
	
	/**
	 * @return the Island percentage
	 */
	public int getIslandPercentage() {
		
		return islandBuyPercentage;
		
	}
	
}
