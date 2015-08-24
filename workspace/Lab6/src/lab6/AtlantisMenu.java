package lab6;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

public class AtlantisMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public AtlantisMenu(final ArrayList<String> information){
		final AtlantisMenu parent = this;
		
		getContentPane().setBackground(new Color(238, 238, 238));
		setForeground(new Color(238, 238, 238));
		setResizable(false);
		setTitle("Main Menu");
		this.setSize(500, 144);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
     
		//Starts LoadEnrollment and hides AtlantisMenu
		JButton btnLoadEnrollment = new JButton("Load Enrollment");
		btnLoadEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoadEnrollment(information, parent); // pass information
				setVisible(false);	
			}
		});
		
		btnLoadEnrollment.setBounds(6, 36, 252, 66);
		getContentPane().add(btnLoadEnrollment);
     
		//Starts a new EnrollmentForm and hides AtlantisMenu
		JButton btnNewEnrollment = new JButton("New Enrollment");
		btnNewEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EnrollmentForm(information, parent); // pass information
				setVisible(false);
			}
		});
		
		btnNewEnrollment.setBounds(258, 36, 236, 66);
		getContentPane().add(btnNewEnrollment);
		
		JLabel lblAtlantisEntryCheckpoint = new JLabel("Atlantis Entry Checkpoint System");
		lblAtlantisEntryCheckpoint.setBounds(150, 8, 220, 16);
		getContentPane().add(lblAtlantisEntryCheckpoint);
		buildPanel();
		setVisible(true);
	}
	
	private void buildPanel() {
	}
}