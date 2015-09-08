package edu.wmich.cs3310.asgn1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/********************************
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class PrettyPrintUtility {

	/******************************************
	 * Displays the backup file to the log file
	 * @throws IOException
	 */
	public static void main() throws IOException{
		File backup = new File("Backup.txt");
		Scanner input = new Scanner(backup);
		PrintWriter log = new PrintWriter(new FileOutputStream(new File(
				"Log.txt"), true));
		DecimalFormat hundredZeros = new DecimalFormat("#000");
		String temp;
		String line[];
		int N = Integer.parseInt(input.nextLine());
		log.println("CODE STATUS > PrettyPrintUtility started");
		log.println("N: " + N);
		log.println("DATA STORAGE");
		log.println("LOC/ CDE ID- NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE");
		for (short x = 1; x <= N; x++) {
			temp = input.nextLine();
			if (!temp.equals("empty")) {
				line = temp.split("_");
				log.println(String.format(
						"%s/ %s %s %-18.18s %-13s %10s %13s %4.1f",
						hundredZeros.format(x), line[0], line[1], line[2],
						line[3], line[4], line[5], Float.parseFloat(line[6])));
			} else
				log.println(String.format("%s/     %s ...",
						hundredZeros.format(x), hundredZeros.format(x)));
		}
		
		log.println("");
		log.println("NAME INDEX");
		log.println("LOC/ NAME-------------- PTR");
		int i = 0;
		while (input.hasNextLine()){
			line = input.nextLine().split("_");
			log.println(String.format("%s/ %-18.18s %s", 
				  hundredZeros.format(i++), line[0], line[1]));
		}
		log.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.println("CODE STATUS > PrettyPrintUtility finished");
		input.close();
		log.close();
	}
}
