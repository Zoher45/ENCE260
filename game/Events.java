package game;

/**
* The Event class is used to decide which event to run (based on the route chances)
* This class is used to run the events and save/return the outcome
* This class is also used to call the Game class if event: pirate was chosen
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class Events extends Game{
	
	/**
	 * A int that represents the weather chance
	 */
	private int weatherChance;
	
	/**
	 * A int that represents the pirate chance
	 */
	private int pirateChance;
	
	/**
	 * A int that represents the sailor chance
	 */
	private int sailorChance;
	
	/**
	 * A int that represents the cost after the event has occurred
	 */
	private int cost;
	
	
	/**
	 * @param weather the chance of the weather event occurring
	 * @param pirate the chance of the pirate event occurring
	 * @param sailor the chance of the sailor event occurring
	 */
	public Events(int weather, int pirate, int sailor) {
		
		//store event chances in appropriate locations
		weatherChance = weather;
		pirateChance = pirate;
		sailorChance = sailor;
		
	} 

	/**
	 * @return Which event occurred
	 */
	public String eventPicker() {
		
		//Generate random event percentage use the according chances (given by the route- weather/pirate/sailor) to run that event
		//Run the calculation for each event and save it to the cost
		//return string of which event occurred (with cost, unless pirate event) after event completed
		
		int finalEventChance = (int) (Math.random() * 100);
		
		String message = "";
		
		if(finalEventChance <= weatherChance) {
			
			int shipDamage = (int) (Math.random() * 30 ) + 5;
			
			int weatherDamage = shipDamage * 20;
			
			message = "Bad weather occured. Your ship was damaged by " + shipDamage + "% You have been charged $ ";
			
			cost = - weatherDamage;
			
		}
		
		else if (finalEventChance > weatherChance && finalEventChance <= (weatherChance + pirateChance)) {
			
	
			message = "Pirates!";
			
		}
		
		else if (finalEventChance > (weatherChance + pirateChance) && finalEventChance < (weatherChance + pirateChance + sailorChance)) {
			
			int sailorsRescured = (int) ( Math.random() * 4);
			
			if(sailorsRescured == 0) {
				
				sailorsRescured = 1;
				
			}
			
			cost = sailorsRescured * 60;
			
			message = "You have picked up " + sailorsRescured + " drowning sailors. They have paid you $" + cost + ".";
	
		}
		
		return message;
		
	}

	/**
	 * @return the cost of the occurred event
	 */
	public int getCost() {
		
		return cost;
		
	}
	
}
