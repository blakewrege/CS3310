package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;

/**
 * Countries Of The World App 1.0
 * Creates table for managing contries information.
 * @author Caleb Viola
 */
public class Main {
	
	/**
	 * Deletes binary file if exists, calls Setup and Userapp according to
	 * A2DemoSpecs.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		File file = new File("CountryData.bin");
		if (file.exists())
			file.delete();
		
		Setup.Table("A2");
		
		for (int i = 5; i <= 7; i++)
			UserApp.Table(i);
		
	}
}
