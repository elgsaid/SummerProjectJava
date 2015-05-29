package com.java.poker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.LayoutQueue;

class ImageTutorial extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image1;
	private ImageIcon image2;
	private JLabel label1;
	private JLabel label2;
	
	public ImageTutorial() {
		// TODO Auto-generated constructor stub
		
		setLayout(new FlowLayout());
		image1 = new ImageIcon("C:\\Users\\swap\\Downloads\\classic-cards\\1.png");
		label1 = new JLabel(image1);
		
		image2 = new ImageIcon("C:\\Users\\swap\\Downloads\\classic-cards\\2.png");
		label2 = new JLabel(image2);

		
		add(label1);
		add(label2);
		
	}
	
	public static void main(String[] args)
	{
		ImageTutorial gui = new ImageTutorial();
		
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
		gui.setSize(500, 500);
		gui.setTitle("Black Jack");
		gui.pack();
	}
}