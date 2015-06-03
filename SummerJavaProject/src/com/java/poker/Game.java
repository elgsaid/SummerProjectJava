package com.java.poker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

	Scanner scan;
	Deck deck;
	Board board;
	int value1;
	int value2;
	// Player player;

	private HashMap<Integer, String> rules;

	/*
	 * Constructor
	 */
	public Game() {
		// initialize a game parameters
		scan = new Scanner(System.in);
		deck = new Deck();
		board = new Board();
		rules = new HashMap<Integer, String>();
		initialiseRules();
	}

	public void play() {

		System.out.println("Shuffle the Deck");
		deck.shuffle();
		deck.shuffle();
		// deck.shuffle();

		System.out.println("Give cards to the players");

		Player player2 = new Player("Swapnil");
		Player player1 = new Player("Abhishek");

		System.out.println("Give 1st cards to the player1 and player2 ..");
		//System.out.println();
		// deal the first cards to players
		player2.addCardToHand(deck.drawNextCard());
		player1.addCardToHand(deck.drawNextCard());

		System.out.println("Give 2nd cards to the player1 and player2 ..");
		System.out.println();
		
		// deal the second card to players
		player2.addCardToHand(deck.drawNextCard());
		player1.addCardToHand(deck.drawNextCard());
		
		// Add cards to the Board
		System.out.println("Add 3 cards on the Board/Community..");
		board.putCardsOnBoard(deck.drawNextCard());
		board.putCardsOnBoard(deck.drawNextCard());
		board.putCardsOnBoard(deck.drawNextCard());
		System.out.println(board.getCardsOnBoard().toString());
		
		System.out.println();
		System.out.println("Add 1 cards on the Board/Community..");
		board.putCardsOnBoard(deck.drawNextCard());
		
		System.out.println(board.getCardsOnBoard().toString());
		System.out.println("Add 1 cards on the Board/Community..");
		board.putCardsOnBoard(deck.drawNextCard());
		System.out.println(board.getCardsOnBoard().toString());
		System.out.println();
		value1 = player1.evaluateHand(board.getCardsOnBoard());
		value2 = player2.evaluateHand(board.getCardsOnBoard());

		if (value1 == value2) {
			
			System.out.println("Both the players have same hand....\n");
			
			System.out.println(player1.getName()+" has cards in hand");
			player1.printCardsInHand(false);
			
			System.out.println();
			
			System.out.println(player2.getName()+" has cards in hand");
			player2.printCardsInHand(false);
			System.out.println();
			
			switch(value1){
			
			case 1:
				if(player1.getPairValue()==player2.getPairValue()){
					System.out.println("both players have same High cards");
				}else if (player1.getPairValue()> player2.getPairValue()) {
					System.out.println("Winner is ...."+player1.getName() );
				}else{
					System.out.println("Winner is ...."+player2.getName() );
				}
				break;
			// Handle if both players have same pairs
			case 2:
				if(player1.getPairValue()==player2.getPairValue()){
					System.out.println("both players have same pairs");
				}else if (player1.getPairValue()> player2.getPairValue()) {
					System.out.println("Winner is ...."+player1.getName() );
				}else{
					System.out.println("Winner is ...."+player2.getName() );
				}
				break;
			case 3:
				if(player1.getPairValue()==player2.getPairValue()){
					System.out.println("both players have same two pairs");
				}else if (player1.getPairValue()> player2.getPairValue()) {
					System.out.println("Winner is ...."+player1.getName() );
				}else{
					System.out.println("Winner is ...."+player2.getName() );
				}
				break;
			case 4:
				if(player1.getPairValue()==player2.getPairValue()){
					System.out.println("both players have same triples");
				}else if (player1.getPairValue()> player2.getPairValue()) {
					System.out.println("Winner is ...."+player1.getName());
				}else{
					System.out.println("Winner is ...."+player2.getName());
				}
				break;
			}
			
			mPrintGameDetails(player1,player2);
			
		} else if (value1 > value2) {
			System.out.println(player1.getName() +" has cards in hand");
			player1.printCardsInHand(false);
			
			System.out.println();
			
			System.out.println(player2.getName() +" has cards in hand");
			player2.printCardsInHand(false);
			
			System.out.println();
			System.out.println("Winner is ...."+player1.getName());
			mPrintGameDetails(player1,player2);
		} else {
			
			System.out.println(player1.getName()+" has cards in hand");
			player1.printCardsInHand(false);
			
			System.out.println();
			
			System.out.println(player2.getName() +" has cards in hand");
			player2.printCardsInHand(false);
			System.out.println();
			System.out.println("Winner is ...."+player2.getName());
			mPrintGameDetails(player1,player2);
		}

	}

	public void initialiseRules() {
		rules.put(1, "high card");
		rules.put(2, "a pair");
		rules.put(3, "two pairs");
		rules.put(4, "triple");
		rules.put(5, "straight");
		rules.put(6, "flush");
		rules.put(7, "full house");
		rules.put(8, "four of a kind");
		rules.put(9, "straight flush");
		rules.put(10, "royal flush");
	}
	
	private void mPrintGameDetails(Player player1,Player player2){
		
		System.out.println(player1.getName() + " Cards.. "+ player1.getEvaluatedHand().toString());
		System.out.println(player1.getName() + " has.. " + rules.get(value1));
		System.out.println(player2.getName() + " Cards.."+ player2.getEvaluatedHand().toString());
		System.out.println(player2.getName() + " has.. " + rules.get(value2));
	}

}
