import java.io.*;

/**
 * Created by cpg on 12/6/15.
 */
public class Setup {
    public static void main(String[] args) throws IOException {
        int N = 0;

        BufferedReader mapDataReader = new BufferedReader(new FileReader("EuropeMapData.csv"));

        // Start building the arrays.
        String[][] cityNames = new String[0][]; // N rows, 2 columns
        short[][] adjMatrix = new short[0][]; // row, col order

        String line = null;
        // Keep track of how many entries we have visited.
        int index = 0;
        // Keep track of whether we have read N yet.
        boolean readN = false;
        // Read the rest of the file.
        while ((line = mapDataReader.readLine()) != null) {
            if (!line.startsWith("%")) {
                String[] tokens = line.split(",");
                if (!readN) {
                    try {
                        N = Integer.parseInt(line.split(",")[1]);
                    } catch (NumberFormatException e) {

                    }
                    readN = true;
                    cityNames = new String[N][2];
                    adjMatrix = new short[N][N];
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < N; c++) {
                            adjMatrix[r][c] = Short.MAX_VALUE;
                        }
                    }
                } else if (index < N) {
                    // Add the city name and code to the array.
                    cityNames[index] = tokens;
                    index++;
                } else {
                    // Add to the adjacency matrix.
                    try {
                        Short city1 = Short.parseShort(tokens[0]);
                        Short city2 = Short.parseShort(tokens[1]);
                        Short distance = Short.parseShort(tokens[2]);

                        if (city1 == city2) {
                            adjMatrix[city1][city2] = 0;
                        } else {
                            // When adding, make sure the row is the larger of the two indices.
                            adjMatrix[Math.max(city1, city2)][Math.min(city1, city2)] = distance;
                        }
                    } catch (NumberFormatException e) {

                    }
                }
            }
        }

        // Write map to file.
        RandomAccessFile mapGraphFile = new RandomAccessFile("MapGraph.bin", "rws");
        mapGraphFile.seek(0);
        mapGraphFile.writeShort(N);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < r; c++) {
                mapGraphFile.writeShort(adjMatrix[r][c]);
            }
        }
        mapGraphFile.close();

        // Write city names to file.
        BufferedWriter cityNamesWriter = new BufferedWriter(new FileWriter("CityNameList.csv", false));
        for (int i = 0; i < N; i++) {
            cityNamesWriter.write(String.format("%-10s,%s\r\n", cityNames[i][0], cityNames[i][1]));
        }
        cityNamesWriter.close();
    }
}
