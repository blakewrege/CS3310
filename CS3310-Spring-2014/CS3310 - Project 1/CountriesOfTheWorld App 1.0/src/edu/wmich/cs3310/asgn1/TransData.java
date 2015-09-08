package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intended to obtain information for UserApp in Main from 
 * the TransData files in order to create table.
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class TransData {
	private Scanner input;
	private String name;
	private String continent;
	private String code;
	private String command;
	private int area;
	private int population;
	private int transactions;
	private float lifeExpectancy;
	boolean doneWithFile = false;
	
	/**
	 * Constructor to open TransData and create log entry
	 * @param fileNameSufix number of TransData file to read
	 * @param tl TheLog object 
	 * @throws IOException
	 */
	public TransData(int fileNameSufix, TheLog tl) throws IOException{		
		File file = new File("TransData"+fileNameSufix+".txt");
		tl.displayThis("FILE STATUS > TransData FILE opened");
		input = new Scanner(file);
	}
	
	
	/**
	 * Obtain command from a line in the TransData file
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void GrabCommand(TheLog tl) throws IOException{
		if(input.hasNextLine()){
			String temp = input.nextLine();
			command = temp.substring(0,2);
			
			if (command.equals("IN")){
				temp = temp.substring(33,temp.length()-2)
						   .replace("'","");
				String[] fields = temp.split(",");
				
				code = fields[0];
				name = fields[1];
				continent = fields[2];
				area = Integer.parseInt(fields[4]);
				population = Integer.parseInt(fields[6]);
				lifeExpectancy = Float.parseFloat(fields[7]);
			}
			else if (command.equals("SN") || command.equals("DN")){
				if (temp.length() > 2)
					name = temp.substring(3, temp.length()).trim();
				else 
					name = "EMPTY";
			}
			
			transactions++;
		}
		else
			doneWithFile = true;
	}
	
	/**
	 * Getter for acquired command
	 * @return
	 */
	public String TransCode(){
		return command;
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
	 * Getter for number of transactions
	 * @return
	 */
	public int GetTransactions() {
		return transactions;
	}
	
	/**
	 * Close file and creat log entry
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void FinishUp(TheLog tl) throws IOException{
		input.close();
		tl.displayThis("FILE STATUS > TransData FILE closed");
	}
}
