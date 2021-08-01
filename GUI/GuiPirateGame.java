package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import game.Game;
import game.GameEnvironment;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
* This class is for displaying the pirate game frame
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class GuiPirateGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPirateGame frame = new GuiPirateGame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param gameState 
	 */
	public GuiPirateGame(GameEnvironment gameState) {
		setTitle("Pirates!");
		setResizable(false);
		
		boolean returnState;
		setBounds(100, 100, 717, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWinnerMsg = new JLabel("");
		lblWinnerMsg.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWinnerMsg.setForeground(Color.CYAN);
		String contents = gameState.getEvent().getWinner();
		String[] lines = contents.split("\\r?\\n");
		lblWinnerMsg.setText(lines[0]);
		lblWinnerMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinnerMsg.setBounds(39, 416, 643, 15);
		contentPane.add(lblWinnerMsg);
		
		JLabel lblWinnerMsg2 = new JLabel("");
		lblWinnerMsg2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWinnerMsg2.setForeground(Color.CYAN);
		lblWinnerMsg2.setText(lines[1]);
		lblWinnerMsg2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinnerMsg2.setBounds(39, 443, 643, 15);
		contentPane.add(lblWinnerMsg2);
		
		JLabel lblWinnerMsg3 = new JLabel("");
		lblWinnerMsg3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWinnerMsg3.setForeground(Color.CYAN);
		lblWinnerMsg3.setText(lines[2]);
		lblWinnerMsg3.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinnerMsg3.setBounds(39, 470, 643, 15);
		contentPane.add(lblWinnerMsg3);
		
		JLabel lblInfoOne = new JLabel("Pirates!");
		lblInfoOne.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInfoOne.setForeground(Color.CYAN);
		lblInfoOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoOne.setBounds(50, 12, 632, 15);
		contentPane.add(lblInfoOne);
		
		JLabel lblInfoTwo = new JLabel("you and your crew were attacked by pirates");
		lblInfoTwo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInfoTwo.setForeground(Color.CYAN);
		lblInfoTwo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoTwo.setBounds(50, 36, 632, 15);
		contentPane.add(lblInfoTwo);
		
		JLabel lblInfoThree = new JLabel("A dice rolling game was played, two dices were rolled randomly.");
		lblInfoThree.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInfoThree.setForeground(Color.CYAN);
		lblInfoThree.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoThree.setBounds(39, 63, 643, 15);
		contentPane.add(lblInfoThree);
		
		JLabel lblInfoFour = new JLabel("The ship with the highest deffense gets to roll an addtional dice.");
		lblInfoFour.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInfoFour.setForeground(Color.CYAN);
		lblInfoFour.setBounds(50, 90, 643, 15);
		contentPane.add(lblInfoFour);
		
		JLabel lblPirate = new JLabel("");
		lblPirate.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pirate.jpg")).getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH)));
		lblPirate.setBounds(380, 100, 302, 277);
		contentPane.add(lblPirate);
		
		JLabel lblPlayer = new JLabel("");
		lblPlayer.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/player.jpg")).getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH)));
		lblPlayer.setBounds(39, 98, 302, 271);
		contentPane.add(lblPlayer);
		
		JLabel lblPlayerMsg = new JLabel("");
		lblPlayerMsg.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPlayerMsg.setForeground(Color.CYAN);
		lblPlayerMsg.setText(gameState.getEvent().getMessages().get(0));
		lblPlayerMsg.setBounds(39, 389, 302, 15);
		contentPane.add(lblPlayerMsg);
		
		JLabel lblPirateMsg = new JLabel("");
		lblPirateMsg.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPirateMsg.setForeground(Color.CYAN);
		lblPirateMsg.setText(gameState.getEvent().getMessages().get(1));
		lblPirateMsg.setBounds(380, 389, 302, 15);
		contentPane.add(lblPirateMsg);
		
		JButton btnReturn = new JButton("New button");
		
		// Checks if the player can still play
		// If the player cant play anymore it will set the button text to game over 
		// Else it will display return (which will return the player to map frame)
		if (gameState.getStatus() == 100) {
			btnReturn.setText("Return");
			returnState = true;
		} else {
			btnReturn.setText("Game Over!");
			returnState = false;
		}
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				if (returnState) {
					GuiMap guiMap = new GuiMap(gameState);
					guiMap.setVisible(true);
				}
			}
		});
		btnReturn.setBounds(588, 497, 117, 25);
		contentPane.add(btnReturn);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setForeground(Color.CYAN);
		lblBackground.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pirateGameBG.jpg")).getImage().getScaledInstance(717, 536, Image.SCALE_SMOOTH)));
		lblBackground.setBounds(0, 0, 717, 536);
		contentPane.add(lblBackground);
	}
}
