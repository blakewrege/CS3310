package lab6;

import java.util.ArrayList;

/**
 * This class contains the main method.
 * @author Sam, Blake
 * @version 1.0
 */

public class MainTest {
	
	/**
	 * The main method contains the ArrayList with information needed and also initializes the AtlantisMenu.
	 * @param args Command line arguments.
	 */
	
	public static void main(String[] args) {	
		ArrayList<String> information = new ArrayList<String>(); // array list for information
		new AtlantisMenu(information); // pass information and load AtlantisMenu
	}
}
