package GUI;

import java.awt.BorderLayout;

import game.CommandLine;
import game.GameEnvironment;
import game.CharacterException;
import game.LengthException;
import game.GameEnvironment;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
* This class is for displaying the entering the players name frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class PlayerCreation extends JFrame {
	public String name;
	private JPanel contentPane;
	private JTextField txtName;
	private game.Player newPlayer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerCreation frame = new PlayerCreation();
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
	public PlayerCreation() {
		setResizable(false);
		newPlayer = new game.Player("", 0, 2000);
		
		setTitle("Player Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTemp = new JLabel("");
		lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemp.setBounds(12, 64, 571, 33);
		contentPane.add(lblTemp);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(266, 8, 46, 15);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Checks the username the user has entered through the key press
					String userName = txtName.getText();
					String nameChecker = nameChecker(userName);
					
					if (nameChecker == "success") {
						name = userName;
						dispose();
						GuiPickDays pickDays = new GuiPickDays(userName);
						pickDays.setVisible(true);
					}
					else if (nameChecker == "1") {
						lblTemp.setText("Please make sure to have have username between 3 - 15 characters");
						
					}
					else if (nameChecker == "2") {
						lblTemp.setText("Please only use letters for the username");
					}
					txtName.setText("");
				}
			}
		});
		txtName.setBounds(207, 33, 165, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Checks the username the user has entered through enter button
				String userName = txtName.getText();
				String nameChecker = nameChecker(userName);
				
				if (nameChecker == "success") {
					name = userName;
					dispose();
					GuiPickDays pickDays = new GuiPickDays(userName);
					pickDays.setVisible(true);
				}
				else if (nameChecker == "1") {
					lblTemp.setText("Please make sure to have have username between 3 - 15 characters");
					
				}
				else if (nameChecker == "2") {
					lblTemp.setText("Please only use letters for the username");
				}
				txtName.setText("");
			}
		});
		btnEnter.setBounds(207, 109, 165, 25);
		contentPane.add(btnEnter);
		
		JLabel label = new JLabel("");
		label.setBounds(34, 82, 165, 15);
		contentPane.add(label);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/woodBackground.jpg")).getImage().getScaledInstance(595, 153, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 595, 153);
		contentPane.add(lblBackground);
	}
	/**
	 * Checks the users inputed name
	 * @param userName
	 */
	public String nameChecker(String userName) {
		boolean found = true;
		try {
			if (userName.length() < 3  | userName.length() > 15) {
				found = false;
				return "1";
			}

			for(char c : userName.toCharArray()) {
				if (!Character.isLetter(c)) {
					found = false;
					return "2";
				}
			}
				
		}catch(LengthException e) {
			return(e.getMessage() + "\n");
			
		}catch(CharacterException e) {
			return(e.getMessage() + "\n");
			
		}finally{
			if (found == true) {
				return "success";
				}
			}
			
		
		return userName;
	}
}
