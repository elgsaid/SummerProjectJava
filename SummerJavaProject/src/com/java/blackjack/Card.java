package com.java.blackjack;

import javax.swing.ImageIcon;

/* An implementation of Card type
 * @author: Swapnil Aher
 * @since: 3/15/2015
 * */
public class Card {

	//private variables
	private int cardValue;
	private Suit suit;
	
	public Card(Suit suit, int cardValue)
	{
		this.suit=suit;
		if(cardValue>=1 && cardValue<=13)
		{
			this.cardValue = cardValue;	
		}
		else
		{
			System.out.println("Invalid Card value exception");
			//throw invalid card value exception
			//Need to create this Exception class
		}
	}
	
	public int getCardValue()
	{
		return cardValue;
	}
	
  
	/* 
	 * Print card value and suit type which it belongs
	 * */
   public String toString()
   {
	   if(cardValue >=2 && cardValue <=10)
		  return cardValue  + " of "+ suit.toString();
	   
	   if(cardValue ==1)
		  return "Ace of " + suit.toString();
	   
	   
	   if(cardValue ==11)
		   return "Jack of " + suit.toString();
	   
	   if(cardValue ==12)
		   return "Queen of " + suit.toString();
	   
	   if(cardValue ==13)
		   return "King of " + suit.toString();
	   
	   return null;
   }
   
   public ImageIcon getImage(){
	   String workingDir = System.getProperty("user.dir");
	   
	   ImageIcon image1 = new ImageIcon(workingDir+"\\images\\"+this.getCardValue()+this.suit.toString().toCharArray()[0]+".png");
	   return image1;
   }
}
