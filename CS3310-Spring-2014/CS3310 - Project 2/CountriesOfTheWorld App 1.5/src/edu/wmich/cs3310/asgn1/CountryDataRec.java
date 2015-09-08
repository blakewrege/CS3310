package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.io.RandomAccessFile;

/******************************************
 * Intended to manage binary file handling.
 * @author Caleb Viola
 */
public class CountryDataRec {
	private String code;
	private short id;
	private String name;
	private String continent;
	private int area;
	private long population;
	private float lifeExpectancy;
	private int byteOffset;
	private int sizeOfHeaderRec = 2;
	private int sizeOfDataRec = 2 + (2 * 3) + (2 * 16) + (2 * 13) + 4 + 8 + 4;

	/************************************
	 * For reading a country from record.
	 * @param file
	 * @throws IOException 
	 */
	public void Read1Country(RandomAccessFile file) throws IOException{
		code = "";
		name = "";
		continent = "";
		id = file.readShort();
		for (int i = 0; i < 3; i++)
			code += file.readChar();
		for (int i = 0; i < 16; i++)
			name += file.readChar();
		for (int i = 0; i < 13; i++)
			continent += file.readChar();
		area = file.readInt();
		population = file.readLong();
		lifeExpectancy = file.readFloat();
	}
	
	/************************************
	 * For writing one country to record.
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
	public void Write1Country(RandomAccessFile file,  short id,
			String code, String name, String continent, int area,
			long population, float lifeExpectancy) throws IOException {		
		file.writeShort(id);

		if (code.length() < 3)
			while (code.length() != 3)
				code += " ";
		else if (code.length() > 3)
			code = code.substring(0, 3);
		file.writeChars(code);

		if (name.length() < 16)
			while (name.length() != 16)
				name += " ";
		else if (name.length() > 16)
			name = name.substring(0, 16);
		file.writeChars(name);

		if (continent.length() < 13)
			while (continent.length() != 13)
				continent += " ";
		else if (continent.length() > 13)
			continent = continent.substring(0, 13);
		file.writeChars(continent);

		file.writeInt(area);
		file.writeLong(population);
		file.writeFloat(lifeExpectancy);		
	}
	
	/*******************************************************************
	 * For calculating and locating byteOffset, and returning the status 
	 * of file at given address utilizing a booleans.
	 * @param RRN
	 * @param file
	 * @return spot status.
	 * @throws IOException
	 */
	public boolean[] byteOffsetAndStatus(int RRN, RandomAccessFile file){
		byteOffset = sizeOfHeaderRec + ((RRN - 1) * sizeOfDataRec);
		try {
			file.seek(byteOffset);
			try {
				short test = file.readShort();
				file.seek(byteOffset);
				if (test > 0)
					return new boolean[] { true, false }; // Existent spot
				return new boolean[] { false, true }; // Empty spot
			} catch (IOException e) {
				return new boolean[] { true, true };// New spot
			}
		} catch (IOException e) {
			return new boolean[] { false, false }; // Out of range
		}
	}
	
	/*************************************************
	 * Deletes 1 country from the record by adding 0s.
	 * @param file
	 * @throws IOException 
	 */
	public void Delete1Country(RandomAccessFile file) throws IOException {
		file.seek(getByteOffset());
		for (int i = 0; i < sizeOfDataRec + sizeOfHeaderRec; i++){
			file.writeByte(0000);
		}
		
	}
	
	/****************
	 * Getter for id.
	 * @return
	 */
	public short getID(){
		return id;
	}
	
	/******************
	 * Getter for code.
	 * @return
	 */
	public String getCode(){
		return code;
	}
	
	/******************
	 * Getter for name.
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/***********************
	 * Getter for continent.
	 * @return
	 */
	public String getContinent(){
		return continent;
	}
	
	/******************
	 * Getter for area.
	 * @return
	 */
	public int getArea(){
		return area;
	}
	
	/************************
	 * Getter for population.
	 * @return
	 */
	public long getPopulation(){
		return population;
	}
	
	/****************************
	 * Getter for lifeExpectancy.
	 * @return
	 */
	public float getLifeExpectancy(){
		return lifeExpectancy;
	}

	/************************
	 * Getter for byteOffset.
	 * @return 
	 */
	public int getByteOffset() {
		return byteOffset;
	}

}
