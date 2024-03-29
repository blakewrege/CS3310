//CLASS: UIoutput - used by both Setup and UserApp
//AUTHOR: Jia GUO
//DESCRIPTION: output report to Log.txt
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************



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
		output.format(status + "\n");
	}
	
	//receive BST node and display (formatted)
	public void displayThis(Node BST) throws IOException
	{
		output.format("%s %03d %-24s %-13s %,11d %,13d %4.1f\n",
               BST.countryCode, Integer.parseInt(BST.id), BST.name, BST.continent, Long.parseLong(BST.area), 
               Long.parseLong(BST.population), Double.parseDouble(BST.life));
	}
	
	public void finishUp() throws IOException
	{
		output.close();
	}
}
