package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intended to obtain information for Setup in Main from 
 * the RawData files in order to create table.
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class RawData {
	private Scanner input;
	private String name;
	private String continent;
	private String code;
	private int area;
	private int population;
	private int transactions;
	private float lifeExpectancy;
	boolean doneWithInput = false;
	
	/**
	 * Contructor to open RawData and create log entry
	 * @param fileNameSufix Name of RawData file to open
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public RawData(String fileNameSufix, TheLog tl) throws IOException{	
		File file = new File("RawData"+fileNameSufix+".csv");
		tl.displayThis("FILE STATUS > RawData FILE opened");
		input = new Scanner(file);
	}
	
	/**
	 * Obtain command from a line in the RawData file
	 */
	public void GrabCountry(){
		if(input.hasNextLine()){
			String temp = input.nextLine();
			
			temp = temp.substring(30,temp.length()-2)
					   .replace("'","");
			String[] fields = temp.split(",");
			
			code = fields[0];
			name = fields[1];
			continent = fields[2];
			area = Integer.parseInt(fields[4]);
			population = Integer.parseInt(fields[6]);
			lifeExpectancy = Float.parseFloat(fields[7]);
			
			transactions++;
		}
		else
			doneWithInput = true;
	}
	
	/**
	 * Getter for name
	 * @return
	 */
	public String GetName(){
		return name;
	}
	
	/**
	 * Getter for continent
	 * @return
	 */
	public String GetContinent(){
		return continent;
	}
	
	/**
	 * Getter for code
	 * @return
	 */
	public String GetCode(){
		return code;
	}
	
	/**
	 * Getter for area
	 * @return
	 */
	public int GetArea(){
		return area;
	}
	
	/**
	 * Getter for population
	 * @return
	 */
	public int GetPopulation(){
		return population;
	}
	
	/**
	 * Getter for lifeExpectancy
	 * @return
	 */
	public float GetLifeExpectancy(){
		return lifeExpectancy;
	}
	
	/**
	 * Getter for transactions
	 * @return
	 */
	public int GetTransactions(){
		return transactions;
	}
	
	/**
	 * Close file and create log entry
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void FinishUp(TheLog tl) throws IOException{
		input.close();
		tl.displayThis("FILE STATUS > RawData FILE closed");
		transactions = 0;
	}
	
}
