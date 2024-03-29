//CLASS: UIoutput
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: output report to Log.txt
//************************************  Assignment 5  **********************************

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class UIoutput {
	private Formatter output;

	public UIoutput() throws IOException // Constructor for Setup
	{
		output = new Formatter(new BufferedWriter(new FileWriter("Log.txt", false)));
	}

	public UIoutput(String message) throws IOException // Constructor for
														// UserApp, "String
														// messasge" is only
														// used to distinguish
														// from
														// another constructor
	{
		output = new Formatter(new BufferedWriter(new FileWriter("Log.txt", true)));
	}

	// Receive status messages.
	public void displayThis(String status) throws IOException {
		output.format(status + "\n", null);
	}

	public void finishUp() throws IOException {
		output.close();
	}

	public void displayThis(StringBuffer buf) {
		// TODO Auto-generated method stub

	}
}
