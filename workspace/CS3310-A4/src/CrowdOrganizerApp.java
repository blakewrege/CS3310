//PROGRAM: CrowdOrganizerApp
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 3  **********************************

import java.io.*;

public class CrowdOrganizerApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Tells the RawData Class to use RawDataTest.csv
		RawData input = new RawData("LineAt6Am.csv");
		UIoutput output = new UIoutput();
		CustomerPQ stor = new CustomerPQ(output);
		int count = 0;
		
		output.displayThis(">> Program starting");
		output.displayThis("STORE OPENS");
		output.displayThis("Will now insert customers from LineAt6Am into PQ.");
		while (input.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			//System.out.println(input.getCode());
			stor.insert(count,input.getCode(), output);
			count = count + 1; // iterator for number of entries sent to HashTables

		}
		stor.dumpNodes(output);
		input.finishUp();
		
		
		RawData events = new RawData("CustomerEvents.csv");
		
		while (events.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			System.out.println(events.getCode());
			//stor.insert(count,input.getCode(), output);
			count = count + 1; // iterator for number of entries sent to HashTables

		}
		events.finishUp();
		
		
		
		
		
		
		output.finishUp();
		
		


	}
}

