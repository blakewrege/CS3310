package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Caleb Viola
 */
public class CountryIndex {
	private short N;
	private final int MAX_N_HOME_LOC = 20;
	private Node[] index;
	private int homeSubscript;
	private int nHome;
	private int nColl;
	private int nodRead;
	private int nextEmpty;
	
	
	/**************************************************
	 * @param tl
	 * @throws IOException 
	 */
	public CountryIndex(TheLog tl) throws IOException {
		N = 0;
		index = new Node[MAX_N_HOME_LOC];
		nHome = 0;
		nColl = 0;
	}
	
	/*********************************************************
	 * Returns homeSubscript.
	 * @param code
	 * @param MAX_N_HOME_LOC
	 * @return
	 */
	private int hashFunction(String code, int MAX_N_HOME_LOC){
		return ((int)code.charAt(0)*(int)code.charAt(1)
			    *(int)code.charAt(2))% MAX_N_HOME_LOC;
	}
	
	/*******************************************************************
	 * Execution for SC command which locates an
	   element using country code.
	 * @param Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public int selectByCode(String code, TheLog tl) throws IOException{
		homeSubscript = hashFunction(code, MAX_N_HOME_LOC);
		nodRead = 0;
		return checkLocation(homeSubscript, code);
	}
	
	/**********************************************************
	 * Recursive method to examine collision locations.
	 * @param homeSubscript
	 * @return
	 */
	private int checkLocation(int homeSubscript, String code) {
		if (index[homeSubscript] == null)
			return -1;
		else if (index[homeSubscript].getLink() == -1)
			if (index[homeSubscript].getCode().equals(code))
				return index[homeSubscript].getDRP();
			else
				return -1;
		else 
			if (index[homeSubscript].getCode().equals(code))
				return index[homeSubscript].getDRP();
			else{
				nodRead++;
				return checkLocation(index[homeSubscript].getLink(), code);
			}
	}

	/************************
	 * Returns number of visited node.
	 * @return the nodRead
	 */
	public int getNodRead() {
		return nodRead;
	}

	/*************************************************************
	 * Execution for IN command which inserts country in index.
	 * @param code
	 * @param rrn
	 * @param tl TheLog object
	 * @param userApp boolean to determine if confirmation message should be logged
	 * @throws IOException 
	 */
	public void insertCodeInIndex(String code, int rrn, TheLog tl,
			boolean userApp) throws IOException {
		homeSubscript = hashFunction(code, MAX_N_HOME_LOC);
		nextEmpty = MAX_N_HOME_LOC + nColl;
		if (index[homeSubscript] != null){
			expandArray();
			index[nextEmpty] = new Node(code, rrn, -1);
			if (index[homeSubscript].getLink() != -1){
				index[nextEmpty].setLink(index[homeSubscript].getLink());
				index[homeSubscript].setLink(nextEmpty);
			}
			else
				index[homeSubscript].setLink(nextEmpty);
			nColl++;
		}
		else{
			index[homeSubscript] = new Node(code, rrn, -1);
			nHome++;
		}
		if (userApp)
			tl.displayThis(tl.Msg("inserted"));
			
//		tl.displayThis("   SORRY, not yet working");
	}
	
	/*******************************************************************
	 * Deletes country in table by code.
	 * @param id
	 * @param tl
	 * @throws IOException
	 */
	public void deleteByCode(String code, TheLog tl) throws IOException{
		tl.displayThis("   SORRY, not yet working.");
	}

	/******************************************************
	 * Backs up index to file.
	 * @throws IOException 
	 */
	public void backupIndex(TheLog tL) throws IOException {
		PrintWriter file = new PrintWriter("IndexBackup.csv");
		tL.displayThis("FILE STATUS > IndexBackup File opened.");
		for (int i = 0; i < index.length; i++){
			if(index[i] != null)
				file.println(index[i].getCode() + ","
						+ index[i].getDRP() + ","
						+ index[i].getLink());
			else
				file.println(",,-1");
				
		}
		file.close();
		tL.displayThis("FILE STATUS > IndexBackup File backed up and closed.");
			
	}
	
	/*******************************************************
	 * Restores index from file.
	 * @throws IOException 
	 */
	public void restoreIndex(TheLog tL) throws IOException {
		File file = new File ("IndexBackup.csv");
		tL.displayThis("FILE STATUS > IndexBackup File opened.");
		Scanner input = new Scanner (file);
		String[] data;
		int i = 0;
		while (input.hasNextLine()){
			data = input.nextLine().split(",");
			if (!data[0].equals("")){
				if (i < MAX_N_HOME_LOC)
					index[i] = new Node(data[0], Integer.parseInt(data[1]),
							Integer.parseInt(data[2]));
				else{
					expandArray();
					index[i] = new Node(data[0], Integer.parseInt(data[1]),
							Integer.parseInt(data[2]));
				}
			}
			i++;
		}
		input.close();
		tL.displayThis("FILE STATUS > IndexBackup File restored and closed.");
	}
	
	/*************************
	 * Expands Node array by 1.
	 */
	public void expandArray(){
		index = Arrays.copyOf(index, index.length+1);
	}

	/****************************************************
	 * Prints index file.
	 * @param tL
	 * @throws IOException 
	 */
	private void snapshot(TheLog tL) throws IOException {
		tL.displayThis("CODE STATUS > Snapshot CountryIndex started.");
		DecimalFormat noFormat;
		tL.displayThis(String.format(
				"CODE INDEX > MAX_N_HOME_LOC: %d, nHome: %d, nColl: %d",
				MAX_N_HOME_LOC, nHome, nColl)+"\n");
		tL.displayThis("[SUB] CODE | DRP | LINK |");
		for (int i = 0; i < index.length; i++){
			if (index[i] != null){
				if (index[i].getLink() != -1)
					noFormat = new DecimalFormat("000");
				else
					noFormat = new DecimalFormat("#00");
				tL.displayThis(String.format("[%s] %s  | %s | %s  |",
						new DecimalFormat("000").format(i),
						index[i].getCode(),
						new DecimalFormat("000").format(index[i].getDRP()),
						noFormat.format(index[i].getLink())));
				N++;
			}
			else
				tL.displayThis(String.format("[%s] EMPTY", new DecimalFormat(
						"000").format(i)));
		}
		tL.displayThis("CODE STATUS > Snapshot CountryIndex finished - " + N
				+ " countries displayed.");
	}
	
	/***********************************************************************
	 * @param tL
	 * @param b
	 * @throws IOException 
	 */
	public void finishUp(TheLog tL, boolean printTable) throws IOException {
		if (printTable)
			snapshot(tL);
		backupIndex(tL);
	}

}
