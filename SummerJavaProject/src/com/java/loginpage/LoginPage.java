package com.java.loginpage;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.java.blackjack.BlackJackGUI;
import com.java.slotmachine.SlotMachine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import com.java.casinoserver.*;

public class LoginPage {

	private JFrame frmLoginPage;
	private JTextField textUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setBounds(100, 100, 364, 208);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);

		final JRadioButton rdbtnBlackJack = new JRadioButton("BlackJack");
		rdbtnBlackJack.setBounds(141, 62, 109, 23);
		frmLoginPage.getContentPane().add(rdbtnBlackJack);

		final JRadioButton rdbtnPoker = new JRadioButton("Poker");
		rdbtnPoker.setBounds(141, 87, 109, 23);
		frmLoginPage.getContentPane().add(rdbtnPoker);

		final JRadioButton rdbtnSlotMachine = new JRadioButton("Solt Machine");
		rdbtnSlotMachine.setBounds(141, 112, 109, 23);
		frmLoginPage.getContentPane().add(rdbtnSlotMachine);

		textUserName = new JTextField();
		textUserName.setBounds(141, 35, 143, 20);
		frmLoginPage.getContentPane().add(textUserName);
		textUserName.setColumns(10);

		JLabel lblUserName = new JLabel("Enter User Name");
		lblUserName.setBounds(22, 35, 99, 14);
		frmLoginPage.getContentPane().add(lblUserName);

		JLabel lblSelectGame = new JLabel("Select Game");
		lblSelectGame.setBounds(22, 66, 76, 14);
		frmLoginPage.getContentPane().add(lblSelectGame);

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textUserName.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null,
							"Please Enter User Name");
					return;
				}

				if (rdbtnBlackJack.isSelected() == true) {
					// BlackJackGUI objBJGUI = new BlackJackGUI();
					try {
						Client.SelectOrInsertUser(textUserName.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					BlackJackGUI.main(null);
				}

				if (rdbtnSlotMachine.isSelected() == true) {
					
					try {
						Client.SelectOrInsertUser(textUserName.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					SlotMachine.main(null);

				}

			}
		});
		btnStartGame.setBounds(227, 135, 99, 23);
		frmLoginPage.getContentPane().add(btnStartGame);
	}
}
