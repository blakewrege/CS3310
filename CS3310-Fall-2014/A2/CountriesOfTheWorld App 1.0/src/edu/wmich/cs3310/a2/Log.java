package edu.wmich.cs3310.a2;

import java.io.*;

/*****************
 * Manages Log.txt
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class Log {
	private PrintWriter log;
	
	/******************************************
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
	
	/***************************************
	 * Formatted string for printing country
	 * @param code
	 * @param id
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @return
	 */
	public String country(String code, short id, String name, String continent,
			int area, long population, float lifeExpectancy) {
		return String.format("%s %03d %-18.18s %-13.13s %,10d %,13d %4.1f",
				code, id, name, continent, area, population, lifeExpectancy);
	}
	
	/**********************************************
	 * Formatted string for printing empty location
	 * @param id
	 * @return
	 */
	public String empty(int id){
		return String.format("    %03d ...", id);
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
