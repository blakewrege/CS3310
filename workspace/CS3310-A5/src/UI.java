
//CLASS: UI
//AUTHOR: Blake
//DESCRIPTION: Handles CodeIndex and DataStorage
//************************************  Assignment 5  **********************************

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UI {

	FileReader input;
	BufferedReader inFile;
	String transCode;
	String Code;
	String[] codeIndex = { "CodeIndex1.bin", "CodeIndex2.bin", "CodeIndex3.bin" };
	String[] dataStorage = { "DataStorage1.txt", "DataStorage2.txt", "DataStorage3.txt" };
	DataStorage data;
	CodeIndex index;

	public UI(String fileName, UIoutput output, int i) throws IOException {

		input = new FileReader(fileName);
		inFile = new BufferedReader(input);
		index = new CodeIndex(codeIndex[i]);
		data = new DataStorage(dataStorage[i]);
		index.setupApp(); // reads the header record, initializes variables
		output.displayThis("+ + + + + + + + + + + + + +");
		output.displayThis("PROCESSING " + fileName + "\n"); // prints
																// transaction
																// file name

		processTrans(output);

	}

	public void finishUp() throws IOException { // called at the end of
												// processTrans, closes all open
												// files besides log

		input.close();
		data.finishUp();
		index.finishUp();

	}

	public void processTrans(UIoutput output) throws IOException {

		while (input1Trans()) // loop through till end of the file
		{
			int loc = index.selectByCode(Code); // locates the code in the index
			if (-1 != loc) {
				output.displayThis(
						"S " + Code + " >> " + data.readData(loc) + " [# nodes read: " + index.getNodesRead() + "]");
			} else { // if the code is not found displays error
				output.displayThis("S " + Code + " >> " + "ERROR - code not found " + " [# nodes read: "
						+ index.getNodesRead() + "]");
			}
		}
		finishUp();

	}

	public boolean input1Trans() throws IOException { // throws out bad lines if
														// there are any
		String theLine = inFile.readLine();
		if (theLine != null) {
			cleanup(theLine);
			return true;
		} else {
			return false;
		}
	}

	private void cleanup(String theLine) // separate the line to independent
											// into parts
	{
		transCode = theLine.substring(0, 1);

		if (transCode.equals("S")) { // normally there would be other transcodes
										// besides just S, the code is
										// structured to support that format
			Code = theLine.substring(2, theLine.length());
		}

	}

}
