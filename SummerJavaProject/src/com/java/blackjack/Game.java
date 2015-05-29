package com.java.blackjack;

import java.util.List;
import java.util.Scanner;


public class Game {

	// Declare Players
	private Player dealer;
	private List<Player> players;
	public Deck objDeck;
	
	private int count =0;
	
	public Game(Player dealer, List<Player> player)
	{
		this.dealer = dealer;
		this.players = player;
		objDeck = new Deck();
	}
	
	public void StartGame()
	{
		char ans = 0 ;
		Scanner reader = new Scanner(System.in);
		
		do {

			
			objDeck.shuffle();
			
			//initialize the game
			// give 2 cards to each player and to dealer
			for(int i =0;i< 2;i++)
			{
				for(Player p :players)
				{
					p.addCardToHand(objDeck.drawNextCard());
				}
				
				dealer.addCardToHand(objDeck.drawNextCard());
			}
			
			// Print initial hands for players and Dealer
			
			for(Player p: players)
			{
				System.out.print("--Printing initial cards of "+p.getName()+" player...\n");
				p.printCardsInHand(false);
			}
			
			System.out.println("\n");
			
			System.out.print("--Printing initial cards of Dealer's \n");
			dealer.printCardsInHand(true);
			System.out.println();
			
			// after initialize starting drawing the cards from deck 
			// fill the players hand
			// find out how we will do it
			
			while(true)
			{
				if(this.count == players.size())
					break;
				
				for(Player p :players)
				{
					// check every player is done or not
					//passing player game reference to get the 
					// Deck of card object
					if(p.isDonePlaying(this))
					{
						this.count++;
					}
					
					
				}
				
			}
			
			// create a dealer and player
			/*Player dealer = new Player("Dealer");
			Player player1 = new Player("Player1");

			// deal the first cards
			dealer.addCardToHand(objDeck.drawNextCard());
			player1.addCardToHand(objDeck.drawNextCard());

			// deal the second card
			dealer.addCardToHand(objDeck.drawNextCard());
			player1.addCardToHand(objDeck.drawNextCard());

			// print the cards in hand
			System.out.print("--Printing initial cards of dealer..." + "\n");
			dealer.printCardsInHand(true);
			System.out.println();

			System.out.print("--Printing initial cards of player...\n");
			player1.printCardsInHand(false);

			boolean dealerDone = false;
			boolean playerDone = false;*/
			

			// now check for player NEXT CARD or STAND
			// loop through till one of them is done
			/*while (playerDone == false) {

				if (playerDone == false) {
					System.out.println("Do you want select NEXT CARD or Do you want to STAY ? (Enter N or S): ");
					char ip = reader.next().charAt(0);

					// If player selects NEXT CARD then add next card to hand of
					// player
					// check for busted
					if (ip == 'N' || ip == 'n') {

						// this function returns true when card sum is < 21
						if (player1.addCardToHand(objDeck.drawNextCard())) {
							playerDone = false;
							System.out.print("--Printing cards of player...\n");
							player1.printCardsInHand(false);
						} else {
							// player is busted
							System.out.println("\n Player is busted .. Dealer wins...");
							playerDone = true;
							System.exit(1);
						}
					} else if (ip == 'S' || ip == 's') {
						// player is done over here
						playerDone = true;
					}
				}

			}*/

			//reader.close();

			// Now we are finished with players card we need to select the
			// dealers cards
			
			boolean dealerDone = false;
			
			while (dealerDone == false) {
				// dealer draws next card till his card sum is less than 17
				if (dealer.getHandSum() < 17) {
					// Draw the card
					// This function returns true when card sum is < 21
					if (dealer.addCardToHand(objDeck.drawNextCard())) {
						// dealerDone=false;
						System.out.println();
						dealer.printCardsInHand(true);
					} else {
						System.out.println("Dealer Stay's \n");
						dealer.printCardsInHand(true);
					}
				} else {
					dealerDone = true;
				}
			}

			System.out.println("Now printing the final cards on the system \n");

			// print the cards in hand
		/*	System.out.println("\n --Printing Players card");
			player1.printCardsInHand(false);

			System.out.println("\n --Printing Dealers card");
			dealer.printCardsInHand(false);*/
			//
			// player wins if his sum is greater than dealer's sum and less than 21 or dealer's sum is greater than 21
			// 
			
			// check for each player with dealer whether he is winning against dealer or not
			
			for(Player p : players)
			{
				if (p.getHandSum() > dealer.getHandSum()
						&& (p.getHandSum() <= 21 || dealer.getHandSum() > 21)) {
					
					System.out.println(p.getName() + " Player wins!! Hurray !!");

				} else if (p.getHandSum() == dealer.getHandSum()) {
					System.out.println("\n Equal points-Game draw");
					
				} else {
					System.out.println("\n Dealer wins " + p.getName() + " looses");
				}
				
			}
			
			System.out.println();
			System.out.println("Do you want to continue ?(Y/N)");
			ans= reader.next().charAt(0);
			
		} while (ans == 'Y' || ans == 'y');

		reader.close();
	}
	
}
