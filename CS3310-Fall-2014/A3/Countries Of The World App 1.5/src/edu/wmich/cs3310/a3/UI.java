package edu.wmich.cs3310.a3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/******************************************
 * Read TransData files prepare for UserApp
 * Countries Of The World App 1.5
 * @author Caleb Viola
 */
public class UI {
	private Scanner input;
	private String name;
	private String continent;
	private String code;
	private String transCode;
	private String origId;
	private short id;
	private int area;
	private long population;
	private int transactions;
	private float lifeExpectancy;
	boolean doneWithTrans = false;
	
	/****************************************************
	 * Constructor to open TransData and create log entry
	 * @param fileNameSufix number of TransData file to read
	 * @param log
	 * @throws IOException
	 */
	public UI(int fileNameSufix, Log log) throws IOException{	
		log.displayThis(String.format(
				"FILE STATUS > TransData FILE opened (A3TransData%s.txt)",
				fileNameSufix));
		input = new Scanner(new File("A3TransData"+fileNameSufix+".txt"));
	}
	
	/**************************************************
	 * Obtain command from a line in the TransData file
	 * @throws IOException
	 */
	public void grabCommand() throws IOException{
		doneWithTrans = false;
		if(input.hasNextLine()){
			String temp = input.nextLine();
			transCode = temp.substring(0,2);
			
			if (transCode.equals("IN")){
				temp = temp.substring(33, temp.length()-2)
						   .replace("'","");
				String[] fields = temp.split(",");
				
				code = fields[0];
				id = Short.parseShort(fields[1]);
				name = fields[2];
				continent = fields[3];
				area = Math.round(Float.parseFloat(fields[5]));
				population = Long.parseLong(fields[7]);
				lifeExpectancy = Float.parseFloat(fields[8]);
			}
			else if (transCode.equals("SC") || transCode.equals("DC")){
				code = temp.substring(3, temp.length()).trim();
			}
			transactions++;
		}
		else
			doneWithTrans = true;
	}
	
	/*****************************
	 * Getter for acquired command
	 * @return
	 */
	public String getTransCode(){
		return transCode;
	}
	
	/*****************
	 * Getter for code
	 * @return
	 */
	public String getCode(){
		return code;
	}
	
	/***************
	 * Getter for id
	 * @return
	 */
	public short getId(){
		return id;
	}
	
	/*******************
	 * Getter for origId
	 * @return
	 */
	public String getOrigId(){
		return origId;
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

	/***********************************
	 * Getter for number of transactions
	 * @return
	 */
	public int getTransactions() {
		return transactions;
	}
	
	/*********************************
	 * Close file and create log entry
	 * @param ppu TheLog object
	 * @throws IOException
	 */
	public void finishUp(Log log) throws IOException{
		input.close();
		log.displayThis("FILE STATUS > TransData FILE closed");
	}
}
