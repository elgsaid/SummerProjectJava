package com.java.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	private String name;
	
	private ArrayList<Card> hand;
	
	private static int numOfCards=0;
	
	// this to get the current state of the player
 
	public char playersCurrentState = ' ';
	
	public Player(String name)
	{
		this.name = name;
		this.emptyHand();
		hand = new ArrayList<Card>();
	}
	
	// Added this function
	public String getName()
	{
		return this.name;
	}
	/*
	 * This method is used when we start with new game we required to 
	 * empty players hand
	 */
	public void emptyHand()
	{
		for(int i=0;i<10;i++)
			hand=null;
		
		this.numOfCards=0;
	}
	
	public ArrayList<Card> getCardsInHand(){
		return this.hand;
	}
	public boolean addCardToHand(Card c)
	{
		if(this.numOfCards==10)
		{
			System.out.printf("%s has already have 10 cards in his hand", this.name);
			System.exit(1);
		}
		
		hand.add(c);
		numOfCards++;
		
		return (this.getHandSum()<=21);
		
	}
	
	//returns the sum in players hand
	public int getHandSum()
	{
		int sumOfHand=0;
		int numofAces=0;
		
		for (Card c: hand)
		{
			if(c.getCardValue()==1) //this is ace
			{
				numofAces++;
				sumOfHand = sumOfHand + 11;
			}
			else if(c.getCardValue()>10) //these are face values
			{
				sumOfHand = sumOfHand+10;
			}
			else // these are normal caards
			{
				sumOfHand = sumOfHand + c.getCardValue();
			}
			
			// recalculate the sum till its less than 21
			while(sumOfHand>21 && numofAces>0)
			{
				sumOfHand= sumOfHand -10;
				numofAces--;
			}
			
		}
			
		
		return sumOfHand;
	}
	
	//print cards in players hands
	public void printCardsInHand(boolean hideFirstCard)
	{
		int i =0;
		for(Card c : hand)
		{
			
			if(hideFirstCard && i==0)
			{
				System.out.println("[Dealer's hidden card]");
				i++;
			}
			else
			{
				System.out.println(c.toString());
				i++;
			}
				
		}
	}
	
	public boolean isDonePlaying(Game game)
	{
		boolean playerDone = false;
		
		Scanner reader = new Scanner(System.in);
		
		//while (playerDone == false) {

			if (playerDone == false) {
				System.out.println(this.name+" Do you want select NEXT CARD or Do you want to STAY ? (Enter N or S): ");
				playersCurrentState = reader.next().charAt(0);

				// If player selects NEXT CARD then add next card to hand of
				// player
				// check for busted
				if (playersCurrentState == 'N' || playersCurrentState == 'n') {

					// this function returns true when card sum is < 21
					if (this.addCardToHand(game.objDeck.drawNextCard())) {
						playerDone = false;
						System.out.print (this.name +"--Printing cards of player...\n");
						this.printCardsInHand(false);
					} else {
						// player is busted
						System.out.println(this.name + " Player is busted .. Dealer wins...");
						playerDone = true;
						//System.exit(1);
					}
				} else if (playersCurrentState == 'S' || playersCurrentState == 's') {
					// player is done over here
					playerDone = true;
				}
				
				
			//}

		}

		return playerDone;	
	}
}

