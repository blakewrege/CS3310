package edu.wmich.cs3310.a3;

import java.io.*;

/***********************
 * Manages file handling
 * Countries Of The World App 1.5
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
	private short link;
	private int byteOffset;
	private int sizeOfHeaderRec = 2 /*MAX_N_HOME_LOC*/ + 2 /*nHome*/ + 2 /*nColl*/;
	private int sizeOfDataRec = 3 /*code*/+ 2 /*id*/+ 15 /*name*/
			+ 13 /*continent*/+ 4 /*area*/+ 8 /*population*/+ 4 /*lifeExpectancy*/ + 2 /*link*/;

	/***********************************
	 * For reading a country from record
	 * @param file
	 * @throws IOException 
	 */
	public void read1Country(RandomAccessFile file) throws IOException{
		code = readString(3, file);
		id = file.readShort();
		name = readString(15, file);
		continent = readString(13, file);
		area = file.readInt();
		population = file.readLong();
		lifeExpectancy = file.readFloat();
		link = file.readShort();
	}
	
	/**********************************
	 * Reads specified length as string
	 * @param length
	 * @param file
	 * @return string
	 * @throws IOException
	 */
	public static String readString(int length, RandomAccessFile file)
			throws IOException {
		String string = "";
		for (int x = 0; x < length; x++)
			string += (char) file.readByte();
		return string;
	}
	
	/***********************************
	 * read link field at current offset
	 * @param file
	 * @throws IOException
	 */
	public void readLink(RandomAccessFile file) throws IOException{
		offsetForLink();
		file.seek(byteOffset);
		link = file.readShort();
	}
	
	/************************************
	 * write link field at current offset
	 * @param file
	 * @param link
	 * @throws IOException
	 */
	public void writeLink(RandomAccessFile file, int link) throws IOException{
		offsetForLink();
		file.seek(byteOffset);
		file.writeShort(link);
	}

	/*********************************
	 * For writing a country to record
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
			long population, float lifeExpectancy, short link) throws IOException {	
		file.writeBytes(code);
		file.writeShort(id);
		file.writeBytes(String.format("%-15.15s", name));
		file.writeBytes(String.format("%-13.13s", continent));
		file.writeInt(area);
		file.writeLong(population);
		file.writeFloat(lifeExpectancy);
		file.writeShort(link);
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
	
	/***************************
	 * Calculate offset for link
	 * @param rrn
	 */
	public void offsetForLink(){
		byteOffset += (sizeOfDataRec - 2);
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

	/*****************
	 * Getter for link
	 * @return
	 */
	public short getLink() {
		return link;
	}
	
	/***********************
	 * Getter for byteOffset
	 * @return 
	 */
	public int getByteOffset() {
		return byteOffset;
	}

}
