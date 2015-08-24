// Description: 
package class20131202;
import javax.swing.JFrame;
public class FrameSample extends JFrame {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 400;
    public FrameSample() {
        setTitle("Title");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        setVisible(true);
    }
    private void buildPanel() {
    }
}
