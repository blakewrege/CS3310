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
	 * Intended to set up theLog according to boolean for append.
	 * @param append Decides append mode for PrintWriter.
	 * @throws IOException
	 */
	public TheLog(boolean append) throws IOException {
		if (!append)
			theLog = new PrintWriter("TheLog.txt");
		else
			theLog = new PrintWriter(new FileOutputStream(
					 new File("TheLog.txt"), append));
		theLog.println("FILE STATUS > TheLog FILE opened.");
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
			long population, float life) {
		if (name.length() > 18)
			name = name.substring(0, 18);
		DecimalFormat myFormatter = new DecimalFormat("#,###");
		return code
				+ " "
				+ String.format("%-16s %-13s %10s %13s %4.1f", name, continent,
						myFormatter.format(area),
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
	public String Country2(short ID, String code, String name,
			String continent, int area, long population, float life) {
		DecimalFormat noFormat = new DecimalFormat("#000");
		return "[" + noFormat.format(ID) + "] "
				+ Country(code, name, continent, area, population, life);
	}
	
	/**
	 * Preformatted string
	 * @param action
	 * @return confirmation message string
	 */
	public String Msg(String action){
		return "   OK, country "+action+".";
	}
	
	/**
	 * Preformatted string
	 * @return invalid entry string
	 */
	public String Sorry() {
		return "   SORRY, no country with that id.";
	}

	public String Sorry2() {
		return "   SORRY, another country has that id.";
	}

	public String Sorry3() {
		return "   SORRY, invalid id.";
	}

	/**
	 * Preformatted string
	 * @return SA header string
	 */
	public String Header1() {
		return "CDE NAME------------ CONTINENT---- ------AREA ---POPULATION LIFE";
	}																																					  					
	
	/**
	 * Preformatted string
	 * @return Snapshot header string
	 */
	public String Header2(){
		return "[ID ] "+Header1();
	}
	
	/**
	 * Preformatted string
	 * @return footer 
	 */
	public String Footer() {
		return "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
	}

	/**
	 * Preformatted string
	 * @return footer
	 */
	public String Footer2(){
		return "++++++";
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
		theLog.println("FILE STATUS > TheLog FILE closed.");
		theLog.close();
	}
}
