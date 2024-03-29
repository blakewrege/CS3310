package edu.wmich.cs3310.asgn5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/***********************************
 * Manages the FakeActualData files.
 * stored in a binary file
 * TEST MODE World Data App 2.5
 * @author Caleb Viola
 */
public class ActualData {
	private RandomAccessFile file;
	private int byteOffset;
	
	/*******************************************************
	 * Initializes the binary file.
	 * @param tL
	 * @param fileName
	 * @throws IOException
	 */
	public ActualData(int fileNameSufix) throws IOException{
		file = new RandomAccessFile(String.format("FakeActualData%d.txt",
									fileNameSufix), "r");
	}	
	
	/*********************************************
	 * Locates element in txt file by country DRP.
	 * @param DRP Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void selectByDRP(int DRP, PrintWriter tl) throws IOException{
		if (DRP != -1){
			String line = "";
			byteOffset(DRP);
			for (int i = 0; i < 23; i++)
				line += (char)file.readByte();
			tl.printf(" -->> %s\t", line);
		}
	}
	
	/***************************************************
	 * For calculating byteOffset and seeking it.
	 * @param DRP
	 * @return spot status.
	 * @throws IOException
	 */
	private void byteOffset(int DRP) throws IOException{
		byteOffset = (DRP-1) * 25;
		file.seek(byteOffset);
		
	}
	
	/*****************************************
	 * Closes binary file.
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		file.close();
	}	
}
