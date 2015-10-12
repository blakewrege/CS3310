package edu.wmich.cs3310.a2;

import java.io.File;
import java.io.IOException;

/*************************************************
 * Creates and manages a country information table
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class TestDriver {
	
	/*********************************************************
	 * According to A2DemoSpecs
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		File logFile = new File("Log.txt");
		File countryData = new File("CountryData.txt");
		File backup = new File("Backup.txt");
		if(logFile.exists()) logFile.delete();
		if(countryData.exists()) countryData.delete();
		if(backup.exists()) backup.delete();
		
		Setup.main("Sample");
		PrettyPrintUtility.main();
		for (short i = 1; i <= 3; i++)
			UserApp.main(i);
		PrettyPrintUtility.main();
	}
}
