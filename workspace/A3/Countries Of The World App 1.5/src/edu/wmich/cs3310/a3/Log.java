package edu.wmich.cs3310.a3;

import java.io.*;

/*****************
 * Manages Log.txt
 * Countries Of The World App 1.5
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
