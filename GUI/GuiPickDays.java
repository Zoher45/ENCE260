package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameEnvironment;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
* This class is for displaying the picking the amount of days wanting to play frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiPickDays extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPickDays frame = new GuiPickDays(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userName 
	 */
	public GuiPickDays(String userName) {
		setResizable(false);
		setTitle("Choose numbers of days");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Slider for picking amount of days wanting to played
		JSlider sldDayPicker = new JSlider(20,50,35);
		sldDayPicker.setBounds(55, 70, 707, 48);
		sldDayPicker.setMajorTickSpacing(1);  
		sldDayPicker.setPaintTicks(true);
		sldDayPicker.setPaintLabels(true); 
		contentPane.add(sldDayPicker);
		
		JLabel lblPleasePickAmount = new JLabel("Please choose amount of days ");
		lblPleasePickAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleasePickAmount.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPleasePickAmount.setBounds(55, 30, 707, 28);
		contentPane.add(lblPleasePickAmount);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				//Closes the current window
				dispose();
				GuiShips guiShips = new GuiShips(userName, sldDayPicker.getValue());
				//Opens the next window which the picking ship
				guiShips.setVisible(true);
			}
		});
		btnConfirm.setBounds(351, 151, 124, 28);
		contentPane.add(btnConfirm);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(299, 3, 244, 15);
		lblWelcome.setText("Welcome " + userName);
		contentPane.add(lblWelcome);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Returns to the player name frame
				dispose();
				PlayerCreation playerCreation = new PlayerCreation();
				playerCreation.setVisible(true);
			}
		});
		btnReturn.setBounds(682, 153, 117, 25);
		contentPane.add(btnReturn);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(811, 202, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 3, 811, 202);
		contentPane.add(lblBackground);
	}
}
