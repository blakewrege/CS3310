//CLASS: RawData - Used by Setup program
//AUTHOR: Blake
//*****************************************************************************************************


import java.io.*;

public class RawData 
{
	
	final short SIZE_OF_A_CHAR = 1; 
	final short SIZE_OF_CODE = (short) (3 * SIZE_OF_A_CHAR);
	final short SIZE_OF_NAME = (short) (18 * SIZE_OF_A_CHAR);
	final short SIZE_OF_CONT = (short) (13 * SIZE_OF_A_CHAR);
	String inFileName = "RawData2.csv";
	private FileReader input;
	private BufferedReader inFile;
	public int n = 0;
	private String name;
	private String continent;
	private String code;
	private short id;
	private int area;
	private long population;
	private float lifeExpectancy;

	
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
		id = Short.parseShort(field[0]);
		if(field[8].equals("NULL"))
			field[8] = "0.0";
		code = field[1];
		name = field[2];
		continent = field[3];
		area = Integer.parseInt(field[5]);
		population = Long.parseLong(field[7]);
		lifeExpectancy = Float.parseFloat(field[8]);
		
		
	}
	
	public int getN()
	{

		return n;
	}
	
	public Short getId()
	{
		return id;
	}
	
		
	public void finishUp() throws IOException
	{
		inFile.close();
		input.close();
	}
	
	
	public String getCode(){
		return code;
	}
	
	
	/*****************
	 * Getter for name
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**********************
	 * Getter for continent
	 * @return
	 */
	public String getContinent(){
		return continent;
	}
	
	/*****************
	 * Getter for area
	 * @return
	 */
	public int getArea(){
		return area;
	}
	
	/***********************
	 * Getter for population
	 * @return
	 */
	public long getPopulation(){
		return population;
	}
	
	/***************************
	 * Getter for lifeExpectancy
	 * @return
	 */
	public float getLifeExpectancy(){
		return lifeExpectancy;
	}
	
}

	
	
	