package edu.wmich.cs3310.asgn5;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/******************************
 * Handles the CodeIndex files.
 * TEST MODE World Data App 2.5
 * @author Caleb Viola
 */
public class CodeIndex {
	private RandomAccessFile file;
	private short M;
//	private short N;
	private short rootPtr;
	private short[] TP;
	private String[] KV;
	private short[] DRP;
	private int byteOffset;
	private int sizeOfHeaderRec;
	private int sizeOfDataRec;
	private int nodesRead;
	
	/***********************************************************************
	 * Initializes objects such as the binary file.
	 * @param tL PrintWriter object
	 * @param fileName
	 * @throws IOException
	 */
	public CodeIndex(int fileNameSufix, PrintWriter tL) throws IOException {
		file = new RandomAccessFile(String.format("CodeIndex%d.bin",
				   					fileNameSufix), "r");
		tL.print("========================================\n");
		tL.print(String.format("PROCESSING A5TransData%d.txt\n", fileNameSufix));
			
		M = file.readByte();
		rootPtr = file.readByte();
//		N = file.readByte();
		sizeOfHeaderRec = 3;
		sizeOfDataRec = M  + 3 * (M - 1) + (M - 1);
		TP = new short[M];
		KV = new String[M-1];
		DRP = new short[M-1];
	}
	
	/***********************************************************************
	 * Locates code in index.
	 * @param code Element id to locate
	 * @param tl PrintWriter object
	 * @throws IOException
	 */
	public int selectByCode(String code, PrintWriter tL) throws IOException{
		nodesRead = 0;
		int result = searchOneNode(rootPtr, code);
		if (result == -1) tL.print(" -->> Error - code not in index");
		return result;
	}
	
	/********************************************
	 * Reads a node from binary file.
	 * @throws IOException
	 */
	private void readOneNode() throws IOException{
		nodesRead++;
		for(int i = 0; i < M-1; i++)
			KV[i] = "";
		
		for(int i = 0; i < M; i++)
			TP[i] = file.readByte();
		for(int i = 0; i < M-1; i++)
			for(int j = 0; j < 3; j++)
				KV[i] += (char) file.readByte();
		for(int i = 0; i < M-1; i++)
			DRP[i] = file.readByte();
	}
	
	/**********************************************************************
	 * Brings node to memoty to check and search further. 
	 * @param pointer
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int searchOneNode(int pointer, String code) throws IOException {
		byteOffset(pointer);
		readOneNode();
		
		for (int i = 0; i < M-1; i++)
			if(code.compareTo(KV[i]) < 0)
				if (TP[i] != -1)
					return searchOneNode(TP[i], code);
				else return -1;
			else if (code.compareTo(KV[i]) == 0)
				return DRP[i];
			else if ((code.compareTo(KV[i]) > 0 && i+1 == M-1)
				  || (code.compareTo(KV[i]) > 0 && KV[i+1].equals("]]]"))) 
				if (TP[i+1] != -1)
					return searchOneNode(TP[i+1], code);
				else return -1;
		return -1;
	}
	
	/******************************************
	 * For calculating and locating byteOffset.
	 * @param rootPtr
	 * @return spot status.
	 * @throws IOException
	 */
	private void byteOffset(int rootPtr) throws IOException{
		byteOffset = sizeOfHeaderRec + ((rootPtr - 1) * sizeOfDataRec);
		file.seek(byteOffset);
	}	
	
	/*********************************************
	 * Returns number of nodes read in the search.
	 * @return nodesRead
	 */
	public int getNodesRead() {
		return nodesRead;
	}
	
	/*********************
	 * Closes binary file.
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		file.close();
	}
}
