//PROGRAM: Setup
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 3  **********************************

import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Tells the RawData Class to use RawDataTest.csv
		RawData input = new RawData("RawDataTest.csv");
		UIoutput output = new UIoutput();
		HashTable stor = new HashTable();
		int count = 0;

		while (input.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			stor.insert1Country(input.getCode(), input.getId(), count);
			count = count + 1; // iterator for number of entries sent to HashTables

		}
		input.finishUp();

		// Running cases 1-6 using Test
		output.displayBrk();
		output.displayThis("CASE:	1");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 1, 20, 2, output);

		output.displayThis("CASE:	2");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 2, 20, 2, output);

		output.displayThis("CASE:	3");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 3, 20, 2, output);

		output.displayThis("CASE:	4");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 4, 20, 2, output);

		output.displayThis("CASE:	5");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 1, 30, 2, output);

		output.displayThis("CASE:	6");
		output.displayThis("RAW DATA FILE:	Test");
		stor.hash(count, 1, 30, 1, output);

		
		// Tells the RawData Class to use RawDataAll.csv
		RawData inputAll = new RawData("RawDataAll.csv");
		HashTable storAll = new HashTable();
		count = 0;

		while (inputAll.input1Country()) // loop through the end of the file
		{
			// Sends each Country and Id to the arrays in HashTables class
			storAll.insert1Country(inputAll.getCode(), inputAll.getId(), count);
			count = count + 1; // iterator for number of entries sent to HashTables

		}
		inputAll.finishUp();

		// Running cases 7-11 using All
		output.displayThis("CASE:	7");
		output.displayThis("RAW DATA FILE:	ALL");
		storAll.hash(count, 1, 240, 2, output);

		output.displayThis("CASE:	8");
		output.displayThis("RAW DATA FILE:	ALL");
		storAll.hash(count, 1, 260, 2, output);

		output.displayThis("CASE:	9");
		output.displayThis("RAW DATA FILE:	ALL");
		storAll.hash(count, 1, 350, 2, output);

		output.displayThis("CASE:	10");
		output.displayThis("RAW DATA FILE:	ALL");
		storAll.hash(count, 1, 240, 1, output);

		output.displayThis("CASE:	11");
		output.displayThis("RAW DATA FILE:	ALL");
		storAll.hash(count, 1, 260, 1, output);

		output.finishUp();

	}
}


