package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import game.GameEnvironment;

import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.util.EventObject;
/**
* This class is for displaying the ship details frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiShipDetails extends JFrame {
	private JPanel contentPane;
	private JLabel lblShipPicture;
	private ArrayList<List<Object>> shipUpgrades;
	private JTable tblInventory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiShipDetails frame = new GuiShipDetails(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Updates the upgrades inventory table
	 * @param shipUpgrades
	 * @param model
	 */
	public void updateInventory(ArrayList<List<Object>> shipUpgrades, DefaultTableModel model) {
		model.setRowCount(0);
		// Loops through each row in the shipUpgrades array and adds each row to the table
		for (int i = 0; i < shipUpgrades.size(); i++) {
			List<Object> each = shipUpgrades.get(i);
			model.addRow(new Object[]{each.get(0), ((int) each.get(2)/ (int) each.get(5))});
		}
	}
	/**
	 * Sets the ship picture
	 * @param shipPictureLocation 
	 */
	public void setShipPicture(String shipPictureLocation) {
		lblShipPicture.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(shipPictureLocation)).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
	}
	
	/**
	 * Checks which ship the player picked
	 * @param shipName 
	 */
	public void checkShipName(String shipName) {
		// Checks which ship is picked and sets the corresponding image with the ship
		if(shipName == "Black Pearl") {
			setShipPicture("/img/blackPearl.jpg");
		} else if (shipName == "Flying Nimbus") {
			setShipPicture("/img/flyingNimbus.jpg");
		} else if (shipName == "Miss Fortune") {
			setShipPicture("/img/missFortune.jpg");
		} else if (shipName == "Jolly Roger") {
			setShipPicture("/img/jollyRoger.jpg");
		}
	}

	/**
	 * Create the frame.
	 * @param gameState 
	 */
	public GuiShipDetails(GameEnvironment gameState) {
		setTitle("Ship Details");
		setResizable(false);

		setBounds(100, 100, 826, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblShipPicture = new JLabel("");
		checkShipName(gameState.getShip().getShipName());

		lblShipPicture.setBounds(36, 30, 250, 250);
		contentPane.add(lblShipPicture);
		
		//Displays the ships name
		JLabel lblShipName = new JLabel("Ship Name");
		lblShipName.setText(" -Ship name: " + gameState.getShip().getShipName());
		lblShipName.setBounds(325, 33, 250, 15);
		contentPane.add(lblShipName);
		
		//Displays the amount of crew members
		JLabel lblCrewMembers = new JLabel("Crew Members");
		lblCrewMembers.setText(" -Crew members: " + gameState.getShip().getShipCrew());
		lblCrewMembers.setBounds(325, 60, 250, 15);
		contentPane.add(lblCrewMembers);
		
		//Displays the maintenance cost for the ship
		JLabel lblMaintenanceCost = new JLabel("Maintenance Cost");
		lblMaintenanceCost.setText(" -Maintenance cost per day (Cost required to travel per day): $" + gameState.getShip().getMaintenanceCost());
		lblMaintenanceCost.setBounds(325, 87, 489, 15);
		contentPane.add(lblMaintenanceCost);
		
		//Displays the storage of the ship
		JLabel lblStorage = new JLabel("Storage");
		lblStorage.setText(" -Storage: " + gameState.getShip().getStorageRemaining() + " units");
		lblStorage.setBounds(325, 114, 250, 15);
		contentPane.add(lblStorage);
		
		//Displays the speed of the ship
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setText(" -Speed factor: " + gameState.getShip().getShipSpeed() + " Knots");
		lblSpeed.setBounds(325, 141, 274, 15);
		contentPane.add(lblSpeed);
		
		//Displays the defense of the ship
		JLabel lblDefense = new JLabel("Defense");
		lblDefense.setText(" -Defense is " +  String.format("%.2f", gameState.getShip().getShipDefence()) + "% of ships capabilities");
		lblDefense.setBounds(325, 168, 466, 15);
		contentPane.add(lblDefense);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Button for returning to the map frame
				dispose();
				GuiMap guiMap = new GuiMap(gameState);
				guiMap.setVisible(true);
			}
			
		});
		btnReturn.setBounds(674, 284, 117, 25);
		contentPane.add(btnReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 195, 466, 75);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Amount");
		
		shipUpgrades = gameState.getShip().getUpgrades();
		updateInventory(shipUpgrades, model);
		
		tblInventory = new JTable(model) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
			}
		};
		scrollPane.setViewportView(tblInventory);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(826, 321, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 826, 321);
		contentPane.add(lblBackground);
	}
}
