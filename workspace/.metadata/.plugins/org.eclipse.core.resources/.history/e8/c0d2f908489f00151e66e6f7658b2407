//PROGRAM: Setup
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them. 

//************************************  Assignment 6  **********************************

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		short[][] adjArr = new short[0][];
		int N = 0;
		Short city1, city2, distance;

		RawData input = new RawData("EuropeMapData.csv");
		Log output = new Log();

		while (input.input1Line()) // loop through the end of the file
		{
			// Checks if line is a comment
			if (input.getLine().contains("%") == false) {
				// Checks if line contains city names
				if (input.getLine().matches(".*\\d+.*") == false) {
					writeCityName(input.getLine(), output);
				} else {
					String[] tokens = input.getLine().split(",");
					// Checks if line is header record
					if (tokens.length == 2) {
						N = Integer.parseInt(tokens[1]);
						adjArr = new short[N][N];
						for (int r = 0; r < N; r++) {
							for (int c = 0; c < N; c++) {
								adjArr[r][c] = Short.MAX_VALUE;
							}
						}
					} else {
						// Sets city in adjacency matrix
						city1 = Short.parseShort(tokens[0]);
						city2 = Short.parseShort(tokens[1]);
						distance = Short.parseShort(tokens[2]);

						if (city1 == city2) {
							adjArr[city1][city2] = 0;
						} else {
							adjArr[Math.max(city1, city2)][Math.min(city1, city2)] = distance;
						}
					}

				}

			}

		}

		// Writes out the map to file.
		RandomAccessFile mapGraph = new RandomAccessFile("MapGraph.bin", "rws");
		mapGraph.seek(0);
		mapGraph.writeShort(N);
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < r; c++) {
				mapGraph.writeShort(adjArr[r][c]);
			}
		}
		mapGraph.close();
		input.finishUp();
		output.finishUp();

		// Used Log class to create CityNameList.csv cause I'm lazy
		File file = new File("Log.txt");
		File file2 = new File("CityNameList.csv");
		// Renames Log.txt to CityNameList.csv
		file.renameTo(file2);

	}

	// Writes city name to CityNameList.csv
	public static void writeCityName(String theLine, Log output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);

		String[] tokens = theLine.split(",");
		String name = tokens[0];
		String code = tokens[1];
		formatter.format("%-10s,%-3s \n", name, code);
		output.displayThis(buf.toString());
		formatter.close();
	}
}
