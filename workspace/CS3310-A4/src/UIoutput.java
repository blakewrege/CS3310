//CLASS: UIoutput - used by both Setup and Hashtable
//AUTHOR: Blake Wrege 
//DESCRIPTION: output report to Log.txt
//************************************  Assignment 3  **********************************



import java.io.*;
import java.util.Formatter;

public class UIoutput 
{
	private Formatter output;

	public UIoutput() throws IOException	//Constructor for Setup
	{
		output = new Formatter(new BufferedWriter(new FileWriter("Log.txt", false)));
	}
	
	public UIoutput(String message) throws IOException	//Constructor for UserApp, "String messasge" is only used to distinguish from 
														//another constructor
	{
		 output = new Formatter(new BufferedWriter(new FileWriter("Log.txt", true)));
	}
	
	//Receive status messages.
	public void displayThis(String status) throws IOException
	{
		output.format(status + "\n", null);
	}
	public void displayBrk() throws IOException
	{
		output.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "\n", null);
	}
	

	public void finishUp() throws IOException
	{
		output.close();
	}

	public void displayThis(StringBuffer buf) {
		// TODO Auto-generated method stub
		
	}
}
