package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intended to obtain information from the RawData files for the Setup class.
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class RawData {
	private Scanner input;
	private short id;
	private String code;
	private String name;
	private String continent;
	private int area;
	private long population;
	private float lifeExpectancy;
	boolean doneWithInput = false;
	
	/**
	 * Contructor to open RawData and create log entry.
	 * @param fileNameSufix Name of RawData file to open.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public RawData(String fileNameSufix, TheLog tl) throws IOException {
		File file = new File("RawData" + fileNameSufix + ".csv");
		tl.displayThis("FILE STATUS > RawData FILE opened.");
		input = new Scanner(file);
	}
	
	/**
	 * Obtain information from one line in the RawData file.
	 */
	public void GrabCountry() {
		if (input.hasNextLine()) {
			String temp = input.nextLine();

			temp = temp.substring(30, temp.length() - 2).replace("'", "");
			String[] fields = temp.split(",");

			id = Short.parseShort(fields[0]);
			code = fields[1];
			name = fields[2];
			continent = fields[3];
			area = Integer.parseInt(fields[5]);
			population = Integer.parseInt(fields[7]);
			lifeExpectancy = Float.parseFloat(fields[8]);
		} else
			doneWithInput = true;
	}
	
	/**
	 * Getter for id.
	 * @return
	 */
	public short GetID(){
		return id;
	}
	
	/**
	 * Getter for code.
	 * @return
	 */
	public String GetCode(){
		return code;
	}
	
	/**
	 * Getter for name.
	 * @return
	 */
	public String GetName(){
		return name;
	}
	
	/**
	 * Getter for continent.
	 * @return
	 */
	public String GetContinent(){
		return continent;
	}
	
	/**
	 * Getter for area.
	 * @return
	 */
	public int GetArea(){
		return area;
	}
	
	/**
	 * Getter for population.
	 * @return
	 */
	public long GetPopulation(){
		return population;
	}
	
	/**
	 * Getter for lifeExpectancy.
	 * @return
	 */
	public float GetLifeExpectancy(){
		return lifeExpectancy;
	}

	/**
	 * Close file and create log entry.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void FinishUp(TheLog tl) throws IOException{
		input.close();
		tl.displayThis("FILE STATUS > RawData FILE closed.");
	}
	
}
