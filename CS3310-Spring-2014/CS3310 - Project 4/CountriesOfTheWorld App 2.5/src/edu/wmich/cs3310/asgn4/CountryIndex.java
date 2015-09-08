package edu.wmich.cs3310.asgn4;

import java.io.IOException;
import java.io.RandomAccessFile;

/*****************************
 * Handles the CodeIndex files
 * @author Caleb Viola
 */
public class CountryIndex {
	private RandomAccessFile file;
	private short M;
	private short rootPtr;
	private short[] TP;
	private String[] KV;
	private short[] DRP;
	private int byteOffset;
	private int sizeOfHeaderRec;
	private int sizeOfDataRec;
	private int nodesRead;
	
	/********************************************************************
	 * Initializes objects such as the binary file .
	 * @param tL
	 * @param fileName
	 * @throws IOException
	 */
	public CountryIndex(int fileNameSufix, TheLog tL) throws IOException{
		file = new RandomAccessFile(String.format("CodeIndex%dUC.bin",
				   					fileNameSufix), "r");
		tL.printThis("========================================");
		tL.printThis(String.format("PROCESSING A4TransData%d.txt\n",fileNameSufix));
			
		M = file.readShort();
		rootPtr = file.readShort();
		sizeOfHeaderRec = (Short.SIZE/8)*3;
		sizeOfDataRec = (Short.SIZE * M  + Character.SIZE * 3 * (M - 1) + Short.SIZE * (M - 1))/8;
		TP = new short[M];
		KV = new String[M-1];
		DRP = new short[M-1];
	}	
	
	/*****************************************
	 * Reads a node from binary file.
	 * @throws IOException
	 */
	public void readNode() throws IOException{
		nodesRead++;
		for(int i = 0; i < M-1; i++)
			KV[i] = "";
		
		for(int i = 0; i < M; i++)
			TP[i] = file.readShort();
		for(int i = 0; i < M-1; i++)
			for(int j = 0; j < 3; j++)
				KV[i] += file.readChar();
		for(int i = 0; i < M-1; i++)
			DRP[i] = file.readShort();
	}
	
	
	/******************************************************************
	 * Locates code in index.
	 * @param Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public int selectByCode(String code, TheLog tL) throws IOException{
		nodesRead = 0;
		int result = searchNode(rootPtr, code);
		if (result == -1)
			tL.error();
		return result;
	}
	
	/********************************************************************
	 * Recursive method that searches for data in 1 node.
	 * @param homeSubscript
	 * @return DRP if existent, -1 if not.
	 * @throws IOException 
	 */
	private int searchNode(int pointer, String code) throws IOException {
		byteOffset(pointer);
		readNode();
		
		for (int i = 0; i < M-1; i++){	
			if(code.compareTo(KV[i]) < 0)
				if (TP[i] != -1)
					return searchNode(TP[i], code);
				else
					return -1;
			else if (code.compareTo(KV[i]) == 0)
				return DRP[i];
			else if ((code.compareTo(KV[i]) > 0 && i+1 == M-1)
				  || (code.compareTo(KV[i]) > 0 && KV[i+1].equals("]]]"))) 
				if (TP[i+1] != -1)
					return searchNode(TP[i+1], code);
				else
					return -1;
		}
		
		return -1; 
	}

	/******************************************************************
	 * For calculating and locating byteOffset.
	 * @param RRN
	 * @param file
	 * @return spot status.
	 * @throws IOException
	 */
	public void byteOffset(int rootPtr) throws IOException{
		byteOffset = sizeOfHeaderRec + ((rootPtr - 1) * sizeOfDataRec);
		file.seek(byteOffset);
	}
	
	/**************************
	 * Returns number of nodes read in the search.
	 * @return nodesRead
	 */
	public int getNodesRead() {
		return nodesRead;
	}
	
	/***********************************************************************
	 * Closes binary file.
	 * @param tL
	 * @param b
	 * @throws IOException 
	 */
	public void finishUp(TheLog tL, boolean printTable) throws IOException {
		file.close();
	}


}
