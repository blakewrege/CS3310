package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

/**
 * Manages countries by implementing a table 
 * stored in a binary file.
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class CountryData {
	private short N;
	private boolean[] status;
	private boolean located;
	private RandomAccessFile file;
	private CountryDataRec cDr;
	private final int MAX_N_LOC = 30;
	private int homeRRN;

	/**********************************************************************
	 * Creates binary file and creates CountryDataRec object.
	 * @param tL
	 * @param fileName
	 * @throws IOException
	 */
	public CountryData(TheLog tL, String fileName) throws IOException{
		file = new RandomAccessFile(fileName, "rw");
		try {
			N = file.readShort();
		} catch (IOException e) {
			N = 0;
		}
		tL.displayThis("FILE STATUS > CountryData FILE opened.");
		cDr = new CountryDataRec();
	}	
	
	/***********************************************
	 * Returns the homeRRN.
	 * @param code
	 * @param MAX_N_LOC
	 * @return
	 */
	private int hashFunction(int id, int MAX_N_LOC){
		if (id % MAX_N_LOC == 0)
			return MAX_N_LOC;
		else
			return id % MAX_N_LOC;
	}
	
	/*******************************
	 * Cycle from 30th to 1st location.
	 * @param homeRRN
	 * @return
	 */
	private int cycle (int homeRRN){
		if(homeRRN == 30)
			return 1;
		else
			return homeRRN + 1;
	}
	
	/*************************************************************
	 * Execution for SI command which locates an
	   element in a binary file using country id.
	 * @param Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void selectByID(int id, TheLog tl, boolean fromSelectByCode)
			throws IOException {
		if (id != -1){
			if (!fromSelectByCode)
				homeRRN = hashFunction(id, MAX_N_LOC);
			else
				homeRRN = id;
			int recRead = 0;
			int firstVal = homeRRN;
			located = false;
			while (!located){
				status = cDr.byteOffsetAndStatus(homeRRN, file);
				if (status[0] && !status[1]){
					cDr.readCountry(file);
					if (cDr.getID() != id && !fromSelectByCode){
						homeRRN = cycle(homeRRN);
						recRead++;
						if (homeRRN == firstVal){
							tl.displayThis(tl.sorry());
							tl.displayThis(tl.recReadMsg(recRead));
							located = true;
						}
					}
					else{	
						tl.displayThis("   " + cDr.getID()+ " "
								+ tl.toFormat(cDr.getCode(), cDr.getName(),
										cDr.getContinent(), cDr.getArea(),
										cDr.getPopulation(),
										cDr.getLifeExpectancy()));
						recRead++;
						located = true;
						tl.displayThis(tl.recReadMsg(recRead));
					}
				}
				else if ((status[0] && status[1]) || (!status[0] && status[1])){
					tl.displayThis(tl.sorry());
					located = true;
				}
				else {
					tl.displayThis(tl.sorry3());
					located = true;
				}
			}
		}
		else
			tl.displayThis(tl.sorry());
	}
	
	/*************************************************************************
	 * Method used by setup to insert countries in index.
	 * @param id
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @param tL TheLog object
	 * @param userApp boolean to determine if confirmation message should be logged
	 * @return 
	 * @throws IOException
	 */
	public int insertCountry (short id, String code, String name,
			String continent, int area, long population, float lifeExpectancy,
			TheLog tL, boolean userApp) throws IOException {
		located = false;
		homeRRN = hashFunction(id, MAX_N_LOC);
		while (!located){
			status = cDr.byteOffsetAndStatus(homeRRN, file);
			if ((status[0] && status[1]) || (!status[0] && status[1])){
				cDr.writeCountry(file, id, code, name, continent, area,
						population, lifeExpectancy);
				located = true;
				N++;
				if (userApp)
					tL.displayThis(tL.Msg("inserted"));
			}
			else if (status[0] && !status[1]){
				homeRRN = cycle(homeRRN);
			}
			else
				tL.displayThis(tL.sorry3());
		}
		return homeRRN;
		
	}
	
	/************************************************************
	 * Deletes country in table by id.
	 * @param id
	 * @param tL
	 * @throws IOException
	 */
	public void deleteByID (int id, TheLog tL) throws IOException{
			tL.displayThis("   SORRY, not yet working");
	}
	
	/***************************************************
	 * Prints all countries.
	 * @param tL TheLog object
	 * @throws IOException
	 */
	private void snapshot(TheLog tL) throws IOException{
		tL.displayThis("CODE STATUS > Snapshot CountryData started.");
		DecimalFormat noFormat = new DecimalFormat("#000");
		
		tL.displayThis("N: "+N);
		tL.displayThis(tL.header());
		short rrn = 1;
		status = cDr.byteOffsetAndStatus(rrn, file);
		while (!(status[0] && status[1])){
			cDr.readCountry(file);
			if(cDr.getID() != 0)
				tL.displayThis(tL.country(rrn, cDr.getID(), cDr.getCode(),
						cDr.getName(), cDr.getContinent(), cDr.getArea(),
						cDr.getPopulation(), cDr.getLifeExpectancy()));
			else
				tL.displayThis("["+noFormat.format(rrn)+"] "+"    EMPTY");
			rrn++;
			status = cDr.byteOffsetAndStatus(rrn, file);
		}
		tL.displayThis(tL.footer());
		
		tL.displayThis("CODE STATUS > Snapshot CountryData finished - " + N
				+ " countries displayed.");
	}
	
	/**********************************************************************
	 * Records N in table, calls Snapshot, closes binary file.
	 * @param tL TheLog object
	 * @param printTable
	 * @throws IOException
	 */
	public void finishUp(TheLog tL, boolean printTable) throws IOException{
		file.seek(0);
		file.writeShort(N);
		if (printTable)
			snapshot(tL);
		file.close();
		tL.displayThis("FILE STATUS > CountryData FILE closed.");
	}
}
