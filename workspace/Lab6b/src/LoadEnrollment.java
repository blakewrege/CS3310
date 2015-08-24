import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadEnrollment extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	private JTextField textField;

	public LoadEnrollment() {
		setTitle("Title");
		this.setSize(500, 363);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 83, 464, 211);
		getContentPane().add(textArea);
		
		JLabel lblEnterFileName = new JLabel("Enter File Name");
		lblEnterFileName.setBounds(10, 16, 108, 14);
		getContentPane().add(lblEnterFileName);
		
		textField = new JTextField(10);
		textField.setBounds(144, 13, 141, 20);
		getContentPane().add(textField);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.setBounds(373, 11, 89, 23);
		getContentPane().add(btnLoad);
		
		//Hides LoadEnrollment and displays AtlantisMenu
		JButton btnMenu = new JButton("MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AtlantisMenu();
				setVisible(false);
			}
		});
		btnMenu.setBounds(373, 49, 89, 23);
		getContentPane().add(btnMenu);
		buildPanel();
		setVisible(true);
	}

	private void buildPanel() {
	}
}

