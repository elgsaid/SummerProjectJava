package com.java.blackjack;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import com.java.casinoserver.*;

public class BlackJackGUI {

	private JFrame frame;
	Player dealer;
	Player player;
	Deck objDeck;
	JPanel panelDealer;
	JPanel panelPlayer;

	JLabel pcard1;
	JLabel pcard2;
	JLabel dcard0;
	JLabel dcard2;
	JLabel dcard1;
	JLabel lblPlayerScore;
	JLabel lblDealerScore;
	JLabel lblMoney;
	JLabel lblTotal;
	JLabel lblBet;
	
	JButton btnDeal;
	JButton btnStay;
	JButton btnNewCard;
	private JLabel lblGameStatus;

	private final int betAmount =10;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGUI window = new BlackJackGUI();
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
	public BlackJackGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Variables for Game

		frame = new JFrame("BlackJack");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					LoginDetails.UserBalance = String.valueOf(player.getPlayerMoney());
					Client.UpdateUser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 778, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnDeal = new JButton("Deal");
		btnDeal.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDeal.setBackground(Color.WHITE);
		btnDeal.addActionListener(new DealButton_Click());
		panel.add(btnDeal);

		btnNewCard = new JButton("New Card");
		btnNewCard.setFont(new Font("Verdana", Font.BOLD, 12));
		btnNewCard.setBackground(Color.WHITE);
		btnNewCard.addActionListener(new NewCardButton_Click());
		panel.add(btnNewCard);

		btnStay = new JButton("Stay");
		btnStay.setFont(new Font("Verdana", Font.BOLD, 12));
		btnStay.setBackground(Color.WHITE);
		btnStay.addActionListener(new StayButton_Click());
		panel.add(btnStay);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setBounds(10, 370, 97, 25);
		lblPlayer.setForeground(new Color(204, 0, 0));
		lblPlayer.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblPlayer);

		lblMoney = new JLabel("Money:");
		lblMoney.setBounds(10, 403, 55, 25);
		lblMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoney.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_1.add(lblMoney);

		lblBet = new JLabel("Bet:");
		lblBet.setBounds(10, 433, 55, 25);
		lblBet.setHorizontalAlignment(SwingConstants.LEFT);
		lblBet.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_1.add(lblBet);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 461, 55, 25);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_1.add(lblTotal);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		panelDealer = new JPanel();
		panelDealer.setBounds(31, 26, 669, 109);
		panel_2.add(panelDealer);

		panelPlayer = new JPanel();
		panelPlayer.setBounds(31, 226, 669, 109);
		panel_2.add(panelPlayer);

		lblPlayerScore = new JLabel("Player:");
		lblPlayerScore.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPlayerScore.setBounds(31, 354, 139, 14);
		panel_2.add(lblPlayerScore);

		lblDealerScore = new JLabel("Dealer:");
		lblDealerScore.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDealerScore.setBounds(31, 146, 139, 14);
		panel_2.add(lblDealerScore);

		// / Create Players
		dealer = new Player("Dealer");
		player = new Player(LoginDetails.UserName);
		player.setPlayerMoney(Integer.parseInt(LoginDetails.UserBalance));
		

		lblPlayer.setText(player.getName());
		lblPlayerScore.setText(player.getName() + ":");
		
		lblGameStatus = new JLabel("Game Status:");
		lblGameStatus.setFont(new Font("Verdana", Font.BOLD, 12));
		lblGameStatus.setBounds(491, 354, 261, 14);
		panel_2.add(lblGameStatus);
		
		enableDisableButton(GameState.NewGame);
		lblMoney.setText("Money: "+player.getPlayerMoney());
		lblTotal.setText("Total: ");
		lblBet.setText("Bet: "+betAmount);
	}

	class DealButton_Click implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(!mCheckPlayerAmountGreaterThanZero()){
				JOptionPane.showMessageDialog(null, "Not Sufficient Money to play the Game");
				return;
			}
			
			objDeck = new Deck();
			objDeck.shuffle();
			
			panelDealer.removeAll();
			panelPlayer.removeAll();
			panelPlayer.repaint();
			panelDealer.repaint();
			
			if(dealer.getHandSum()>0)
			dealer.emptyHand();
			
			if(player.getHandSum()>0)
			player.emptyHand();
			
			// Deal the First Card
			dealer.addCardToHand(objDeck.drawNextCard());
			player.addCardToHand(objDeck.drawNextCard());

			// deal the second card
			dealer.addCardToHand(objDeck.drawNextCard());
			player.addCardToHand(objDeck.drawNextCard());


			// Get Dealer Card Images
			ImageIcon dCardImg1 = dealer.getCardsInHand().get(0)
					.getImage(false);
			ImageIcon dCardImg2 = dealer.getCardsInHand().get(1).getImage(true);

			//dcard0 = new JLabel(dCardImg1);
			dcard1 = new JLabel(dCardImg2);

			panelDealer.add(new JLabel(dCardImg1));
			panelDealer.add(new JLabel(dCardImg2));

			// Get Player Card Images
			ImageIcon pCardImg1 = player.getCardsInHand().get(0)
					.getImage(false);
			ImageIcon pCardImg2 = player.getCardsInHand().get(1)
					.getImage(false);
			
			panelPlayer.repaint();
			panelPlayer.add(new JLabel(pCardImg1));
			panelPlayer.add(new JLabel(pCardImg2));

			lblDealerScore.setText("  Dealer:  "+ dealer.getCardsInHand().get(0).getCardValue());
			lblPlayerScore.setText("  " + player.getName() + ":  "+ player.getHandSum());
			
			enableDisableButton(GameState.Deal);
			// Check That if Player wins
			if (player.getHandSum() == 21 ) {
				enableDisableButton(GameState.NewGame);
				lblGameStatus.setText("BlackJack!! "+player.getName() + " wins");
				mCalculatePlayerAmount(PlayerState.Win);
				lblMoney.setText("Money: "+ player.getPlayerMoney());

			}else if(dealer.getHandSum() == 21){
				enableDisableButton(GameState.NewGame);
				lblGameStatus.setText("BlackJack!! Dealer wins");
				mCalculatePlayerAmount(PlayerState.Loose);
				lblMoney.setText("Money: "+ player.getPlayerMoney());
			}
		}
	}

	class NewCardButton_Click implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			Card nextCard = objDeck.drawNextCard();
			ImageIcon nextCardImg = nextCard.getImage(false);
			panelPlayer.add(new JLabel(nextCardImg));
			panelPlayer.repaint();
			player.addCardToHand(nextCard);

			if (player.getHandSum() > 21) {
				enableDisableButton(GameState.NewGame);	
				lblGameStatus.setText("Game Status: Player is Busted");	
				mCalculatePlayerAmount(PlayerState.Busted);
				lblMoney.setText("Money: "+ player.getPlayerMoney());
			}
			
			lblPlayerScore.setText(player.getName()+": " +player.getHandSum());
		}
	}

	class StayButton_Click implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			panelDealer.remove(dcard1);
			panelDealer.add(new JLabel(dealer.getCardsInHand().get(1).getImage(false)));

			boolean dealerDone = false;

			while (dealerDone == false) {
				// dealer draws next card till his card sum is less than 17
				if (dealer.getHandSum() < 17) {
					// Draw the card
					// This function returns true when card sum is < 21
					if (dealer.addCardToHand(objDeck.drawNextCard())) {
						// dealerDone=false;
						// System.out.println();
						// dealer.printCardsInHand(true);
					} else {
						// System.out.println("Dealer Stay's \n");
						// dealer.printCardsInHand(true);
					}
				} else {
					dealerDone = true;
				}
			}

			panelDealer.removeAll();

			Card dhitcard = null;
			Iterator<Card> scan = (dealer.getCardsInHand()).iterator();
			JLabel dealercardhit =null;
			while (scan.hasNext()) {
				dhitcard = scan.next();
				dealercardhit = new JLabel(dhitcard.getImage(false));
				panelDealer.add(dealercardhit);
			}

			lblDealerScore.setText("Dealer: " + dealer.getHandSum());
			lblPlayerScore.setText(player.getName() +" "+ player.getHandSum());

			if (player.getHandSum() > dealer.getHandSum()
					&& player.getHandSum() <= 21 || dealer.getHandSum() > 21) {
				// System.out.println("\n Player wins!! Hurray !!");
				enableDisableButton(GameState.NewGame);
				lblGameStatus.setText("Game Status: Player wins!! Hurray !!");
				mCalculatePlayerAmount(PlayerState.Win);
				lblMoney.setText("Money: "+ player.getPlayerMoney());
				
			} else if (player.getHandSum() == dealer.getHandSum()) {
				// System.out.println("\n Equal points-Game draw");
				enableDisableButton(GameState.NewGame);
				lblGameStatus.setText("Game Status: Equal points-Game draw");
			} else {
				enableDisableButton(GameState.NewGame);
				lblGameStatus.setText("Game Status:Dealer wins !!");
				mCalculatePlayerAmount(PlayerState.Loose);
				lblMoney.setText("Money: "+ player.getPlayerMoney());
			}

			
		}
	}

	public void enableDisableButton(GameState gs) {
		if (gs == GameState.NewGame) {
			btnDeal.setEnabled(true);
			btnStay.setEnabled(false);
			btnNewCard.setEnabled(false);
		} else if (gs == GameState.Stay) {
			btnDeal.setEnabled(false);
			btnStay.setEnabled(true);
			btnNewCard.setEnabled(true);
		} else if (gs == GameState.Deal) {
			btnDeal.setEnabled(false);
			btnStay.setEnabled(true);
			btnNewCard.setEnabled(true);
		}
		
		lblGameStatus.setText("Game Status: ");
	}

	public boolean mCheckPlayerAmountGreaterThanZero(){
		
		if(player.getPlayerMoney()<=0)
		{
			return false;
		}else{
			return true;
		}
	}
	
	public void mCalculatePlayerAmount(PlayerState ps)
	{
		if(ps == PlayerState.Win)
		{
			player.setPlayerMoney(player.getPlayerMoney() + betAmount);
		}else if(ps == PlayerState.Loose || ps == PlayerState.Busted){
			player.setPlayerMoney(player.getPlayerMoney() - betAmount);
		}
	}
	
	enum GameState {
		NewGame, Stay, Deal
	}
	
	enum PlayerState{
		Win, Loose, Busted
	}
}
