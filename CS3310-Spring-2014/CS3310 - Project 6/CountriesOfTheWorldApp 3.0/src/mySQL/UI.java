package mySQL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UI {
	private Scanner input;
	private FileWriter fileW;
	
	/*********************************************
	 * Contructor to initialize files.
	 * @param fileName
	 * @throws IOException
	 */
	public UI(String fileName) throws IOException{
		File file = new File(fileName);
		input = new Scanner(file);
		fileW = new FileWriter("Log.txt");
	}

	/************************
	 * Self documenting.
	 * @return
	 */
	public String nextLine(){
		return input.nextLine();
	}
	
	/****************************
	 * Self documenting.
	 * @return
	 */
	public boolean hasNextLine(){
		return input.hasNextLine();
	}
	
	/***************************************************
	 * Prints line to log file.
	 * @param line
	 * @throws IOException
	 */
	public void println(String line) throws IOException{
		fileW.write(String.format("%s\r\n", line));
	}
	
	/*****************************************
	 * Closes files.
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		input.close();
		fileW.close();
	}
}
