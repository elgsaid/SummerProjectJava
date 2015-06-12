package com.java.slotmachine;


import com.java.blackjack.*;
import com.java.casinoserver.Client;
import com.java.casinoserver.LoginDetails;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class SlotMachine {

	private JFrame frmSlotMachine;
	private ArrayList<Card> cardList=null;
	
	private JLabel lblCard1;
	private JLabel lblCard2;
	private JLabel lblCard3;
	Player player;
	
	ImageIcon pCardImg1;
	ImageIcon pCardImg2;
	ImageIcon pCardImg3;
	
	JLabel lblUserName;
	JLabel lblUserPoints;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlotMachine window = new SlotMachine();
					window.frmSlotMachine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public SlotMachine() throws InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws InterruptedException {
		frmSlotMachine = new JFrame();
		frmSlotMachine.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//JOptionPane.showMessageDialog(null, "window closeing");
				//store player score into database while closing it.
				try {
					LoginDetails.UserBalance = String.valueOf(player.getPlayerMoney());
					Client.UpdateUser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmSlotMachine.setResizable(false);
		frmSlotMachine.setTitle("Slot Machine");
		frmSlotMachine.setBounds(100, 100, 450, 296);
		frmSlotMachine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSlotMachine.getContentPane().setLayout(null);
		
		lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserName.setBounds(45, 212, 181, 14);
		frmSlotMachine.getContentPane().add(lblUserName);
		
		lblUserPoints = new JLabel("User Points:");
		lblUserPoints.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUserPoints.setBounds(45, 237, 181, 14);
		frmSlotMachine.getContentPane().add(lblUserPoints);
		
		// Initialize card array
		initialiseCardArray();
		
		pCardImg1 = cardList.get(0).getImage(false);
		pCardImg2 = cardList.get(0).getImage(false);
		pCardImg3 = cardList.get(0).getImage(false);
		
		JButton btnSpin = new JButton("Spin");
		btnSpin.setBounds(303, 207, 90, 25);
		frmSlotMachine.getContentPane().add(btnSpin);
		btnSpin.setFont(new Font("Verdana", Font.BOLD, 12));
		
		lblCard1 = new JLabel("");
		lblCard1.setBounds(51, 31, 84, 125);
		frmSlotMachine.getContentPane().add(lblCard1);
		
		lblCard2 = new JLabel("");
		lblCard2.setBounds(180, 31, 84, 125);
		frmSlotMachine.getContentPane().add(lblCard2);
		
		lblCard3 = new JLabel("");
		lblCard3.setBounds(309, 31, 84, 125);
		frmSlotMachine.getContentPane().add(lblCard3);
		btnSpin.addActionListener(new Spin_Click());
		//Thread.sleep(2000);
		
		lblCard1.setIcon(pCardImg1);
		lblCard2.setIcon(pCardImg2);
		lblCard3.setIcon(pCardImg3);
		
		player = new Player(LoginDetails.UserName);
		player.setPlayerMoney(Integer.parseInt(LoginDetails.UserBalance));
		
		lblUserName.setText("User Name: "+player.getName());
		lblUserPoints.setText("User Points: " + player.getPlayerMoney());
		
		//spinWheel();
	}
	
	void initialiseCardArray(){
		
		cardList= new ArrayList<Card>();
		for(int i=1;i<=6;i++){
			cardList.add(new Card(Suit.values()[1], i));
		}
	}
	
	
	class Spin_Click implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			spinWheel();
		}
	}
	private void spinWheel()
	{
		//panelOne.remove(0);
		//panelTwo.remove(0);
		//panelThree.remove(0);
		
		Random r = new Random();
		
		int panel1 = r.nextInt(cardList.size()) ;
		int panel2 = r.nextInt(cardList.size()) ;
		int panel3 = r.nextInt(cardList.size()) ;
		
		pCardImg1 = cardList.get(panel1).getImage(false);
		pCardImg2 = cardList.get(panel2).getImage(false);
		pCardImg3 = cardList.get(panel3).getImage(false);
		
		
		/*panelOne.repaint();
		panelTwo.repaint();
		panelThree.repaint();*/
		
		lblCard1.setIcon(pCardImg1);
		lblCard2.setIcon(pCardImg2);
		lblCard3.setIcon(pCardImg3);
		
/*		panelOne.add(new JLabel(pCardImg1),0);
		panelTwo.add(new JLabel(pCardImg2),0);
		panelThree.add(new JLabel(pCardImg3),0);*/
		
		
		if(panel1==panel2 && panel2==panel3){
			player.setPlayerMoney(player.getPlayerMoney() + 60);
			lblUserPoints.setText("User Points: " + player.getPlayerMoney());
			
		}else if (panel1 == panel2 || panel1 == panel3 || panel2==panel3){
			player.setPlayerMoney(player.getPlayerMoney() + 30);
			lblUserPoints.setText("User Points: " + player.getPlayerMoney());
		} else{
			player.setPlayerMoney(player.getPlayerMoney() - 5);
			lblUserPoints.setText("User Points: " + player.getPlayerMoney());
		}
	}
}
	
	

