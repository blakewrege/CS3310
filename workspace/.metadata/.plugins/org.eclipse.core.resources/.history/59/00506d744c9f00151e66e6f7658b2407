
//CLASS: Map
//AUTHOR: Blake Wrege 
//************************************  Assignment 6  **********************************

import java.io.IOException;
import java.io.RandomAccessFile;

public class Map {
	private int N;
	private RandomAccessFile cityList;
	private RandomAccessFile mapGraph;

	public Map() throws IOException {
		this.N = 0;
		this.cityList = new RandomAccessFile("CityNameList.csv", "rws");
		this.mapGraph = new RandomAccessFile("MapGraph.bin", "rws");
		this.mapGraph.seek(0);
		this.N = mapGraph.readShort();

	}

	// Compares selected city name to list to find it's number
	public short getCityNumber(String cityName) throws IOException {
		for (short i = 0; i < N; i++) {
			String nameInList = getCityName(i).trim();
			if (nameInList.compareToIgnoreCase(cityName) == 0) {
				return i;
			}
		}
		return -1;
	}

	// closes files
	public void finishUp() throws IOException {
		this.cityList.close();
		this.mapGraph.close();
	}

	// Calculates road distance between 2 cities
	public short getRoadDistance(short cityNumber1, short cityNumber2) throws IOException {
		if (cityNumber1 == cityNumber2) {
			return 0;
		} else {
			int row = Math.max(cityNumber1, cityNumber2);
			int col = Math.min(cityNumber1, cityNumber2);
			int indexLoc = ((row - 1) * (row)) / 2 + col;
			int offset = 2 + indexLoc * 2;
			mapGraph.seek(offset);
			return mapGraph.readShort();
		}
	}

	// Gets city code of selected city number
	public String getCityCode(short cityNumber) throws IOException {
		cityList.seek(cityNumber * 16);
		return cityList.readLine().split(",")[1];
	}

	// Gets city name of selected city number
	public String getCityName(short cityNumber) throws IOException {
		cityList.seek(cityNumber * 16);
		return cityList.readLine().split(",")[0];
	}

	public short getN() {
		return (short) this.N;
	}

}
