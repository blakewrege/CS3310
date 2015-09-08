package edu.wmich.cs3310.asgn4;

import java.io.IOException;
import java.io.RandomAccessFile;

/*************************
 * Manages the CountryData#.txt files.
 * stored in a binary file.
 * Countries Of The World App 2.5
 * @author Caleb Viola
 */
public class CountryData {
	private RandomAccessFile file;
	private int byteOffset;
	
	/*******************************************************************
	 * Initializes the binary file.
	 * @param tL
	 * @param fileName
	 * @throws IOException
	 */
	public CountryData(int fileNameSufix, TheLog tL) throws IOException{
		file = new RandomAccessFile(String.format("CountryData%d.txt",
									fileNameSufix), "r");
	}	
	
	/**************************************************************
	 * Locates element in txt file by country DRP.
	 * @param Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void selectByDRP(int DRP, TheLog tl) throws IOException{
		if (DRP != -1){
			String line = "";
			byteOffset(DRP);
			for (int i = 0; i < 23; i++)
				line += (char)file.readByte();
			tl.printThis(String.format(">>> %s",line));
		}
	}
	
	/**************************************************
	 * For calculating byteOffset and seeking it.
	 * @param RRN
	 * @param file
	 * @return spot status.
	 * @throws IOException
	 */
	public void byteOffset(int DRP) throws IOException{
		byteOffset = (DRP-1) * 25;
		file.seek(byteOffset);
		
	}
	
	/**********************************************************************
	 * Closes binary file.
	 * @param tL TheLog object
	 * @param printTable
	 * @throws IOException
	 */
	public void finishUp(TheLog tL, boolean printTable) throws IOException{
		file.close();
	}
}
