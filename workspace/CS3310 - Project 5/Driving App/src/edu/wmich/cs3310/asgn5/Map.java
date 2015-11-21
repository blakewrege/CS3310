package edu.wmich.cs3310.asgn5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Map {
	private short n;
	private short byteOffset;
	private short sizeOfHeaderRec;
	private short sizeOfDataRec;
	private String[] cityNames;
	private RandomAccessFile roadsBin;
	
	/*****************************************
	 * 
	 * @throws FileNotFoundException
	 */
	public Map(String fileNameSufix) throws FileNotFoundException{
		loadCityNameArray();
		sizeOfHeaderRec = 2;
		sizeOfDataRec = (short) (2 * n);
		roadsBin = new RandomAccessFile(String.format("%sRoads.bin", fileNameSufix), "rw");
	}
	
	/*********************************************
	 * @param cityNumber
	 * @return
	 */
	public String whatsCityName(short cityNumber){
		return cityNames[cityNumber];
	}
	
	/*********************************************
	 * 
	 * @param cityName
	 * @return
	 */
	public short whatsCityNumber(String cityName){
		for (short x = 0; x < n; x++)
			if(cityNames[x].equals(cityName))
				return x;
		return -1;
		
	}
	
	/***********************************************************
	 * 
	 * @param cityNum1
	 * @param cityNum2
	 * @return
	 * @throws IOException
	 */
	public short getRoadDistance(short cityNum1, short cityNum2)
			throws IOException {
		byteOffset = (short) (sizeOfHeaderRec + ((cityNum1 - 1) * sizeOfDataRec)
				 + (cityNum2 * 2));
		roadsBin.seek(byteOffset);
		return roadsBin.readShort();
	}
	
	/*************************************************************
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadCityNameArray() throws FileNotFoundException{
		File file = new File("CityNames.txt");
		Scanner input = new Scanner(file);
		n = Short.parseShort(input.nextLine());
		
		for(int x = 0; x < n; x++)
			cityNames[x] = input.nextLine();
		input.close();
	}
	
	/********************
	 * 
	 * @return
	 */
	public short getN() {
		return n;
	}

	/*****************************************
	 * 
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		roadsBin.close();
	}
}
