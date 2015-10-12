package edu.wmich.cs3310.a2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

/***********************
 * Manages file handling
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class DataTableRecord {
	private String code;
	private short id;
	private String name;
	private String continent;
	private int area;
	private long population;
	private float lifeExpectancy;
	private int byteOffset;
	private int sizeOfHeaderRec = 3 /*n*/ + 1 /*single quote*/ + 3 /*maxId*/ + 1 /*line feed*/;
	private int sizeOfDataRec = 3 /*code*/ + 1 /*single quote*/ + 3 /*id*/ + 1 /*single quote*/ +
							    15 /*name*/ + 1 /*single quote*/ + 13 /*continent*/ + 1 /*single quote*/ + 
								8 /*area*/ + 1 /*single quote*/ + 10 /*population*/ + 1 /*single quote*/ + 
								4 /*lifeExpectancy*/ + 1 /*line feed*/;

	/***********************************
	 * For reading a country from record
	 * @param file
	 * @throws IOException 
	 */
	public void read1Country(RandomAccessFile file) throws IOException{
		String[] line = file.readLine().split("'");
		code = line[0];
		id = Short.parseShort(line[1]);
		name = line[2].trim();
		continent = line[3].trim();
		area = Integer.parseInt(line[4]);
		population = Long.parseLong(line[5]);
		lifeExpectancy = Float.parseFloat(line[6]);
	}
	
	/***********************************
	 * For writing one country to record
	 * @param file
	 * @param id
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @throws IOException
	 */
	public void write1Country(RandomAccessFile file, String code, short id,
			String name, String continent, int area,
			long population, float lifeExpectancy) throws IOException {		
		file.writeBytes(String.format(
				"%s'%03d'%-15.15s'%-13.13s'%08d'%010d'%4.1f\n", code, id, name,
				continent, area, population, lifeExpectancy));
	}
	
	/****************************
	 * Fills empty spaces with 0s
	 * @param file
	 * @throws IOException
	 */
	public void fill1record(RandomAccessFile file) throws IOException{
		file.writeBytes(String.format(
				"%-3.3s'%03d'%-15.15s'%-13.13s'%08d'%010d'%04.1f\n", " ", 0,
				" ", " ", 0, 0, 0.0));
	}
	
	/***********************************************************
	 * Discover nature of file at given offset and return status
	 * @param file
	 * @return
	 */
	public boolean[] locateWithStatus(RandomAccessFile file){
		try {
			file.seek(byteOffset);
			try {
				char test = (char)file.readByte();
				file.seek(byteOffset);
				if (Character.isLetter(test))
					return new boolean[] { true, false }; // Occupied spot
				return new boolean[] { false, true }; // Empty spot
			} catch (IOException e) {
				return new boolean[] { true, true };// New spot
			}
		} catch (IOException e) {
			return new boolean[] { false, false }; // Out of range
		}
	}
	
	/******************
	 * Calculate offset
	 * @param rrn
	 */
	public void byteOffset(int rrn){
		byteOffset = sizeOfHeaderRec + ((rrn - 1) * sizeOfDataRec);
	}
	
	/*****************
	 * Getter for code
	 * @return
	 */
	public String getCode(){
		return code;
	}
	
	/***************
	 * Getter for id
	 * @return
	 */
	public short getId(){
		return id;
	}
	
	/*****************
	 * Getter for name
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**********************
	 * Getter for continent
	 * @return
	 */
	public String getContinent(){
		return continent;
	}
	
	/*****************
	 * Getter for area
	 * @return
	 */
	public int getArea(){
		return area;
	}
	
	/***********************
	 * Getter for population
	 * @return
	 */
	public long getPopulation(){
		return population;
	}
	
	/***************************
	 * Getter for lifeExpectancy
	 * @return
	 */
	public float getLifeExpectancy(){
		return lifeExpectancy;
	}

	/***********************
	 * Getter for byteOffset
	 * @return 
	 */
	public int getByteOffset() {
		return byteOffset;
	}

}
