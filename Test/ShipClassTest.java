package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import game.Ship;

import org.junit.jupiter.api.Test;

class ShipClassTest {
	private Ship testShip;
	@BeforeEach 
	public void init() {
		testShip = new Ship("TestShip", 30, 200, 15, 100, 100, 50, 50);
	}
	
	@Test
	public void testSetStorageNumber() {
		testShip.setStorageNumber(10);
		assertEquals(210, testShip.getStorageRemaining());
	}
	
	
	@Test
	public void testSetAddUpgrade() {
		ArrayList<List<Object>> shipInventory = testShip.getUpgrades();
		
		// Adds Cannon upgrade
		List<Object> item = new ArrayList <Object>();
		
		item.add("Cannons");
		item.add(300);
		item.add(5);
		item.add("Island Name");
		item.add(600);
		item.add(10);
		item.add(0);
		
		testShip.setAddUpgrade(item);
		testShip.setAddUpgrade(item);
		
		List<Object> upgradeName = shipInventory.get(0);
		assertEquals("Cannons",upgradeName.get(0));
		
		// Adds Crew members
		List<Object> item2 = new ArrayList <Object>();
		
		item2.add("Crew member");
		item2.add(300);
		item2.add(7);
		item2.add("Island Name");
		item2.add(300);
		item2.add(2);
		item2.add(0);
		
		testShip.setAddUpgrade(item2);
		testShip.setAddUpgrade(item2);
		
		List<Object> upgradeName2 = shipInventory.get(1);
		assertEquals("Crew member",upgradeName2.get(0));
		
		// Adds gun upgrade
		List<Object> item3 = new ArrayList <Object>();
		
		item3.add("Guns");
		item3.add(60);
		item3.add(2);
		item3.add("Island Name");
		item3.add(300);
		item3.add(2);
		item3.add(0);
		
		testShip.setAddUpgrade(item3);
		testShip.setAddUpgrade(item3);
		
		List<Object> upgradeName3 = shipInventory.get(2);
		assertEquals("Guns",upgradeName3.get(0));
	}
	
	
	@Test
	public void testSetAddStorage() {
		ArrayList<List<Object>> shipInventory = testShip.getCurrentAllItems();
		
		List<Object> item = new ArrayList <Object>();
		
		item.add("Banana Crate"); // Name
		item.add(42); // Price
		item.add(5); // Total Storage
		item.add("Sus Island"); // Origin
		item.add(42); // Cost
		item.add(4); // Item Size
		
		testShip.setAddStorage(item);
		testShip.setAddStorage(item);
		
		List<Object> itemOne = shipInventory.get(0);
		assertEquals("Banana Crate", itemOne.get(0));
		
	}
	@Test
	public void testSetRemoveItem() {
		Ship testShipRemoval = new Ship("TestShip", 30, 200, 15, 100, 100, 50, 50);
		ArrayList<List<Object>> emptyInventory = testShipRemoval.getCurrentAllItems();
		
		ArrayList<List<Object>> shipInventory = testShipRemoval.getCurrentAllItems();
		
		List<Object> item = new ArrayList <Object>();
		
		item.add("Banana Crate"); // Name
		item.add(42); // Price
		item.add(4); // Total Storage
		item.add("Sus Island"); // Origin
		item.add(42); // Cost
		item.add(4); // Item Size
		
		testShipRemoval.setAddStorage(item);
		testShipRemoval.setAddStorage(item);
		
		testShipRemoval.setRemoveItem(0, 8);
		assertEquals(emptyInventory,testShipRemoval.getCurrentAllItems());
	}
}
