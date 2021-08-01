package GUI;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.*;

import game.GameEnvironment;
import game.Item;

import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
/**
* This class is for displaying the shop frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiShop extends JFrame {

	private JPanel contentPane;
	private JTable shopTable;
	private JComboBox cmbxItems;
	private JComboBox<Integer> cmbxAmount;
	private Object itemSelected;
	private JLabel lblNotEnoughMoney;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiShop frame = new GuiShop(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Adds rows to the table
	 * @param name
	 * @param description
	 * @param rarity
	 * @param price
	 * @param size
	 * @param stock
	 */
	public void addRow(String name, String description, String rarity, String price, String size, String stock) {
		DefaultTableModel yourModel = (DefaultTableModel) shopTable.getModel();
		yourModel.addRow(new Object[]{name, description, rarity, price, size, stock});
	}
	/**
	 * Stores the rows that need to be added
	 * @param itemFive 
	 * @param itemFour 
	 * @param itemThree 
	 * @param itemTwo 
	 * @param itemOne 
	 */
	public void rows(Item itemOne, Item itemTwo, Item itemThree, Item itemFour, Item itemFive) {
		// Calls method addRow() to add the rows of data to the table 
		
		addRow(itemOne.getItemName(),
				itemOne.getItemDescription(),
				itemOne.getItemRarity(),
				String.valueOf(itemOne.getItemPrice()),
				String.valueOf(itemOne.getIitemSize()),
				String.valueOf(itemOne.getItemMaxBuy()));
				
		addRow(itemTwo.getItemName(),
				itemTwo.getItemDescription(),
				itemTwo.getItemRarity(),
				String.valueOf(itemTwo.getItemPrice()),
				String.valueOf(itemTwo.getIitemSize()),
				String.valueOf(itemTwo.getItemMaxBuy()));
				
		addRow(itemThree.getItemName(),
				itemThree.getItemDescription(),
				itemThree.getItemRarity(),
				String.valueOf(itemThree.getItemPrice()),
				String.valueOf(itemTwo.getIitemSize()),
				String.valueOf(itemThree.getItemMaxBuy()));
				
		addRow(itemFour.getItemName(),
				itemFour.getItemDescription(),
				itemFour.getItemRarity(),
				String.valueOf(itemFour.getItemPrice()),
				String.valueOf(itemTwo.getIitemSize()),
				String.valueOf(itemFour.getItemMaxBuy()));
				
		addRow(itemFive.getItemName(),
				itemFive.getItemDescription(),
				itemFive.getItemRarity(),
				String.valueOf(itemFive.getItemPrice()),
				String.valueOf(itemTwo.getIitemSize()),
				String.valueOf(itemFive.getItemMaxBuy()));
	}
	/**
	 * Adds items to the comboBox
	 * @param stockAmount
	 */
	public void addComboItems(int stockAmount) {
		for(int i = 1; i <= stockAmount; i++) {
			cmbxAmount.addItem(i);
		}
	}
	/**
	 * Create the frame.
	 * @param gameState 
	 */

	@SuppressWarnings("unchecked")
	public GuiShop(GameEnvironment gameState) {
		setTitle(gameState.getIsland().getIslandName() + " Shop");
		setResizable(false);
		setBounds(100, 100, 1033, 351);
		
		Item itemOne = gameState.getIsland().getSellItemOne();
		Item itemTwo = gameState.getIsland().getSellItemTwo();
		Item itemThree = gameState.getIsland().getSellItemThree();
		Item itemFour = gameState.getIsland().getSellItemFour();
		Item itemFive = gameState.getIsland().getSellItemFive();

		String[] columnNames = { "Name", "Description", "Rarity", "Price", "Size"};
		String[][] data = {};
		
		//setTitle("Shop");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 34, 1009, 116);
		
		shopTable = new JTable(data, columnNames) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
			}
		};
		shopTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Description", "Rarity", "Price", "Size", "Stock"
			}
		));
		// Call rows()
		rows(itemOne, itemTwo, itemThree, itemFour, itemFive);
		scrollPane.setViewportView(shopTable);
		
		// Sets the size of each column in the table
		shopTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		shopTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		shopTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		shopTable.getColumnModel().getColumn(3).setPreferredWidth(5);
		shopTable.getColumnModel().getColumn(4).setPreferredWidth(5);
		
		JLabel lblItemYouWant = new JLabel("Item you want to buy");
		lblItemYouWant.setBounds(106, 172, 163, 15);
		contentPane.add(lblItemYouWant);
		contentPane.add(scrollPane);
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(393, 172, 70, 15);
		contentPane.add(lblAmount);
		
		// Combo box for selecting the amount of the item you want to buy 
		cmbxAmount = new JComboBox();
		cmbxAmount.setBounds(366, 214, 117, 24);
		contentPane.add(cmbxAmount);
		
		// Combo box for displaying all the items you can buy from that islands shop
		cmbxItems = new JComboBox();
		for(int i = 0; i <= itemOne.getItemMaxBuy(); i++) {
			cmbxAmount.addItem(i);
		}
		cmbxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// When the items combo box is changed it will update the amount you can buy for that item
				itemSelected = cmbxItems.getSelectedItem();
				cmbxAmount.removeAllItems();
				
				
				if (itemSelected == itemOne.getItemName()) {
					for(int i = 0; i <= itemOne.getItemMaxBuy(); i++) {
						cmbxAmount.addItem(i);
					}
				} else if (itemSelected == itemTwo.getItemName()) {
					for(int i = 0; i <= itemTwo.getItemMaxBuy(); i++) {
						cmbxAmount.addItem(i);
					}
				} else if (itemSelected == itemThree.getItemName()) {
					for(int i = 0; i <= itemThree.getItemMaxBuy(); i++) {
						cmbxAmount.addItem(i);
					}
				} else if (itemSelected == itemFour.getItemName()) {
					for(int i = 0; i <= itemFour.getItemMaxBuy(); i++) {
						cmbxAmount.addItem(i);
					}
				} else if (itemSelected == itemFive.getItemName()) {
					for(int i = 0; i <= itemFive.getItemMaxBuy(); i++) {
						cmbxAmount.addItem(i);
					}
				}
			}
		});
		
		JLabel lblBalance = new JLabel("You have $" + gameState.getPlayer().getMoney());
		lblBalance.setBounds(864, 172, 157, 15);
		contentPane.add(lblBalance);
		
		//Adds all the item names to the cmbxItems 
		cmbxItems.setModel(new DefaultComboBoxModel(new String[] {
				itemOne.getItemName(), 
				itemTwo.getItemName(), 
				itemThree.getItemName(), 
				itemFour.getItemName(), 
				itemFive.getItemName()}));
		
		cmbxItems.setBounds(44, 214, 280, 24);
		contentPane.add(cmbxItems);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Buy button 
				
				int item = cmbxItems.getSelectedIndex() + 1;
				int amount = cmbxAmount.getSelectedIndex(); 
				int itemNum = (gameState.getItem(item).getItemMaxBuy()) - amount; 
				boolean outOfStock = true;
				String message = "";
				
				// Checks the amount you want to buy and selected item is above 0
				if (amount > 0 && itemNum >= 0) {
					if(gameState.getIsland().getIslandName().equals("Serpent Island")) {
						message = gameState.setUpgradeOrder(item, amount);
					}else {
						message = gameState.setPurchaseOrder(item, amount);
					}
					lblBalance.setText("You have $" + gameState.getPlayer().getMoney());
				}
				else if (amount == 0 && itemNum >= 0) {
					outOfStock = false;
					JOptionPane.showMessageDialog(null,"You have bought nothing");
				}
				shopTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Name", "Description", "Rarity", "Price", "Size", "Stock"
						}
					));
				//Updates the shop with the correct stock amount
				rows(itemOne, itemTwo, itemThree, itemFour, itemFive);	
				if (message != "") {
					JOptionPane.showMessageDialog(null,message);
				} else if (outOfStock){
					JOptionPane.showMessageDialog(null,"Out of stock");
				}
			}
		});
		JButton btnGoBack = new JButton("Return");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Closes the window and re-opens the map
				gameState.endClauseCheck();
				if(gameState.getStatus() != 0) {
					GuiMap guiMap = new GuiMap(gameState);
					guiMap.setVisible(true);
					dispose();
				} else {
					dispose();
					GuiEndGame guiEndGame = new GuiEndGame(gameState);
					guiEndGame.setVisible(true);
				}
			}
			
		});
		btnGoBack.setBounds(904, 261, 117, 25);
		btnBuy.setBounds(366, 261, 117, 25);
		
		contentPane.add(btnBuy);
		contentPane.add(btnGoBack);
		
		lblNotEnoughMoney = new JLabel("");
		lblNotEnoughMoney.setBounds(376, 261, 163, 15);
		contentPane.add(lblNotEnoughMoney);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(1033, 327, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 1033, 327);
		contentPane.add(lblBackground);
	}
}
