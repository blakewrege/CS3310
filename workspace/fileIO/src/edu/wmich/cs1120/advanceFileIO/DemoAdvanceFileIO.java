/**
 * 
 */
package edu.wmich.cs1120.advanceFileIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author akg
 *
 */
public class DemoAdvanceFileIO {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		writeBinaryFile();
		readBinaryFile();
		writeUTFdataToBinaryFile();
		readUTFdataFromBinaryFile();
		writeToRandomAccessFile();
		readFromRandomAccessFile();

	}
	
/**
 * This method opens a binary file and writes the contents
 * of an int array to the file.
 */

	private static void writeBinaryFile() throws IOException, FileNotFoundException
	{
		// Create an array of integers.
	      int[] numbers = { 2, 4, 6, 8, 10, 12, 14, 13, 11, 9, 5, 7, 2345678};
	      
	      // Open a binary file for output.
	      FileOutputStream fstream =
	                    new FileOutputStream("Numbers.dat");
	      DataOutputStream outputFile = 
	                    new DataOutputStream(fstream);
	      
	      System.out.println("Writing to the file...");
	      
	      // Write the array elements to the binary file.
	      for (int i = 0; i < numbers.length; i++)
	         outputFile.writeInt(numbers[i]);

	      // Close the file.      
	      outputFile.close();
	      System.out.println("   ***Done with writing a binary file.");
	}
	

/**
 *  This method opens a binary file, then reads and displays         
 *  the contents. 
 *
*/
	private static void readBinaryFile() throws IOException
	{
		int number;                // To hold a number
	      boolean endOfFile = false; // End of file flag
	      
	      // Open Numbers.dat as a binary file.
	      FileInputStream fstream =
	                      new FileInputStream("Numbers.dat");
	      DataInputStream inputFile =
	                      new DataInputStream(fstream);
	      
	      System.out.println("Reading numbers from the file:");
	      
	      // Read data from the file.
	      while (!endOfFile)
	      {
	           try
	           {
	                number = inputFile.readInt();
	                System.out.print(number + " ");
	           }
	           catch (EOFException e)
	           {
	                endOfFile = true;
	           }
	      }

	      // Close the file.
	      inputFile.close();
	      System.out.println("\n   ***Done with reading from a binary file.");
	   
	}
	
/**
 * This method opens a binary file and writes a series of strings
 * using UTF-8 encoding.
 */

	private static void writeUTFdataToBinaryFile() throws FileNotFoundException, IOException
	{
		String names[] = { "Warren", "Becky", "Holly", "Chloe" };
	      FileOutputStream fstream = new FileOutputStream("UTFnames.dat");
	      DataOutputStream outputFile = new DataOutputStream(fstream);
	      
	      System.out.println("Writing the names to the file...");
	      
	      for (int i = 0; i < names.length; i++)
	         outputFile.writeUTF(names[i]);
	      
	      System.out.println("   ***Done with writing strings to a binary file.");
	      outputFile.close();  
	}

/**
 * This method reads UTF-8 encoded strings from a binary file.
 */

	private static void readUTFdataFromBinaryFile() throws FileNotFoundException, IOException
	{
		String name;
	      boolean endOfFile = false;
	      FileInputStream fstream = new FileInputStream("UTFnames.dat");
	      DataInputStream inputFile = new DataInputStream(fstream);
	      
	      System.out.println("Reading the names from the file:");
	      while (!endOfFile)
	      {
	           try
	           {
	                name = inputFile.readUTF();
	                System.out.print(name + " ");
	           }
	           catch (EOFException e)
	           {
	                endOfFile = true;
	           }
	      }

	      System.out.println("\n   ***Done with reading strings (UTF data) from a binary file.");
	      inputFile.close();  
	}
	
/**
 *  This method uses a RandomAccessFile object to create
 *  the file Letters.dat. The letters of the alphabet are
 *  written to the file. 
 */

	private static void writeToRandomAccessFile() throws FileNotFoundException, IOException
	{
		// The letters array has all 26 letters of the alphabet.
	      char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	                         'h', 'i', 'j', 'k', 'l', 'm', 'n',
	                         'o', 'p', 'q', 'r', 's', 't', 'u',
	                         'v', 'w', 'x', 'y', 'z' };

	      System.out.println("Opening the random access file.");

	      // Open a file for reading and writing.
	      RandomAccessFile randomFile =
	                  new RandomAccessFile("Letters.dat", "rw");

	      System.out.println("Writing data to the random access file...");

	      // Sequentially write the letters array to the file.
	      for (int i = 0; i < letters.length; i++)
	         randomFile.writeChar(letters[i]);
	      
	      // Close the file.
	      randomFile.close();
	      System.out.println("   ***Done with writing to random access binary file.");
	}
	
/**
 *  This method uses the RandomAccessFile class
 *  to open the file Letters.dat and randomly read
 *  letters from different locations.  
 */

	private static void readFromRandomAccessFile() throws FileNotFoundException, IOException
	{
		final int CHAR_SIZE = 2;   // 2 byte characters
	      long byteNum;              // For the byte number
	      char ch;                   // To hold a character
	      
	      // Open the file for reading.
	      RandomAccessFile randomFile =
	                 new RandomAccessFile("Letters.dat", "r");
	      
	      // Move to character 5. This is the 6th character
	      // from the beginning of the file.
	      byteNum = CHAR_SIZE * 5;
	      randomFile.seek(byteNum);
	      
	      // Read the character stored at this location
	      // and display it. Should be the letter f.
	      ch = randomFile.readChar();
	      System.out.println(ch);
	      
	      // Move to character 10 (the 11th character),
	      // read the character, and display it.
	      // Should be the letter k.
	      byteNum = CHAR_SIZE * 10;
	      randomFile.seek(byteNum);
	      ch = randomFile.readChar();
	      System.out.println(ch);
	      
	      // Move to character 3 (the 4th character),
	      // read the character, and display it.
	      // Should be the letter d.
	      byteNum = CHAR_SIZE * 3;
	      randomFile.seek(byteNum);
	      ch = randomFile.readChar();
	      System.out.println(ch);
	      
	      // Close the file.
	      randomFile.close();
	      System.out.println("   ***Done with reading from a random access binary file.");
	}
}