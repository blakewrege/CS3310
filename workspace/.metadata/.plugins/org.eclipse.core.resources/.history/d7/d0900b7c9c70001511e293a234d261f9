//CLASS: UIinput - Used by UserApp program
//AUTHOR: Jia Guo
//DESCRIPTION: Input file for UserApp program. EVERYTHING to do with this file is done within UIinput class
//		transCode is the 1st char in the record: I, D, S, A (for Insert, Delete, Select, showAll)
//		sample records: I FRA ... D CHN S USA A 
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************


import java.io.*;

public class UIinput {
	
	private String inFileName = "TransData.txt";
	private FileReader input;
	private BufferedReader inFile;
	private int n = 0;
	private String transCode;
	private String countryCode;
	private String restOfData;
	
	//Constructor
	public UIinput() throws FileNotFoundException
	{
		input = new FileReader(inFileName);
		inFile = new BufferedReader(input);
	}
	
	//A single record read in.
	public boolean input1User() throws IOException
	{
		String theLine = inFile.readLine();
		//if(theLine.length() != 0)
		if(theLine != null)
		{
			cleanup(theLine);
			if(!(transCode.equals("%")))
			n++;
			return true;
		}
		else
			return false;
	}
	
	public String getTransCode()
	{
		return transCode;
	}
	
	public String getCountryCode()
	{
		return countryCode;
	}
	
	public String getRestOfData()
	{
		return restOfData;
	}
	
	public int getN() //get the number of transactions
	{
		return n;
	}
	
	private void cleanup(String theLine) //separate the line to independent String field.
	{
		transCode = theLine.substring(0, 1);
		if(transCode.equals("%")){} //If transcode is %, then do nothing
		else if(transCode.equals("S")){countryCode = theLine.substring(2, 5);} //If thranscode is S, then read countryCode
		else if(transCode.equals("A")){}
		else if(transCode.equals("I")) //If transcode is I, then clean up the information of the record.
		{
			String field[] = theLine.substring(2, theLine.length()).split(",");
			countryCode = field[1];
			restOfData = field[0] + "," + field[2] + "," + field[3] + "," + field[4] + "," + field[5] + "," + field[6];
		}
		else if(transCode.equals("D")){countryCode = theLine.substring(2, 5);}
	}
	
	private void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}
	
	private void setTransCode(String transCode)
	{
		this.transCode = transCode;
	}

	public void finishUp() throws IOException //close file
	{
		input.close();
		inFile.close();
	}
}
