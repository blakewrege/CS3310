package edu.wmich.cs3310.a2;

import java.io.*;
import java.text.DecimalFormat;

/********************
 * Manages Backup.txt
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class Backup {
	private PrintWriter backup;
	
	/**********************************
	 * Constructor
	 * @throws IOException
	 */
	public Backup() throws IOException{
		backup = new PrintWriter("Backup.txt");
	}

	/**************************************************
	 * String format for nameIndex print on backup file
	 * @param Lch
	 * @param name
	 * @param ptr
	 * @param Rch
	 * @return
	 */
	public String nameFormat(short Lch, String name, short ptr, short Rch){
		return String.format("%d'%s'%d'%d", Lch, name, ptr, Rch);
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


