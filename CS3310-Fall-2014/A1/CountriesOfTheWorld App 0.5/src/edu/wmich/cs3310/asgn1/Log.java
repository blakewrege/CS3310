package edu.wmich.cs3310.asgn1;

import java.io.*;
import java.text.DecimalFormat;

/*****************
 * Manages Log.txt
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class Log {
	private PrintWriter log;
	
	/*********************************************
	 * Opens Log.txt in append or truncate mode
	 * @param append
	 * @throws IOException
	 */
	public Log(boolean append) throws IOException{
		if (!append)
			log = new PrintWriter("Log.txt");
		else
			log = new PrintWriter(new FileOutputStream(new File("Log.txt"),
					append));
		log.println("FILE STATUS > Log FILE opened");
	}
	
	/*********************************************
	 * Formatted string for printing country
	 * @param record
	 * @return
	 */
	public String country(DataTableRecord record){
		String name = record.name;
		if(name.length() > 18){
			name = name.substring(0, 18); //Cuts off part of names longer than 18 characters
		}
		DecimalFormat hundredZeros = new DecimalFormat("#000");
		DecimalFormat thousand = new DecimalFormat("#,###");
		return String.format("%s %s %-18s %-13s %10s %13s %4.1f", record.code,
				hundredZeros.format(record.id), name, record.continent,
				thousand.format(record.area),
				thousand.format(record.population), record.lifeExpectancy);
	}
	
	/**********************************************
	 * Formatted string for printing empty location
	 * @param id
	 * @return
	 */
	public String empty(int id){
		DecimalFormat hundredZeros = new DecimalFormat("#000");
		return String.format("    %s ...", hundredZeros.format(id));
	}
	
	/******************************************
	 * Formatted string for header in AI and AN
	 * @return SA header string
	 */
	public String header(){
		return "CDE ID- NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE";
	}																																						  					

	/******************************************
	 * Formatted screen for footer in AI and AN
	 * @return footer 
	 */
	public String footer(){
		return "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
	}
	
	/*********************************************************
	 * Prints line to file
	 * @param message string to write to file
	 * @throws IOException 
	 */
	public <T> void displayThis(T message) throws IOException{
		log.println(message);
	}
	

	/***********************************
	 * Creates log entry and closes file
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		log.println("FILE STATUS > Log FILE closed");
		log.close();
	}
}
