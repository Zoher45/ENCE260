package game;


import java.util.ArrayList;

/**
* The Game class is where the dice rolling game (done at random) is played between player and pirate
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class Game {
	
	/**
	 * An array list that has the messages to display about the outcome of the game
	 */
	private ArrayList<String> message = new ArrayList<String>();
	
	/**
	 * A String that represents the winner of the game
	 */
	private String winner;
	
	/**
	 * A boolean that represents if the winner is the player (true)
	 */
	private boolean winnerAction = true;
	
	/**
	 * @param playerDef the player's ship defense
	 * @param pirateDef the pirate's defense (generated randomly)
	 * @param totalPlayerItemAmount the current worth of the player's inventory
	 */
	public void gameLaunch(int playerDef, int pirateDef, int totalPlayerItemAmount) {
		
		///Generate random dice rolls for the player and the pirate
		int playerThirdRollState = 0;
		
		int pirateThirdRollState = 0;
		
		int playerRollOne = (int) (Math.random() * 6) + 1;
		
		int playerRollTwo = (int) (Math.random() * 6) + 1;
		
		int playerRollThree = (int) (Math.random() * 6) + 1;
		
		int playerDiceTotal = playerRollOne + playerRollTwo;
		
		playerThirdRollState = playerRollOne + playerRollTwo + playerRollThree;
		
		int pirateRollOne = (int) (Math.random() * 5) + 1;
		
		int pirateRollTwo = (int) (Math.random() * 5) + 1;
		
		int pirateRollThree = (int) (Math.random() * 6) + 1;
		
		int pirateDiceTotal = pirateRollOne + pirateRollTwo;
		
		pirateThirdRollState = pirateRollOne + pirateRollTwo + pirateRollThree;
		
		//The highest defense between the player and the pirate will have the added third roll advantage
		if (playerDef >= pirateDef) {
			
			playerDiceTotal += playerRollThree;
			
		}
			
		else {
			
			pirateDiceTotal += pirateRollThree;
			
		}

		//save the results of the game
		if (playerDiceTotal != pirateDiceTotal) {
			
			if(playerDiceTotal == playerThirdRollState) {
				
				message.add(("Player rolled - " + playerRollOne + ", " + playerRollTwo + " and " + playerRollThree + ".Total - " + playerDiceTotal));
				
			}	
			
			else {
				
				message.add("Player rolled - " + playerRollOne + " and " + playerRollTwo + ".Total - " + playerDiceTotal);
				
			}
			
			if(pirateDiceTotal == pirateThirdRollState) {
				
				message.add("Pirate rolled - " + pirateRollOne + ", " + pirateRollTwo + " and " + pirateRollThree + ".Total - " + pirateDiceTotal);
				
			}
			
			else {
				
				message.add("Pirate rolled - " + pirateRollOne + " and " + pirateRollTwo + ".Total - " + pirateDiceTotal);
				
			}
			
			if (playerDiceTotal > pirateDiceTotal) {
				
				winner = "YOU WON! \n \n ";
				
				}

			else {
				
				//Generate a random number so that if it is less the the player's current inventory amount, the player can continue
				//since player lost the game set winnerAction to be false
				int pirateSatisfaction = (int) (Math.random() * 4000);
				
				if(totalPlayerItemAmount < pirateSatisfaction) {
					
					winner = "PIRATES WON! \nThey have examined your loot and are not satisfied with your items \nThey have made you walk the plank!";
					
					//used to end game
					winnerAction = false;
					
				}
				
				else {
					
					winner = "PIRATES WON! \nThey have examined your loot and are satisfied \nYou can carry on your adventure but have lost all your Items!";
				}
				
			}
			
		}
		
		
		else {
			
			gameLaunch(100,50, totalPlayerItemAmount);
			
		}
			
	}
	
	/**
	 * @return the result of the game
	 */
	public  ArrayList<String> getMessages(){
		
		return message;
		
	}
	
	/**
	 * @return the winner of the dice rolling game
	 */
	public boolean getWinnerAction() {
		
		return winnerAction;
		
	}
	
	/**
	 * @return string representation of the winner
	 */
	public String getWinner() {
		
		return winner;
		
	}
	
}
	
