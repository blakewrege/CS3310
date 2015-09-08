package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/********************************************************
 * Read RawData files and organize contents for retrieval
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class RawData {
	private Scanner input;
	private String name;
	private String continent;
	private String code;
	private int id;
	private int area;
	private int population;
	private int transactions;
	private float lifeExpectancy;
	private Log log;
	boolean doneWithInput;
	boolean countryGrabbed;
	
	/***************************************************
	 * Contructor
	 * @param fileNameSufix Name of RawData file to open
	 * @param Log TheLog object
	 * @throws IOException
	 */
	public RawData(String fileNameSufix, Log log) throws IOException{	
		File file = new File("A1RawData"+fileNameSufix+".csv");
		log.displayThis(String.format(
				"FILE STATUS > RawData FILE opened (A1RawData%s.csv)",
				fileNameSufix));
		input = new Scanner(file);
	}
	
	/**************************************************
	 * Obtain country from a line from the RawData file
	 */
	public void grabCountry(){
		doneWithInput = false;
		countryGrabbed = false;
		if(input.hasNextLine()){
			String temp = input.nextLine();
			if (temp.length() > 2 && temp.substring(0,6).equals("INSERT")){
				temp = temp.substring(30, temp.length()-2)
						   .replace("'","");
				String[] fields = temp.split(",");
				
				code = fields[0];
				id = Integer.parseInt(fields[1]);
				name = fields[2];
				continent = fields[3];
				area = Math.round(Float.parseFloat(fields[5]));
				population = Integer.parseInt(fields[7]);
				lifeExpectancy = Float.parseFloat(fields[8]);
				
				transactions++;
				countryGrabbed = true;
			}else
				countryGrabbed = false;
		}else 
			doneWithInput = true;
	}
	
	/***********************
	 * Getter for code
	 * @return
	 */
	public String getCode(){
		return code;
	}
	
	/******************
	 * Getter for id
	 * @return
	 */
	public int getId(){
		return id;
	}
	
	/***********************
	 * Getter for name
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/****************************
	 * Getter for continent
	 * @return
	 */
	public String getContinent(){
		return continent;
	}
	
	/********************
	 * Getter for area
	 * @return
	 */
	public int getArea(){
		return area;
	}
	
	/**************************
	 * Getter for population
	 * @return
	 */
	public int getPopulation(){
		return population;
	}
	
	/********************************
	 * Getter for lifeExpectancy
	 * @return
	 */
	public float getLifeExpectancy(){
		return lifeExpectancy;
	}
	
	/****************************
	 * Getter for transactions
	 * @return
	 */
	public int getTransactions(){
		return transactions;
	}
		
	/*****************************************
	 * Close file and create log entry
	 * @param Log TheLog object
	 * @throws IOException
	 */
	public void finishUp(Log log) throws IOException{
		input.close();
		log.displayThis("FILE STATUS > RawData FILE closed");
		transactions = 0;
	}
}
