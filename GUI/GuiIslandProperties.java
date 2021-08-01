package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameEnvironment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
* This class is for displaying the about island properties frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiIslandProperties extends JFrame {

	private JPanel contentPane;
	private JLabel lblIslandPic; 
	
	// All the labels needed to display all the island properties
	private JLabel lblSellItemOne = new JLabel("Tuna Crate ($87)");
	private JLabel lblSellItemTwo = new JLabel("Catfish Crate ($47)");
	private JLabel lblSellItemThree = new JLabel("Rabbit Crate ($45)");
	private JLabel lblSellItemFour = new JLabel("Gold Bar ($5250)");
	private JLabel lblSellItemFive  = new JLabel("Silver Bar ($3500)");
	
	private JLabel lblBuyItemOne = new JLabel("Banana Crate ($42)");
	private JLabel lblBuyItemTwo = new JLabel("Coconut Crate ($33)");
	private JLabel lblBuyItemThree = new JLabel("Feijoa Crate ($36)");
	private JLabel lblBuyItemFour  = new JLabel("Mango Crate ($41)");
	private JLabel lblBuyItemFive = new JLabel("Pineapple Crate ($39)");
	
	private JLabel lblIslandSell = new JLabel("This island has rich soil and are main exporter of fresh produce ");;
	private JLabel lblIslandBuy  = new JLabel("This island generally buys meats and precious metal");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiIslandProperties frame = new GuiIslandProperties(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Sets the island picture
	 * @param islandPictureLocation 
	 */
	public void setIslandPicture(String islandPictureLocation) {
		lblIslandPic.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(islandPictureLocation)).getImage().getScaledInstance(222, 222, Image.SCALE_SMOOTH)));
	}
	/**
	 * Updates all the island labels
	 * @param islandProperties 
	 */
	public void updateAllLabels(String[] islandProperties) {
		// Sets all the labels with the correct island properties. 
		lblIslandBuy.setText(islandProperties[1]);
		lblIslandSell.setText(islandProperties[2]);
		
		lblBuyItemOne.setText(islandProperties[4]);
		lblBuyItemTwo.setText(islandProperties[5]);
		lblBuyItemThree.setText(islandProperties[6]);
		lblBuyItemFour.setText(islandProperties[7]);
		lblBuyItemFive.setText(islandProperties[8]);
		
		lblSellItemOne.setText(islandProperties[10]);
		lblSellItemTwo.setText(islandProperties[11]);
		lblSellItemThree.setText(islandProperties[12]);
		lblSellItemFour.setText(islandProperties[13]);
		lblSellItemFive.setText(islandProperties[14]);
	}
	/**
	 * Updates the island contents
	 * @param islandName
	 * @param gameState 
	 */
	public void updateIslandContents(String islandName, GameEnvironment gameState) {
		// Checks which island the player has picked and sets the island properties
		if(islandName.equals("Sus Island")) {
			setIslandPicture("/img/Sus Island.jpg");
			updateAllLabels(gameState.getSusIsland().getIslandProperties());
		} else if(islandName.equals("Skull Island")) {
			setIslandPicture("/img/Skull Island.jpg");
			updateAllLabels(gameState.getSkullIsland().getIslandProperties());
		} else if(islandName.equals("Serpent Island")) {
			setIslandPicture("/img/Serpent Island.jpg");
			updateAllLabels(gameState.getSerpentIsland().getIslandProperties());
		} else if(islandName.equals("Treasure Island")){
			setIslandPicture("/img/Treasure Island.jpg");
			updateAllLabels(gameState.getTreasureIsland().getIslandProperties());
		} else {
			setIslandPicture("/img/Pirate Island.jpg");
			updateAllLabels(gameState.getPirateIsland().getIslandProperties());
		}
	}
	

	/**
	 * Create the frame.
	 * @param gameState 
	 */
	public GuiIslandProperties(GameEnvironment gameState) {
		
		setTitle("Island Properties");
		setResizable(false);
		setBounds(100, 100, 696, 418);
		
		// Gets the island properties 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Display picture for the island
		lblIslandPic = new JLabel("");
		String islandName = "Sus Island";
		updateIslandContents(islandName, gameState);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiMap guiMap = new GuiMap(gameState);
				guiMap.setVisible(true);
			}
		});
		btnReturn.setBounds(536, 351, 117, 25);
		contentPane.add(btnReturn);
		lblIslandPic.setBounds(30, 49, 222, 222);
		contentPane.add(lblIslandPic);
		
		// Label for the islands property #1
		lblIslandSell.setHorizontalAlignment(SwingConstants.CENTER);
		lblIslandSell.setBounds(30, 297, 654, 15);
		contentPane.add(lblIslandSell);
		
		// Label for the islands property #2
		lblIslandBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblIslandBuy.setBounds(30, 324, 654, 15);
		contentPane.add(lblIslandBuy);
		
	
		JLabel lblThisIslandBuys = new JLabel("Selling Items ($ per item) ");
		lblThisIslandBuys.setHorizontalAlignment(SwingConstants.LEFT);
		lblThisIslandBuys.setBounds(279, 96, 188, 15);
		contentPane.add(lblThisIslandBuys);
		
		lblBuyItemOne.setBounds(279, 123, 174, 15);
		contentPane.add(lblBuyItemOne);
		
		lblBuyItemTwo.setBounds(279, 150, 180, 15);
		contentPane.add(lblBuyItemTwo);
		
		lblBuyItemThree.setBounds(279, 177, 174, 15);
		contentPane.add(lblBuyItemThree);
		
		lblBuyItemFour.setBounds(279, 204, 180, 15);
		contentPane.add(lblBuyItemFour);
		
		lblBuyItemFive.setBounds(279, 231, 174, 15);
		contentPane.add(lblBuyItemFive);
		
		JLabel lblBuyThisIslandSells = new JLabel("Buying Items ($ per item) ");
		lblBuyThisIslandSells.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuyThisIslandSells.setBounds(479, 96, 205, 15);
		contentPane.add(lblBuyThisIslandSells);
		
		lblSellItemOne.setBounds(489, 123, 174, 15);
		contentPane.add(lblSellItemOne);
		
		lblSellItemTwo.setBounds(489, 150, 180, 15);
		contentPane.add(lblSellItemTwo);
		
		lblSellItemThree.setBounds(489, 177, 174, 15);
		contentPane.add(lblSellItemThree);
		
		lblSellItemFour.setBounds(489, 204, 180, 15);
		contentPane.add(lblSellItemFour);
		
		lblSellItemFive.setBounds(489, 231, 174, 15);
		contentPane.add(lblSellItemFive);
		
	
		JComboBox cmbxIsland = new JComboBox();
		cmbxIsland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Updates the island properties when the player changes a option in the combo box 
				updateIslandContents((String)cmbxIsland.getSelectedItem(), gameState);
			}
		});
		cmbxIsland.setModel(new DefaultComboBoxModel(new String[] {"Sus Island", "Pirate Island ", "Treasure Island", "Skull Island", "Serpent Island"}));
		cmbxIsland.setBounds(418, 49, 235, 24);
		contentPane.add(cmbxIsland);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(696, 458, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 696, 458);
		contentPane.add(lblBackground);
		
	}
}
