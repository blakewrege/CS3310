package edu.wmich.cs3310.a3;

import java.io.File;
import java.io.IOException;

/*************************************************
 * Creates and manages a country information table
 * Countries Of The World App 1.5
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
		File countryData = new File("CountryData.bin");
		if(logFile.exists()) logFile.delete();
		if(countryData.exists()) countryData.delete();
		
		Setup.main("Sample");
		PrettyPrintUtility.main();
		UserApp.main(1);
	}
}
