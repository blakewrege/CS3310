//PROGRAM: Setup
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 3  **********************************

import java.awt.event.FocusAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Formatter;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Tells the RawData Class to use RawDataTest.csv

		String[][] cityNames = new String[0][];
		short[][] adjMatrix = new short[0][];
		int N = 0;
		Short city1, city2, distance;

		RawData input = new RawData("EuropeMapData.csv");
		UIoutput output = new UIoutput();

		int count = 0;

		while (input.input1Country()) // loop through the end of the file
		{
			if (input.getCode().contains("%") == false) {
				if (input.getCode().matches(".*\\d+.*") == false) {
					readCityName(input.getCode(), output);
				} else {
					String[] tokens = input.getCode().split(",");
					if (tokens.length == 2) {
						N = Integer.parseInt(tokens[1]);

						adjMatrix = new short[N][N];
						for (int r = 0; r < N; r++) {
							for (int c = 0; c < N; c++) {
								adjMatrix[r][c] = Short.MAX_VALUE;
							}
						}

						// System.out.println(input.getCode());

					} else {

						city1 = Short.parseShort(tokens[0]);
						city2 = Short.parseShort(tokens[1]);
						distance = Short.parseShort(tokens[2]);

						if (city1 == city2) {
							adjMatrix[city1][city2] = 0;
						} else {
							// When adding, make sure the row is the larger of
							// the
							// two indices.
							adjMatrix[Math.max(city1, city2)][Math.min(city1, city2)] = distance;
						}
					}

				}

			}

		}

		// Write map to file.
		RandomAccessFile mapGraphFile = new RandomAccessFile("MapGraph.bin", "rw");
		// mapGraphFile.seek(0);
		// mapGraphFile.writeShort(N);
		// for (int r = 0; r < N; r++) {
		// for (int c = 0; c < r; c++) {
		// mapGraphFile.writeShort(adjMatrix[r][c]);
		// }
		// }
		long x = 0;
		mapGraphFile.seek(0);
		System.out.println("length: " + mapGraphFile.length());
		while (x < mapGraphFile.length() - 2) {
			x = mapGraphFile.getFilePointer();
			System.out.println("pointer: " + mapGraphFile.getFilePointer() + " short: " + mapGraphFile.readShort());

		}

		mapGraphFile.close();

		input.finishUp();
		output.finishUp();
	}

	public static void readCityName(String theLine, UIoutput output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);

		String[] tokens = theLine.split(",");
		String name = tokens[0];
		String code = tokens[1];
		formatter.format("%-10s,%-3s ", name, code);
		output.displayThis(buf.toString());
		formatter.close();
	}
}
