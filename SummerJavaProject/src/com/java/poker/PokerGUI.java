package com.java.poker;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.Delayed;

import javax.swing.JTextField;

import com.java.casinoserver.Client;
import com.java.casinoserver.LoginDetails;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PokerGUI {

	private JFrame frmPoker;
	Deck objDeck;
	
	Player dealer;
	Player player;
	Board board;
	
	JLabel lblUserName;
	JLabel lblDealerName;
	
	JButton btnUserBet;
	JButton btnUserCheck;
	JButton btnUserCall;
	JButton btnUserRaise;
	JButton btnUserFold;
	JButton btnDealerBet;
	JButton btnDealerCheck;
	JButton btnDealerCall;
	JButton btnDealerRaise;
	JButton btnDealerFold;
	
	private HashMap<Integer, String> rules=null;
	private JTextField txtDealerBet;
	private JTextField txtDealerRaise;
	private JTextField txtUsetBet;
	private JTextField txtUserRaise;
	private JButton btnDeal;

	private static int potMoney =0;
	private JTextField txtPotMoney;
	private JLabel lblPotMoney;
	private JLabel lblGameStatus;
	
	ImageIcon boardCardImg1;
	ImageIcon boardCardImg2;
	ImageIcon boardCardImg3;
	ImageIcon boardCardImg4;
	ImageIcon boardCardImg5;
	
	private JLabel lblCard1;
	private JLabel lblCard2;
	private JLabel lblCard3;
	private JLabel lblCard4;
	private JLabel lblCard5;
	
	private boolean flag3rdCard=false, flag4thCard=false, flag5thCard=false;
	private int showCards=0; 
	
	int value1=0,value2=0;
	
	private JLabel lblDealerCard1;
	private JLabel lblDealerCard2;
	private JLabel lblPlayerCard1;
	private JLabel lblPlayerCard2;
	
	ImageIcon DealerCard1Img1;
	ImageIcon DealerCard2Img2;
	ImageIcon PlayerCard1Img1;
	ImageIcon PlayerCard1Img2;
	JLabel lblUserBalance;
	JLabel lblDealerBalance;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI window = new PokerGUI();
					window.frmPoker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPoker = new JFrame();
		frmPoker.setTitle("Poker");
		frmPoker.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					
					LoginDetails.UserBalance = String.valueOf(player.getPlayerMoney());
					Client.UpdateUser();
					//System.out.println("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmPoker.setBounds(100, 100, 935, 557);
		frmPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPoker.getContentPane().setLayout(null);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserName.setBounds(75, 459, 77, 14);
		frmPoker.getContentPane().add(lblUserName);
		
		btnUserBet = new JButton("Bet");
		btnUserBet.addActionListener(new UserBet_Click());
		btnUserBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserBet.setBounds(46, 484, 67, 23);
		frmPoker.getContentPane().add(btnUserBet);
		
		btnUserCheck = new JButton("Check");
		btnUserCheck.addActionListener(new UserCheck_Click());
		btnUserCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCheck.setBounds(227, 484, 83, 23);
		frmPoker.getContentPane().add(btnUserCheck);
		
		btnUserCall = new JButton("Call");
		btnUserCall.addActionListener(new UserCall_Click());
		btnUserCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCall.setBounds(328, 484, 83, 23);
		frmPoker.getContentPane().add(btnUserCall);
		
		btnUserRaise = new JButton("Raise");
		btnUserRaise.addActionListener(new UserRaise_Click());
		btnUserRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserRaise.setBounds(431, 484, 83, 23);
		frmPoker.getContentPane().add(btnUserRaise);
		
		btnUserFold = new JButton("Fold");
		btnUserFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserFold.setBounds(642, 484, 83, 23);
		frmPoker.getContentPane().add(btnUserFold);
		
		btnDealerBet = new JButton("Bet");
		btnDealerBet.addActionListener(new DealerBet_Click());
		btnDealerBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerBet.setBounds(46, 11, 67, 23);
		frmPoker.getContentPane().add(btnDealerBet);
		
		btnDealerCheck = new JButton("Check");
		btnDealerCheck.addActionListener(new DealerCheck_Click());
		btnDealerCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCheck.setBounds(227, 11, 83, 23);
		frmPoker.getContentPane().add(btnDealerCheck);
		
		btnDealerCall = new JButton("Call");
		btnDealerCall.addActionListener(new DealerCall_Click());
		btnDealerCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCall.setBounds(328, 11, 83, 23);
		frmPoker.getContentPane().add(btnDealerCall);
		
		btnDealerRaise = new JButton("Raise");
		btnDealerRaise.addActionListener(new DealerRaise_Click());
		btnDealerRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerRaise.setBounds(431, 11, 83, 23);
		frmPoker.getContentPane().add(btnDealerRaise);
		
		btnDealerFold = new JButton("Fold");
		btnDealerFold.addActionListener(new DealerRaise_Click());
		btnDealerFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerFold.setBounds(642, 11, 83, 23);
		frmPoker.getContentPane().add(btnDealerFold);
		
		txtDealerBet = new JTextField();
		txtDealerBet.setBounds(123, 12, 86, 20);
		frmPoker.getContentPane().add(txtDealerBet);
		txtDealerBet.setColumns(10);
		
		txtDealerRaise = new JTextField();
		txtDealerRaise.setBounds(530, 11, 86, 20);
		frmPoker.getContentPane().add(txtDealerRaise);
		txtDealerRaise.setColumns(10);
		
		txtUsetBet = new JTextField();
		txtUsetBet.setBounds(123, 486, 86, 20);
		frmPoker.getContentPane().add(txtUsetBet);
		txtUsetBet.setColumns(10);
		
		txtUserRaise = new JTextField();
		txtUserRaise.setBounds(530, 485, 86, 20);
		frmPoker.getContentPane().add(txtUserRaise);
		txtUserRaise.setColumns(10);
		
		lblDealerName = new JLabel("Player 2");
		lblDealerName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDealerName.setBounds(75, 137, 77, 14);
		frmPoker.getContentPane().add(lblDealerName);
		
		btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new Deal_Click());
		btnDeal.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDeal.setBounds(46, 59, 83, 23);
		frmPoker.getContentPane().add(btnDeal);
		
		txtPotMoney = new JTextField();
		txtPotMoney.setFont(new Font("Verdana", Font.BOLD, 12));
		txtPotMoney.setBounds(43, 345, 86, 20);
		frmPoker.getContentPane().add(txtPotMoney);
		txtPotMoney.setColumns(10);
		
		lblPotMoney = new JLabel("Pot Money");
		lblPotMoney.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPotMoney.setBounds(43, 320, 86, 14);
		frmPoker.getContentPane().add(lblPotMoney);
		
		lblCard1 = new JLabel("");
		lblCard1.setBounds(70, 180, 84, 125);
		frmPoker.getContentPane().add(lblCard1);
		
		lblCard2 = new JLabel("");
		lblCard2.setBounds(224, 177, 84, 125);
		frmPoker.getContentPane().add(lblCard2);
		
		lblCard3 = new JLabel("");
		lblCard3.setBounds(378, 177, 84, 125);
		frmPoker.getContentPane().add(lblCard3);
		
		lblCard4 = new JLabel("");
		lblCard4.setBounds(532, 177, 84, 125);
		frmPoker.getContentPane().add(lblCard4);
		
		lblCard5 = new JLabel("");
		lblCard5.setBounds(686, 180, 84, 125);
		frmPoker.getContentPane().add(lblCard5);
		
		lblGameStatus = new JLabel("Game Status:");
		lblGameStatus.setFont(new Font("Verdana", Font.BOLD, 12));
		lblGameStatus.setBounds(584, 437, 350, 14);
		frmPoker.getContentPane().add(lblGameStatus);
		
		lblDealerCard1 = new JLabel("");
		lblDealerCard1.setBounds(315, 45, 84, 125);
		frmPoker.getContentPane().add(lblDealerCard1);
		
		lblDealerCard2 = new JLabel("");
		lblDealerCard2.setBounds(422, 45, 84, 125);
		frmPoker.getContentPane().add(lblDealerCard2);
		
		lblPlayerCard1 = new JLabel("");
		lblPlayerCard1.setBounds(315, 326, 84, 125);
		frmPoker.getContentPane().add(lblPlayerCard1);
		
		lblPlayerCard2 = new JLabel("");
		lblPlayerCard2.setBounds(422, 326, 84, 125);
		frmPoker.getContentPane().add(lblPlayerCard2);
		
		lblUserBalance = new JLabel("User Balance :");
		lblUserBalance.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserBalance.setBounds(735, 488, 174, 14);
		frmPoker.getContentPane().add(lblUserBalance);
		
		lblDealerBalance = new JLabel("Dealer Balance :");
		lblDealerBalance.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDealerBalance.setBounds(735, 16, 174, 14);
		frmPoker.getContentPane().add(lblDealerBalance);
		
		dealer = new Player("Dealer");
		//player= new Player(LoginDetails.UserName);
		player= new Player("Swapnil");
		
		
		
		//Set initial amount for players
		// This amount will come from database
		dealer.setPlayerMoney(200);
		//player.setPlayerMoney(Integer.parseInt(LoginDetails.UserBalance));
		player.setPlayerMoney(850);
		
		lblDealerBalance.setText(dealer.getName()+" Balance :" +dealer.getPlayerMoney());
		lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		
		initializeGame();
		//initialiseRules();
	}
	
public void initializeGame()
	{
		objDeck = new Deck();
		board = new Board();
		
		//if(rules!=null){
		rules = new HashMap<Integer, String>();
		initialiseRules();
		//}
		objDeck.shuffle();
		
		
		player.emptyHand();
		dealer.emptyHand();
		
		// Deal the First Card
		dealer.addCardToHand(objDeck.drawNextCard());
		player.addCardToHand(objDeck.drawNextCard());

		// deal the second card
		dealer.addCardToHand(objDeck.drawNextCard());
		player.addCardToHand(objDeck.drawNextCard());

		// Show them on the JPanel

		// Get Dealer Card Images
		ImageIcon dCardImg1 = dealer.getCardsInHand().get(0).getImage(false);
		ImageIcon dCardImg2 = dealer.getCardsInHand().get(1).getImage(false);

		// Get Player Card Images
		ImageIcon pCardImg1 = player.getCardsInHand().get(0).getImage(false);
		ImageIcon pCardImg2 = player.getCardsInHand().get(1).getImage(false);
		
		// Add cards to Community Board
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		board.putCardsOnBoard(objDeck.drawNextCard());
		
		boardCardImg1 = board.getCardsOnBoard().get(0).getImage(true);
		boardCardImg2 = board.getCardsOnBoard().get(1).getImage(true);
		boardCardImg3 = board.getCardsOnBoard().get(2).getImage(true);
		boardCardImg4 = board.getCardsOnBoard().get(3).getImage(true);
		boardCardImg5 = board.getCardsOnBoard().get(4).getImage(true);
		
		lblCard1.setIcon(boardCardImg1);
		lblCard2.setIcon(boardCardImg2);
		lblCard3.setIcon(boardCardImg3);
		lblCard4.setIcon(boardCardImg4);
		lblCard5.setIcon(boardCardImg5);
		
		lblDealerCard1.setIcon(dCardImg1);
		lblDealerCard2.setIcon(dCardImg2);
		
		
		lblPlayerCard1.setIcon(pCardImg1);
		lblPlayerCard2.setIcon(pCardImg2);
		
		lblUserName.setText(player.getName());
		lblDealerName.setText(dealer.getName());
		
		enableDisableButton(false, player);
		enableDisableButton(true, dealer);
		
	}

class Deal_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		initializeGame();
		txtDealerBet.setText("");
		txtDealerRaise.setText("");
		txtPotMoney.setText("");
		txtUserRaise.setText("");
		txtUsetBet.setText("");
		player.setTotalBetAmount(0);
		dealer.setTotalBetAmount(0);
		potMoney = 0;
		
		flag3rdCard=false;
		flag4thCard=false;
		flag5thCard=false;
		
		lblGameStatus.setText("Game Status: ");
		lblDealerBalance.setText(dealer.getName()+" Balance :" +dealer.getPlayerMoney());
		lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		
		enableDisableButton(false, player);
		enableDisableButton(true, dealer);
	}
}

class DealerBet_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(txtDealerBet.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Please enter the bet Amount");
		}
		
		dealer.setBetMoney(Integer.parseInt(txtDealerBet.getText()));
	
		dealer.setPlayerMoney(dealer.getPlayerMoney()-dealer.getBetMoney());
		lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
		
		dealer.setTotalBetAmount(dealer.getTotalBetAmount()+Integer.parseInt(txtDealerBet.getText()));
		
		potMoney += dealer.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
		
		btnDealerBet.setEnabled(false);
		txtDealerBet.setEnabled(false);
		
		enableDisableButton(false,dealer);
		enableDisableButton(true,player);
		
	}
	
}

class UserBet_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(txtUsetBet.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Please enter the bet Amount");
		}
		
		player.setBetMoney(Integer.parseInt(txtUsetBet.getText()));

		// set the player amount and display it on label
		player.setPlayerMoney(player.getPlayerMoney()-player.getBetMoney());
		lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		
		player.setTotalBetAmount(player.getTotalBetAmount()+ Integer.parseInt(txtUsetBet.getText()));
		potMoney += player.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
		
		
		//enableDisableButton(false,dealer);
		
		btnUserBet.setEnabled(false);
		txtUsetBet.setEnabled(false);
		
		enableDisableButton(false,player);
		enableDisableButton(true,dealer);
		
	}
	
}

class DealerCall_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dealer.setPreviousState(pokerGameState.Call);
		//int callAmount = player.getTotalBetAmount() - dealer.getTotalBetAmount();
		if(performPlayerCallClick(dealer)){
			enableDisableButton(false,dealer);
			enableDisableButton(true,player);
		};
		
		
		
	}
	
}

class UserCall_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.setPreviousState(pokerGameState.Call);
		
		if(performPlayerCallClick(player)){
			enableDisableButton(false,player);
			enableDisableButton(true,dealer);
		};
		
		
	}	
}

class DealerCheck_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(getPotAmount()!=0){
			JOptionPane.showMessageDialog(null, "Pot is not equal. You can not check.");
			return;
		}
		dealer.setPreviousState(pokerGameState.Check);
		playerCheck_Click(player);
		enableDisableButton(false,dealer);
		enableDisableButton(true,player);
	}
	
}

class UserCheck_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(getPotAmount()!=0){
			JOptionPane.showMessageDialog(null, "Pot is not equal. You can not check.");
			return;
		}
		player.setPreviousState(pokerGameState.Check);
		playerCheck_Click(dealer);
		
		enableDisableButton(false,player);
		enableDisableButton(true,dealer);
	}
	
}

private void playerCheck_Click(Player player){
	
	//if(player.getPreviousState() == pokerGameState.Call || player.getPreviousState() == pokerGameState.Check){
	if(showCards==0){
		showCards=1;
	}else if(showCards==1){
		showCards=0;
		if(getPotAmount() == 0){
			if(flag3rdCard==false && flag4thCard==false && flag5thCard == false){
				openFirst3Cards();
				flag3rdCard=true;
			}else if (flag3rdCard==true && flag4thCard==false && flag5thCard == false){
				open4ThCards();
				flag4thCard = true;
			}else if (flag3rdCard==true && flag4thCard==true && flag5thCard == false){
				open5ThCards();
				flag5thCard = true;
				/// declare winner on the Window
				getWinner();
			}
		}
	}
		
		
		
	//}
}

class DealerFold_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		player.setPlayerMoney(player.getPlayerMoney()+potMoney);
		lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
		enableDisableButton(false,dealer);
		enableDisableButton(false,player);
	}
	
}

class UserFold_click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		dealer.setPlayerMoney(dealer.getPlayerMoney()+potMoney);
		lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
		
		enableDisableButton(false,player);
		enableDisableButton(false,dealer);
	}
	
}

class DealerRaise_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(txtUserRaise.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Please enter the bet Amount");
		}
		
		dealer.setBetMoney(Integer.parseInt(txtDealerRaise.getText()));
		
		dealer.setPlayerMoney(dealer.getPlayerMoney()-dealer.getBetMoney());
		lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
		
		dealer.setTotalBetAmount(dealer.getTotalBetAmount()+ Integer.parseInt(txtUserRaise.getText()));
		potMoney += dealer.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
		showCards=0;
		txtDealerRaise.setText("");
		enableDisableButton(false,dealer);
		enableDisableButton(true,player);
	}
	
}

class UserRaise_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(txtUserRaise.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Please enter the bet Amount");
		}
		
		player.setBetMoney(Integer.parseInt(txtUserRaise.getText()));
		
		player.setPlayerMoney(player.getPlayerMoney()-player.getBetMoney());
		lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		
		player.setTotalBetAmount(player.getTotalBetAmount()+ Integer.parseInt(txtUserRaise.getText()));
		potMoney += player.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
		showCards=0;
		txtUserRaise.setText("");
		
		enableDisableButton(false,player);
		enableDisableButton(true,dealer);
	}
	
}


private boolean performPlayerCallClick(Player player)
{
	int potAmount = getPotAmount();
	if(potAmount ==0){
		showMessagePotIsEqual();
		return false;
	}else{
		
		player.setTotalBetAmount(player.getTotalBetAmount()+potAmount);
		player.setPlayerMoney(player.getPlayerMoney()-potAmount);
		
		if(player.getName().equalsIgnoreCase("Dealer")){
			lblDealerBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		}else
		{
			lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
		}
		
		potMoney += potAmount;
		txtPotMoney.setText(String.valueOf(potMoney));
		playerCheck_Click(player);
		return true;
	}
}

private void openFirst3Cards(){
	
	boardCardImg1 = board.getCardsOnBoard().get(0).getImage(false);
	boardCardImg2 = board.getCardsOnBoard().get(1).getImage(false);
	boardCardImg3 = board.getCardsOnBoard().get(2).getImage(false);
	boardCardImg4 = board.getCardsOnBoard().get(3).getImage(true);
	boardCardImg5 = board.getCardsOnBoard().get(4).getImage(true);
	
	lblCard1.setIcon(boardCardImg1);
	lblCard2.setIcon(boardCardImg2);
	lblCard3.setIcon(boardCardImg3);
	lblCard4.setIcon(boardCardImg4);
	lblCard5.setIcon(boardCardImg5);
	
}

private void open4ThCards(){
	
	boardCardImg1 = board.getCardsOnBoard().get(0).getImage(false);
	boardCardImg2 = board.getCardsOnBoard().get(1).getImage(false);
	boardCardImg3 = board.getCardsOnBoard().get(2).getImage(false);
	boardCardImg4 = board.getCardsOnBoard().get(3).getImage(false);
	boardCardImg5 = board.getCardsOnBoard().get(4).getImage(true);
	
	lblCard1.setIcon(boardCardImg1);
	lblCard2.setIcon(boardCardImg2);
	lblCard3.setIcon(boardCardImg3);
	lblCard4.setIcon(boardCardImg4);
	lblCard5.setIcon(boardCardImg5);
	
}

private void open5ThCards(){
	
	boardCardImg1 = board.getCardsOnBoard().get(0).getImage(false);
	boardCardImg2 = board.getCardsOnBoard().get(1).getImage(false);
	boardCardImg3 = board.getCardsOnBoard().get(2).getImage(false);
	boardCardImg4 = board.getCardsOnBoard().get(3).getImage(false);
	boardCardImg5 = board.getCardsOnBoard().get(4).getImage(false);
	
	lblCard1.setIcon(boardCardImg1);
	lblCard2.setIcon(boardCardImg2);
	lblCard3.setIcon(boardCardImg3);
	lblCard4.setIcon(boardCardImg4);
	lblCard5.setIcon(boardCardImg5);
	
}

private void showMessagePotIsEqual(){
	JOptionPane.showMessageDialog(null, "Pot is equal. You can not call.");
}

private int getPotAmount(){
	return Math.abs(dealer.getTotalBetAmount() - player.getTotalBetAmount());
}

private void getWinner(){
	
	value1 = dealer.evaluateHand(board.getCardsOnBoard());
	value2 = player.evaluateHand(board.getCardsOnBoard());
	//player1 ==dealer
	//player2 == player
	if (value1 == value2) {
		
		System.out.println("Both the players have same hand....\n");
		
		System.out.println(dealer.getName()+" has cards in hand");
		dealer.printCardsInHand(false);
		
		System.out.println();
		
		System.out.println(player.getName()+" has cards in hand");
		player.printCardsInHand(false);
		System.out.println();
		
		switch(value1){
		
		// both players have same high cards
		case 1:

			if(dealer.compareTo(player.evaluatedHandValue) == 0){
				System.out.println("both players have same High cards");
				
				lblGameStatus.setText("Game Status: Winner is ....Game draw");
				//divide pot amount in equally 
				dealer.setPlayerMoney((potMoney/2)+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
				player.setPlayerMoney((potMoney/2)+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == 1){
				System.out.println("Winner is ...."+dealer.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
				dealer.setPlayerMoney(potMoney+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == -1){
				System.out.println("Winner is ...."+player.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
				///set user balance 
				player.setPlayerMoney(potMoney+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
			}
			break;
			
		// Handle if both players have same pairs
		case 2:

			
			if(dealer.compareTo(player.evaluatedHandValue) == 0){
				System.out.println("both players have same pairs");
				lblGameStatus.setText("Game Status: Winner is ....Game draw");
				//divide pot amount in equally 
				dealer.setPlayerMoney((potMoney/2)+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
				player.setPlayerMoney((potMoney/2)+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == 1){
				System.out.println("Winner is ...."+dealer.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
				dealer.setPlayerMoney(potMoney+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == -1){
				System.out.println("Winner is ...."+player.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
				///set user balance 
				player.setPlayerMoney(potMoney+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
			}
			
			break;
		case 3:

			if(dealer.compareTo(player.evaluatedHandValue) == 0){
				System.out.println("both players have same two pairs");
				lblGameStatus.setText("Game Status: Winner is ....Game draw");
				//divide pot amount in equally 
				dealer.setPlayerMoney((potMoney/2)+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
				player.setPlayerMoney((potMoney/2)+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == 1){
				System.out.println("Winner is ...."+dealer.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
				dealer.setPlayerMoney(potMoney+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == -1){
				System.out.println("Winner is ...."+player.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
				///set user balance 
				player.setPlayerMoney(potMoney+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
			}
			break;
			
		case 4:
			
			if(dealer.compareTo(player.evaluatedHandValue) == 0){
				System.out.println("both players have same triples");
				lblGameStatus.setText("Game Status: Winner is ....Game draw");
				
				//divide pot amount in equally 
				dealer.setPlayerMoney((potMoney/2)+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
				player.setPlayerMoney((potMoney/2)+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == 1){
				System.out.println("Winner is ...."+dealer.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
				dealer.setPlayerMoney(potMoney+dealer.getPlayerMoney());
				lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
				
			}else if(dealer.compareTo(player.evaluatedHandValue) == -1){
				System.out.println("Winner is ...."+player.getName() );
				lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
				///set user balance 
				player.setPlayerMoney(potMoney+player.getPlayerMoney());
				lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
			}
			
			break;
		}
		
		
		mPrintGameDetails(dealer,player);
		
	} else if (value1 > value2) {
		System.out.println(dealer.getName() +" has cards in hand");
		dealer.printCardsInHand(false);
		
		System.out.println();
		
		System.out.println(player.getName() +" has cards in hand");
		player.printCardsInHand(false);
		
		System.out.println();
		System.out.println("Winner is ...."+dealer.getName());
		lblGameStatus.setText("Game Status: Winner is ...."+ dealer.getName());
		
		///set user balance 
		dealer.setPlayerMoney(potMoney+dealer.getPlayerMoney());
		lblDealerBalance.setText(dealer.getName()+ " Balance :" +dealer.getPlayerMoney());
		
		mPrintGameDetails(dealer,player);
	} else {
		
		System.out.println(dealer.getName()+" has cards in hand");
		dealer.printCardsInHand(false);
		
		System.out.println();
		
		System.out.println(player.getName() +" has cards in hand");
		player.printCardsInHand(false);
		System.out.println();
		System.out.println("Winner is ...."+player.getName());
		mPrintGameDetails(dealer,player);
		lblGameStatus.setText("Game Status: Winner is ...."+ player.getName());
		
		///set user balance 
		player.setPlayerMoney(potMoney+player.getPlayerMoney());
		lblUserBalance.setText(player.getName()+ " Balance :" +player.getPlayerMoney());
	}
}

private void mPrintGameDetails(Player player1,Player player2){
	
	System.out.println(dealer.getName() + " Cards.. "+ player1.getEvaluatedHand().toString());
	System.out.println(dealer.getName() + " has.. " + rules.get(value1));
	System.out.println(player.getName() + " Cards.."+ player2.getEvaluatedHand().toString());
	System.out.println(player.getName() + " has.. " + rules.get(value2));
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

private void enableDisableButton(boolean value,Player player){
	
	if(player.getName().equalsIgnoreCase("Dealer")){
		btnDealerCall.setEnabled(value);
		btnDealerCheck.setEnabled(value);
		btnDealerRaise.setEnabled(value);
		btnDealerFold.setEnabled(value);
		txtDealerRaise.setEditable(value);
		btnDealerBet.setEnabled(value);
		txtDealerBet.setEnabled(value);
	} else{ 
		//	if(player.getName().equalsIgnoreCase("Player 2")){
		btnUserCall.setEnabled(value);
		btnUserCheck.setEnabled(value);
		btnUserRaise.setEnabled(value);
		txtUserRaise.setEnabled(value);
		btnUserFold.setEnabled(value);
		btnUserBet.setEnabled(value);
		txtUsetBet.setEnabled(value);
	}
}
}

enum pokerGameState{
	Call,
	Check,
	Raise
}
