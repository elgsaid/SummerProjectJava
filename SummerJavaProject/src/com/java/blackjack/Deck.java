package com.java.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {

	// This is Array of cards
	private ArrayList<Card> cardsInDeck;
	//private Card[] cardsInDeck;

	// This variables currently present number of Cards in a Deck
	// Initially when we create a deck we have 52 cards in a deck
	private static int NoOfCardsInDeck = 0;

	public Deck() {
		cardsInDeck = new ArrayList<Card>();
		// cardsInDeck = new Card[52];
		// for each suit
		for (int i = 0; i < 4; i++) {
			// add 13 cards into deck
			for (int j = 1; j <= 13; j++) {
				// values() returns the array
				cardsInDeck.add(new Card(Suit.values()[i], j));
			}
		}
	
	}

	public void shuffle()
	{
		shuffleArray(cardsInDeck);
	}
	/*
	 * This method will shuffle the deck for us for each new game we will
	 * shuffle the deck Algorithm used for shuffling is  Fisher–Yates shuffle
	 */
	private void shuffleArray(ArrayList<Card> ar) {
		Random rnd = new Random();
		for (int i = ar.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Collections.swap(ar, index, i);
		}
	}
	
	
	public Card drawNextCard()
	{
		return cardsInDeck.remove(0);
	}
	
	/*
	 * This will print the currently available cards of the deck
	 * For debugging purpose we print it
	 */
	public String toString()
	{
		for(Card cards: this.cardsInDeck)
			System.out.print(cards.getCardValue()+", ");
		
		return null;
	}
	
	

}
