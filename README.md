# SummerProjectJava
Steps to run the project

1) Download the project and open the project in eclipse
2) Add all jar files from resource folder to reference library in project for Sqlite database
3) GOTO com.java.casinoserver package and start the TestServer.java file. This will start the server
4) After this goto com.java.loginpage and run the LoginPage.java file 
5) Enter your name for testing purpose I have created test user "Swapnil" and select the game and click on start game
6) After closing the application user balance will get updated into database 
7) To check the changes in DATABASE. Please add a SQLite Manager plug-in on Mozilla browser and open "Casino.db" Database 
   which is in source folder of the project


A)Server Implementation

TestServer.java file contains the server implementation
two functions are provided by that server
1) insert the user records if the user is not present in database with default balance of 200
2) Update the User with its Balance money

B)User Management in database
DatabaseConnection.java file
Database Name -> Casino
All jar files related to Sqlite database are in resource folder

It has a table "USER" which store the user Balance and user name 
Columns are 
	--ID (identity column) primary key
	--Name 
	--Money
	
C) Login Page
This is the start page of Casino application
--User will enter his name
  if user is not present in the database it will insert its details into the database and return back 
  into login details class
--Select a game from BlackJack, Poker, SlotMachine
--Click on the start button
-- when ever user ends his game its balance money is gets updated into database
Game Rules

D) Black Jack

1) Players of the game are Dealer and login user

Rules of the Game

--Player will win the game if his card sum is greater than dealer's hand sum and less than 21 
-- OrIf player gets hand sum which is 21(BlackJack) 
-- If any player get hand sum which is greater than 21 then that player is busted
-- Cards value will indicate there points except ace which can be 1 or 11

1) login user will bet 
2) if player Loose - player losses his bet amount
   if player Wins - the player wins his bet amount + dealers bet amount
   if player gets Blackjack - the player wins 2 times the bet amount plus his original bet
   Draw -  The player keeps his bet and dealer also keeps his bet

E) SlotMachine

1) We have 9 cards from of diamonds
2) Click on spin button
   -- if user gets 2 same cards then user will win 30 points
   -- if user gets 3 same cards(JackPot) then user will win 60 points
   -- if user gets 3 different cards then he losses 5 points
 
 F) Poker
 
 There are 10 rules
 We have 5 community or board cards
 Each player will have 2 hand cards. So when we have to select best 5 cards from 7 cards
 rules are arranged in ascending order(for example a pair beats a high card in a game etc.)
 
 1) high card
 2) a pair
 3) two pairs
 4) triple
 5) straight (5 cards placed in serially forms a continuous sequence)
 6) flush ( all cards with same suit)
 7) full house (a triple and a pair)
 8) four of a kind ( 4 kings, 4 nines etc)
 9) straight flush ( straight + flush)
10) royal flush(Ace, king, Queen, Jack, ten of all suits)

Player choices

Bet-> set a initial bet amount when player begin to play the game, this bet amount will be added to the pot money
Call -> After initial bet by 1st player , 2nd player have choice of call, check or raise. If player press CALL button 
		then the difference between the total bet amount is calculated and that amount is put into pot amount		 
Check -> to press the CHECK button pot should be equal(means both the players should put equal amount of money into pot)
Raise-> you can increase the pot amount by clicking in raise button
Fold -> if player press fold button the other player wins

--Winner will win the pot amount, looser looses his bet amount
--if there is a tie then the pot amount is equally distributed

G) References:

Used to create Server and client 
http://stackoverflow.com/questions/15591397/sending-objects-to-localhost-server-using-java
http://stackoverflow.com/questions/1601151/how-do-i-check-in-sqlite-whether-a-table-exists
http://www.tutorialspoint.com/sqlite/sqlite_java.htm
http://stackoverflow.com/questions/1776457/java-client-server-application-with-sockets
https://www.youtube.com/watch?v=buGFs1aQgaY
http://faculty.washington.edu/moishe/javademos/blackjack/BlackjackGUI.java
http://www.poornerd.com/2014/08/22/448/
https://github.com/ahh2131/Simple-Poker
http://www.codeproject.com/Articles/38821/Make-a-poker-hand-evalutator-in-Java
http://www.tutorialspoint.com/javaexamples/net_multisoc.htm
http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
http://www.mkyong.com/jdbc/jdbc-preparestatement-example-insert-a-record/
http://www.mkyong.com/jdbc/jdbc-preparestatement-example-update-a-record/
https://www.youtube.com/watch?v=t7saIOLl06c
https://www.youtube.com/watch?v=gU3DLOsw0Eg
