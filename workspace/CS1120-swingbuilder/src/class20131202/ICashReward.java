package class20131202;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

public class ICashReward extends JFrame {

    private JPanel panel;
    private JLabel message0;
    private JLabel message1;
    private JTextField arg1;
    private JLabel message2;
    private JTextField arg2;
    private JLabel message3;
    private JTextField arg3;
    private JLabel message4;
    private JTextField arg4;
    private JTextField answer;
    private JButton multButton;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 400;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final Action action = new BPAction();
    private final Action action_1 = new CitiAction();
    private final Action action_2 = new DiscoverAction();
    private double b1=0;
    private double b2=0;
    private double b3=0;
    private double b4=0;
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
    return Double.valueOf(twoDForm.format(d));
    }
    
    public  ICashReward() {
        setTitle("Find Your Best Credit Card");

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();

        getContentPane().add(panel);
        
        JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("BP Credit Card");
        rdbtnmntmNewRadioItem.setBounds(37, 83, 125, 22);
        rdbtnmntmNewRadioItem.setAction(action);
        buttonGroup.add(rdbtnmntmNewRadioItem);
        
        JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Citi Credit Card");
        rdbtnmntmNewRadioItem_1.setBounds(37, 111, 125, 22);
        rdbtnmntmNewRadioItem_1.setAction(action_1);
        buttonGroup.add(rdbtnmntmNewRadioItem_1);
        
        JRadioButtonMenuItem rdbtnmntmNewRadioItem_2 = new JRadioButtonMenuItem("Discover Card");
        rdbtnmntmNewRadioItem_2.setBounds(37, 136, 125, 22);
        rdbtnmntmNewRadioItem_2.setAction(action_2);
        buttonGroup.add(rdbtnmntmNewRadioItem_2);
       
        panel.setLayout(null);
        panel.add(message0);
        panel.add(rdbtnmntmNewRadioItem);
        panel.add(message1);
        panel.add(arg1);
        panel.add(rdbtnmntmNewRadioItem_1);
        panel.add(message4);
        panel.add(arg2);
        panel.add(rdbtnmntmNewRadioItem_2);
        panel.add(message2);
        panel.add(arg3);
        panel.add(message3);
        panel.add(arg4);
        panel.add(multButton);
        panel.add(answer);

        setVisible(true);
    }

    private void buildPanel() {
        message0 = new JLabel("Your Anual Spending Estimate");
        message0.setBounds(124, 11, 183, 14);
    	message1 = new JLabel("Restuarant");
    	message1.setBounds(222, 83, 71, 14);
        arg1 = new JTextField(10);
        arg1.setBounds(309, 80, 86, 20);
        message2 = new JLabel("Amazon");
        message2.setBounds(222, 136, 71, 14);
        arg2 = new JTextField(10);
        arg2.setBounds(309, 105, 86, 20);
        message3 = new JLabel("Grocery");
        message3.setBounds(222, 161, 71, 14);
        arg3 = new JTextField(10);
        arg3.setBounds(309, 133, 86, 20);
        message4 = new JLabel("Gas");
        message4.setBounds(222, 111, 69, 14);
        arg4 = new JTextField(10);
        arg4.setBounds(309, 158, 86, 20);
        multButton = new JButton("Calculate Cash Rewards");
        multButton.setBounds(124, 212, 183, 23);

        answer = new JTextField(10);
        answer.setHorizontalAlignment(SwingConstants.CENTER);
        answer.setBounds(106, 241, 218, 20);

        multButton.addActionListener(new Caclulate());

        arg1.addMouseListener(new MyMouseListener());
                arg2.addMouseListener(new MyMouseListener());


        panel = new JPanel();
    }

    private class Caclulate implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            double a1=0,a2=0,a3=0,a4=0;
            a1 = Double.parseDouble(arg1.getText())*b1;
            a2 = Double.parseDouble(arg2.getText())*b2;
            a3 = Double.parseDouble(arg3.getText())*b3;
            a4 = Double.parseDouble(arg4.getText())*b4;
            answer.setText("" + roundTwoDecimals(a1+a2+a3+a4));        
        }
    }


    private class MyMouseListener implements MouseListener {

        public void mousePressed(MouseEvent e) {
       answer.setText("");
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    }
	private class BPAction extends AbstractAction {
		public BPAction() {
			putValue(NAME, "BP Credit Card");
		}
		public void actionPerformed(ActionEvent e) {
			 b1 = .01;
			 b2 = .01;
			 b3 = .01;
			 b4 = .05;		 
		}
	}
	private class CitiAction extends AbstractAction {
		public CitiAction() {
			putValue(NAME, "Citi Credit Card");
		}
		public void actionPerformed(ActionEvent e) {
			 b1 = .01;
			 b2 = .01;
			 b3 = .03;
			 b4 = .02;	
		}
	}
	private class DiscoverAction extends AbstractAction {
		public DiscoverAction() {
			putValue(NAME, "Discover Card");
		}
		public void actionPerformed(ActionEvent e) {
			 b1 = .03;
			 b2 = .05;
			 b3 = .01;
			 b4 = .01;	
		}
	}
}
