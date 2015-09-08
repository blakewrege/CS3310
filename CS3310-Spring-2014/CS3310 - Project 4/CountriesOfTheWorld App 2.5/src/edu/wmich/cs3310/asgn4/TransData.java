package edu.wmich.cs3310.asgn4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***********************
 * Intended to obtain information for UserApp in Main from the TransData files
 * in order to create table. 
 * @author Caleb Viola
 */
public class TransData {
	private Scanner input;
	private String code;
	private String command;
	boolean doneWithFile = false;

	/******************************************************************
	 * Constructor to open a TransData file.
	 * @param fileNameSufix number of TransData file to read.
	 * @param tl TheLog object 
	 * @throws IOException
	 */
	public TransData(int fileNameSufix, TheLog tl) throws IOException {
		File file = new File(String.format("A4TransData%d.txt", fileNameSufix));
		input = new Scanner(file);
	}

	/******************************************************
	 * Obtain command from a line in the TransData file.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void grabCommand(TheLog tl) throws IOException {
		if (input.hasNextLine()) {
			String temp = input.nextLine();
			command = temp.substring(0, 2);
			
			if (command.equals("SC"))
				code = temp.substring(3, temp.length()).trim();
			
		} else
			doneWithFile = true;
	}

	/***************************
	 * Getter for acquired command.
	 * @return
	 */
	public String getCommand() {
		return command;
	}
	
	/************************
	 * Getter for code.
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/***************************************************
	 * Closes data file.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void finishUp(TheLog tL) throws IOException {
		input.close();
		tL.printThis(". . .");
	}
}
