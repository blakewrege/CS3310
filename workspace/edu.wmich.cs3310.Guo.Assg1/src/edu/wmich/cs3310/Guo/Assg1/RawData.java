//CLASS: RawData - Used by Setup program
//AUTHOR: Jia Guo
//DESCRIPTION: This is the input file for Setup program. The functions of this class includes: 
//		open file in public constructor, close file in public finishUp method, a single record read 
//		in by input1Country public method; record split into individual fields by private cleanup and setter methods
//		individual field data available to Setup by public getter methods; only a SINGLE record (and a single set of fields) 
//		is needed within the class since a NEW record can over-write the prior record’s data, since the prior record is already 
//		completely handled
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************

package edu.wmich.cs3310.Guo.Assg1;
import java.io.*;

public class RawData 
{
	//There is no need to convert each attribute to corresponding data type, since no 
	//numeric processing needed to be done.
	
	private String countryCode;
	private String restOfData;
	public int n = 0;
	
	String inFileName = "RawData.csv";
	private FileReader input;
	private BufferedReader inFile;
	
	//Constructor for Setup program
	public RawData() throws FileNotFoundException, IOException
	{
		input = new FileReader(inFileName);
		inFile = new BufferedReader(input);
	}
	
	//A single record read in.
	public boolean input1Country() throws IOException
	{
		String theLine = inFile.readLine();
		if(theLine != null)
		//if(theLine.length() != 0)
		{
			cleanup(theLine);
			n++;
			return true;
		}	
		else
			return false;
	}
	
	//split record into individual fields
	private void cleanup(String theLine)
	{
		String field[] = theLine.split(",");
		countryCode = field[1];
		if(field[8].equals("NULL"))
			field[8] = "0.0";
		restOfData = field[0] + "," + field[2] + "," + field[3] + "," + field[5] + "," + field[7] + "," + field[8];
	}

	public int getN()
	{
		return n;
	}
	
	public String getCountryCode()
	{
		return countryCode;
	}
	
	public String getRestOfData()
	{
		return restOfData;
	}
		
	public void finishUp() throws IOException
	{
		inFile.close();
		input.close();
	}
}