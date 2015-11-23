package edu.wmich.cs3310.asgn5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**************************************
 * Controller for format/functionality.
 * from the TransData files
 * TEST MODE World Data App 2.5
 * @author Caleb Viola
 */
public class UserApp {
	private static PrintWriter tL;
	private static Scanner input;
	private static String code;
	private static String command;

	/*********************************************************
	 * Manage functionalities as requested by TransData files.
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void main(int fileNameSufix)throws IOException {
		tL = new PrintWriter(new FileOutputStream(new File("Log.txt"), true));
		File file = new File(String.format("A5TransData%d.txt", fileNameSufix));
		input = new Scanner(file);
		
		ActualData aD = new ActualData(fileNameSufix);
		CodeIndex cI= new CodeIndex(fileNameSufix, tL);
		
		while(grabCommand()) switch (command) {
			case "SC":
				tL.print("SC " + code);
				aD.selectByDRP(cI.selectByCode(code, tL), tL);
				tL.printf("\t[nodes read: %d]\n", cI.getNodesRead());
				break;
			default:
				tL.print(command + "\n   ERROR, invalid command.");
		}
		
		input.close();
		aD.finishUp();
		cI.finishUp();
		tL.print("========================================\n\n");
		tL.close();
	}
	
	/*********************************************
	 * Obtain command from line in TransData file.
	 * @throws IOException
	 */
	private static boolean grabCommand() {
		if (input.hasNextLine()) {
			String temp = input.nextLine();
			command = temp.substring(0, 2);
			if (command.equals("SC"))
				code = temp.substring(3, temp.length()).trim();
			return true;
		} else return false;
	}
}
