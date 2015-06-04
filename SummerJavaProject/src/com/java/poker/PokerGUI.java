package com.java.poker;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JTextField;

public class PokerGUI {

	private JFrame frame;
	Deck objDeck;
	
	JPanel panelDealer;
	JPanel panelCommunity;
	JPanel panelPlayer;
	
	Player dealer;
	Player player;
	Board board;
	
	JLabel lblUserName;
	JLabel lblDealerName;
	
	JButton btnUserBet;
	JButton btnUserCheck;
	private HashMap<Integer, String> rules;
	private JTextField txtDealerBet;
	private JTextField txtDealerRaise;
	private JTextField txtUsetBet;
	private JTextField txtUserRaise;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI window = new PokerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelDealer = new JPanel();
		panelDealer.setBounds(259, 42, 315, 110);
		frame.getContentPane().add(panelDealer);
		
		panelCommunity = new JPanel();
		panelCommunity.setBounds(82, 194, 670, 110);
		frame.getContentPane().add(panelCommunity);
		
		panelPlayer = new JPanel();
		panelPlayer.setBounds(259, 345, 315, 110);
		frame.getContentPane().add(panelPlayer);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserName.setBounds(75, 459, 77, 14);
		frame.getContentPane().add(lblUserName);
		
		btnUserBet = new JButton("Bet");
		btnUserBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUserBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserBet.setBounds(46, 484, 67, 23);
		frame.getContentPane().add(btnUserBet);
		
		JButton btnUserCheck = new JButton("Check");
		btnUserCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCheck.setBounds(227, 484, 83, 23);
		frame.getContentPane().add(btnUserCheck);
		
		JButton btnUserCall = new JButton("Call");
		btnUserCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCall.setBounds(328, 484, 83, 23);
		frame.getContentPane().add(btnUserCall);
		
		JButton btnUserRaise = new JButton("Raise");
		btnUserRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserRaise.setBounds(431, 484, 83, 23);
		frame.getContentPane().add(btnUserRaise);
		
		JButton btnUserFold = new JButton("Fold");
		btnUserFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserFold.setBounds(642, 484, 83, 23);
		frame.getContentPane().add(btnUserFold);
		
		JButton btnDealerBet = new JButton("Bet");
		btnDealerBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDealerBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerBet.setBounds(46, 11, 67, 23);
		frame.getContentPane().add(btnDealerBet);
		
		JButton btnDealerCheck = new JButton("Check");
		btnDealerCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCheck.setBounds(227, 11, 83, 23);
		frame.getContentPane().add(btnDealerCheck);
		
		JButton btnDealerCall = new JButton("Call");
		btnDealerCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCall.setBounds(328, 11, 83, 23);
		frame.getContentPane().add(btnDealerCall);
		
		JButton btnDealerRaise = new JButton("Raise");
		btnDealerRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerRaise.setBounds(431, 11, 83, 23);
		frame.getContentPane().add(btnDealerRaise);
		
		JButton btnDealerFold = new JButton("Fold");
		btnDealerFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerFold.setBounds(642, 11, 83, 23);
		frame.getContentPane().add(btnDealerFold);
		
		txtDealerBet = new JTextField();
		txtDealerBet.setBounds(123, 12, 86, 20);
		frame.getContentPane().add(txtDealerBet);
		txtDealerBet.setColumns(10);
		
		txtDealerRaise = new JTextField();
		txtDealerRaise.setBounds(530, 11, 86, 20);
		frame.getContentPane().add(txtDealerRaise);
		txtDealerRaise.setColumns(10);
		
		txtUsetBet = new JTextField();
		txtUsetBet.setBounds(123, 486, 86, 20);
		frame.getContentPane().add(txtUsetBet);
		txtUsetBet.setColumns(10);
		
		txtUserRaise = new JTextField();
		txtUserRaise.setBounds(530, 485, 86, 20);
		frame.getContentPane().add(txtUserRaise);
		txtUserRaise.setColumns(10);
		
		lblDealerName = new JLabel("Player 2");
		lblDealerName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDealerName.setBounds(75, 137, 77, 14);
		frame.getContentPane().add(lblDealerName);
		
		dealer = new Player("Player 1");
		player= new Player("Player 2");
		
		initializeGame();
	}
	
	public void initializeGame()
	{
		objDeck = new Deck();
		board = new Board();
		rules = new HashMap<Integer, String>();
		
		objDeck.shuffle();
		
		panelDealer.removeAll();
		panelPlayer.removeAll();
		panelCommunity.removeAll();
		
		panelPlayer.repaint();
		panelDealer.repaint();
		panelCommunity.repaint();
		
		// Deal the First Card
		dealer.addCardToHand(objDeck.drawNextCard());
		player.addCardToHand(objDeck.drawNextCard());

		// deal the second card
		dealer.addCardToHand(objDeck.drawNextCard());
		player.addCardToHand(objDeck.drawNextCard());

		// Show them on the JPanel

		// Get Dealer Card Images
		ImageIcon dCardImg1 = dealer.getCardsInHand().get(0).getImage(false);
		ImageIcon dCardImg2 = dealer.getCardsInHand().get(1).getImage(false);

		//dcard0 = new JLabel(dCardImg1);
		//dcard1 = new JLabel(dCardImg2);

		panelDealer.add(new JLabel(dCardImg1));
		panelDealer.add(new JLabel(dCardImg2));

		// Get Player Card Images
		ImageIcon pCardImg1 = player.getCardsInHand().get(0)
				.getImage(false);
		ImageIcon pCardImg2 = player.getCardsInHand().get(1)
				.getImage(false);
		
		panelPlayer.add(new JLabel(pCardImg1));
		panelPlayer.add(new JLabel(pCardImg2));
		
		// Add cards to Community Board
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		
		ImageIcon boardCardImg1 = board.getCardsOnBoard().get(0).getImage(true);
		ImageIcon boardCardImg2 = board.getCardsOnBoard().get(1).getImage(true);
		ImageIcon boardCardImg3 = board.getCardsOnBoard().get(2).getImage(true);
		ImageIcon boardCardImg4 = board.getCardsOnBoard().get(3).getImage(true);
		ImageIcon boardCardImg5 = board.getCardsOnBoard().get(4).getImage(true);
		
		panelCommunity.add(new JLabel(boardCardImg1));
		panelCommunity.add(new JLabel(boardCardImg2));
		panelCommunity.add(new JLabel(boardCardImg3));
		panelCommunity.add(new JLabel(boardCardImg4));
		panelCommunity.add(new JLabel(boardCardImg5));
		
		lblUserName.setText(player.getName());
		lblDealerName.setText(dealer.getName());
		
	}
}
