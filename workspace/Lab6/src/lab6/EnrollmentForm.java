package lab6;


import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class EnrollmentForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField tfPassportNo;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfBirthPlace;
	private JTextField tfVisaNo;
	private JTextArea taRemarks;
	private JComboBox boxVisa;
	private JComboBox boxPassport;
	private JComboBox boxCountry;
	private JButton btnSave;
	ArrayList<String> information = null;

	public EnrollmentForm(final ArrayList<String> information, final AtlantisMenu parent) {
		this.information = information;
		
		setResizable(false);
		getContentPane().setBackground(new Color(238, 238, 238));
		setTitle("New Enrollment");
		this.setSize(598, 246);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblVisaType = new JLabel("Visa Type");
		lblVisaType.setBounds(260, 6, 71, 14);
		getContentPane().add(lblVisaType);
		
		String[] VisaStr = { "A","B","C" };
		boxVisa = new JComboBox(VisaStr);
		boxVisa.setSelectedItem(null);
		boxVisa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkValues();
			}
		});
		boxVisa.setBounds(343, 3, 84, 23);
		getContentPane().add(boxVisa);
		
		JLabel lblPassportNo = new JLabel("Passport No.");
		lblPassportNo.setBounds(6, 32, 98, 14);
		getContentPane().add(lblPassportNo);
		
		tfPassportNo = new JTextField(10);
		tfPassportNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		tfPassportNo.setBounds(115, 29, 139, 20);
		getContentPane().add(tfPassportNo);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(6, 58, 98, 14);
		getContentPane().add(lblFirstName);
		
		tfFirstName = new JTextField(10);
		tfFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		tfFirstName.setBounds(115, 55, 139, 20);
		getContentPane().add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(6, 84, 98, 14);
		getContentPane().add(lblLastName);
		
		tfLastName = new JTextField(10);
		tfLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		tfLastName.setBounds(115, 81, 139, 20);
		getContentPane().add(tfLastName);
		
		JLabel lblPlaceOfBirth = new JLabel("Place of Birth");
		lblPlaceOfBirth.setBounds(6, 136, 98, 14);
		getContentPane().add(lblPlaceOfBirth);
		
		tfBirthPlace = new JTextField(10);
		tfBirthPlace.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		tfBirthPlace.setBounds(115, 133, 139, 20);
		getContentPane().add(tfBirthPlace);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(6, 110, 98, 14);
		getContentPane().add(lblCountry);
		
		String[] PassportStr = { "P", "UNK" };
		boxPassport = new JComboBox(PassportStr);
		boxPassport.setSelectedItem(null);
		boxPassport.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkValues();
			}
		});
		boxPassport.setBounds(115, 3, 84, 23);
		getContentPane().add(boxPassport);
		buildPanel();
		setVisible(true);
		
		JLabel lblPassportType = new JLabel("Passport Type");
		lblPassportType.setBounds(6, 6, 98, 14);
		getContentPane().add(lblPassportType);
		
		JLabel lblVisaNo = new JLabel("Visa No.");
		lblVisaNo.setBounds(260, 32, 71, 14);
		getContentPane().add(lblVisaNo);
		
		tfVisaNo = new JTextField(10);
		tfVisaNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		tfVisaNo.setBounds(343, 29, 86, 20);
		getContentPane().add(tfVisaNo);	

		// Hides EnrollmentForm and displays the AtlantisMenu
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.setVisible(true);
				setVisible(false);	
			}
		});
		
		btnMenu.setBounds(115, 194, 89, 23);
		getContentPane().add(btnMenu);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(260, 58, 71, 14);
		getContentPane().add(lblRemarks);
		
		taRemarks = new JTextArea();
		taRemarks.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkValues();
			}
		});
		taRemarks.setBounds(343, 57, 245, 93);
		getContentPane().add(taRemarks);
		
		String[] CountryStr = {"United States","Mexico","Brazil"};
		boxCountry = new JComboBox(CountryStr);
		boxCountry.setSelectedItem(null);
		boxCountry.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkValues();
			}
		});
		boxCountry.setBounds(115, 107, 139, 22);
		getContentPane().add(boxCountry);
		buildPanel();
		setVisible(true);

		// saves data to information arraylist
		btnSave = new JButton("SAVE");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				File data = new File("data.txt");
				
				information.add(getFirstName() + ", " + 
								getLastName() + ", " +
								getCountry() + ", " +
								getPassportType() + ", " +
								getPassportNo() + ", " + 
								getBirthPlace() + ", " + 
								getVisaType() + ", " + 
								getVisaNo() + ", " +
								getRemarks());
				
				try {
					FileWriter writer = new FileWriter(data,true);
					i = information.size()-1;
					writer.write(information.subList(i, information.size())+ "\n");
					writer.close();
					JOptionPane.showMessageDialog(null, "Saved to data.txt!");
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Unable to save to data.txt...");
				}
			}
		});
		btnSave.setBounds(343, 194, 89, 23);
		getContentPane().add(btnSave);
		buildPanel();
		setVisible(true);
	}

	private void buildPanel() {
	}
	
	// getters for actionlistener to use
	public String getPassportType() {
		if (boxPassport.getSelectedItem() != null)
				return (String) boxPassport.getSelectedItem();
		else
			return "";
		}
	
	public String getPassportNo() {return tfPassportNo.getText();}
	
	public String getFirstName() {return tfFirstName.getText();}
	
	public String getLastName() {return tfLastName.getText();}
	
	public String getCountry() {
		if (boxCountry.getSelectedItem() != null)
			return (String) boxCountry.getSelectedItem();
	else
		return "";
	}
	
	public String getBirthPlace() {return tfBirthPlace.getText();}
	
	public String getVisaType() {
		if (boxVisa.getSelectedItem() != null)
			return (String) boxVisa.getSelectedItem();
	else
		return "";
	}
	
	public String getVisaNo() {return tfVisaNo.getText();}
	
	public String getRemarks() {return taRemarks.getText();}
	
	// method which enables btnSave when all fields have values
	public void checkValues() {
		if (tfPassportNo.getText().compareTo("") != 0 &&
				tfFirstName.getText().compareTo("") != 0 &&
				tfLastName.getText().compareTo("") != 0 &&
				tfBirthPlace.getText().compareTo("") != 0 &&
				tfVisaNo.getText().compareTo("") != 0 &&
				taRemarks.getText().compareTo("") != 0 &&
				getPassportType().compareTo("") != 0 &&
				getCountry().compareTo("") != 0 &&
				getVisaType().compareTo("") != 0) {
			
				btnSave.setEnabled(true);
		}
		if (tfPassportNo.getText().compareTo("") == 0 ||
				tfFirstName.getText().compareTo("") == 0 ||
				tfLastName.getText().compareTo("") == 0 ||
				tfBirthPlace.getText().compareTo("") == 0 ||
				tfVisaNo.getText().compareTo("") == 0 ||
				taRemarks.getText().compareTo("") == 0 ||
				getPassportType().compareTo("") == 0 ||
				getCountry().compareTo("") == 0 ||
				getVisaType().compareTo("") == 0) {
			
				btnSave.setEnabled(false);
		}
	}
}
	



