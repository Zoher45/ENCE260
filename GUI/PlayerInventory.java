package GUI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.GameEnvironment;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
* This class is for displaying the players inventory frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class PlayerInventory extends JFrame {

	private JPanel contentPane;
	private JTable tblInventory;
	private JComboBox cmbxItem;
	private JComboBox cmbxAmount;
	private String[] buyingCollection;
	private ArrayList<List<Object>> shipInventory;
	private ArrayList<String> itemNames;
	private JLabel lblTotalPrice;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInventory frame = new PlayerInventory(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Updates the inventory table
	 * @param shipInventory 
	 * @param model 
	 */
	public void updateInventory(ArrayList<List<Object>> shipInventory, DefaultTableModel model) {
		model.setRowCount(0);
		// Goes through each row in the ship inventory and adds each row the table
		for (int i = 0; i < shipInventory.size(); i++) {
			List<Object> each = shipInventory.get(i);
			model.addRow(new Object[]{each.get(0), each.get(1), each.get(2), ((int) each.get(2)/ (int) each.get(5)), each.get(3), each.get(4)});
		}
	}
	/**
	 * Sets sell order 
	 * @param itemNumber
	 * @param gameState
	 * @param gottenValue
	 * @param sellState
	 */
	public void sellOrder(int itemNumber, GameEnvironment gameState, int gottenValue, boolean sellState) {		
		List <Object> currentPlayerSellItem = gameState.getSpecificStorageItem(itemNumber);
		
		float percentage = (float) currentPlayerSellItem.get(1)  * (float) (gameState.getIsland().getIslandPercentage()) / 100;
		int sellPrice = (int) percentage + (int)(float) currentPlayerSellItem.get(1);
		
		// Checks if the player can sell
		if (sellState && (int)currentPlayerSellItem.get(2) >= gottenValue) {
			gameState.setSellOrder(itemNumber, gottenValue, sellPrice);
			if (gottenValue > 0) {
				JOptionPane.showMessageDialog(null,"Transaction approved");
			}
		}
		// Else it will display the item is out of stock 
		else if((int)currentPlayerSellItem.get(2) == 0 && sellState) {
			JOptionPane.showMessageDialog(null,"Out of stock");
		}
		// Updates the total price label
		lblTotalPrice.setText("Total Price  $ " + sellPrice * gottenValue);
		
	}
	/**
	 * Populates the amounts combo box 
	 * @param itemSelected
	 */
	public void amountsAdd(String itemSelected) {
		//Removes all the items from the combo box
		cmbxAmount.removeAllItems();
		//Adds back what is needed to the combo box
		
		//Loops through each row in inventory table 
		for(int i = 0; i<tblInventory.getRowCount();i++) {
			int amount = 0;
			//Checks if the item they selected that the shop is buying that they have it in their inventory
			// If the item matches, the stock level combo box will fill up to the max amount the player can sell 
			if (itemSelected == tblInventory.getValueAt(i, 0)) {
				//Amount is the how many of that item the player has in their inventory 
				amount = (int) tblInventory.getValueAt(i, 3);
				for (int i2 = 0; i2 < amount+1; i2++) {
					cmbxAmount.addItem(i2);
				}
			}
		}
	}
	
	/**
	 * Create the frame.
	 * @param gameState 
	 */
	public PlayerInventory(GameEnvironment gameState) {
		setResizable(false);
		setBounds(100, 100, 566, 403);
		
		setTitle("Inventory");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 36, 503, 219);
		contentPane.add(scrollPane);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Price");
		model.addColumn("Storage");
		model.addColumn("Qty");
		model.addColumn("Origin");
		model.addColumn("Total");
		
		// Adds items to the Jtable
		shipInventory =  gameState.getShip().getCurrentAllItems();
		updateInventory(shipInventory, model);
		
		//Disables table editing
		tblInventory = new JTable(model) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
			}
		};
		//Sets the table column widths
		tblInventory.getColumnModel().getColumn(0).setPreferredWidth(150);
		tblInventory.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblInventory.getColumnModel().getColumn(2).setPreferredWidth(70);
		tblInventory.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblInventory.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblInventory.getColumnModel().getColumn(5).setPreferredWidth(50);
		scrollPane.setViewportView(tblInventory);
		
		JLabel lblSellItems = new JLabel("Sell Item/s");
		lblSellItems.setBounds(28, 267, 83, 15);
		contentPane.add(lblSellItems);
	
		cmbxItem = new JComboBox();
			
		cmbxItem.addActionListener(new ActionListener() {;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Checks for each item selected in the items combo box and updates how much the player can sell if they have that item in their inventory
				String itemSelected = (String) cmbxItem.getSelectedItem();
				amountsAdd(itemSelected);
				lblTotalPrice.setText("Total price   $0");
			}
			
		});
		
		gameState.setPlayerItems();
		
		ArrayList<int[]> playerCanSell = gameState.getPlayerCanSell();
		ArrayList<List<Object>> shipStorage = gameState.getShip().getCurrentAllItems();
		
		String[] buyingCollection = new String[playerCanSell.size()];
		


		for(int i = 0; i < playerCanSell.size(); i++) {
			int[] each = playerCanSell.get(i);
			buyingCollection[i] = (String) shipStorage.get(each[0]).get(0);
		}
		
		cmbxItem.setModel(new DefaultComboBoxModel(buyingCollection));
		cmbxItem.setBounds(28, 294, 126, 24);
		contentPane.add(cmbxItem);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(186, 267, 70, 15);
		contentPane.add(lblAmount);
		
		cmbxAmount = new JComboBox();
		cmbxAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = cmbxItem.getSelectedIndex();
				sellOrder(index + 6 ,gameState, cmbxAmount.getSelectedIndex(), false);
			}
		});
		cmbxAmount.setBounds(196, 294, 49, 24);
		contentPane.add(cmbxAmount);
		
		JButton btnClose = new JButton("Return");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Return to the map
				gameState.checkForEmpty();
				GuiMap guiMap = new GuiMap(gameState);
				guiMap.setVisible(true);
				dispose();
			}
		});
		btnClose.setBounds(437, 342, 117, 25);
		contentPane.add(btnClose);

		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gameState.getShip().getCurrentAllItems().size() > 0) {
					// Button to sell the selected item
					int index = cmbxItem.getSelectedIndex();
					if (cmbxAmount.getSelectedIndex() != -1) {
						sellOrder(index + 6,gameState,cmbxAmount.getSelectedIndex(), true);
						updateInventory(shipInventory, model);
					} 
				} else {
					JOptionPane.showMessageDialog(null,"Out of stock");
				}
			}
		});
		btnSell.setBounds(253, 342, 117, 25);
		contentPane.add(btnSell);
		
		lblTotalPrice = new JLabel("Total price   $0");
		lblTotalPrice.setBounds(263, 299, 156, 15);
		contentPane.add(lblTotalPrice);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(566, 379, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 566, 379);
		contentPane.add(lblBackground);
		amountsAdd((String) cmbxItem.getItemAt(0));
	}
}
