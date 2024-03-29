//CLASS: UIinput - Used by UserApp program
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Input file for UserApp program. 
//*****************************************************************************************************


import java.io.*;
import java.util.Arrays;
public class UIinput {
	// Use both TransData2a.txt and TransData2b.txt
	private String inFileName = "TransData2b.txt";
	private FileReader input;
	private BufferedReader inFile;
	private int n = 0;
	private String transCode;
	private short id;
	private String name;
	private String continent;
	private String code;
	private int area;
	private long population;
	private float lifeExpectancy;
	
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
	
	public Short getId()
	{
		return id;
	}
	

	
	public int getN() //get the number of transactions
	{
		return n;
	}
	
	private void cleanup(String theLine) //separate the line to independent String field.
	{
		transCode = theLine.substring(0, 1);

		if(transCode.equals("%")){} //If transcode is %, then do nothing
		else if(transCode.equals("S")){id = (short) Integer.parseInt(theLine.substring(2, theLine.length()));} //If thranscode is S, then read id
		else if(transCode.equals("A")){}
		else if(transCode.equals("I")) //If transcode is I, then clean up the information of the record.
		{

			String field[] = theLine.substring(2, theLine.length()).split(",");
			//System.out.println(Arrays.toString(field)+" "+ field.length);
		    id= Short.parseShort((field[1]));
			code = field[0].trim();
			name = field[2];
			
			continent = field[3];
			area = Integer.parseInt(field[4]);
			population = Long.parseLong(field[5]);
			lifeExpectancy = Float.parseFloat(field[6]);
			
		}
		else if(transCode.equals("D")){id = (short) Integer.parseInt(theLine.substring(2, theLine.length()));}
	}

	public float getLifeExpectancy() {
		// TODO Auto-generated method stub
		return lifeExpectancy;
	}

	public long getPopulation() {
		// TODO Auto-generated method stub
		return population;
	}

	public int getArea() {
		// TODO Auto-generated method stub
		return area;
	}

	public String getContinent() {
		// TODO Auto-generated method stub
		return continent;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}
	

//
	public void finishUp() throws IOException //close file
	{
		input.close();
		inFile.close();
	}
	
	
	
	
}
