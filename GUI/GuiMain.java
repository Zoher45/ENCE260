package GUI;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
/**
* This class is for displaying the main frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiMain {

	private JFrame frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain window = new GuiMain();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setResizable(false);
		frmMain.setTitle("Welcome");
		frmMain.setBounds(100, 100, 500, 500);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		//Button to exit the game
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMain.dispose();
			}
		});
		btnNewButton.setBounds(371, 417, 117, 25);
		frmMain.getContentPane().add(btnNewButton);
		
		//Button to start the game
		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMain.dispose();
				PlayerCreation plyrInfo = new PlayerCreation();
				plyrInfo.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(12, 417, 117, 25);
		frmMain.getContentPane().add(btnNewButton_1);
		
		// Button for display the about game frame 
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMain.dispose();
				GuiAbout guiAbout = new GuiAbout();
				guiAbout.setVisible(true);
			}
		});
		btnAbout.setBounds(191, 417, 117, 25);
		frmMain.getContentPane().add(btnAbout);
		
		JLabel lblSusTraders = new JLabel("Sus Traders");
		lblSusTraders.setForeground(Color.CYAN);
		lblSusTraders.setFont(new Font("Century Schoolbook L", Font.BOLD, 48));
		lblSusTraders.setHorizontalAlignment(SwingConstants.CENTER);
		lblSusTraders.setBounds(12, 41, 476, 97);
		frmMain.getContentPane().add(lblSusTraders);
		
		JLabel lblMainBackground = new JLabel("");
		lblMainBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Main.jpg")).getImage().getScaledInstance(500, 476, Image.SCALE_SMOOTH)));
		lblMainBackground.setBounds(0, 0, 500, 476);
		frmMain.getContentPane().add(lblMainBackground);
	}
}
