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

public class PokerGUI {

	private JFrame frame;
	Deck objDeck;
	
	JPanel panelDealer;
	JPanel panelPlayer;
	
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
	
	private HashMap<Integer, String> rules;
	private JTextField txtDealerBet;
	private JTextField txtDealerRaise;
	private JTextField txtUsetBet;
	private JTextField txtUserRaise;
	private JButton btnDeal;

	private static int potMoney =0;
	private JTextField txtPotMoney;
	private JLabel lblPotMoney;
	
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI window = new PokerGUI();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelDealer = new JPanel();
		panelDealer.setBounds(259, 42, 315, 110);
		frame.getContentPane().add(panelDealer);
		
		panelPlayer = new JPanel();
		panelPlayer.setBounds(259, 345, 315, 110);
		frame.getContentPane().add(panelPlayer);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserName.setBounds(75, 459, 77, 14);
		frame.getContentPane().add(lblUserName);
		
		btnUserBet = new JButton("Bet");
		btnUserBet.addActionListener(new UserBet_Click());
		btnUserBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserBet.setBounds(46, 484, 67, 23);
		frame.getContentPane().add(btnUserBet);
		
		btnUserCheck = new JButton("Check");
		btnUserCheck.addActionListener(new UserCheck_Click());
		btnUserCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCheck.setBounds(227, 484, 83, 23);
		frame.getContentPane().add(btnUserCheck);
		
		btnUserCall = new JButton("Call");
		btnUserCall.addActionListener(new UserCall_Click());
		btnUserCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserCall.setBounds(328, 484, 83, 23);
		frame.getContentPane().add(btnUserCall);
		
		btnUserRaise = new JButton("Raise");
		btnUserRaise.addActionListener(new UserRaise_Click());
		btnUserRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserRaise.setBounds(431, 484, 83, 23);
		frame.getContentPane().add(btnUserRaise);
		
		btnUserFold = new JButton("Fold");
		btnUserFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUserFold.setBounds(642, 484, 83, 23);
		frame.getContentPane().add(btnUserFold);
		
		btnDealerBet = new JButton("Bet");
		btnDealerBet.addActionListener(new DealerBet_Click());
		btnDealerBet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerBet.setBounds(46, 11, 67, 23);
		frame.getContentPane().add(btnDealerBet);
		
		btnDealerCheck = new JButton("Check");
		btnDealerCheck.addActionListener(new DealerCheck_Click());
		btnDealerCheck.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCheck.setBounds(227, 11, 83, 23);
		frame.getContentPane().add(btnDealerCheck);
		
		btnDealerCall = new JButton("Call");
		btnDealerCall.addActionListener(new DealerCall_Click());
		btnDealerCall.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerCall.setBounds(328, 11, 83, 23);
		frame.getContentPane().add(btnDealerCall);
		
		btnDealerRaise = new JButton("Raise");
		btnDealerRaise.addActionListener(new DealerRaise_Click());
		btnDealerRaise.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerRaise.setBounds(431, 11, 83, 23);
		frame.getContentPane().add(btnDealerRaise);
		
		btnDealerFold = new JButton("Fold");
		btnDealerFold.addActionListener(new DealerRaise_Click());
		btnDealerFold.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDealerFold.setBounds(642, 11, 83, 23);
		frame.getContentPane().add(btnDealerFold);
		
		txtDealerBet = new JTextField();
		txtDealerBet.setBounds(123, 12, 86, 20);
		frame.getContentPane().add(txtDealerBet);
		txtDealerBet.setColumns(10);
		
		txtDealerRaise = new JTextField();
		txtDealerRaise.setBounds(530, 11, 86, 20);
		frame.getContentPane().add(txtDealerRaise);
		txtDealerRaise.setColumns(10);
		
		txtUsetBet = new JTextField();
		txtUsetBet.setBounds(123, 486, 86, 20);
		frame.getContentPane().add(txtUsetBet);
		txtUsetBet.setColumns(10);
		
		txtUserRaise = new JTextField();
		txtUserRaise.setBounds(530, 485, 86, 20);
		frame.getContentPane().add(txtUserRaise);
		txtUserRaise.setColumns(10);
		
		lblDealerName = new JLabel("Player 2");
		lblDealerName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDealerName.setBounds(75, 137, 77, 14);
		frame.getContentPane().add(lblDealerName);
		
		btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new Deal_Click());
		btnDeal.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDeal.setBounds(46, 59, 83, 23);
		frame.getContentPane().add(btnDeal);
		
		txtPotMoney = new JTextField();
		txtPotMoney.setFont(new Font("Verdana", Font.BOLD, 12));
		txtPotMoney.setBounds(43, 345, 86, 20);
		frame.getContentPane().add(txtPotMoney);
		txtPotMoney.setColumns(10);
		
		lblPotMoney = new JLabel("Pot Money");
		lblPotMoney.setFont(new Font("Verdana", Font.BLD, 12));
		lblPotMoney.setBounds(43, 320, 86, 14);
		frame.getContentPane().add(lblPotMoney);
		
		lblCard1 = new JLabl("1");
		lblCard1.setBounds(70, 180, 84, 125);
		frame.getContentPane().add(lblCard1);
		
		lblCard2 = new JLabel("2");
		lblCard2.setBounds(224, 177, 84, 125);
		frame.getContentPane().add(lblCard2);
		
		lblCard3 = new JLabel("3");
		lblard3.setBounds(378, 177, 84, 125);
		frame.getContentPane().add(lblCard3);
		
		lblCard4 = new JLabel("4");
		lblCard4.stBounds(532, 177, 84, 125);
		frame.getContentPane().add(lblCard4);
		
		lblCard5 = new JLabel("5");
		lblCard5.setBounds(686, 180, 84, 125);
		frame.getContentPane().add(lblCard5);
		
		dealer = new Player("Player 1");
		player= new Player("Player 2");
		
		//Set initial amount for players
		// This amount will come from database
		dealer.setPlayerMoney(200);
		player.setPlayerMoney(200);
		
		initializeGame();
	}
	
public void initializeGame()
	{
		objDeck = new Deck();
		board = new Board();
		rules = new HashMap<Integer, String>();
		
		objDeck.shuffle();
		
		panelDealer.removeAll();
		panelPlayer.removeAll();
		
		panelPlayer.repaint();
		panelDealer.repaint();
		
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

		//dcard0 = new JLabel(dCardImg1);
		//dcard1 = new JLabel(dCardImg2);

		panelDealer.add(new JLabel(dCardImg1));
		panelDealer.add(new JLabel(dCardImg2));

		// Get Player Card Images
		ImageIcon pCardImg1 = player.getCardsInHand().get(0)
				.getImage(false);
		ImageIcon pCardImg2 = player.getCardsInHand().get(1)
				.getImage(false);
		
		panelPlayer.add(new JLabel(pCardImg1));
		panelPlayer.add(new JLabel(pCardImg2));
		
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
		
		lblUserName.setText(player.getName());
		lblDealerName.setText(dealer.getName());
		
	}

class Deal_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		initializeGame();
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
		dealer.setTotalBetAmount(dealer.getTotalBetAmount()+Integer.parseInt(txtDealerBet.getText()));
		
		potMoney += dealer.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
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
		player.setTotalBetAmount(player.getTotalBetAmount()+ Integer.parseInt(txtUsetBet.getText()));
		potMoney += player.getBetMoney();
		txtPotMoney.setText(String.valueOf(potMoney));
		
	}
	
}

class DealerCall_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dealer.setPreviousState(pokerGameState.Call);
		//int callAmount = player.getTotalBetAmount() - dealer.getTotalBetAmount();
		performPlayerCallClick(dealer);
		/*int potAmount = getPotAmount();
		if(potAmount ==0){
			showMessagePotIsEqual();
			return;
		}else{
			dealer.setTotalBetAmount(dealer.getTotalBetAmount()+potAmount);
			dealer.setPlayerMoney(dealer.getPlayerMoney()-potAmount);
			potMoney += potAmount;
			txtPotMoney.setText(String.valueOf(potMoney));	
		}*/
		
	}
	
}

class UserCall_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.setPreviousState(pokerGameState.Call);
		performPlayerCallClick(dealer);
		
		/*int potAmount = getPotAmount();
		if(potAmount ==0){
			showMessagePotIsEqual();
			return;
		}else{
		player.setTotalBetAmount(player.getTotalBetAmount()+potAmount);
		player.setPlayerMoney(player.getPlayerMoney()-potAmount);
		potMoney += potAmount;
		txtPotMoney.setText(String.valueOf(potMoney));
		}*/
	}	
}

class DealerCheck_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dealer.setPreviousState(pokerGameState.Check);
		playerCheck_Click(player);
	}
	
}

class UserCheck_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.setPreviousState(pokerGameState.Check);
		playerCheck_Click(dealer);
	}
	
}

private void playerCheck_Click(Player player){
	if(player.getPreviousState() == pokerGameState.Call || player.getPreviousState() == pokerGameState.Check){
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
			}
		}
		
	}
}

class DealerFold_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class UserFold_click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class DealerRaise_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class UserRaise_Click implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


private void performPlayerCallClick(Player player)
{
	int potAmount = getPotAmount();
	if(potAmount ==0){
		showMessagePotIsEqual();
		return;
	}else{
		player.setTotalBetAmount(player.getTotalBetAmount()+potAmount);
		player.setPlayerMoney(player.getPlayerMoney()-potAmount);
		potMoney += potAmount;
		txtPotMoney.setText(String.valueOf(potMoney));	
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
}

enum pokerGameState{
	Call,
	Check,
	Raise
}
