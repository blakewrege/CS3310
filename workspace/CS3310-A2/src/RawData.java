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
	
	
//	private String countryCode;
//	private String restOfData;
//	public int n = 0;
//	private String name;
//	private String continent;
//	private String code;
//	private short id;
//	private int area;
//	private long population;
//	private float lifeExpectancy;
//	private int transactions;
//	boolean doneWithInput;
//	boolean countryGrabbed;
//	private Log log;
//	
//	String inFileName = "RawData.csv";
//	private FileReader input;
//	private BufferedReader inFile;
//	
//	//Constructor for Setup program
//	public RawData() throws FileNotFoundException, IOException
//	{
//		input = new FileReader(inFileName);
//		inFile = new BufferedReader(input);
//	}
//	
//	//A single record read in.
//	public boolean input1Country() throws IOException
//	{
//		String theLine = inFile.readLine();
//		if(theLine != null)
//		//if(theLine.length() != 0)
//		{
//			cleanup(theLine);
//			n++;
//			return true;
//		}	
//		else
//			return false;
//	}
//	
//	//split record into individual fields
//	private void cleanup(String theLine)
//	{
//		String field[] = theLine.split(",");
//		countryCode = field[1];
//		if(field[8].equals("NULL"))
//			field[8] = "0.0";
//		restOfData = field[0] + "," + field[2] + "," + field[3] + "," + field[5] + "," + field[7] + "," + field[8];
//	}
//
//	public int getN()
//	{
//		return n;
//	}
//	
//	public String getCountryCode()
//	{
//		return countryCode;
//	}
//	
//	public String getRestOfData()
//	{
//		return restOfData;
//	}
//		
//	public void finishUp() throws IOException
//	{
//		inFile.close();
//		input.close();
//	}
//	
//	
//	
//	
//	
//	public String getCode(){
//		return code;
//	}
//	
//	/***************
//	 * Getter for id
//	 * @return
//	 */
//	public short getId(){
//		return id;
//	}
//	
//	/*****************
//	 * Getter for name
//	 * @return
//	 */
//	public String getName(){
//		return name;
//	}
//	
//	/**********************
//	 * Getter for continent
//	 * @return
//	 */
//	public String getContinent(){
//		return continent;
//	}
//	
//	/*****************
//	 * Getter for area
//	 * @return
//	 */
//	public int getArea(){
//		return area;
//	}
//	
//	/***********************
//	 * Getter for population
//	 * @return
//	 */
//	public long getPopulation(){
//		return population;
//	}
//	
//	/***************************
//	 * Getter for lifeExpectancy
//	 * @return
//	 */
//	public float getLifeExpectancy(){
//		return lifeExpectancy;
//	}
//	
//	public int getTransactions(){
//		return transactions;
//	}
//	
//	
//	public void finishUp(Log log) throws IOException{
//		input.close();
//		log.displayThis("FILE STATUS > RawData FILE closed");
//		transactions = 0;
//	}
//	
//	
	
