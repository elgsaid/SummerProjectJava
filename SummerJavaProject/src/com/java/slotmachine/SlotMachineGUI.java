package com.java.slotmachine;

import java.awt.*;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class SlotMachineGUI {
     
    private JButton btnCash, btnSpin;
    private JFrame frmFrame;
    private JLabel lblCredits, lblLost, lblMatchThree, lblMatchTwo, lblMoney, lblReel1, lblReel2, lblReel3, lblStatus, lblWon;
    private JPanel spinPanel, pnlReel1, pnlReel2, pnlReel3;
    private JSeparator sepStats, sepStats2;
    private int credits = 100, boughtCredits = 100, bet = 15, matchThree, matchTwo, win, lost;
    private double payout = 25.0, creditBuyout = 10.0, funds;
    private int reel1 = 1, reel2 = 1, reel3 = 1; 
    
    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    
    private DecimalFormat df = new DecimalFormat("0.00");
         
    public SlotMachineGUI() {
        createForm();
        getImages();
        addFields();
        addButtons();
        layoutFrame();
        layoutReels();
        layoutOther();
    }
     
    /** Creates the JFrame and Panels. */
    private void createForm() {
         
        frmFrame = new JFrame();
        frmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frmFrame.setTitle("JAVA PROJECT SLOT MACHINE");
        frmFrame.setResizable(false);
        //frmFrame.setBackground(Color.green);
        frmFrame.setVisible(true);
         
        spinPanel = new JPanel();
        //pnlReels.setBorder(BorderFactory.createEtchedBorder(1));
         
        pnlReel1 = new JPanel();
        pnlReel1.setBackground(new Color(255, 215, 0));
        pnlReel1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        pnlReel2 = new JPanel();
        pnlReel2.setBackground(new Color(255, 216, 0));
        pnlReel2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        pnlReel3 = new JPanel();
        pnlReel3.setBackground(new java.awt.Color(255, 215, 0));
        pnlReel3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
         
    }
     
    /** Adds labels to the form. */
    private void addFields() {
         
        lblReel1 = new JLabel();
        lblReel2 = new JLabel();
        lblReel3 = new JLabel();
         
        sepStats = new JSeparator();
        lblMatchTwo = new JLabel();
        lblMatchTwo.setText("Matched Two: ");
        lblMatchThree = new JLabel();
        lblMatchThree.setText("Matched Three: ");
        lblWon = new JLabel();
        lblWon.setText("Won: ");
         
        sepStats2 = new JSeparator();
        sepStats2.setOrientation(SwingConstants.VERTICAL);
        lblCredits = new JLabel();
        lblCredits.setText("Credits: "+credits);
        lblMoney = new JLabel();
        lblMoney.setText("Money: $"+df.format(funds));
        lblLost = new JLabel();
        lblLost.setText("Lost: ");
         
        lblStatus = new JLabel();
        lblStatus.setBackground(new Color(255, 255, 255));
        lblStatus.setFont(new Font("Arial", 1, 14));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setText("Welcome to NYU");
         
        lblReel1.setIcon(images.get(reel1));
        lblReel2.setIcon(images.get(reel2));
        lblReel3.setIcon(images.get(reel3));
         
    }
     
    /** Adds buttons to the form. */
    private void addButtons() {
         
        btnSpin = new JButton();
        btnSpin.setBackground(new Color(255, 0, 0));
        btnSpin.setText("Roll");
        btnSpin.setToolTipText("Click to roll the cards!!");        
        btnSpin.addActionListener(new btnSpinHandler());
         
        btnCash = new JButton();
        btnCash.setBackground(new Color(255, 0, 0));
        btnCash.setText("Buy Credits");
        btnCash.setToolTipText("$"+df.format(bet)+" converts to "+boughtCredits+" credits.");
        btnCash.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCash.addActionListener(new BuyCreditsHandler());
         
         
    }
     
    /** Lays out the frame. */
    private void layoutFrame() {
         
        GroupLayout frameLayout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(frameLayout);
        
    }
     
    /** Lays out the panels and reels. */
    private void layoutReels() {
         
        GroupLayout spinPanelLayout = new GroupLayout(spinPanel);
        spinPanel.setLayout(spinPanelLayout);
        spinPanelLayout.setHorizontalGroup(
        spinPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(spinPanelLayout.createSequentialGroup()
        .addComponent(pnlReel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(pnlReel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(pnlReel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        )
        );
        
        spinPanelLayout.setVerticalGroup(
        spinPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(spinPanelLayout.createSequentialGroup()
        //.addContainerGap()
        .addGroup(spinPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
        .addComponent(pnlReel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pnlReel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pnlReel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
         
        GroupLayout pnlReel1Layout = new GroupLayout(pnlReel1);
        pnlReel1.setLayout(pnlReel1Layout);
        pnlReel1Layout.setHorizontalGroup(
        pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel1)
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
        pnlReel1Layout.setVerticalGroup(
        pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel1)
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
         
        GroupLayout pnlReel2Layout = new GroupLayout(pnlReel2);
        pnlReel2.setLayout(pnlReel2Layout);
        pnlReel2Layout.setHorizontalGroup(
        pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel2)
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
        pnlReel2Layout.setVerticalGroup(
        pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel2)
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
         
        GroupLayout pnlReel3Layout = new GroupLayout(pnlReel3);
        pnlReel3.setLayout(pnlReel3Layout);
        pnlReel3Layout.setHorizontalGroup(
        pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel3)
        //.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
        );
        pnlReel3Layout.setVerticalGroup(
        pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblReel3)

        )
        );
         
    }
     
    /** lays out the remaining labels, check boxes, progress bars, etc. */
    private void layoutOther() {
         
        GroupLayout layout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(layout);
         
        layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        .addComponent(btnSpin,GroupLayout.PREFERRED_SIZE,325,GroupLayout.PREFERRED_SIZE)
        .addComponent(spinPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(sepStats, GroupLayout.Alignment.TRAILING)
        .addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
        .addComponent(lblMatchTwo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblWon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(sepStats2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        .addComponent(lblLost, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblCredits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)        
        .addComponent(btnCash,GroupLayout.PREFERRED_SIZE,325,GroupLayout.PREFERRED_SIZE)
        ))
        
        
        .addContainerGap())))
        );
         
        layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(spinPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(btnSpin, GroupLayout.PREFERRED_SIZE,56, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(sepStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblWon, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMatchTwo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        .addComponent(sepStats2)
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblLost, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        )
        
        .addComponent(btnCash ,GroupLayout.PREFERRED_SIZE,56, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addContainerGap())
        );
         
        frmFrame.pack();
         
    }
     
    /** Performs action when Buy Credits button is clicked. */
    class BuyCreditsHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            buyCredits();
        }
    }
     
    /** if the player has enough funds credits are added. */
    public void buyCredits() {
        if (funds >= creditBuyout) {
            funds -= creditBuyout;
            lblMoney.setText("Money: $"+df.format(funds));
            credits += boughtCredits;
            lblCredits.setText("Credits: "+credits);
            lblStatus.setText("+"+boughtCredits+" credits purchased! -$"+df.format(creditBuyout));
            } else {
            lblStatus.setText("Insufficient $ to purchase credits!");
        }
        buyCreditsCheck();
    }
     
    /** if user has enough funds to buy credits changes buttons colour to alert user. */
    public void buyCreditsCheck() {
        if (funds < bet) {
            btnCash.setBackground(new java.awt.Color(0, 0, 255));
            } else {
            btnCash.setBackground(new java.awt.Color(50, 0, 50));
        }
    }
     
    class btnSpinHandler implements ActionListener {
    	
        public void actionPerformed(ActionEvent event) {
        	
            if (funds < creditBuyout && credits < bet) {
                lblStatus.setText("No link");
                } else if ((credits - bet) >= 0) {
                pnlReel1.setBackground(new java.awt.Color(255, 215, 0));
                pnlReel2.setBackground(new java.awt.Color(255, 215, 0));
                pnlReel3.setBackground(new java.awt.Color(255, 215, 0));
                performWinnigSeq();
                checkMatechedColumns();
                } else {
                lblStatus.setText("Bet is "+bet+" credits, purchase more with $!");
            }
            
            buyCreditsCheck();
        }
    }
     
    /*
     * Generate the winning sequence for 
     */
    public void performWinnigSeq() {
        	Random rand = new Random();
        
            int WinningNumber = rand.nextInt(10); 
            reel1 = rand.nextInt(images.size());       
        	
            switch(WinningNumber){
            case 0:
            	reel2 = reel1;
                reel3 = reel1;
                break;
            case 1:
            	 reel2 = reel1;
            	 break;
            case 2:
            	reel3 = reel1;
            	break;
            case 3:
            	if (reel1 >= 0 ) {
                    reel2 = reel1 + 1;
                    reel3 = reel1 + 1;
                    } if (reel1 == images.size()-1) {
                    reel2 = reel1 - 1;
                    reel3 = reel1 - 1;
                }
                break;
            }           
            
        // Set the reel image
        if(WinningNumber>=0 && WinningNumber<=3 )
        setReelIcon(reel1, reel2, reel3);
        else
        setReelIcon(rand.nextInt(images.size()), rand.nextInt(images.size()), rand.nextInt(images.size()));	//loosing sequence of the Game
    }
     
    /** Sets the reels icon based on loaded image in images ArrayList. */
    public void setReelIcon(int ico1, int ico2, int ico3) {
        lblReel1.setIcon(images.get(ico1)); // icon = the ArrayList index = random reel number
        lblReel2.setIcon(images.get(ico2));
        lblReel3.setIcon(images.get(ico3));
    }
     
    /** Checks for number matches and adjusts score depending on result. */
    public void checkMatechedColumns() {
        if (reel1 == reel2 && reel2 == reel3) {
            lblStatus.setText("You matched THREE symbols ("+images.get(reel1).getDescription()+")! +$"+df.format(getPrize(payout))+"!");
            lblMatchThree.setText("Matched Three: "+matchThree());
            pnlReel1.setBackground(new java.awt.Color(0, 255, 0)); 
            pnlReel2.setBackground(new java.awt.Color(0, 255, 0));
            pnlReel3.setBackground(new java.awt.Color(0, 255, 0));
            } else if (reel1 == reel2 || reel1 == reel3) {
            lblStatus.setText("You matched TWO symbols ("+images.get(reel1).getDescription()+")! +$"+df.format(getPrize(payout))+"!");
            lblMatchTwo.setText("Matched Two: "+matchTwo());
            if (reel1 == reel2) {
                pnlReel1.setBackground(new java.awt.Color(0, 255, 0)); 
                pnlReel2.setBackground(new java.awt.Color(0, 255, 0));
                } else if (reel1 == reel3){
                pnlReel1.setBackground(new java.awt.Color(0, 255, 0)); 
                pnlReel3.setBackground(new java.awt.Color(0, 255, 0));
            }
            } else if (reel2 == reel3) {
            lblStatus.setText("You matched TWO symbols ("+images.get(reel2).getDescription()+")! +$"+df.format(getPrize(payout))+"!");
            lblMatchTwo.setText("Matched Two: "+matchTwo());
            pnlReel2.setBackground(new java.awt.Color(255, 0, 0)); 
            pnlReel3.setBackground(new java.awt.Color(255, 0, 0));
            } else {
            lblStatus.setText("Sorry, you didn't match any symbols. -"+bet+" credits!");
            lblLost.setText("Lost: "+lose());
        }
        lblCredits.setText("Credits: "+(credits -= bet)); 
        lblMoney.setText("Money: $"+df.format((funds += getPrize(payout)))); 
        lblWon.setText("Wins: "+win());
    }
     

    public double getPrize(double prize) {
        if (reel1 == reel2 && reel2 == reel3) {
                prize = payout; 
                
            } else if (reel1 == reel2 || reel1 == reel3 || reel2 == reel3) {
            
                prize = payout / 5; 
           
            } else {
            prize = 0; 
        }
        return prize;
    }
     
      
	/*
	 * Get images from the project directory
	 */
    public void getImages() {
    	
    	String workingDir = System.getProperty("user.dir");
    	 
        images.add(createImageIcon(workingDir+"\\images\\1H.png", "1H"));
        images.add(createImageIcon(workingDir+"\\images\\2H.png", "2H"));
        images.add(createImageIcon(workingDir+"\\images\\3H.png", "3H"));
        images.add(createImageIcon(workingDir+"\\images\\4H.png", "4H"));
        images.add(createImageIcon(workingDir+"\\images\\5H.png", "5H"));
        images.add(createImageIcon(workingDir+"\\images\\6H.png", "6H"));
        images.add(createImageIcon(workingDir+"\\images\\7H.png", "7H"));
        images.add(createImageIcon(workingDir+"\\images\\8H.png", "8H"));
        images.add(createImageIcon(workingDir+"\\images\\9H.png", "9H"));
    }
     
    /** Create a new ImageIcon, unless the URL is not found. */
    public ImageIcon createImageIcon(String path, String description) {
            return new ImageIcon(path, description);
    }
     
    /** Increments matchThree by 1 and returns value. */
    public int matchThree() {
        matchThree++;
        return matchThree;
    }
     
    /** Increments matchTwo by 1 and returns value. */
    public int matchTwo() {
        matchTwo++;
        return matchTwo;
    }
     
    /** Increments lost by 1 and returns value. */
    public int lose() {
        lost++;
        return lost;
    }
     
    /** Increments win by 1, increases progress bar and returns value. */
    public int win() {
        win = matchThree + matchTwo;
        return win;
    }
     
    public static void main(String args[]) {
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
             
            public void run() {
                new SlotMachineGUI();
            }
        });
         
    }
     
}