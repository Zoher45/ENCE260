package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Player;

class PlayerClassTest {
	private Player testPlayer;
	@BeforeEach 
    public void init() {
		testPlayer = new Player("TestPlayer", 50, 5000);
    }
	@Test
	public void playerMoneyTest() {
		// Tests the adding money to the players balance.
		testPlayer.setMoney(1000);
		assertEquals(6000, testPlayer.getMoney());
	}
	@Test
	public void playerDaysLeft() {
		// Tests removing the amount of days the player has left to play.
		testPlayer.setDays(10);
		assertEquals(40, testPlayer.getDaysRemaining());
	}
}
