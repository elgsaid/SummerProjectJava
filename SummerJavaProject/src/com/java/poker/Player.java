package com.java.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<int[]> {

	private String name;

	private ArrayList<Card> hand;
	private ArrayList<Card> evaluatedHand;
	private int numOfCards = 0;
	private int pairValue=0;
	private int pairValue2=0;
	
	private int betMoney=0;
	private int raiseMoney=0;
	private int totalBetAmount=0;
	private int playerMoney=0;
	private pokerGameState previousState;
	public int[] evaluatedHandValue;
	
/*	{ { 0, 1, 2 }, { 0, 1, 3 },
		{ 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 }, { 0, 3, 4 }, { 1, 2, 3 },
		{ 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };*/
	
	private int[][] combinationsForBoardCards ={ {1,2,3,4,5},
	{1,2,3,4,6},
	{1,2,3,4,7},
	{1,2,3,5,6},
	{1,2,3,5,7},
	{1,2,3,6,7},
	{1,2,4,5,6},
	{1,2,4,5,7},
	{1,2,4,6,7},
	{1,2,5,6,7},
	{1,3,4,5,6},
	{1,3,4,5,7},
	{1,3,4,6,7},
	{1,3,5,6,7},
	{1,4,5,6,7},
	{2,3,4,5,6},
	{2,3,4,5,7},
	{2,3,4,6,7},
	{2,3,5,6,7},
	{2,4,5,6,7},
	{3,4,5,6,7} } ;
	
 public Player(String name) {
		this.name = name;
		this.emptyHand();
		hand = new ArrayList<Card>();
		evaluatedHand = new ArrayList<Card>();
		setPreviousState(null);
	}

	// Added this function
	public String getName() {
		return this.name;
	}
	
	public int getBetMoney() {
		return betMoney;
	}

	public void setBetMoney(int betMoney) {
		this.betMoney = betMoney;
	}

	public int getRaiseMoney() {
		return raiseMoney;
	}

	public void setRaiseMoney(int raiseMoney) {
		this.raiseMoney = raiseMoney;
	}

	public int getTotalBetAmount() {
		return totalBetAmount;
	}

	public void setTotalBetAmount(int totalBetAmount) {
		this.totalBetAmount = totalBetAmount;
	}
	
	public int getPlayerMoney() {
		return playerMoney;
	}

	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	
	public pokerGameState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(pokerGameState previousState) {
		this.previousState = previousState;
	}
	
	/* This method is used when we start with new game we required to empty
	 * players hand
	 * */

	public void emptyHand() {

		if (hand != null){
			hand.removeAll(hand);
			hand = new ArrayList<Card>();
			
		}
			
		if (evaluatedHand != null){
			evaluatedHand.removeAll(evaluatedHand);
			evaluatedHand = new ArrayList<Card>();
		}
			

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
	
	public ArrayList<Card> getCardsInHand(){
		return this.hand;
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
		// them
		ArrayList<Card> temp = new ArrayList<Card>();
		int maxHandValue = 0;

		
		// 
		// add 5 cards from board cards
		for (int j = 0; j < 5; j++) {
			temp.add(boardCards.get(j));
		}
		// add 2 cards from players hand
		for (Card c : this.hand) {
			temp.add(c);
		}

		// sort the hand and evaluate the hand
		//Collections.sort(temp);

		//Declare array list for evaluating the cards
		ArrayList<Card> evalCards;
		
		for (int i = 0; i < 21; i++) {
			
			evalCards = new ArrayList<Card>();
			//get the 5 cards out of 7 cards
			for(int j =0 ; j<5;j++){
				evalCards.add(temp.get(combinationsForBoardCards[i][j]-1));
			}
			
			Collections.sort(evalCards);	
			
			int value = evaluate(evalCards);
			if (maxHandValue < value) {

				if (evaluatedHand != null)
					evaluatedHand.removeAll(evaluatedHand);

				maxHandValue = value;
				evaluatedHand.addAll(evalCards);
			}

		}
		this.createEvaluatedHandValueArray(maxHandValue);
		return maxHandValue;

	}

	// evaluates the hand
	private int evaluate(ArrayList<Card> temp) {
		//
		if (this.royalFlush(temp) == 1) {
			return 10;
		} else if (this.straightFlush(temp) == 1) {
			return 9;
		} else if (this.fourOfaKind(temp) == 1) {
			return 8;
		} else if (this.fullHouse(temp) == 1) {
			return 7;
		} else if (this.flush(temp) == 1) {
			return 6;
		} else if (this.straight(temp) == 1) {
			return 5;
		} else if (this.triple(temp) == 1) {
			return 4;
		} else if (this.twoPairs(temp) == 1) {
			return 3;
		} else if (this.pair(temp) == 1) {
			return 2;
		} else {
			pairValue = this.highCard(temp);
			return 1;
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
		if (temp.get(0).getCardValue() != temp.get(3).getCardValue() && 
			temp.get(1).getCardValue() != temp.get(4).getCardValue()) {
			return 0;
		} else {
			pairValue= temp.get(1).getCardValue();
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
		pairValue2 = 0;
		
		for (int counter = 1; counter < 5; counter++) {
			if (temp.get(counter - 1).getCardValue() == temp.get(counter)
					.getCardValue()) {
				check++;
				if(check==1){
					pairValue = temp.get(counter - 1).getCardValue();
				}
				
				if(check==2){
					pairValue2 = temp.get(counter - 1).getCardValue();
				}
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
			if (temp.get(counter - 1).getCardValue() == temp.get(counter).getCardValue()) {
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
		// Check if ace is higher card or not
		int highCard = 0;
		for (int counter = 0; counter < 5; counter++) {
			if (temp.get(counter).getCardValue() > highCard) {
				highCard = temp.get(counter).getCardValue();
			}
		}
		return highCard;
	}

	
	private void createEvaluatedHandValueArray(int handvalue){
		
		int[] CntEachCards = new int[14];
		this.evaluatedHandValue = new int[5];
		int[] OrderedCards = new int[5];
		
		for (int i = 0; i <= 13; i++) {
			CntEachCards[i] = 0;
		}
		 
		// this for loop will determine the each card counts
		for (int j = 0; j <= 4; j++) {
			CntEachCards[evaluatedHand.get(j).getCardValue()]++;
		}		
		
		int inx=0;
		if (CntEachCards[1] == 1) // ace is highest card
							// card
		{
			OrderedCards[inx] = 14;
			inx++;
		}
		
		// this loop will give us cards whose count is single
		// and stored it into OrderedCards array 
		for(int k =13; k>=2 ;k--){
			
			if(CntEachCards[k]==1){
				OrderedCards[inx]=k;
				inx++;
			}
		}
				
	    Collections.sort(this.evaluatedHand);
	    
		// if we have high cards
		if(handvalue==1){ 
			int i =0;
			for (int j = 4; j >= 0; j--) {
				evaluatedHandValue[i] = evaluatedHand.get(j).getCardValue();
				i++;
			}
		}
		// we have a pair
		if(handvalue==2){
			
			int pair =0;
			for (int p =1;p<=13;p++){	
				if (CntEachCards[p] == 2) {
					pair=p;
				}
			}
			
			evaluatedHandValue[0]= pair;
			evaluatedHandValue[1]=  OrderedCards[0]; 
			evaluatedHandValue[2] = OrderedCards[1];
			evaluatedHandValue[3] = OrderedCards[2];
		}
		
		// we have 2 pairs
		if (handvalue == 3) {

			evaluatedHandValue[0] = pairValue > pairValue2 ? pairValue: pairValue2;
			evaluatedHandValue[1] = pairValue < pairValue2 ? pairValue: pairValue2;
			evaluatedHandValue[2] = OrderedCards[0];

		}
		
		// we have 3 of kind
		if (handvalue == 4) {
			int triple =0;
			for (int p =1;p<=13;p++){	
				if (CntEachCards[p] == 3) {
					triple=p;
				}
			}
			evaluatedHandValue[0]= triple;
			evaluatedHandValue[1]=  OrderedCards[0]; 
			evaluatedHandValue[2] = OrderedCards[1];

		}
		
		// we have straight
		if(handvalue ==5){
			int i =0;
			for (int j = 4; j >= 0; j--) {
				evaluatedHandValue[i] = evaluatedHand.get(j).getCardValue();
				i++;
			}	
		}
		
		// we have flush
		if(handvalue==6){
			int i =0;
			for (int j = 4; j >= 0; j--) {
				evaluatedHandValue[i] = evaluatedHand.get(j).getCardValue();
				i++;
			}
		}
		
		// we have full house
		// that means we can get the values from CntEachCards 
		if (handvalue == 7) {
			int triple =0;
			int pair =0;
			
			for (int p =1;p<=13;p++){
				if (CntEachCards[p]==2){
					pair = p;
				}
			}
			
			for (int p =1;p<=13;p++){	
				if (CntEachCards[p] == 3) {
					triple=p;
				}
			}
			
			evaluatedHandValue[0] = triple;
			evaluatedHandValue[1] = pair;
			
		}
		
		// we have 4 of a kind
		if (handvalue == 8) {
			
			int fourofkind =0;
			for (int p =1;p<=13;p++){
				if (CntEachCards[p]==4){
					fourofkind = p;
				}
			}
			
			evaluatedHandValue[0]= fourofkind;
			evaluatedHandValue[1]=  OrderedCards[0]; 
		}
		
		//Straight flush
		if (handvalue == 9) {

		}
		
		//royal flush
		if(handvalue == 10){
			
		}
	}


	@Override
	public int compareTo(int[] o) {
		// TODO Auto-generated method stub

		for(int i=0;i<5;i++)
		{
			if (this.evaluatedHandValue[i] > o[i]) {
				return 1;
			} else if (this.evaluatedHandValue[i] < o[i]) {
				return -1;
			}
		}
		return 0;
	}

	
}
