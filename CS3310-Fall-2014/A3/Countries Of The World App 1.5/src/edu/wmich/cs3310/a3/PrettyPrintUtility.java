package edu.wmich.cs3310.a3;

import java.io.*;

/********************************
 * Countries Of The World App 1.0
 * Modified version (per Kaminski's approval)
 * @author Caleb Viola
 */
public class PrettyPrintUtility {
	private static RandomAccessFile file;
	/******************************************************
	 * Displays the countrydata binary file to the log file
	 * @throws IOException on I/O error
	 */
	public static void main() throws IOException {
		file = new RandomAccessFile("CountryData.bin", "r");
		PrintWriter log = new PrintWriter(new FileOutputStream(
				new File("log.txt"), true));
			
		log.println("STATUS > Log FILE opened");
		log.println("STATUS > CountryData FILE opened");
		
		file.seek(0);
		short MAX_N_HOME_LOC = file.readShort();
		short nHome = file.readShort();
		short nColl = file.readShort();
		
		log.println("DATA STORAGE");
		log.println(String.format("MAX_N_HOME_LOC: %02d | nHome: %02d | nColl: %02d", 
								   MAX_N_HOME_LOC, nHome, nColl));
		log.println("LOC/ CDE ID- NAME----------- CONTINENT---- ------AREA ---POPULATION LIFE LINK");
		
		String toPrint;
		for (short x = 1; x <= (MAX_N_HOME_LOC + nColl); x++){
			toPrint = String.format("%03d/ %s %03d %s %s %,10d %,13d %4.1f %03d", 
					x, readString(3), file.readShort(), readString(15), readString(13),
					file.readInt(), file.readLong(), file.readFloat(), file.readShort());
			toPrint = (Character.isLetter(toPrint.charAt(5)) 
					? toPrint 
					: String.format("%03d/ ...", x));
			log.println(toPrint);
		}

		log.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.println("STATUS > CountryData FILE closed");
		log.println("STATUS > Log FILE closed");
		file.close();
		log.close();
	}
	
	/**********************************
	 * Reads specified length as string
	 * @param length
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readString(int length)
			throws IOException {
		String string = "";
		for (int x = 0; x < length; x++)
			string += (char) file.readByte();
		return string;
	}
}