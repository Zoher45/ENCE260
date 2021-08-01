package game;

/**
* The Shop class is where the island stores what items they sell
* This class is used to replenish the stocks
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/

public class Shop {
	
	/**
	 * An instance of the first item the shop sells 
	 */
	private Item sellItemOne;
	
	/**
	 * An instance of the second item the shop sells
	 */
	private Item sellItemTwo;
	
	/**
	 * An instance of the third item the shop sells
	 */
	private Item sellItemThree;
	
	/**
	 * An instance of the fourth item the shop sells
	 */
	private Item sellItemFour;
	
	/**
	 * An instance of the fifth item the shop sells
	 */
	private Item sellItemFive;
	

	/**
	 * @param sellingItems is a list of instances from Item class 
	 */
	public void setItems(Item[] sellingItems){
		
		//from sellingItems index 0 save instance to sellItemOne 
		sellItemOne = sellingItems[0];
		
		//From sellingItems index 1 save instance to sellItemTwo 
		sellItemTwo = sellingItems[1];
		
		//From sellingItems index 2 save instance to sellItemThree 
		sellItemThree = sellingItems[2];
		
		//From sellingItems index 3 save instance to sellItemFour 
		sellItemFour = sellingItems[3];
		
		//From sellingItems index 4 save instance to sellItemFive 
		sellItemFive = sellingItems[4];		
		
	}
	
	
	/**
	 * @param sellingItemsStock is a list of int's from GameEnvironment class 
	 */
	public void resetItems(int[] sellingItemsStock) {
		
		//From sellingItemsStock index 0 set stock amount of sellItemOne
		sellItemOne.replenishStock(sellingItemsStock[0]);
		
		//From sellingItemsStock index 1 set stock amount of sellItemTwo
		sellItemTwo.replenishStock(sellingItemsStock[1]);
		
		//From sellingItemsStock index 2 set stock amount of sellItemThree
		sellItemThree.replenishStock(sellingItemsStock[2]);
		
		//From sellingItemsStock index 3 set stock amount of sellItemFour
		sellItemFour.replenishStock(sellingItemsStock[3]);
		
		//From sellingItemsStock index 4 set stock amount of sellItemFive
		sellItemFive.replenishStock(sellingItemsStock[4]);
		
	}
		
	
	/**
	 * @return Item one that the shop sells 
	 */
	public Item getSellItemOne() {
		return sellItemOne;
	}
	
	/**
	 * @return Item two that the shop sells 
	 */
	public Item getSellItemTwo() {
		return sellItemTwo;
	}
	
	/**
	 * @return Item three that the shop sells 
	 */
	public Item getSellItemThree() {
		return sellItemThree;
	}
	
	/**
	 * @return Item four that the shop sells 
	 */
	public Item getSellItemFour() {
		return sellItemFour;
	}
	
	/**
	 * @return Item five that the shop sells 
	 */
	public Item getSellItemFive() {
		return sellItemFive;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
