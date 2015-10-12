package edu.wmich.cs3310.asgn1;

import java.io.*;
import java.text.DecimalFormat;

/********************
 * Manages Backup.txt
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class Backup {
	private PrintWriter backup;
	private DecimalFormat hundredZeros;
	
	/************************************************
	 * Constructor
	 * @throws IOException
	 */
	public Backup(boolean append) throws IOException{
		if (!append)
			backup = new PrintWriter("Backup.txt");
		else
			backup = new PrintWriter(new FileOutputStream(new File("Backup.txt"), append));
		hundredZeros = new DecimalFormat("#000");
	}
	
	/**************************************************
	 * String format for dataTable print on backup file
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param life
	 * @return String with SA format for country
	 */
	public String dataFormat(DataTableRecord record){
		return String.format("%s_%s_%s_%s_%s_%s_%f", 
				  record.code, hundredZeros.format(record.id), record.name, record.continent, 
				  record.area, record.population,
				  record.lifeExpectancy);
	}
	
	/**************************************************
	 * String format for nameIndex print on backup file
	 * @param name 
	 * @param ptr
	 * @return
	 */
	public String nameFormat(String name, int ptr){
		return String.format("%s_%s", 
				  name, ptr);
	}
	
	/*****************************************
	 * Represent empty location in backup file
	 * @return
	 */
	public String empty(){
		return String.format("empty");
	}
	
	/*********************************************************
	 * Prints line to file
	 * @param message string to write to file
	 * @throws IOException 
	 */
	public <T> void displayThis(T message) throws IOException{
		backup.println(message);
	}
	

	/*****************************************
	 * Closes backup file
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		backup.close();
	}
}
