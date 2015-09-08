package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * Writes log entries to a .txt file
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class TheLog {
	private PrintWriter tL;
	
	/*************************************************
	 * Intended to set up theLog according to boolean for append.
	 * @param append Decides append mode for PrintWriter.
	 * @throws IOException
	 */
	public TheLog(boolean append) throws IOException {
		if (!append)
			tL = new PrintWriter("TheLog.txt");
		else
			tL = new PrintWriter(new FileOutputStream(
					 new File("TheLog.txt"), append));
		tL.println("FILE STATUS > TheLog FILE opened.");
	}
	
	/*****************************************************************
	 * Preformatted string with Snapshot format for printing country
	   data.
	 * @param index
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param life
	 * @param LCh
	 * @param RCh
	 * @return string
	 */
	public String country(int RRN, short id, String code, String name,
			String continent, int area, long population, float life) {
		DecimalFormat noFormat = new DecimalFormat("#000");
		return String.format("[%s] %s ", noFormat.format(RRN),
				noFormat.format(id))
				+ toFormat(code, name, continent, area, population, life);
	}
	
	/**************************************************************************
	 * Preformatted string for printing country data.
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param life
	 * @return string
	 */
	public String toFormat(String code, String name, String continent, int area,
			long population, float life) {
		if (name.length() > 18)
			name = name.substring(0, 18);
		DecimalFormat noFormat = new DecimalFormat("#,###");
		return code
				+ " "
				+ String.format("%-16s %-13s %10s %13s %4.1f", name, continent,
						noFormat.format(area),
						noFormat.format(population), life);
	}
	
	/********************************
	 * Confirmation string.
	 * @param action
	 * @return string
	 */
	public String Msg(String action){
		return "   OK, country "+action+".";
	}
	
	/**************************************
	 * Read records message.
	 * @param recRead
	 * @return
	 */
	public String recReadMsg(int recRead) {
		return "   " + recRead + " data records read";
	}
	
	/**********************
	 * Sorry message.
	 * @param action
	 * @return string
	 */
	public String sorry() {
		return "   SORRY, no country with that id.";
	}
	
	/**********************
	 * Sorry message.
	 * @param action
	 * @return string
	 */
	public String sorry2() {
		return "   SORRY, another country has that id.";
	}
	
	/**********************
	 * Sorrry message.
	 * @param action
	 * @return string
	 */
	public String sorry3() {
		return "   SORRY, invalid id.";
	}
	
	/***********************************
	 * Sorry message.
	 * @param action
	 * @return string
	 */
	public String dummy(String method) {
		return "   SORRY, " + method + " not yet working.";
	}
	
	/************************
	 * Header format.
	 * @return string
	 */
	public String header() {
		return "[RRN] ID  CDE NAME------------ CONTINENT---- ------AREA ---POPULATION LIFE";
	}																																					  					

	/***********************
	 * Footer format.
	 * @return string
	 */
	public String footer() {
		return "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
	}
	
	/**********************************************************
	 * Writes string to file
	 * @param toPrint string to write to file
	 * @throws IOException 
	 */
	public void displayThis(String toPrint) throws IOException{
		tL.println(toPrint);
	}

	/*****************************************
	 * Creates log entry and closes file
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		tL.println("FILE STATUS > TheLog FILE closed.");
		tL.close();
	}
}
