
//PROGRAM: UserApp
//AUTHOR: Blake Wrege
//DESCRIPTION: UserApp calls UI class for each transaction data file
//************************************  Assignment 5  **********************************

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[] transData = { "A5TransData1.txt", "A5TransData2.txt", "A5TransData3.txt" };
		UIoutput output = new UIoutput(); // creates log file

		for (int i = 0; i < 3; i++) {
			UI process = new UI(transData[i], output, i); // Creates new UI
		}
		output.finishUp(); // closes log file

	}
}
