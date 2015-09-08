
// Description: 
import javax.swing.*;

import java.awt.event.*;
public class EnrollmentForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField PassportNo;
	private JTextField FirstName;
	private JTextField LastName;
	private JTextField BirthPlace;
	private JTextField VisaTxt;


	public EnrollmentForm() {
		setTitle("New Enrollment Form");
		this.setSize(657, 472);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblVisaType = new JLabel("Visa Type");
		lblVisaType.setBounds(312, 44, 71, 14);
		getContentPane().add(lblVisaType);
		
		String[] VisaStr = { "a","b","c" };
		final JComboBox VisaBox = new JComboBox(VisaStr);
		VisaBox.setBounds(420, 41, 50, 20);
		getContentPane().add(VisaBox);
		
		JLabel lblPassportNo = new JLabel("Passport No.");
		lblPassportNo.setBounds(29, 72, 98, 14);
		getContentPane().add(lblPassportNo);
		
		PassportNo = new JTextField(10);
		PassportNo.setBounds(137, 72, 86, 20);
		getContentPane().add(PassportNo);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(29, 100, 98, 14);
		getContentPane().add(lblFirstName);
		
		FirstName = new JTextField(10);
		FirstName.setBounds(137, 97, 86, 20);
		getContentPane().add(FirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(29, 125, 98, 14);
		getContentPane().add(lblLastName);
		
		LastName = new JTextField(10);
		LastName.setBounds(137, 125, 86, 20);
		getContentPane().add(LastName);
		
		JLabel lblPlaceOfBirth = new JLabel("Place of birth:");
		lblPlaceOfBirth.setBounds(29, 228, 98, 14);
		getContentPane().add(lblPlaceOfBirth);
		
		BirthPlace = new JTextField(10);
		BirthPlace.setBounds(137, 225, 86, 20);
		getContentPane().add(BirthPlace);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(29, 173, 98, 14);
		getContentPane().add(lblCountry);
		
		String[] PassportStr = { "P", "UNK" };
		final JComboBox PassportBox = new JComboBox(PassportStr);
		PassportBox.setBounds(137, 41, 59, 20);
		getContentPane().add(PassportBox);
		buildPanel();
		setVisible(true);
		
		JLabel lblPassportType = new JLabel("Passport Type");
		lblPassportType.setBounds(29, 41, 98, 14);
		getContentPane().add(lblPassportType);
		
		JLabel lblVisaNo = new JLabel("Visa No.");
		lblVisaNo.setBounds(312, 72, 71, 14);
		getContentPane().add(lblVisaNo);
		
		VisaTxt = new JTextField(10);
		VisaTxt.setBounds(393, 72, 86, 20);
		getContentPane().add(VisaTxt);	

// Hides Enrollmentform and displays the AtlantisMenu
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			new AtlantisMenu();	
			setVisible(false);	
			}
		});
		btnMenu.setBounds(134, 290, 89, 23);
		getContentPane().add(btnMenu);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(312, 128, 71, 14);
		getContentPane().add(lblRemarks);
		
		final JTextArea RemarksTxt = new JTextArea();
		RemarksTxt.setBounds(393, 128, 86, 83);
		getContentPane().add(RemarksTxt);
		
		
		String[] CountryStr = {"United States","Mexico","Brazil"};
		final JComboBox CountryBox = new JComboBox(CountryStr);
		CountryBox.setBounds(137, 170, 121, 20);
		getContentPane().add(CountryBox);
		buildPanel();
		setVisible(true);

//		Saves all of the variables to a string
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String a1="",a2="",a3="",a4="",a5="",a6="",a7="",a8="",a9="", SaveStr="";
		        
		        a1 = (String) PassportBox.getSelectedItem();
		        a2 = PassportNo.getText();
		        a3 = FirstName.getText();
		        a4 = LastName.getText();
		        a5 = (String) CountryBox.getSelectedItem();
		        a6 = BirthPlace.getText();
		        a7 = (String) VisaBox.getSelectedItem();
		        a8 = VisaTxt.getText();
		        a9 = RemarksTxt.getText();
		     SaveStr = a1+","+a2+","+a3+","+a4+","+a5+","+a6+","+a7+","+a8+","+a9;
		     System.out.println(SaveStr);
				
			}
		});
		btnSave.setBounds(398, 290, 89, 23);
		getContentPane().add(btnSave);
		buildPanel();
		setVisible(true);


	}


	private void buildPanel() {
		// TODO Auto-generated method stub
		
	}
	
	// Reads the File into a String

	}
	


