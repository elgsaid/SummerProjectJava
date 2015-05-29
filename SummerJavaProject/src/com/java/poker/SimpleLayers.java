package com.java.poker;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SimpleLayers extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
public SimpleLayers() {
    super("LayeredPane Demonstration");
   
 
	
/*    // Create 3 buttons
    JButton top = new JButton();
    top.setBackground(Color.white);
    top.setBounds(20, 20, 50, 50);
    
    JButton middle = new JButton();
    middle.setBackground(Color.gray);
    middle.setBounds(30, 30, 550, 550);
    
    JButton bottom = new JButton();
    bottom.setBackground(Color.black);
    bottom.setBounds(40, 40, 550, 550);*/

    
    
    // Place the buttons in different layers
    //lp.add(middle, new Integer(2));
    //lp.add(top, new Integer(3));
    //lp.add(bottom, new Integer(1));
    
    
  }

  public static void main(String[] args) {
	  //SimpleLayers sl = new SimpleLayers();
	  //sl.setVisible(true);
	 ImageIcon image1;
	 ImageIcon image2;
	 JLabel label1;
     JLabel label2;
		
	  JFrame frm = new JFrame();
	  frm.setSize(500, 550);
	  
	  // create Layered Pane for Dealer
	  JLayeredPane lp = new JLayeredPane();
	  
	  lp.setSize(500, 550);
	  lp.setVisible(true);
	  
	  image1 = new ImageIcon("C:\\Users\\swap\\Downloads\\classic-cards\\1.png");
	  label1 = new JLabel(image1);
	  label1.setBounds(60, 60, 100, 100);
		
	  image2 = new ImageIcon("C:\\Users\\swap\\Downloads\\classic-cards\\2.png");
	  label2 = new JLabel(image2);
	  label2.setBounds(50, 50, 100, 100);
   
	  lp.add(label1, new Integer(4));
	  lp.add(label2, new Integer(5));
	    
	  frm.add(lp);
	  frm.setVisible(true);
	  
  }
}