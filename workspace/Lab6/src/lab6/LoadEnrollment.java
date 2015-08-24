package lab6;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadEnrollment extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfFileName;
	private JButton btnLoad;
	ArrayList<String> information;

	public LoadEnrollment(final ArrayList<String> information, final AtlantisMenu parent) {
		this.information = information;
		
		setResizable(false);
		setTitle("Load Enrollment");
		this.setSize(670, 287);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		final JTextArea taData = new JTextArea();
		taData.setEditable(false);
		taData.setBounds(10, 42, 654, 211);
		getContentPane().add(taData);
		
		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setBounds(10, 16, 70, 14);
		getContentPane().add(lblFileName);
		
		tfFileName = new JTextField(10);
		tfFileName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				checkValues();
			}
		});
		tfFileName.setBounds(78, 13, 182, 20);
		getContentPane().add(tfFileName);
		
		// loads file name inputed and outputs information
		btnLoad = new JButton("LOAD");
		btnLoad.setEnabled(false);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner scan = new Scanner(new File(tfFileName.getText()));
					taData.setText("Loading " + tfFileName.getText() );
					while (scan.hasNextLine()) {
						taData.append("\n"+scan.nextLine());
					}
				}
				catch (Exception fileNotFound) {
					taData.setText("Loading '" + tfFileName.getText() + "':\n" + "File not found...\n");
				}
			}
		});
		btnLoad.setBounds(272, 13, 89, 23);
		getContentPane().add(btnLoad);
		
		// Hides LoadEnrollment and displays AtlantisMenu
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.setVisible(true);
				setVisible(false);
			}
		});
		btnMenu.setBounds(575, 13, 89, 23);
		getContentPane().add(btnMenu);
		buildPanel();
		setVisible(true);
	}

	private void buildPanel() {
	}
	
	// method which enables btnLoad when tfFileName has value
	public void checkValues() {
		if (tfFileName.getText().compareTo("") != 0) {
			btnLoad.setEnabled(true);
		}
		if (tfFileName.getText().compareTo("") == 0) {
			btnLoad.setEnabled(false);
		}
	}
}


