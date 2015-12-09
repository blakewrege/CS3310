package edu.wmich.cs3310.asgn5;

import java.io.*;

/********************
 * Writes log entries to a .txt file.
 * Countries Of The World App 2.5
 * @author Caleb Viola
 */
public class TheLog {
	private PrintWriter tL;
	
	/***********************************
	 * Initializes the PrintWriter object.
	 * @param append Decides append mode for PrintWriter.
	 * @throws IOException
	 */
	public TheLog(String whoCalls) throws IOException {
		tL = new PrintWriter(String.format("TheLog%s.txt", whoCalls));
	}
	
	/**
	 * Prints pretty print header.
	 * @param n
	 */
	public void printHeader(short n){
		tL.print("   ");
		for (short x = 0; x < n; x++){
			tL.print(String.format("%5d",x));
		}
		tL.print("\n   ");
		for (short x = 0; x < n; x++){
			tL.print("-----");
		}
		tL.print("\n");
	}
	
	/*********************************
	 * Prints string to log file.
	 * @param msg
	 */
	public void printThis(String msg){
		tL.print(msg);
	}
	
	/*********************************
	 * Prints string to log file.
	 * @param msg
	 */
	public void printThisln(String msg){
		tL.println(msg);
	}

	/*****************************************
	 * Closes file log file.
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		tL.close();
	}
}
