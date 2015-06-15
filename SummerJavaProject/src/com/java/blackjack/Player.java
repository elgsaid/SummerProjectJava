package com.java.blackjack;


import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	private String name;
	
	private ArrayList<Card> hand;
	
	private static int numOfCards=0;
	private int playerMoney;
	
	
	// this to get the current state of the player
 
	public char playersCurrentState = ' ';
	
	public Player(String name)
	{
		this.name = name;
		this.playerMoney=200;
		this.emptyHand();
		hand = new ArrayList<Card>();
	}
	
	// Added this function
	public String getName()
	{
		return this.name;
	}
	
	public int getPlayerMoney() {
		return playerMoney;
	}

	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	/*
	 * This method is used when we start with new game we required to 
	 * empty players hand
	 */
	public void emptyHand()
	{
		for(int i=0;i<10;i++){
			hand=null;
			hand = new ArrayList<Card>();
		}
		//this(this.name);
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
	

}

