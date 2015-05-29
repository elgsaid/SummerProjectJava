package com.java.blackjack;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BlackjackGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel topPanel = new JPanel();
	JPanel dcardPanel = new JPanel();
	JPanel pcardPanel = new JPanel();
	JTextPane winlosebox = new JTextPane();
	JButton hitbutton = new JButton();
	JButton dealbutton = new JButton();
	JButton staybutton = new JButton();
	JButton playagainbutton = new JButton();
	JLabel dealerlabel = new JLabel();
	JLabel playerlabel = new JLabel();

	// declare the variables for the game
	private Player dealer;
	private Player player;
	public Deck objDeck;

	JLabel playercard1;
	JLabel playercard2;
	JLabel playercardhit;
	JLabel dealercard0;
	JLabel dealercard2;
	JLabel dealercard1;
	JLabel dealercardhit;

	public BlackjackGUI() {

		topPanel.setBackground(new Color(0, 122, 0));
		dcardPanel.setBackground(new Color(0, 122, 0));
		pcardPanel.setBackground(new Color(0, 122, 0));

		topPanel.setLayout(new FlowLayout());
		winlosebox.setText(" ");
		winlosebox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));
		dealbutton.setText("  Deal");
		dealbutton.addActionListener(new dealbutton());
		hitbutton.setText("  Hit");
		hitbutton.addActionListener(new hitbutton());
		hitbutton.setEnabled(false);
		staybutton.setText("  Stay");
		staybutton.addActionListener(new staybutton());
		staybutton.setEnabled(false);
		playagainbutton.setText("  Play Again");
		playagainbutton.addActionListener(new playagainbutton());
		playagainbutton.setEnabled(false);

		dealerlabel.setText("  Dealer:  ");
		playerlabel.setText("  Player:  ");

		topPanel.add(winlosebox);
		topPanel.add(dealbutton);
		topPanel.add(hitbutton);
		topPanel.add(staybutton);
		topPanel.add(playagainbutton);
		pcardPanel.add(playerlabel);
		dcardPanel.add(dealerlabel);

		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(dcardPanel, BorderLayout.CENTER);
		add(pcardPanel, BorderLayout.SOUTH);

		// / will initialize the starting elements for the Game

		dealer = new Player("Dealer");
		player = new Player("Swapnil");
		objDeck = new Deck();

	}

	public void display() {
		JFrame myFrame = new JFrame("Blackjack");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setContentPane(this);
		myFrame.setPreferredSize(new Dimension(700, 550));

		// Display the window.
		myFrame.pack();
		myFrame.setVisible(true);

	}

	class dealbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dcardPanel.add(dealerlabel);
			pcardPanel.add(playerlabel);

			/**
			 * Get's dealer and player cards from Hand and the image associated
			 * with that random card and puts them on the screen.
			 */

			dealercard0 = new JLabel(new ImageIcon("back.png"));

			// game.dealInitialCards();
			objDeck.shuffle();

			dealer.addCardToHand(objDeck.drawNextCard());
			player.addCardToHand(objDeck.drawNextCard());

			// deal the second card
			dealer.addCardToHand(objDeck.drawNextCard());
			player.addCardToHand(objDeck.drawNextCard());

			// to iterate set and get current dealer cards
			Card dcard = null;
			Iterator<Card> dscan = (dealer.getCardsInHand()).iterator();
			int count = 0;
			while (dscan.hasNext()) {
				dcard = dscan.next();
				if (count == 0)
					dealercard1 = new JLabel(dcard.getImage());
				else
					dealercard2 = new JLabel(dcard.getImage());

				count++;
			}

			// to iterate set and get current player cards
			Iterator<Card> pscan = (player.getCardsInHand()).iterator();
			count = 0;
			while (pscan.hasNext()) {
				Card pcard = pscan.next();
				if (count == 0)
					playercard1 = new JLabel(pcard.getImage());
				else
					playercard2 = new JLabel(pcard.getImage());

				count++;
			}

			dcardPanel.add(dealercard0);
			dcardPanel.add(dealercard2);

			pcardPanel.add(playercard1);
			pcardPanel.add(playercard2);

			dealerlabel.setText("  Dealer:  " + dcard.getCardValue());
			playerlabel.setText("  Player:  " + player.getHandSum());

			hitbutton.setEnabled(true);
			staybutton.setEnabled(true);
			dealbutton.setEnabled(false);

			
			 if(player.getHandSum()==21 || dealer.getHandSum()==21) { 
			 hitbutton.setEnabled(false);
			 staybutton.setEnabled(false); dealbutton.setEnabled(false);
			 playagainbutton.setEnabled(true);
			 winlosebox.setText("BlackJack"); }
			 

			add(dcardPanel, BorderLayout.CENTER);
			add(pcardPanel, BorderLayout.SOUTH);

		}
	}

	class hitbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Card hitcard = objDeck.drawNextCard();
			playercardhit = new JLabel(hitcard.getImage());
			pcardPanel.add(playercardhit);
			pcardPanel.repaint();
			player.addCardToHand(hitcard);

			if (player.getHandSum() > 21) {
				winlosebox.setText("Player is Busted");
				hitbutton.setEnabled(false);
				dealbutton.setEnabled(false);
				staybutton.setEnabled(false);
				playagainbutton.setEnabled(true);
			}

			playerlabel.setText("  Player:   " + player.getHandSum());

		}
	}// end hitbutton

	/*
 * 
 */
	class staybutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dcardPanel.remove(dealercard0);
			dcardPanel.add(dealercard1);

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

			// dealer = game.dealerPlays();
			dcardPanel.removeAll();
			dcardPanel.add(dealerlabel);
			dealerlabel.setText(" " + dealerlabel.getText());

			// iterate through cards and re-display
			Card dhitcard = null;
			Iterator<Card> scan = (dealer.getCardsInHand()).iterator();
			while (scan.hasNext()) {
				dhitcard = scan.next();
				dealercardhit = new JLabel(dhitcard.getImage());
				dcardPanel.add(dealercardhit);
			}

			dealerlabel.setText("Dealer: " + dealer.getHandSum());
			playerlabel.setText("Player: " + player.getHandSum());

			if (player.getHandSum() > dealer.getHandSum()
					&& player.getHandSum() <= 21 || dealer.getHandSum() > 21) {
				// System.out.println("\n Player wins!! Hurray !!");
				winlosebox.setText("Player wins!! Hurray !!");

			} else if (player.getHandSum() == dealer.getHandSum()) {
				// System.out.println("\n Equal points-Game draw");
				winlosebox.setText("Equal points-Game draw");
			} else {
				winlosebox.setText("Dealer wins !!");
			}

			// winlosebox.setText(game.winner());
			hitbutton.setEnabled(false);
			staybutton.setEnabled(false);

			playagainbutton.setEnabled(true);

		}
	}

/*
 * 
 */
	class playagainbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dealerlabel.setText("Dealer: ");
			playerlabel.setText("Player: ");
			winlosebox.setText("Play again");
			dealer = new Player("Dealer");
			player = new Player("Swapnil");

			dcardPanel.removeAll();
			pcardPanel.removeAll();

			hitbutton.setEnabled(false);
			staybutton.setEnabled(false);
			playagainbutton.setEnabled(false);
			dealbutton.setEnabled(true);

		}
	}
}