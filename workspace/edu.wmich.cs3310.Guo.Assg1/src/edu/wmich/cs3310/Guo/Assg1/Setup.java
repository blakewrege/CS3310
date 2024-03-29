//PROGRAM: Setup
//AUTHOR: Jia Guo
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage based on data in the RawData file. 
//		Since DataStorage is built as an INTERNAL storage structure, it needs to be saved to an EXTERNAL storage 
//		structure (the Backup file) after it’s completely built (as the last step in Setup) in order for UserApp 
//		to be able to use it – it calls finishUp method in DataStorage class which handles this. Status messages 
//		are sent to displayThis method in UIoutput class, as needed. This controller program uses the “sequential file processing” 
//		design pattern.
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************

package edu.wmich.cs3310.Guo.Assg1;

import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		RawData input = new RawData();
		DataStorage dataStorage = new DataStorage();
		UIoutput output = new UIoutput(); //Open in truncate mode
		
		output.displayThis("-->> SETUP started");
		output.displayThis("-->> OPENED RawData file");
		output.displayThis("-->> OPENED Backup file");
		output.displayThis("-->> OPENED Log file\n");
		
		while(input.input1Country()) //loop through the end of the file
		{
			dataStorage.insert(input.getCountryCode(), input.getRestOfData());
			//store to interior storage, which is an array
		}
		
		output.displayThis("-->> CLOSED Log file");
		output.displayThis("-->> CLOSED Backup file");
		output.displayThis("-->> CLOSED RawData file");
		output.displayThis("-->> SETUP finished" + " - inserted " + input.getN() + " countries into DataStorage\n");
		
		output.finishUp();
		dataStorage.finishUp();
		input.finishUp();
	} 
}
