package edu.wmich.cs3310.asgn1;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Writes log entries to a .txt file
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class TheLog {
	private PrintWriter theLog;
	
	/**
	 * Constructor
	 * @throws IOException
	 */
	public TheLog () throws IOException{
		theLog = new PrintWriter("TheLog.txt");
		theLog.println("FILE STATUS > TheLog FILE opened");
	}
	
	/**
	 * Preformatted string
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param life
	 * @return String with SA format for country
	 */
	public String Country(String code, String name, String continent, int area, 
						  int population, float life){
		if(name.length()>18){
			name = name.substring(0, 18); //Cuts off names longer than 18 characters
//			name+="\n                           ";  //Prints whole name in 1 line and information in next one
		}
		DecimalFormat myFormatter = new DecimalFormat("#,###");
		return code+" "+String.format("%-18s %-13s %10s %13s %4.1f", 
				  name, continent, myFormatter.format(area), 
				  myFormatter.format(population), life);
	}
	
	/**
	 * Preformatted string
	 * @param index
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param life
	 * @param LCh
	 * @param RCh
	 * @return String with Snapshot format for country
	 */
	public String Country2(int index, String code, String name, String continent, 
						   int area, int population, float life, int LCh, int RCh){
		DecimalFormat noFormat = new DecimalFormat("#000");
		return "["+noFormat.format(index)+"] "+Country(code, name, continent, area,
			   population,life)+String.format(" %03d %03d", LCh, RCh);
	}
	
	/**
	 * Preformatted string
	 * @param action
	 * @return confirmation message string
	 */
	public String Msg(String action){
		return "   OK, country "+action;
	}
	
	/**
	 * Preformatted string
	 * @return invalid entry string
	 */
	public String Sorry(){
		return "   SORRY, invalid country name";
	}

	/**
	 * Preformatted string
	 * @param no number of nodes
	 * @return nodes visited string
	 */
	public String Visited(int no){
		if (no == 1)
			return "      >> "+no+" node visited";
		return "      >> "+no+" nodes visited";
	}
	
	/**
	 * Preformatted string
	 * @return SA header string
	 */
	public String Header1(){
		return "CDE NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE";
	}																																						  					
	
	/**
	 * Preformatted string
	 * @return Snapshot header string
	 */
	public String Header2(){
		return "[SUB] "+Header1()+" LCh RCh";
	}
	
	/**
	 * Preformatted string
	 * @return footer 
	 */
	public String Footer(){
		return "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
	}
	
	/**
	 * Preformatted string
	 * @return footer
	 */
	public String Footer2(){
		return "++++++++++++++";
	}
	
	/**
	 * Writes string to file
	 * @param toPrint string to write to file
	 * @throws IOException 
	 */
	public void displayThis(String toPrint) throws IOException{
		theLog.println(toPrint);
	}

	/**
	 * Creates log entry and closes file
	 * @throws IOException
	 */
	public void FinishUp() throws IOException{
		theLog.println("FILE STATUS > TheLog FILE closed");
		theLog.close();
	}
}
