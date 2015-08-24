package class20131202;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CellPlans3 {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CellPlans3 window = new CellPlans3();
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
	public CellPlans3() {
		initialize();
	}
	static String str1="",str2="",str3="",str4="";
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Extra features");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(296, 31, 125, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Type of Phone");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(151, 31, 125, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Minutes Per Month");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 31, 125, 14);
		frame.getContentPane().add(label_2);
		
		final JLabel plan = new JLabel("");
		plan.setHorizontalAlignment(SwingConstants.CENTER);
		plan.setBounds(10, 210, 415, 14);
		frame.getContentPane().add(plan);
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("300 minutes: $45");
		radioButtonMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str1 = "300 minutes: $45";
			}
		});
		buttonGroup.add(radioButtonMenuItem);
		radioButtonMenuItem.setBounds(10, 53, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem);
		
		JRadioButtonMenuItem radioButtonMenuItem_1 = new JRadioButtonMenuItem("800 Minutes: $65");
		radioButtonMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str1 = "800 minutes: $65";
			}
		});
		buttonGroup.add(radioButtonMenuItem_1);
		radioButtonMenuItem_1.setBounds(10, 81, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem_1);
		
		JRadioButtonMenuItem radioButtonMenuItem_2 = new JRadioButtonMenuItem("1500 Minutes: $99");
		radioButtonMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str1 = "1500 Minutes: $99";
			}
		});
		buttonGroup.add(radioButtonMenuItem_2);
		radioButtonMenuItem_2.setBounds(10, 106, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem_2);
		

		JRadioButtonMenuItem radioButtonMenuItem_3 = new JRadioButtonMenuItem("Model 100: $29.95");
		radioButtonMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str2 = "Model 100: $29.95";
			}
		});
		buttonGroup_1.add(radioButtonMenuItem_3);
		radioButtonMenuItem_3.setBounds(151, 52, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem_3);
		
		JRadioButtonMenuItem radioButtonMenuItem_4 = new JRadioButtonMenuItem("Model 110: $49.95");
		radioButtonMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str2 = "Model 110: $49.95";
			}
		});
		buttonGroup_1.add(radioButtonMenuItem_4);
		radioButtonMenuItem_4.setBounds(151, 80, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem_4);
		
		JRadioButtonMenuItem radioButtonMenuItem_5 = new JRadioButtonMenuItem("Model 200: $99.95");
		radioButtonMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str2 = "Model 200: $99.95";
			}
		});
		buttonGroup_1.add(radioButtonMenuItem_5);
		radioButtonMenuItem_5.setBounds(151, 105, 125, 22);
		frame.getContentPane().add(radioButtonMenuItem_5);
		
		final JCheckBox checkBox = new JCheckBox("Voicemail: $5");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str3 = "Voicemail: $5";
			}
		});
		checkBox.setBounds(306, 52, 115, 23);
		frame.getContentPane().add(checkBox);
		
		final JCheckBox checkBox_1 = new JCheckBox("Texting: $10");
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str4 = "Texting: $10";
			}
		});
		checkBox_1.setBounds(306, 90, 115, 23);
		frame.getContentPane().add(checkBox_1);
		
		final JCheckBox checkBox_2 = new JCheckBox("None of these");
		checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBox.setSelected(false);
				checkBox_1.setSelected(false);
				checkBox.setEnabled(false);
				checkBox_1.setEnabled(false);
				str3 = "";
				str4 = "";
				
			}
		});
		checkBox_2.setBounds(306, 127, 115, 23);
		frame.getContentPane().add(checkBox_2);
		
		JButton display = new JButton("display");
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			plan.setText(str1 +"   "+str2 +"   " + str3 + "   " + str4);	
			}
		});
		display.setBounds(151, 163, 125, 23);
		frame.getContentPane().add(display);
		
		
	}
}
