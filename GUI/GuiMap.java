package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameEnvironment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowAdapter;
/**
* This class is for displaying the playing map frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiMap extends JFrame {

	private JPanel contentPane;
	private GameEnvironment gameState; 
	public JLabel lblBalance;
	private JLabel lblDaysRemaining;
	/**
	 * Sets the balance label on the map
	 * @param balance 
	 */
	public void setBalanceLabel(int balance) {
		lblBalance.setText("Balance - $" + Integer.toString(balance));
	}
	/**
	 * Sets days remaining  
	 * @param gameState 
	 */
	public void setDayRemain(GameEnvironment gameState) {
		lblDaysRemaining.setText("Days Remaining - " + gameState.getPlayer().getDaysRemaining());
	}
	/**
	 * Opens Ship Information 
	 * @param gameState
	 */
	public void shipInfoGui(GameEnvironment gameState) {
		dispose();
		GuiShipDetails guiShipDetails = new GuiShipDetails(gameState);
		guiShipDetails.setVisible(true);
	}
	/**
	 * Manages routes 
	 * @param islandPosition
	 * @param gameState
	 * @return route 
	 */
	public int[] findRoute(int islandPosition, GameEnvironment gameState) {
		
		if (islandPosition == gameState.getIsland().getRoute().getRouteA()[5]) {
			return gameState.getIsland().getRoute().getRouteA();
		}
		else if(islandPosition == gameState.getIsland().getRoute().getRouteB()[5]) {
			return gameState.getIsland().getRoute().getRouteB();
		}
		else if(islandPosition == gameState.getIsland().getRoute().getRouteC()[5]) {
			return gameState.getIsland().getRoute().getRouteC();
		}
		else {
			return gameState.getIsland().getRoute().getRouteD();
		}
		
	}
	/**
	 * Show/hides the "Travel here" button depending where the player is
	 * @param shopButton
	 * @param button1
	 * @param button2
	 * @param button3
	 * @param button4
	 */
	public void buttonManagement(JButton shopButton, JButton button1, JButton button2, JButton button3, JButton button4) {
		// Current island the player is on
		shopButton.setVisible(false);
		// Every other island button the player isnt on, so they can see they can travel there
		button1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		button4.setVisible(true);
		
		// Sets the text for the button
		button1.setText("Travel here");
		button2.setText("Travel here");
		button3.setText("Travel here");
		button4.setText("Travel here");
	}
	/**
	 * Manages the display label to show where the player is currently
	 * @param showLabel
	 * @param hideLabel1
	 * @param hideLabel2
	 * @param hideLabel3
	 * @param hideLabel4
	 */
	public void labelMangement(JLabel showLabel, JLabel hideLabel1, JLabel hideLabel2, JLabel hideLabel3, JLabel hideLabel4) {
		// Shows the label for showing where the player is
		showLabel.setVisible(true);
		// Hides all the labels for the islands that the player isnt at 
		hideLabel1.setVisible(false);
		hideLabel2.setVisible(false);
		hideLabel3.setVisible(false);
		hideLabel4.setVisible(false);
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		GuiMap frame = new GuiMap(null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Launch Shop Gui
	 * @param gameState 
	 */
	public void shopGui(GameEnvironment gameState) {
		dispose();
		GuiShop shop = new GuiShop(gameState);
		shop.setVisible(true);
	}
	/**
	 * Confirms if the player wants to travel
	 * @param gameState
	 */
	public boolean confirmDialog(int[] route, GameEnvironment gameState) {
		String message = "";
		// First checks if the player has enough days to travel to the selected island 
		if (gameState.getPlayer().getDaysRemaining() < route[1]) {
			message = "You do not have enough days to complete your jounrey to this island";
		}
		// Displays all the route description in a dialog box
		int routeDialog = JOptionPane.showConfirmDialog(null, "Do you want to travel here?\n" + 
														"Distance is " + route[0] + " km\n" +
														"Expected Time of Arrival is within " + route[1] + " days\n" +
														"Chance of encountering bad weather is " + route[2]  + " %\n" +
														"Chance of encountering pirates is " + route[3]  + " %\n" +
														"Chance of encountering drowning sailors  is " + route[2]  + " %\n" + 
														"Cost of travel is $" + (gameState.getShip().getMaintenanceCost() * route[1]) + "\n" + 
														message, 
														"Confirm Travel", JOptionPane.YES_NO_OPTION);
		
		// Catches if the player selects yes on the dialog box
		if (routeDialog == JOptionPane.YES_OPTION) {
			// Cost of traveling for the selected route
			int cost = gameState.getPlayer().getMoney() - (gameState.getShip().getMaintenanceCost() * route[1]);
			
			// When player has enough days remaining and also enough money to travel
			if (cost >= 0 && gameState.getPlayer().getDaysRemaining() >= route[1] ) {
				String canGo = gameState.checkIslandTraversal(route);
				
				if (canGo == "Pirates!") {
					// Launch the pirate game 
					GuiPirateGame guiPirateGame = new GuiPirateGame(gameState);
					guiPirateGame.setVisible(true);
					
					if(gameState.getStatus() == 0) {
						// End game check
						dispose();
						GuiEndGame guiEndGame = new GuiEndGame(gameState);
						guiEndGame.setVisible(true);
						
					} else {
						dispose();
						return true;
						
					}
				}else {
					if (canGo != "") {
						JOptionPane.showMessageDialog(null,canGo);
						
					}
				if (gameState.getStatus() == 0 || gameState.getPlayer().getDaysRemaining() <= 0) {
					dispose();
					GuiEndGame guiEndGame = new GuiEndGame(gameState);
					guiEndGame.setVisible(true);
					
					}
				return true;
				}
			}
			else {
				if (cost < 0) {
					JOptionPane.showMessageDialog(null, "Sorry not enough money to travel this island!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry you don't have enough days left to travel to this island!");
				}
				return false;
		}	
		
		}
		return false;
	}
		


	/**
	 * Create the frame.
	 * @param gameState
	 */
	public GuiMap(GameEnvironment gameState) {
		setTitle("Map");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 633);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 69, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPirateIsland = new JButton("Travel here");
		btnPirateIsland.setBounds(143, 180, 117, 25);
		contentPane.add(btnPirateIsland);
		
		JButton btnTreasureIsland = new JButton("Travel here");
		btnTreasureIsland.setBounds(164, 442, 117, 25);
		contentPane.add(btnTreasureIsland);
		
		JButton btnSkullIsland = new JButton("Travel here");
		btnSkullIsland.setBounds(550, 161, 117, 25);
		contentPane.add(btnSkullIsland);
		
		JButton btnSerpentIsles = new JButton("Travel here");
		btnSerpentIsles.setBounds(641, 487, 117, 25);
		contentPane.add(btnSerpentIsles);
		
		JButton btnSusIsland = new JButton("Travel here");
		btnSusIsland.setBounds(345, 300, 117, 25);
		contentPane.add(btnSusIsland);
		
		JLabel lblSusIslandMarker = new JLabel("You Are Here");
		lblSusIslandMarker.setForeground(Color.CYAN);
		lblSusIslandMarker.setBounds(345, 180, 103, 15);
		contentPane.add(lblSusIslandMarker);
		
		JLabel lblPirateIslandMarker = new JLabel("You Are Here");
		lblPirateIslandMarker.setForeground(Color.CYAN);
		lblPirateIslandMarker.setBounds(143, 54, 103, 15);
		contentPane.add(lblPirateIslandMarker);
		
		JLabel lblTreasureIslandMarker = new JLabel("You Are Here");
		lblTreasureIslandMarker.setForeground(Color.CYAN);
		lblTreasureIslandMarker.setBounds(175, 316, 103, 15);
		contentPane.add(lblTreasureIslandMarker);
		
		JLabel lblSkullIslandMarker = new JLabel("You Are Here");
		lblSkullIslandMarker.setForeground(Color.CYAN);
		lblSkullIslandMarker.setBounds(550, 54, 103, 15);
		contentPane.add(lblSkullIslandMarker);
		
		JLabel lblSerpentIslesMarker = new JLabel("You Are Here");
		lblSerpentIslesMarker.setForeground(Color.CYAN);
		lblSerpentIslesMarker.setBounds(641, 371, 103, 15);
		contentPane.add(lblSerpentIslesMarker);
		
		lblDaysRemaining = new JLabel("Days Remaining - " + gameState.getPlayer().getDaysRemaining());
		lblDaysRemaining.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysRemaining.setForeground(Color.WHITE);
		lblDaysRemaining.setBounds(323, 12, 188, 15);
		contentPane.add(lblDaysRemaining);
		
		lblBalance = new JLabel("Balance - $" + gameState.getPlayer().getMoney());
		lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalance.setForeground(Color.WHITE);
		lblBalance.setBounds(323, 40, 188, 15);
		contentPane.add(lblBalance);
		
		JLabel lblMap = new JLabel("");
		lblMap.setHorizontalAlignment(SwingConstants.LEFT);
		lblMap.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/map_final.jpg")).getImage().getScaledInstance(826, 547, Image.SCALE_SMOOTH)));
		lblMap.setBounds(0, 0, 826, 547);
		contentPane.add(lblMap);
		
		// Checks which island the player is currently at and sets the correct labels and buttons.
		String islandName = gameState.getIsland().getIslandName();
		if (islandName == "Sus Island") {
			buttonManagement(btnSusIsland, btnSerpentIsles, btnSkullIsland, btnTreasureIsland, btnPirateIsland);
			labelMangement(lblSusIslandMarker,lblPirateIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
		} else if(islandName == "Pirate Island") {
			buttonManagement(btnPirateIsland, btnSerpentIsles, btnSkullIsland, btnTreasureIsland, btnSusIsland);
			labelMangement(lblPirateIslandMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
		} else if(islandName == "Skull Island") {
			buttonManagement(btnSkullIsland, btnSerpentIsles, btnPirateIsland, btnTreasureIsland, btnSusIsland);
			labelMangement(lblSkullIslandMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblPirateIslandMarker,lblSerpentIslesMarker);
		} else if(islandName == "Treasure Island") {
			buttonManagement(btnTreasureIsland, btnSerpentIsles, btnSkullIsland, btnPirateIsland, btnSusIsland);
			labelMangement(lblTreasureIslandMarker,lblSusIslandMarker,lblPirateIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
		} else if(islandName == "Serpent Island") {
			buttonManagement(btnSerpentIsles, btnPirateIsland, btnSkullIsland, btnTreasureIsland, btnSusIsland);
			labelMangement(lblSerpentIslesMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblPirateIslandMarker);
		}
		
		
		JButton btnNewButton = new JButton("Ship Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shipInfoGui(gameState);
			}
		});
		btnNewButton.setBounds(10, 559, 188, 25);
		contentPane.add(btnNewButton);
		
	
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Opens inventory frame
				gameState.checkForEmpty();
				dispose();
				PlayerInventory playerInventory = new PlayerInventory(gameState);
				playerInventory.setVisible(true);
			}
		});
		btnInventory.setBounds(416, 559, 188, 25);
		contentPane.add(btnInventory);
		
		JButton btnIslandProperties = new JButton("Island Properties");
		btnIslandProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Opens island properties frame
				dispose();
				GuiIslandProperties guiIslandProperties = new GuiIslandProperties(gameState);
				guiIslandProperties.setVisible(true);
			}
		});
		btnIslandProperties.setBounds(616, 559, 188, 25);
		contentPane.add(btnIslandProperties);
		
		JButton btnShop = new JButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Opens shop frame
				shopGui(gameState);
			}
		});
		btnShop.setBounds(216, 559, 188, 25);
		contentPane.add(btnShop);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(823, 62, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 547, 823, 62);
		contentPane.add(lblBackground);
		
		
		//All the buttons that manages traveling
		//buttonManagement(1,2,3,4,5)  hides 1 and shows 2-3
		//labelMangement(1,2,3,4,5)  shows 1 and hides 2-5
		//setBalanceLabel(int) sets the players balance label on the map
		//setDayRemain(gameState) sets the amount of days left label
		
		//Button to travel to Pirate island
		btnPirateIsland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] routeInfo = findRoute(2,gameState);
				if (confirmDialog(routeInfo, gameState)) {
					buttonManagement(btnPirateIsland, btnSerpentIsles, btnSkullIsland, btnTreasureIsland, btnSusIsland);
					labelMangement(lblPirateIslandMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
					setBalanceLabel(gameState.getPlayer().getMoney());
					setDayRemain(gameState);
				}
			}
		});
		
		//Button to travel to Sus island
		btnSusIsland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] routeInfo = findRoute(1,gameState);
				if (confirmDialog(routeInfo, gameState)) {
					buttonManagement(btnSusIsland, btnSerpentIsles, btnSkullIsland, btnTreasureIsland, btnPirateIsland);
					labelMangement(lblSusIslandMarker,lblPirateIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
					setBalanceLabel(gameState.getPlayer().getMoney());
					setDayRemain(gameState);
				}
			}
		});
		
		//Button to travel to Serpent Isles
		btnSerpentIsles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] routeInfo = findRoute(5,gameState);
				if (confirmDialog(routeInfo, gameState)) {
					buttonManagement(btnSerpentIsles, btnPirateIsland, btnSkullIsland, btnTreasureIsland, btnSusIsland);
					labelMangement(lblSerpentIslesMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblSkullIslandMarker,lblPirateIslandMarker);
					setBalanceLabel(gameState.getPlayer().getMoney());
					setDayRemain(gameState);
				}
			}
		});
		
		//Button to travel to Skull island
		btnSkullIsland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] routeInfo = findRoute(3,gameState);
				if (confirmDialog(routeInfo, gameState)) {
					buttonManagement(btnSkullIsland, btnSerpentIsles, btnPirateIsland, btnTreasureIsland, btnSusIsland);
					labelMangement(lblSkullIslandMarker,lblSusIslandMarker,lblTreasureIslandMarker,lblPirateIslandMarker,lblSerpentIslesMarker);
					setBalanceLabel(gameState.getPlayer().getMoney());
					setDayRemain(gameState);
				}
			}
		});
		
		//Button to travel to Treasure island
		btnTreasureIsland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int [] routeInfo = findRoute(4,gameState);
				if (confirmDialog(routeInfo, gameState)) {
					buttonManagement(btnTreasureIsland, btnSerpentIsles, btnSkullIsland, btnPirateIsland, btnSusIsland);
					labelMangement(lblTreasureIslandMarker,lblSusIslandMarker,lblPirateIslandMarker,lblSkullIslandMarker,lblSerpentIslesMarker);
					setBalanceLabel(gameState.getPlayer().getMoney());
					setDayRemain(gameState);
				}
			}
		});
		
		setBalanceLabel(gameState.getPlayer().getMoney());
	}
}
