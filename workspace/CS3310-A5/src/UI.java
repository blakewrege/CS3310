
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UI {

	FileReader input;
	BufferedReader inFile;
	String transCode;
	String Code;

	public UI(String fileName, UIoutput output) throws IOException {

		input = new FileReader(fileName);
		inFile = new BufferedReader(input);
		output.displayThis("+ + + + + + + + + + + + + +");
		output.displayThis("PROCESSING "+ fileName);


	}

	public void finishUp() throws IOException {

		input.close();

	}

	public void readFile() throws IOException {

		while (input1Trans()) // loop through the end of the file
		{
			System.out.println(Code);
		}

	}

	public boolean input1Trans() throws IOException {
		String theLine = inFile.readLine();
		// if(theLine.length() != 0)
		if (theLine != null) {
			cleanup(theLine);
			return true;
		} else
			return false;
	}

	private void cleanup(String theLine) // separate the line to independent
											// String field.
	{
		transCode = theLine.substring(0, 1);

		if (transCode.equals("S")) {
			Code = theLine.substring(2, theLine.length());
		}

	}

}

