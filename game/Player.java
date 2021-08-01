package game;

/**
* The Player class stores information about the player such as the player name, balance and days remaining.
* This class also sets the money as the player buys or sells items.
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class Player {
	
	/**
	 * A String that represents the player name
	 */
	private String name;
	
	/**
	 * A int that represents the days remaining
	 */
	private int daysRemaining;
	
	/**
	 * A int that represents current money
	 */
	private int money;
	
	/**
	 * A int that represents the days chosen initially
	 */
	private int daysChosen;
	
	/**
	 * @param playerName the player name given
	 * @param choosenDays is the allocated days chosen
	 * @param starterMoney initial amount of money the player starts of with
	 */
	public Player(String playerName, int choosenDays, int starterMoney) {
		
		//store player information in appropriate locations
		name = playerName;
		daysRemaining = choosenDays;
		daysChosen = choosenDays;
		money = starterMoney;
		
	}
	
	/**
	 * @param amount the amount the player has gained/loss
	 */
	public void setMoney(int amount) {
		
		money += amount;
		
	}
	
	/**
	 * @param islandTravseral the amount of days the trip cost (reduce player days remaining)
	 */
	public void setDays(int islandTravseral) {
		
		daysRemaining -= islandTravseral;
		
	}
	
	/**
	 * @return the days remaining for the player 
	 */
	public int getDaysRemaining() {
		
		return daysRemaining;
		
	}
	
	/**
	 * @return the player name
	 */
	public String getName() {
		
		return name;
		
	}
	
	/**
	 * @return the current amount the player has
	 */
	public int getMoney() {
		
		return money;
		
	}
	
	/**
	 * @return the days chosen initially by the player
	 */
	public int getDaysChosen() {
		
		return daysChosen;
		
	}
	
}
