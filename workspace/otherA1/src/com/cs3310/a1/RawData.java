package com.cs3310.a1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * This class is responsible for opening/reading/closing the RawData.csv file.
 * It is only used by the Setup program.
 */
public class RawData {
    private BufferedReader br;
    private Country country;
    private static short numCountries;
    private UIoutput log;

    /**
     * Open the file for reading.
     * I could have hardcoded the filename: RawData.csv in here, but decided against it.
     */
    public RawData(String inFile, UIoutput logFile) {
        try {
            RawData.numCountries = 0;   // keeps track of the number of countries we've input
            this.log = logFile;
            this.br = new BufferedReader(new FileReader(inFile));
            this.log.display_this("-->> OPENED RawData file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static short getNumCountries() {
        return numCountries;
    }

    /**
     * Get the next line of input, clean it up, turn it into a Country object
     * and return it (or null if at EOF).
     * @return
     */
    public Country inputOneCountry() {
        String line;
        String[] parts;
        line = readNextLine();

        if (line != null) {
            RawData.numCountries++;
            line = nullRemover(line);
            parts = split(line);
            country = assignStringParts(parts);
        } else {
            country = null;
        }

        return country;
    }

    /**
     * Close the log file
     */
    public void finish_up() {
        try {
            log.display_this("-->> CLOSED RawData file");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read another line and return it
     * @return
     */
    private String readNextLine() {
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    /**
     * Split the line on commas and return a string array
     * @param line
     * @return
     */
    private String[] split(String line) {
        return line.split(",");
    }

    /**
     * Turn those NULLs into 0s
     * @param line
     * @return
     */
    private String nullRemover(String line) {
        if (line.contains("NULL")) {
            line = line.replaceAll("NULL", "0");
        }

        return line;
    }

    /**
     * Take a string array and turn them into a Country object
     * @param parts
     * @return
     */
    private Country assignStringParts(String[] parts) {
        country = new Country();
        country.setId(Short.parseShort(parts[0]));
        country.setCode(parts[1]);
        country.setName(parts[2]);
        country.setContinent(parts[3]);
        country.setRegion(parts[4]);
        country.setArea(Integer.parseInt(parts[5]));
        country.setYearFounded(Short.parseShort(parts[6]));
        country.setPopulation(Integer.parseInt(parts[7]));
        country.setLifeExpectancy(Float.parseFloat(parts[8]));
        country.setField10(Integer.parseInt(parts[9]));
        country.setField11(parts[10]);
        country.setGovernmentForm(parts[11]);
        country.setLeader(parts[12]);
        country.setField14(Short.parseShort(parts[13]));
        country.setField15(parts[14]);

        return country;
    }
}
