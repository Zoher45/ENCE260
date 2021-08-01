package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
/**
* This class is for displaying the about game frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiAbout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAbout frame = new GuiAbout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiAbout() {
		setResizable(false);
		setTitle("About game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Labels for about the game 
		JLabel lblAboutOne = new JLabel("The main goal of this game is to make as much profit as you can!");
		lblAboutOne.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutOne.setBounds(12, 22, 647, 15);
		contentPane.add(lblAboutOne);
		
		JLabel lblAboutTwo = new JLabel("There are five Islands, each with unique advantages and disadvantages");
		lblAboutTwo.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutTwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutTwo.setBounds(12, 49, 647, 15);
		contentPane.add(lblAboutTwo);
		
		JLabel lblAboutThree = new JLabel("You can travel to each island but there is a chance of an event to occur!");
		lblAboutThree.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutThree.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutThree.setBounds(12, 76, 647, 15);
		contentPane.add(lblAboutThree);
		
		JLabel lblAboutFour = new JLabel("As you progress through the game make sure to upgrade to help you survive!");
		lblAboutFour.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutFour.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutFour.setBounds(12, 103, 647, 15);
		contentPane.add(lblAboutFour);
		
		JLabel lblAboutFive = new JLabel("While traveling or buying keep an eye on your maintenance cost ");
		lblAboutFive.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutFive.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutFive.setBounds(12, 130, 647, 15);
		contentPane.add(lblAboutFive);
		
		JLabel lblAboutSix = new JLabel("and the money you have remaining");
		lblAboutSix.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutSix.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutSix.setBounds(12, 157, 647, 15);
		contentPane.add(lblAboutSix);
		
		JLabel lblAboutSeven = new JLabel("If you don't have enough days left or enough money to travel, the game will end");
		lblAboutSeven.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutSeven.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutSeven.setBounds(12, 184, 647, 15);
		contentPane.add(lblAboutSeven);
		
		JLabel lblAboutEight = new JLabel("Good luck on your journey!");
		lblAboutEight.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblAboutEight.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutEight.setBounds(12, 211, 647, 15);
		contentPane.add(lblAboutEight);
		
		// Button for returning to the main menu
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GuiMain guiMain = new GuiMain();
				guiMain.main(null);
			}
		});
		btnReturn.setBounds(282, 238, 117, 25);
		contentPane.add(btnReturn);
		
		// Background label
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/About Game.jpg")).getImage().getScaledInstance(681, 277, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 681, 277);
		contentPane.add(lblBackground);
	}
}
