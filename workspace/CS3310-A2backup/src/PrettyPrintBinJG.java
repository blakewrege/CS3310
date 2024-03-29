//PROGRAM: PrettyPrint
//AUTHOR: J.G.
//DESCRIPTION:This program is a developer's utility which reads/prints the DataStorage.bin file (binary file),
//		showing it (nicely) in the Log file. The program just opens, reads/writes and closes DataStorage.bin 
//		and Log.txt files itself since it's PP not OOP.
//NOTE: Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************


import java.io.*;
import java.util.Formatter;

public class PrettyPrintBinJG {
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		final short SIZE_OF_A_CHAR = 1; 
		final short SIZE_OF_CODE = (short) (3 * SIZE_OF_A_CHAR);
		final short SIZE_OF_NAME = (short) (18 * SIZE_OF_A_CHAR);
		final short SIZE_OF_CONT = (short) (13 * SIZE_OF_A_CHAR);
		
		short N, maxID, id;
		int area;
		long population;
		float life;
		int loc = 1;
		
		char[] code = new char[SIZE_OF_CODE];
		char[] name = new char[SIZE_OF_NAME];
		char[] continent = new char[SIZE_OF_CONT];
		byte[] tempCode = new byte[SIZE_OF_CODE];
		byte[] tempName = new byte[SIZE_OF_NAME];
		byte[] tempContinent = new byte[SIZE_OF_CONT];

		String inFileName = "DataStorage.bin";
		String outFileName = "Log.txt";
		RandomAccessFile bReader = new RandomAccessFile(inFileName, "r"); //open for reading only
		Formatter output = new Formatter(new BufferedWriter(new FileWriter(outFileName, true))); //open in append mode
        output.format("-->> PRETTYPRINT started\n");
        
		bReader.seek(0); //start from the beginning of the binary file
		N = bReader.readShort();
		maxID = bReader.readShort();
		
		//print the information of Header: N and maxID, and write to Log.txt
        System.out.printf("N is %d, MaxID is %d\n\n", N, maxID);
        output.format("N is %d, MaxID is %d\n\n", N, maxID);
        
        //print the category, and write to Log.txt
        System.out.printf("LOC> CDE ID- NAME-------------- CONTINENT---- -------AREA ---POPULATION LIFE\n");
        output.format("LOC> CDE ID- NAME-------------- CONTINENT---- -------AREA ---POPULATION LIFE\n");
		
        for(int i = 0; i < maxID; i++)	//loop till finish reading the record of maxID
        {
        	id = bReader.readShort();
    		bReader.read(tempCode); //read 3 chars into a byte array
			code = new String(tempCode).toCharArray(); //convert byte to char array
			bReader.read(tempName); //read 18 chars into a byte array
			name = new String(tempName).toCharArray(); //convert byte to char array
			bReader.read(tempContinent); //read 13 chars into a byte array
			continent = new String(tempContinent).toCharArray(); //convert byte to char array
			area = bReader.readInt();
			population = bReader.readLong();
			life = bReader.readFloat();
			
			if(id != 0)	//if the field is not empty
			{
				//print in console and write to Log.txt using specific format
                System.out.printf("%03d> %s %03d %-18s %-13s %,11d %,13d %4.1f\n", 
                		loc, new String(code), id, new String(name), new String(continent), area, population, life);
                output.format("%03d> %s %03d %-18s %-13s %,11d %,13d %4.1f\n", 
                		loc, new String(code), id, new String(name), new String(continent), area, population, life);
			}
			else //the field is empty
			{
				 System.out.printf("%03d> %s\n", loc, "EMPTY");
	             output.format("%03d> %s\n", loc, "EMPTY");
			}
			loc++;
        }
        
        //Indicates the end of the file
        System.out.printf("===========================\n");
        output.format("===========================\n");
        output.format("-->> PRETTY PRINT finished\n");

		bReader.close();	
		output.close();
	}
}
