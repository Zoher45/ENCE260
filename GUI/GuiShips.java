package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameEnvironment;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
* This class is for displaying the picking which ship the player wants frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiShips extends JFrame {

	private JPanel contentPane;
	
	private JLabel lblCrewSize;
	private JLabel lblStorage;
	private JLabel lblSpeed;
	private JLabel lblDefense;
	private int shipPicked = 1;
	private GameEnvironment gameState; 
	private JButton btnReturn;
	private JLabel lblShipPicture;
	private JLabel lblBackground;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiShips frame = new GuiShips("",0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Label Setter for each ship
	 * @param crewSize
	 * @param storage
	 * @param speed
	 * @param defense 
	 * @param pictureLocation
	 */
	public void setLabels(String crewSize, String storage, String speed, String defense, String pictureLocation) {
		//Sets each label with the relevant information for each ship
		lblCrewSize.setText(crewSize);
		lblStorage.setText(storage);
		lblSpeed.setText(speed);
		lblDefense.setText(defense);
		lblShipPicture.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(pictureLocation)).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
	}

	/**
	 * Create the frame.
	 * @param daysPicked
	 * @param userName 
	 */
	public GuiShips(String userName, int daysPicked) {
		setTitle("Pick Ship");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Combo box to display all the playable ships
		JComboBox cmbxShips = new JComboBox();
		cmbxShips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object itemSelected = cmbxShips.getSelectedItem();
				//Checks which ship was selected from the combobox and passes in the info about each ship to set onto the labels
				if (itemSelected == "Black Pearl") {
					shipPicked = 1;
					setLabels("-Crew members: 17 (max amount is 24)",
							"-Storage: 200 units",
							"-Speed factor: 18 knots",
							"-Starting defense is 45% of ships capabilities",
							"/img/blackPearl.jpg");
					
				} else if (itemSelected == "Flying Nimbus") {
					shipPicked = 2;
					setLabels("-Crew members: 9 (max amount is 15)",
							"-Storage: 120 units",
							"-Speed factor: 22 knots",
							"-Starting defense is 50% of ships capabilities",
							"/img/flyingNimbus.jpg");
				} else if (itemSelected == "Miss Fortune") {
					shipPicked = 3;
					setLabels("-Crew members: 20 (max amount is 28)",
							"-Storage: 260 units",
							"-Speed factor: 15 knots",
							"-Starting defense is 41% of ships capabilities",
							"/img/missFortune.jpg");
				} else if (itemSelected == "Jolly Roger") {
					shipPicked = 4;
					setLabels("-Crew members: 27 (max amount is 35)",
							"-Storage: 340 units",
							"-Speed factor: 10 knots",
							"-Starting defense is 38% of ships capabilities",
							"/img/jollyRoger.jpg");
				}
			}
		});
		
		lblShipPicture = new JLabel("");
		lblShipPicture.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/blackPearl.jpg")).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
		lblShipPicture.setBounds(43, 12, 250, 250);
		contentPane.add(lblShipPicture);
		cmbxShips.setModel(new DefaultComboBoxModel(new String[] {"Black Pearl", "Flying Nimbus", "Miss Fortune", "Jolly Roger"}));
		cmbxShips.setBounds(421, 45, 149, 24);
		contentPane.add(cmbxShips);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Closes current window
				dispose();
				
				//Opens next window which is the map
				gameState = new GameEnvironment(userName,daysPicked,shipPicked);
				GuiMap guiMap = new GuiMap(gameState);
				guiMap.setVisible(true);
			}
		});
		btnConfirm.setBounds(394, 239, 117, 25);
		contentPane.add(btnConfirm);
		
		lblCrewSize = new JLabel("-Crew members: 17 (max amount is 24)");
		lblCrewSize.setBounds(320, 81, 298, 15);
		contentPane.add(lblCrewSize);
		
		lblStorage = new JLabel("-Storage: 200 units");
		lblStorage.setBounds(320, 108, 298, 15);
		contentPane.add(lblStorage);
		
		lblDefense = new JLabel("-Starting defense is 45% of ships capabilities");
		lblDefense.setBounds(320, 162, 339, 15);
		contentPane.add(lblDefense);
		
		lblSpeed = new JLabel("-Speed factor: 18 knots");
		lblSpeed.setBounds(320, 135, 298, 15);
		contentPane.add(lblSpeed);
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Returns to the previous frame
				dispose();
				GuiPickDays guiPickDays = new GuiPickDays(userName);
				guiPickDays.setVisible(true);
			}
		});
		btnReturn.setBounds(542, 239, 117, 25);
		contentPane.add(btnReturn);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(671, 276, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 671, 276);
		contentPane.add(lblBackground);
	}
}
