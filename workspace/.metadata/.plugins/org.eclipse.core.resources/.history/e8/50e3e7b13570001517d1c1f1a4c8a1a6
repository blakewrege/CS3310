/*
 * Author: Talivaldis Strautkalns
 * Description: prettyPrint program for Assignment 2
 */


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PrettyPrintTS {
	static short n;
	static short maxID;
	static boolean UTFFlag = false;
	static boolean AsciiFlag = false;
	static short id;
	static char code[] = new char[3];
	static char name[] = new char[18];
	static char continent[] = new char[13];
	static int area;
	static long population;
	static float lifeExpectancy;

	public static void main(String[] args) throws IOException {
		FileInputStream binaryInputStream;
		BufferedWriter  logWriter = new BufferedWriter(new FileWriter("log.txt", true));
		DataInputStream binaryReader;
		binaryInputStream = new FileInputStream("DataStorage.bin");
		binaryReader = new DataInputStream(binaryInputStream);
		
		n = binaryReader.readShort();
		maxID = binaryReader.readShort();
		logWriter.write("-->> PRETTYPRINT started");
		logWriter.newLine();
		logWriter.write("===========================");
		logWriter.newLine();
		
		logWriter.write(String.format("N is %d, MaxID is %d", n, maxID));
		logWriter.newLine();logWriter.newLine();
		logWriter.write("LOC> CDE ID- NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE");
		logWriter.newLine();
		int count=1;
		
		String input= " ";
		Scanner keyboard = new Scanner(System.in);
		
		while(input.charAt(0)!= 'Y' && input.charAt(0)!= 'y' &&input.charAt(0)!= 'N' && input.charAt(0)!= 'n'){
			System.out.println("Did you use writeUTF for name and continent? (Enter Y or N)");
			input = keyboard.next();
		}
		
		if(input.charAt(0)== 'Y' && input.charAt(0)== 'y'){
			UTFFlag = true;
		}else{
			input = " ";
			while(input.charAt(0)!= 'Y' && input.charAt(0)!= 'y' &&input.charAt(0)!= 'N' && input.charAt(0)!= 'n'){
				System.out.println("Did you use write characters in Ascii(Java defaults to Unicode)? (Enter Y or N)");
				input = keyboard.next();
			}
			if(input.charAt(0)== 'Y' || input.charAt(0)== 'y'){
				AsciiFlag = true;
			}
		}
		keyboard.close();
		
		for(int i=0;i<maxID;i++,count++){
			read1Record(binaryReader);
			
			if(id!=0){
				System.out.println(code);
				logWriter.write(String.format("%03d> %-3s %03d %-18.18s %-13.13s %,10d %,13d %04.1f", count, new String(code), id, new String(name), new String(continent), area, population, lifeExpectancy));
			}else{
				logWriter.write(String.format("%03d> EMPTY", count));
			}
			logWriter.newLine();
		}
		logWriter.write("===========================");
		logWriter.newLine();
		logWriter.write("-->> PRETTYPRINT finished");
		logWriter.newLine();
		
		binaryReader.close();
		logWriter.close();
	}
	
	private static void read1Record(DataInputStream binaryReader) throws IOException{
		id = binaryReader.readShort();
		
		if(UTFFlag == true){
			code = binaryReader.readUTF().toCharArray();
		}else if(AsciiFlag == true){
			for(int i =0;i<3;i++){
				code[i] = (char)binaryReader.readByte();
			}
		}else{
			for(int i =0;i<3;i++){
				code[i] = binaryReader.readChar();
			}
		}
		
		if(UTFFlag == true){
			name = binaryReader.readUTF().toCharArray();
		}else if(AsciiFlag == true){
			for(int i =0;i<18;i++){
				name[i] = (char)binaryReader.readByte();
			}
		}else{
			for(int i =0;i<18;i++){
				name[i] = binaryReader.readChar();
			}
		}
		
		if(UTFFlag == true){
			continent = binaryReader.readUTF().toCharArray();
		}else if(AsciiFlag == true){
			for(int i =0;i<13;i++){
				continent[i] = (char)binaryReader.readByte();
			}
		}else{
			for(int i =0;i<13;i++){
				continent[i] = binaryReader.readChar();
			}
		}
		
		area = binaryReader.readInt();
		
		population = binaryReader.readLong();
		
		lifeExpectancy = binaryReader.readFloat();
	}
}
