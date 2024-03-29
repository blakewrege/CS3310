//CLASS: HashTable
//AUTHOR: Blake Wrege
//DESCRIPTION: Setup creates hash table. I used a lot of static arrays to things easier. Uses UIoutput to send to log

//************************************  Assignment 3  **********************************


import java.io.IOException;
import java.util.Arrays;

public class HashTable {
	//
	int MAX_SIZE = 499; // Sets the max size for all arrays
	int IdArr[] = new int[MAX_SIZE];
	String CodeArr[] = new String[MAX_SIZE];
	int HomeArr[] = new int[MAX_SIZE];	// Contains the collision resolved home addresses in the order they were created
	int LinkArr[] = new int[MAX_SIZE];	// Used by setLinks method to store links for chaining
	int ColArr[] = new int[MAX_SIZE];	// Contains raw home addresses before collision resolution
	int ColArrNew[] = new int[MAX_SIZE];// Used by setLinks to sort home addresses
	boolean usedArr[] = new boolean[MAX_SIZE]; // Declares if a location in the array is used
	String HomeLoc[] = new String[MAX_SIZE];   // Contains Code and Id values as a string in correct order
	// Iterators for collisions and home
	int nColl = 0;
	int tColl = 0;
	int nHome = 0;

	// Insert into static arrays from the Setup Program
	public void insert1Country(String code, short id, int count) throws IOException {
		CodeArr[count] = code;
		IdArr[count] = id;

	}
	// Clears all of the variables between each case, this function is called at the end of Hash 
	public void cleanup() {
		HomeArr = new int[MAX_SIZE];
		LinkArr = new int[MAX_SIZE];
		ColArr = new int[MAX_SIZE];
		ColArrNew = new int[MAX_SIZE];
		HomeArr = new int[MAX_SIZE];
		usedArr = new boolean[MAX_SIZE];
		HomeLoc = new String[MAX_SIZE];
		nColl = 0;
		tColl = 0;
		nHome = 0;
	}

	// My giant function that is used to hash all 11 cases. 
	public void hash(int count, int HashFunc, int maxNHomeLoc, int ColRes, UIoutput output) throws IOException {

		int hashnum = 0;
		int hashaddr = 0;
		
		for (int i = 0; i < count; i++) {
			// Calls the desired hash function from hashFormula
			hashnum = hashFormula(HashFunc, i);
			hashaddr = hashnum % maxNHomeLoc;
			if (ColRes == 1) { 			// Sends the address of the hash to linearCol method
				ColArr[i] = hashaddr;
				HomeArr[i] = linearCol(hashaddr, i, maxNHomeLoc);
			}
			if (ColRes == 2) {			// Sends the address of the hash to chainCol method
				ColArr[i] = hashaddr;
				HomeArr[i] = chainCol(hashaddr, i, maxNHomeLoc);
			}


		}
		// Sends hash function and max home location data to log file
		output.displayThis("HASH FUNCTION: " + HashFunc + " (with maxNHomeLoc: " + maxNHomeLoc + ")");

		if (ColRes == 1) {	// Prints the results if Linear
			linearFinshup(maxNHomeLoc);
			tColl = tColl + nColl;
			output.displayThis("COL RESOL ALG: 1 (Linear, Embedded)");
			output.displayThis("N_HOME: " + nHome + ", N_COLL: " + nColl + " --> " + (nColl + nHome));
			output.displayThis("AVE SEARCH PATH (for successful):	(" + nHome + "+" + tColl + ")/(" + nHome + "+"
					+ nColl + ") --> " + roundAvg());
			if (count < 31) {	// Prints the hash table if it's size is 30 or less 
			output.displayThis("LOC  CODE DRP");
				for (int i = 0; i < maxNHomeLoc; i++) {
					linearFormat(i, output);	// Formats each line for log
				}
			}else{
				output.displayThis("HASH TABLE:  big table --> saved paper by not printing it");	
			}
			

		}

		if (ColRes == 2) {	// Prints the results if Chaining
			output.displayThis("COL RESOL ALG: 2 (Chaining, Separate)");
			output.displayThis("N_HOME: " + nHome + ", N_COLL: " + nColl + " --> " + (nColl + nHome));

			setLinks(maxNHomeLoc);	// Sets link values
			searchPathChain(maxNHomeLoc);
			output.displayThis("AVE SEARCH PATH (for successful):	(" + nHome + "+" + tColl + ")/(" + nHome + "+"
					+ nColl + ") --> " + roundAvg());
			if (count < 31) {   // Prints the hash table if it's size is 30 or less 
				output.displayThis("LOC  CODE DRP LINK");
				for (int i = 0; (i < maxNHomeLoc + nColl); i++) {
					if (LinkArr[i] == 0) {
						LinkArr[i] = -1;
					}
					chainFormat(i, output); // Formats each line for log
					}
				
				System.out.println();
			}else{
				output.displayThis("HASH TABLE:  big table --> saved paper by not printing it");	
			}

		}
		cleanup();
		output.displayBrk();

	}

	// A couple functions for rounding average search path
	public String roundAvg() {
		float top = nHome + tColl;
		float bottem = nHome + nColl;
		return roundOff(top / bottem);
	}
	String roundOff(float val) {
		return String.format("%.1f", val);
	}


	// Used to set HomeArr results for linear
	private int linearCol(int hashaddr, int i, int maxNHomeLoc) {

		if (usedArr[hashaddr] == false) {
			nHome++;
		} else {
			nColl++;
		}
		while (usedArr[hashaddr] == true) {
			if (hashaddr < maxNHomeLoc - 1) {
				hashaddr++;
				tColl++;
			} else {
				hashaddr = 0;
				tColl++;
			}
		}
		usedArr[hashaddr] = true;
		return hashaddr;
	}
	// Sorts Code and Id into home locations for linear
	public void linearFinshup(int maxNHomeLoc) {
		for (int i = 0; i < (nColl + nHome); i++) {
			HomeLoc[HomeArr[i]] = CodeArr[i] + " " + IdArr[i];
		}
	}

	// Prints each line of the hash table formated for linear
	public void linearFormat(int loc, UIoutput output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);
		if (HomeLoc[loc] != null && !HomeLoc[loc].isEmpty()) {
			String fields[] = HomeLoc[loc].split(" ");
			int id = Integer.parseInt(fields[1]);
			formatter.format("%03d> %s  %03d", loc, new String(fields[0]), id);
			output.displayThis(buf.toString());
			formatter.close();
		} else {
			formatter.format("%03d>", loc);
			output.displayThis(buf.toString());
			formatter.close();
		}
	}
	// Used to determine total number collisions for chaining
	public void searchPathChain(int maxNHomeLoc) {

		for (int i = 0; i < (maxNHomeLoc + nColl); i++) {
			if (ColArrNew[i] != -1) {
				for (int j = i + 1; j < (maxNHomeLoc + nColl); j++) {
					if (ColArrNew[i] == ColArrNew[j]) {
						LinkArr[i] = j;
						tColl++;
					}
				}

			}
		}

		tColl = tColl + nColl;

	}
	// Sets the link values to the correct addresses for chaining
	public void setLinks(int maxNHomeLoc) {

		Arrays.fill(HomeLoc, "");

		Arrays.fill(ColArrNew, -1);
		for (int i = 0; i < (nHome + nColl); i++) {
			ColArrNew[HomeArr[i]] = ColArr[i];
		}
		for (int i = 0; i < (maxNHomeLoc); i++) {
			if (ColArrNew[i] != -1) {
				for (int j = i + 1; j < (maxNHomeLoc + nColl); j++) {

					if (ColArrNew[i] == ColArrNew[j]) {
						LinkArr[i] = j;

					}
				}

			}
		}

		for (int i = maxNHomeLoc + nColl; i > (maxNHomeLoc); i--) {
			if (ColArrNew[i] != -1) {
				int j = i - 1;
				while (j > (maxNHomeLoc)) {
					if (ColArrNew[i] == ColArrNew[j]) {
						LinkArr[i] = j;
						j = maxNHomeLoc;
					}
					j--;
				}
			}
		}
		// Sorts Code and Id into their home locations for chaining
		for (int i = 0; i < (nHome + nColl); i++) {
			HomeLoc[HomeArr[i]] = CodeArr[i] + " " + IdArr[i];
		}

	}
	
	// Prints each line of the hash table formated for chain
	public void chainFormat(int loc, UIoutput output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);
		if (HomeLoc[loc] != null && !HomeLoc[loc].isEmpty()) {
			String fields[] = HomeLoc[loc].split(" ");
			int id = Integer.parseInt(fields[1]);
			formatter.format("%03d> %s  %03d %03d", loc, new String(fields[0]), id, LinkArr[loc]);
			output.displayThis(buf.toString());
			formatter.close();
		} else {
			formatter.format("%03d>", loc);
			output.displayThis(buf.toString());
			formatter.close();
		}
	}
	// Used to set HomeArr results for chaining
	private int chainCol(int hashaddr, int i, int maxNHomeLoc) {
		if (usedArr[hashaddr] == false) {
			nHome++;
		} else {
			nColl++;
		}

		if (usedArr[hashaddr] == true) {

			hashaddr = maxNHomeLoc + nColl - 1;
		}

		usedArr[hashaddr] = true;
		return hashaddr;
	}
	// This method contains a switch to determine which hash function to use
	public int hashFormula(int select, int i) {
		String hashstr = "";
		switch (select) {
		case 1:
			return CodeArr[i].charAt(0) * CodeArr[i].charAt(1) * CodeArr[i].charAt(2);

		case 2:
			return CodeArr[i].charAt(0) + CodeArr[i].charAt(1) + CodeArr[i].charAt(2);

		case 3:
			hashstr = Integer.toString((int) CodeArr[i].charAt(0)) + Integer.toString((int) CodeArr[i].charAt(1))
					+ Integer.toString((int) CodeArr[i].charAt(2));
			return Integer.parseInt(hashstr);
		case 4:
			hashstr = Integer.toString((int) CodeArr[i].charAt(2)) + Integer.toString((int) CodeArr[i].charAt(1))
					+ Integer.toString((int) CodeArr[i].charAt(0));
			return Integer.parseInt(hashstr);

		}
		return i;

	}

}


