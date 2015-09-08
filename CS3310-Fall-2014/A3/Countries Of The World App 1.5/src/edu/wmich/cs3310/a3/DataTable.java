package edu.wmich.cs3310.a3;

import java.io.*;

/********************************
 * Countries Of The World App 1.5
 * @author Caleb Viola
 */
public class DataTable {
	private short MAX_N_HOME_LOC;
	private short nHome; 
	private short nColl;
	private int homeAddress;
	private RandomAccessFile file;
	private DataTableRecord dtr;

	/*************************************************************
	 * Constructor
	 * @param fileName
	 * @param log
	 * @throws IOException
	 */
	public DataTable(String fileName, Log log) throws IOException{
		file = new RandomAccessFile(fileName, "rw");
		file.seek(0);
		try {
			MAX_N_HOME_LOC = file.readShort();
			nHome = file.readShort();
			nColl = file.readShort();
		} catch (Exception e) {
			MAX_N_HOME_LOC = 20;
			nHome = 0;
			nColl = 0;
			file.writeShort(MAX_N_HOME_LOC);
			file.writeShort(nHome);
			file.writeShort(nColl);
		}
		log.displayThis("FILE STATUS > CountryData FILE opened");
		dtr = new DataTableRecord();
	}
	
	/****************************************
	 * hashFunction which returns homeAddress
	 * @param code
	 * @return homeAddress
	 */
	private void hashFunction(char[] code){
		homeAddress = (code[0] * code[1] * code[2]) % MAX_N_HOME_LOC;
		homeAddress = (homeAddress == 0) ? MAX_N_HOME_LOC : homeAddress;
	}
	

	/************************************************************
	 * IN command
	 * @param code
	 * @param id
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @param userApp
	 * @param log
	 * @throws IOException
	 */
	public int insertCountry(String code, short id, String name,
			String continent, int area, long population, float lifeExpectancy,
			Log log) throws IOException {
		boolean[] status;
		short oldLink;
		hashFunction(code.toCharArray());
		dtr.byteOffset(homeAddress);
		status = dtr.locateWithStatus(file);
		if ((status[0] && status[1]) || (!status[0] && status[1])){
			dtr.write1Country(file, code, id, name, continent, area,
					population, lifeExpectancy, (short) -1);
			nHome++;
		}
		else if (status[0] && !status[1]){
			nColl++;
			int collPos = MAX_N_HOME_LOC + nColl;
			dtr.readLink(file);
			oldLink = dtr.getLink();
			
			dtr.byteOffset(homeAddress);
			dtr.writeLink(file, collPos);
			
			dtr.byteOffset(collPos);
			dtr.locateWithStatus(file);
			dtr.write1Country(file, code, id, name, continent, area,
					population, lifeExpectancy, oldLink);
		}
		return homeAddress;
	}

	/************
	 * DC command
	 * @param code
	 * @param log
	 * @throws IOException
	 */
	public void deleteByCode(String code, Log log) throws IOException {
		log.displayThis("   [SORRY, Select By Code module not yet working]");
	}
	
	/******************************************************************
	 * SC command
	 * @param code
	 * @param log
	 * @throws IOException 
	 */
	public void selectByCode(String code, Log log) throws IOException {
		hashFunction(code.toCharArray());
		dtr.byteOffset(homeAddress);
		boolean[] status = dtr.locateWithStatus(file);
		if (status[0] && !status[1]){
			dtr.read1Country(file);
			if (dtr.getCode().equals(code))
				log.displayThis("   "
						+ log.country(dtr.getCode(), dtr.getId(),
								dtr.getName(), dtr.getContinent(),
								dtr.getArea(), dtr.getPopulation(), dtr.getLifeExpectancy()));
			else 
				if (dtr.getLink() == -1)
					log.displayThis("   SORRY, invalid code");
				else 
					if (findInColl(dtr.getLink(), code) == -1)
						log.displayThis("   SORRY, invalid code");
					else
						log.displayThis("   "
								+ log.country(dtr.getCode(), dtr.getId(),
										dtr.getName(), dtr.getContinent(),
										dtr.getArea(), dtr.getPopulation(),
										dtr.getLifeExpectancy()));
		}
		else
			log.displayThis("   SORRY, invalid code");
	}
	
	/*********************************************************
	 * recursive method for finding elements in collision area
	 * @param link
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public int findInColl(int link, String code) throws IOException{
		if (link != -1){
			dtr.byteOffset(link);
			dtr.locateWithStatus(file);
			dtr.read1Country(file);
			if (dtr.getCode().equals(code))
				return 1;
			else
				return findInColl(dtr.getLink(), code);
		}
		else
			return -1;
	}

	/****************************************
	 * Dumps header data into CountryData.bin
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		file.seek(0);
		file.writeShort(MAX_N_HOME_LOC);
		file.writeShort(nHome);
		file.writeShort(nColl);
		file.close();
	}
}
