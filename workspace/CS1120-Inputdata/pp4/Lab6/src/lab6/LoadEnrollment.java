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

/**
 * This class contains the LoadEnrollment GUI and loads information from data.txt.
 * @author Sam, Blake
 * @version 1.0
 */

public class LoadEnrollment extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfFileName;
	private JButton btnLoad;
	ArrayList<String> information;

	/**
	 * This consturctor initializes the GUI and contains ActionListeners for the buttons "Menu" and "Load".
	 * @param information The ArrayList where information entered by the user is put.
	 * @param parent References AtlantisMenu.
	 */
	
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
		
		btnLoad = new JButton("LOAD");
		btnLoad.setEnabled(false);
		btnLoad.addActionListener(new ActionListener() {
			
			/**
			 * This method loads data from "data.txt" using a scanner and sets the text to the textArea when the "Load" button is selected.
			 */
			
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner scan = new Scanner(new File(tfFileName.getText()));
					taData.setText("Loading '" + tfFileName.getText() + "':\n");
					while (scan.hasNextLine()) {
						taData.append(scan.nextLine() + "\n");
					}
				}
				catch (Exception fileNotFound) {
					taData.setText("Loading '" + tfFileName.getText() + "':\n" + "File not found...\n");
				}
			}
		});
		btnLoad.setBounds(272, 13, 89, 23);
		getContentPane().add(btnLoad);
	
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			
			/**
			 * This method sets the LoadEnrollment GUI invisible and sets the parent AtlantisMenu GUI visible when "Menu" is pressed.
			 */
			
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
	
	/**
	 * This method checks the value where you input the file name and if it is empty, the "Load" button is disabled. KeyListeners are used
	 * to call this method every time a key is released.
	 */
	
	public void checkValues() {
		if (tfFileName.getText().compareTo("") != 0) {
			btnLoad.setEnabled(true);
		}
		if (tfFileName.getText().compareTo("") == 0) {
			btnLoad.setEnabled(false);
		}
	}
}


