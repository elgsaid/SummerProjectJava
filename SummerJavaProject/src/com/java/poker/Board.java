package com.java.poker;

import java.util.ArrayList;

public class Board {

	private ArrayList<Card> cardsOnBoard = null;

	/*
	 * Constructor
	 */
	public Board() {
		// initialize the array
		cardsOnBoard = new ArrayList<Card>();
	}

	/*
	 * Get cards from board
	 */
	public ArrayList<Card> getCardsOnBoard() {
		return cardsOnBoard;
	}

	public void putCardsOnBoard(Card c) {
		this.cardsOnBoard.add(c);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		for (Card card : cardsOnBoard) {
			// System.out.println(card.toString());
			sb.append(card.toString());
			sb.append(",");
		}
		return sb.toString();
	}

}
