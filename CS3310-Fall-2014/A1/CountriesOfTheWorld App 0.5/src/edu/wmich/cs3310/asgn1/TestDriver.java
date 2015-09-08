package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;

/************************************************
 * Creates table for managing country information
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class TestDriver {
	
	/*********************************************************
	 * According to A1DemoSpecs
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		File logFile = new File("Log.txt");
		File backup = new File("Backup.txt");
		if(logFile.exists()) logFile.delete();
		if(backup.exists()) backup.delete();
		
		Setup.main("Sample");
		PrettyPrintUtility.main();
		for (int i = 1; i <= 3; i++)
			UserApp.main(i);
		PrettyPrintUtility.main();
		Setup.main("");
		for (int i = 4; i <= 4; i++)
			UserApp.main(i);
	}
}
