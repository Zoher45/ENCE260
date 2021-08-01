package game;

/**
* The Item class is where the properties of an individual item is stored
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/

public class Item  {
	
	/**
	 * A String that contains the item name
	 */
	private String itemName;
	
	/**
	 * A String that contains the item description
	 */
	private String itemDescription;
	
	/**
	 * A String that contains the item rarity
	 */
	private String itemRarity;
	
	/**
	 * An int that contains the item size (storage)
	 */
	private int itemSize;
	
	/**
	 * An int that contains the item stock (max buy)
	 */
	private int itemMaxBuy;
	
	/**
	 * An float that contains the item price
	 */
	private float itemPrice;

	/**
	 * @param name name of item
	 * @param price price per item
	 * @param description description of item
	 * @param size size per item (storage per item)
	 * @param rarity indication of price
	 * @param maxBuy max amount the player can buy
	 */
	public Item(String name, float price, String description, int size, String rarity, int maxBuy){
		
		//Store's item details in appropriate location
		itemName = name;
		itemDescription = description;
		itemRarity = rarity;
		itemSize = size;
		itemMaxBuy = maxBuy;
		itemPrice = price;

	}
	
	
	/**
	 * @param purchaseAmount is the amount the player has bought and stock is reduced accordingly
	 */
	public void setStock(int purchaseAmount) {
		
		itemMaxBuy -= purchaseAmount;
		
	}
	
	/**
	 * @param maxBuy replenish stock
	 */
	public void replenishStock(int maxBuy) {
		
		itemMaxBuy = maxBuy;
		
	}
	
	/**
	 * @return item name
	 */
	public String getItemName() {
		
		return itemName;
		
	}
	
	/**
	 * @return item description
	 */
	public String getItemDescription(){
		
		return itemDescription;
		
	}
	
	/**
	 * @return item rarity
	 */
	public String getItemRarity(){
		
		return itemRarity;
		
	}
	
	/**
	 * @return item size
	 */
	public int getIitemSize(){
		
		return itemSize;
		
	}
	
	/**
	 * @return item stock
	 */
	public int getItemMaxBuy(){
		
		return itemMaxBuy;
		
	}
	
	/**
	 * @return item price per item
	 */
	public float getItemPrice(){
		
		return itemPrice;
		
	}

}
