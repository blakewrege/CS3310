//PROGRAM: CrowdOrganizerApp
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 4  **********************************

import java.io.*;
import java.util.Arrays;

public class CrowdOrganizerApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Tells the RawData Class to use RawDataTest.csv
		RawData input = new RawData("LineAt6Am.csv");
		UIoutput output = new UIoutput();
		CustomerPQ stor = new CustomerPQ(output);
		int count = 0;
		
		output.displayThis(">> Program starting");
		output.displayThis("STORE OPENS");
		output.displayThis("Will now insert customers from LineAt6Am into PQ");
		while (input.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			stor.addCustToPQ(input.getCode(), output);
			count = count + 1; // iterator for number of entries sent to HashTables

		}
		output.displayThis("\nFinished putting customers from LineAt6Am into PQ");
		stor.dumpNodes(output);
		input.finishUp();
		
		output.displayThis("\nWill now process CustomerEvents data");
		RawData events = new RawData("CustomerEvents.csv");
		
		while (events.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			switch (events.getCode()) {
			case "CustomerServed":
				stor.serveNextCustInPQ(output);
				break;
			default:
				stor.addCustToPQ(events.getCode().substring(18), output);	
				break;
			}
		}
		output.displayThis("\nFinished putting customers from LineAt6Am into PQ");
		stor.dumpNodes(output);
		events.finishUp();
		
		
		output.finishUp();
		
		


	}
}


