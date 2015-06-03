package com.java.poker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Player {

	private String name;

	private ArrayList<Card> hand;
	private ArrayList<Card> evaluatedHand;
	private static int numOfCards = 0;
	private int pairValue=0;
	
	private int[][] combinationsForBoardCards = { { 0, 1, 2 }, { 0, 1, 3 },
			{ 0, 1, 4 }, { 0, 2, 4 }, { 0, 2, 4 }, { 0, 3, 4 }, { 1, 2, 3 },
			{ 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

	public Player(String name) {
		this.name = name;
		this.emptyHand();
		hand = new ArrayList<Card>();
		evaluatedHand = new ArrayList<Card>();
	}

	// Added this function
	public String getName() {
		return this.name;
	}
	
	/* This method is used when we start with new game we required to empty
	 * players hand
	 * */

	private void emptyHand() {

		if (hand != null)
			hand.removeAll(hand);

		if (evaluatedHand != null)
			evaluatedHand.removeAll(evaluatedHand);

		this.numOfCards = 0;
	}

	public boolean addCardToHand(Card c) {
		if (this.numOfCards == 10) {
			System.out.printf("%s has already have 10 cards in his hand",
					this.name);
			System.exit(1);
		}

		hand.add(c);
		numOfCards++;

		return (this.getHandSum() <= 21);

	}

	// returns the sum in players hand
	public int getHandSum() {
		int sumOfHand = 0;
		int numofAces = 0;

		for (Card c : hand) {
			if (c.getCardValue() == 1) // this is ace
			{
				numofAces++;
				sumOfHand = sumOfHand + 11;
			} else if (c.getCardValue() > 10) // these are face values
			{
				sumOfHand = sumOfHand + 10;
			} else // these are normal caards
			{
				sumOfHand = sumOfHand + c.getCardValue();
			}

			// recalculate the sum till its less than 21
			while (sumOfHand > 21 && numofAces > 0) {
				sumOfHand = sumOfHand - 10;
				numofAces--;
			}

		}

		return sumOfHand;
	}

	public ArrayList<Card> getEvaluatedHand() {
		return this.evaluatedHand;
	}
	
	public int getPairValue(){
		return this.pairValue;
	}
	
	// print cards in players hands
	public void printCardsInHand(boolean hideFirstCard) {
		int i = 0;
		for (Card c : hand) {

			if (hideFirstCard && i == 0) {
				System.out.println("[Dealer's hidden card]");
				i++;
			} else {
				System.out.println(c.toString());
				i++;
			}

		}
	}

	/*
	 * This Function will evaluate the hand of player
	 */
	public int evaluateHand(ArrayList<Card> boardCards) {

		// will check each combination and calculate the max hand from
		ArrayList<Card> temp = new ArrayList<Card>();
		int maxHandValue = 0;

		for (int i = 0; i < 10; i++) {

			// add 3 cards from board cards
			for (int j = 0; j < 3; j++) {
				temp.add(boardCards.get(combinationsForBoardCards[i][j]));
			}
			// add 2 cards from players hand
			for (Card c : this.hand) {
				temp.add(c);
			}

			// sort the hand and evaluate the hand
			Collections.sort(temp);

			int value = evaluate(temp);
			if (maxHandValue < value) {

				if (evaluatedHand != null)
					evaluatedHand.removeAll(evaluatedHand);

				maxHandValue = value;
				evaluatedHand.addAll(temp);
			}

			temp.removeAll(temp);
		}

		return maxHandValue;

	}

	// evaluates the hand
	private int evaluate(ArrayList<Card> temp) {
		//
		if (this.royalFlush(temp) == 1) {
			// System.out.println("You have a royal flush!");
			return 10;
		} else if (this.straightFlush(temp) == 1) {
			// System.out.println("You have a straight flush!");
			return 9;
		} else if (this.fourOfaKind(temp) == 1) {
			// System.out.println("You have four of a kind!");
			return 8;
		} else if (this.fullHouse(temp) == 1) {
			// System.out.println("You have a full house!");
			return 7;
		} else if (this.flush(temp) == 1) {
			// System.out.println("You have a flush!");
			return 6;
		} else if (this.straight(temp) == 1) {
			// System.out.println("You have a straight!");
			return 5;
		} else if (this.triple(temp) == 1) {
			// System.out.println("You have a triple!");
			return 4;
		} else if (this.twoPairs(temp) == 1) {
			// System.out.println("You have two pairs!");
			return 3;
		} else if (this.pair(temp) == 1) {
			// System.out.println("You have a pair!");
			return 2;
		} else {
			pairValue = this.highCard(temp);
			return 1;
			// System.out.println("Your highest card is " + highCard);
		}
	}

	// checks for a royal flush
	private int royalFlush(ArrayList<Card> temp) {
		if (temp.get(0).getCardValue() == 1 && temp.get(1).getCardValue() == 10
				&& temp.get(2).getCardValue() == 11
				&& temp.get(3).getCardValue() == 12
				&& temp.get(4).getCardValue() == 13) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks for a straight flush
	private int straightFlush(ArrayList<Card> temp) {
		
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(0).getSuit().ordinal() != temp.get(counter).getSuit().ordinal()) {
				return 0;
			}
		}

		for (int counter2 = 1; counter2 < 5; counter2++) {
			if (temp.get(counter2 - 1).getCardValue() != (temp.get(counter2)
					.getCardValue() - 1)) {
				return 0;
			}

		}
		return 1;

	}

	// checks for four of a kind
	private int fourOfaKind(ArrayList<Card> temp) {
		if (temp.get(0).getCardValue() != temp.get(3).getCardValue()
				&& temp.get(1).getCardValue() != temp.get(4).getCardValue()) {
			return 0;
		} else {
			return 1;
		}
	}

	// checks for full house
	private int fullHouse(ArrayList<Card> temp) {
		int comparison = 0;
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(counter - 1).getCardValue() == temp.get(counter)
					.getCardValue()) {
				comparison++;
			}
		}
		if (comparison == 3) {
			return 1;
		} else {
			return 0;
		}
	}

	// checks for flush
	private int flush(ArrayList<Card> temp) {
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(0).getSuit().ordinal() != temp.get(counter).getSuit()
					.ordinal()) {
				return 0;
			}
		}
		return 1;
	}

	// check for straight
	private int straight(ArrayList<Card> temp) {
		for (int counter2 = 1; counter2 < 5; counter2++) {
			if (temp.get(counter2 - 1).getCardValue() != (temp.get(counter2)
					.getCardValue() - 1)) {
				return 0;
			}

		}
		return 1;
	}

	// checks for triple
	private int triple(ArrayList<Card> temp) {
		if (temp.get(0).getCardValue() == temp.get(2).getCardValue()
				|| temp.get(2).getCardValue() == temp.get(4).getCardValue()) {
			pairValue=temp.get(0).getCardValue();
			return 1;
		}
		return 0;
	}

	// checks for two pairs
	private int twoPairs(ArrayList<Card> temp) {
		int check = 0;
		pairValue = 0;
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(counter - 1).getCardValue() == temp.get(counter)
					.getCardValue()) {
				check++;
				pairValue = pairValue+ temp.get(counter - 1).getCardValue();
			}
		}
		if (check == 2) {
			return 1;
		} else {
			return 0;
		}
	}

	// check for pair
	private int pair(ArrayList<Card> temp) {
		int check = 0;
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(counter - 1).getCardValue() == temp.get(counter)
					.getCardValue()) {
				check++;
				pairValue = temp.get(counter).getCardValue();
			}
		}
		if (check == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	// find highest card
	private int highCard(ArrayList<Card> temp) {
		int highCard = 0;
		for (int counter = 0; counter < 5; counter++) {
			if (temp.get(counter).getCardValue() > highCard) {
				highCard = temp.get(counter).getCardValue();
			}
		}
		return highCard;
	}

}
