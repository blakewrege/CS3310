// Description: 
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AtlantisMenu extends JFrame {
	private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 400;
    public AtlantisMenu() {
        setTitle("Menu");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
      //Starts LoadEnrollment and hides AtlantisMenu
        JButton btnNewButton = new JButton("Load Enrollment");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new LoadEnrollment();
        		setVisible(false);	
        	}
        });
        btnNewButton.setBounds(10, 76, 168, 220);
        getContentPane().add(btnNewButton);
        
        //Starts a new EnrollmentForm and hides AtlantisMenu
        JButton btnNewEnrollment = new JButton("New Enrollment");
        btnNewEnrollment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new EnrollmentForm();
        		setVisible(false);
        	}
        });
        btnNewEnrollment.setBounds(306, 76, 168, 220);
        getContentPane().add(btnNewEnrollment);
        buildPanel();
        setVisible(true);
    }
    private void buildPanel() {
    }
}
