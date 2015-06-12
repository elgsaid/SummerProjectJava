package com.java.casinoserver;
/*
 * This is Global class which will contain user information
 * when it log in to the system
 */

public class LoginDetails {
	    public static String UserName;
	    public static String UserBalance;
	    public static Games UserGameName;
	    public static String UserId;
}

enum Games{
	Blackjack,
	Poker,
	SlotMachine
}