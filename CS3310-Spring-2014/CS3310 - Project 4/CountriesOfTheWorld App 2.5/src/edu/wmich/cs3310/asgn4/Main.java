package edu.wmich.cs3310.asgn4;

import java.io.File;
import java.io.IOException;

/******************
 * Countries Of The World App 2.5
 * Creates table for managing countries information.
 * @author Caleb Viola
 */
public class Main {
	
	/*********************************************************
	 * Deletes TheLog if it exists, Userapp according to
	 * A4DemoSpecs.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		File file = new File("TheLog.txt");
		if (file.exists())
			file.delete();
		
		for (int i = 1; i <= 3; i++)
			UserApp.userAppMain(i);
	}
}
