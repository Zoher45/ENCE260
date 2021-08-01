package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import game.GameEnvironment;
import game.Item;
import game.Ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameEnviromentClassTest {
	private GameEnvironment testGlobal;
	@BeforeEach 
	public void init() {
		testGlobal = new GameEnvironment("GlobalPlayer", 50, 50);
	}
	// Checks all the ship setters
	 @Test
	 public void setBlackPearl() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 1);
		 assertEquals("Black Pearl",testGame.getShip().getShipName());
	 }
	 @Test
	 public void setFlyingNimbus() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 2);
		 assertEquals("Flying Nimbus",testGame.getShip().getShipName());
	 }
	 @Test
	 public void setMissFortune() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 3);
		 assertEquals("Miss Fortune",testGame.getShip().getShipName());
	 }
	 @Test
	 public void setJollyRoger() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 4);
		 assertEquals("Jolly Roger",testGame.getShip().getShipName());
	 }
	 
	 // Checks all the island setters
	 @Test
	 public void setSusIsland() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 1);
		 testGame.setIslands(1);
		 assertEquals("Sus Island", testGame.getIsland().getIslandName());
	 }
	 @Test
	 public void setPirateIsland() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 2);
		 testGame.setIslands(2);
		 assertEquals("Pirate Island", testGame.getIsland().getIslandName());
	 }
	 @Test
	 public void setSkullIsland() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 3);
		 testGame.setIslands(3);
		 assertEquals("Skull Island", testGame.getIsland().getIslandName());
	 }
	 @Test
	 public void setTreasureIsland() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 4);
		 testGame.setIslands(4);
		 assertEquals("Treasure Island", testGame.getIsland().getIslandName());
	 }
	 @Test
	 public void setSerpentIsland() {
		 GameEnvironment testGame = new GameEnvironment("TestPlayer", 50, 4);
		 testGame.setIslands(5);
		 assertEquals("Serpent Island", testGame.getIsland().getIslandName());
		 
		 // Checks all the upgrades
		 assertEquals("Cannons", testGame.getItem(1).getItemName());
		 assertEquals("Crew member", testGame.getItem(2).getItemName());
		 assertEquals("Guns", testGame.getItem(3).getItemName());
		 assertEquals("Swords", testGame.getItem(4).getItemName());
		 assertEquals("Shields", testGame.getItem(5).getItemName());
		 
		 testGame.setUpgradeOrder(1, 7);
		 List<Object> testUpgrade = testGame.getShip().getUpgrades().get(0);
		 assertEquals("Cannons",testUpgrade.get(0));
	 }
	 
	 @Test
	 public void testPurchaseOrder() {
		 testGlobal.setPurchaseOrder(0,1);
		 ArrayList<List<Object>> testItem = testGlobal.getShip().getCurrentAllItems();
		 assertEquals("Pineapple Crate",testItem.get(0).get(0)); 
	 }
	 @Test
	 public void testTravelingSell() {
		 GameEnvironment testTraveling = new GameEnvironment("TravelingPlayer", 50, 50);
		 testTraveling.setPurchaseOrder(1,1);
		 testTraveling.setIslands(2);
		 testTraveling.setPlayerItems();
		 ArrayList<List<Object>> emptyInventory = testTraveling.getShip().getCurrentAllItems();
		 
		 testTraveling.setSellOrder(6, 1, 50);
		 testTraveling.checkForEmpty();
		 assertEquals(emptyInventory, testTraveling.getShip().getCurrentAllItems());
	 }
	 
}
