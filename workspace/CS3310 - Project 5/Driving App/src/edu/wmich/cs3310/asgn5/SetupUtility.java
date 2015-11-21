package edu.wmich.cs3310.asgn5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class SetupUtility {
	private static TheLog tL;
	private static short n;
	private static String line;
	private static boolean done;
	private static Scanner input;
	private static char tableType;
	private static short[][] table;
	private static PrintWriter cityNames;
	private static RandomAccessFile roads;

	/*****************************************************************
	 * 
	 * @param fileNameSufix
	 * @param tL
	 * @throws IOException
	 */
	public static void Setup(String fileNameSufix) throws IOException{
		tL = new TheLog("SU");
		cityNames = new PrintWriter("CityNames.txt");
		roads = new RandomAccessFile((String.format("%sRoads.bin",fileNameSufix)),"rw");
		
		File file = new File(String.format("%sMapData.txt",fileNameSufix));
		input = new Scanner(file);
		
		getCityTypeNum();
		table = new short[n][n];
		for(short [] eachRow : table)
			Arrays.fill(eachRow, Short.MAX_VALUE);
		fillDiagZero();
		
		tL.printThisln(String.format("Map Data: %s  Number of cities: %d", fileNameSufix, n));
		
		getCityNamesAndTxt();
		
		tL.printHeader(n);
		
		getRoads();
		
		prettyPrint();
		
		input.close();
		roads.close();
		cityNames.close();
		tL.finishUp();
	}
	
	/***********************************
	 * 
	 */
	public static void getCityTypeNum(){
		done = false;
		while (input.hasNextLine() && !done){
			line = input.nextLine();
			if(line.charAt(0) != '%'){
				tableType = line.charAt(0);
				n = Short.parseShort(line.substring(2));
				done = true;
			}
		}
	}
	
	/*********************************
	 * 
	 * @param tL
	 */
	public static void getCityNamesAndTxt(){
		done = false;
		cityNames.println(n);
		while(input.hasNextLine() && !done){
			line = input.nextLine();
			if (line.charAt(0) != '%'){
				for(int i = 0; i < n; i++){
					cityNames.println(line);
					tL.printThisln(line);
					line = input.nextLine();
				}
				done = true;
			}
		}
	}
	
	/**************************************
	 * 
	 * @param tL
	 */
	public static void getRoads(){
		String[] numArray;
		while(input.hasNextLine()){
			line = input.nextLine();
			if (line.charAt(0) != '%'){
				numArray = line.split(" ");
				table[Short.parseShort(numArray[0])]
					 [Short.parseShort(numArray[1])] 
					= Short.parseShort(numArray[2]);
				if(tableType == 'U'){
					table[Short.parseShort(numArray[1])]
						 [Short.parseShort(numArray[0])] 
					    = Short.parseShort(numArray[2]);
				}
			}
		}
	}
	
	/*********************************
	 * 
	 */
	public static void fillDiagZero(){
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				if (x == y)
					table[x][y] = 0;
	}
	
	/**************************************************
	 * 
	 * @throws IOException
	 */
	public static void tableToBin() throws IOException{
		roads.writeShort(n);
		for (int j = 0; j < n ; j++)
			for (int k = 0; k < n; k++)
				roads.writeShort(table[j][k]);
	}
	
	/***************************************************
	 * 
	 * @param tL
	 * @throws IOException
	 */
	public static void prettyPrint() throws IOException {
		for (int x = 0; x < n; x++){
			tL.printThis(String.format("%2d|", x));
			for (int y = 0; y < n; y++){
				if(table[x][y] == Short.MAX_VALUE)
					tL.printThis("  inf");
				else 
					tL.printThis(String.format("%5d",table[x][y]));
			}
			tL.printThis("\n");
		}
				
	}

}
