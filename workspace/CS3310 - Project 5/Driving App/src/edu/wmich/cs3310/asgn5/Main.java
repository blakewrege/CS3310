package edu.wmich.cs3310.asgn5;

import java.io.IOException;

/******************
 * Driving App
 * Finds the shortest path on a road map for designated
 * start and destination city pairs.
 * @author Caleb Viola
 */
public class Main {
	
	/*********************************************************
	 * A5DemoSpecs.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		SetupUtility.Setup("Europe");
		DrivingApp.Setup("Europe");
		DrivingApp.finishUp();
		
		SetupUtility.Setup("Other");
		DrivingApp.Setup("Other");
		DrivingApp.finishUp();
	}
}
