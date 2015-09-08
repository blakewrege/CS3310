package edu.wmich.cs3310.a2;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/********************************
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class PrettyPrintUtility {

	/***********************************************************
	 * Displays the backup and countrydata files to the log file
	 * @throws IOException
	 */
	public static void main() throws IOException{
		Scanner input = new Scanner(new File("CountryData.txt"));
		PrintWriter log = new PrintWriter(new FileOutputStream(new File(
				"Log.txt"), true));
		String temp;
		String[] line = input.nextLine().split("'");
		int maxId = Integer.parseInt(line[1]);
		log.println("CODE STATUS > PrettyPrintUtility started");
		log.printf("N: %d | MaxId: %d\n", Integer.parseInt(line[0]), maxId);
		log.println("DATA STORAGE");
		log.println("LOC/ CDE ID- NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE");
		for (short x = 1; x <= maxId; x++) {
			temp = input.nextLine();
			if (Character.isLetter(temp.charAt(0))) {
				line = temp.split("'");
				log.println(String.format(
						"%03d/ %s %s %-18s %-13s %,10d %,13d %4.1f", x,
						line[0], line[1], line[2], line[3],
						Integer.parseInt(line[4]), Integer.parseInt(line[5]),
						Float.parseFloat(line[6])));
			} else
				log.println(String.format("%03d/     %03d ...", x, x));
		}
		input.close();
		
		input = new Scanner(new File("Backup.txt"));
		line = input.nextLine().split("'");
		int N = Integer.parseInt(line[0]);
		log.println("");
		log.println("NAME INDEX");
		log.printf("N: %s | NextEmpty: %s | RootPtr: %s\n", N, line[1], line[2]);
		log.println("LOC/ Lch NAME-------------- PTR Rch");
		for (short x = 0; x < N; x++) {
			line = input.nextLine().split("'");
			log.println(String.format("%03d/ %03d %-18s %03d %03d", x,
					Short.parseShort(line[0]), line[1],
					Short.parseShort(line[2]), Short.parseShort(line[3])));
		}
		log.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.println("CODE STATUS > PrettyPrintUtility finished");
		input.close();
		log.close();
	}
}
