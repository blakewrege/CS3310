//PROGRAM: Setup
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage file

//******************************************  Assignment 2  *******************************************************

import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		RandomAccessFile file = new RandomAccessFile("CodeIndex3.bin", "rw");
		file.seek(0);
		int m = file.readShort();
		System.out.print(m);
		System.out.print(file.readShort());
		System.out.print(file.readShort() + "\n");
		
		while (file.getFilePointer() < file.length()) {
			for (int j = 0; j < m-1; j++) {

				System.out.print(file.readShort());
				for (int i = 0; i < 3; i++) {
					System.out.print((char) file.readByte());
				}
				System.out.print(file.readShort());

			}
			System.out.print(file.readShort());
			System.out.println();
		}
		file.close();
	}
}

