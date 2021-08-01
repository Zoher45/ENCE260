package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameEnvironment;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
/**
* This class is for displaying the about end game frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiEndGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiEndGame frame = new GuiEndGame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 *	Gets the players score 
	 * @param gameState 
	 */
	public int checkScore(GameEnvironment gameState) {
		int score = (int) (gameState.getPlayer().getMoney() - gameState.getStartingMoney()) / (gameState.getPlayer().getDaysChosen() - gameState.getPlayer().getDaysRemaining());
		if(score < 0) {
			score = 0;
		}
		return score;
	}

	/**
	 * Create the frame.
	 * @param gameState 
	 */
	public GuiEndGame(GameEnvironment gameState) {
		setTitle("Game Over");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Labels for displaying all the information needed. 
		JLabel lblEndMessage = new JLabel(gameState.getEndMessage());
		lblEndMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndMessage.setForeground(Color.CYAN);
		lblEndMessage.setBounds(12, 93, 566, 15);
		contentPane.add(lblEndMessage);
		
		JLabel lblPlayerName = new JLabel("Player - " + gameState.getPlayer().getName());
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setForeground(Color.CYAN);
		lblPlayerName.setBounds(12, 12, 566, 15);
		contentPane.add(lblPlayerName);
		
		JLabel lblTotalDays = new JLabel("Total day survived:  " + (gameState.getPlayer().getDaysChosen() - gameState.getPlayer().getDaysRemaining()));
		lblTotalDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalDays.setForeground(Color.CYAN);
		lblTotalDays.setBounds(12, 291, 566, 15);
		contentPane.add(lblTotalDays);
		
		JLabel lblPlayerShip = new JLabel("Player's Ship: " + gameState.getShip().getShipName());
		lblPlayerShip.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerShip.setForeground(Color.CYAN);
		lblPlayerShip.setBounds(12, 264, 566, 15);
		contentPane.add(lblPlayerShip);
		
		
		int score = checkScore(gameState);
		JLabel lblPlayerScore = new JLabel("Score : " + score);
		lblPlayerScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerScore.setForeground(Color.CYAN);
		lblPlayerScore.setBounds(12, 66, 566, 15);
		contentPane.add(lblPlayerScore);
		
		int profit = gameState.getPlayer().getMoney() - gameState.getStartingMoney();
		JLabel lblProfit = new JLabel("");
		lblProfit.setText("Profit made $" + profit);
		lblProfit.setForeground(Color.CYAN);
		lblProfit.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfit.setBounds(12, 39, 566, 15);
		contentPane.add(lblProfit);
		
		JLabel lblDaysPicked = new JLabel("");
		lblDaysPicked.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaysPicked.setText("Days selected: " + gameState.getPlayer().getDaysChosen());
		lblDaysPicked.setForeground(Color.CYAN);
		lblDaysPicked.setBounds(12, 318, 566, 15);
		contentPane.add(lblDaysPicked);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GameOver.png")).getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 600, 376);
		contentPane.add(lblBackground);
	}
}
