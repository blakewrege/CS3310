package lab5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Summation2 {

	private JFrame frmSummation;
	private static JTextField input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Summation2 window = new Summation2();
					window.frmSummation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Summation2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSummation = new JFrame();
		frmSummation.setTitle("Summation");
		frmSummation.setBounds(100, 100, 411, 203);
		frmSummation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSummation.getContentPane().setLayout(null);

		input = new JTextField();
		input.setBounds(73, 39, 149, 20);
		frmSummation.getContentPane().add(input);
		input.setColumns(10);
		
		final JLabel lblSum = new JLabel("Sum:");
		lblSum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSum.setBounds(1, 102, 384, 14);
		frmSummation.getContentPane().add(lblSum);
		
		final JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reads and displays the binary file
				try {
					lblSum.setText(readBinaryFile());
				}
				// Informs user if there is an error
				catch(FileNotFoundException e1){
					JOptionPane.showMessageDialog(btnAccept,
						    "File not found, please enter a valid file name",
						    "Invaild File",
						    JOptionPane.WARNING_MESSAGE);
				}catch (IOException e1) {
					JOptionPane.showMessageDialog(btnAccept,
						    "Your file is ethier empty or an unknown I/O error. Please enter name of another binary file.",
						    "Invaild File",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAccept.setBounds(248, 38, 89, 23);
		frmSummation.getContentPane().add(btnAccept);
		
	}

	private static String readBinaryFile() throws IOException
	{
		int number, numSum = 0, count = 0;
		String strNum = "";
	      boolean endOfFile = false; // End of file flag
	      
	      // Open Numbers.dat as a binary file.
	      FileInputStream fstream =
	                      new FileInputStream(input.getText());

	      DataInputStream inputFile =
	                      new DataInputStream(fstream);	     
	      while (!endOfFile)
	      {
	           try
	           {
	        	   
	                number = inputFile.readInt();
	               strNum =  strNum + " " + number;
	               numSum = numSum + number;
	               count++;
	           }
	           catch (EOFException e)
	           {
	              e.getMessage();
	        	   endOfFile = true;
	           }
	      }

	if (count<11){
	      strNum = "The summation of " + strNum + " is " + numSum;
	}else{
		strNum = "Your input file contains " + count + " integers and their summation is " + numSum;	
	}     
	      // Close the file.
	      inputFile.close();
	      return strNum;
	   
	      }	
}


	
