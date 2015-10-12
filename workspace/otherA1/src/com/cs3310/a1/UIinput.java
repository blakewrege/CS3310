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
 * This class is responsible for processing the TransData file.
 */
public class UIinput {
    private BufferedReader br;
    private UIoutput log;
    public static int numberOfTransactions = 0;

    /**
     * Open the file for reading.
     * I could have hardcoded the filename: TransData.txt here, but chose not to
     * @param inFile
     * @param logFile
     */
    public UIinput(String inFile, UIoutput logFile) {
        try {
            this.log = logFile;
            this.br = new BufferedReader(new FileReader(inFile));
            this.log.display_this("-->> OPENED TransData file\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the next line, turn it into a transaction object
     * and return it (or null if at the EOF)
     * @return
     */
    public Transaction getNextTransaction() {
        String line = readNextLine();
        Transaction transaction;
        if (line != null) {
            transaction = new Transaction(line);
        } else {
            transaction = null;
        }

        return transaction;
    }

    /**
     * Find the next transaction and return it as a string.
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
     * Close the file
     */
    public void finish_up() {
        try {
            log.display_this("-->> CLOSED TransData file");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
