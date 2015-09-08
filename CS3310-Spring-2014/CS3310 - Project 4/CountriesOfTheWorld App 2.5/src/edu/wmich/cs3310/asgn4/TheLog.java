package edu.wmich.cs3310.asgn4;

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
	public TheLog() throws IOException {
		tL = new PrintWriter(new FileOutputStream(new File("TheLog.txt"),true));
	}
	
	/*************************************
	 * Nodes read notification.
	 * @param recRead
	 * @return
	 */
	public void nodesRead(int nodesRead) {
		printThis(String.format("   [# nodes read: %d]", nodesRead));
	}
	
	/********************
	 * Error message.
	 * @param action
	 * @return string
	 */
	public void error() {
		printThis(">>> Error - code not in index.");
	}
	
	
	/*********************************
	 * Prints string to log file.
	 * @param msg
	 */
	public void printThis(String msg){
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
