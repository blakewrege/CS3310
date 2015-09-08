package edu.wmich.cs3310.asgn5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UI {
	TheLog tL;
	Scanner input;
	private String line;
	private String dName;
	private String[] lineArr;
	private String sCityName;
	
	/**************************************************
	 * 
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public UI(String fileNameSufix) throws IOException{
		tL = new TheLog("UI");
		File file = new File(String.format("%sCityPairs.txt", fileNameSufix));
		input = new Scanner(file);
		
	}
	
	/********************************
	 * 
	 * @return
	 */
	public String getStartCityName(){
		return sCityName;
	}
	
	/**********************************
	 * 
	 * @return
	 */
	public String getDestinationName(){
		return dName;
	}
	
	/****************************
	 * 
	 * @return
	 */
	public boolean moreTrans(){
		if(input.hasNextLine()){
			line = input.nextLine();			
			while (line.charAt(0) == '%'){
				line = input.nextLine();
			}
			lineArr = line.split(" ");
			sCityName = lineArr[0];
			dName = lineArr[1];
			return true; /*Succesful*/
		}
		else 
			return false; /*No more lines*/
	}
	
	public void writeThis(boolean line, String msg){
		if (line)
			tL.printThisln(msg);
		else 
			tL.printThis(msg);
	}
	
	/*****************************************
	 * 
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		input.close();
		tL.finishUp();
	}
}
